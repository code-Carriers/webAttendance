package com.cmhit.controller.paibanguangli;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmhit.controller.renshiguanli.staff;
import com.cmhit.dahua.DahuaImplement;
import com.cmhit.po.AttributeGatherChild;
import com.cmhit.po.Classesgroup;
import com.cmhit.po.Positionclasses;
import com.cmhit.po.Staff;
import com.cmhit.service.paibanguangli.bancishezhi.IsetClassesSv;
import com.cmhit.service.paibanguangli.paiban.IclassGroupSv;
import com.cmhit.service.paibanguangli.paiban.IstaffClassesRecordSv;
import com.cmhit.service.renshiguanli.staff.IstaffSv;
import com.cmhit.vo.GengGaiPaiBanList;
import com.cmhit.vo.GengGaiPaiPanXinXi;
import com.cmhit.vo.YuanGongFenBanJiLu;
import com.cmhit.vo.paibanchaxunVo;
import com.dahua.client.SdkClient;

@Controller
@RequestMapping("/Scheduling")
public class Scheduling {
	@Autowired
	IstaffClassesRecordSv istaffClassesRecordSv;
	@Autowired
	IstaffSv istaffSv;
	@Autowired
	IsetClassesSv isetClassesSv;
	@Autowired
	IclassGroupSv iclassGroupSv;

	
	/** 查询班组信息 **/
	@RequestMapping("/selectClassGroup")
	public void selectClassGroup(PrintWriter pw) {
		List<paibanchaxunVo> banzuList = istaffClassesRecordSv
				.selectClassGroup();
		String str = listToJson(banzuList);
		pw.write(str);
	}
	/** 模糊查询员工信息 **/
	@RequestMapping("/selectStaff")
	public void selectStaff(int departmentId, String name, String number,
			PrintWriter pw) {
		List<Staff> staffs = istaffSv.selectStaff(departmentId, name, number);
		String str = listToJson(staffs);
		pw.write(str);
	}

	/** 查询班次信息 **/
	@RequestMapping("/selectClass")
	public void selectClass(PrintWriter pw) {
		List<Positionclasses> listClass = isetClassesSv.selectClass();
		String str = listToJson(listClass);
		pw.write(str);
	}

	/** 新增班组 **/
	@RequestMapping("/insertGroupName")
	public void insertClassGroup(AttributeGatherChild t, PrintWriter pw) {
		int id = iclassGroupSv.insertClassGroup(t);
		pw.write("" + id);
	}
	 /**修改班组名称**/
	@RequestMapping("/editGroupName")
    public void editClassGroupName(AttributeGatherChild t,PrintWriter pw){
		int i= iclassGroupSv.editClassGroupName(t);
		pw.write(i);
	}
	/** 更改人员班组id **/
	@RequestMapping("/updateStaffGroupId")
	public void updateStaffGroupId(int[] staffids, int groupid, PrintWriter pw) {
		int i = istaffSv.updateStaffGroupId(staffids, groupid);
		pw.write("" + i);
	}
	/** 新增班组班次 **/
	@RequestMapping("/insertClassGroup")
	public void insertClassGroup(int[] classIds, int groupId, PrintWriter pw) {
		int i = iclassGroupSv.insertClassGroup(classIds, groupId);
		pw.write("" + i);
	}
	/** 编辑班组时查询班组人员信息 **/
	@RequestMapping("/selectGroupStff")
	public void selectGroupStff(int groupid, PrintWriter pw) {
		List<Staff> staffs = istaffSv.selectStaffByGroupId(groupid);
		String str = listToJson(staffs);
		pw.write(str);
	}
	/** 编辑班组时查询班次信息 **/
	@RequestMapping("/selectClassTimes")
	public void selectClassTimes(int groupid, PrintWriter pw) {
		List<Classesgroup> classesgroups = iclassGroupSv
				.selectClassesByGroupID(groupid);
		String str = listToJson(classesgroups);
		pw.write(str);
	}
	/** 编辑班组修改员工信息 **/
	@RequestMapping("/editByGroupidAndStaffids")
	public void editByGroupidAndStaffids(int groupid, int[] staffids,
			PrintWriter pw) {
		int i = istaffSv.editByGroupidAndStaffids(groupid, staffids);
		pw.write("" + i);
	}
	/**   编辑班组时修改班组班次信息**/
	@RequestMapping("/editClassesgroup")
	public void editClassesgroup(int[] ClassIds, int groupid, PrintWriter pw) {
		try {
			DahuaImplement dahua = new DahuaImplement(); 
			String token = dahua.login();
			String ip = dahua.getIp();
			String url = "http://" + ip + "/CardSolution/attendance/result/page?userId=1&userName=system&token=" + token;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pageNum", 1);
			param.put("pageSize", 150);
			String str = SdkClient.post(url, JSON.toJSONString(param));
			JSONObject object1 = JSONObject.parseObject(str);
			JSONObject object2 = object1.getJSONObject("data");
			com.alibaba.fastjson.JSONArray object23 = object2.getJSONArray("pageData");
			//List<StaffClassesRecord> record2 = ipersonAttendanceSv.selectAll2();
			for (int i1 = 0; i1 < object23.size(); i1++) {
				int str1 = (int) object23.getJSONObject(i1).get("dailyId");
				int i = iclassGroupSv.editClassesgroup(ClassIds, str1);
					}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**删除班组和附带信息**/
	@RequestMapping("/deleteGroupClass")
	public void deleteGroupClass(int groupid,PrintWriter pw) {
	 int i=	iclassGroupSv.deleteGroupClass(groupid);
		 pw.write(""+i);
	}
	@RequestMapping("/selectGroupRecord")
	 /** 查询班组员工分班记录 **/
	public void selectGroupRecord(int goupid,String bginDate,String endDate,PrintWriter pw)
	  {
		 List<YuanGongFenBanJiLu> YuanGongFenBanJiLus= istaffClassesRecordSv.selectGroupRecord(goupid, bginDate, endDate);
		  String str=listToJson(YuanGongFenBanJiLus);
		  pw.write(str);
	  }
	
	/**更改班组排班信息**/
	@RequestMapping("/updatePaiBanXinXi")
	public void updatePaiBanXinXi(GengGaiPaiBanList updateAndInsert,int[] deleteIds,int groupid,PrintWriter pw){
    int i= istaffClassesRecordSv.updatePaiBanXinXi(updateAndInsert.getListInsert(), deleteIds, updateAndInsert.getListUpdate(),groupid);
    pw.write(""+i);
	}
	
	/*按选择星期的日期排班*/
	@RequestMapping("/paiban")
	public void paiban(int[] xingqi,String startDate,String endDate,int classid,int groupid,PrintWriter pw){
		List<Staff> staffs = istaffSv.selectStaffByGroupId(groupid);
		GengGaiPaiBanList riqipaibanList=new GengGaiPaiBanList();
		List<GengGaiPaiPanXinXi> listInsert = new ArrayList<GengGaiPaiPanXinXi>();//创建集合对象；
		try {
			Calendar c = Calendar.getInstance();
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date beginDate = dateFormat1.parse(startDate);
			Date overDate = dateFormat1.parse(endDate);	
			Date date = beginDate;
			while (!date.after(overDate)) {
				//System.out.println(dateFormat1.format(date));
				c.setTime(date);
				int week_index = c.get(Calendar.DAY_OF_WEEK) - 1;
				for(int i=0;i<xingqi.length;i++)
					if(week_index == xingqi[i]){
					for(int j=0;j<staffs.size();j++){
						GengGaiPaiPanXinXi riqipaiban=new GengGaiPaiPanXinXi();
						riqipaiban.setStaffid(staffs.get(j).getStaffid());
						riqipaiban.setWorkdate(dateFormat1.format(date));
						riqipaiban.setClassid(classid);
						riqipaiban.setGroupid(groupid);
						listInsert.add(riqipaiban);
					}
					break;
				}
				c.add(Calendar.DATE, 1); // 日期加1天
				date = c.getTime();
			}
			riqipaibanList.setListInsert(listInsert);
			int i= istaffClassesRecordSv.riqiPaiban(listInsert, groupid);
	    
			pw.write(""+i);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.write(""+ -1);
		}
	}
	
	
	/**list转json**/
	public <T> String listToJson(List<T> t) {
		JSONArray sjArray = JSONArray.fromObject(t);
		String str = sjArray.toString();
		return str;
	}
}
