package com.mapper;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Param;

import com.domain.shopcart;

public interface shopcartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(shopcart record);

    int insertSelective(shopcart record);

    shopcart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(shopcart record);

    int updateByPrimaryKey(@Param("commId")Integer commId,@Param("commNum")Integer commNum);
    
    int updateNum(@Param("commNum")int commNum,@Param("userId")int userId,@Param("commId")int commId,@Param("modify_time")Date modify_time);
    
    shopcart select(@Param("userId")Integer userId,@Param("commId")Integer commId);
    
    int getNum(@Param("userId")Integer userId);
    
    ArrayList<shopcart> selectByMore(@Param("userId")Integer userId,@Param("status")Integer status);
    
    int delete(@Param("userId")Integer userId,@Param("commId")Integer commId);
    
    shopcart selectBycommId(Integer commId);
    
    int updateBycomId(@Param("commId")int commId,@Param("commNum")Integer commNum);
    
    shopcart selectByLock(@Param("commId")Integer commId);
    
    ArrayList<shopcart> selectBystatus(@Param("status")Integer status,@Param("date")Date date,@Param("offset")int offset,@Param("move")int move);
    
    int updateCanel(@Param("status")Integer status,@Param("id")Integer id);
    
    
    
    
    
    
}