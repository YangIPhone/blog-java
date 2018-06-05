package com.yang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.pojo.Answer;
import com.yang.pojo.Question;
import com.yang.pojo.Resource;
import com.yang.service.AnswerService;
import com.yang.service.QuestionService;
import com.yang.util.Page;

@Controller
public class QueandansController {
	@Autowired
	QuestionService questionService;
	@Autowired
	AnswerService answerService;
	
	/**
	 * 提问求助页面
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queandans" ,method=RequestMethod.GET)
	public String queAndAns(HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null)
		{
			return "redirect:login";
		}
		return "queandans";
	}
	
	/**
	 * 发布问题
	 * @param question
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addquestion",method=RequestMethod.POST)
	public String addQuestion(Question question,HttpSession session)
	{
		Map<String, String> tips=new HashMap<String,String >();
		try {
			questionService.addQuestion(question);
			tips.put("status", "YES");
			tips.put("msg", "发布成功");
		} catch (Exception e) {
			// TODO: handle exception
			tips.put("status", "NO");
			tips.put("msg", "发布失败");
		}
		return JSONObject.toJSONString(tips);
	}
	
	
	/**
	 * 问题列表页面
	 * @param page
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/queslist" ,method=RequestMethod.GET)
	public String quesList(Page page,Model model,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null)
		{
			return "redirect:login";
		}
		if(page.getStart()<0) {
			return "404page";
		}
		String by=page.getBy();
		String value=page.getValue();
		List<Question> quesList=null;
		int limit=10;//每页显示的条数
		//开启分页，从start开始查找五条记录
		PageHelper.offsetPage(page.getStart(), limit);
		switch (by) {
		case "uid":
			quesList=questionService.getQuestionByUid(value);
			break;
		default:
			quesList=questionService.getQuestionList();
			break;
		}
		//总的记录条数
		int total = (int) new PageInfo<>(quesList).getTotal();		
		//设置最后一页第一条记录
		page.setCount(limit);
		page.caculateLast(total);
		model.addAttribute("total", total);
		model.addAttribute("limit", limit);
		model.addAttribute("queslist", quesList);
		return "queslist";
	}
	
	/**
	 * 根据问题ID查看具体问题及回答
	 * @param model
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/question" ,method=RequestMethod.GET)
	public String question(Model model,HttpServletRequest req,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null)
		{
			return "redirect:login";
		}
		String quesid=req.getParameter("quesid");
		Question question=questionService.getQuestionById(quesid);
		if(question==null)
		{
			return "404page";
		}
		List<Answer> answers=answerService.getAnswerById(quesid);
		if(answers==null)
		{
			return "404page";
		}
		int total=answers.size();
		model.addAttribute("total", total);
		model.addAttribute("question", question);
		model.addAttribute("answers", answers);
		return "question";
	}
	
	/**
	 * 添加回答
	 * @param answer
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addanswer" ,method=RequestMethod.POST)
	public String addAnswer(Answer answer,HttpServletRequest req)
	{
		answerService.addAnswer(answer);
		return "{\"msg\":\"回答成功\",\"code\":0}";
	}
}
