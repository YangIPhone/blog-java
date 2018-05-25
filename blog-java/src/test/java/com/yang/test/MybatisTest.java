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
import com.yang.mapper.UserMapper;
import com.yang.pojo.Article;
import com.yang.pojo.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private ArticleMapper articleMapper;
	
	@Test
	public void testList() {
		PageHelper.offsetPage(0, 5);
		User cs=userMapper.getUserByUseridAndPwd("123456", "123456");
		System.out.println(cs.getPassword());
	}
	
	@Test
	public void testaddarticle() {
		Article article=new Article();
		article.setTitle("title");
		article.setContent("content");
		article.setUserid("123456");
		article.setUsername("杨昌权");
		int i= articleMapper.addArticle(article);
		System.out.println(i);
	}

}
