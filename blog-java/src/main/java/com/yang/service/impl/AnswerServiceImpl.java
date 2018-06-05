package com.yang.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yang.mapper.AnswerMapper;
import com.yang.pojo.Answer;
import com.yang.service.AnswerService;

@Service
public class AnswerServiceImpl implements AnswerService {
	@Autowired
	AnswerMapper answerMapper;
	
	@Override
	public List<Answer> getAnswerById(String quesid) {
		// TODO Auto-generated method stub
		return answerMapper.getAnswerById(quesid);
	}

	@Override
	public int addAnswer(Answer answer) {
		// TODO Auto-generated method stub
		return answerMapper.addAnswer(answer);
	}

}
