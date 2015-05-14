package tk.Cloud1008.controller.base;

import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionSupport;

@ParentPackage("page-package")
public class PageBaseAction extends ActionSupport {

	private static final long serialVersionUID = 2323603138082550798L;

    public static final String NOTFOUND = "notfound";
    
    public static final String BADREQUEST = "badrequest";
    
    public static final String CONFILCT = "conflict";
}
