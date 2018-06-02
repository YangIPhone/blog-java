package com.yang.service.impl;

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
	public int addQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionMapper.addQuestion(question);
	}

}
