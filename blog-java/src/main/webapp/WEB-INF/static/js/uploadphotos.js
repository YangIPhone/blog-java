var upload = layui.upload;
var form=layui.form;
form.render('select');
var url="uploadphotos";
var album=$('#album').val();
upload.render({	
    elem: '#imgs'
    ,url: url
    ,data:{album:album}
    ,auto:false
    ,bindAction:'#upload'
    ,multiple: true
    ,choose: function(obj){  	
      //预读本地文件示例，不支持ie8
      files = obj.pushFile();
      obj.preview(function(index, file, result){
        $('#preview').append('<img src="'+ result +'" alt="'+ file.name +'" class="layui-upload-img">');  
      });
    }
    ,done: function(res){
    	if(res.code==0)
		{
		layer.open({
			 title: '提示',
			 content: res.msg+"，3秒后自动跳转到相册",
			});
		setTimeout(function() {
			location.href="album";
		}, 3000);
		}
    }
  });