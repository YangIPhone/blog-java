package com.yang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.mapper.UserMapper;
import com.yang.pojo.User;
import com.yang.service.UserService;

@Service
public class UserserviceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	@Override
	public List<User> users() {
		// TODO Auto-generated method stub
		return userMapper.users();
	}
	@Override
	public User getUserByUseridAndPwd(String userid,String password) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUseridAndPwd(userid,password);
	}

}
