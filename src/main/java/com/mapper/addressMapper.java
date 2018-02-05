package com.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.domain.address;

public interface addressMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(address record);

    int insertSelective(address record);

    address selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(address record);

    int updateByPrimaryKey(address record);
    
   ArrayList<address> selectByUserId(@Param("userId")int userId);
    
}