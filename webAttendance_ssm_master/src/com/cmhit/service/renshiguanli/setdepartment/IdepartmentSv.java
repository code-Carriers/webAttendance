package com.cmhit.service.renshiguanli.setdepartment;

import java.util.List;

import com.cmhit.core.base.BaseService;
import com.cmhit.po.AttributeGatherChild;
import com.cmhit.po.Department;

public interface IdepartmentSv extends BaseService<Department, String> {
	public List<Department> selectAll();

	// 根据属性集合id查询明细信息
	public List<AttributeGatherChild> selectByAttributeGatherId(int attributeGatherId);

	public void update2(String str3, String str5, String str6, int str7, int mm);

	public void insert2(String str3, String str5, String str6, int str7, int mm);

	void delete2();
}
