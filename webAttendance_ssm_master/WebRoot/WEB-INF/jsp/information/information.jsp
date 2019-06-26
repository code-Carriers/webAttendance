<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" scope="page" var="ctx"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'main.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
<link rel="stylesheet" href="${ctx}/css/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
<style type="text/css">
.thistop {
	padding-left: 15px;
	padding-top: 9px;
}

.table_hover tr:HOVER {
	background-color: rgba(98, 168, 209, 0.11);
}

.thisdate {
	font-size: 15px;
	width: 130px;
	font-weight: 800;
	height: 11px;
}

.trClass {
	background: rgba(98, 168, 209, 0.11);
}

#tbodyStaff tr td {
	text-align: center;
	vertical-align: middle;
}
</style>
<script type="text/javascript">
$(document).ready(function(e) {
   $("#btn").click(function(){//提交信息
	
		var staffname=$("#staffname").val();
 		var phone=$("#loginnumber").val();
 		var email=$("#email").val();
 		
 		$.ajax({
			async : false,
			type : 'post',
			url : "${ctx}/menuController/updateInfo.do?staffname="+staffname+"&phone="+phone+"&email="+email,
			contentType : 'application/json',
			//dataType : "text",
			//scriptCharset : "utf-8",
			success : function(data) {
				if(data=="1")
				  alert("保存成功");	
			}
			 /* error:function(data){
						 alert(data);	
			}  */
		});
 	 
 	});
 	
 
});
</script>
<body style="text-align: center">
	<div></div>
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="./main.jsp">首页</a>
			</li>
			<li class="active">个人信息管理
			</li>
			<li class="active">设置个人信息</li>
		</ul>
	</div>

	<div >
		<div  style="margin: 10px; font-size: 20px;">
	<div ><span>基本信息</span></div>
	<div >
    
    	
    
    <ul >
   
    <li><label>照片</label>
    	<img style="width:150px;height:138px"  src="./pic/${user.headimage}">
    </li>
    <li>
      <label>工号 </label>
      <input name="staffnumber" type="text" class="scinput" value="${user.staffnumber}" readonly="readonly" id="staffnumber"/>
    </li>
    <li>
      <label>姓名 </label>
      <input name="staffname" type="text" class="dfinput" value="${user.staffname}"  id="staffname"/>
     </li>
      <li>
      <label>卡号 </label>
      <input name="cardnumber" type="text" class="dfinput" value="${user.cardnumber}" readonly="readonly" id="cardnumber"/>
     </li>
     <li><label>部门</label>  
        <input name="department" type="text" class="dfinput" value="${dept.name}" readonly="readonly" id="department"/>     
     </li>
     <li>
      <label>电话号码 </label>
      <input name="loginnumber" type="text" class="dfinput" value="${user.loginnumber}" id="loginnumber"/>
     </li>
     <li>
      <label>电子邮箱 </label>
      <input name="email" type="text" class="dfinput" value="${user.email}" id="email"/>
     </li>
     <li>
      <label>入职日期 </label>
      <input name="dateofentry" type="text" class="dfinput" value="${user.dateofentry}" readonly="readonly" id="dateofentry"/>
     </li>
    
   
    
   
    	<!-- <input name="" type="button" class="btn" value="确认保存" onclick="saveButton()"/> -->
    	 <button type="button" id="btn" class="btn btn-sm btn-success">
				确认保 存 <i class="icon-check icon-on-right bigger-110"></i>
		 </button>
    	 
    
    </ul>
    </div>

		</div>
	</div>
	
</body>
</html>

