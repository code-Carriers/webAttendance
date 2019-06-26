<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" scope="page" var="ctx"></c:set>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'main.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="${ctx}/css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${ctx}/css/jedate.css" type="text/css"></link>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${ctx}/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.validate.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.jedate.min.js"></script>
<style type="text/css">
#header tr th {
	text-align: center;
	width: 90px;
	font-size: 15px;
	font-weight: 600;
	background: #fafafa;
}
#KaoQinheader tr th{
    background: rgb(67, 142, 185);
    color: white;
    max-width: 100px;
    min-width: 50px;
    text-align: center;
}
#YuanGongKaoQinheader tr th{
    background: rgb(67, 142, 185);
    color: white;
    max-width: 100px;
    min-width: 50px;
    text-align: center;
}
#tbodyGeRenPaiBanXinXi tr td {
	height: 80px;
	text-align: center;
}
.tbody tr td{
text-align: center;
    font-weight: 800;
    font-size: 17px;
    color: #736e6e;
    min-width: 70px;
    max-width: 150px;
}
.showdiv {
	display: none;
}

.divBanZu {
	font-size: 15px;
	font-weight: 600;
}

.BanDuanTiShi {
	z-index: 100;
	background: rgb(52, 154, 184);
	text-align: center;
	max-width: 350px;
	min-width: 200px;
	position: absolute;
	color: white;
	position: absolute;
	position: absolute;
}

.tubiao {
	position: relative;
	width: 0;
	height: 0;
	border-color: rgba(199, 28, 28, 0);
	border-style: solid;
	bottom: 0;
	left: 47%;
	top: 29px;
	margin-left: -5px;
	border-width: 30px 26px 0px 0px;
	border-top-color: rgb(52, 154, 184);
}

.banci {
	padding-top: 10px;
	font-size: 20px;
}
.thismydiv{
    margin-top: 5px;
    font-size: 10px;
    font-weight: 100;
    color: red;
    }
    .myhiden{
    display: none;}
    .btn_buqian{
   background: #438eb9;
   color: white;}
</style>
<SCRIPT type="text/javascript">
	$(function() {
		$("#menuContent").hide();
	
		bangdingDepartment();
	});
 
	var setting = {
		view : {
			selectedMulti : false
		//是否允许多选
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			//zTree节点的点击事件
			onClick : onClick
		}
	};
	var setting1 = {
		view : {
			selectedMulti : false
		//是否允许多选
		},
		data : {
			simpleData : {
				enable : true
			}
		}/* ,
		callback : {
			//zTree节点的点击事件
			onClick : onclickinsert
		} */
	};

	//点击某个节点 然后将该节点的名称赋值值文本框
	function onClick(e, treeId, treeNode) {
		getChildNodes(treeNode);
		$("#txtDepatmentid").val(treeNode.id);
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		//获得选中的节点
		var nodes = zTree.getSelectedNodes(), v = "";
		//根据id排序
		nodes.sort(function compare(a, b) {
			return a.id - b.id;
		});
		for ( var i = 0, l = nodes.length; i < l; i++) {
			v += nodes[i].name + ",";
		}
		//将选中节点的名称显示在文本框内
		if (v.length > 0)
			v = v.substring(0, v.length - 1);
		var cityObj = $("#Department");
		cityObj.attr("value", v);
		//隐藏zTree
		hideMenu();
		return false;
	}

	var departmentNodes = [];
	//获取子节点
	function getChildNodes(treeNode) {
		var childNodes = $.fn.zTree.getZTreeObj("treeDemo").transformToArray(
				treeNode);
		var nodes = new Array();
		for (i = 0; i < childNodes.length; i++) {
			nodes[i] = childNodes[i].id;
		}
		departmentNodes = nodes;//.join(",")
	}

	//显示树
	function showMenu() {
		var cityObj = $("#Department");
		var cityOffset = $("#Department").offset();
		$("#menuContent").css({
			left : cityOffset.left + "px",
			top : "82px"
		}).slideDown("fast");
	}

	//隐藏树
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
	}
	//查询部门绑定下拉树、
	function bangdingDepartment() {
		var zNode = [];
		$.ajax({
			async : false,//同步异步
			type : 'post',
			url : '${ctx}/staff/selectDepartment.do',
			dataType : "json",
			scriptCharset : "utf-8",
			success : function(data) {
				for ( var i = 0; i < data.length; i++) {
					zNode.push({
						id : data[i].departmentid,
						pId : data[i].fatherid,
						name : data[i].name,
						open : true
					});
				}
				$.fn.zTree.init($("#treeDemo"), setting, zNode);
				$.fn.zTree.init($("#Ztree"), setting1, zNode);

			},
		});
	} 
 
 	var staffs=[],staffnames=[],staffnumbers=[],depts=[];
 //模糊查询员工信息
	function selectAllStaff() {
		  $.ajax({
					async : false,//同步异步
					type : 'post',
					url : "${ctx}/staff/selectAllStaff.do?departmentids="
							+ departmentNodes+"&positionid=0&typeid=0&number="+
							 $("#txtNumber").val()+"&name="+ $("#txtName").val()+
						    "&groupid=0",					
					dataType : "json",
					scriptCharset : "utf-8",
					success : function(data) {
						 
						$('#tbodyStaff').empty();
						staffs=[],staffnames=[],staffnumbers=[],depts=[];
  	  					for(var i=0;i<data.length;i++){
  	  						 var staffid=data[i].staffid;
  	 						 staffs.push(staffid);
  	 						 staffnames.push(data[i].staffname);
  	 						 staffnumbers.push(data[i].staffnumber);
  	 						 depts.push(data[i].staffPosition.department.name)
  	  					}  
  	  					
					},
				});  
	}
</script>
<body style="text-align: center" onclick="hideMenu()">
	<div class="breadcrumbs" id="breadcrumbs">
		<ul class="breadcrumb">
			<li><i class="icon-home home-icon"></i> <a href="./main.jsp">首页</a></li>
			<li class="active">考勤日志</a></li>
			<li class="active">个人考勤记录</li>
		</ul>
	</div>
	
	<div class="content_wrap">
		<div class="zTreeDemoBackground left" style="margin: 10px; font-size: 15px;">
			<table>
				<tr>
					<td>所属机构:</td>
					<td><input id="Department" readonly="readonly" type="text"
						onblur="hideMenu()" onclick="showMenu()" style="width:115px"
						class="form-control"> <input type="hidden"
						id="txtDepartmentid">
					</td>
				
					<td>起始日期</td>
					<td><input placeholder="YYYY-MM-DD" class="workinput wicon mr25" style="width: 150px;" name="startdate" id="startDate" type="text">
								<script type="text/javascript">
								$("#startDate").jeDate({
							    isinitVal:false,
							    ishmsVal:false,
							    format:"YYYY-MM-DD",
							    zIndex:3000,
							 });
							</script>
								</td>
					<td>结束日期</td>
					<td><input placeholder="YYYY-MM-DD" class="workinput wicon mr25" style="width: 150px;" name="enddate" id="endDate" type="text">
								<script type="text/javascript">
								$("#endDate").jeDate({
							    isinitVal:false,
							    ishmsVal:false,
							    format:"YYYY-MM-DD",
							    zIndex:3000,
							 });
							</script>
								</td>
					
					<td><input id="txtNumber" placeholder="人员编号" type="text" style="width: 100px;" class="form-control"></td>
					<td><input id="txtName" placeholder="姓名" type="text" style="width: 100px;" class="form-control"></td>
					<td>	
						<button type="button" onclick="selectkaoqingjilu()"
							class="btn btn-info btn-sm"
							style="font-size:14px;margin-right: 5px;">
							查询 <i class="icon-search icon-on-right bigger-120"></i>
						</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div id="menuContent" class="menuContent"
		style="displayx: none; position: absolute;z-index: 99;background-color: #f9f9f9;min-height: 200px;">
		<ul id="treeDemo" class="ztree" style="margin-top: 0; width: 110px;">
		</ul>
	</div>
	
	<div style="max-width: 1130px;padding-left: 10px;overflow: auto; min-height: 500px; max-height: 500px;">
		
		 <table border="1" class="table"style="max-width: 2000px;min-width: 1120px;" id="tbYuangGong">
				<thead>
						<tr>
							<th style="text-align: center; width: 104px;">考勤时间</th>
							<th style="text-align: center;">姓名</th>
							<th style="text-align: center;">员工编号</th>
							<th style="text-align: center;">部门</th>
							<th style="text-align: center;">签入时间</th>
							<th style="text-align: center;">签出时间</th> 
							<th style="text-align: center;">考勤结果</th>
							<th style="text-align: center;">是否迟到</th>
							<th style="text-align: center;">是否早退</th>
							<th style="text-align: center;">备注</th> 
							
						</tr>
					</thead>
				<tbody id="tbodyStaff" class="tbody">
				</tbody>
		</table> 
	</div>
</body>
<script type="text/javascript">

 
 
  
 
 function isDate(dateString){
  if(dateString.trim()==""){
  alert("请选择日期");
  return false;}  
  var r=dateString.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/); 
  if(r==null){
   alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例  如：2008-08-08\n\r");
  return false;
  }
  var d=new Date(r[1],r[3]-1,r[4]);  
  var num = (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
  if(num==0){
   alert("请输入格式正确的日期\n\r日期格式：yyyy-mm-dd\n\r例  如：2008-08-08\n\r");
  }
  return (num!=0);
 } 
  /**判断起始时间是否大于结束时间**/
	 function panduanshijian(beginDate,endDate){
	 var startDate=new Date(beginDate);
	 var endDate=new Date(endDate);
	 if(endDate.getTime()>=startDate.getTime()){
	 return true;
	 }
	 else{
	 return false;
	 }
	 }
	 
 /**查询出勤加班公休信息**/
 function selectkaoqingjilu(){
 
   var start_date= $("#startDate").val();
   var end_date= $("#endDate").val();
  
  	if(isDate(start_date)&&isDate(end_date)){
 	  if(panduanshijian(start_date,end_date)){
   		
   		selectAllStaff();
   		for(var i=0;i<staffs.length;i++){
   			var startDate=start_date+" 00:00";
   			var endDate=end_date+" 23:59";
   			
   			var data = ajax("${ctx}/PersonAttendance/selectMyClass.do?beginDate="
				+ startDate + "&endDate=" + endDate + "&staffid="+staffs[i]);
			var html="";
			for(var j=0;j<data.length;j++){
				var r=data[j].signInformation.toString().split("_");
				html="<tr><td>"+data[j].workDate+
					 "</td><td>"+staffnames[i]+
					 "</td><td>"+staffnumbers[i]+
					 "</td><td>"+depts[i]+ 
					 "</td><td>"+r[1]+ 
					 "</td><td>"+r[2]+
					 "</td><td>"+(r[1]!="缺签"&&r[2]!="缺签"?"出勤":"缺勤")+
					 "</td><td>"+(r[3]>0?"是":"否")+ 
					 "</td><td>"+(r[4]>0?"是":"否")+ 
					 "</td><td>"+r[5]+ 
					 "</td></tr>" ;
					 $('#tbodyStaff').append(html);
   				
   		    }
   		
   		}
   	}
   	else{
 	 	alert("起始日期必须大于终止日期，请重新选择日期"); 
    }
  }
  
   
 }

	 /** 封装的ajax **/
	function ajax(url) {
		var mydata;
		$.ajax({
			async : false,
			type : 'post',
			url : url,
			dataType : "json",
			scriptCharset : "utf-8",
			success : function(data) {
				mydata = data;
			}
		});
		return mydata;
	}
</script>
</html>

