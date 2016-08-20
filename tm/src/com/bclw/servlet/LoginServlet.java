package com.bclw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bclw.bean.Admin;
import com.bclw.bean.Teacher;
import com.bclw.service.AdminServiceImp;
import com.bclw.tool.*;

public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1043100742543184200L;
	private AdminServiceImp	asi		= new AdminServiceImp();
	private HttpSession		session	= null;

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			String uname = request.getParameter("username");// 表单用户名
			String passwd = request.getParameter("pwd");// 表单密码
			String id = request.getParameter("identity");// 用户身份
			String vcode = (String) request.getSession().getAttribute("code");// 获取验证码图片的值
			String code = request.getParameter("code");// 获取用户输入密码
			String flag = request.getParameter("isLogin");// 是否自动登陆
			String MD5upass = MD5Util.MD5(passwd);// 密码转MD5
			PrintWriter out = response.getWriter();
			Cookie cookie;
			if (code.equalsIgnoreCase(vcode)) {// 对比验证码
				System.out.println("验证码正确");
				session = request.getSession(true);
				session.setMaxInactiveInterval(30 * 60);
				System.out.println("id=" + id);
				switch (id) {
					case "admin": {
						Admin admin = null;
						admin = asi.findadminuser(uname);
						if (admin != null) {
							System.out.println("admin not null");
							if (admin.getUpass().equals(MD5upass)) {
								session.setAttribute("admin", admin);

								cookie = new Cookie("id", id);
								cookie.setMaxAge(60 * 10);
								response.addCookie(cookie);

								cookie = new Cookie("passwd", MD5upass);
								cookie.setMaxAge(60 * 10);
								response.addCookie(cookie);

								cookie = new Cookie("uname", uname);
								cookie.setMaxAge(60 * 10);
								response.addCookie(cookie);

								if ("y".equals(flag)) {// 自动保存密码
									// 创建两个Cookie对象
									Cookie nameCookie = new Cookie("autoname",
											uname);
									// 设置Cookie的有效期为3天
									nameCookie.setMaxAge(60 * 60 * 24 * 3);
									nameCookie.setPath("/");
									Cookie pwdCookie = new Cookie("autoword",
											passwd);
									pwdCookie.setMaxAge(60 * 60 * 24 * 3);
									pwdCookie.setPath("/");
									response.addCookie(nameCookie);
									response.addCookie(pwdCookie);
								}

								request.getRequestDispatcher(
										"../Admin/Admin.jsp").forward(request,
										response);
								/* response.sendRedirect("../Admin/Admin.jsp"); */

							}
						}
						out.println("登陆失败");
						out.println("<a href=\"../login.jsp\">登陆失败,点击返回</a>");

					}
						break;
					case "teacher": {
						Teacher teacher = null;
						teacher = asi.findteacheruser(uname);
						if (teacher != null) {
							if (teacher.getUpass().equals(MD5upass)) {
								out.println("登陆失败");
								out.println("<a href=\"../login.jsp\">登陆失败,点击返回</a>");
								
								session.setAttribute("teacher", teacher);
								cookie = new Cookie("id", id);
								cookie.setMaxAge(60 * 10);
								response.addCookie(cookie);

								cookie = new Cookie("passwd", passwd);
								cookie.setMaxAge(60 * 10);
								response.addCookie(cookie);

								cookie = new Cookie("uname", uname);
								cookie.setMaxAge(60 * 10);
								response.addCookie(cookie);

								if ("y".equals(flag)) {// 自动保存密码
									// 创建两个Cookie对象
									Cookie nameCookie = new Cookie("autoname",
											uname);
									// 设置Cookie的有效期为3天
									nameCookie.setMaxAge(60 * 60 * 24 * 3);
									nameCookie.setPath("/");
									Cookie pwdCookie = new Cookie("autoword",
											passwd);
									pwdCookie.setMaxAge(60 * 60 * 24 * 3);
									pwdCookie.setPath("/");
									response.addCookie(nameCookie);
									response.addCookie(pwdCookie);
								}
								System.out.println("登陆成功");

								request.getRequestDispatcher(
										"../Teacher/teacher.jsp").forward(
										request, response);
								/*
								 * response.sendRedirect("../Teacher/teacher.jsp"
								 * );
								 */

							} else {
								out.println("登陆失败");
								out.println("<a href=\"../login.jsp\">登陆失败,点击返回</a>");
							}
						}
					}
						break;
					default: {
						out.println("身份错误");
						out.println("<a href=\"../login.jsp\">身份错误,点击返回</a>");
					}
				}
			} else {// 验证码错误时
				System.out.print(vcode);
				out.print("验证码错误！");
				out.println("<a href=\"../login.jsp\">验证码错误,点击返回</a>");
				// response.sendRedirect("login.html");
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect("/erro/404.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/erro/404.jsp");
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
