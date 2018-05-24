package com.yang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.yang.util.FormDataUtil;

@Controller
public class IndexController {
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
	public String verifyUser(HttpServletRequest req,HttpSession session)
	{	
		Map<String, Object> value =new HashMap<>();
		//FormDataUtil formdata=new FormDataUtil(req);
		//value=formdata.getFormData();
		if("a".equals(req.getParameter("username"))&&"b".equals(req.getParameter("password")))
		{
			value.put("status", "YES");
			value.put("msg", "登录成功");
			session.setAttribute("username", req.getParameter("username"));
		}else {
			value.put("status", "NO");
			value.put("msg", "用户名或密码错误");
		}
		System.out.println(JSON.toJSONString(value));
		return JSON.toJSONString(value);//返回json字符串
	}
	
}
