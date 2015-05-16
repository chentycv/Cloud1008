package tk.Cloud1008.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.entity.File;
import tk.Cloud1008.entity.Share;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.service.FileService;
import tk.Cloud1008.service.ShareService;
import tk.Cloud1008.service.UsersService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class SharesController extends ActionSupport implements ModelDriven<Object> { 
	
	@Autowired
	ShareService shareService;
	
	private static final long serialVersionUID = 1L;
	private String id;
	private Share share = new Share();
	private List<Share> shares;
	
	private Object model = share;
	
	private DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders("index").disableCaching();
	
/*	// Get /rest/files
	public HttpHeaders index() {
		files = fileService.select(0, 0);
		model = files;
		return new DefaultHttpHeaders("index").disableCaching();
	}*/
	
	// POST /rest/files
	public HttpHeaders create() throws IOException {
		shareService.add((Share)model);
		model = shares;
		return httpHeaders.withStatus(200);
	}
	
	
	// PUT /rest/files/{id}
	public HttpHeaders update() {
		share.setId(Long.parseLong(id));
		shareService.update(share);
		model = share;
		return httpHeaders.withStatus(200);
	}
	
	// DELETE /rest/files/{id}
	public HttpHeaders destroy() {
		share = new Share();
		share.setId(Long.parseLong(id));
		shareService.delete(share);
		model = share;
		return httpHeaders.withStatus(200);
	}
	
	
/*	// GET /rest/users/{id}
	public HttpHeaders show() {
		file = usersService.get(Long.parseLong(id));
		return new DefaultHttpHeaders("show");
	}
	
	// Get /rest/users/{id}/files
	public String files(){
		return "SUCCESS";
	}
	*/
	
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