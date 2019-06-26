package com.cmhit.service.kaoqinguangli.kaoqingleibieshezhi;

import java.io.PrintWriter;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmhit.core.base.BaseService;
import com.cmhit.po.Attendanceclasschild;

public interface IsetAttendanceTypeSv extends BaseService<Attendanceclasschild, String >{
	 List<Attendanceclasschild> selectAttendanceById(int id);
}
