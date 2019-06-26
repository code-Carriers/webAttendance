package com.cmhit.controller.scheduled;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmhit.po.Staffclassperiodsigninrecord;
import com.cmhit.service.gerenkaoqinxinxi.IpersonAttendanceSv;
import com.cmhit.service.gerenkaoqinxinxi.Impl.PersonAttendanceImpl;
import com.cmhit.vo.PunchCard;
import com.dahua.client.SdkClient;

import net.sf.json.JSONArray;

import com.dahua.DahuaImplement;

@Component
public class Scheduled {
	//@org.springframework.scheduling.annotation.Scheduled(cron = "0/1 * * * * ?")
	//@RequestMapping("/start")
	@Autowired
	private IpersonAttendanceSv ipersonAttendanceSv;
	
	 public void setIpersonAttendanceSv(IpersonAttendanceSv ipersonAttendanceSv) {
		this.ipersonAttendanceSv = ipersonAttendanceSv;
	}
	//在spring初始化之前，初始化一个静态类
    private static Scheduled scheduled;
  //通过@PostConstruct方法实现Bean初始化之前和销毁之前的自定义操作
    @PostConstruct
    public void init() {
    	scheduled=this;
    	scheduled.ipersonAttendanceSv=this.ipersonAttendanceSv; // 初使化时将已静态化的vTcTbdwdmService实例化
    }
	public void insertData(){
		IpersonAttendanceSv ipersonAttendanceSv=new PersonAttendanceImpl();
		System.out.println("没隔1秒刷新一次，调用第三方接口查询考勤数据");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		try {
			DahuaImplement dahua=new DahuaImplement();
			String token=dahua.login();
			String ip=dahua.getIp();
			String url="http://"+ip+"/CardSolution/attendance/result/page?token="+token;
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("pageNum", "1");
			param.put("pageSize", "500");
			param.put("dutyDateAfter",df1.format(new Date()));
			param.put("dutyDateBefore",df1.format(new Date()));
			//param.put("result", 1);
			//System.out.println(SdkClient.post(url, JSON.toJSONString(param)));
			String result=SdkClient.post(url, JSON.toJSONString(param));
			System.out.println(result);
			JSONObject Object=JSON.parseObject(result);
			JSONObject Object1=JSON.parseObject(Object.getString("data"));
			JSONArray pageData=JSONArray.fromObject(Object1.getString("pageData"));
			
			for(int i=0;i<pageData.size();i++){
				
				net.sf.json.JSONObject Object2=net.sf.json.JSONObject.fromObject(pageData.get(i));
				String checkInTime=Object2.getString("checkInTime");
				String checkOutTime=Object2.getString("checkOutTime");
				String dutyDate=Object2.getString("dutyDate");
				int personId=Object2.getInt("personId");
				System.out.println(dutyDate+" "+personId);
				
				List<PunchCard> punchCards=scheduled.ipersonAttendanceSv.selectPunchCard(dutyDate, personId);
				
				System.out.println(punchCards.size());
				if(punchCards.size()>0){
		    		PunchCard punchCard=punchCards.get(0);
		    		int signId=punchCard.getStaffClassPeriodSignInRecordId();
		    		String shangban=punchCard.getGoToWorkTime();
		    		String xiaban=punchCard.getTimeFromWork();
		    		System.out.println(signId);
		    		Staffclassperiodsigninrecord record=scheduled.ipersonAttendanceSv.selectByPrimaryKey(signId);
		    		if(!checkInTime.equals("")){
		    			record.setSignintime(checkInTime);
		    			Date shangban1 = df.parse(dutyDate+" "+shangban+":00");
		    			Date checkInTime1 = df.parse(checkInTime);	
		    			
		    			if(checkInTime1.after(shangban1)) 
		    				record.setLateno(true);
		    			else record.setLateno(false);
		    			System.out.println(record.getLateno());
		    		}
		    		if(!checkOutTime.equals("")){
		    			record.setSignbacktime(checkOutTime);
		    			Date xiaban1 = df.parse(dutyDate+" "+xiaban+":00");
		    			Date checkOutTime1 = df.parse(checkOutTime);	
	    			
		    			if(checkOutTime1.before(xiaban1)) 
		    				record.setLeaveearlyno(true);
		    			else record.setLeaveearlyno(false);
		    		}
		    		scheduled.ipersonAttendanceSv.updateClassperiodsigninrecord(record);
		    	
		    		
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
