package com.cmhit.service.paibanguangli.bancishezhi;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmhit.core.base.BaseService;
import com.cmhit.po.Attendanceclasschild;
import com.cmhit.po.Classperiod;

public interface IclassperiodSv extends BaseService<Classperiod, String>{
 //根据班次id查询班段
  public  List<Classperiod> selectClassperiod(int Classid,String str1,String str2);
  //根据考勤类别ids查询考勤类别明细
  public  List<Attendanceclasschild> selectAttendanceByIds(int[] ids);
  
}
