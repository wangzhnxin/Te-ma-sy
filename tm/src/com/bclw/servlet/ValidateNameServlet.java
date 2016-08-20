package com.bclw.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bclw.service.AdminServiceImp;

public class ValidateNameServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1612393418297197017L;
	protected AdminServiceImp adsi;
	protected String vname;//接受用户名;
	protected String table;//要查询的表格;
	protected boolean isyes=false;//是否存在;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		adsi= new AdminServiceImp();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(request.getParameter("name")!=null){
			vname=request.getParameter("name");
/*			vname=new String(vname.getBytes("ISO-8859-1"),"utf-8");*/
		}
		if(request.getParameter("table")!=null){
			table=request.getParameter("table");
		}
		switch (table) {
		case "admin":{
			if((adsi.findadminuser(vname)!=null)||(adsi.findteacheruser(vname)!=null)){
				out.print("<font color='green'>√</font>");
/*				System.out.print(adsi.findadminuser(vname).getUname());*/
			}else{
				out.print("<font color='red'>用户不存在</font>");
			}
			break;
		}
		case "acayear":{
			if(adsi.findAcaname(vname)!=null){
				out.print("<font color='red'>学年已存在</font>");
			}else{
				out.print("<font color='green'>√</font>");
			}
			break;
		}
		case "class":{
			if(adsi.findCname(vname)!=null){
				out.print("<font color='red'>班级已存在</font>");
			}else{
				out.print("<font color='green'>√</font>");
			}
			break;
		}
		case "course":{
			if(adsi.findConame(vname)!=null){
				out.print("<font color='red'>课程已存在</font>");
			}else{
				out.print("<font color='green'>√</font>");
			}
/*			System.out.println(adsi.findConame(vname).getConame());*/
			break;
		}
		case "prof":{
			System.out.println(vname);
			if(adsi.findPname(vname)!=null){
				out.print("<font color='red'>专业已存在</font>");
			}else{
				out.print("<font color='green'>√</font>");
			}
			break;
		}
		case "section":{
			if(adsi.findSname(vname)!=null){
				out.print("<font color='red'>节次已存在</font>");
			}else{
				out.print("<font color='green'>√</font>");
			}
			break;
		}
		case "teacher":{
			if(adsi.findteacheruser(vname)!=null){
				out.print("<font color='red'>该教师用户名已存在</font>");
			}else{
				out.print("<font color='green'>√</font>");
			}
			break;
		}
		default:
			break;
		}
		
		

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
/*<servlet>
<servlet-name>ValidateNameServlet</servlet-name>
<servlet-class>com.bclw.servlet.ValidateNameServlet</servlet-class>
</servlet>
<servlet-mapping>
<servlet-name>ValidateNameServlet</servlet-name>
<url-pattern>/servlet/ValidateNameServlet</url-pattern>
</servlet-mapping>*/
