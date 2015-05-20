package tk.Cloud1008.controller;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import tk.Cloud1008.controller.base.PageBaseAction;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.interceptor.LoginInterceptor;
import tk.Cloud1008.service.UsersService;

@Namespace("/")
@Controller
public class UserLoginController extends PageBaseAction implements ServletResponseAware, ServletRequestAware, SessionAware{

	private static final long serialVersionUID = 1L;

	@Autowired
	UsersService usersService;

	private String loginName;
	private String password;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private Map session;
	private User user;

	@Action("register")
	public String register() {
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		this.user = usersService.getByLoginName(user);
		if ( this.user == null ){ 
			if (loginName.length() >= 3 && password.length() >= 6) {
				this.user = new User();
				this.user.setLoginName(loginName);
				this.user.setPassword(password);
				this.usersService.save(user);
				return SUCCESS;
			} else {
				return BADREQUEST;
			}
		} else {
			return CONFILCT;
		}
	}

	@SuppressWarnings("unchecked")
	@Action("login")
	public String login(){
		User user = new User();
		user.setLoginName(loginName);
		user.setPassword(password);
		this.user = usersService.getByLoginNameAndPassword(user);
		Cookie cookie;
		if ( user != null ){
			try {
				
				// Token cookie
				cookie = new Cookie(LoginInterceptor.COOKIE_REMEMBERME_KEY, usersService.getCurrentCookiesValue());
				cookie.setMaxAge(60 * 60 * 24 * 14);
				response.addCookie(cookie);
				
				// UserId cookie
				cookie = new Cookie(LoginInterceptor.COOKIE_USER_ID, this.user.getId().toString());
				cookie.setMaxAge(60 * 60 * 24 * 14);
				response.addCookie(cookie);
				session.put(LoginInterceptor.USER_SESSION_KEY, this.user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return SUCCESS;
		} else {
			return NOTFOUND;
		}
	}
	
	@Action("logout")
	public String logout() throws Exception{
		HttpSession session = request.getSession(false);
		if (session!=null)
			session.removeAttribute(LoginInterceptor.USER_SESSION_KEY);
		
		Cookie[] cookies = request.getCookies();
		if (cookies!=null) {
			for (Cookie cookie : cookies) {
				if (LoginInterceptor.COOKIE_REMEMBERME_KEY.equals(cookie
						.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
				
				if (LoginInterceptor.COOKIE_USER_ID.equals(cookie
						.getName())) {
					cookie.setValue("");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
				}
			}
		}
		return SUCCESS;
	}

	
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	public void setSession(Map session) {
		this.session = session;
	}	
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
