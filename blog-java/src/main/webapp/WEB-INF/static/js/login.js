var div = $('#div1');
div.slideDown(3000);
//div.show(3000);
//div.fadeIn(3000);

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