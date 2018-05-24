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
import com.yang.pojo.User;
import com.yang.service.UserService;

@Controller
public class IndexController {
	@Autowired
	UserService userService;
	@RequestMapping(value="/index",method=RequestMethod.GET)
	public String index(Model model,HttpSession session)
	{	
		String username=(String) session.getAttribute("username");
		if(username==null)
		{
			return "redirect:login";
		}
		model.addAttribute("username",username);
		return "index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model,HttpServletRequest req)
	{	System.out.println("login get");
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String verifyUser(User users,HttpServletRequest req,HttpSession session)
	{	
		String userid=users.getUserid();
		String password=users.getPassword();
		Map<String, Object> value =new HashMap<>();
		User user=userService.getUserByUseridAndPwd(userid, password);
		if(user!=null)
		{
			value.put("status", "YES");
			value.put("msg", "登录成功");
			session.setAttribute("username", user.getUsername());
		}else {
			value.put("status", "NO");
			value.put("msg", "用户名或密码错误");
		}
		return JSON.toJSONString(value);//返回json字符串
	}
	
}
