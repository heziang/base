$(document).ready(function() {
	loadMeanu();
	
});

//选择菜单
function index_selectMeanu(title,url){
	$('#index_main_panel').panel({
	    href:appName+url,
	    title:title
	});
}

//请求菜单
function loadMeanu(){
	$.ajax({
        type: "POST",
        url: appName+"/user/findUserResource.htmls",
        dataType:"json",
        success: function(data){
        	$(data).each(function(index,obj){
        		//菜单数据返回格式[{菜单组名称:[{sysResource对象1}]}]
        		for(var key in obj){
        			var menu_group_div = $('<div></div>');
        			var menus = obj[key];
        			$(menus).each(function(num,resource){
        				var menudiv =  $('<div onclick="index_selectMeanu(\''+resource.resourcename+'\',\''+resource.resourcevalue+'\')"><a>'+resource.resourcename+'</a></div>');
        				menu_group_div.append(menudiv);
        			});
        			$('#index_main_menu').accordion('add', {title: key,content: menu_group_div,selected: false});
        		}
        	});
        	$('#index_main_menu').accordion('select', 0);
        }
     });
}


