<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>roleGroup</title>
</head>
<body>
    <ul id="rolegrouptree" class="easyui-tree" data-options="url:'<%=getServletContext().getContextPath() %>/rolegroup/searchByParent.htmls',onClick:clickRoleGroupTree,onContextMenu: openRoleMenu">
	    
	</ul>
	<div id="rolerightmenu" class="easyui-menu" data-options="onClick:rolemenuHandler" style="width:120px;">
    	<div data-options="name:'add',iconCls:'icon-add'">增加</div>
   	    <div data-options="name:'edit',iconCls:'icon-edit'">编辑</div>
        <div data-options="name:'delete',iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div data-options="name:'refresh'">刷新</div>
    </div>
    
    <div id="addRoleGroupWindow" class="easyui-window" title="增加角色组" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
	</div>
	<div id="editRoleGroupWindow" class="easyui-window" title="编辑角色组" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
	</div>
    <script type="text/javascript">
	    $(document).ready(function() {
			
		});
	    
		function rolemenuHandler(item){
			switch(item.name){
				case 'add':addRoleGroup();break;
				case 'edit':editRoleGroup();break;
				case 'delete':deleteRoleGroup();break;
				default: refreshParentNode();
			}
		}
		
		function openRoleMenu(e,node){
			e.preventDefault();
			$(this).tree('select',node.target);
			$('#rolerightmenu').menu('show',{left: e.pageX,top: e.pageY});
		}
		
		function addRoleGroup(){
			var node = $('#rolegrouptree').tree('getSelected');
			$('#addRoleGroupWindow').window({href:appName + "/rolegroup/add/"+node.id+".htmls"});
			$('#addRoleGroupWindow').window('open');
		}
		
		function editRoleGroup(){
			var node = $('#rolegrouptree').tree('getSelected');
			$('#editRoleGroupWindow').window({href:appName + "/rolegroup/edit/"+node.id+".htmls"});
			$('#editRoleGroupWindow').window('open');
		}
		
		function refreshNode(){
			var node = $('#rolegrouptree').tree('getSelected');
			$("#rolegrouptree").tree("reload",node.target);
		}
		//如果有父节点，刷新父节点，否则刷新本节点
		function refreshParentNode(){
			var node = $('#rolegrouptree').tree('getSelected');
			var parentNode = $("#rolegrouptree").tree("getParent",node.target);
			if(parentNode){
				$("#rolegrouptree").tree("reload",parentNode.target);
			}else{
				$("#rolegrouptree").tree("reload",node.target);
			}
		}
		
		function deleteRoleGroup(){
			$.messager.confirm('操作确认','确认操作?',function(r){
				if (r){
					var node = $('#rolegrouptree').tree('getSelected');
					if(node.id){
						$.ajax({
				               type: "POST",
				               url: appName+"/rolegroup/delete.htmls",
				               data: {groupids:node.id},
				               success: function(data){
					            	    if(data=='isRef'){
					            	    	$.messager.alert('操作结果','该组下存在自组或成员记录，不能被删除');
					            	    }else{
					            	    	refreshParentNode();
											$.messager.alert('操作结果','操作成功');
					            	    }
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