package com.yang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yang.pojo.Answer;

public interface AnswerMapper {
	List<Answer> getAnswerById(@Param("quesid")String quesid);
	int addAnswer(Answer answer);
}
