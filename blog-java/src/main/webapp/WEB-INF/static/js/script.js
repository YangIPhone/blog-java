	function sub(that)
	{
	var tip=$('#tip');
	var url="login";
	var parent=that.parentNode;//按钮的父级元素————即将提交的表单
	var oData=new FormData(parent);
	//$.post(url,{Nickname:"oData"},function(data){console.log("data")});
	$.ajax({
	  url:url,
	  type:'POST',
	  data:oData, 
	  dataType:'json',
      processData: false,  
      contentType: false,
	  success:function(res)
	  {
	  	tip.html(res.Nickname);
//	   console.log(res);
	  }
	});
	}

		function getbackpwd()
		{
			var login=document.getElementById('login');
			var getbackpwd=document.getElementById('getbackpwd');
			login.style.display='none';
			getbackpwd.style.display='block';
		}

		function cancel()
		{
			var login=document.getElementById('login');
			var getbackpwd=document.getElementById('getbackpwd');
			login.style.display='block';
			getbackpwd.style.display='none';
		}

		function gbp(that)
		{
		var tip2=$('#tip2');
		var url="/verifyUser";
    	var parent=that.parentNode;//按钮的父级元素————即将提交的表单
    	var oData=new FormData(parent);
    	$.ajax({
		  url:url,
		  type:'POST',
		  data:oData, 
		  dataType:'json',
          cache: false,  
          contentType: false,  
          processData: false,  
		  success:function(res)
		  {
		  	tip2.html(res.errmsg);
		  }
		});
		}