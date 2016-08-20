<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style>
.bodybg{
	background:#eee;
}
.admin{
	width:200px;
	float:right;
}
.adminname{
  margin-top:-20px;
  float:left;
}
</style>
<body class="bodybg">
    <div class="adminname"><h1>教师</h1></div>
    <div class="admin">欢迎教师:${sessionScope.teacher.tname} <% 
  ServletContext context=session.getServletContext(); 
  Integer count=(Integer)context.getAttribute("count"); 
  %> 
  <br>
  在线人数：<%=count %> 
 <br>
 <a href="${pageContext.request.contextPath}/servlet/UserExitServlet"  target="_parent">退出</a> 
  </div>
    </body>

