<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <li class="layui-nav-item"><a href="reslist">资源专区</a></li>
    <li class="layui-nav-item"><a href="queslist">问答社区</a></li> 
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
            <dd><a href="queslist?by=uid&value=${sessionScope.userid }">我的提问</a></dd>
          </dl>
        </li><li class="layui-nav-item"><a href="warticle?userid=${sessionScope.userid }"><i class="layui-icon">&#xe642;</i>写博客</a></li>
        <li class="layui-nav-item"><a href="uploadres"><i class="layui-icon">&#xe681;</i>上传资源</a></li>
        <li class="layui-nav-item"><a href="queandans"><i class="layui-icon">&#xe63a;</i>我要提问</a></li>
      </ul>
    </div>
  </div>

<div class="layui-body" style="background: #555" >
    <!-- 内容主体区域 -->
  <div style="padding: 15px;"> 
  <div class="layui-row">
    <div class="layui-col-xs4">
      <div class="grid-demo">&nbsp;</div>
    </div>
    <div class="layui-col-xs4">
      <div class="grid-demo">
		<form class="layui-form layui-form-pane" action="reslist" method="get" style="width:100%;">
  		<div class="layui-form-item" pane> 
    	<label class="layui-form-label" >搜索</label>
    	<div class="layui-input-block">
    		<input type="text" name="by" value="describe" hidden="">
      		<input type="text" name="value" required  placeholder="请输入关键词" autocomplete="off" class="layui-input" style="float:left;width: 85%">
      		<button type="submit" class="layui-btn"  style="width: 15%" id="search"><i class="layui-icon">&#xe615;</i></button>    		    		
    	</div>
  		</div>
  	   </form>
	  </div>
    </div>
    <div class="layui-col-xs4">
      <div class="grid-demo"></div>
    </div>
  </div>
  
	<div class="layui-form news_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="15%">
				<col width="15%">
				<col width="10%">
				<col width="40%">
				<col width="15%">
				<col width="5%">
		    </colgroup>
		    <thead>
				<tr>
					<th>文件名</th>
					<th>上传资源用户</th>
					<th>文件大小</th>
					<th>资源描述</th>
					<th>上传时间</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="news_content">
		    	<c:forEach items="${reslist}" var="reslist">
		    	<tr>
		    		<td>${reslist.filename }</td>
      				<td>${reslist.username }</td>
      				<td>${reslist.filesize }</td>
      				<td id="content">${reslist.describ }</td>  
      				<td>${reslist.time }</td>
      				<td><a href="${reslist.filesrc}">下载</a></td>
    			</tr>
    			</c:forEach>
			</tbody>
		</table>
	</div>
	<div style="text-align:center" >
		<a class="layui-btn layui-btn-primary layui-btn" href="?by=${page.by}&value=${page.value}&start=0">首  页</a>
		<span id="pagecode"></span>
		<a class="layui-btn layui-btn-primary layui-btn" href="?by=${page.by}&value=${page.value}&start=${page.last}">末  页</a>
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
var startp=${page.start-page.count};
var startn=${page.start+page.count};
var pagecode=document.getElementById("pagecode");
for(var i=0,page=1;i<${total};i+=${limit},page++){
	var url="?by=${page.by}&value=${page.value}&start="+i;
	pagecode.innerHTML+="<a class='layui-btn layui-btn-primary layui-btn' href='"+url+"'>第"+page+"页</a>";
}
</script>
</body>
</html>