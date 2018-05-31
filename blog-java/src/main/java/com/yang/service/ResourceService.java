package com.yang.service;

import java.util.List;

import com.yang.pojo.Resource;

public interface ResourceService {
	List<Resource> resListByAll();
	List<Resource> resListByUserid(String userid);
	List<Resource> resListByDescribe(String describe);
	int addResource(Resource resource);
}
