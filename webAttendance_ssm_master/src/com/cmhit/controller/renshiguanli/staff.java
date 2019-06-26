package com.cmhit.controller.renshiguanli;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmhit.core.base.BaseController;
import com.cmhit.core.query.CommonMethod;
import com.cmhit.po.AttributeGatherChild;
import com.cmhit.po.Department;
import com.cmhit.po.Staff;
import com.cmhit.po.StaffPosition;
import com.cmhit.service.renshiguanli.setdepartment.IdepartmentSv;
import com.cmhit.service.renshiguanli.setdepartment.IpositionSv;
import com.cmhit.service.renshiguanli.staff.IstaffSv;
import com.dahua.DahuaImplement;
import com.dahua.client.SdkClient;

@Controller
@RequestMapping("/staff")
public class staff extends BaseController<Staff, String> {
	@Autowired
	IstaffSv istaffSv;
	@Autowired
	IdepartmentSv idepartment;
	@Autowired
	IpositionSv ipositionSv;

	/** 查询模糊查询所有员工
	 * */
	@ResponseBody
	@RequestMapping(value = "/selectAllStaff", produces = "text/html;charset=UTF-8")
	public String selectAllStaff(int[] departmentids, int positionid,
			int typeid, String number, String name,int groupid) {
		boolean flag;


	try {
		/****/
		DahuaImplement dahua=new DahuaImplement();
		String token=dahua.login();
		int userId=dahua.getUserId();
		String userName=dahua.getUserName();
		String ip=dahua.getIp();
		String url="http://"+ip+"/CardSolution/card/person/bycondition/combined?userId="+userId+"&userName="+userName+"&token="+token;
		//获取卡号
		String url2 = "http://"+ip+"//CardSolution/card/card/bycondition/combined?userId=1&userName=system&token="+token;
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageNum", 1);
		param.put("pageSize", 150);
		String result = SdkClient.post(url, JSON.toJSONString(param));
		JSONObject object = JSONObject.parseObject(result);
		JSONObject data = object.getJSONObject("data");
		com.alibaba.fastjson.JSONArray pageData = data.getJSONArray("pageData");
		
		
		for (int i = 0; i < pageData.size(); i++) {
			flag = true;
			int staffid = (int) pageData.getJSONObject(i).get("id");
			String staffName = (String) pageData.getJSONObject(i).get("name");
			String staffNumber = (String) pageData.getJSONObject(i).get("code");
			String deptId = (String) pageData.getJSONObject(i).get("deptId").toString();
			String phone = (String) pageData.getJSONObject(i).get("phone");
			String sex = (String) pageData.getJSONObject(i).get("sex");
			String cardNumber = (String) pageData.getJSONObject(i).get("cardNumber");
			//int cardNumber1 = (Integer) pageData.getJSONObject(i).get("cardNumber");
			if(sex.equals("男")) {
				sex = "1";
			}else sex = "0";
			List list=ipositionSv.selectBydepartmentId(Integer.parseInt(deptId));
			Integer staffPositionId=0;
			if(list.size()>0){
				StaffPosition t=(StaffPosition)list.get(0); 
				staffPositionId =t.getStaffpositionid();
				
			}
			//查询卡片
			/*param.put("personCode", staffNumber);
			String result2 = SdkClient.post(url2, JSON.toJSONString(param));
			System.out.println(result2);
			JSONObject object2 = JSONObject.parseObject(result2);
			JSONObject data2 = object.getJSONObject("data");
			com.alibaba.fastjson.JSONArray pageData2 = data2.getJSONArray("pageData");
			String cardNumber = null;
			for(int k = 0;k < pageData2.size();k++) 
				cardNumber= (String) pageData2.getJSONObject(k).get("cardNumber");
			*/
			System.out.println(pageData.getJSONObject(i));
			System.out.println("员工id"+staffid+" "+staffName+"卡号"+cardNumber);			
			
			Staff oldstaff=istaffSv.selectById(staffid);
			if(oldstaff!=null){
					oldstaff.setCardnumber(cardNumber);
					oldstaff.setStaffid(staffid);
					oldstaff.setSex(sex);
					oldstaff.setStaffname(staffName);
					oldstaff.setStaffnumber(staffNumber);
					oldstaff.setLoginnumber(phone);
					oldstaff.setStaffpositionid(staffPositionId);
					istaffSv.update(oldstaff);
					flag = false;
					break;
				
			}
			if (flag) {
				if(staffPositionId!=0){
					Staff newStaff=new Staff();
					newStaff.setCardnumber(cardNumber);
					newStaff.setStaffid(staffid);
					newStaff.setSex(sex);
					newStaff.setStaffname(staffName);
					newStaff.setStaffnumber(staffNumber);
					newStaff.setLoginnumber(phone);
					newStaff.setStaffpositionid(staffPositionId);
					newStaff.setHeadimage("headImg/"+staffNumber+".jpg");
					newStaff.setPositiontypeid(1);
					//班组，制度设置
					List attributeGatherChild =istaffSv.selectByAttributeGatherId(7);
					if(attributeGatherChild.size()>0){
						AttributeGatherChild group=(AttributeGatherChild)attributeGatherChild.get(0);
						newStaff.setGroupid(group.getAttributegatherchildid());
					}
					List systems=istaffSv.selectSystems();
					if(systems.size()>0){
						com.cmhit.po.System system=(com.cmhit.po.System)systems.get(0);
						newStaff.setSystemid(system.getSystemid());
					}
					istaffSv.insert(newStaff);
				}
			}
		}
		/*	
		String url2 = "http://"+ip+"/CardSolution/card/person/bycondition/combined?userId=1&userName=system&token="+token;
		String url3 = "http://192.168.78.119//CardSolution/card/card/bycondition/combined?userId=1&userName=system&token="+token;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("pageNum", 1);
		param.put("pageSize", 150);
		System.out.println(SdkClient.post(url2, JSON.toJSONString(param)));
		String str2 = SdkClient.post(url2, JSON.toJSONString(param));
		String str3 = SdkClient.post(url3, JSON.toJSONString(param));
		List<Staff> staffs = istaffSv.selectAll2();
		JSONObject object21 = JSONObject.parseObject(str2);
		JSONObject object11 = JSONObject.parseObject(str3);
		JSONObject object22 = object21.getJSONObject("data");
		JSONObject object12 = object11.getJSONObject("data");
		com.alibaba.fastjson.JSONArray object23 = object22.getJSONArray("pageData");
		com.alibaba.fastjson.JSONArray object13 = object12.getJSONArray("pageData");
		System.out.println("数据为：" + object13);
		for (int i = 0; i < object23.size(); i++) {
			flag = true;
			String str21 = (String) object23.getJSONObject(i).get("code");
			String str22 = (String) object23.getJSONObject(i).get("deptId").toString();
			String str23 = (String) object23.getJSONObject(i).get("deptName");
			String str24 = (String) object23.getJSONObject(i).get("id").toString();
			String str25 = (String) object23.getJSONObject(i).get("name");
			String str26 = (String) object23.getJSONObject(i).get("personIdentity");
			String str27 = (String) object23.getJSONObject(i).get("phone");
			String str28 = (String) object23.getJSONObject(i).get("sex");
			int str29 = 1;
			int str30 = 3;
			int str31 = 12;
			if(str28.equals("男")) {
				str28 = "1";
			}else str28 = "0";
			if(str22 == "7") {
				str22 = "11";
			}else if(str22 == "45") {
				str22 = "12";
			}else if(str22 == "9") {
				str22 = "13";
			}else if(str22 == "11") {
				str22 = "14";
			}else if(str22 == "6") {
				str22 = "15";
			}else if(str22 == "10") {
				str22 = "16";
				System.out.println("部门号为："+str22);
			}
			String str21 = (String) object23.getJSONObject(i).get("code");
			String str22 = (String) object23.getJSONObject(i).get("deptid");
			String str23 = (String) object23.getJSONObject(i).get("deptname");
			String str24 = (String) object23.getJSONObject(i).get("faceData");
			String str25 = (String) object23.getJSONObject(i).get("faceDataStatus");
			String str26 = (String) object23.getJSONObject(i).get("fingerCode");
			String str27 = (String) object23.getJSONObject(i).get("hongwaiFaceData");
			String str28 = (String) object23.getJSONObject(i).get("hongwaiFaceDataStatus");
			String str29 = (String) object23.getJSONObject(i).get("id");
			String str30 = (String) object23.getJSONObject(i).get("name");
			String str31 = (String) object23.getJSONObject(i).get("personIdentity");
			String str32 = (String) object23.getJSONObject(i).get("phone");
			String str33 = (String) object23.getJSONObject(i).get("sex ");
			List list=ipositionSv.selectBydepartmentId(Integer.parseInt(str22));
			if(list.size()>0){
				StaffPosition t=(StaffPosition)list.get(0); 
				Integer staffPositionId =t.getStaffpositionid();
				str22=String.valueOf(staffPositionId);
			}
			for(int k = 0;k < object13.size();k++) {
			String str32 = (String) object13.getJSONObject(k).get("cardNumber");
			String str33 = (String) object13.getJSONObject(k).get("personId").toString();
			int nn1 = Integer.parseInt(str24);
			int nn2 = Integer.parseInt(str33);
			if(nn2 == nn1) {
			for (int j = 0; j < staffs.size(); j++) {
				nn = staffs.get(j).getStaffid();	
				if (nn1 == nn) {
					istaffSv.update2(str21, str22, str23, str24, str25, str27, str28, str29, str30, str31, str32);
					flag = false;
				}  
			}
			if (flag == true) {
				str26="2";//remark作为权限字段，默认为2
				String image="headImg/"+str21+".jpg";//头像用工号命名
				istaffSv.insert2(str21, str22, str23, str24, str25, str26, str27, str28, str29, str30, str31, str32,image);
			}
		}
		}
		}*/
		}
		 catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	List<Staff> lidytList = istaffSv.selectAll(departmentids, positionid,
			typeid, number, name,groupid);
	
		   	return listToJson(lidytList);
	}	

	@ResponseBody
	@RequestMapping(value = "/selectAllStaff2", produces = "text/html;charset=UTF-8")
	public String selectAllStaff2() {
		String staffid = request.getParameter("staffid");
		List<Staff> lidytList = istaffSv.selectid(Integer.parseInt(staffid));
		   		return listToJson(lidytList);
	}	
	
	public String show(Staff sf,HttpServletResponse res,HttpServletRequest req) throws Exception {
		String url="http://192.168.78.119/CardSolution/card/department?userId=1&userName=system&token=a101af18dd4abb4c146d593b64c5a4e2";
		System.out.println(SdkClient.get(url));
		String str = SdkClient.get(url);
		JSONObject jsonObject = JSONObject.parseObject(str);
		String r = jsonObject.getString("deptId");
		return null;
	}
	

/**新增员工
 * */
	@ResponseBody
	@RequestMapping(value = "/insertStaff", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String insertStaff(Staff sf, MultipartFile file_img,HttpServletRequest req)
			throws Exception {
		
		Staff user=istaffSv.selectUserByUsername(sf.getUsername());
		if(user!=null){
			System.out.println(sf.getUsername()+" has exit");
			request.getSession().setAttribute("msg", "账号已存在");
			
			return "0";
		}else{
			String path = req.getSession().getServletContext().getRealPath("/pic/");
			if (file_img.getSize() > 0) {
				sf.setHeadimage(CommonMethod.saveFile(file_img, "headImg/",path));
			}
			sf.setPositiontypeid(1);// 设置人员为在职人员
			/*author黄宽录
			 *同步员工到大华平台*/
			try {
				DahuaImplement dahua=new DahuaImplement();
				String token=dahua.login();
				int userId=dahua.getUserId();
				String userName=dahua.getUserName();
				String ip=dahua.getIp();
				//插入员工数据到平台
				String url="http://"+ip+"/CardSolution/card/person?token="+token;
				Map<String,Object> param = new HashMap<String,Object>();
				param.put("birthday", "");
				param.put("code", sf.getStaffnumber());
				param.put("fingerCode", "");
				param.put("secfingerCode", "");
				param.put("name", sf.getStaffname());
				param.put("paperType", "身份证");
				param.put("paperNumber", sf.getIdcar());
				param.put("phone", sf.getLoginnumber());
				if(sf.getSex().equals("1"))
					param.put("sex", "男");
				else 	param.put("sex", "女");
				//职位id，-99为默认身份
				param.put("personIdentityId", "-99");
				param.put("status", "在职");
				StaffPosition p=ipositionSv.selectById(sf.getStaffpositionid());
				System.out.println(p.getDepartmentid());
				param.put("deptId",p.getDepartmentid() );//deptId为部门id
				
				param.put("loginPassword", "");
				String result=SdkClient.post(url, JSON.toJSONString(param));
				System.out.println(result);
				JSONObject Object=JSON.parseObject(result);
				String success =Object.getString("success");
				if(success.equals("false")) {
					request.getSession().setAttribute("msg", Object.getString("errMsg"));				
					return "0";
				}
				//更改员工id
				sf.setStaffid(Object.getInteger("data"));
				//照片同步处理（待实现）
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return "" + insert(sf);
		}
	}


 /** 获取部门 绑定ztree
  * */
	@ResponseBody
	@RequestMapping(value = "/selectDepartment", produces = "text/html;charset=UTF-8")
	public String selectDepartment() {
		String str = listToJson(idepartment.selectAll());
		return str;
	}


    /**根据部门id查询部门职务
     * */
	@ResponseBody
	@RequestMapping(value = "/selectOpsition", produces = "text/html;charset=UTF-8")
	public String selectOpsition(int[] departmentids) {
		return listToJson(ipositionSv.selectBydepartmentIds(departmentids));
	}

	 /**
	  * list转json
	  * */
	public <T> String listToJson(List<T> t) {
		JSONArray sjArray = JSONArray.fromObject(t);
		String str = sjArray.toString();
		return str;
	}
	
       /**修改员工信息
        * */
	@ResponseBody
	@RequestMapping(value = "/updateStaff", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String updateStaff(Staff sf,MultipartFile file_img,HttpServletRequest req)
			throws Exception {
		String path = req.getSession().getServletContext().getRealPath("/pic/");
		if (file_img.getSize() > 0) {
			CommonMethod.deleteFile(path
					+ sf.getHeadimage());
			sf.setHeadimage(CommonMethod.saveFile(file_img, "headImg/",path));
		}
		
		/*author=黄宽录
		同步大华平台
		 */
		
		try {
			DahuaImplement dahua=new DahuaImplement();
			String token=dahua.login();
			int userId=dahua.getUserId();
			String userName=dahua.getUserName();
			String ip=dahua.getIp();
			//更新数据到平台
			String url="http://"+ip+"/CardSolution/card/person/update?token="+token;
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("id", sf.getStaffid());
			param.put("birthday", "");
			param.put("code", sf.getStaffnumber());
			param.put("fingerCode", "");
			param.put("secfingerCode", "");
			param.put("name", sf.getStaffname());
			param.put("paperType", "身份证");
			param.put("paperNumber", sf.getIdcar());
			param.put("phone", sf.getLoginnumber());
			if(sf.getSex().equals("1"))
			param.put("sex", "男");
			else 	param.put("sex", "女");
			//职位id，-99为默认身份
			param.put("personIdentityId", "-99");
			param.put("status", sf.getPositiontype().getName());
			param.put("deptId", sf.getGroupid());
			param.put("loginPassword", "");
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
		
		String i = update(sf);
		return i;
	}

	public String update(Staff t) {
		return "" + istaffSv.update(t);
	}

	public String insert(Staff t) {
		return "" + istaffSv.insert(t);
	}

	/** 根据staffid查询要修改的数据
	 * */
	@ResponseBody
	@RequestMapping(value = "/selectByStaffid", produces = "text/html;charset=UTF-8")
	public String selectById(int staffid) {
		JSONArray jsonArray = JSONArray
				.fromObject(istaffSv.selectById(staffid));
		return jsonArray.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/deleteStaff", produces = "text/html;charset=UTF-8")
	public String delete(int id) {
		/*author 黄宽录
		  删除部门同步平台*/
		try {
			DahuaImplement dahua=new DahuaImplement();
			String token=dahua.login();
			int userId=dahua.getUserId();
			String userName=dahua.getUserName();
			String ip=dahua.getIp();
			//删除部门数据
			String url="http://"+ip+"/CardSolution/card/person/delete/token="+token;
			Map<String,Object> param = new HashMap<String,Object>();
			int[] ids={id}; 
			param.put("personIds", ids);
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
		return "" + istaffSv.delete(id);
	}
	 @RequestMapping("deleteRecordByStaffid")
	 /**班组id改变时删除大于今天的考勤记录**/
	public void  deleteRecordByStaffid(int staffid){
		 istaffSv.deleteRecordByStaffid(staffid);
	};
	 @RequestMapping("updateStaffSystem")
	 /**修改某个员工制度时改变班次安排大于今天的考勤制度 **/
	public void updateStaffSystem(int staffid, int systemid){
		 istaffSv.updateStaffSystem(staffid, systemid);
	}
	
	
	public String selectAll() {
		 
		return null;
	}

}
