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
import com.yang.pojo.Album;
import com.yang.pojo.Photo;
import com.yang.service.AlbumService;
import com.yang.service.PhotosService;
import com.yang.util.FormDataUtil;

@Controller
public class PhotoController {
	@Autowired
	AlbumService albumService;
	@Autowired
	PhotosService photoService;
/**
 * 查看相册页面
 * @param req
 * @param session
 * @return
 */
@RequestMapping(value="/album",method=RequestMethod.GET)
	public String album(Model model,HttpSession session)
	{
	String userid=(String) session.getAttribute("userid");
	if(userid==null)
	{
		return "redirect:login";
	}
	List<Album> albumList=albumService.getAlbumList(userid);
	for(int i=0;i<albumList.size();i++)
	{
		String aname=albumList.get(i).getAname();
		//获取相册封面
		Photo photo=photoService.getAlbumCover(userid, aname);
		if(photo!=null) 
		{
			albumList.get(i).setAlbumcover(photo.getPhotosrc());
		}
	}
	model.addAttribute("albumList", albumList);
	return "album";
	}

/**
 * 新建相册页面
 * @param req
 * @param session
 * @return
 */
@RequestMapping(value="/newalbum",method=RequestMethod.GET)
	public String newalbum(Model model,HttpServletRequest req,HttpSession session)
	{
	String userid=(String) session.getAttribute("userid");
	if(userid==null)
	{
		return "redirect:login";
	}
	String aname=req.getParameter("aname");
	if(aname==null) {
		return "newalbum";	
	}	
	Album album=albumService.getAlbumListByaname(userid, aname);
	model.addAttribute("album", album);
	return "newalbum";
	}

/**
 * 创建一个相册
 * @param req
 * @param session
 * @return
 */
@ResponseBody
	@RequestMapping(value="/calbum",method=RequestMethod.POST)
	public String calbum(Album album,HttpServletRequest req, HttpSession session)
	{
	String oldname=req.getParameter("oldname");
	String userid=album.getUserid();
	int status=0;
	if("".equals(oldname)) {
		//设置默认封面
		album.setAlbumcover("image/logoa.png");
		//新建相册
		status=albumService.createAlbum(album);
	}else {
		//更新相册信息
		status=albumService.upDateAlbum(album.getAname(),album.getAdescribe(), oldname,userid);
	}
	Map<String, Object> tips =new HashMap<>();
	if(status==1)
	{
		tips.put("status", "YES");
		tips.put("msg", "创建成功");
	}else {
		tips.put("status", "NO");
		tips.put("msg", "创建失败");
	}			
	return JSONObject.toJSONString(tips);
	}

/**
 * 上传照片页面
 * @param model
 * @param session
 * @return
 */
@RequestMapping(value="/uploadphotos",method=RequestMethod.GET)
	public String uploadPhotos( Model model,HttpServletRequest req,HttpSession session)
	{
	String userid=(String) session.getAttribute("userid");
	if(userid==null)
	{
	return "redirect:login";
	}
	String album=req.getParameter("album");
	model.addAttribute("album", album);
	return "uploadphotos";
	}
	
/**
 * 上传照片接口
 * @param req
 * @param model
 * @param session
 * @return
 */
@ResponseBody
@RequestMapping(value="/uploadphotos",method=RequestMethod.POST)
public String uploadPhotospost( HttpServletRequest req,Model model,HttpSession session)
{	
	Photo photo=new Photo();
	String name=(String) session.getAttribute("userid");
	String filedir="photo";
	FormDataUtil formdata=new FormDataUtil(name, filedir, req);
	//datamap：包含表单数据、文件路径(List)及文件名(List)
	Map<String,Object> datamap= formdata.getFormData();
	//返回的json提示
	Map<String, Object> tips =new HashMap<>();
	//用户ID
	String userid=(String) session.getAttribute("userid");
	//用户名
	String username=(String) session.getAttribute("username");
	//相册名
	String album=(String) datamap.get("album");
	//文件资源路径
	List<String> src=(List<String>) datamap.get("src");
	//文件名（包含后缀）
	List<String> filename=(List<String>) datamap.get("filenamelist");
	String photosrc=src.get(0);
	String photoname=filename.get(0);
	photo.setUserid(userid);
	photo.setUsername(username);
	photo.setAname(album);
	photo.setPhotosrc(photosrc);
	photo.setPhotoname(photoname);
	photoService.addPhoto(photo);
	tips.put("code", 0);
	tips.put("msg", "success");
	return JSONObject.toJSONString(tips);
}


/**
 * 查看照片页面
 * @param req
 * @param session
 * @return
 */
@RequestMapping(value="/photos",method=RequestMethod.GET)
	public String photos(Model model, HttpServletRequest req,HttpSession session)
	{
	String userid=(String) session.getAttribute("userid");
	if(userid==null)
	{
	return "redirect:login";
	}
	String aname=req.getParameter("aname");
	model.addAttribute("userid", userid);
	model.addAttribute("aname", aname);
	return "photos";
	}

/**
 * 获取对应相册的照片信息
 * @param req
 * @param session
 * @return json格式数据:[{"name": "照片路径", "caption": "照片名字"}]
 */
@ResponseBody
@RequestMapping(value="/getphotos",method=RequestMethod.POST)
	public String getphotos( HttpServletRequest req,HttpSession session)
	{
	List<Object> data =null;
	Map<String,String> photomsg=new HashMap<String,String>();
	String userid=req.getParameter("userid");
	String aname=req.getParameter("aname");
	List<Photo> photos=photoService.getPhotosByUidAndAname(userid,aname);
	return JSONObject.toJSONString(photos);
	}

}
