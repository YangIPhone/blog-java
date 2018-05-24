package com.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.User;

public interface UserMapper {
		public List<User> users();
		public User getUserByUseridAndPwd(@Param("userid") String userid,@Param("password")String password);
}
