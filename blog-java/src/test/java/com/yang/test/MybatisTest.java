package com.yang.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.mapper.ArticleMapper;
import com.yang.mapper.ResourceMapper;
import com.yang.mapper.UserMapper;
import com.yang.pojo.Article;
import com.yang.pojo.Resource;
import com.yang.pojo.User;
import com.yang.service.ResourceService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {	
	@Autowired
	ResourceService resourceService;
	
	@Test
	public void testFindRes() {
		String d="js";
		List<Resource> res=resourceService.resListByDescribe(d);
		System.out.println(res);
	}
	
	@Test
	public void testresListByAll() {
		List<Resource> res=resourceService.resListByAll();
		System.out.println(res);
	}

}
