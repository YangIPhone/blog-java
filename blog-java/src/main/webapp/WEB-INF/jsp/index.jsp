<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>§流い年§博客社区</title>
	<style>
	#content img{height: 200px;position: relative;top:10px;}
	#content div{color: #fff;size:20px; overflow : hidden;min-height:35px;
				 text-overflow: ellipsis;
				 display: -webkit-box;
				 -webkit-line-clamp: 2;
				 -webkit-box-orient: vertical;}
	</style>
	<link rel="stylesheet" href="layui/css/layui.css">
	<link rel="icon" type="image/png" href="image/favicon.png">
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="layui/layui.all.js"></script>
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
            <dd><a href="warticle?userid=${sessionScope.userid }">留言列表</a></dd>
            <dd><a href="articlelist?by=uid&value=${sessionScope.userid }">我的博客</a></dd>
            <dd><a href="warticle?userid=${sessionScope.userid }">我的资源</a></dd>
          </dl>
        </li>
        <li class="layui-nav-item"><a href="warticle?userid=${sessionScope.userid }"><i class="layui-icon">&#xe642;</i>写博客</a></li>
        <li class="layui-nav-item"><a href="warticle?userid=${sessionScope.userid }"><i class="layui-icon">&#xe681;</i>上传资源</a></li>
        <li class="layui-nav-item"><a href="warticle?userid=${sessionScope.userid }"><i class="layui-icon">&#xe63a;</i>我要提问</a></li>
      </ul>
    </div>
  </div>

<div class="layui-body" style="background: #555" >
    <!-- 内容主体区域 -->
  <div style="padding: 15px;"  >
   <div class="layui-row">
   <div class="layui-col-md9" id="content">
   <font size="5px" color="#fff">最新文章</font> 
	<hr class="layui-bg-blue" style="height:8px;">
	<c:forEach items="${articlelist}" var="article">
	<fieldset class="layui-elem-field">
  		<legend><a href="article?articleid=${article.articleid}"><font size="5px" color="#fff">${article.title}</font></a></legend>
  			<div>
    		${article.content}
  			</div>
	</fieldset>
	  		<div>
    		<span><a href="articlelist?by=uid&value=${article.userid}" style="color: #FFF;"><i class="layui-icon">&#xe66f;</i>作者:${article.username}</a></span>
    		<span style="margin-left: 50px;"><a href="" style="color: #FFF;"><i class="layui-icon">&#xe6c6;</i></a>${article.clicknum}人已赞</span>
    		<span style="margin-left: 50px;"><a href="articlelist?by=type&value=${article.type}" style="color: #FFF;"><i class="layui-icon">&#xe66e;</i>文章类型:${article.type}</a></span>
    		<span style="margin-left: 50px;"><i class="layui-icon">&#xe637;</i>发布时间:${article.time}</span>
  			</div>
  			<hr class="layui-bg-red" style="height:2px;">
	</c:forEach>
    </div>
    
    <div class="layui-col-md3"  style="text-align:center;">
      <font size="5px" color="#fff">热门推荐</font> 
    </div>
   </div>
  </div>
</div>
  
  <div class="layui-footer" style="text-align:center;">
    <!-- 底部固定区域 -->
    © §流い年§ Blog <a href="http://www.miibeian.gov.cn/">渝ICP备17008739号-1</a>
  </div>
</div>
<script type="text/javascript" src="js/index.js"></script>
</body>
</html>