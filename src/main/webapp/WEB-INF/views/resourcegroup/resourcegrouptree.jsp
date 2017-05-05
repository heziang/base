<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>resourceGroup</title>
</head>
<body>
    <ul id="resourcegrouptree" class="easyui-tree" data-options="url:'<%=getServletContext().getContextPath() %>/resourcegroup/searchByParent.htmls',onClick:clickResourceGroupTree,onContextMenu: openOrgMenu">
	    
	</ul>
	<div id="orgrightmenu" class="easyui-menu" data-options="onClick:menuHandler" style="width:120px;">
    	<div data-options="name:'add',iconCls:'icon-add'">增加</div>
   	    <div data-options="name:'edit',iconCls:'icon-edit'">编辑</div>
        <div data-options="name:'delete',iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div data-options="name:'refresh'">刷新</div>
    </div>
    
    <div id="addResourceGroupWindow" class="easyui-window" title="增加用户组" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
	</div>
	<div id="editResourceGroupWindow" class="easyui-window" title="编辑用户组" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
	</div>
    <script type="text/javascript">
	    $(document).ready(function() {
			
		});
	    
		function menuHandler(item){
			switch(item.name){
				case 'add':addResourceGroup();break;
				case 'edit':editResourceGroup();break;
				case 'delete':deleteResourceGroup();break;
				default: refreshNode();
			}
		}
		
		function openOrgMenu(e,node){
			e.preventDefault();
			$(this).tree('select',node.target);
			$('#orgrightmenu').menu('show',{left: e.pageX,top: e.pageY});
		}
		
		function addResourceGroup(){
			var node = $('#resourcegrouptree').tree('getSelected');
			$('#addResourceGroupWindow').window({href:appName + "/resourcegroup/add/"+node.id+".htmls"});
			$('#addResourceGroupWindow').window('open');
		}
		
		function editResourceGroup(){
			var node = $('#resourcegrouptree').tree('getSelected');
			$('#editResourceGroupWindow').window({href:appName + "/resourcegroup/edit/"+node.id+".htmls"});
			$('#editResourceGroupWindow').window('open');
		}
		
		function refreshNode(){
			var node = $('#resourcegrouptree').tree('getSelected');
			$("#resourcegrouptree").tree("reload",node.target);
		}
		
		function deleteResourceGroup(){
			$.messager.confirm('操作确认','确认操作?',function(r){
				if (r){
					var node = $('#resourcegrouptree').tree('getSelected');
					if(node.id){
						$.ajax({
				               type: "POST",
				               url: appName+"/resourcegroup/delete.htmls",
				               data: {groupids:node.id},
				               success: function(data){
				            	    $("#resourcegrouptree").tree("reload",$("#resourcegrouptree").tree("getParent",node.target).target);
									$.messager.alert('操作结果','操作成功');
				                  }
				            });
					}else{
						$.messager.alert('提示','获取数据错误');
					}
				}
			});
		}
	</script>
</body>
</html>