package tk.Cloud1008.controller;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.entity.File;
import tk.Cloud1008.service.FileManager;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class FileController extends ActionSupport implements ModelDriven<Object> { 
	
	@Autowired
	FileManager fileManager;
	
	private static final long serialVersionUID = 1L;
	private String id;
	private Object model;
	
	public HttpHeaders index() {
		return new DefaultHttpHeaders("index").disableCaching();
	}
	
	public String add(){
		File file = new File();
		file.setName("fuck");
		file.setPath("fuck");
		file.setSize(111L);
		fileManager.addFile(file);
		model = file;
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