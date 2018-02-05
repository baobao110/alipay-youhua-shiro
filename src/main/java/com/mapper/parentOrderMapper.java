package com.mapper;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.domain.parentOrder;

public interface parentOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(parentOrder record);

    int insertSelective(parentOrder record);

    parentOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(parentOrder record);

    int updateByPrimaryKey(parentOrder record);
    
    parentOrder getOrderLock(Integer id);
    
    ArrayList<parentOrder> ListByUserId(Integer userId);
    
    int updateOrderStatus(@Param("date")Date date);
    
    int updateStatus(@Param("id")Integer id,@Param("status")Integer status);
    
    ArrayList<parentOrder> ListBydate(@Param("date")Date date);
    
    int drop(@Param("id")Integer id,@Param("date")Date date);
    
    ArrayList<parentOrder> LockByStatus(@Param("date")Date date);
}