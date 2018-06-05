var layedit=layui.layedit;

//设置编辑器图片接口
layedit.set({
	  uploadImage: {
	    url: 'articleimg' //接口url
	    ,type: 'post' //默认post
	  }
	});
	
//绑定编辑器
var text=layedit.build('questino',{
	 height: 500//设置编辑器高度
}); 

$('#submit').click(function(){
	var userid=$('#userid').val();
	var username=$('#username').val();
	var question=layedit.getContent(text);
	var questitle=$('#questitle').val();
	var time=$('#time').val();
	var url="addquestion";
	if(questitle==""){
		layer.open({
			 title: '提示',
			 content: "请输入问题标题",
			});
		return;
	}
	if(question=="")
	{
		layer.open({
			 title: '提示',
			 content: "具体说说什么问题吧",
			}); 
		return;
	}
	$.post(url,{questitle:questitle,question:question,userid:userid,username:username,time:time},
			function(data)
			{if(data.status=="YES")
				{
				layer.open(
					{
					 title: '提示',
					 content: data.msg,
					});
				setTimeout(function() {
					location.href="queslist";
				}, 3000);
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