package com.yang.service;

import java.util.List;

import com.yang.pojo.Answer;

public interface AnswerService {
	List<Answer> getAnswerById(String quesid);
	int addAnswer(Answer answer);
}
