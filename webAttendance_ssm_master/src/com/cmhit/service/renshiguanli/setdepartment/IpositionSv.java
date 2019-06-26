package com.cmhit.service.renshiguanli.setdepartment;

import java.util.List;

import com.cmhit.core.base.BaseService;
import com.cmhit.po.StaffPosition;

public interface IpositionSv extends BaseService<StaffPosition, String> {
public List<StaffPosition> selectBydepartmentId(int departmentid );
public List<StaffPosition> selectBydepartmentIds(int[] departmentids );
}
