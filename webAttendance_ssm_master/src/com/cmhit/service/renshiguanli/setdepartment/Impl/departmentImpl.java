package com.cmhit.service.renshiguanli.setdepartment.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmhit.dao.AttributeGatherChildMapper;
import com.cmhit.dao.DepartmentMapper;
import com.cmhit.po.AttributeGatherChild;
import com.cmhit.po.Department;
import com.cmhit.service.renshiguanli.setdepartment.IdepartmentSv;
@Service("idepartmentSv")
public class departmentImpl implements IdepartmentSv{
	@Autowired
   	DepartmentMapper  departmentDo;
	@Autowired
    AttributeGatherChildMapper attributeGatherChildDo;
	public List<Department> selectAll() {
	     List<Department> departments=departmentDo.selectAll();
		return departments;
	}

	public Department selectById(int id) {
	    Department dp=departmentDo.selectByPrimaryKey(id);
		return null;
	}
	public int insert(Department t) {
		int i=departmentDo.insert(t);
		return i;
	}

 
	public int update(Department t) {
		   int i=departmentDo.updateByPrimaryKey(t);
				return i;
	}

 
	public int delete(int id) {
		int i= departmentDo.deleteByPrimaryKey(id);
		return i;
	}
	
	@Override
	public void delete2() {
		departmentDo.deleteByPrimaryKey2();
	}
	

	@Override
	public List<AttributeGatherChild> selectByAttributeGatherId(
			int attributeGatherId) {
		return attributeGatherChildDo.selectByAttributeGatherId(attributeGatherId);
	}

	@Override
	public void update2(String str3, String str5, String str6, int str7, int mm) {
		departmentDo.updateByPrimaryKey2(str3,str5,str6,str7,mm);
	}

	@Override
	public void insert2(String str3, String str5, String str6, int str7, int mm) {
		departmentDo.insert2(str3,str5,str6,str7,mm);
		
	}
}
