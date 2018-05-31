upload = layui.upload;
var filesize;
var filename;
//上传
  var demoListView = $('#file')
  ,uploadListIns = upload.render({
    elem: '#choosefile'
    ,url: 'uploadres'
    ,accept: 'file'
    ,auto: false
    ,bindAction: '#upload'
    ,choose: function(obj){
      //读取本地文件
      obj.preview(function(index, file, result){
    	  filename=file.name;
    	  filesize=(file.size/1014).toFixed(1);
        var tr = $(['<tr id="upload-'+ index +'">'
          ,'<td>'+ file.name +'</td>'
          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
          ,'<td>等待上传</td>'
          ,'<td>'
            ,'<button class="layui-btn layui-btn-mini demo-reload layui-hide">重传</button>'
            ,'<button class="layui-btn layui-btn-mini layui-btn-danger demo-delete">删除</button>'
          ,'</td>'
        ,'</tr>'].join(''));
        
        //重传
        tr.find('.demo-reload').on('click', function(){
          obj.upload(index, file);
        });
        
        //删除
        tr.find('.demo-delete').on('click', function(){
          delete file[index]; //删除对应的文件
          tr.remove();
          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
        });      
        demoListView.html(tr);
      });
    }
  
  	,before: function(obj){ 	
      //设置data参数
  	this.data.username=$('#username').val();
  	this.data.describe=$('#describe').val();
  	this.data.filename=filename;
   	this.data.filesize=filesize+"KB";
  	
    }
  	
    ,done: function(res, index, upload){
      if(res.code == 0){ //上传成功
        var tr = demoListView.find('tr#upload-'+ index)
        ,tds = tr.children();
        tds.eq(2).html('<span style="color: #5FB878;">上传成功</span>');
        tds.eq(3).html(''); //清空操作
        return delete this.files[index]; //删除文件队列已经上传成功的文件
      }else{
    	  layer.open({
   			 title: '提示',
   			 content: res.msg,
   			});
    	  this.error(index, upload);
      }    
    }
    
    ,error: function(index, upload){
      var tr = demoListView.find('tr#upload-'+ index)
      ,tds = tr.children();
      tds.eq(2).html('<span style="color: #FF5722;">上传失败</span>');
      tds.eq(3).find('.demo-reload').removeClass('layui-hide'); //显示重传
    }
  });