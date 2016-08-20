<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'selectOthers.jsp' starting page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/table.css" type="text/css" />

</head>

<body class="bodyborder">
<br>
		<h3>其他教师课程安排</h3>
	<form
		action="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectOthers"
		method="post">
		<select name="acaid">
			<c:forEach items="${acayears}" var="member">
				<option value="${member.acaid}">${member.acaname}</option>
			</c:forEach>
		</select>学年 <input type="text" size="4" placeholder="教师姓名" name="tname" /> <input
			type="submit" value="查询" />
	</form>
	<c:choose>
		<c:when test="${! empty currentPage}">
			<table border="1" class="bordered">
				<tr>
					<!-- 	<th>id</th> -->
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
				<c:forEach items="${currentPage}" var="member">
					<tr>
						<%-- 	<td>${member.arid}</td> --%>
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
					<td><fmt:formatDate value="${member.week}" pattern="EEEE"/></td>
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
							href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectTCotext&arid=${member.arid}&tid=${member.tid}">教学内容</a></td>
						<%-- 	<td>
							<!-- 通过servlet跳转到修改页面 --> <a
							href="${pageContext.request.contextPath}/Teacher/servlet/courseArgServletaction=redirect&id=${member.arid}">编辑</a>
						</td> --%>
					</tr>
				</c:forEach>
			</table>
			<a
				href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectOthers&currentPageNo=1&tname=${tname}&acaid=${acaid}">首页</a>&nbsp;
	<c:if test="${currentPageNo>1}">
				<a
					href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectOthers&currentPageNo=${currentPageNo-1}&tname=${tname}&acaid=${acaid}">上一页</a>&nbsp;
		</c:if>
			<c:if test="${currentPageNo<totalPage}">
				<a
					href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectOthers&currentPageNo=${currentPageNo+1}&tname=${tname}&acaid=${acaid}">下一页</a>&nbsp;
		</c:if>
			<a
				href="${pageContext.request.contextPath}/Teacher/servlet/teacherServlet?action=selectOthers&currentPageNo=${totalPage}&tname=${tname}&acaid=${acaid}">末页</a>&nbsp;
	当前页面${currentPageNo }&nbsp;&nbsp;共${totalPage}	
	</c:when>
		<c:otherwise>
			<h2>暂无数据</h2>
		</c:otherwise>
	</c:choose>
</body>
</html>
