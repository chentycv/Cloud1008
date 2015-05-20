package tk.Cloud1008.controller;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.controller.base.RestBaseAction;
import tk.Cloud1008.entity.Friend;
import tk.Cloud1008.service.FriendsService;

@Controller
public class FriendsController extends RestBaseAction { 
	
	@Autowired
	FriendsService friendsService;
	
	private static final long serialVersionUID = 1L;
	private String id;
	private Friend friend = new Friend();
	private List<Friend> friends;
	
	private Long userAId;
	private Object model = friend;
	
	private DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders("index").disableCaching();

	
	// Get /rest/friends
	public HttpHeaders index() {
		friends = friendsService.getAll();
		model = friends;
		return httpHeaders.withStatus(200);
	}
	
	// POST /rest/friends
	public HttpHeaders create() throws IOException {
		friend = friendsService.save((Friend)model);
		model = friend;
		return httpHeaders.withStatus( friend != null ? 200 : 409 );
	}
	
	// GET /rest/friends/{id}
	public HttpHeaders show() {
		friend = friendsService.get(Long.parseLong(id));
		model = friend;
		return httpHeaders.withStatus(200);
	}
	
	// PUT /rest/friends/{id}
	public HttpHeaders update() {
		friend.setId(Long.parseLong(id));
		friendsService.update(friend);
		model = friend;
		return httpHeaders.withStatus(200);
	}

	// DELETE /rest/friends/{id}
	public HttpHeaders destroy() {
		Friend friend = (Friend)model;
		friendsService.delete(friend);
		model = friend;
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