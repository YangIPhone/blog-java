<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<!-- Head -->
<head>

	<title>登录</title>

	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all">
	<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
	<script type="text/javascript" src="js/script.js"></script>
</head>
<!-- //Head -->

<!-- Body -->
<body>

	<h1>登录</h1>
    <div class="container w3layouts agileits" style="width: 30%;height: 30%;position: absolute; top: 30%;left: 30%;display: none;"
     id="getbackpwd">
    <div class="send-button w3layouts agileits">
			<form action="" method="post" target="hidden_iframe">
				<input type="text" Name="Email" placeholder="邮箱" required>
				<input type="password" Name="Password" placeholder="密码" required>				
				<input type="submit" onclick="gbp(this);" value="找回密码">
				<input type="submit" onclick="cancel();" value="取消">					
			</form>
			</div>
			<div class="clear" style="color: #fff" id="tip2"></div>
			</div>

	<div style="display: block;" id="login">
	<div class="container w3layouts agileits">
		<div class="login w3layouts agileits">
			<h2>登 录</h2>
			<form action="login" method="post">
				<input type="text" Name="Username" placeholder="昵称/邮箱" required="">
				<input type="password" Name="Password" placeholder="密码" required="">			
			<ul class="tick w3layouts agileits">
				<li>
					<input type="checkbox" id="brand1" name="remember" value="1">
					<label for="brand1"><span></span>30内记住我</label>
				</li>
			</ul>
			<div class="send-button w3layouts agileits">				
					<input type="submit" value="登 录">
				</form>
			</div>
			<a href="#" onclick="getbackpwd();">找回密码</a>
			<div class="social-icons w3layouts agileits">
				<p>- 其他方式登录 -</p>
				<ul>
					<li class="qq"><a href="#">
					<span class="icons w3layouts agileits"></span>
					<span class="text w3layouts agileits">QQ</span></a></li>
					<li class="weibo w3ls"><a href="#">
					<span class="icons w3layouts"></span>
					<span class="text w3layouts agileits">微博</span></a></li>
					<li class="baidu aits"><a href="#">
					<span class="icons agileits"></span>
					<span class="text w3layouts agileits">百度</span></a></li>
					<div class="clear"> </div>
				</ul>
			</div>
			<div class="clear"></div>
		</div>
		<div class="register w3layouts agileits">
			<h2>注 册</h2>
			<div class="send-button w3layouts agileits">
			<form method="post" id="form" target="hidden_iframe">
				<input type="text" Name="Nickname" placeholder="昵称" required="">
				<input type="text" Name="Email" placeholder="邮箱" required="">
				<input type="password" Name="Password" placeholder="密码" required="">	
				<input type="button" id="b1" onclick="sub(this);" value="免费注册">											
			</form>
			</div>
			<div class="clear" style="color: #fff" id="tip"></div>
		</div>
		<div class="clear"></div>
	</div>
	<iframe name="hidden_iframe" src="about:blank" style="display:none;"></iframe>
</body>
</html>