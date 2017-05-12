<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userInfo</title>
</head>
<body>
    <ul id="usergrouptree" class="easyui-tree" data-options="url:'<%=getServletContext().getContextPath() %>/usergroup/searchByParent.htmls',onClick:clickUserGroupTree,onContextMenu: openOrgMenu">
	    
	</ul>
	<div id="orgrightmenu" class="easyui-menu" data-options="onClick:usermenuHandler" style="width:120px;">
    	<div data-options="name:'add',iconCls:'icon-add'">增加</div>
   	    <div data-options="name:'edit',iconCls:'icon-edit'">编辑</div>
        <div data-options="name:'delete',iconCls:'icon-remove'">删除</div>
        <div class="menu-sep"></div>
        <div data-options="name:'refresh'">刷新</div>
    </div>
    
    <div id="addUserGroupWindow" class="easyui-window" title="增加用户组" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
	</div>
	<div id="editUserGroupWindow" class="easyui-window" title="编辑用户组" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
	</div>
    <script type="text/javascript">
	    $(document).ready(function() {
			
		});
	    
		function usermenuHandler(item){
			switch(item.name){
				case 'add':addUserGroup();break;
				case 'edit':editUserGroup();break;
				case 'delete':deleteUserGroup();break;
				default: refreshNode();
			}
		}
		
		function openOrgMenu(e,node){
			e.preventDefault();
			$(this).tree('select',node.target);
			$('#orgrightmenu').menu('show',{left: e.pageX,top: e.pageY});
		}
		
		function addUserGroup(){
			var node = $('#usergrouptree').tree('getSelected');
			$('#addUserGroupWindow').window({href:appName + "/usergroup/add/"+node.id+".htmls"});
			$('#addUserGroupWindow').window('open');
		}
		
		function editUserGroup(){
			var node = $('#usergrouptree').tree('getSelected');
			$('#editUserGroupWindow').window({href:appName + "/usergroup/edit/"+node.id+".htmls"});
			$('#editUserGroupWindow').window('open');
		}
		
		function refreshNode(){
			var node = $('#usergrouptree').tree('getSelected');
			$("#usergrouptree").tree("reload",node.target);
		}
		
		//如果有父节点，刷新父节点，否则刷新本节点
		function refreshParentNode(){
			var node = $('#usergrouptree').tree('getSelected');
			var parentNode = $("#usergrouptree").tree("getParent",node.target);
			if(parentNode){
				$("#usergrouptree").tree("reload",parentNode.target);
			}else{
				$("#usergrouptree").tree("reload",node.target);
			}
		}
		
		function deleteUserGroup(){
			$.messager.confirm('操作确认','确认操作?',function(r){
				if (r){
					var node = $('#usergrouptree').tree('getSelected');
					if(node.id){
						$.ajax({
				               type: "POST",
				               url: appName+"/usergroup/delete.htmls",
				               data: {groupids:node.id},
				               success: function(data){
				            	    refreshParentNode();
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