package com.mapper;

import java.util.ArrayList;

import com.domain.catalog;

public interface catalogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(catalog record);

    int insertSelective(catalog record);

    catalog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(catalog record);

    int updateByPrimaryKey(catalog record);
    
    ArrayList<catalog> List(String parent_num);
    
    catalog getNum(String catalogNum);
    
    
}