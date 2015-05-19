package tk.Cloud1008.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.controller.base.RestBaseAction;
import tk.Cloud1008.entity.Group;
import tk.Cloud1008.entity.UserGroup;
import tk.Cloud1008.service.GroupService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class GroupsController extends RestBaseAction { 
	
	@Autowired
	GroupService GroupsService;
	
	private static final long serialVersionUID = 1L;
	private String id;
	private Group Group = new Group();
	private List<Group> Groups;
	private List<UserGroup> usersgroups;
	
	private Object model = Group;
	
	private DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders("index").disableCaching();
	
	// Get /rest/groups
	public HttpHeaders index() {
		Groups = GroupsService.getAll();
		model = Groups;
		return httpHeaders.withStatus(200);
	}
	
	// POST /rest/groups
	public HttpHeaders create() throws IOException {
		GroupsService.save((Group)model);
		model = Group;
		return httpHeaders.withStatus(200);
	}
	
	// GET /rest/groups/{id}
	public HttpHeaders show() {
		Group = GroupsService.get(Long.parseLong(id));
		model = Group;
		return httpHeaders.withStatus(200);
	}
	
	// GET /rest/groups/{id}/users
	public HttpHeaders users() {
		usersgroups = GroupsService.getUserGroup(Long.parseLong(id));
		model = usersgroups;
		return httpHeaders.withStatus(200);
	}
	
	// PUT /rest/groups/{id}
	public HttpHeaders update() {
		Group.setId(Long.parseLong(id));
		GroupsService.update(Group);
		model = Group;
		return httpHeaders.withStatus(200);
	}

	// DELETE /rest/groups/{id}
	public HttpHeaders destroy() {
		Group = new Group();
		Group.setId(Long.parseLong(id));
		GroupsService.delete(Group);
		model = Group;
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