package com.cmhit.controller.renshiguanli;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cmhit.core.base.BaseController;
import com.cmhit.po.Department;
import com.cmhit.po.Staff;
import com.cmhit.po.StaffPosition;
import com.cmhit.service.renshiguanli.setdepartment.IdepartmentSv;
import com.cmhit.service.renshiguanli.setdepartment.IpositionSv;
import com.cmhit.service.renshiguanli.staff.IstaffSv;
import com.dahua.DahuaImplement;
import com.dahua.client.SdkClient;

@Controller
@RequestMapping("/setDeparment")
public class SetDepartment extends BaseController<Department, String> {

	@Autowired
	IdepartmentSv idepartment;

	@Autowired
	IpositionSv ipositionSv;
	@Autowired
	IstaffSv istaffSv;

	@RequestMapping("/main")
	public String selectAll() {
		Staff user=(Staff) request.getSession().getAttribute("user");
		if(!user.getRemark().equals("0"))
			return "/role";
		
		try {
		//List<Staff> staffs = istaffSv.selectAll2();
		String url = "http://192.168.78.119/CardSolution/card/department?userId=1&userName=system&token=a101af18dd4abb4c146d593b64c5a4e2";
		System.out.println(SdkClient.get(url));
		String str = SdkClient.get(url);
		JSONObject object1 = JSONObject.parseObject(str);
		com.alibaba.fastjson.JSONArray object3 = object1.getJSONArray("data");
		idepartment.delete2();
		for (int i1 = 0; i1 < object3.size(); i1++) {
			System.out.print(object3.getJSONObject(i1).get("name") + "  ");
			String str3 = (String) object3.getJSONObject(i1).get("name");
			int mm = (int) object3.getJSONObject(i1).get("id");
			String str5 = (String) object3.getJSONObject(i1).get("groupid");
			String str6 = (String) object3.getJSONObject(i1).get("description");
			int str7 = (int) object3.getJSONObject(i1).get("parentId");
			idepartment.insert2(str3, str5, str6, str7, mm);
//同步添加职务
			
			List<StaffPosition> staffposions=ipositionSv.selectBydepartmentId(mm);
			for(int i=0;i<staffposions.size();i++){
				StaffPosition sp=ipositionSv.selectBydepartmentId(mm).get(i);
				ipositionSv.delete(sp.getStaffpositionid());
			}
			
				StaffPosition t = new StaffPosition(mm,6,mm);
				/*t.setStaffpositionid(mm);
				t.setDepartmentid(mm);
				t.setPositionid(6);*/
				ipositionSv.insert(t);
		}
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		List<Department> departments = idepartment.selectAll();
		request.setAttribute("departments", departments);
		
		return "/renshiguanli/jigoushezhi/main";
	}

	@ResponseBody
	@RequestMapping("/update2")
	public String show(Department t) throws Exception {
		int nn = 0;
		boolean flag;
		List<Department> departments = idepartment.selectAll();
		List<Staff> staffs = istaffSv.selectAll2();
		String url = "http://192.168.78.119/CardSolution/card/department?userId=1&userName=system&token=a101af18dd4abb4c146d593b64c5a4e2";
		System.out.println(SdkClient.get(url));
		String str = SdkClient.get(url);
		JSONObject object1 = JSONObject.parseObject(str);
		com.alibaba.fastjson.JSONArray object3 = object1.getJSONArray("data");
		idepartment.delete2();
		for (int i1 = 0; i1 < object3.size(); i1++) {
			System.out.print(object3.getJSONObject(i1).get("name") + "  ");
			String str3 = (String) object3.getJSONObject(i1).get("name");
			int mm = (int) object3.getJSONObject(i1).get("id");
			String str5 = (String) object3.getJSONObject(i1).get("groupid");
			String str6 = (String) object3.getJSONObject(i1).get("description");
			int str7 = (int) object3.getJSONObject(i1).get("parentId");
			idepartment.insert2(str3, str5, str6, str7, mm);
		}
		/*// ___人员同步过更新
		String url2 = "http://192.168.78.119//CardSolution/card/person/bycondition/combined?userId=1&userName=system&token=bb2546a3c9f0a45767a963ecbe629d5c";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageNum", 1);
		param.put("pageSize", 10);
		System.out.println(SdkClient.post(url2, JSON.toJSONString(param)));
		String str2 = SdkClient.post(url2, JSON.toJSONString(param));
		JSONObject object21 = JSONObject.parseObject(str2);
		JSONObject object22 = object21.getJSONObject("data");
		com.alibaba.fastjson.JSONArray object23 = object22.getJSONArray("pageData");
		for (int i = 0; i < object23.size(); i++) {
			String str21 = (String) object23.getJSONObject(i).get("name");
			String str22 = (String) object23.getJSONObject(i).get("code");
			String str23 = (String) object23.getJSONObject(i).get("cardNumber");
			String str24 = (String) object23.getJSONObject(i).get("phone");
			String str25 = (String) object23.getJSONObject(i).get("personIdentityId");
			String str26 = (String) object23.getJSONObject(i).get("status");
			for (int j = 0; j < staffs.size(); j++) {
				nn = staffs.get(j).getStaffid();
				int nn1 = Integer.parseInt(str22);
				if (nn1 == nn) {
					istaffSv.update2(str21, str22, str23, str24, str25, str26);
					flag = false;
					return "success!";
				}  
			}
			flag = true;
			if (flag = true) {
				istaffSv.insert2(str21, str22, str23, str24, str25, str26);
			}
		}*/
		// ___人员同步过更新

			/*for (int j = 0; j < departments.size(); j++) {*/
				/*nn = departments.get(j).getDepartmentid();
				if (mm == nn) {
					idepartment.update2(str3, str5, str6, str7, mm);
					System.out.println("您的部门号是:" + mm + "结束");
					flag = false;
					return "success!";
				}
			}
			flag = true;
			if (flag = true) {*/
				
			/*}*/
		
		return "../../../main"; 
	}

	/*
	 * 修改部门
	 */@ResponseBody
	@RequestMapping("/update")
	public String update(Department t) {
		 /*author=黄宽录
			同步大华平台
			 */
			try {
				DahuaImplement dahua=new DahuaImplement();
				String token=dahua.login();
				int userId=dahua.getUserId();
				String userName=dahua.getUserName();
				String ip=dahua.getIp();
				//更新部门数据到平台
				String url="http://"+ip+"/CardSolution/card/department/update?userId="+userId+"&userName="+userName+"&token="+token;
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("id",t.getDepartmentid());
				param.put("name",t.getName());
				param.put("description",t.getRemark());		
				param.put("parentId",t.getFatherid());
				String result=SdkClient.post(url, JSON.toJSONString(param));
				System.out.println(result);
				JSONObject Object=JSON.parseObject(result);
				String success =Object.getString("success");
				if(success.equals("false"))
					return "0";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		return "" + idepartment.update(t);
	}

	/*
	 * 新增部门
	 */
	@ResponseBody
	@RequestMapping("/insert")
	public String insert(Department t) {
		/*author 黄宽录
		  删除部门同步平台*/
		System.out.println(t.getName()+"  "+t.getNumber());
		try {
			DahuaImplement dahua=new DahuaImplement();
			String token=dahua.login();
			int userId=dahua.getUserId();
			String userName=dahua.getUserName();
			String ip=dahua.getIp();
			//插入部门数据到平台
			String url="http://"+ip+"/CardSolution/card/department?userId="+userId+"&userName="+userName+"&token="+token;
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("name",t.getName());
			param.put("description",t.getRemark());		
			param.put("parentId",t.getFatherid());
			String result=SdkClient.post(url, JSON.toJSONString(param));
			System.out.println(result);
			JSONObject Object=JSON.parseObject(result);
			//更改部门id
			t.setDepartmentid(Object.getInteger("data"));
			String success =Object.getString("success");
			if(success.equals("false"))
				return "0";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return "" + idepartment.insert(t);
	}

	@Override
	public String selectById(int id) {

		return null;
	}

	@ResponseBody
	@RequestMapping("/delete")
	public String delete(int id) {
		/*author 黄宽录
		  删除部门同步平台*/
		try {
			DahuaImplement dahua=new DahuaImplement();
			String token=dahua.login();
			int userId=dahua.getUserId();
			String userName=dahua.getUserName();
			String ip=dahua.getIp();
			//插入部门数据到平台
			String url="http://"+ip+"/CardSolution/card/department/delete/"+id+"?userId="+userId+"&userName="+userName+"&token="+token;
			Map<String,Object> param = new HashMap<String,Object>();
			String result=SdkClient.post(url, JSON.toJSONString(param));
			System.out.println(result);
			JSONObject Object=JSON.parseObject(result);
			String success =Object.getString("success");
			if(success.equals("false"))
				return "0";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return ""+idepartment.delete(id);
	}

	/*
	 * 根据属性集合id查询属性明细（查询绑定职务下拉框）
	 */
	@ResponseBody
	@RequestMapping(value = "/selectByAttributeGatherId", produces = "text/html;charset=UTF-8")
	public String selectByAttributeGatherId(int attributeGatherId) {
		JSONArray Positions = JSONArray.fromObject(idepartment.selectByAttributeGatherId(attributeGatherId));
		String str = Positions.toString();
		return str;
	}

	/*
	 * 机构职务的操作
	 */
	@ResponseBody
	@RequestMapping(value = "/selectOpsition", produces = "text/html;charset=UTF-8")
	public String selectBydepartmentId(int departmentid) {
		JSONArray staffPositions = JSONArray.fromObject(ipositionSv.selectBydepartmentId(departmentid));
		String str = staffPositions.toString();
		return str;
	}

	/*
	 * 新增修改职务
	 */
	@ResponseBody
	@RequestMapping(value = "/add_editOpsition")
	public String add_editOpsition(StaffPosition t, String add_edit) {
		if (add_edit.equals("add")) {
			return "" + ipositionSv.insert(t);
		} else {
			return "" + ipositionSv.update(t);
		}

	}

	/*
	 * 删除职务
	 */
	@ResponseBody
	@RequestMapping(value = "/deleteOpsition")
	public String deleteOpsition(int opsitionid) {
		return "" + ipositionSv.delete(opsitionid);
	}
}
