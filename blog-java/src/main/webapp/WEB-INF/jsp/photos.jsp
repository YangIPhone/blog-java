<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>§流い年§博客社区</title>
	<link rel="stylesheet" href="layui/css/layui.css">
	<link rel="stylesheet" type="text/css" href="css/normalize.css" />
	<link rel="stylesheet" type="text/css" href="css/htmleaf-demo.css">
	<link rel="stylesheet" type="text/css" href="css/polaroid-gallery.css"/>
	<link rel="icon" type="image/png" href="image/favicon.png">
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="layui/layui.all.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
</head>
<body class="layui-layout-body fullscreen">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <a href="index"><div class="layui-logo">§流い年§博客社区</div></a>
    <!-- 头部区域（可配合layui已有的水平导航） -->
    
    <ul class="layui-nav layui-layout-left">
    <li class="layui-nav-item"><a href="index">博客首页</a></li>
    <li class="layui-nav-item"><a href="reslist">资源专区</a></li>
    <li class="layui-nav-item"><a href="">问答社区</a></li> 
    <li class="layui-nav-item">
        <a href="javascript:;">文章论坛</a>
        <dl class="layui-nav-child">
         <dd><a href="articlelist?by=type&value=技术博客">技术博客</a></dd>
          <dd><a href="articlelist?by=type&value=心情随笔">心情随笔</a></dd>
          <dd><a href="articlelist?by=type&value=生活琐事">生活琐事</a></dd>
        </dl>
      </li>
                 
    </ul>
    
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="${sessionScope.headimg}" class="layui-nav-img">
         ${sessionScope.username}
        </a>
        <dl class="layui-nav-child">
          <dd><a href="basicinfor">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item">
    <a href="">消息中心<span class="layui-badge">9</span></a>
  </li>
      <li class="layui-nav-item"><a href="loginout">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">        
        <li class="layui-nav-item">
          <a class="" href="javascript:;"><i class="layui-icon">&#xe66f;</i>个人空间</a>
          <dl class="layui-nav-child">
            <dd><a href="album?userid=${sessionScope.userid }">专属相册</a></dd>
            <dd><a href="javascript:;">留言列表</a></dd>
            <dd><a href="articlelist?by=uid&value=${sessionScope.userid }">我的博客</a></dd>
            <dd><a href="reslist?by=uid&value=${sessionScope.userid }">我的资源</a></dd>
          </dl>
        </li><li class="layui-nav-item"><a href="warticle?userid=${sessionScope.userid }"><i class="layui-icon">&#xe642;</i>写博客</a></li>
        <li class="layui-nav-item"><a href="uploadres"><i class="layui-icon">&#xe681;</i>上传资源</a></li>
        <li class="layui-nav-item"><a href=""><i class="layui-icon">&#xe63a;</i>我要提问</a></li>
      </ul>
    </div>
  </div>

<div class="layui-body" style="background: #555" >
    <!-- 内容主体区域 -->
  <div style="padding: 15px;" >
  <input type="text"  id="userid" value="${userid}" hidden/>
  <input type="text"  id="aname" value="${aname}" hidden/>
   	<div id="gallery" class="fullscreen"></div>
	<div id="nav">
	    <button id="preview">&lt; 前一张</button>
	    <button id="next">下一张 &gt;</button>
	</div>
  </div>
</div>

  <div class="layui-footer" style="text-align:center;color:#000">
    <!-- 底部固定区域 -->
    © §流い年§ Blog <a style="color:#000" href="http://www.miibeian.gov.cn/">渝ICP备17008739号-1</a>
  </div>
</div>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/polaroid-gallery.js"></script>
	<script>
	    window.onload = function () {	    	
	        new polaroidGallery();
	    }
	</script>
</body>
</html>