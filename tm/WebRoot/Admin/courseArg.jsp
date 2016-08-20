<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

<title>My JSP 'courseArg.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/table.css" type="text/css" />
</head>

<body class="bodyborder">
	<h3>${msg}</h3>
	课程安排
	<br>
	<form
		action="${pageContext.request.contextPath}/Admin/servlet/courseArgServlet?action=select"
		method="post">
		<select name="scid">
			<c:forEach items="${classes}" var="member">
				<option value="${member.cid}">
					${member.pname}&nbsp;${member.cname }</option>
			</c:forEach>
		</select> <select name="sweek">
			<option value="星期一"
				<c:if test="${sweek==星期一}"> selected="selected"</c:if>>星期一</option>
			<option value="星期二"
				<c:if test="${sweek==星期二}"> selected="selected"</c:if>>星期二</option>
			<option value="星期三"
				<c:if test="${sweek==星期三}"> selected="selected"</c:if>>星期三</option>
			<option value="星期四"
				<c:if test="${sweek==星期四}"> selected="selected"</c:if>>星期四</option>
			<option value="星期五"
				<c:if test="${sweek==星期五}"> selected="selected"</c:if>>星期五</option>
			<input type="submit" value="查询" />
	</form>

	<br>
	<c:choose>
		<c:when test="${flag}">
			<table border="1" class="bordered">
				<tr>

					<!-- <th>id</th> -->
					<th>学年</th>
					<th>节次</th>
					<th>星期</th>
					<th>班级</th>
					<th>课程</th>
					<th>教师</th>
					<th>说明</th>
					<th colspan="2">操作</th>
				</tr>
				<!-- 迭代输出 -->
				<c:forEach items="${AList}" var="member">
					<tr>
						<%-- <td>${member.arid}</td> --%>
						<td><c:forEach items="${acayears}" var="acayear">
								<c:if test="${acayear.acaid==member.acaid}">
						${acayear.acaname}
						</c:if>
							</c:forEach></td>
						<td><c:forEach items="${sections}" var="section">
								<c:if test="${section.sid==member.sid}">
						${section.sname}
						</c:if>
							</c:forEach></td>
						<td><fmt:formatDate value="${member. week}" pattern="EEEE"/> </td>
						<td><c:forEach items="${classes}" var="cla">
								<c:if test="${cla.cid==member.cid}">
						${cla.pname} ${cla.cname}
						</c:if>
							</c:forEach></td>
						<td><c:forEach items="${courses}" var="course">
								<c:if test="${course.coid==member.coid}">
						${course.coname}
						</c:if>
							</c:forEach></td>
						<td><c:forEach items="${teachers}" var="teacher">
								<c:if test="${teacher.tid==member.tid}">
						${teacher.tname}
						</c:if>
							</c:forEach></td>
						<td>${member.aexp}</td>
						<td><a
							href="${pageContext.request.contextPath}/Admin/servlet/courseArgServlet?action=delete&id=${member.arid }&sweek=${sweek}&scid=${scid}">删除</a></td>
						<%-- 	<td>
							<!-- 通过servlet跳转到修改页面 --> <a
							href="${pageContext.request.contextPath}/Admin/servlet/courseArgServletaction=redirect&id=${member.arid}">编辑</a>
						</td> --%>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
			<h2>暂无数据</h2>
		</c:otherwise>
	</c:choose>
	<br>
	<form
		action="${pageContext.request.contextPath}/Admin/servlet/courseArgServlet?action=insert&sweek=${sweek}&scid=${scid}"
		method="post">
		<table border="1" class="bordered">
			<tr>
				<th>学年</th>
				<th>节次</th>
				<th>星期</th>
				<th>班级</th>
				<th>课程</th>
				<th>教师</th>
				<th>说明</th>
				<th colspan="2">操作</th>
			</tr>
			<tr>

				<td><select name="acaid">
						<c:forEach items="${acayears}" var="member">
							<option value="${member.acaid}">${member.acaname}</option>
						</c:forEach>
				</select></td>

				<td><select name="sid">
						<c:forEach items="${sections}" var="member">
							<option value="${member.sid}">${member.sname}</option>
						</c:forEach>
				</select></td>

				<td><select name="week">
						<option value="星期一">星期一</option>
						<option value="星期二">星期二</option>
						<option value="星期三">星期三</option>
						<option value="星期四">星期四</option>
						<option value="星期五">星期五</option>
				</select></td>

				<td><select name="cidAndPname">
						<c:forEach items="${classes}" var="member">
							<option value="${member.cid},${member.pname}">${member.pname}
								${member.cname}</option>
						</c:forEach>
				</select></td>

				<td><select name="coid">
						<c:forEach items="${courses}" var="member">
							<option value="${member.coid}">${member.coname}</option>
						</c:forEach>
				</select></td>

				<td><select name="tid">
						<c:forEach items="${teachers}" var="member">
							<option value="${member.tid}">${member.tname}</option>
						</c:forEach>
				</select></td>
				<td><input type="text" size="10" name="aexp"></td>
				<td><input type="submit" value="添加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
