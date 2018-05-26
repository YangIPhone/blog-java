<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDateTime"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>§流い年§博客社区</title>
	<link rel="stylesheet" href="layui/css/layui.css">
	<link rel="icon" type="image/png" href="image/favicon.png">
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="layui/layui.all.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <a href="index"><div class="layui-logo">§流い年§博客社区</div></a>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    
    <ul class="layui-nav layui-layout-left">
    <li class="layui-nav-item"><a href="index">博客首页</a></li>
    <li class="layui-nav-item"><a href="">资源专区</a></li>
    <li class="layui-nav-item"><a href="">问答社区</a></li> 
    <li class="layui-nav-item">
        <a href="javascript:;">文章论坛</a>
        <dl class="layui-nav-child">
          <dd><a href="">技术博客</a></dd>
          <dd><a href="">心情随笔</a></dd>
          <dd><a href="">生活琐事</a></dd>
        </dl>
      </li>
                 
    </ul>
    
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
         ${sessionScope.username}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item">
    <a href="">消息中心<span class="layui-badge">9</span></a>
  </li>
      <li class="layui-nav-item"><a href="">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">        
        <li class="layui-nav-item">
          <a class="" href="javascript:;"><i class="layui-icon">&#xe66f;</i>个人空间</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">专属相册</a></dd>
            <dd><a href="javascript:;">留言列表</a></dd>
            <dd><a href="javascript:;">我的博客</a></dd>
            <dd><a href="">我的资源</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href=""><i class="layui-icon">&#xe642;</i>写博客</a></li>
        <li class="layui-nav-item"><a href=""><i class="layui-icon">&#xe681;</i>上传资源</a></li>
        <li class="layui-nav-item"><a href=""><i class="layui-icon">&#xe63a;</i>我要提问</a></li>
      </ul>
    </div>
  </div>

<div class="layui-body" style="background: #555" >
    <!-- 内容主体区域 -->
  <div style="padding: 15px;" >
  <form class="layui-form layui-form-pane" id="article">
  <div class="layui-form-item" pane> 
    <label class="layui-form-label">标题</i>   </label>
    <div class="layui-input-block">
      <input type="text" name="title" id="title" required  placeholder="请输入文章标题" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item" >
   <label class="layui-form-label">文章类型</label>
   <div class="layui-input-block">
  <select name="type" id="type" lay-verify="">
  <option value="">请选择文章类型</option>
  <option value="技术博客">技术博客</option>
  <option value="心情随笔">心情随笔</option>
  <option value="生活琐事">生活琐事</option>
</select> 
    </div>
  </div>
  <input type="text" name="userid" id="userid" value="${sessionScope.userid}" hidden/>
  <input type="text" name="username" id="username" value="${sessionScope.username}" hidden/>
  <%LocalDateTime localtime=LocalDateTime.now();%>
  <input type="datetime-local" name="time" id="time" value="<%=localtime.toString() %>" hidden/>
  <textarea id="demo" name="content" style="display: none;"></textarea>
  </form>
     <button class="layui-btn" id="submit">发布</button>
  </div>
</div>	
  
  <div class="layui-footer" style="text-align:center;">
    <!-- 底部固定区域 -->
    © §流い年§ Blog <a href="http://www.miibeian.gov.cn/">渝ICP备17008739号-1</a>
  </div>
</div>
<script type="text/javascript">
var element = layui.element;
var form=layui.form;
element.render('nav');//重新对导航进行渲染。
form.render('select'); //刷新select选择框渲染
var layedit = layui.layedit;
layedit.set({
	  uploadImage: {
	    url: 'articleimg' //接口url
	    ,type: 'post' //默认post
	  }
	});
var text=layedit.build('demo',{
	 height: 500//设置编辑器高度
}); //建立编辑器
$('#submit').click(function(){
	var title=$('#title').val();
	var type=$('#type').val();
	var content=layedit.getContent(text);
	var userid=$('#userid').val();
	var username=$('#username').val();
	var time=$('#time').val();
	var url="warticle";
	if(title==""){
		layer.open({
			 title: '提示',
			 content: "请输入文章标题",
			});
		return;
	}
	if(type=="")
	{
		layer.open({
			 title: '提示',
			 content: "请选择文章类型",
			}); 
		return;
	}
	if(content=="")
	{
		layer.open({
			 title: '提示',
			 content: "至少写点什么吧",
			}); 
		return;
	}
	$.post(url,{title:title,content:content,userid:userid,username:username,time:time,type:type},
			function(data)
			{if(data.status=="YES")
				{
				layer.open(
					{
					 title: '提示',
					 content: data.msg,
					});
				location.href="index";
				}else 
				{
					if(data.status=="NO")
					{
						layer.open({
							 title: '提示',
							 content: data.msg,
							});						
					}
				}
			});
});
</script>
</body>
</html>