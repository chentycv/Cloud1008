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
	private long fromuserid;
	private long touserid;
	private long togroupid;
	private Share share = new Share();
	private List<Share> shares;
	
	private Object model = share;
	
	private DefaultHttpHeaders httpHeaders = new DefaultHttpHeaders("index").disableCaching();
	
	// Get /rest/shares
		public HttpHeaders index() {
			if(fromuserid!=0)
			{
				if(touserid!=0)
				{
					shares = shareService.getByFromUserToUser(fromuserid, touserid);
					model = shares;
					return httpHeaders.withStatus(200);
				}
				else 
				{
					shares = shareService.getByFromUser(fromuserid);
					model = shares;
					return httpHeaders.withStatus(200);			
				}
			}
			else if(touserid!=0)
			{
				shares = shareService.getByToUser(touserid);
				model = shares;
				return httpHeaders.withStatus(200);
			}
			else
			{
				shares = shareService.getByToGroup(togroupid);
				model = shares;
				return httpHeaders.withStatus(200);
			}

		}
			
		// Get /rest/shares/getByType
		public HttpHeaders getByType() {
			shares = shareService.getByType(fromuserid);
			model = shares;
			return httpHeaders.withStatus(200);
		}
		
		// POST /rest/shares
		public HttpHeaders create() throws IOException {
			shareService.add((Share)model);
			model = shares;
			return httpHeaders.withStatus(200);
		}
		
		
		// PUT /rest/shares/{id}
		public HttpHeaders update() {
			share.setId(Long.parseLong(id));
			shareService.update(share);
			model = share;
			return httpHeaders.withStatus(200);
		}
		
		// DELETE /rest/shares/{id}
		public HttpHeaders destroy() {
			share = new Share();
			share.setId(Long.parseLong(id));
			shareService.delete(share);
			model = share;
			return httpHeaders.withStatus(200);
		}
		
		
	/*	// GET /rest/users/{id}
		public HttpHeaders show() {
			share = shareService.;
			return new DefaultHttpHeaders("show");
		}*/
		
		public long getFromuserid() {
			return fromuserid;
		}

		public void setFromuserid(long fromuserid) {
			this.fromuserid = fromuserid;
		}

		public long getTouserid() {
			return touserid;
		}

		public void setTouserid(long touserid) {
			this.touserid = touserid;
		}

		public long getTogroupid() {
			return togroupid;
		}

		public void setTogroupid(long togroupid) {
			this.togroupid = togroupid;
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