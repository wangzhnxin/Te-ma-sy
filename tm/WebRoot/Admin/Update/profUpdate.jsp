<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
		xhr.open("GET","${pageContext.request.contextPath}/servlet/ValidateNameServlet?table=prof&time="+new Date().getTime()+"&name="+name,true);
		// 4.发送
		xhr.send(null);
		
	}

 </script>
</head>
<body class="bodyborder">


修改
<h3>${msg}</h3>
 <form action="${pageContext.request.contextPath}/Admin/servlet/profManServlet?&action=update&id=${OneData.pid}" method="post">
 <table border="1" class="bordered">
	<tr>
			<th>专业名</th>
			<th>说明</th>
			<th>操作</th>
		</tr>
		<tr>
			<td><input type="text" size=5 value="${OneData.pname}" name="name" onblur="checkMajoyname()" id="majoyname"/><span id="span1"></span></td>
			<td><input type="text" size=10 value="${OneData.pexp}" name="pexp" /></td>
			<td><input type="submit" value="确认" /></td>
		</tr>
	</table>
 </form>
 <td><a href="${pageContext.request.contextPath}/Admin/servlet/profManServlet?action=select">返回</a></td>
</body>
</html>

