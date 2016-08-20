<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'Teacher.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>
<frameset rows='70px,*' border=0 scrolling="no">
		<frame src='${pageContext.request.contextPath}/Teacher/public/top.jsp' name='top'/>
		<frameset cols='150px,*'>
			<frame src='${pageContext.request.contextPath}/Teacher/public/left.jsp' scrolling="no" name='left'/>
			<frame src='${pageContext.request.contextPath}/Teacher/public/right.jsp' scrolling="no" name='right'/>
		</frameset>
	</frameset>
</html>
