package com.cmhit.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cmhit.po.Staffclassperiodsigninrecord;
import com.cmhit.vo.PunchCard;

public interface StaffclassperiodsigninrecordMapper {
    int deleteByPrimaryKey(Integer staffclassperiodsigninrecordid);

    int insert(Staffclassperiodsigninrecord record);

    int insertSelective(Staffclassperiodsigninrecord record);
    
    void updateSelective2(@Param("str21")int str21,@Param("str22")String str22,@Param("str23")String str23,@Param("str24")int str24);

    Staffclassperiodsigninrecord selectByPrimaryKey(Integer staffclassperiodsigninrecordid);

    int updateByPrimaryKeySelective(Staffclassperiodsigninrecord record);

    int updateByPrimaryKey(Staffclassperiodsigninrecord record);

	List<Staffclassperiodsigninrecord> selectAll();
   
}