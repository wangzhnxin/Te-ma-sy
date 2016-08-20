<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
String autoname ="";
 String autoword = "";
//获取当前站点的所有Cookie
if(request.getCookies()!=null){
Cookie[] cookies = request.getCookies();
for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数
if ("autoname".equals(cookies[i].getName())) {
 autoname = cookies[i].getValue();
} else if ("autoword".equals(cookies[i].getName())) {
 autoword = cookies[i].getValue();
}
 System.out.println(autoname+","+autoword);
}
}
 %>
<html class="login-alone">
<head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="res/homepage/favicon.ico?v=3.9">
        <link href="res/ui/css/screen.css?v=3.9" media="screen, projection" rel="stylesheet" type="text/css">
        <link rel="stylesheet" type="text/css" href="res/ui/css/base.css?v=3.9">
        <link rel="stylesheet" type="text/css" href="res/passport/css/login.css?v=3.9">
<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script language="JavaScript"> 
 function check(){
  var username = document.f1.username.value;
  var password= document.f1.pwd.value;
   if(username == ""||password==""){
   alert("用户名或者密码不能为空！");
   }
   }
 function refreshCode()
 {
	 document.getElementById("checkImg").src="dna.images";
	 alert("验证码已刷新");
 }
 
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
		xhr.open("GET","${pageContext.request.contextPath}/servlet/ValidateNameServlet?table=admin&time="+new Date().getTime()+"&name="+name,true);
		// 4.发送
		xhr.send(null);
		
	}

 </script>
</head>

    <body>
        <div class="logina-logo" style="height: 55px">
            <a href="">
                
            </a>
        </div>
        <div class="logina-main main clearfix">
            <div class="tab-con">
                <form id="form-login" action="servlet/LoginServlet" method="post" name="f1">
                    <div id="login-error" class="error-tip"></div>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                            <tr>
                                <th>账户</th>
                                <td width="245">
                                    <input type="text" name="username"  value="<%=autoname%>" onblur="checkMajoyname()" id="majoyname"><span id="span1"></span> </td>
                                <td>
                                </td>
                            </tr>
                            <tr>
                                <th>密码</th>
                                <td width="245">
                                    <input id="password" type="password" name="pwd"  value="<%=autoword%>">
                                </td>
                                <td>
                                </td>
                            </tr>
                            <tr id="tr-vcode">
                                <th>验证码</th>
                                <td width="255">
                                    <div class="valid">
                                        <input type="text" name="code"><img src="dna.images" alt="" name="checkImg" id="checkImg" onclick="refreshCode();" />
                                    </div>
                                </td>
                                <td>
                                </td>
                            </tr>
                            <tr class="find">
                                <th></th>
                                <td>
                                                                    管理员<input type="radio" name="identity" value="admin" checked="checked"/> 
                教师<input type="radio"  name="identity" value="teacher" /> <label class="checkbox" for="chk11"><input style="height: auto;" id="chk11"  value="y" name="isLogin" type="checkbox">记住我</label>
                                </td>
                                <td>                
                                <div>
                                    </div></td>
                                <td></td>
                            </tr>
                            <tr>
                                <th></th>
                                <td width="245"><input class="confirm" value="登  录" type="submit" onclick="javascript:check()"></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                    <input name="refer" value="site/" type="hidden">
                </form>
            </div>
            <div class="reg">
  <p>教师管理系统<br></p>
            </div>
        </div>
        
        <div id="footer">
            <div class="copyright">Copyright © 2016 All Rights Reserved.  版权所有</div>
        </div>
        <script src="res/skin/js/lib/jquery-2.0.3.min.js?v=3.9"></script>
        <script src="res/skin/js/ui.common.js?v=3.9"></script>
        <script src="res/passport/js/login.js?v=3.9"></script>  
</body></html>