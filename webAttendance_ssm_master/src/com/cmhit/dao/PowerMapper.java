package com.cmhit.dao;

import com.cmhit.po.Power;

public interface PowerMapper {
    int insert(Power record);

    int insertSelective(Power record);
}