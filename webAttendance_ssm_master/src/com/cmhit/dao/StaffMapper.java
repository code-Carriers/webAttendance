package com.cmhit.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.cmhit.po.Department;
import com.cmhit.po.Staff;
public interface StaffMapper {

	int deleteByPrimaryKey(Integer staffid);

	int insert(Staff record);

	int insertSelective(Staff record);

	Staff selectByPrimaryKey(Integer staffid);

	int updateByPrimaryKeySelective(Staff record);
	
	void updateByPrimaryKeySelective2(@Param("str21")String str21,@Param("str22")String str22,@Param("str23")String str23,@Param("str24")String str24,@Param("str25")String str25,@Param("str27")String str27,@Param("str28")String str28,@Param("str29")int str29,@Param("str30")int str30,@Param("str31")int str31,@Param("str32")String str32);

	int updateByPrimaryKey(Staff record);

	/** 模糊查询所有员工 **/
	List<Staff> selectAll(@Param("departmentids") int[] departmentids,
			@Param("positionid") int positionid, @Param("typeid") int typeid,
			@Param("number") String number, @Param("name") String name,
			@Param("groupid") int groupid);
	
	List<Staff> selectAll2();

	/** 模糊只查询机构对应的员工 **/
	List<Staff> selectStaff(@Param("departmentId") int departmentId,
			@Param("name") String name, @Param("number") String number);

	/** 修改员工班组id **/
	int updateStaffGroupId(@Param("staffids") int[] staffids,
			@Param("groupid") int groupid);

	/** 根据班组id查询员工信息 **/
	List<Staff> selectStaffByGroupId(@Param("groupid") int groupid);

	/** 编辑班组时修改班组和删除大于今天的考勤安排 **/
	int editByGroupidAndStaffids(@Param("groupid") int groupid,
			@Param("staffids") int[] staffids);

	/** 班组id改变时删除大于今天的考勤记录 **/
	int deleteRecordByStaffid(@Param("staffid") int staffid);

	/** 修改某个员工制度时改变班次安排大于今天的考勤制度 **/
	int updateStaffSystem(@Param("staffid") int staffid,
			@Param("systemid") int systemid);
	
	
	/**根据用户名密码查找用户 **/
    Staff selectUserByUserName(@Param("username")String username,@Param("password")String password);
    /**黄宽录      根据员工号查看所在部门 **/
    Department selectDeptByStaffID(@Param("staffid") int staffid);
    
  //检验用户名是否存在
    Staff selectUserByUsername(@Param("username")String username);

	void insertSelective2(@Param("str21")String str21,@Param("str22")String str22,@Param("str23")String str23,@Param("str24")String str24,@Param("str25")String str25,@Param("str26")String str26,@Param("str27")String str27,@Param("str28")String str28,@Param("str29")int str29,@Param("str30")int str30,@Param("str31")int str31,@Param("str32")String str32,@Param("image")String image);

	void deleteByPrimaryKey2();

	List<Staff> selectid(@Param("staffId") int staffId);
}