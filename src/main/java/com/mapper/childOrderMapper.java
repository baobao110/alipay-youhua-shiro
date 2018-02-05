package com.mapper;

import java.util.ArrayList;

import com.domain.childOrder;

public interface childOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(childOrder record);

    int insertSelective(childOrder record);

    childOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(childOrder record);

    int updateByPrimaryKey(childOrder record);
    
    ArrayList<childOrder> childOrder(Integer orderId);
}