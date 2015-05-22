package tk.Cloud1008.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.controller.base.RestBaseAction;
import tk.Cloud1008.entity.File;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.service.FileService;
import tk.Cloud1008.service.HdfsService;
import tk.Cloud1008.service.UsersService;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class FilesController extends RestBaseAction { 
	
	@Autowired
	FileService fileService;
	
	private static final long serialVersionUID = 1L;
	private String id;
	private long parentid;
	private long ownerid;


	private File file = new File();
	private List<File> files;
	
	private Object model = file;
	
	private DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders("index").disableCaching();
	
	// Get /rest/files
	public HttpHeaders index() {
		files = fileService.select(parentid,ownerid);
		model = files;
		return httpHeaders.withStatus(200);
	}
	
	// POST /rest/files
	public HttpHeaders create() throws IOException {
		fileService.add((File)model);
		model = file;
		return httpHeaders.withStatus(200);
	}
	
	
	// PUT /rest/files/{id}
	public HttpHeaders update() {
		file.setId(Long.parseLong(id));
		fileService.update(file);
		model = file;
		return httpHeaders.withStatus(200);
	}
	
	// DELETE /rest/files/{id}
	public HttpHeaders destroy() {
		file = new File();
		file.setId(Long.parseLong(id));
		fileService.delete(file);
		model = file;
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

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public long getOwnerid() {
		return ownerid;
	}

	public void setOwnerid(long ownerid) {
		this.ownerid = ownerid;
	}

} 