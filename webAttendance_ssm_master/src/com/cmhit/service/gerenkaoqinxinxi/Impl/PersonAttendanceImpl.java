package com.cmhit.service.gerenkaoqinxinxi.Impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmhit.dao.AskForLeaveMapper;
import com.cmhit.dao.KaoQinTongJiDao;
import com.cmhit.dao.PersonAttendanceDao;
import com.cmhit.dao.StaffClassesRecordMapper;
import com.cmhit.dao.StaffRetroactiveMapper;
import com.cmhit.dao.StaffclassperiodsigninrecordMapper;
import com.cmhit.po.AskForLeave;
import com.cmhit.po.Positionclasses;
import com.cmhit.po.StaffClassesRecord;
import com.cmhit.po.StaffRetroactive;
import com.cmhit.po.Staffclassperiodsigninrecord;
import com.cmhit.service.gerenkaoqinxinxi.IpersonAttendanceSv;
import com.cmhit.vo.ChuChaQingJiaTongJi;
import com.cmhit.vo.ChuQinJiaBanGongXiuTongJiVo;
import com.cmhit.vo.MyAskForLeave;
import com.cmhit.vo.MyClass;
import com.cmhit.vo.PunchCard;
@Transactional
@Service("ipersonAttendanceSv")
public class PersonAttendanceImpl  implements IpersonAttendanceSv{
@Autowired
PersonAttendanceDao personAttendanceDao;
@Autowired
StaffclassperiodsigninrecordMapper staffclassperiodsigninrecordDao;
@Autowired
StaffClassesRecordMapper staffclassesrocordDao;
@Autowired
AskForLeaveMapper askForLeaveDao;
@Autowired
KaoQinTongJiDao kaoQinTongJiDao;
@Autowired
StaffRetroactiveMapper staffRetroactiveDao;

/**
  * 查询我的班次信息
  */
	public List<MyClass> selectPersonClass(String beginDate, String endDate,
			int staffid) {
		List<MyClass> myClasses= personAttendanceDao.selectPersonClass(beginDate, endDate, staffid);
		return myClasses;
	}
   @Override
    public List<PunchCard> selectPunchCard(String workDate, int staffId) {
	return personAttendanceDao.selectPunchCard(workDate, staffId);
}
@Override
public int updateClassperiodsigninrecord(Staffclassperiodsigninrecord staffclassperiodsigninrecord){
	return personAttendanceDao.updateClassperiodsigninrecord(staffclassperiodsigninrecord);
}

@Override
public void updateClassperiodsigninrecord2(String str21,String str22,String str23){
	 personAttendanceDao.updateClassperiodsigninrecord2(str21,str22,str23);
}

	/**新增不定时班次签到记录**/
   public int insertClassperiodsigninrecord(Staffclassperiodsigninrecord staffclassperiodsigninrecord) {
	return staffclassperiodsigninrecordDao.insertSelective(staffclassperiodsigninrecord);
   }
   
   public void updateSelective2(int str21,String str22,String str23,int str24) {
		staffclassperiodsigninrecordDao.updateSelective2(str21,str22,str23,str24);
	   }
   
   
    /**新增请假、出差**/
	public int insertAskForLeave(AskForLeave askforleave) {
	return askForLeaveDao.insertSelective(askforleave);
	}
	/**新增申请加班的班次**/
	public int insertJiaBan(AskForLeave askforleave, int classid) {
		askForLeaveDao.insertSelective(askforleave);
		int askForLeaveId=askforleave.getAskforleaveid();
	    int i=	askForLeaveDao.insertAskForLeaveMx(classid, askForLeaveId);
		return i;
	}
	public List<MyAskForLeave> selectAskForLeave(int staffid, String startDate,
			String endDate, int checkTypeId, int registrationTypeId) {
		List<MyAskForLeave> myAskForLeaves=   askForLeaveDao.selectAskForLeave(staffid, startDate, endDate, checkTypeId, registrationTypeId);
		   return myAskForLeaves;
	}
	 
	public List<Positionclasses> selectClassByAttendanceClassChildId(
			int staffId, int attendanceClassChildId) {
		return askForLeaveDao.selectClassByAttendanceClassChildId(staffId, attendanceClassChildId);
		 
	}
	/**修改申请加班班次**/
	public int updateByAskForLeaveMxId(AskForLeave askforleave,int positionClassesId,
			int askForLeaveMxId) {
		updateAskForLeave(askforleave);
	    int i=askForLeaveDao.updateByAskForLeaveMxId(positionClassesId, askForLeaveMxId);
		return i;
	}
	 /**修改请假、加班出差信息**/
	public int updateAskForLeave(AskForLeave askforleave) {
		int i= askForLeaveDao.updateByPrimaryKeySelective(askforleave);
		return i;
	}
	/**删除申请加班班次**/
	public int deleteByAskForLeaveMxId(int askForLeaveId,int askForLeaveMxId) {
	 int i=askForLeaveDao.deleteByPrimaryKey(askForLeaveId);	
	       if(askForLeaveMxId!=0){
	    	   askForLeaveDao.deleteByAskForLeaveMxId(askForLeaveMxId);
	       }
	 return i;
	}
	  /**查询我的考勤记录之出差请假**/
	public List<ChuChaQingJiaTongJi> selectChuChaQingJia(String starDate,
			String endDate, int[] staffids) {
		List<ChuChaQingJiaTongJi> list= kaoQinTongJiDao.selectChuChaQingJia(starDate, endDate, staffids);
		return list;
	}
	 /**查询我的考勤记录之出勤、加班、公休**/
	public List<ChuQinJiaBanGongXiuTongJiVo> selectChuQingJiaBanGongXiuTongJi(
			String starDate, String endDate, int[] staffids) {
		List<ChuQinJiaBanGongXiuTongJiVo> list=kaoQinTongJiDao.selectChuQingJiaBanGongXiuTongJi(starDate, endDate, staffids);
		return list;
	}
	/**新增补签登记记录**/
	public int insertStaffretroactive(StaffRetroactive staffretroactive) {
		 int i=staffRetroactiveDao.insertSelective(staffretroactive);
		return i;
	}
	/**查询班段申请补签记录**/
	public List<StaffRetroactive> selectRecord(int staffClassesChildRecordId,
			boolean toAndFrom) {
		return   staffRetroactiveDao.selectRecord(staffClassesChildRecordId, toAndFrom);
	}
	 /**多条件查询我的所有补签申请 **/ 
	public List<StaffRetroactive> selectMyRetroactive(int staffid,String starDate,
			String endDate, int checkTypeId) {
		return staffRetroactiveDao.selectMyRetroactive(staffid,starDate, endDate, checkTypeId);
	}
	@Override
	public List<Staffclassperiodsigninrecord> selectAll() {
		// TODO Auto-generated method stub
		return staffclassperiodsigninrecordDao.selectAll();
}
	@Override
	public List<StaffClassesRecord> selectAll2(){
		// TODO Auto-generated method stub
		return staffclassesrocordDao.selectAll2();
}
	@Override
	public Staffclassperiodsigninrecord selectByPrimaryKey(Integer staffclassperiodsigninrecordid) {
		// TODO Auto-generated method stub
		return staffclassperiodsigninrecordDao.selectByPrimaryKey(staffclassperiodsigninrecordid);
	}
	
}
