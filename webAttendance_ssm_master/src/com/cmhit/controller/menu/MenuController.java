package com.cmhit.controller.menu;

import java.io.IOException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmhit.po.Department;
import com.cmhit.po.Staff;
import com.cmhit.po.Staffclassperiodsigninrecord;
import com.cmhit.po.StaffClassesRecord;
import com.cmhit.service.gerenkaoqinxinxi.IpersonAttendanceSv;
import com.cmhit.service.renshiguanli.staff.IstaffSv;
import com.dahua.client.SdkClient;
import com.dahua.DahuaImplement;

@Controller
@RequestMapping("/menuController")
public class MenuController {
	@Autowired
	IstaffSv istaffSv;
	@Autowired
	IpersonAttendanceSv ipersonAttendanceSv;
	StaffClassesRecord staffclassesrecord;

	@RequestMapping("/redirectScheduling")
	/** 人员排班 **/
	public String ZhuanTiaoYeMian(HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		if(!user.getRemark().equals("0"))
			return "/role";
		return "/paibanguangli/paiban/scheduling";
	}

	/** 班次设置 **/
	@RequestMapping("/selectClasses")
	public String selectAll(HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		if(!user.getRemark().equals("0"))
			return "/role";
		return "/paibanguangli/bancishezhi/setClasses";
	}

	/** 员工管理 **/
	@RequestMapping("/selectStaff")
	public String redirect(HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		if(!user.getRemark().equals("0"))
			return "/role";
		return "/renshiguanli/renyuanluru/staff";
	}
	
	/** 员工个人密码修改 **/
	@RequestMapping("/alterStaff")
	public String redirect1() {
		return "/renshiguanli/renyuanluru/pw";
	}

	/** 考勤制度设置 **/
	@RequestMapping("/selectSystem")
	public String selectSystem(HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		if(!user.getRemark().equals("0"))
			return "/role";
		return "/kaoqinguangli/kaoqinzhidushezhi/setSystem";
	}

	/** 考勤类别设置 **/
	@RequestMapping("/selectByAttendanceType")
	public String selectByAttendanceType(HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		if(!user.getRemark().equals("0"))
			return "/role";
		return "/kaoqinguangli/kaoqinleibieshezhi/setAttendanceType";
	}

	@RequestMapping("/selectMyClass")
	/** 我的班次查询 **/
	public String selectMyClass() {
		return "/gerenkaoqinxinxi/myClass";
	}

	/** 考勤打卡 **/
	@RequestMapping("/punchCard")
	public String punchCard() {
		return "/gerenkaoqinxinxi/PunchCard";
	}

	/*	*//** 考勤打卡 **//*
						 * @RequestMapping("/punchCard2")
						 * 
						 * @ResponseBody public String punchCard2(HttpServletRequest
						 * request,HttpServletResponse response) throws Exception{
						 * response.setContentType("text/html;charset=UTF-8"); //设置请求以及响应的内容类型以及编码方式
						 * response.setCharacterEncoding("UTF-8"); String url=
						 * "http://192.168.78.119/face/groupInfo/page?token=9799541f35a3a4a19b6fea48a6876967";
						 * JSONArray json = JSONArray.fromObject(SdkClient.get(url));
						 * System.out.println(json.get(1)); //response.getWriter().write(str);
						 * //将str字符传输到前台 return "/gerenkaoqinxinxi/PunchCard2"; }
						 */


	@RequestMapping("/selectMyAttendance")
	/** 查询我的考勤记录 **/
	public String selectMyAttendance() {
		/*try {
			DahuaImplement dahua = new DahuaImplement(); // 获取平台考勤数据 try {
			String token = dahua.login();
			String ip = dahua.getIp();
			String url = "http://" + ip + "/CardSolution/attendance/result/page?userId=1&userName=system&token=" + token;
			// String url2 =
			// "http://192.168.78.119//CardSolution/attendance/result/page?userId=1&userName=system&token";
			Map<String, Object> param = new HashMap<String, Object>();
			 param.put("personCode","009015"); 
			param.put("pageNum", 1);
			param.put("pageSize", 150);
			 System.out.println(SdkClient.post(url2, JSON.toJSONString(param))); 
			String str = SdkClient.post(url, JSON.toJSONString(param));
			JSONObject object1 = JSONObject.parseObject(str);
			JSONObject object2 = object1.getJSONObject("data");
			JSONArray object23 = object2.getJSONArray("pageData");
			
			 * List<Staffclassperiodsigninrecord> record = ipersonAttendanceSv.selectAll();
			 
			List<StaffClassesRecord> record2 = ipersonAttendanceSv.selectAll2();
			for (int i = 0; i < object23.size(); i++) {
				int str21 = (int) object23.getJSONObject(i).get("personId");
				if (str21 == 0) {
					str21 = 666;
				}
				String str22 = (String) object23.getJSONObject(i).get("checkInTime");
				if (str22.equals("")) {
					str22 = "2019-01-01 11:11:11";
				}
				String str23 = (String) object23.getJSONObject(i).get("checkOutTime");
				if (str23.equals("")) {
					str23 = "2019-01-01 11:11:11";
				}
				for (int k = 0; k < record2.size(); k++) {
					if (record2.get(k).getStaffid() == str21) {
						int str24 = (int) record2.get(k).getStaffclassesrecordid();
						 for (int j = 0; j < record.size(); j++) { 
						
						 * if(record2.get(j).getStaffclassesrecordid() ==
						 * record.get(j).getStaffclassperiodsigninrecordid()) {
						 
						 int mm = record.get(j).getStaffclassperiodsigninrecordid(); 
						 System.out.println(str24); 
						ipersonAttendanceSv.updateSelective2(str21, str22, str23, str24);
						 } 
						 } 
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return "/gerenkaoqinxinxi/myAttendance";
	}
	/*
	 * Staff user=(Staff) request.getSession().getAttribute("user"); Date d = new
	 * Date(); SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 * 调用实现考勤数据同步刷新 DahuaImplement dahua=new DahuaImplement(); //获取平台考勤数据 try {
	 * String token=dahua.login(); int userId=dahua.getUserId(); String
	 * userName=dahua.getUserName(); String ip=dahua.getIp(); String
	 * url="http://"+ip+"/CardSolution/attendance/result/page?token="+token;
	 * Map<String,Object> param = new HashMap<String,Object>();
	 * param.put("pageNum",1); param.put("pageSize",1000);
	 * param.put("dutyDateAfter",sdf.format(d));
	 * param.put("dutyDateBefore",sdf.format(d));
	 * 
	 * if(!user.getStaffnumber().equals(""))
	 * param.put("personCode",user.getStaffnumber()); String
	 * result=SdkClient.post(url, JSON.toJSONString(param));
	 * System.out.println(result); JSONObject Object=JSON.parseObject(result);
	 * JSONObject Object1=JSON.parseObject(Object.getString("data")); JSONArray
	 * pageData=JSONArray.fromObject(Object1.getString("pageData")); for(int
	 * i=0;i<pageData.size();i++){
	 * 
	 * net.sf.json.JSONObject
	 * Object2=net.sf.json.JSONObject.fromObject(pageData.get(i)); String
	 * checkInTime=Object2.getString("checkInTime"); String
	 * checkOutTime=Object2.getString("checkOutTime"); String
	 * dutyDate=Object2.getString("dutyDate"); int
	 * personId=Object2.getInt("personId");
	 * System.out.println(dutyDate+" "+personId);
	 * 
	 * List<PunchCard> punchCards=ipersonAttendanceSv.selectPunchCard(dutyDate,
	 * personId); System.out.println(punchCards.size()); if(punchCards.size()>0){
	 * PunchCard punchCard=punchCards.get(0); int
	 * signId=punchCard.getStaffClassPeriodSignInRecordId();
	 * System.out.println(signId); Staffclassperiodsigninrecord
	 * record=ipersonAttendanceSv.selectByPrimaryKey(signId);
	 * record.setSignintime(checkInTime); record.setSignbacktime(checkOutTime);
	 * ipersonAttendanceSv.updateClassperiodsigninrecord(record); } } } catch
	 * (Exception e) { // TODO Auto-generated catch block e.printStackTrace(); }
	 */

	@RequestMapping("/selectMyAskForLeave")
	/** 我要请假 **/
	public String selectMyAskForLeave() {
		return "/gerenkaoqinxinxi/myAskForLeave";
	}

	@RequestMapping("/selectAllAskForLeave")
	/** 请假、加班申请处理 **/
	public String selectAllAskForLeave(HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		if(user.getRemark().equals("2"))
			return "/role";
		return "/kaoqinchuli/qingjiajiabanchuli/dealwithAskForLeave";
	}

	@RequestMapping("/selectMyRetroactive")
	/** 查询补签申请 **/
	public String selectMyRetroactive() {
		return "/gerenkaoqinxinxi/myRetroactive";
	}

	@RequestMapping("/selectDealwihthRetroative")
	/** 查询补签、异常信息进行处理 **/
	public String selectDealwihthRetroative(HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		if(!user.getRemark().equals("0"))
			return "/role";
		return "/kaoqinchuli/buqianyichangchuli/dealwihthRetroative";
	}

	@RequestMapping(value = "/bumenattendanceStatistics")
	/** 查询部门考勤统计信息 **/
	public String bumenattendanceStatistics() {
		return "/kaoqintongji/bumenkaoqintongji/attendanceStatistics";
	}

	/* author=黄宽录 */
	@RequestMapping(value = "/gerenattendanceStatistics")
	/** 查询个人考勤统计信息 **/
	public String gerenattendanceStatistics() {
		return "/kaoqintongji/gerenkaoqintongji/attendanceStatistics";
	}

	@RequestMapping(value = "/gongsiattendanceStatistics")
	/** 查询公司考勤统计信息 **/
	public String gongsiattendanceStatistics() {
		return "/kaoqintongji/gongsikaoqintongji/attendanceStatistics";
	}

	/*
	 * @RequestMapping("/attendanceStatistics")
	 *//** 查询部门考勤统计信息 **//*
							 * public String attendanceStatistics(){ return
							 * "/kaoqintongji/bumenkaoqintongji/attendanceStatistics"; }
							 */
	@RequestMapping("/login")
	public String login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		Staff user = istaffSv.selectUserByUserName(userName, passWord);
		
		Cookie nameCookie = new Cookie("username", username); // 设置Cookie的有效期为3天
		nameCookie.setMaxAge(60 * 60 * 24 * 3);
		nameCookie.setPath("/");
		response.addCookie(nameCookie);
		if (user != null) {
			// 获取部门
			Department dept = istaffSv.selectDeptByStaffID(user.getStaffid());
			request.getSession().setAttribute("dept", dept);
			request.getSession().setAttribute("user", user);

			// 判断是否保存密码
			String flag = request.getParameter("remPwd");
			// 保存到cookie
			if ("checked".equals(flag)) { // 创建两个Cookie对象
				
				Cookie pwdCookie = new Cookie("password", password);
				pwdCookie.setMaxAge(60 * 60 * 24 * 3);
				pwdCookie.setPath("/");
				response.addCookie(pwdCookie);

			} else {// 删除cookie内的密码
				Cookie newCookie = new Cookie("password", null); // 假如要删除名称为username的Cookie
				newCookie.setMaxAge(0); // 立即删除型
				newCookie.setPath("/");
				response.addCookie(newCookie); // 重新写入，将覆盖之前的
			}

			return "../../../main";
		} else {
			/*Cookie[] cookies=request.getCookies();
			for(int i=0;i<cookies.length;i++)
				if(cookies[i].getName().equals("password")){
					cookies[i].setMaxAge(0);
					
				}*/
			Cookie newCookie = new Cookie("password", null); // 假如要删除名称为username的Cookie
			newCookie.setMaxAge(0); // 立即删除型
			newCookie.setPath("/");
			response.addCookie(newCookie); // 重新写入，将覆盖之前的
			request.setAttribute("tishi", "用户名或密码错误");
			return "../../../login";
		}
	}

	@SuppressWarnings("null")
	@RequestMapping("/exit")
	public String exit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/* return "/login_main/login" */;
		Cookie newCookie = new Cookie("password", null); // 假如要删除名称为username的Cookie
		newCookie.setMaxAge(0); // 立即删除型
		response.addCookie(newCookie); // 重新写入，将覆盖之前的
		
		HttpSession session = request.getSession(false);// 防止创建Session
		if (session == null) {
			/* response.sendRedirect("/login_main/login.jsp"); */
			session.removeAttribute("user");
			System.out.println("user已清除");
			request.getSession().invalidate();
		}
		return "../../../login";
	}
	@RequestMapping("/updatePassJsp")
	public String updatePassJsp(HttpServletRequest request) {
		return "/information/updatePass";
	}
	@ResponseBody
	@RequestMapping(value="/updatePass")
	public String updatePass(String password,HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		user.setPassword(password);
		istaffSv.update(user);
		return "1";
	}
	@RequestMapping(value="/information")
	public String information(HttpServletRequest request) {
		
		return "/information/information";
	}
	@ResponseBody
	@RequestMapping(value="/updateInfo")
	public String updateInfo(String staffname,String phone,String email,HttpServletRequest request) {
		Staff user=(Staff) request.getSession().getAttribute("user");
		user.setStaffname(staffname);
		user.setEmail(email);
		user.setLoginnumber(phone);
		istaffSv.update(user);
		return "1";
	}
}
