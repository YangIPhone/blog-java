var username=$('#username').val();
//function c () {
//    var img= document.getElementById('headimg');
//    img.style.display="block";
//var r= new FileReader();
//f=document.getElementById('chooseimg').files[0];
//r.readAsDataURL(f);
//r.onload=function  (e) {
//img.src=this.result;
//};
//}

var upload = layui.upload;
var uploadInst = upload.render({
    elem: '#chooseimg'
    ,url: 'basicinfor'
    ,auto:false
    ,bindAction:'#upload'
    ,choose: function(obj){
      //预读本地文件示例，不支持ie8
      obj.preview(function(index, file, result){
        $('#headimg').attr('src', result); //图片链接（base64）
      });
    }
    ,before: function(obj){
        //设置data参数
    	this.data.username=$('#username').val();
      }
    ,done: function(res){
      //如果上传失败
      if(res.code==0){
    	  layer.open({
 			 title: '提示',
 			 content: res.msg+"，3秒后自动跳转到相册",
 			});
// 		setTimeout(function() {
// 			location.href="index";
// 		}, 3000);
      }else{ 
    	  layer.open({
  			 title: '提示',
  			 content: res.msg,
  			});
    	  }  
    }
    ,error: function(){
      //失败状态(如断网等)，并实现重传
      var demoText = $('#Text');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  });