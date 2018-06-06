package com.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.User;

public interface UserMapper {
		public User getUserByUserid(@Param("userid") String userid);
		public User getUserByUseridAndPwd(@Param("userid") String userid,@Param("password")String password);
		public int 	addUser(User user);
		public int 	updateUser(User user);
		public int 	updateUserPwd(@Param("userid") String userid,@Param("password")String password);
}
