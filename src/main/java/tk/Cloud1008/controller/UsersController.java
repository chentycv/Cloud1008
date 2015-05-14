package tk.Cloud1008.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.controller.base.RestBaseAction;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.service.UsersService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class UsersController extends RestBaseAction { 
	
	@Autowired
	UsersService usersService;
	
	private static final long serialVersionUID = 1L;
	private String id;
	private User user = new User();
	private List<User> users;
	
	private Object model = user;
	
	private DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders("index").disableCaching();
	
	
	// Get /rest/users
	public HttpHeaders index() {
		users = usersService.getAll();
		model = users;
		return httpHeaders.withStatus(200);
	}
	
	// POST /rest/users
	public HttpHeaders create() throws IOException {
		usersService.save((User)model);
		model = user;
		return httpHeaders.withStatus(200);
	}
	
	// GET /rest/users/{id}
	public HttpHeaders show() {
		user = usersService.get(Long.parseLong(id));
		model = user;
		return httpHeaders.withStatus(200);
	}
	
	// PUT /rest/users/{id}
	public HttpHeaders update() {
		user.setId(Long.parseLong(id));
		usersService.update(user);
		model = user;
		return httpHeaders.withStatus(200);
	}

	// DELETE /rest/users/{id}
	public HttpHeaders destroy() {
		user = new User();
		user.setId(Long.parseLong(id));
		usersService.delete(user);
		model = user;
		return httpHeaders.withStatus(200);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Object getModel() {
		return model;
	}
} 