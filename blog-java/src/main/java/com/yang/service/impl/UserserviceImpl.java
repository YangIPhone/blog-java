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
	public User getUserByUserid(String userid) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUserid(userid);
	}
	@Override
	public User getUserByUseridAndPwd(String userid,String password) {
		// TODO Auto-generated method stub
		return userMapper.getUserByUseridAndPwd(userid,password);
	}
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateUser(user);
	}
	@Override
	public int updateUserPwd(String userid, String password) {
		// TODO Auto-generated method stub
		return userMapper.updateUserPwd(userid, password);
	}

}
