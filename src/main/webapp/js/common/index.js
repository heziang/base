$(document).ready(function() {
});

function index_selectMeanu(title,index){
	
	if($('#main-tab').tabs('exists', title)){
		$('#main-tab').tabs('select', title); 
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



