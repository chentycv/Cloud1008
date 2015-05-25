package tk.Cloud1008.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import tk.Cloud1008.controller.base.PageBaseAction;
import tk.Cloud1008.service.FileService;
import tk.Cloud1008.service.HdfsService;
import tk.Cloud1008.service.UsersService;

@Namespace("/")
@Controller
public class DownloadController extends PageBaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	HdfsService hdfsService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	UsersService usersService;

	private String path;
	private String name;
	private InputStream inputStream;

	@Action(value = "download", results = { @Result(name = "success", type = "stream", params = { "contentType", "application/octet-stream" })})
	public String download() throws IOException {

		// Download file to /tmp
		hdfsService.download("/user/root/" + path, "/tmp/");
		inputStream = new FileInputStream(new File("/tmp/" + path ));
		return SUCCESS;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public InputStream getInputStream() {
	    return inputStream;
	}
	
	public String getContentDisposition() throws UnsupportedEncodingException {
		return "attachment;filename=\""+ java.net.URLEncoder.encode(name,"UTF-8") +"\"";
	}
}
