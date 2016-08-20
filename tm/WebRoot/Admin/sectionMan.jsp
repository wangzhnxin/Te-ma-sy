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
<script language="JavaScript"> 
function createXmlHttp(){
		   var xmlHttp;
		   try{ // Firefox, Opera 8.0+, Safari
		        xmlHttp=new XMLHttpRequest();
		    }
		    catch (e){
			   try{// Internet Explorer
			         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
			      }
			    catch (e){
			      try{
			         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
			      }
			      catch (e){}
			      }
		    }

			return xmlHttp;
		 }
	function checkMajoyname(){
		// 获得文件框值:
		var name=document.getElementById("majoyname").value;
		//创建异步交互对象
		var xhr=createXmlHttp();
		//设置监听
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					document.getElementById("span1").innerHTML = xhr.responseText;
					
				}
			}
		}
		// 3.打开连接
		xhr.open("GET","${pageContext.request.contextPath}/servlet/ValidateNameServlet?table=section&time="+new Date().getTime()+"&name="+name,true);
		// 4.发送
		xhr.send(null);
		
	}

 </script>

</head>
<body class="bodyborder">
	
	<h4>${msg}</h4>
	课程节次管理
	<br>
	<c:choose>
		<c:when test="${! empty currentPage}">
			<table border="1" class="bordered">
				<tr>
					<th>课程名</th>
					<th>开始时间</th>
					<th>结束时间</th>
				    <th colspan="2">操作</th>
				</tr>
				<!-- 迭代输出 -->
				<c:forEach items="${currentPage}" var="member">
					<tr>
						<td>${member.sname}</td>
						<td>${member. start}</td>
						<td>${member. end}</td>
						<td><a
							href="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?&action=delete&id=${member.sid }&currentPageNo=${currentPageNo}">删除</a></td>
						<td>
							<!-- 通过servlet跳转到修改页面 --> <a
							href="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?action=redirect&name=${member.sname}&currentPageNo=${currentPageNo}">编辑</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<!-- 翻页 -->

			<a
				href="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?action=select&currentPageNo=1">首页</a>&nbsp;
		<c:if test="${currentPageNo>1}">
				<a
					href="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?action=select&currentPageNo=${currentPageNo-1}">上一页</a>&nbsp;
		</c:if>
			<c:if test="${currentPageNo<totalPage}">
				<a
					href="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?action=select&currentPageNo=${currentPageNo+1}">下一页</a>&nbsp;
		</c:if>
			<a
				href="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?action=select&currentPageNo=${totalPage}">末页</a>&nbsp;
			当前页面${currentPageNo }&nbsp;&nbsp;共${totalPage}
	</c:when>
		<c:otherwise>
			<h2>暂无数据</h2>
		</c:otherwise>
	</c:choose>
	<br>
	<form
		action="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?action=insert"
		method="post">
		<table border="1" class="bordered">
			<tr>
				<th>节次</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th colspan="2">操作</th>
			</tr>
			<tr>
				<td><select name="name" onblur="checkMajoyname()" id="majoyname">
						<c:forEach begin="1" end="8" var="count">
							<option value="第${count}节">第${count}节</option>
						</c:forEach>
				</select><span id="span1"></span></td>
				<td><select name="startHour">
						<c:forEach begin="8" end="21" var="count">
							<option value="${count}">${count}</option>
						</c:forEach>
				</select>时 <select name="startMinute">
						<c:forEach begin="0" end="50" var="count" step="10">
							<option value="${count}">${count}</option>
						</c:forEach>
				</select>分</td>
				<td><select name="endHour">
						<c:forEach begin="8" end="21" var="count">
							<option value="${count}">${count}</option>
						</c:forEach>
				</select>时 <select name="endMinute">
						<c:forEach begin="0" end="50" var="count" step="10">
							<option value="${count}">${count}</option>
						</c:forEach>
				</select>分</td>
				<td><input type="submit" value="添加" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
