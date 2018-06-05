package com.yang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.mapper.QuestionMapper;
import com.yang.pojo.Question;
import com.yang.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	@Autowired
	QuestionMapper questionMapper;
	@Override
	public List<Question> getQuestionList() {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionList();
	}
	@Override
	public Question getQuestionById(String quesid) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionById(quesid);
	}
	@Override
	public List<Question> getQuestionByUid(String userid) {
		// TODO Auto-generated method stub
		return questionMapper.getQuestionByUid(userid);
	}
	@Override
	public int addQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionMapper.addQuestion(question);
	}
}
