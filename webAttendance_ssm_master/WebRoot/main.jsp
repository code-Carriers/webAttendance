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
<title>招商国际信息考勤管理平台</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/css/font-awesome.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace-rtl.min.css" />
<link rel="stylesheet" href="${ctx}/css/ace-skins.min.css" />
<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />
<script src="${ctx}/js/ace-extra.min.js"></script>
<script type="text/javascript">
	window.onload = function() {
		//定时器每秒调用一次fnDate()
		setInterval(function() {
			fnDate();
		}, 1000);
	}
    
	var mydate=new Date();
	 var myddy=mydate.getDay();//获取存储当前日期
	 var weekday=["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
	 /* document.write("今天是：" + weekday[myddy]); */
	
	//js 获取当前时间
	function fnDate() {
		var oDiv = document.getElementById("time");
		var date = new Date();
		var year = date.getFullYear();//当前年份
		var month = date.getMonth();//当前月份
		var data = date.getDate();//天
		var hours = date.getHours();//小时
		var minute = date.getMinutes();//分
		var second = date.getSeconds();//秒
		var time = year + "-" + fnW((month + 1)) + "-" + fnW(data) + " "
				+ fnW(hours) + ":" + fnW(minute) + ":" + fnW(second);
		oDiv.innerHTML = (time + "  " +weekday[myddy]);
	}
	//补位 当某个字段不是两位数时补0
	function fnW(str) {
		var num;
		str > 9 ? num = str : num = "0" + str;
		return num;
	}
</script>
</head>

<body>
	<div class="navbar navbar-default" id="navbar">
		<script type="text/javascript">
			try {
				ace.settings.check('navbar', 'fixed')
			} catch (e) {
			}
		</script>

		<div class="navbar-container" id="navbar-container">
			<div class="navbar-header pull-left">
				<span class="navbar-brand"> <small> <img
						src="${ctx}/images/1.jpg"
						<%-- <img src="${ctx}/images/1.jpg" class="msg-photo" alt="Alex's Avatar" /> --%>
						style="height: 30px; display: inline;">&nbsp;
						<!-- <i class="icon-leaf"></i> --> CMHIT考勤管理系统
				</small> <small style="text-align: center; font-size: 14px;">当前时间 </small><small
					id="time" style="font-size: 14px;"> </small>
				</span>
			</div>
		
			<div class="navbar-header pull-right" role="navigation">
				<ul class="nav ace-nav">
					<li class="light-blue"><a href="#" data-toggle="dropdown" class="dropdown-toggles">
							<span><img src="../pic/${user.headimage}" style="width:60px;height:50px;"></span>
						<%-- <img class="nav-user-photo" src="${ctx}/images/timg.jpg" alt="Jason's Photo" /> --%>
							<span>欢迎,</span> <span>${user.staffname} </span> <i
							class="icon-caret-down"></i>
					</a>
						<ul
							class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							
							<li onclick="user('menuController/updatePassJsp')">
									<a>
										<i class="icon-cog"></i>
										设 置密码
									</a>
								</li>

								<li onclick="user('menuController/information')">
									<a>
										<i class="icon-user"></i>
										用户信息
									</a>
								</li>
							<li class="divider"></li>
							<li><a href="${ctx}/menuController/exit.do"> <i
									class="icon-off" onclick="mysession()"></i> 退出登录
							</a> </li>
						</ul></li>
				</ul>
				<!-- /.ace-nav -->
			</div>
			<!-- /.navbar-header -->
		</div>
		<!-- /.container -->
	</div>

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
							<!-- <li id="punchCard"
								onclick="activechange(document.getElementById('myattendance'),document.getElementById('punchCard'),'menuController/punchCard')">
								<a> <i class="icon-double-angle-right"></i> 考勤打卡
							</a>
							</li> -->
							
						</ul>
					<!-- 判断是否有HR权限，如果是则有人事管理权限 -->
					
						<li id="renshiguanli"><a href="#" class="dropdown-toggle">
								<i class="icon-list"></i> <span class="menu-text"> 人事管理 </span>
								<b class="arrow icon-angle-down"></b>
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
								<i class="icon-edit"></i> <span class="menu-text"> 排班管理 </span>
								<b class="arrow icon-angle-down"></b>
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
									onclick="activechange(document.getElementById('kaoqinchuli'),
									document.getElementById('qingjiajiabanchuli'),'menuController/selectAllAskForLeave')">
									<a> <i class="icon-double-angle-right"></i> 请假、加班申请处理
								</a>
								</li>

								<li id="buqianyichang"
									onclick="activechange(document.getElementById('kaoqinchuli'),
									document.getElementById('buqianyichang'),'menuController/selectDealwihthRetroative')">
									<a> <i class="icon-double-angle-right"></i> 补签、异常信息处理
								</a>
								</li>
							</ul></li>
					

					<%-- <ul class="submenu">
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
						</ul></li>--%>
					<li id="kaoqingtongji"><a href="#" class="dropdown-toggle">
							<i class="icon-tag"></i> <span class="menu-text">
								考勤日志 </span> <b class="arrow icon-angle-down"></b>
					</a>
						<ul class="submenu">
						 	<li id="gerentongji"
								onclick="activechange(document.getElementById('kaoqingtongji'),
								document.getElementById('gerentongji'),'menuController/gerenattendanceStatistics')">
								<a> <i class="icon-double-angle-right"></i> 考勤记录
							</a>
							</li> 
							<!-- <li id="bumentongji"
									onclick="activechange(document.getElementById('kaoqingtongji'),
								 document.getElementById('bumentongji'),
								 'menuController/bumenattendanceStatistics')">
									<a> <i class="icon-double-angle-right"></i> 部门考勤统计
								</a>
							</li> -->
							<li id="gongsitongji"
									onclick="activechange(document.getElementById('kaoqingtongji'),
								 document.getElementById('gongsitongji'),
								 'menuController/gongsiattendanceStatistics')">
									<a><i class="icon-double-angle-right"></i> 考勤统计 </a>
								</li>
							
								
							
					</ul></li>
						
					<%-- 
						<li id="xiugaimima"><a href="#" class="dropdown-toggle">
								<i class="icon-file-alt"></i> <span class="menu-text">
									个人信息修改 </span> <b class="arrow icon-angle-down"></b>
						</a>
							<ul class="submenu">
							<li><a href="${ctx}/menuController/updatePassJsp.do">  个人密码修改
							</a> </li> 
							</ul>
							</li>
					 --%>
					 
					<li id="gerenxinxi">
						<a href="#" class="dropdown-toggle">
							<i class="icon-user"></i> <span class="menu-text"> 个人信息管理 </span> 
							<b class="arrow icon-angle-down"></b>
						</a>

						<ul class="submenu">
							<li id="xiugaimima"
								onclick="activechange(document.getElementById('gerenxinxi'),
								document.getElementById('xiugaimima'),
								'menuController/updatePassJsp')">
							    <a> <i class="icon-double-angle-right"></i> 个人密码修改</a>
							</li>
							<li id="xiugaixinxi"
								onclick="activechange(document.getElementById('gerenxinxi'),
								document.getElementById('xiugaixinxi'),
								'menuController/information')">
							    <a> <i class="icon-double-angle-right"></i> 个人信息</a>
							</li>
								
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
			<%
			String id = session.getId();
			%>
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
				function user(gotopath) {
					$.ajax({
						type : 'post',
						url : '${ctx}/' + gotopath + '.do',
						success : function(html) {
							$('#mainContainer').empty();
							$('#mainContainer').append(html);
						},
					});
				}
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
				  $(function(){
					// 默认显示
					$(".dropdown-toggles").dropdown('toggles');
				});  
				
			</script>
							</div>
						</div>
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

