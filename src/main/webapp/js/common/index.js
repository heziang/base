$(document).ready(function() {
	$('#index_main_menu').accordion({
			onSelect:index_selectMeanu
		});
	$('#index_main_tab').tabs({
		    border:false
		});
});

function index_selectMeanu(title,index){
	var tab = {
		    title:title,
		    href:appName+'/user/list.htmls',
		    closable:true,
		    tools:[{
		        iconCls:'icon-mini-refresh',
		        handler:function(){
		            alert('refresh');
		        }
		    }]
		}
	$('#index_main_tab').tabs('add',tab);
}



