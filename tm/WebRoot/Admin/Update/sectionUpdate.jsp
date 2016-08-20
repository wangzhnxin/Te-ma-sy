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
<h3>${msg}</h3>
<br>
<h3>修改</h3>
<hr>
<br>
<form
	action="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?&action=update&id=${id}"
	method="post">
	<table border="1" class="bordered">
			<tr>
				<th>节次</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>操作</th>
			</tr>
			<tr>
				<td><select name="name"  onblur="checkMajoyname()" id="majoyname">
						<c:forEach begin="1" end="8" var="count">			
							<option <c:if test="${name==count}">selected="selected"</c:if> value="第${count}节">第${count}节</option>
						</c:forEach>
				</select><span id="span1"></span></td>
				<td><select name="startHour" >
						<c:forEach begin="8" end="21" var="count">
							<option  <c:if test="${startHour==count}">selected="selected"</c:if> value="${count}">${count}</option>
						</c:forEach>
				</select>时 <select name="startMinute">
						<c:forEach begin="0" end="60" var="count" step="10">
							<option <c:if test="${startMinute==count}">selected="selected"</c:if> value="${count}">${count}</option>
						</c:forEach>
				</select>分</td>
				<td><select name="endHour">
						<c:forEach begin="8" end="21" var="count">
							<option <c:if test="${endHour==count}">selected="selected"</c:if> value="${count}">${count}</option>
						</c:forEach>
				</select>时 <select name="endMinute">
						<c:forEach begin="0" end="60" var="count" step="10">
							<option <c:if test="${endMinute==count}">selected="selected"</c:if> value="${count}">${count}</option>
						</c:forEach>
				</select>分</td>
				<td><input type="submit" value="添加" /></td>
			</tr>
		</table>
</form>
<td><a
	href="${pageContext.request.contextPath}/Admin/servlet/sectionManServlet?action=select">返回</a></td>
</body>
</html>
