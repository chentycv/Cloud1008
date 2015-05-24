package tk.Cloud1008.controller;

import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.controller.base.PageBaseAction;
import tk.Cloud1008.service.FileService;
import tk.Cloud1008.service.HdfsService;
import tk.Cloud1008.service.UsersService;

@Namespace("/")
@Controller
public class UploadController extends PageBaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	HdfsService hdfsService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	UsersService usersService;

	private String contentType;
	private String filename;
	private File file;
	private InputStream inputStream;
	
	@SuppressWarnings("deprecation")
	@Action(value = "upload", results = { @Result(name="success", type="stream") })
	public String upload() {

		// Send the file to the hadoop server
		hdfsService.upload("/user/root/", file.getAbsolutePath());

		inputStream = new StringBufferInputStream(file.getName()); 
		hdfsService.download("/user/root/fuck", "/tmp/out/");
		return SUCCESS;
	}
	
	public void setUpload(File file) {
		this.file = file;
	}

	public void setUploadContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setUploadFileName(String filename) {
		this.filename = filename;
	}
	
	public InputStream getInputStream() {
	    return inputStream;
	}
}
