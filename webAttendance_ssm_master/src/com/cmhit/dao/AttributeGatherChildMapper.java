package com.cmhit.dao;

import java.util.List;

import com.cmhit.po.AttributeGatherChild;

public interface AttributeGatherChildMapper {
    int deleteByPrimaryKey(Integer attributegatherchildid);

    int insert(AttributeGatherChild record);

    int insertSelective(AttributeGatherChild record);

    AttributeGatherChild selectByPrimaryKey(Integer attributegatherchildid);

    int updateByPrimaryKeySelective(AttributeGatherChild record);

    int updateByPrimaryKey(AttributeGatherChild record);
    //新增的
    List<AttributeGatherChild> selectByAttributeGatherId(int attributeGatherId);
}