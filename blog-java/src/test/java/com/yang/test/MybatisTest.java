package com.yang.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.yang.mapper.AnswerMapper;
import com.yang.pojo.Answer;
import com.yang.pojo.Question;
import com.yang.pojo.Resource;
import com.yang.service.QuestionService;
import com.yang.service.ResourceService;
import com.yang.util.SendCodeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class MybatisTest {	
	@Autowired
	ResourceService resourceService;
	@Autowired
	QuestionService questionService;
	@Autowired
	AnswerMapper answerMapper;
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
	
	@Test
	public void testAddQuestion()
	{
		Question question=new Question();
		question.setUserid("123456");
		question.setUsername("§流い年§");
		question.setQuestitle("JUnitTest");
		question.setQuestion("JUnitTest");
		question.setTime("2018-06-05 9:42:10");
		questionService.addQuestion(question);
	}
	
	@Test
	public void testAnswer()
	{
		String quesid="1";
		List<Answer> answer=answerMapper.getAnswerById(quesid);
		System.out.println(answer.get(0).getHeadimg());
	}
	
	@Test
	public void testSendCode() throws IOException
	{
		String codeLen="6";
		String phone="13512393403";
		String templateid="4013053";
		SendCodeUtil sendcode=new SendCodeUtil();
		Map<String,String> data=new HashMap<>();
		data.put("templateid", templateid);
		data.put("mobile",phone);
		data.put("codeLen", codeLen);
		String result=sendcode.httpByPost(data);
		Map maps = (Map)JSON.parse(result);
		System.out.println(maps);
	}
	
}
