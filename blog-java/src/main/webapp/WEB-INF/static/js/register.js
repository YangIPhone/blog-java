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