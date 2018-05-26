package com.yang.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yang.bean.Progress;

public class FormDataUtil {
	private HttpServletRequest req;
	private String name=null;
	private Map<String,Object> formdata=new HashMap<>();
	private List<String> srclist=new ArrayList<>();
	private LocalDateTime localtime=LocalDateTime.now();
			
	public FormDataUtil(HttpServletRequest request) {
		this.req=request;
	}
	public FormDataUtil(String name,HttpServletRequest request) {
		this.name=name;
		this.req=request;
	}
	public Map<String,Object> getFormData()
	{
		//创建一个工厂
		DiskFileItemFactory factory=new DiskFileItemFactory();
		//创建临时文件夹，用来存储上传文件时产生的临时文件
		factory.setRepository(new File(req.getServletContext().getRealPath("WEB-INF/temp")));
		//生产文件上传核心类
		ServletFileUpload fileupload=new ServletFileUpload(factory);
		//boolean isMultipart= fileupload.isMultipartContent(request)//判断上传表单是否为multipart/form-data类型
		//fileupload.setFileSizeMax(long fileSizeMax) 设置单个上传文件的最大值
		//fileupload.setSizeMax(long sizeMax) 设置上传文件总量的最大值
		//设置编码集，防止上传中文文件乱码问题
		fileupload.setHeaderEncoding("utf-8");
		try {
			//利用ServletFileUpload类的parseRequest(Request req)解析request请求，返回值是一个FileItem类型的list
			List<FileItem> list=fileupload.parseRequest(this.req);
			//遍历FileTiem，调用其isFormField方法判断是否是上传文件
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i).isFormField())
				{
					//True 为普通表单字段，则调用getFieldName、getString(String	encoding)方法得到字段名和字段值,可以用encoding进行编码设置,防止中文乱码
					String name=list.get(i).getFieldName();
					String value=list.get(i).getString();
					formdata.put(name, value);
				}else {
					//False 为上传文件，则调用getInputStream方法得到数据输入流，从而读取上传数据。
					//获取当前日期
					String data=localtime.toLocalDate().toString();
					//getName()方法获取上传文件名
					String filename=list.get(i).getName();
					//保存路径
					String path=req.getServletContext().getRealPath("WEB-INF/uploadfile/")+data+"/"+name;
					//创建文件夹
					new File(path).mkdirs();
					path=path+"/"+filename;
//					System.out.println(path);
					InputStream in=list.get(i).getInputStream();
					OutputStream out=new FileOutputStream(path);
					IOUtil.inToOut(in,out);
					IOUtil.closeStream(in, out);
					//删除临时文件
					list.get(i).delete();
					//将路径加入集合
					srclist.add("uploadfile/"+data+"/"+name+"/"+filename);				
				}
			}
		} catch (FileUploadException|IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
		//监听文件上传状态
		fileupload.setProgressListener(new ProgressListener() {
			
			@Override
			public void update(long bytesRead, long contentLength, int items) {
				Progress progress = null;
				long beginTime=System.currentTimeMillis();
				// TODO Auto-generated method stub
				//除以1024并四舍五入转换为KB
				BigDecimal br=new BigDecimal(bytesRead).divide(new BigDecimal(1024), 2, BigDecimal.ROUND_HALF_UP);
				BigDecimal cl=new BigDecimal(contentLength).divide(new BigDecimal(1024), 2, BigDecimal.ROUND_HALF_UP);
				//剩余大小
				BigDecimal ll=cl.subtract(br);
				//已上传百分比
				BigDecimal per=br.multiply(new BigDecimal(100)).divide(cl, 2, BigDecimal.ROUND_HALF_UP);
				//上传速度
				long nowTime=System.currentTimeMillis();
				long useTime=(nowTime-beginTime)/1000;
				BigDecimal speed=new BigDecimal(0);
				BigDecimal lt=new BigDecimal(0);
				if(useTime!=0) {
				speed=br.divide(new BigDecimal(useTime), 2, BigDecimal.ROUND_HALF_UP);
				}
				//大概剩余时间
				if(!speed.equals(new BigDecimal(0)))
				{
				lt=ll.divide(speed, 0, BigDecimal.ROUND_HALF_UP);
				}
				//将信息加入progress
				progress.setLsize(ll.toString());//剩余大小
				progress.setLtime(lt.toString());//剩余时间
				progress.setProgress(per.toString());//当前进度
				progress.setSpeed(speed.toString());//当前速度
				//将进度信息加入session
				req.getSession().setAttribute("Progress", progress);
			}			
		});
		formdata.put("src", srclist);
		return formdata;
	}
}
