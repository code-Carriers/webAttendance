package com.cmhit.controller.paibanguangli;

import java.io.PrintWriter;
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
import com.cmhit.core.base.BaseController;
import com.cmhit.dahua.DahuaImplement;
import com.cmhit.po.Attendanceclasschild;
import com.cmhit.po.Classperiod;
import com.cmhit.po.Positionclasses;
import com.cmhit.po.StaffPosition;
import com.cmhit.service.paibanguangli.bancishezhi.IclassperiodSv;
import com.cmhit.service.paibanguangli.bancishezhi.IsetClassesSv;
import com.cmhit.service.paibanguangli.bancishezhi.Impl.classperiodImpl;
import com.dahua.client.SdkClient;
 

@Controller
@RequestMapping("/SetClasses")
public  class SetClasses extends BaseController<Positionclasses, String >{
     @Autowired
     IclassperiodSv iclassperiodSv;
     @Autowired
     IsetClassesSv isetClassesSv;
  
	@RequestMapping(value="/selectPositionclasse")
    public void selectPositionclasses(PrintWriter pw){
		List<Positionclasses> positionclasses=isetClassesSv.selectPositionclasses();
		String str= listToJson(positionclasses);
		pw.write(str);
	}

/*根据班次id查询班段
 * 
 * */
	@ResponseBody
	@RequestMapping(value="/selectClassePeriod",produces = "text/html;charset=UTF-8" )
    public  String selectClassperiod(int Classid){
		try {
			DahuaImplement dahua = new DahuaImplement(); 
			String token = dahua.login();
			String ip = dahua.getIp();
			String url = "http://" + ip + "/CardSolution/attendance/result/page?userId=1&userName=system&token=" + token;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("pageNum", 1);
			param.put("pageSize", 2);
			String str = SdkClient.post(url, JSON.toJSONString(param));
			JSONObject object1 = JSONObject.parseObject(str);
			JSONObject object2 = object1.getJSONObject("data");
			com.alibaba.fastjson.JSONArray object23 = object2.getJSONArray("pageData");
			//List<StaffClassesRecord> record2 = ipersonAttendanceSv.selectAll2();
				String str1 = (String) object23.getJSONObject(1).get("onDutyTime");
				String str2 = (String) object23.getJSONObject(1).get("offDutyTime");
				 List<Classperiod> classperiods=iclassperiodSv.selectClassperiod(Classid,str1,str2);
				 return listToJson(classperiods);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 根据考勤类别ids查询考勤类别明细绑定到时间段类型下拉框中
	 * */
	@ResponseBody
	@RequestMapping(value="/selectAttendanceByIds",produces = "text/html;charset=UTF-8" )
	public String selectAttendanceByIds(int[] ids) {
	 	 String str= listToJson(iclassperiodSv.selectAttendanceByIds(ids));
		return str;
	}

   /* 修改班次
    * 
    * */
	@ResponseBody
	@RequestMapping(value="/updateClass",produces = "text/html;charset=UTF-8" )
	@Override
	public String update(Positionclasses t) {
		int i= isetClassesSv.update(t);
		return ""+i;
	}
	/*新增班次
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/insertPositionclasses",produces = "text/html;charset=UTF-8" )
	public String insert(Positionclasses t) {
		return ""+isetClassesSv.insert(t);
	}
	/*根据班次id查找要修改的班次
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/selectClassById",produces = "text/html;charset=UTF-8" )
	@Override
	public String selectById(int id) {
     String str= JSONArray.fromObject(isetClassesSv.selectById(id)).toString();
	 return str;
	}
	/*删除班次
	 * 
	 * */
	@ResponseBody
	@RequestMapping(value="/deleteClass",produces = "text/html;charset=UTF-8" )
	@Override
	public String delete(int id) {
		int i= isetClassesSv.delete(id);
		return ""+i;
	}
	@RequestMapping("selectClassUseNo")
	 /**查询班次使用数**/
   public void selectClassUseNo(int positionClassesId,PrintWriter pw) {
		int i=isetClassesSv.selectClassUseNo(positionClassesId);
		pw.write(""+i);
   }
	/*新增班段
	 * 
	 * */
	@RequestMapping(value="/insertClassPeriod",produces = "text/html;charset=UTF-8" )
    public void insertClassPeriod(Classperiod t ,PrintWriter pw){
    int i=iclassperiodSv.insert(t);
    pw.write(""+i);
    }
	/*删除班段
	 * 
	 * */
	@RequestMapping(value="/deleteClassPeriod",produces = "text/html;charset=UTF-8" )
	public void deleteClassPeriod(int id,PrintWriter pw){
		int i= iclassperiodSv.delete(id);
		pw.write(""+i);
	}
	@RequestMapping(value="/selectByClassPeriodId",produces = "text/html;charset=UTF-8" )
	/*查询要修改的班段
	 * 
	 * */
    public void selectByClassPeriodId(int id,PrintWriter pw){
     String strPeriod=JSONArray.fromObject(iclassperiodSv.selectById(id)).toString();
    pw.write(strPeriod);
	}
	@RequestMapping(value="/updateClassPeriod",produces = "text/html;charset=UTF-8" )
	/*保存班段信息
	 * 
	 * */
	public void updateClassPeriod(Classperiod t,PrintWriter pw){
		int i= iclassperiodSv.update(t);
		pw.write(""+i);
	}
	
	

/*list转json
 * 
 * */
    public <T> String listToJson(List<T> t){
    	JSONArray sjArray =JSONArray.fromObject(t);
		String str=sjArray.toString();
    	return str;
    }

	@Override
	public String selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

 
}
