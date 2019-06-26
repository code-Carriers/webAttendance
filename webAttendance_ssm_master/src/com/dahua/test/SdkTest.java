package com.dahua.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import net.sf.json.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cmhit.po.Staff;
import com.cmhit.po.StaffPosition;
import com.cmhit.service.gerenkaoqinxinxi.IpersonAttendanceSv;
import com.cmhit.service.gerenkaoqinxinxi.Impl.PersonAttendanceImpl;
import com.cmhit.vo.PunchCard;
import com.dahua.client.SdkClient;

/**
 * 
 * <p>
 * Title:SdkTest
 * </p>
 * <p>
 * Description:测试类
 * </p>
 * <p>
 * Company:浙江大华技术股份有限公司
 * </p>
 * 
 * @author 32174
 * @date 2018年12月18日
 */
public class SdkTest {

	@Test
	public void login() throws Exception {
		String ip = "192.168.78.119";
		int port = 80;
		String userName = "system";
		String password = "admin123";
		System.out.println(SdkClient.login(ip, port, userName, password));
	}

	/*
	 * @Test public void get() throws Exception { String url=
	 * "http://192.168.78.119/face/groupInfo/page?token=5f8f2479e8aa54aa951247d349d121f0";
	 * System.out.println(SdkClient.get(url)); }
	 */

	@Test
	public void get() throws Exception {
		
		
		/*
		 * String url=
		 * "http://192.168.78.119/face/faceRecognition/page?token=5f8f2479e8aa54aa951247d349d121f0";
		 */
		/*
		 * String url=
		 * "http://192.168.78.119/face/faceQuery/page?token=5f8f2479e8aa54aa951247d349d121f0";
		 */
		/*
		 * String url=
		 * "http://192.168.78.119/face/personInfo/page?token=5f8f2479e8aa54aa951247d349d121f0";
		 */
		/*
		 * String url=
		 * "http://192.168.78.119/face/groupInfo/reloadIVSS/{id}/{deviceCode}?token=5f8f2479e8aa54aa951247d349d121f0";
		 */
		/*
		 * String url=
		 * "http://192.168.78.119/CardSolution/card/department?userId=1&userName=system&token=a101af18dd4abb4c146d593b64c5a4e2";
		 * System.out.println(SdkClient.get(url)); Object jsObject =
		 * JSON.parse(SdkClient.get(url)); System.out.println(jsObject.toString());
		 */

		String url = "http://192.168.78.119/CardSolution/card/visitor/getReviewInfoByVisitorId/{20}?userId=1&userName=system&token=a535e25d5b2437774a2003c1e95a1f65?pageNum=1&pageSize=10&owner=ss";
		System.out.println(SdkClient.get(url));
		
		 /*String url="http://192.168.78.119/CardSolution/card/person/personidentity?userId=1&userName=system&token=3eef4fe11ec8020242a92f28c1ce95b0";
		 System.out.println("第一次输出"+SdkClient.get(url));
		String str = SdkClient.get(url);
		JSONObject object1 = JSONObject.parseObject(str);
		// System.ou.println(object1.get("Status")) 获取Status的值
		JSONArray object2 = object1.getJSONArray("data"); // result的值是一个数组，所以需要JSONArray
		for (int i = 0; i < object2.size(); i++) {
			System.out.print(object2.getJSONObject(i).get("name") + "  ");
		}*/
		
		/*
		 * String str = SdkClient.get(url); JSONObject jsonObject =
		 * JSONObject.parseObject(str); String r = jsonObject.getString("data");
		 * System.out.println(r); String str2 = JSONArray.toJSONString(r);
		 * System.out.println(str2);
		 */

	}

	/*
	 * @Test public void post() throws Exception { String url=
	 * "http://192.168.78.119//CardSolution/card/department?userId=1&userName=system&token=a101af18dd4abb4c146d593b64c5a4e2";
	 * Map<String,Object> param = new HashMap<String,Object>(); param.put("name",
	 * "测试部门"); param.put("description", "描述测试"); param.put("parentId",1);
	 * System.out.println(SdkClient.post(url, JSON.toJSONString(param))); }
	 */

	@Test
	public void post() throws Exception {
	/*	String url = "http://192.168.78.119//CardSolution/card/department/delete?userId=1&userName=system&token=3eef4fe11ec8020242a92f28c1ce95b0";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("id","36");
		param.put("pageSize", 10);
		
		 * param.put("birthday", "2018-11-03"); param.put("code", "666");
		 * param.put("fingerCode",""); param.put("loginPassword", "2018-11-03");
		 * param.put("name", "AAAA"); param.put("paperNumber","333333");
		 * param.put("paperType", "学生证"); param.put("personIdentityId", "-99");
		 * param.put("phone","15546897845"); param.put("secfingerCode", "");
		 * param.put("sex", "男"); param.put("status","在职"); param.put("deptId",1);
		 
		System.out.println(SdkClient.post(url, JSON.toJSONString(param)));
		String url = "http://192.168.78.119//CardSolution/card/card/bycondition/combined?userId=1&userName=system&token=852dbb964efeec16478d8c2f8dab478a";
		
		String url2 = "http://192.168.78.119//CardSolution/card/person/bycondition/combined?userId=1&userName=system&token=852dbb964efeec16478d8c2f8dab478a";
		
		String url2 = "http://192.168.78.119//CardSolution/attendance/result/page?token=84bac332825ee3e373f4204860fac388";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageNum", 1);
		param.put("pageSize",150);
		
	
		param.put("dutyDateAfter","2019-05-30");
		param.put("dutyDateBefore","2019-05-30");
		
		System.out.println(SdkClient.post(url2, JSON.toJSONString(param)));
		//String str2 = SdkClient.post(url2, JSON.toJSONString(param));
		JSONObject object21 = JSONObject.parseObject(str2);
		JSONObject object22 = object21.getJSONObject("data");
		JSONArray object23 = object22.getJSONArray("pageData"); 
		
		String str = SdkClient.post(url, JSON.toJSONString(param));
		JSONObject object1 = JSONObject.parseObject(str);
		JSONObject object2 = object1.getJSONObject("data");
		JSONArray object3 = object2.getJSONArray("pageData"); 
		
		String result = SdkClient.post(url, JSON.toJSONString(param));		
		JSONObject Object=JSON.parseObject(result);
		JSONObject Object1=JSON.parseObject(Object.getString("data"));
		JSONArray pageData=JSONArray.fromObject(Object1.getString("pageData"));
		
		for (int i = 0; i < pageData.size(); i++) {
		System.out.println(object3.getJSONObject(i).get("id")+"人员id："+object3.getJSONObject(i).get("personId") +"卡片号码："+object3.getJSONObject(i).get("cardNumber")
				+"员工电话="+object3.getJSONObject(i).get("phone")+"输出员工名："+object3.getJSONObject(i).get("deptName")
				+" "+object3.getJSONObject(i).get("personCode"));
		
			//net.sf.json.JSONObject Object2=net.sf.json.JSONObject.fromObject(pageData.get(i));
			JSONObject Object2=JSON.parseObject(pageData.get(i));
			
			System.out.println("数据为：" +Object2);	
			System.out.println("  deptId="+Object2.get("deptId") +" deptName="+Object2.getString("deptName")
				+" id="+Object2.get("id")+"  name="+Object2.getString("name")
				+" phone="+Object2.getString("phone")+" sex="+Object2.getString("sex"));
		}
		String str22 = (String) object3.getJSONObject(i).get("checkInTime").toString();
		System.out.println("数据为：" +str22);
		}
		
		/*IpersonAttendanceSv ipersonAttendanceSv=new PersonAttendanceImpl();
		List<PunchCard> punchCards=ipersonAttendanceSv.selectPunchCard("2017-04-16", 26);
		System.out.println(punchCards.size());
		}*/
		String url = "http://192.168.78.119//CardSolution/card/card/bycondition/combined?userId=1&userName=system&token=485631fed84977cb2496782040a3a38e";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageNum", 1);
		param.put("pageSize", 150);
		param.put("personCode", "011159");
		String result = SdkClient.post(url, JSON.toJSONString(param));
		System.out.println(result);
		JSONObject object = JSONObject.parseObject(result);
		JSONObject data = object.getJSONObject("data");
		com.alibaba.fastjson.JSONArray pageData = data.getJSONArray("pageData");
		
		for (int i = 0; i < pageData.size(); i++) {
			System.out.println("数据为：" +pageData.get(i));
			
			int staffid = (int) pageData.getJSONObject(i).get("personId");
			String staffName = (String) pageData.getJSONObject(i).get("personName");
			String staffNumber = (String) pageData.getJSONObject(i).get("personCode");
			String cardNumber= (String) pageData.getJSONObject(i).get("cardNumber");
			String deptId = (String) pageData.getJSONObject(i).get("deptId").toString();
			/*String phone = (String) pageData.getJSONObject(i).get("phone");
			String sex = (String) pageData.getJSONObject(i).get("sex");
			if(sex.equals("男")) {
				sex = "1";
			}else sex = "0";*/
			System.out.println("员工id"+staffid+"员工姓名 "+staffName+"  staffNumber="+staffNumber+"卡号"+cardNumber+
					" deptId"+deptId);
		}
}

	@Test
	public void put() throws Exception {
		Calendar c = Calendar.getInstance();
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
		Date beginDate = dateFormat1.parse("2019-05-01");
		Date endDate = dateFormat1.parse("2019-06-25");
		Date date = beginDate;
		while (!date.equals(endDate)) {
		System.out.print(dateFormat1.format(date));
		c.setTime(date);
		int week_index = c.get(Calendar.DAY_OF_WEEK) - 1;
        System.out.println("是：" + week_index);
		c.add(Calendar.DATE, 1); // 日期加1天
		date = c.getTime();
		}
	
	}

	@Test
	public void delete() throws Exception {

	}

}
