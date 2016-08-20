<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
</head>
<body class="bodyborder">
	<h4>${msg}</h4>
	班级管理
	<c:choose>
		<c:when test="${! empty currentPage}">
			<table border="1" class="bordered">
				<tr>
					<th>班级名称</th>
					<th>专业</th>
					<th>说明</th>
				<th colspan="2">操作</th>
				</tr>
				<!-- 迭代输出 -->
				<c:forEach items="${currentPage}" var="member">
					<tr>
						<td>${member.cname}</td>
						<td>${member. pname}</td>
						<td>${member. cexp}</td>
						<td><a
							href="${pageContext.request.contextPath}/Admin/servlet/classManServlet?&action=delete&id=${member.cid }&currentPageNo=${currentPageNo}">删除</a></td>
						<td>
							<!-- 通过servlet跳转到修改页面 --> <a
							href="${pageContext.request.contextPath}/Admin/servlet/classManServlet?action=redirect&name=${member.cname}&currentPageNo=${currentPageNo}">编辑</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<!-- 翻页 -->

			<a
				href="${pageContext.request.contextPath}/Admin/servlet/classManServlet?action=select&currentPageNo=1">首页</a>&nbsp;
		<c:if test="${currentPageNo>1}">
				<a
					href="${pageContext.request.contextPath}/Admin/servlet/classManServlet?action=select&currentPageNo=${currentPageNo-1}">上一页</a>&nbsp;
		</c:if>
			<c:if test="${currentPageNo<totalPage}">
				<a
					href="${pageContext.request.contextPath}/Admin/servlet/classManServlet?action=select&currentPageNo=${currentPageNo+1}">下一页</a>&nbsp;
		</c:if>
			<a
				href="${pageContext.request.contextPath}/Admin/servlet/classManServlet?action=select&currentPageNo=${totalPage}">末页</a>&nbsp;
			当前页面${currentPageNo }&nbsp;共${totalPage}页
	</c:when>
		<c:otherwise>
			<h2>暂无数据</h2>
		</c:otherwise>
	</c:choose>
	<br>
	<form
		action="${pageContext.request.contextPath}/Admin/servlet/classManServlet?action=insert"
		method="post">
		<table border="1" class="bordered">
			<tr>
				<th>班级名称</th>
				<th>专业</th>
				<th>说明</th>
				<th colspan="2">操作</th>
			</tr>
			<tr>
				<td><input type="text" size=10 name=name /></td>
				<td><select name="pid">
						<c:forEach items="${profs}" var="member">
							<option value="${member.pid}">${member.pname}</option>
						</c:forEach>
				</select></td>
				<td><input type="text" size=10 name=cexp /></td>
				<td><input type="submit" value="添加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
