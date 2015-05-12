package tk.Cloud1008.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.service.UsersService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class UsersController extends ActionSupport implements ModelDriven<Object> { 
	
	@Autowired
	UsersService usersService;
	
	private static final long serialVersionUID = 1L;
	private String id;
	private User user = new User();
	private List<User> users;
	
	private Object model = user;
	
	// Get /rest/users
	public HttpHeaders index() {
		users = usersService.getAll();
		model = users;
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	// POST /rest/users
	public HttpHeaders create() throws IOException {
		usersService.save((User)model);
		return null;
	}
	
	// GET /rest/users/{id}
	public HttpHeaders show() {
		user = usersService.get(Long.parseLong(id));
		return new DefaultHttpHeaders("show");
	}
	
	// PUT /rest/users/{id}
	public HttpHeaders update() {
		user.setId(Long.parseLong(id));
		usersService.update(user);
		return null;
	}

	// DELETE /rest/users/{id}
	public HttpHeaders destroy() {
		user = new User();
		user.setId(Long.parseLong(id));
		usersService.delete(user);
		return null;
	}
	
	// Get /rest/users/{id}/files
	public String files(){
		return "SUCCESS";
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