package com.cmhit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cmhit.po.Department;
@Repository("departmentMapper")
public interface DepartmentMapper {
    int deleteByPrimaryKey(Integer departmentid);
    void deleteByPrimaryKey2();
    List<Department> selectAll();
    int insert(Department record);
    
    int insertSelective(Department record);
    
    Department selectByPrimaryKey(Integer departmentid);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
	void updateByPrimaryKey2(@Param("str3") String str3,@Param("str5") String str5,@Param("str6") String str6,@Param("str7") int str7,@Param("mm") int mm);
	void insert2(@Param("str3") String str3,@Param("str5") String str5,@Param("str6") String str6,@Param("str7") int str7,@Param("mm") int mm);
}