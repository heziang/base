$(document).ready(function() {
});

function index_selectMeanu(title,index){
	
	if($('#index_main_menu').tabs('exists', title)){
		$('#index_main_menu').tabs('select', title); 
	}else{
		var tab = {
			    title:title,
			    href:appName+'/user/list.htmls',
			    closable:true,
			    tools:[{
			        iconCls:'icon-mini-refresh'
			    }]
			}
		$('#index_main_tab').tabs('add',tab);
	}
	  
}



