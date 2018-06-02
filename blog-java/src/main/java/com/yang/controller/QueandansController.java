package com.yang.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class QueandansController {
	
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
}
