var element = layui.element;
element.render('nav');//重新对导航进行渲染。
var layedit = layui.layedit;

$('#calbum').click(function(){
	var aname=$('#aname').val();
	var adescribe=$('#adescribe').val();
	var userid=$('#userid').val();
	var username=$('#username').val();
	var oldname=$('#oldname').val();
	var url="calbum";
	if(aname==""){
		layer.open({
			 title: '提示',
			 content: "请给相册起个名字",
			});
		return;
	}
	if(adescribe=="")
	{
		layer.open({
			 title: '提示',
			 content: "请描述一下这个相册是干嘛的",
			}); 
		return;
	}

	$.post(url,{aname:aname,adescribe:adescribe,userid:userid,username:username,oldname:oldname},
			function(data)
			{if(data.status=="YES")
				{
				layer.open(
					{
					 title: '提示',
					 content: data.msg,
					});
				location.href="album";
				}else 
				{
					if(data.status=="NO")
					{
						layer.open({
							 title: '提示',
							 content: data.msg,
							});						
					}
				}
			});
});