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
	
	if($("#password").val()==""){
		alert("请输入密码……");
 		return false;
	}
 	else if($("#password").val()!=$("#repassword").val()){
		alert("密码不一致……");
 		return false;
	}
 	else
 		
 		$.ajax({
			async : false,
			type : 'post',
			url : "${ctx}/menuController/updatePass.do?password="+$("#password").val(),
			contentType : 'application/json',
			dataType : "text",
			scriptCharset : "utf-8",
			success : function(data) {
				if(data=="1")
				  alert("修改成功");	
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
			<li class="active">设置密码</li>
		</ul>
	</div>

	<div class="content_wrap">
		<div class="zTreeDemoBackground left"
			style="margin: 10px; font-size: 15px;">
			<form action="update_emp" method="post"  enctype="multipart/form-data" >
	
    <ul >
    <li>
      <label>账号 </label>
      </label><input name="username" type="text" value="${user.username}" readonly="readonly" id="username"/>
    </li>
    <li>
      <label>输入新密码 <font color="red">*</font></label>
      </label><input name="password" type="password" id="password"/>
     </li>
     <li>
      <label>确认新密码 <font color="red">*</font></label>
      </label><input name="repassword" type="password" id="repassword"/>
     </li>
     
    <li><label>&nbsp;</label>
    	 <input name="" type="reset" value="重置"/> 
    	 <input type="button"  id="btn" value="确认保存"/> 
    </li>
    </ul>
    </div>
</form>

		</div>
	</div>
	
</body>
</html>

