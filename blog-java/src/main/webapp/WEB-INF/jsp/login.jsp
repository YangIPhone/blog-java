<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- Head -->
<head>

	<title>登录</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<link rel="stylesheet" href="layui/css/layui.css">
	<link rel="icon" type="image/png" href="image/favicon.png">
	<script type="text/javascript" src="layui/layui.js"></script>
	<script type="text/javascript" src="layui/layui.all.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
</head>
<!-- //Head -->

<!-- Body -->
<body style="background: #555">
<div class="layui-main" style="width:500px; margin-top:10%;text-align:center;" id="div1"  hidden>
<img  src="image/logoa.png">
<form class="layui-form layui-form-pane" style="margin-top:25px;" id="form" method="post">
  <div class="layui-form-item" pane> 
    <label class="layui-form-label"><i class="layui-icon layui-icon-user">用户名</i>   </label>
    <div class="layui-input-block">
      <input type="text" name="username" id="username" required  placeholder="请输入用户名" autocomplete="off" class="layui-input">
    </div>
  </div>
  <div class="layui-form-item" pane>
    <label class="layui-form-label"><i class="layui-icon layui-icon-circle-dot">密码</i></label>
    <div class="layui-input-block">
      <input type="password" name="password" id="password" required  placeholder="请输入密码" autocomplete="off" class="layui-input">
    </div>
  </div>
</form>
	<div class="layui-form-item" pane> 
     <button class="layui-btn layui-btn-fluid" id="login">登录</button>
     <a style="position: absolute;right:0px;color: #fff;">立即加入</a>
     
   </div>
</div>
<script type="text/javascript">
var div = $('#div1');
div.fadeIn(5000);

$('#login').click(function(){
	var username=$('#username').val();
	var password=$('#password').val();
	var url="login";
	if(username!=""&&password!="")
		{
		$.post(url,{username:username,password:password},function(data){
			if(data.status=="NO")
				{
				layer.open({
					 title: '提示',
					 content: data.msg,
					});   
				}else {
					if(data.status=="YES")
						{
						location.href="index";
						}
				}
			});
		}else{
			layer.open({
				 title: '提示',
				 content: '请填写用户名和密码',
				});   
		}	
});
</script>
</body>
</html>