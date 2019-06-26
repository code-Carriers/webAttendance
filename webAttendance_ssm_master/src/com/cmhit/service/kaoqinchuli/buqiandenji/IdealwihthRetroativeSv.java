package com.cmhit.service.kaoqinchuli.buqiandenji;

import java.util.List;

import com.cmhit.po.StaffRetroactive;
import com.cmhit.po.Staffclassperiodsigninrecord;
import com.cmhit.vo.DealwihthRetroative;
import com.cmhit.vo.Unusual;

public interface IdealwihthRetroativeSv {
    /**查询异常信息进行处理**/   
    List<Unusual>  selectUnusual();
    /**处理异常考勤时修改考勤类别**/
    int updateAttendanceType(int staffClassPeriodSignInRecordId ,int attendanceClassChildId );
    /**查询所有补签申请**/
    List<DealwihthRetroative> selectDealwihthRetroative(int checkStateId);
    /**修改签到表**/
    int updateStaffclassperiodsigninrecord(Staffclassperiodsigninrecord t);
    /**修改补签登记表**/
    int updateStaffretroactive(StaffRetroactive t);
}
