package com.yang.service;

import java.util.List;

import com.yang.pojo.Question;

public interface QuestionService {
	List<Question> getQuestionList();
	Question getQuestionById(String quesid);
	List<Question> getQuestionByUid(String userid);
	int addQuestion(Question question);
}
