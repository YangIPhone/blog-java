package com.yang.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.yang.pojo.Resource;
import com.yang.service.ResourceService;
import com.yang.util.FormDataUtil;
import com.yang.util.IOUtil;
import com.yang.util.Page;

@Controller
public class ResourceController {
	@Autowired
	ResourceService resourceService;
	
	/**
	 * 上传资源页面
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/uploadres",method=RequestMethod.GET)
	public String uploadRes(Model model,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		if(userid==null)
		{
			return "redirect:login";
		}
		return "uploadres";
	}
	
	/**
	 * 上传资源
	 * @param req
	 * @param session
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/uploadres",method=RequestMethod.POST)
	public String uploadResfile(HttpServletRequest req,HttpSession session)
	{
		String userid=(String) session.getAttribute("userid");
		String username=(String) session.getAttribute("username");
		Map<String, Object> tips =new HashMap<>();
		FormDataUtil formdate=new FormDataUtil(userid, "resources", req);
		Map<String,Object> date= formdate.getFormData();
		//文件描述
		String describe=(String) date.get("describe");
		if("".equals(describe))
		{
			tips.put("code", 1);
			tips.put("msg", "资源描述不能为空");
			return JSONObject.toJSONString(tips);
		}
		Resource resource=new Resource();
		//文件名
		String filename=(String) date.get("filename");
		//文件路径
		List<String> src=(List<String>) date.get("src");
		String filesrc=src.get(0);
		//文件大小
		String filesize=(String) date.get("filesize");
		//上传时间
		LocalDateTime localtime=LocalDateTime.now();
		String time=localtime.toString();
		resource.setUserid(userid);
		resource.setUsername(username);
		resource.setFilename(filename);
		resource.setDescrib(describe);
		resource.setFilesize(filesize);
		resource.setFilesrc(filesrc);
		resource.setTime(time);
		try {
			int status=resourceService.addResource(resource);
			tips.put("code", 0);
			tips.put("msg", "上传成功");
		} catch (Exception e) {
			// TODO: handle exception
			tips.put("code", 2);
			tips.put("msg", "上传文件失败");
		}
		return JSONObject.toJSONString(tips);
	}
	
	/**
	 * 显示资源列表
	 * @param page
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/reslist",method=RequestMethod.GET)
	public String ResList(Page page,Model model,HttpSession session)
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
		List<Resource> resList=null;
		int limit=10;//每页显示的条数
		//开启分页，从start开始查找五条记录
		PageHelper.offsetPage(page.getStart(), limit);
		switch (by) {
		case "describe":
			resList= resourceService.resListByDescribe(value);
			break;
		case "uid":
			resList= resourceService.resListByUserid(value);
			break;
		default:
			resList= resourceService.resListByAll();
			break;
		}
		//总的记录条数
		int total = (int) new PageInfo<>(resList).getTotal();		
		//设置最后一页第一条记录
		page.setCount(limit);
		page.caculateLast(total);
		model.addAttribute("total", total);
		model.addAttribute("limit", limit);
		model.addAttribute("reslist", resList);
		return "reslist";
	}
	
	/**
	 * 下载资源文件
	 * @param req
	 * @param res
	 * @param session
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/download",method=RequestMethod.GET)
	public String download(HttpServletRequest req,HttpServletResponse res,HttpSession session) throws ServletException, IOException
	{
		String resid=req.getParameter("resid");
		Resource resource=resourceService.getResourceByResid(resid);
		String filename=resource.getFilename();
		String filesrc=resource.getFilesrc();
		res.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filename, "utf-8"));
		InputStream input=new FileInputStream(req.getServletContext().getRealPath("WEB-INF/"+filesrc));
		OutputStream output=res.getOutputStream();
		IOUtil.inToOut(input, output);
		input.close();
		return "download begin";
	}
}
