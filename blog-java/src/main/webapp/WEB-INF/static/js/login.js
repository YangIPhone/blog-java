var div = $('#div1');
div.slideDown(3000);
//div.show(3000);
//div.fadeIn(3000);

//登录信息验证
$('#login').click(function(){
	var userid=$('#userid').val();
	var password=$('#password').val();
	var url="login";
	if(userid!=""&&password!="")
		{
		$.post(url,{userid:userid,password:password},function(data){
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
//发送手机验证码
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

$('#backpwd').click(function(){
	var url="backpwd";
	var phone=$('#phone').val();
	var phonecode=$('#phonecode').val();
	var password=$('#backpassword').val();	
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
	$.post(url,{phone:phone,phonecode:phonecode,password:password},
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

//隐藏登录块，显示找回密码块
function getpwd()
{
	var login=document.getElementById('div1');
	var getbackpwd=document.getElementById('getpwd');
	login.style.display='none';
	getbackpwd.style.display='block';
}

//隐藏找回密码块并显示登录块
function cancel()
{
	var login=document.getElementById('div1');
	var getbackpwd=document.getElementById('getpwd');
	login.style.display='block';
	getbackpwd.style.display='none';
}

//单击图片刷新验证码
function refreshimg(img) {
	img.src="imgcode?time="+new Date().getTime();
}