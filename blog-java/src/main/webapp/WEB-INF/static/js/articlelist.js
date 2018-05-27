var element = layui.element;
element.render('nav');//重新对导航进行渲染。
var startp="${page.start-page.count}";
var startn="${page.start+page.count}";
var pagecode=document.getElementById("pagecode");
for(var i=0,page=1;i<"${total}";i+="${limit}",page++){
	var url="?by=${page.by}&value=${page.value}&start="+i;
	pagecode.innerHTML+="<a class='layui-btn layui-btn-primary layui-btn' href='"+url+"'>第"+page+"页</a>";
}
