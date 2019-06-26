<%@ page language="java" import="java.util.*,java.net.URLDecoder"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" scope="page" var="ctx"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>考勤管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="${ctx}/css/bootstrap.min.css"
	type="text/css"></link>
<link rel="stylesheet" href="${ctx}/css/loginStyle.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript"></script>
<body>
</head>
<body style="padding-top: 140px;">

	<form class="form-horizontal" action="${ctx}/menuController/login.do"
		method="post">
		<div class="login">
			<div class="login-main">
				<%-- <div class="m1">
           		<img src="${ctx}/images/1.jpg" style="width:30px;height:30px;"/>&nbsp;&nbsp;<span class="mm">CMHIT</span>
           	   </div> --%>

				<!-- <h4>人脸识别考勤管理系统</h4> -->
				<div class="login-top"
					style="padding-top: 0px; padding-bottom: 0px;">
					<img src="${ctx}/images/12.jpg"
						style="width: 800px; height: 130px;" />
					<!-- <img class="img" src="images/03.jpg" alt=""/><Br> -->
					<!-- <input type="radio" name="identify" value="admin" />管理员&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		 			<input type="radio" name="identify" value="admin" />员工-->
					<br> <br> <br>
					<%
						String username = "";
						String password = "";
						//获取当前站点的所有Cookie
						Cookie[] cookies = request.getCookies();
						if(cookies!=null){
						for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
							if ("username".equals(cookies[i].getName())) {
								username = cookies[i].getValue();
							} else if ("password".equals(cookies[i].getName())) {
								password = cookies[i].getValue();
							}
						}}
					%>
					<input type="text" id="username" name="username" placeholder="用户名"
						onkeyup="$('#tishi')[0].innerHTML=''" required=""
						value="<%=username%>"> <input type="password"
						name="password" placeholder="密码"
						onkeyup="$('#tishi')[0].innerHTML=''" required=""
						value="<%=password%>">
					<div class="login-bottom">
						<div id="tishi" style="color: red; width: 130px;">${tishi}</div>
						<div class="login-check" style="text-align: center;">
							<label class="checkbox"> <input name="remPwd"
								type="checkbox" value="checked" checked="checked" />记住密码
							</label>
							<!-- <i> </i></label>记住密码 -->
						</div>
						<div class="clear"></div>
					</div>
					<input type="submit" value="登录" />
				</div>
			</div>
		</div>
	</form>
</body>
</html>
