<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>§流い年§博客社区</title>
<style>
#articlecontent{color: #fff;}
#articlecontent img{width: 200px;}
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
  <div class="layui-col-md9">
   <font size="5px" color="#fff">最新文章</font> 
	<hr class="layui-bg-red">
	<fieldset class="layui-elem-field">
  		<legend><font size="5px" color="#fff">${article.title}</font></legend>
  			<div id="articlecontent">
    		${article.content}
  			</div>
	</fieldset>
	  		<div style="color: #fff;">
    		<span><i class="layui-icon">&#xe66f;</i>作者:${article.username}</span>
    		<span style="margin-left: 50px;"><i class="layui-icon">&#xe6c6;</i>${article.clicknum}人已赞</span>
    		<span style="margin-left: 50px;"><i class="layui-icon">&#xe66e;</i>文章类型:${article.type}</span>
    		<span style="margin-left: 50px;"><i class="layui-icon">&#xe637;</i>发布日期:${article.time}</span>
  			</div>
    </div>
  </div>
</div>
  
  <div class="layui-footer" style="text-align:center;">
    <!-- 底部固定区域 -->
    © §流い年§ Blog <a href="http://www.miibeian.gov.cn/">渝ICP备17008739号-1</a>
  </div>
</div>
<script type="text/javascript">
var element = layui.element;
element.render('nav');//重新对导航进行渲染。
</script>
</body>
</html>