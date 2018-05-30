package com.yang.service;

import java.util.List;

import com.yang.pojo.User;

public interface UserService {
	public User getUserByUserid(String userid);
	public User getUserByUseridAndPwd(String userid,String password);
	public int 	updateUser(User user);
	public int 	updateUserPwd(String userid,String password);
}
