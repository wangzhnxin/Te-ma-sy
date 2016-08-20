<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">

<style>
.bodybg{
	background:#eee;
}
li{
    width:120px;
    font-family:宋体;
    font-size:17px;
    text-align:center;
    margin:10px 0px 0px -35px;
	list-style-type:none;
    //border:1px solid red;


}
li a{
	padding:5px;
	text-decoration:none;
    color:#333;
    //border:1px solid red;
}
li a:hover{
	background:#e56a78;
	color:#fff;
}
</style>
<body class="bodybg">
 
 <ul> 
<li><a href="${pageContext.request.contextPath}/Admin/servlet/AcayearServlet?action=select" target="right">学年管理</a></li>
<li><a href="${pageContext.request.contextPath}/Admin/servlet/profManServlet?action=select" target="right">专业管理</a></li>
<li><a href="${pageContext.request.contextPath}/Admin/servlet/classManServlet?action=select" target="right">班级管理</a></li>
<li><a href="${pageContext.request.contextPath}/Admin/servlet/courseManServlet?action=select" target="right">课程管理</a></li>
<li><a href="${pageContext.request.contextPath}/Admin/servlet/TeacherManServlet?action=select" target="right">教师管理</a></li>
<li><a href="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?action=select" target="right">课程节次管理</a></li>
<li><a href="${pageContext.request.contextPath}/Admin/servlet/courseArgServlet?action=select" target="right">课程安排</a></li>
 </ul>

  </body>





