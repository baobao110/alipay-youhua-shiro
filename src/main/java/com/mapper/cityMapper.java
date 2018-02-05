package com.mapper;

import java.util.ArrayList;

import com.domain.city;
import com.domain.province;

public interface cityMapper {
    int insert(city record);

    int insertSelective(city record);
    
    ArrayList<city> City(String proviceNum);
}