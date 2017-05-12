$(document).ready(function() {
	//请求菜单
	$.ajax({
        type: "POST",
        url: appName+"/user/findResourceByUserid/"+"admin"+".htmls",
        dataType:"json",
        success: function(data){
        	$(data).each(function(index,obj){
        		//菜单数据返回格式[{菜单组名称:[{sysResource对象1}]}]
        		for(var key in obj){
        			var menu_group_div = $('<div></div>');
        			var menus = obj[key];
        			$(menus).each(function(num,resource){
        				var menudiv =  $('<div onclick="index_selectMeanu(\''+resource.resourcename+'\',\''+resource.resourcevalue+'\')">'+resource.resourcename+'</div>');
        				menu_group_div.append(menudiv);
        			});
        			$('#index_main_menu').accordion('add', {title: key,content: menu_group_div,selected: false});
        		}
        	});
        }
     });
});

//选择菜单
function index_selectMeanu(title,url){
	$('#index_main_panel').panel({
	    href:appName+url,
	    title:title
	});
}



