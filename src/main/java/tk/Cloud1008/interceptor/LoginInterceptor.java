package tk.Cloud1008.interceptor;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import tk.Cloud1008.entity.User;
import tk.Cloud1008.exceptions.InvalidCookiesException;
import tk.Cloud1008.service.UsersService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Controller
public class LoginInterceptor extends AbstractInterceptor {
	
	@Autowired
	UsersService usersService;	
	
	public static final String USER_SESSION_KEY="wallet.session.user";
	public static final String COOKIE_REMEMBERME_KEY="wallet.cookie.rememberme";
	public static final String COOKIE_USER_ID="wallet.cookie.userid";

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request= (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
		
		// Check if the current session is not a new one
		Map session = actionContext.getSession();
		if (session != null && session.get(USER_SESSION_KEY) != null){
			return invocation.invoke();
		}
		
		// Current session is a new one so login with cookies
		Cookie[] cookies = request.getCookies();
		try{
			if (cookies != null) {	
				
				User user = null;
				Long userId = null;
				Cookie tokenCookie = null;
				Cookie userIdCookie;
				
				// Get the value of cookies
				for (Cookie cookie : cookies) {
					if (COOKIE_REMEMBERME_KEY.equals(cookie.getName())){
						user = usersService.getByCooikes(cookie.getValue());
						tokenCookie = cookie;
					} else if (COOKIE_USER_ID.equals(cookie.getName())){
						userId = Long.parseLong(cookie.getValue());
					}
				}
				if (user != null && userId == user.getId()){ 
					
					// Add the token cookie
					session.put(LoginInterceptor.USER_SESSION_KEY,user);
					tokenCookie.setValue(usersService.getCurrentCookiesValue());
					response.addCookie(tokenCookie);
					
					// Add the userid cookie
					userIdCookie = new Cookie(COOKIE_USER_ID, userId.toString()); 
					userIdCookie.setMaxAge(60 * 60 * 24 * 14);
					response.addCookie(tokenCookie);
					
					
					// Bypass the action
					return invocation.invoke();
				}
			}
		} catch (InvalidCookiesException e){
			
		}
		
		// Login with cookies fail so login with password
		return "badrequest";
	}
}