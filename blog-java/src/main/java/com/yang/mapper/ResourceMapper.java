package com.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.Resource;

public interface ResourceMapper {
	List<Resource> resListByAll();
	List<Resource> resListByUserid(@Param("userid") String userid);
	List<Resource> resListByDescribe(@Param("describe") String describe);
	int addResource(Resource resource);
}
