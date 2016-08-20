<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<style>
.bodybg {
	background: #eee;
}

.admin {
	width: 200px;
	float: right;
}

.adminname {
	margin-top: -20px;
	float: left;
}
</style>
<body class="bodybg">
	<div class="adminname">
		<h1>管理系统</h1>
	</div>
	<div class="admin">
		欢迎管理员${sessionScope.admin.uname}
		<%
			ServletContext context = session.getServletContext();
			Integer count = (Integer) context.getAttribute("count");
		%>
		<br> 在线人数：<%=count%>
		<br> <a
			href="${pageContext.request.contextPath}/servlet/UserExitServlet"
			target="_parent">退出</a>
	</div>
</body>

