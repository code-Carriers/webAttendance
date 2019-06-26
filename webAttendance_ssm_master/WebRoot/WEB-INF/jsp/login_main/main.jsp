<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<c:set value="${pageContext.request.contextPath}" scope="page" var="ctx"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="0">
<title>主页</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace-skins.min.css" />
<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />
<script src="${ctx}/js/ace-extra.min.js"></script>
<script type="text/javascript">
window.onload=function(){
//定时器每秒调用一次fnDate()
setInterval(function(){
fnDate();
},1000);
}
 
//js 获取当前时间
function fnDate(){
var oDiv=document.getElementById("time");
var date=new Date();
var year=date.getFullYear();//当前年份
var month=date.getMonth();//当前月份
var data=date.getDate();//天
var hours=date.getHours();//小时
var minute=date.getMinutes();//分
var second=date.getSeconds();//秒
var time=year+"-"+fnW((month+1))+"-"+fnW(data)+" "+fnW(hours)+":"+fnW(minute)+":"+fnW(second);
oDiv.innerHTML=time;
}
//补位 当某个字段不是两位数时补0
function fnW(str){
var num;
str>10?num=str:num="0"+str;
return num;
} 
</script>
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<span class="navbar-brand"> <small> <img
						src="${ctx}/images/1.jpg"
						<%-- <img src="${ctx}/images/1.jpg" class="msg-photo" alt="Alex's Avatar" /> --%>
						style="height: 30px; display: inline;">&nbsp;
						<i class="icon-leaf"></i> CMHIT考勤管理系统
				</small> <small style="text-align: center; font-size: 14px;">当前时间 </small><small
					id="time" style="font-size: 14px;"> </small>
				</span>
				<!-- /.brand -->
			</div>
			<!-- /.navbar-header -->

			<%-- <c:when test="${empty sessionScope.user }">
                              <span>该用户不存在</span>
                           </c:when> --%>
			<%--  <% if (Session["username"].tostring == null)%> --%>
			<%
				String username = (String) request.getParameter("username");
			%>
			<c:if test="#username == null">
				<script>   if(confirm("请先登录！"))   
                	  window.location.href='<%=request.getContextPath()%>
					/login.jsp';
				</script>
			</c:if>
			<div align="center"></div>



			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<!-- <li class="grey">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-tasks"></i>
								<span class="badge badge-grey">4</span>
							</a>  -->

					<!-- <ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close"> -->
					<!-- <li class="dropdown-header">
									<i class="icon-ok"></i>
									4 Tasks to complete
								</li>

								<li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Software Update</span>
											<span class="pull-right">65%</span>
										</div>

										<div class="progress progress-mini ">
											<div style="width:65%" class="progress-bar "></div>
										</div>
									</a>
								</li> -->

					<!-- <li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Hardware Upgrade</span>
											<span class="pull-right">35%</span>
										</div>

										<div class="progress progress-mini ">
											<div style="width:35%" class="progress-bar progress-bar-danger"></div>
										</div>
									</a>
								</li> -->

					<!-- <li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Unit Testing</span>
											<span class="pull-right">15%</span>
										</div>

										<div class="progress progress-mini ">
											<div style="width:15%" class="progress-bar progress-bar-warning"></div>
										</div>
									</a>
								</li> -->

					<!-- <li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">Bug Fixes</span>
											<span class="pull-right">90%</span>
										</div>

										<div class="progress progress-mini progress-striped">
											<div style="width:90%" class="progress-bar progress-bar-success"></div>
										</div>
									</a>
								</li> -->
					<!-- 
								<li>
									<a href="#">
										See tasks with details
										<i class="icon-arrow-right"></i>
									</a>
								</li> -->
					<!-- </ul> -->
					<!-- </li> -->
					<!-- <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-bell-alt icon-animated-bell"></i>
								<span class="badge badge-important">8</span>
							</a> -->
					<!-- <ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close"> -->
					<!-- <li class="dropdown-header">
									<i class="icon-warning-sign"></i>
									8 Notifications
								</li> -->
					<!-- <li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
												New Comments
											</span>
											<span class="pull-right badge badge-info">+12</span>
										</div>
									</a>
								</li> -->
					<!-- <li>
									<a href="#">
										<i class="btn btn-xs btn-primary icon-user"></i>
										Bob just signed up as an editor ...
									</a>
								</li>
 -->
					<!-- <li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
												New Orders
											</span>
											<span class="pull-right badge badge-success">+8</span>
										</div>
									</a>
								</li>
 -->
					<!-- <li>
									<a href="#">
										<div class="clearfix">
											<span class="pull-left">
												<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
												Followers
											</span>
											<span class="pull-right badge badge-info">+11</span>
										</div>
									</a>
								</li> -->
					<!-- <li>
									<a href="#">
										See all notifications
										<i class="icon-arrow-right"></i>
									</a>
								</li> -->
					<!-- </ul> -->


					<!-- <a data-toggle="dropdown" class="dropdown-toggle" href="#">
								<i class="icon-envelope icon-animated-vertical"></i>
								<span class="badge badge-success">5</span>
							</a> -->
					<!-- <ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close"> -->
					<!-- <li class="dropdown-header">
									<i class="icon-envelope-alt"></i>
									5 Messages
								</li>
 -->
					<%-- <li>
									<a href="#">
										<img src="${ctx}/images/avatar.png" class="msg-photo" alt="Alex's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Alex:</span>
												Ciao sociis natoque penatibus et auctor ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>a moment ago</span>
											</span>
										</span>
									</a>
								</li> --%>
					<%-- 
								<li>
									<a href="#">
										<img src="${ctx}/images/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Susan:</span>
												Vestibulum id ligula porta felis euismod ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>20 minutes ago</span>
											</span>
										</span>
									</a>
								</li> --%>
					<%-- <li>
									<a href="#">
										<img src="${ctx}/images/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
										<span class="msg-body">
											<span class="msg-title">
												<span class="blue">Bob:</span>
												Nullam quis risus eget urna mollis ornare ...
											</span>

											<span class="msg-time">
												<i class="icon-time"></i>
												<span>3:15 pm</span>
											</span>
										</span>
									</a>	
								</li> --%>
					<!-- 
								<li>
									<a href="inbox.html">
										See all messages
										<i class="icon-arrow-right"></i>
									</a>
								</li> -->
					<!-- </ul> -->
					<!-- class="dropdown-toggle" -->
					<li class="light-blue"><a data-toggle="dropdown" href="#">
							<%-- <img class="nav-user-photo" src="${ctx}/images/timg.jpg" alt="Jason's Photo" /> --%>
							<span>欢迎,</span> <!-- class="user-info" --> <span>
								${user.staffname} </span> <i class="icon-caret-down"></i>
					</a>
						<ul
							class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<li>
									<a href="./information/updatePass.jsp">
										<i class="icon-cog"></i>
										设 置密码
									</a>
								</li>

								<li>
									<a href="./information/information.jsp">
										<i class="icon-user"></i>
										用户信息
									</a>
								</li>
							<li class="divider"></li>
							<li><a href="${ctx}/menuController/exit.do"> <i
									class="icon-off" onclick="mysession()"></i> 退出登录
							</a> <!-- <li id="exit" 
							        onclick="activechange(document.getElementById('myattendance'),document.getElementById('exit'),'menuController/exit')">
									<a>
										<i class="icon-double-angle-right"></i>
										我要请假、加班、出差
									</a>
								</li> --></li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

 <script>
function mysession(){
/* response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();

Cookie killMyCookie = new Cookie("mycookie",null);  
killMyCookie.setMaxAge(0);  
killMyCookie.setPath("/");  
response.addCookie(killMyCookie);

session.invalidate();     */
}
</script> 

	<div class="main-container" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.check('main-container', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="main-container-inner">
			<a class="menu-toggler" id="menu-toggler" href="#"> 菜单导航 </a>
			<div class="sidebar" id="sidebar">
				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'fixed')
					} catch (e) {
					}
				</script>

				<div class="sidebar-shortcuts" id="sidebar-shortcuts">
					<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
						<span class="btn btn-success"></span> <span class="btn btn-info"></span>

						<span class="btn btn-warning"></span> <span class="btn btn-danger"></span>
					</div>
				</div>
				<!-- #sidebar-shortcuts -->

				<ul class="nav nav-list">
					<li id="controller" class="active"><a> <i
							class="icon-dashboard"></i> <span class="menu-text"> 功能导航
						</span>
					</a></li>
					<li id="myattendance"><a href="#" class="dropdown-toggle">
							<i class="icon-desktop"></i> <span class="menu-text"> 我的考勤
						</span> <b class="arrow icon-angle-down"></b>
					</a>

						<ul class="submenu">
							<li id="MyAttendanceRecord"
								onclick="activechange(document.getElementById('myattendance'),document.getElementById('MyAttendanceRecord'),'menuController/selectMyAttendance')">
								<a> <i class="icon-double-angle-right"></i> 我的考勤记录
							</a>
							</li>

							<li id="myclass"
								onclick="activechange(document.getElementById('myattendance'),document.getElementById('myclass'),'menuController/selectMyClass')">
								<a> <i class="icon-double-angle-right"></i> 我的班次安排
							</a>

							</li>
							<li id="myRetroactive"
								onclick="activechange(document.getElementById('myattendance'),document.getElementById('myRetroactive'),'menuController/selectMyRetroactive')">

								<a> <i class="icon-double-angle-right"></i> 我的补签申请
							</a>
							</li>
							<li id="myAskForLeave"
								onclick="activechange(document.getElementById('myattendance'),document.getElementById('myAskForLeave'),'menuController/selectMyAskForLeave')">
								<a> <i class="icon-double-angle-right"></i> 我要请假、加班、出差
							</a>
							</li>
							<li id="punchCard"
								onclick="activechange(document.getElementById('myattendance'),document.getElementById('punchCard'),'menuController/punchCard')">
								<a> <i class="icon-double-angle-right"></i> 考勤打卡
							</a>
							</li>
						</ul></li>
                      
						
					<li id="renshiguanli"><a href="#" class="dropdown-toggle">
							<i class="icon-list"></i> <span class="menu-text"> 人事管理 </span> <b
							class="arrow icon-angle-down"></b>
					</a>

						<ul class="submenu">
							<li id="setDepartment"
								onclick="activechange(document.getElementById('renshiguanli'),document.getElementById('setDepartment'),'setDeparment/main')">
								<a> <i class="icon-double-angle-right"></i> 机构、职务设置
							</a>
							</li>
							<li id="renyuanluru"
								onclick="activechange(document.getElementById('renshiguanli'),document.getElementById('renyuanluru'),'menuController/selectStaff')">
								<a> <i class="icon-double-angle-right"></i> 人员录入
							</a>
							</li>
						</ul></li>
 
					<li id="paibanguangli"><a href="#" class="dropdown-toggle">
							<i class="icon-edit"></i> <span class="menu-text"> 排班管理 </span> <b
							class="arrow icon-angle-down"></b>
					</a>

						<ul class="submenu">
							<li id="renyuanpaiban"
								onclick="activechange(document.getElementById('paibanguangli'),document.getElementById('renyuanpaiban'),'menuController/redirectScheduling')">
								<a> <i class="icon-double-angle-right"></i> 人员排班
							</a>
							</li>

							<li id="bancishezhi"
								onclick="activechange(document.getElementById('paibanguangli'),document.getElementById('bancishezhi'),'menuController/selectClasses')">
								<a> <i class="icon-double-angle-right"></i> 班次设置
							</a>
							</li>
							<!-- <li id="gudinglunbanshizhe"
                          onclick="activechange(document.getElementById('paibanguangli'),document.getElementById('gudinglunbanshizhe'),'SetShift/selectShift')">

									<a >
										<i class="icon-double-angle-right"></i>
										固定轮班设置
									</a>
								</li> -->
						</ul></li>


					<li id="kaoqinguangli"><a href="#" class="dropdown-toggle">
							<i class="icon-tag"></i> <span class="menu-text"> 考勤管理 </span> <b
							class="arrow icon-angle-down"></b>
					</a>

						<ul class="submenu">
							<li id="kaoqinzhidushezhi"
								onclick="activechange(document.getElementById('kaoqinguangli'),document.getElementById('kaoqinzhidushezhi'),'menuController/selectSystem')">
								<a> <i class="icon-double-angle-right"></i> 考勤制度设置
							</a>
							</li>

							<li id="kaoqinleibieshezhi"
								onclick="activechange(document.getElementById('kaoqinguangli'),document.getElementById('kaoqinleibieshezhi'),'menuController/selectByAttendanceType')">
								<a> <i class="icon-double-angle-right"></i> 考勤类别设置
							</a>
							</li>
						</ul></li>
						 
					<li id="kaoqinchuli"><a href="#" class="dropdown-toggle">
							<i class="icon-file-alt"></i> <span class="menu-text">
								考勤处理 </span> <b class="arrow icon-angle-down"></b>
					</a>

						<ul class="submenu">
							<li id="qingjiajiabanchuli"
								onclick="activechange(document.getElementById('kaoqinchuli'),document.getElementById('qingjiajiabanchuli'),'menuController/selectAllAskForLeave')">
								<a> <i class="icon-double-angle-right"></i> 请假、加班申请处理
							</a>
							</li>

							<li id="buqianyichang"
								onclick="activechange(document.getElementById('kaoqinchuli'),document.getElementById('buqianyichang'),'menuController/selectDealwihthRetroative')">
								<a> <i class="icon-double-angle-right"></i> 补签、异常信息处理
							</a>
							</li>
						</ul></li>
						
					<li id="kaoqingtongji"><a href="#" class="dropdown-toggle">
							<i class="icon-file-alt"></i> <span class="menu-text">
								考勤统计 </span> <b class="arrow icon-angle-down"></b></a>
						<ul class="submenu">
							 <li id="bumentongji"
								onclick="activechange(document.getElementById('kaoqingtongji'),document.getElementById('bumentongji'),'menuController/gerenattendanceStatistics')">
								<a> <i class="icon-double-angle-right"></i> 个人考勤统计
							</a>
							</li> 
							<li 
								 id="bumentongji" onclick="activechange(document.getElementById('kaoqingtongji'),
								 document.getElementById('bumentongji'),
								 'menuController/bumenattendanceStatistics')">
									<a >
										<i class="icon-double-angle-right"></i>
										部门考勤统计
									</a>
								</li>
							<li 
								 id="bumentongji" onclick="activechange(document.getElementById('kaoqingtongji'),
								 document.getElementById('bumentongji'),
								 'menuController/gongsiattendanceStatistics')">
									<a ><i class="icon-double-angle-right"></i>
										公司考勤统计
									</a>
								</li>
							<%-- <!-- 判断是否部门经理，可以查看部门考勤记录 -->
							 <c:if test="${user.remark =='1'}"> 
								<li 
								 id="bumentongji" onclick="activechange(document.getElementById('kaoqingtongji'),
								 document.getElementById('bumentongji'),
								 'menuController/bumenattendanceStatistics')">
									<a >
										<i class="icon-double-angle-right"></i>
										部门考勤统计
									</a>
								</li>
							 </c:if> 
							<!-- 判断是否HR，可以查看公司考勤记录 -->
							 <c:if test="${user.remark =='0'}"> 
								<li 
								 id="bumentongji" onclick="activechange(document.getElementById('kaoqingtongji'),
								 document.getElementById('bumentongji'),
								 'menuController/gongsiattendanceStatistics')">
									<a ><i class="icon-double-angle-right"></i>
										公司考勤统计
									</a>
								</li> </c:if> --%>
							</ul>
							</li>
				<!-- /.nav-list -->
				<div class="sidebar-collapse" id="sidebar-collapse">
					<i class="icon-double-angle-left"
						data-icon1="icon-double-angle-left"
						data-icon2="icon-double-angle-right"></i>
				</div>

				<script type="text/javascript">
					try {
						ace.settings.check('sidebar', 'collapsed')
					} catch (e) {
					}
				</script>
			</div>
			<h1 style="text-align: center; font-color: #B9D3EE;">欢迎使用CMHIT考勤管理系统</h1>
			<!--主区显示 -->
			<div id="mainContainer" class="main-content"></div>

			<script src="${ctx}/js/jquery.min.js"></script>
			<script type="text/javascript">
				window.jQuery
						|| document
								.write("<script src='${ctx}/js/jquery-2.0.3.min.js'>"
										+ "<"+"/script>");
			</script>
			<script type="text/javascript">
				if ("ontouchend" in document)
					document
							.write("<script src='${ctx}/js/jquery.mobile.custom.min.js'>"
									+ "<"+"/script>");
			</script>
			<script src="${ctx}/js/bootstrap.min.js"></script>
			<script src="${ctx}/js/typeahead-bs2.min.js"></script>
			<script src="${ctx}/js/ace-elements.min.js"></script>
			<script src="${ctx}/js/ace.min.js"></script>
			<script src="${ctx}/js/main.js"></script>
			<script type="text/javascript">
				function activechange(curfather, currentElement, gotopath) {
					$(".active").removeClass("active");
					curfather.setAttribute('class', 'active open');
					currentElement.setAttribute('class', 'active');
					$.ajax({
						type : 'post',
						url : '${ctx}/' + gotopath + '.do',
						success : function(html) {
							$('#mainContainer').empty();
							$('#mainContainer').append(html);
						},
					});

				}
				function removeActive() {

				}
			</script>

			<!--
			<h5 style="text-align: center;">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				招商局国际信息技术有限公司 版权所有 Coypright ©2011-2015 China Merchants
				Holdings(International) Information Technology Company Limited
				版本：V1.0.6.140107.1 <a id="A2" href="#"
					target="_top" class="a1" title="系统介绍">系统简介</a> <a id="A1"
					href="mailto:dliu@cmhit.com?subject=考勤管理系统Bug申报&body=考勤系统管理员：您好！&nbsp;我好像发现考勤系统的一个bug，问题描述如下：&nbsp;图片为：&nbsp;"
					target="_top" class="a1" title="Bug申报">Bug申报</a>
			</h5> -->
</body>
</html>

