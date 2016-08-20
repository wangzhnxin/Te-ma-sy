<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
    
  </head>
  <body class="bodyborder">
<h3>${msg}</h3>
 <form
		action="${pageContext.request.contextPath}/Admin/servlet/classManServlet?action=update&id=${OneData.cid}"
		method="post">
		<table border="1">
			<tr>
				<td>班级名称</td>
				<td>专业</td>
				<td>说明</td>
				<td></td>
			</tr>
			<tr>
				<td><input type="text" size=10 name=name value="${OneData.cname}"/></td>
				<td><select name="pid">
						<c:forEach items="${profs}" var="member">
							<option value="${member.pid}">${member.pname}</option>
						</c:forEach>
				</select></td>
				<td><input type="text" size=10 name=cexp value="${OneData.cexp }"/></td>
				<td><input type="submit" value="确认" /></td>
			</tr>
		</table>
	</form>
 <td><a href="${pageContext.request.contextPath}/Admin/servlert/classManServlet?action=select">返回</a></td>

</body>
</html>