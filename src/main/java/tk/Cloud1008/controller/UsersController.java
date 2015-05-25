package tk.Cloud1008.controller;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.controller.base.RestBaseAction;
import tk.Cloud1008.entity.File;
import tk.Cloud1008.entity.Friend;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.service.FileService;
import tk.Cloud1008.service.FriendsService;
import tk.Cloud1008.service.UsersService;

@Controller
public class UsersController extends RestBaseAction { 
	
	@Autowired
	UsersService usersService;
	
	@Autowired
	FriendsService friendsService;
	
	@Autowired
	FileService fileService;
	
	private static final long serialVersionUID = 1L;
	private String id;
	private User user = new User();
	private List<User> users;
	private List<Friend> friends;
	private List<File> files;
	
	private Object model = user;
	
	private DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders("index").disableCaching();
	
	private String searchTerm;
	
	// Get /rest/users
	public HttpHeaders index() {
		if (searchTerm == null){
			users = usersService.getAll();
		} else {
			users = usersService.getAllBySearchTerm(searchTerm);
		}
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
		user.setPassword("");
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
	
	// GET /rest/users/{id}/friends
	public HttpHeaders friends() {
		user = usersService.get(Long.parseLong(id));
		friends = friendsService.getAllByUser(user);
		model = friends;
		return httpHeaders.withStatus(200);
	}
	
	// GET /rest/users/{id}/files
	public HttpHeaders files() {
		files = fileService.getByParentAndOwner(0, Long.parseLong(id));
		model = files;
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
	
	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
} 