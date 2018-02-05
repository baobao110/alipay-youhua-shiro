package com.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.domain.commodity;
import com.domain.commodityWithBLOBs;

public interface commodityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(commodityWithBLOBs record);

    int insertSelective(commodityWithBLOBs record);

    commodityWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(commodityWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(commodityWithBLOBs record);

    int updateByPrimaryKey(commodity record);
    
    ArrayList<commodity> Flow(String parent_catalog_num);
    
    int descNum(@Param("id")Integer id,@Param("store")int store);
    
    commodityWithBLOBs selectByLock(Integer id);
    
    int aescNum(@Param("id")Integer id,@Param("store")int store);
    
    
    
}