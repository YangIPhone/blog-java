package com.yang.service;

import java.util.List;

import com.yang.pojo.User;

public interface UserService {
	List<User> users();
	User getUserByUseridAndPwd(String userid,String password);
}
