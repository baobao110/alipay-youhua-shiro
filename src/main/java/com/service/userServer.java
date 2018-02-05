package com.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.domain.user;
import com.exception.BigException;
import com.mapper.userMapper;

@Service
public class userServer {
	
	@Autowired
	private userMapper u;
	
	@Transactional(rollbackFor = Exception.class)
	public user login(String username,String password) throws Exception {
		user x= u.Login(username, password);
		if(null==x) {
			throw new BigException("用户名或者密码不正确");
		}
		else {
			return x;
		}
		
		
	}
	
	public user getByname(String username) {
		return u.getByname(username);
	}
	
	@Transactional(rollbackFor=Exception.class)
	public user register(String username,String password) throws Exception {
		user use=new user();
		use.setUsername(username);
		use.setPassword(DigestUtils.md5Hex(password));
		int i=u.addUser(use);
		if(i!=1) {
			
			throw new BigException("注册失败");
		}
		return use;
	}
			
}
