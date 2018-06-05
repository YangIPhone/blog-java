var layedit = layui.layedit;
layedit.set({
	  uploadImage: {
	    url: 'articleimg' //接口url
	    ,type: 'post' //默认post
	  }
	});
var text=layedit.build('demo',{
	 tool: ['strong', 'italic', 'underline', 'del', 'link','unlink','image'],
	 height: 300//设置编辑器高度
}); //建立编辑器

$('#submit').click(function(){
	var answer=layedit.getContent(text);
	var userid=$('#userid').val();
	var username=$('#username').val();
	var quesid=$('#quesid').val();
	var url="addanswer";
	if(answer==""){
		layer.open({
			 title: '提示',
			 content: "请秀出你的回答",
			});
		return;
	}
	$.post(url,{answer:answer,userid:userid,username:username,quesid:quesid},
			function(data)
			{
				location.href="question?quesid="+quesid;
			});
});