package com.yang.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String index(Model model,HttpSession session)
	{	
		String name=(String) session.getAttribute("name");
		if(name==null)
		{
			return "redirect:login";
		}
		model.addAttribute("username",name);
		return "index";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String login(Model model,HttpServletRequest req)
	{	System.out.println("login get");
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String verifyUser(HttpServletRequest req)
	{	
		Map<String, Object> value =new HashMap<>();
		FormDataUtil formdata=new FormDataUtil(req);
		value=formdata.getFormData();
		System.out.println(JSON.toJSONString(value));
		return JSON.toJSONString(value);//返回json字符串
	}
	
}
