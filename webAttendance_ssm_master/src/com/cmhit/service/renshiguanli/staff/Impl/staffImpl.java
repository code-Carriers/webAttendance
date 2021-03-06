package com.cmhit.service.renshiguanli.staff.Impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmhit.controller.renshiguanli.staff;
import com.cmhit.dao.AttributeGatherChildMapper;
import com.cmhit.dao.StaffMapper;
import com.cmhit.dao.SystemMapper;
import com.cmhit.po.AttributeGatherChild;
import com.cmhit.po.Department;
import com.cmhit.po.Staff;
import com.cmhit.po.System;
import com.cmhit.service.renshiguanli.staff.IstaffSv;
@Service("istaffSv")
public class staffImpl implements IstaffSv{
   @Autowired
   StaffMapper staffMapper;
   @Autowired
	AttributeGatherChildMapper attributeGatherChildDao;
   @Autowired
	SystemMapper systemDao;
	@Override
	public List<Staff> selectAll(int[] departmentids, int positionid, int typeid,
			String number, String name,int groupid) {
		 	return staffMapper.selectAll(departmentids, positionid, typeid, number, name,groupid);
	}

	@Override
	public List<Staff> selectAll2() {
		 	return staffMapper.selectAll2();
	}
	
	public Staff selectById(int id) {
		return staffMapper.selectByPrimaryKey(id);
	}

	 
	public int insert(Staff t) {
		return staffMapper.insertSelective(t);
	}
	
	public void insert2(String str21, String str22, String str23, String str24, String str25, String str26, String str27, String str28, int str29, int str30, int str31, String str32,String image) {
		staffMapper.insertSelective2(str21,str22,str23,str24,str25,str26,str27,str28,str29,str30,str31,str32,image);
	}

	 
	public int update(Staff t) {
		 
		return staffMapper.updateByPrimaryKeySelective(t);
	}

	 
	public int delete(int id) {
		 
		return staffMapper.deleteByPrimaryKey(id);
		 
	}
	
	public void delete2() {
		 
		staffMapper.deleteByPrimaryKey2();
		 
	}
	
	 /** 模糊只查询机构对应的员工**/
	@Override
	public List<Staff> selectStaff(int departmentId,String name,String number){
	 List<Staff> staffs=staffMapper.selectStaff(departmentId, name, number);
		return staffs;
	}
    /**修改员工班组id**/
	@Override
	public int updateStaffGroupId(int[] staffids, int groupid) {
		int i= staffMapper.updateStaffGroupId(staffids, groupid);
		return i;
	}

	 /**根据班组id查询员工信息**/
	public List<Staff> selectStaffByGroupId(int groupid) {
		List<Staff> staffs=	staffMapper.selectStaffByGroupId(groupid);
		return staffs;
	}

	 /**编辑班组时修改班组和删除大于今天的考勤安排 **/
	public int editByGroupidAndStaffids(int groupid, int[] staffids) {
	 int i=	staffMapper.editByGroupidAndStaffids(groupid, staffids);
		return i;
	}

	public int deleteRecordByStaffid(int staffid) {
		int i= staffMapper.deleteRecordByStaffid(staffid);
		return i;
	}

	 /**修改某个员工制度时改变班次安排大于今天的考勤制度 **/
	public int updateStaffSystem(int staffid, int systemid) {
	  int i=staffMapper.updateStaffSystem(staffid, systemid);
		return i;
	}

	@Override
	public Staff selectUserByUserName(String username, String password) {
		return staffMapper.selectUserByUserName(username, password);
	}

	@Override
	public Department selectDeptByStaffID(int staffid) {
		// TODO Auto-generated method stub
		return staffMapper.selectDeptByStaffID(staffid);
	}
	
	@Override
	public Staff selectUserByUsername(String username) {
		// TODO Auto-generated method stub
		return staffMapper.selectUserByUsername(username);
	}

	@Override
	public void update2(String str21,String str22,String str23,String str24,String str25,String str27,String str28, int str29, int str30, int str31, String str32) {
		// TODO Auto-generated method stub
		staffMapper.updateByPrimaryKeySelective2(str21,str22,str23,str24,str25,str27,str28,str29,str30,str31,str32);
	}

	@Override
	public List<Staff> selectid(int staffid) {
		// TODO Auto-generated method stub
		return staffMapper.selectid(staffid);
	}

	@Override
	public List<AttributeGatherChild> selectByAttributeGatherId(int attributeGatherId) {
		// TODO Auto-generated method stub
		return attributeGatherChildDao.selectByAttributeGatherId(attributeGatherId);
	}

	@Override
	public List<System> selectSystems() {
		// TODO Auto-generated method stub
		return systemDao.selectSystems();
	}
}
