<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>§流い年§博客社区</title>
	<style>
	#headimg{width:92px;margin-bottom: 10px;}
	</style>
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
            <dd><a href="articlelist?by=uid&value=${sessionScope.userid }">我的博客</a></dd>
            <dd><a href="reslist?by=uid&value=${sessionScope.userid }">我的资源</a></dd>
          </dl>
        </li><li class="layui-nav-item"><a href="warticle?userid=${sessionScope.userid }"><i class="layui-icon">&#xe642;</i>写博客</a></li>
        <li class="layui-nav-item"><a href="uploadres"><i class="layui-icon">&#xe681;</i>上传资源</a></li>
        <li class="layui-nav-item"><a href="queandans"><i class="layui-icon">&#xe63a;</i>我要提问</a></li>
      </ul>
    </div>
  </div>

<div class="layui-body" style="background: #555" >
    <!-- 内容主体区域 -->
  <div style="padding: 15px;" >
   	<form class="layui-form layui-form-pane" id="form">
    <div class="layui-form-item" pane >
   	<label class="layui-form-label">昵称:</label>
		<div class="layui-input-block">
      		<input type="text" name="username" id="username" value="${user.username}" autocomplete="off" class="layui-input">
    	</div>
  	</div>
    <div class="layui-form-item" pane >
   		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px; color: #fff">
  			<legend>上传头像</legend>
		</fieldset>
 
		<div class="layui-upload">
  			<button type="button" class="layui-btn" id="chooseimg">选择头像</button>
  			<div class="layui-upload-list">
    			<img class="layui-upload-img" id="headimg">
    			<p id="Text">
    				<button type="button" class="layui-btn" id="upload">修改信息</button>
    			</p>
  			</div>
		</div> 
  	</div>
    </form>
  </div>
</div>
  
  <div class="layui-footer" style="text-align:center;">
    <!-- 底部固定区域 -->
    © §流い年§ Blog <a href="http://www.miibeian.gov.cn/">渝ICP备17008739号-1</a>
  </div>
</div>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/basicinfor.js"></script>
</body>
</html>