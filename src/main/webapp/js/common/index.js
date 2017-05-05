$(document).ready(function() {
});

function index_selectMeanu(title,url){
	if($('#index_main_tab').tabs('exists', title)){
		$('#index_main_tab').tabs('select', title); 
	}else{
		var tab = {
			    title:title,
			    href:appName+url,
			    closable:true,
			    tools:[{
			        iconCls:'icon-mini-refresh'
			    }]
			}
		$('#index_main_tab').tabs('add',tab);
	}
}



