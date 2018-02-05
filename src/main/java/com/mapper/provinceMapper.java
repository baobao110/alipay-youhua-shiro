package com.mapper;

import java.util.ArrayList;

import com.domain.province;

public interface provinceMapper {
	int insert(province record);

    int insertSelective(province record);
    
    ArrayList<province> Province();
}