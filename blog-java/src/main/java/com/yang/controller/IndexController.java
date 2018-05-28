package com.yang.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yang.pojo.Article;
import com.yang.pojo.User;
import com.yang.service.ArticleService;
import com.yang.service.UserService;

@Controller
public class IndexController {
	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	
	/**
	 * 主页
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,HttpSession session)
	{	
		String userid=(String) session.getAttribute("userid");		
		if(userid==null)
		{
			return "redirect:login";
		}
		String username=(String) session.getAttribute("username");
		List<Article> articlelist=articleService.getArticleList();
		model.addAttribute("articlelist", articlelist);
		model.addAttribute("username",username);
		return "index";
	}
	
	/**
	 * 登录页面
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model,HttpServletRequest req)
	{
		return "login";
	}
	
	/**
	 * 验证用户登录信息
	 * @param users
	 * @param req
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String verifyUser(User users,HttpServletRequest req,HttpSession session)
	{	
		String userid=users.getUserid();
		String password=users.getPassword();
		Map<String, Object> tips =new HashMap<>();
		User user=userService.getUserByUseridAndPwd(userid, password);
		if(user!=null)
		{
			tips.put("status", "YES");
			tips.put("msg", "登录成功");
			session.setAttribute("userid", user.getUserid());
			session.setAttribute("username", user.getUsername());
		}else {
			tips.put("status", "NO");
			tips.put("msg", "用户名或密码错误");
		}
		return JSON.toJSONString(tips);//返回json字符串 
	}
	
	/**
	 * 退出登录 
	 * @param model
	 * @param req
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/loginout",method=RequestMethod.GET)
	public String loginout(Model model,HttpServletRequest req,HttpSession session)
	{	
		session.removeAttribute("userid");
		session.removeAttribute("username");
		return "redirect:login";
	}
}
