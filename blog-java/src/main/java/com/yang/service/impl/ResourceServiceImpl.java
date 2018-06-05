package com.yang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.mapper.ResourceMapper;
import com.yang.pojo.Resource;
import com.yang.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	ResourceMapper resourceMapper;
	@Override
	public List<Resource> resListByAll() {
		// TODO Auto-generated method stub
		return resourceMapper.resListByAll();
	}
	@Override
	public List<Resource> resListByUserid(String userid) {
		// TODO Auto-generated method stub
		return resourceMapper.resListByUserid(userid);
	}

	@Override
	public List<Resource> resListByDescribe(String describe) {
		// TODO Auto-generated method stub
		return resourceMapper.resListByDescribe("%"+describe+"%");
	}

	@Override
	public int addResource(Resource resource) {
		// TODO Auto-generated method stub
		return resourceMapper.addResource(resource);
	}
	@Override
	public Resource getResourceByResid(String resid) {
		// TODO Auto-generated method stub
		return resourceMapper.getResourceByResid(resid);
	}
}
