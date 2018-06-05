package com.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.yang.pojo.Question;

public interface QuestionMapper {
	List<Question> getQuestionList();
	Question getQuestionById(@Param("quesid") String quesid);
	List<Question> getQuestionByUid(@Param("userid") String userid);
	int addQuestion(Question question);
}
