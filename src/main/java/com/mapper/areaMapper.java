package com.mapper;

import java.util.ArrayList;

import com.domain.area;

public interface areaMapper {
    int insert(area record);

    int insertSelective(area record);
    
    ArrayList<area> Area(int cityNumber);
}