package com.cmhit.service.renshiguanli.staff;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmhit.controller.renshiguanli.staff;
import com.cmhit.core.base.BaseService;
import com.cmhit.po.AttributeGatherChild;
import com.cmhit.po.Department;
import com.cmhit.po.Staff;
import com.cmhit.po.StaffPosition;
import com.cmhit.po.System;

public interface IstaffSv extends BaseService<Staff, String> {
	public List<Staff> selectAll(int[] departmentids, int positionid, int typeid, String number, String name,
			int groupid);

	/** 模糊只查询机构对应的员工 **/
	
	public List<Staff> selectAll2();
	
	public List<Staff> selectStaff(int departmentId, String name, String number);

	/** 修改员工班组id **/
	int updateStaffGroupId(int[] staffids, int groupid);

	/** 根据班组id查询员工信息 **/
	List<Staff> selectStaffByGroupId(int groupid);

	/** 编辑班组时修改班组和删除大于今天的考勤安排 **/
	public int editByGroupidAndStaffids(int groupid, int[] staffids);

	/** 班组id改变时删除大于今天的考勤记录 **/
	int deleteRecordByStaffid(int staffid);

	/** 修改某个员工制度时改变班次安排大于今天的考勤制度 **/
	int updateStaffSystem(int staffid, int systemid);

	/** 根据用户名密码查找用户 **/
	Staff selectUserByUserName(String username, String password);

	/** 黄宽录 根据员工号查看所在部门 **/
	Department selectDeptByStaffID(int staffid);

	/* 检查用户名是否存在 */
	Staff selectUserByUsername(String username);

	public void update2(String str21, String str22, String str23, String str24, String str25,  String str27, String str28, int str29, int str30, int str31, String str32);

	public void insert2(String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, int str29, int str30, int str31, String str32,String image);

	public void delete2();

	public List<Staff> selectid(int staffId);
	public List<AttributeGatherChild> selectByAttributeGatherId(int attributeGatherId);
	/**查询所有考勤制度**/
    public List<System> selectSystems();
}
