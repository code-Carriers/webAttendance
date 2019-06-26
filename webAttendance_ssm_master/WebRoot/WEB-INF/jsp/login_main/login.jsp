<%@ page language="java" import="java.util.*,java.net.URLDecoder" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" scope="page" var="ctx"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<title>考勤管理系统</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/style1.css" />
	<link rel="stylesheet" type="text/css" href="${ctx}/css/body.css"/> 
  <%--   <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css" type="text/css"></link>
    <link rel="stylesheet" href="${ctx}/css/loginStyle.css" type="text/css"></link> --%>
    <script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
    <script type="text/javascript"></script> 
  </head>
  <body>
  
    <% 
      String username = "";  String password = "";   //获取当前站点的所有Cookie   
      Cookie[] cookies = request.getCookies();  
      for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据    
         if ("username".equals(cookies[i].getName())) {       
             username = URLDecoder.decode(cookies[i].getValue(), "UTF-8"); 
         } else if ("password".equals(cookies[i].getName())) {      
           password = URLDecoder.decode(cookies[i].getValue(), "UTF-8");    
         }   
     } 
	%> 
   <%-- <form class="form-horizontal" action="${ctx}/menuController/login.do" method="post">
 	  <div class="login">
		 <div class="login-main">
		 		<div class="login-top">
		 			<img class="img" src="images/top.png" alt=""/>
		 			<h1>WEB考勤管理系统</h1>
		 			<input type="text" id="username" name="username" placeholder="用户名" onkeyup="$('#tishi')[0].innerHTML=''"  required="" value="${ctq}">
		 			<input type="password" name="password"  placeholder="密码" onkeyup="$('#tishi')[0].innerHTML=''"  required="">
		 			<div class="login-bottom">
		 			<div id="tishi" style="color: red;">*${tishi}</div>
		 			  <div class="login-check">
			 			<label class="checkbox"><input type="checkbox" name="checkbox"  checked="checked" /><i> </i> 记 住 我</label>
			 		  </div>
			 			<div class="clear"> </div>
		 			</div>
		 			<input type="submit" value="登录" />
		 		</div>
		 	</div>
  		</div>
	</form> --%>
	
	<div class="container">
	<section id="content">
		<form action="${ctx}/menuController/login.do" method="post">
			<h1>考勤管理系统</h1>
			<div>
				<input type="text" placeholder="用户名" required="" id="username" name="username" value="<%=username%>" />
			</div>
			<div>
				<input type="password" placeholder="密码" required="" id="password" name="password" value="<%=password%>" />
			</div>
			<div id="tishi" style="color: red;">${tishi}</div>
			 <div class="">
				<span class="help-block u-errormessage" id="js-server-helpinfo">&nbsp;</span>			</div> 
			<div>
				<!-- <input type="submit" value="Log in" /> -->
				<input type="submit" value="登录" class="btn btn-primary" id="js-btn-login"/>
               <label> <input name="remPwd" type="checkbox" value="checked" checked="checked" />记住密码</label>
               <label> <input name="" type="checkbox" value="" />自动登录</label>
			</div>
      </form>
      <!-- form --> 

	</section><!-- content -->
</div>
<br><br><br><br>
	
  </body>
</html>
