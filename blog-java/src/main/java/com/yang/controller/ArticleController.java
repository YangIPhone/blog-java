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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yang.pojo.Article;
import com.yang.service.ArticleService;
import com.yang.service.UserService;
import com.yang.util.FormDataUtil;
import com.yang.util.Page;

@Controller
public class ArticleController {
	@Autowired
	UserService userService;
	@Autowired
	ArticleService articleService;
	
	@RequestMapping(value="/warticle",method=RequestMethod.GET)
	public String wArticle(HttpServletRequest req,HttpSession session){
		String userid=(String) session.getAttribute("userid");
		if(userid==null)
		{
			return "redirect:login";
		}
		return "warticle";
	}
	
	/**
	 * 发表文章的接口
	 * @param article 文章内容对象
	 * @return 返回前端的提示信息
	 */
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
	
	/**
	 * 写文章时插入图片的接口
	 * @param req HttpServletRequest
	 * @param session HttpSession
	 * @return 固定格式的json数据
	 */
	@ResponseBody
	@RequestMapping(value="/articleimg",method=RequestMethod.POST)
	public String saveArticleimg(HttpServletRequest req,HttpSession session) {
		String userid=(String) session.getAttribute("userid");
		Map<String, Object> tips =new HashMap<>();
		Map<String, Object> data =new HashMap<>();
		Map<String, Object> filesrc =new HashMap<>();		
		FormDataUtil formdata=new FormDataUtil(userid,"article",req);
		filesrc=formdata.getFormData();
		List<String> srclist=(List<String>) filesrc.get("src");
		data.put("src", srclist.get(0));
		tips.put("code", 0);
		tips.put("msg", "success");
		tips.put("data", data);
		return JSONObject.toJSONString(tips);
	}
	
	/**
	 * 根据get到的文章ID查看文章
	 * @param model   Model
	 * @param req     HttpServletRequest
	 * @param session HttpSession
	 * @return
	 */
	@RequestMapping(value="/article",method=RequestMethod.GET)
	public String lookArticle(Model model,HttpServletRequest req,HttpSession session){
		String userid=(String) session.getAttribute("userid");
		if(userid==null)
		{
			return "redirect:login";
		}
		String articleid=req.getParameter("articleid");
		Article article=articleService.getArticleByArticleid(articleid);//按文章ID查找文章
		if(article==null)
		{
			return "404page";
		}
		model.addAttribute("article", article);
		return "article";
	}
	
	/**
	 * 文章列表分页显示
	 * @param page 分页对象
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/articlelist",method=RequestMethod.GET)
	public String articleList(Page page, Model model,HttpSession session) {
		String userid=(String) session.getAttribute("userid");
		if(userid==null){
			return "redirect:login";
		}
		if(page.getStart()<0) {
			return "404page";
		}
		String by=page.getBy();
		String value=page.getValue();
		List<Article> articlelist=null;
		int limit=10;//每页显示的条数
		//开启分页，从start开始查找五条记录
		PageHelper.offsetPage(page.getStart(), limit);
		switch (by) {
		case "type":
			articlelist= articleService.getArticleByType(value);
			break;
		case "uid":
			articlelist= articleService.getArticleByUserid(value);
			break;
		default:
			return "404page";
		}
//		if(articlelist.size()==0)
//		{
//			return "404page";
//		}
		//总的记录条数
		int total = (int) new PageInfo<>(articlelist).getTotal();		
		//设置最后一页第一条记录
		page.setCount(limit);
		page.caculateLast(total);
		model.addAttribute("total", total);
		model.addAttribute("limit", limit);
		model.addAttribute("articlelist", articlelist);
		return "articlelist";
	}	
}
