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

import com.alibaba.fastjson.JSONObject;
import com.yang.pojo.Article;
import com.yang.service.ArticleService;
import com.yang.service.UserService;
import com.yang.util.FormDataUtil;

@Controller
public class ArticleController {
	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	@RequestMapping(value="/warticle",method=RequestMethod.GET)
	public String wArticle(HttpServletRequest req,HttpSession session){
		String username=(String) session.getAttribute("username");
		if(username==null)
		{
			return "redirect:login";
		}
		return "warticle";
	}
	
	@ResponseBody
	@RequestMapping(value="/warticle",method=RequestMethod.POST)
	public String wArticle1(Article article){
		int status=articleService.addArticle(article);
		Map<String, Object> tips =new HashMap<>();
		if(status==1)
		{
			tips.put("status", "YES");
			tips.put("msg", "发表成功");
		}else {
			tips.put("status", "NO");
			tips.put("msg", "发表失败");
		}			
		return JSONObject.toJSONString(tips);
	}
	
	@ResponseBody
	@RequestMapping(value="/articleimg",method=RequestMethod.POST)
	public String saveArticleimg(HttpServletRequest req,HttpSession session) {
		Map<String, Object> tips =new HashMap<>();
		Map<String, Object> data =new HashMap<>();
		Map<String, Object> filesrc =new HashMap<>();
		String userid=(String) session.getAttribute("userid");
		FormDataUtil formdata=new FormDataUtil(userid,req);
		filesrc=formdata.getFormData();
		List<String> srclist=(List<String>) filesrc.get("src");
		data.put("src", srclist.get(0));
		tips.put("code", 0);
		tips.put("msg", "success");
		tips.put("data", data);
		return JSONObject.toJSONString(tips);
	}
	
	@RequestMapping(value="/article",method=RequestMethod.GET)
	public String lookArticle(Model model,HttpServletRequest req,HttpSession session)
	{
		String username=(String) session.getAttribute("username");
		if(username==null)
		{
			return "redirect:login";
		}
		String articleid=req.getParameter("articleid");
		Article article=articleService.getArticleByArticleid(articleid);//按文章ID查找文章
		model.addAttribute("article", article);
		return "article";
	}
}
