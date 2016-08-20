package com.bclw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bclw.bean.Admin;
import com.bclw.bean.Teacher;



public class LoginFilter implements Filter {
	String						permitUrls[]	= null;
	private boolean				ignore			= false;
	private String				gotoUrl			= null;	// 默认链接
	private HttpServletRequest	req				= null;
	private HttpServletResponse	res				= null;
	private HttpSession			session			= null;
	private Cookie[]			cookies			= null;

	public void destroy() {
		permitUrls = null;
		ignore = false;
		gotoUrl = null;

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		req = (HttpServletRequest) request;
		res = (HttpServletResponse) response;
		session = req.getSession(false);
	/*	AdminServiceImp asi;*/

		System.out.println("登录过滤器");

		//自动登陆
		/*if (session == null) {
			System.out.println("session is null");
			cookies = req.getCookies();
			if (cookies != null) {
				System.out.println("get cookies");
				String id = null;
				String uname = null;
				String passwd = null;
				for (Cookie c : cookies) {
					if (c.getName().equals("id"))
						id = c.getValue();
					if (c.getName().equals("uname"))
						uname = c.getValue();
					if (c.getName().equals("passwd"))
						passwd = c.getValue();
				}
				if (id != null && uname != null && passwd != null) {
					if (id.equals("admin")) {
						System.out.println("get AdminCookie");
						Admin admin = null;
						asi = new AdminServiceImp();
						admin = asi.findadminuser(uname);
						if (admin != null) {
							session = req.getSession(true);
							session.setMaxInactiveInterval(10 * 60);
							session.setAttribute("admin", admin);
						}
					}
					if (id.equals("teacher")) {
						Teacher teacher = null;
						asi = new AdminServiceImp();
						teacher = asi.findteacheruser(uname);
						if (teacher != null) {
							session = req.getSession(true);
							session.setMaxInactiveInterval(10 * 60);
							session.setAttribute("teacher", teacher);
						}
					}
				}
			}
		}*/
		
		if (!ignore) {
			if (!isPermitUrl()) {
				if (filterCurrUrl()) {
					res.sendRedirect(req.getContextPath() + gotoUrl);
					return;
				}
			}

		}
		chain.doFilter(request, response);
	}

	public boolean isPermitUrl() {
		boolean isPermit = false;

		if (permitUrls != null && permitUrls.length > 0) {
			for (int i = 0; i < permitUrls.length; i++) {
				if (permitUrls[i].equals(currentUrl(req))) {
					isPermit = true;
					break;
				}
			}
		}
		System.out.println("isPermit=" + isPermit);
		return isPermit;
	}

	public boolean filterCurrUrl() {

		boolean filter = false;
		if(session!=null)
		{
			try {

				String uri = req.getRequestURI();
				if (uri.contains("/Admin/")) {
					if ( session.getAttribute("admin") == null){
						filter = true;
					}
				}

				if (uri.contains("/Teacher/")) {
					 
					if (session.getAttribute("teacher") == null) {
						filter = true;
					}
				}
			} catch (NullPointerException ex) {
				filter = true;
				ex.printStackTrace();
			} catch (Exception ex) {
				filter = true;
				ex.printStackTrace();
			}
		}
		else
		{
			filter=true;
		}
		
		
		System.out.println("filter=" + filter);
		return filter;
	}

	// xx.jsp
	// servlet/aaServlet?task=11&bb=yy

	public String currentUrl(ServletRequest request) {

		String path = req.getContextPath();
		String uri = req.getRequestURI();

		uri = uri.substring(path.length(), uri.length());

		System.out.println("当前请求地址:" + uri);
		return uri;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String ignore = filterConfig.getInitParameter("ignore");
		String permitUrls = filterConfig.getInitParameter("permitUrls");
		String gotoUrl = filterConfig.getInitParameter("gotoUrl");

		if ("1".equals(ignore) || "yes".equals(ignore) || "true".equals(ignore)) {
			this.ignore = true;
		}
		if (permitUrls != null && permitUrls.length() > 0)
			;
		this.permitUrls = permitUrls.split(",");

		this.gotoUrl = gotoUrl;
	}

}