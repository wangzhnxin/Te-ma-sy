<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

<title>My JSP 'teacherCware.jsp' starting page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body class="bodyborder">
	<c:choose>
		<c:when test="${not empty currentPage}">
			<table border="1" class="bordered">
				<tr>
					<th>课件名</th>
					<th>下载链接</th>
					<th colspan="2">操作</th>
				</tr>
				<c:forEach items="${currentPage}" var="member">
					<tr>
						<td>${member.cwname}</td>
						<td><a
							href="${pageContext.request.contextPath}${member.addr}">下载</a></td>

						<td>
					<!-- 	课件删除 -->
						<c:if test="${sessionScope.teacher.tid==sessionScope.tid}">
								<a
									href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=delCware&cwid=${member.cwid}&currentPageNo=${currentPageNo}&addr=${member.addr}&tcid=${tcid}">删除</a>
							</c:if></td>

					</tr>
				</c:forEach>
			</table>
			<!-- 翻页 -->
			<a
				href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectCware&tcid=${tcid}&currentPageNo=1">首页</a>&nbsp;
	<c:if test="${currentPageNo>1}">
				<a
					href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectCware&tcid=${tcid}&currentPageNo=${currentPageNo-1}">上一页</a>&nbsp;
		</c:if>
			<c:if test="${currentPageNo<totalPage}">
				<a
					href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectCware&tcid=${tcid}&currentPageNo=${currentPageNo+1}">下一页</a>&nbsp;
		</c:if>
			<a
				href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectCware&tcid=${tcid}&currentPageNo=${totalPage}">末页</a>&nbsp;
	当前页面${currentPageNo }&nbsp;&nbsp;共${totalPage}	
		</c:when>
		<c:otherwise>
  暂无课件。
  </c:otherwise>
	</c:choose>
	<c:if test="${sessionScope.teacher.tid==sessionScope.tid}">
		<h3>${msg}</h3>
		<br>
	上传课件
	<br>
		<form
			action="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=uploadFile&tcid=${tcid}"
			method="post" enctype="multipart/form-data">
			<input type="file" name="file" /> <input type="submit" value="上传" />
		</form>
	</c:if>

</body>
</html>
