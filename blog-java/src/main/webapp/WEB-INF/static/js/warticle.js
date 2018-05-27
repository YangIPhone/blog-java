var element = layui.element;
var form=layui.form;
element.render('nav');//重新对导航进行渲染。
form.render('select'); //刷新select选择框渲染
var layedit = layui.layedit;
layedit.set({
	  uploadImage: {
	    url: 'articleimg' //接口url
	    ,type: 'post' //默认post
	  }
	});
var text=layedit.build('demo',{
	 height: 500//设置编辑器高度
}); //建立编辑器
$('#submit').click(function(){
	var title=$('#title').val();
	var type=$('#type').val();
	var content=layedit.getContent(text);
	var userid=$('#userid').val();
	var username=$('#username').val();
	var time=$('#time').val();
	var url="warticle";
	if(title==""){
		layer.open({
			 title: '提示',
			 content: "请输入文章标题",
			});
		return;
	}
	if(type=="")
	{
		layer.open({
			 title: '提示',
			 content: "请选择文章类型",
			}); 
		return;
	}
	if(content=="")
	{
		layer.open({
			 title: '提示',
			 content: "至少写点什么吧",
			}); 
		return;
	}
	$.post(url,{title:title,content:content,userid:userid,username:username,time:time,type:type},
			function(data)
			{if(data.status=="YES")
				{
				layer.open(
					{
					 title: '提示',
					 content: data.msg,
					});
				location.href="index";
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