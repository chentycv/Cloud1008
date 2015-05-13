package tk.Cloud1008.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.entity.FileEntity;
import tk.Cloud1008.entity.ShareEntity;
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
	private ShareEntity share = new ShareEntity();
	private List<ShareEntity> shares;
	
	private Object model = share;
	
	
/*	// Get /rest/files
	public HttpHeaders index() {
		files = fileService.select(0, 0);
		model = files;
		return new DefaultHttpHeaders("index").disableCaching();
	}*/
	
	// POST /rest/files
	public HttpHeaders create() throws IOException {
		shareService.add((ShareEntity)model);
		return null;
	}
	
	
	// PUT /rest/files/{id}
	public HttpHeaders update() {
		share.setId(Long.parseLong(id));
		shareService.update(share);
		return null;
	}
	
	// DELETE /rest/files/{id}
	public HttpHeaders destroy() {
		share = new ShareEntity();
		share.setId(Long.parseLong(id));
		shareService.delete(share);
		return null;
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