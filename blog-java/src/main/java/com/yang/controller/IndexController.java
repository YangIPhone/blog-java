package com.yang.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yang.pojo.Article;
import com.yang.pojo.User;
import com.yang.service.ArticleService;
import com.yang.service.UserService;
import com.yang.util.FormDataUtil;
import com.yang.util.Progress;
import com.yang.util.SendCodeUtil;
import com.yang.util.ValicodeUtil;

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
			session.setAttribute("headimg", user.getHeadimg());
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
	
	@RequestMapping(value="/basicinfor",method=RequestMethod.GET)
	public String basicInfor(Model model,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");		
		if(userid==null)
		{
			return "redirect:login";
		}
		User user=userService.getUserByUserid(userid);
		model.addAttribute("user", user);
		return "basicinfor";
	}
	
	/**
	 * 修改用户昵称及头像
	 * @param req
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/basicinfor",method=RequestMethod.POST)
	public String updateUserInfor(HttpServletRequest req,HttpSession session) {
		Map<String, Object> tips =new HashMap<>();
		User user=new User();
		String userid=(String) session.getAttribute("userid");
		FormDataUtil formdate=new FormDataUtil(userid, "headimg", req);
		Map<String,Object> date= formdate.getFormData();
		String username=(String) date.get("username");
		if("".equals(username))
		{
			tips.put("code", 1);
			tips.put("msg", "昵称不能为空");
			return JSONObject.toJSONString(tips);
		}
		List<String> src=(List<String>) date.get("src");
		String headimg=src.get(0);
		user.setUserid(userid);
		user.setUsername(username);
		user.setHeadimg(headimg);
		int status=userService.updateUser(user);
		if(status==1) 
		{
			session.setAttribute("username", username);
			session.setAttribute("headimg", headimg);
			tips.put("code", 0);
			tips.put("msg", "修改成功");
		}
		return JSONObject.toJSONString(tips);
	}
	
	/**
	 * 上传文件的进度信息
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getprogress",method=RequestMethod.POST)
	public String getprogress(HttpSession session)
	{
		Progress progress=(Progress) session.getAttribute("progress");
		return JSONObject.toJSONString(progress);
	}
	
	@RequestMapping(value="register",method=RequestMethod.GET)
	public String register()
	{
		return "register";
	}
	
	/**
	 * 图片验证码
	 * @param res
	 * @param session
	 * @throws IOException
	 */
	@RequestMapping(value="imgcode",method=RequestMethod.GET)
	public void imgcode( HttpServletResponse res,HttpSession session) throws IOException
	{
		ValicodeUtil randomcode=new ValicodeUtil();
		//获取6位随机验证码
		char[] code= randomcode.RandomCode();
		session.setAttribute("imgcode", String.valueOf(code));
		RenderedImage img=randomcode.getCaptcha(code,35,200,50);//五位验证码,验证码字体大小,矩形宽,矩形高
		ImageIO.write(img, "jpg", res.getOutputStream());
	}
	
	/**
	 * 发送短信验证码
	 * @param req
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="sendphonecode",method=RequestMethod.POST)
	public String sendphoneCode(HttpServletRequest req,HttpSession session) throws IOException
	{	
		String imgcode=req.getParameter("imgcode");
		String simgcode=(String) session.getAttribute("imgcode");
		Map<String,String> tips=new HashMap<>();
		if(!imgcode.equals(simgcode))
		{
			tips.put("code", "1");
			tips.put("msg", "图片验证码错误");
			return JSONObject.toJSONString(tips);
		}
		//验证码长度(4-10)
		String codeLen="6";
		//用户手机号
		String phone=req.getParameter("phone");
		//短信模板ID
		String templateid="4013053";
		//验证手机号的正则表达式
		String pattern="^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$";
		boolean isPhone = Pattern.matches(pattern, phone);
		if(!isPhone)
		{
			tips.put("code", "2");
			tips.put("msg", "请输入正确的手机号");
			return JSONObject.toJSONString(tips);
		}
		//发送验证信息
		SendCodeUtil sendcode=new SendCodeUtil();
		Map<String,String> data=new HashMap<>();
		data.put("templateid", templateid);
		data.put("mobile",phone);
		data.put("codeLen", codeLen);
		String result=sendcode.httpByPost(data);
		//将应答信息转为map
		Map datamap = (Map)JSON.parse(result);
//		System.out.println(result);
		if(200==(int)datamap.get("code"))
		{
			session.removeAttribute("imgcode");
			String phonecode=(String) datamap.get("obj");
			session.setAttribute("phonecode", phonecode);
			tips.put("code", "0");
			tips.put("msg", "验证码已发送至您的手机");
		}else {
			tips.put("code", "3");
			tips.put("msg", "验证码发送失败");
		}
		return JSONObject.toJSONString(tips);
	}
	
	/**
	 * 注册用户
	 * @param req
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="register",method=RequestMethod.POST)
	public String registeruser(HttpServletRequest req,HttpSession session)
	{	
		Map<String,String> tips=new HashMap<>();
		String userid=req.getParameter("userid");
		String username=req.getParameter("username");
		String phone=req.getParameter("phone");
		String password=req.getParameter("password");
		String phonecode=req.getParameter("phonecode");
		if("".equals(userid)||"".equals(username)||"".equals(phone)||"".equals(password)||"".equals(phonecode))
		{
			tips.put("code", "1");
			tips.put("msg", "所有项为必填");
			return JSONObject.toJSONString(tips);
		}
		//验证用户账号的正则表达式
		String pattern="^[A-Za-z0-9]{8,15}$";
		boolean isuserid = Pattern.matches(pattern, userid);
		boolean ispassword=Pattern.matches(pattern, password);
		if(!isuserid||!ispassword)
			{
				tips.put("code", "2");
				tips.put("msg", "账号和密码只能是8-15位的字母或数字组合");
				return JSONObject.toJSONString(tips);
			}
		User u=userService.getUserByUserid(userid);
		if(u!=null)
		{
			tips.put("code", "3");
			tips.put("msg", "该账号已被注册，换一个吧");
			return JSONObject.toJSONString(tips);
		}
		String sphonecode=(String) session.getAttribute("phonecode");
		if(sphonecode!=null&&sphonecode.equals(phonecode))
		{
			User user=new User();
			user.setUserid(userid);
			user.setUsername(username);
			user.setPhone(phone);
			user.setPassword(password);
			//设置默认头像
			user.setHeadimg("http://t.cn/RCzsdCq");
			userService.addUser(user);
			session.removeAttribute("phonecode");
			tips.put("code", "0");
			tips.put("msg", "注册成功");
		}else {
			tips.put("code", "4");
			tips.put("msg", "手机验证码错误");
		}
		return JSONObject.toJSONString(tips);
	}
}
