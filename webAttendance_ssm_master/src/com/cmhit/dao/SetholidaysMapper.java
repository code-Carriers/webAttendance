package com.cmhit.dao;

import com.cmhit.po.Setholidays;

public interface SetholidaysMapper {
    int deleteByPrimaryKey(Integer holidaysid);

    int insert(Setholidays record);

    int insertSelective(Setholidays record);

    Setholidays selectByPrimaryKey(Integer holidaysid);

    int updateByPrimaryKeySelective(Setholidays record);

    int updateByPrimaryKey(Setholidays record);
}