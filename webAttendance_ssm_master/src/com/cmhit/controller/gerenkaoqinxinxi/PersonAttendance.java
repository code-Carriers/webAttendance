package com.cmhit.controller.gerenkaoqinxinxi;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmhit.core.query.Json;
import com.cmhit.dahua.DahuaImplement;
import com.cmhit.po.AskForLeave;
import com.cmhit.po.Positionclasses;
import com.cmhit.po.Staff;
import com.cmhit.po.StaffRetroactive;
import com.cmhit.po.Staffclassperiodsigninrecord;
import com.cmhit.service.gerenkaoqinxinxi.IpersonAttendanceSv;
import com.cmhit.service.renshiguanli.staff.IstaffSv;
import com.cmhit.vo.ChuChaQingJiaTongJi;
import com.cmhit.vo.ChuQinJiaBanGongXiuTongJiVo;
import com.cmhit.vo.MyAskForLeave;
import com.cmhit.vo.MyClass;
import com.cmhit.vo.PunchCard;
import com.dahua.client.SdkClient;

@Controller
@RequestMapping("PersonAttendance")
public class PersonAttendance {
	@Autowired
	IpersonAttendanceSv ipersonAttendanceSv;
	@Autowired
	IstaffSv istaffSv;
	/**刷新考勤记录**/
	@RequestMapping("shuaxin")
		public void shuaxin(String beginDate, String endDate) {
		
	}
	/** 查询模糊查询所有员工
	 * */
	@ResponseBody
	@RequestMapping(value = "/selectAllStaff", produces = "text/html;charset=UTF-8")
	public String selectAllStaff(int[] departmentids, int positionid,
			int typeid, String number, String name,int groupid) {
		List<Staff> lidytList = istaffSv.selectAll(departmentids, positionid,
				typeid, number, name,groupid);
		
			   	return listToJson(lidytList);
		}	
	
	/**查询我的班次信息**/
	@RequestMapping("selectMyClass")
		public void selectPersonClass(String beginDate, String endDate,
				int staffid,PrintWriter pw,HttpServletRequest request) {
		
		/**刷新考勤记录**/
		/*调用实现考勤数据同步刷新*/
		DahuaImplement dahua=new DahuaImplement();
		
		//获取平台考勤数据
    	/*try {
			String token=dahua.login();
			int userId=dahua.getUserId();
			String userName=dahua.getUserName();
			String ip=dahua.getIp();
			String url="http://"+ip+"/CardSolution/attendance/result/page?token="+token;
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("pageNum",1);
			param.put("pageSize",300);
			param.put("dutyDateAfter",beginDate);
			param.put("dutyDateBefore",endDate);
			Staff user=(Staff) request.getSession().getAttribute("user");
			param.put("personCode",user.getStaffnumber());
			
			String result=SdkClient.post(url, JSON.toJSONString(param));
			System.out.println(result);
			JSONObject Object=JSON.parseObject(result);
			JSONObject Object1=JSON.parseObject(Object.getString("data"));
			JSONArray pageData=JSONArray.fromObject(Object1.getString("pageData"));
			
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			for(int i=0;i<pageData.size();i++){
			
				net.sf.json.JSONObject Object2=net.sf.json.JSONObject.fromObject(pageData.get(i));
				String checkInTime=Object2.getString("checkInTime");
				String checkOutTime=Object2.getString("checkOutTime");
				String dutyDate=Object2.getString("dutyDate");
				int personId=Object2.getInt("personId");
				System.out.println(dutyDate+" "+personId);
				
				List<PunchCard> punchCards=ipersonAttendanceSv.selectPunchCard(dutyDate, personId);
				
				if(punchCards.size()>0){
		    		PunchCard punchCard=punchCards.get(0);
		    		int signId=punchCard.getStaffClassPeriodSignInRecordId();
		    		String shangban=punchCard.getGoToWorkTime();
		    		String xiaban=punchCard.getTimeFromWork();
		    		System.out.println(signId);
		    		Staffclassperiodsigninrecord record=ipersonAttendanceSv.selectByPrimaryKey(signId);
		    		if(!checkInTime.equals("")){
		    			record.setSignintime(checkInTime);
		    			Date shangban1 = dateFormat1.parse(dutyDate+" "+shangban+":00");
		    			Date checkInTime1 = dateFormat1.parse(checkInTime);	
		    			
		    			if(checkInTime1.after(shangban1)) 
		    				record.setLateno(true);
		    			else record.setLateno(false);
		    			System.out.println(record.getLateno());
		    		}
		    		if(!checkOutTime.equals("")){
		    			record.setSignbacktime(checkOutTime);
		    			Date xiaban1 = dateFormat1.parse(dutyDate+" "+xiaban+":00");
		    			Date checkOutTime1 = dateFormat1.parse(checkOutTime);	
	    			
		    			if(checkOutTime1.before(xiaban1)) 
		    				record.setLeaveearlyno(true);
		    			else record.setLeaveearlyno(false);
		    		}
					ipersonAttendanceSv.updateClassperiodsigninrecord(record);
		    	}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		
			List<MyClass> myClasses= ipersonAttendanceSv.selectPersonClass(beginDate, endDate, staffid);
		   String str =Json.listToJson(myClasses);
		   pw.write(str);
	}
    @RequestMapping("selectPunchCard")
    public void selectPunchCard(String workDate,int staffId,PrintWriter pw){
    	List<PunchCard> punchCards=ipersonAttendanceSv.selectPunchCard(workDate, staffId);
    	  String str =Json.listToJson(punchCards);
		   pw.write(str);
    }
    /**正常班次的签到**/
    @RequestMapping("updateClassperiodsigninrecord")
   /* public void updateClassperiodsigninrecord(String signInTime,int attendanceClassChildId, int lateNo,int signInWayId,String SignInNumber
    		,int  staffClassPeriodSignInRecordId,String signBackTime,int leaveEarlyNo){
    	ipersonAttendanceSv.updateClassperiodsigninrecord(signInTime, attendanceClassChildId, lateNo, signInWayId, SignInNumber, staffClassPeriodSignInRecordId,signBackTime, leaveEarlyNo);
    }*/
    public void updateClassperiodsigninrecord(Staffclassperiodsigninrecord staffclassperiodsigninrecord,PrintWriter pw){
    int i=ipersonAttendanceSv.updateClassperiodsigninrecord(staffclassperiodsigninrecord);
    	pw.write(""+i);
    }
    @RequestMapping("insertClassperiodsigninrecord")
	/**新增不定时班次签到记录**/
    public void insertClassperiodsigninrecord(Staffclassperiodsigninrecord staffclassperiodsigninrecord) {
	 ipersonAttendanceSv.insertClassperiodsigninrecord(staffclassperiodsigninrecord);
    }
    @RequestMapping("insertAskForLeave")
    /**新增请假、出差**/
	public void insertAskForLeave(AskForLeave askforleave,PrintWriter pw) {
	 int i= ipersonAttendanceSv.insertAskForLeave(askforleave);
	  pw.write(""+i);
	}
    @RequestMapping("insertAskForLeaveMx")
	/**新增申请加班的班次**/
	public void insertJiaBan(AskForLeave askforleave, int classid,PrintWriter pw) {
		 int i=ipersonAttendanceSv.insertJiaBan(askforleave, classid);
		pw.write(""+i);
	}
    @RequestMapping("selectMyAskForLeave")
	/**查询加班请假等信息**/
	public void selectMyAskForLeave(int staffid,String startDate,String endDate,int checkTypeId,int registrationTypeId,PrintWriter pw) {
		 List<MyAskForLeave> list=ipersonAttendanceSv.selectAskForLeave(staffid, startDate, endDate, checkTypeId, registrationTypeId);
		 String str=listToJson(list);
		 pw.write(str);
		   
    }
    @RequestMapping("selectClassByAttendanceClassChildId")
    /**根据加班类别查询班组中对应的班次**/
	public void selectClassByAttendanceClassChildId(int staffId, int attendanceClassChildId,PrintWriter pw) {
		 List<Positionclasses> list=ipersonAttendanceSv.selectClassByAttendanceClassChildId(staffId, attendanceClassChildId);
				 String str=listToJson(list);
		 pw.write(str);
    }
    
    @RequestMapping("updateByAskForLeaveMxId")
    /**根据加班类别查询班组中对应的班次**/
	public void updateByAskForLeaveMxId(AskForLeave askforleave,int positionClassesId,
			int askForLeaveMxId,PrintWriter pw) {
		  int i=ipersonAttendanceSv.updateByAskForLeaveMxId(askforleave, positionClassesId, askForLeaveMxId);
		 pw.write(""+i);
    }
    @RequestMapping("updateAskForLeave")
    /**修改请假、出差、加班的申请信息**/
	public void  updateAskForLeave(AskForLeave askforleave,PrintWriter pw) {
		  int i=ipersonAttendanceSv.updateAskForLeave(askforleave);
		 pw.write(""+i);
    }
    @RequestMapping("deleteByAskForLeaveMxId")
    /**根据加班类别查询班组中对应的班次**/
	public void  deleteByAskForLeaveMxId(int askForLeaveId,int askForLeaveMxId,PrintWriter pw) {
		 int i=ipersonAttendanceSv.deleteByAskForLeaveMxId(askForLeaveId, askForLeaveMxId);
		 pw.write(""+i);
    }
    @RequestMapping("selectChuChaQingJia")
    /**查询我的考勤记录之出差请假**/
  	public void selectChuChaQingJia(String starDate,
  		String endDate, int[] staffids,PrintWriter pw){
  		 List<ChuChaQingJiaTongJi> list=ipersonAttendanceSv.selectChuChaQingJia(starDate, endDate, staffids);
		 String str=listToJson(list);
         pw.write(str);
    }
    @RequestMapping("selectChuQingJiaBanGongXiuTongJi")
  	 /**查询我的考勤记录之出勤、加班、公休**/
  	public void selectChuQingJiaBanGongXiuTongJi(
  		String starDate, String endDate, int[] staffids,PrintWriter pw) {
  		 List<ChuQinJiaBanGongXiuTongJiVo> list=ipersonAttendanceSv.selectChuQingJiaBanGongXiuTongJi(starDate, endDate, staffids);
		 String str=listToJson(list);
         pw.write(str);
    }
    @RequestMapping("insertStaffretroactive")
    /**新增补签登记记录**/
	public void insertStaffretroactive(StaffRetroactive staffretroactive,PrintWriter pw) {
		 int i=ipersonAttendanceSv.insertStaffretroactive(staffretroactive);
		 pw.write(""+i);
	}
    @RequestMapping("selectRecord")
    /**查询班段申请补签记录**/
	public void selectRecord(int staffClassesChildRecordId,boolean toAndFrom,PrintWriter pw) {
      List<StaffRetroactive>list=ipersonAttendanceSv.selectRecord(staffClassesChildRecordId, toAndFrom);
      String str=listToJson(list);
      pw.write(str);
    }
    @RequestMapping("selectMyRetroactive")
    /**多条件查询我的所有补签申请 **/ 
   	public void selectMyRetroactive(int staffid,String starDate,
   			String endDate, int checkTypeId,PrintWriter pw){
    	 List<StaffRetroactive>list=ipersonAttendanceSv.selectMyRetroactive(staffid,starDate, endDate, checkTypeId);
         String str=listToJson(list);
         pw.write(str);
    }
    
    
	/**list转json**/
	public <T> String listToJson(List<T> t) {
		JSONArray sjArray = JSONArray.fromObject(t);
		String str = sjArray.toString();
		return str;
	}
}
