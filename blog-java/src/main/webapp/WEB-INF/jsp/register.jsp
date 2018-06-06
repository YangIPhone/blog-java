<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- Head -->
<head>
	<title>§流い年§博客社区</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="layui/css/layui.css">
	<link rel="icon" type="image/png" href="image/favicon.png">
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="layui/layui.all.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>
<!-- //Head -->

<!-- Body -->
<body style="background: #555">
<div class="layui-main" style="width:500px; margin-top:10%;text-align:center;" id="div1">
<img  src="image/logoa.png">
<form class="layui-form layui-form-pane" style="margin-top:25px;" id="form" method="post">
  <div class="layui-form-item" pane> 
    <label class="layui-form-label"><i class="layui-icon layui-icon-username">昵称</i>   </label>
    <div class="layui-input-block">
      <input type="text" name="username" id="username" required  placeholder="请输入昵称" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item" pane> 
    <label class="layui-form-label"><i class="layui-icon layui-icon-user">账号</i>   </label>
    <div class="layui-input-block">
      <input type="text" name="userid" id="userid" required  placeholder="请输入账号" autocomplete="off" class="layui-input">
    </div>
  </div>
  
  <div class="layui-form-item" pane>
    <label class="layui-form-label"><i class="layui-icon">&#xe678;</i>手机</label>
    <div class="layui-input-block">
      <input type="text" name="phone" id="phone" required  placeholder="请输入手机号码" autocomplete="off" class="layui-input">
    </div>
  </div>
   <div class="layui-form-item" pane>
    <label class="layui-form-label">图片验证</label>
    <div class="layui-input-block">
      <input type="text" name="imgcode" id="imgcode" required  placeholder="请输入图片验证码" autocomplete="off" class="layui-input" style="float:left;width: 62%">
      <img src="imgcode" id="img" style="width: 38%;" onclick="refreshimg(this)">
    </div>
  </div>
   
  <div class="layui-form-item" pane>
    <label class="layui-form-label">手机验证</label>
    <div class="layui-input-block">
      <input type="text" name="phonecode" id="phonecode" required  placeholder="请输入手机验证码" autocomplete="off" class="layui-input" style="float:left;width: 65%">
      <button type="button" class="layui-btn"  style="width: 35%" id="getphonecode">获取手机验证码</button>
    </div>
  </div>
  
  <div class="layui-form-item" pane>
    <label class="layui-form-label"><i class="layui-icon layui-icon-password">密码</i></label>
    <div class="layui-input-block">
      <input type="password" name="password" id="password" required  placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
</form>
	<div class="layui-form-item" pane> 
     <button class="layui-btn layui-btn-fluid" id="register">注册</button>
     <a href="login" style="position: absolute;right:0px;color: #fff;">已有账号，登录</a>
   </div>
</div>

<script type="text/javascript">
function refreshimg(img) {
	img.src="imgcode?time="+new Date().getTime();
}

$('#getphonecode').click(function(){
	var url="sendphonecode";
	var imgcode=$('#imgcode').val();
	var phone=$('#phone').val();
	if(imgcode=="")
		{
		layer.open({
			 title: '提示',
			 content: "请先输入图片验证码",
			});
		return;
		}
	if(phone=="")
	{
	layer.open({
		 title: '提示',
		 content: "请先输入手机号",
		});
	return;
	}
	$.post(url,{imgcode:imgcode,phone:phone},
			function(data){
				layer.open({
					 title: '提示',
					 content: data.msg,
					});
	})
});

$('#register').click(function(){
	var url="";
	var username=$('#username').val();
	var userid=$('#userid').val();
	var phone=$('#phone').val();
	var phonecode=$('#phonecode').val();
	var password=$('#password').val();
	if(username=="")
		{
		layer.open({
			 title: '提示',
			 content: "昵称不能为空",
			});
		return;
		}
	
	if(userid=="")
	{
	layer.open({
		 title: '提示',
		 content: "账号不能为空",
		});
	return;
	}
	
	if(phonecode=="")
	{
	layer.open({
		 title: '提示',
		 content: "手机验证码不能为空",
		});
	return;
	}
	
	if(password=="")
	{
	layer.open({
		 title: '提示',
		 content: "密码不能为空",
		});
	return;
	}
	$.post(url,{username:username,userid:userid,phone:phone,phonecode:phonecode,password:password},
			function(data){
			if(data.code==0)
			{
				layer.open({
					 title: '提示',
					 content: data.msg+"，3秒后自动跳转到登录页面",
					});
					setTimeout(function() {
						location.href="login";
					}, 3000);
			}else{
				layer.open({
					 title: '提示',
					 content: data.msg,
					});
			}
			
	})
});
</script>
</body>
</html>