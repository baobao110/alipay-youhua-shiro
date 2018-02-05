package com.mapper;

import com.domain.user;

public interface userMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(user record);

    int insertSelective(user record);

    user selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(user record);

    int updateByPrimaryKey(user record);
    
    user Login(String username,String password);
    
    user getByname(String username);
    
    int addUser(user record);
}