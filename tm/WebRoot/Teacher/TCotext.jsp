<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
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

<title>My JSP 'teacherContext.jsp' starting page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body class="bodyborder">
<br>
	<h3>${msg}</h3>
	<br>
	<c:choose>
		<c:when test="${! empty currentPage}">
			<table border="1" class="bordered">
				<tr>
					<th>时间</th>
					<th>标题</th>
					<th>上课说明</th>
					<th>课件</th>
					<th colspan="2">操作</th>
				</tr>
				<c:forEach items="${currentPage}" var="member">
					<tr>
						<td>${member.tcdate}</td>
						<td>${member.tctitle}</td>
						<td>${member.tcexp}</td>
						<td><a
							href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectCware&tcid=${member.tcid}">查看课件</a></td>
						
							<td>
							<!-- 教学内容删除 -->
							<c:if test="${sessionScope.teacher.tid eq sessionScope.tid}"><a
								href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=delTCotext&tcid=${member.tcid}&currentPageNo=${currentPageNo}&arid=${arid}">删除</a></c:if></td>
					</tr>
				</c:forEach>
			</table>
			<!-- 翻页 -->
			<a
				href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectTCotext&currentPageNo=1&arid=${arid}">首页</a>&nbsp;
	<c:if test="${currentPageNo>1}">
				<a
					href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectTCotext&currentPageNo=${currentPageNo-1}&arid=${arid}">上一页</a>&nbsp;
		</c:if>
			<c:if test="${currentPageNo<totalPage}">
				<a
					href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectTCotext&currentPageNo=${currentPageNo+1}&arid=${arid}">下一页</a>&nbsp;
		</c:if>
			<a
				href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectTCotext&currentPageNo=${totalPage}&arid=${arid}">末页</a>&nbsp;
	当前页面${currentPageNo }&nbsp;&nbsp;共${totalPage}	
		</c:when>
		<c:otherwise>	
   暂无教学内容。
   </c:otherwise>
	</c:choose><!-- 添加教学内容 -->
	<c:if test="${sessionScope.teacher.tid==sessionScope.tid}">
		<br>
		<form
			action="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=addTCotext&arid=${arid}"
			method="post">
			<table border="1" class="bordered">
				<tr>
					<th>时间</th>
					<th>名称</th>
					<th>上课内容</th>
				</tr>
				<tr>
					<%
						Date currentTime = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
					%>
					<td><input type="date" name="tcdate" /></td>
					<td><input type="text" size="5" name="tctitle"  /></td>
					<td><input type="text" size="5" name="tcexp" /></td>
				</tr>
				<tr>
					<td><input type="submit" value="添加" /></td>
				</tr>
			</table>
		</form>
	</c:if>
</body>
</html>
