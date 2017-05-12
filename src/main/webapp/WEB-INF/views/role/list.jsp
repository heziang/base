<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色列表</title>
</head>
<body>
	
<div id="rolelistlayout" class="easyui-layout" style="height:100%;">
    <div data-options="region:'west',title:'角色组',split:true" style="width:20%;">
		<div id="rolegrouptreepanel" class="easyui-panel" data-options="href:'<%=getServletContext().getContextPath() %>/rolegroup/tree.htmls'">
		</div>
    </div>
    <div data-options="region:'center',hideCollapsedContent:true">
    	
    	<div style="height:10%">
    		<form id="searchSysRoleForm">
    			<input type="hidden" id="rolelistgroupcode" name="conditions[groupcode]" value="<%=request.getAttribute("groupcode") %>">
    			<table>
    				<tr>
		    			<td>角色名称:</td>
		    			<td><input class="easyui-textbox" type="text" name="conditions[rolename]"  data-options="validType:['length[0,50]']"></input></td>
		    			<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchRoleList()">查询</a></td>
		    		</tr>
    			</table>
    		</form>
    	</div>
    	
	    <div id="roleListToolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addRole()">增加</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editRole()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteRoles()">删除</a>
		</div>
		<table id="roleListTable" toolbar="#roleListToolbar" title="角色列表" style="height:90%" data-options=" url:'<%=getServletContext().getContextPath()%>/role/search/list.htmls',idField:'userid',pagination:true">
			<thead>
	            <tr>
	                <th data-options="field:'check',checkbox:true"></th>
	                <th data-options="field:'rolename',width:'33%'">角色名称</th>
	                <th data-options="field:'roleorder',width:'33%'">排序</th>
	                <th data-options="field:'_operate',width:'33%',align:'center',formatter:formatOperResource">分配资源</th>
	            </tr>
	        </thead>
		</table>
		
		<div id="addRoleWindow" class="easyui-window" title="增加" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
		</div>
		
		<div id="editRoleWindow" class="easyui-window" title="编辑" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
		</div>
		
		<div id="grantResourceWindow" class="easyui-window" title="分配资源" data-options="top:'25%',left:'25%',modal:true,width:'50%',height:'50%',padding:'10px'" closed="true" >
		</div>
    </div>
</div>
	<script type="text/javascript">
		
		$(document).ready(function() {
			$('#roleListTable').datagrid();
		});
		
		function addRole(){
			if($("#rolelistgroupcode").val()=="null"){
				$.messager.alert('提示','请选择组.');
			}else{
				$('#addRoleWindow').window({href:appName + "/role/add/"+$("#rolelistgroupcode").val()+".htmls"});
				$('#addRoleWindow').window('open');
			}
		}
		
		function editRole(){
			var rows = $('#roleListTable').datagrid("getSelections");
			if(rows.length!=1){
				$.messager.alert('提示','请选择一条记录编辑.');
				return;
			}
			
			var row = $('#roleListTable').datagrid("getSelected");
			$('#editRoleWindow').window({href:appName + "/role/edit/"+row.rolecode+".htmls"});
			$('#editRoleWindow').window('open');
		}
		
		function deleteRoles(){
			var ids = "";
			$($('#roleListTable').datagrid("getSelections")).each(function(index,obj){
				ids += obj.rolecode+",";
			});
			if(ids.length>0&&ids.charAt(ids.length-1)==","){
				$.messager.confirm('操作确认','确认操作?',function(r){
					if (r){
						ids = ids.substring(0, ids.length-1);
						$.ajax({
				               type: "POST",
				               url: appName+"/role/delete.htmls",
				               data: {ids:ids},
				               success: function(data){
									$('#roleListTable').datagrid('reload');
									$.messager.alert('操作结果','操作成功');
				                  }
				            });
					}
				});
			}else{
				$.messager.alert('提示','请选择角色.');
			}
		}
		
		function searchRoleList(){
			$('#roleListTable').datagrid('load',$("#searchSysRoleForm").serializeObject());
		}
		
		function clickRoleGroupTree(node){
			$("#rolelistgroupcode").val(node.id);
			$('#roleListTable').datagrid('load',$("#searchSysRoleForm").serializeObject());
		}
		
		function formatOperResource(val,row,index){
			return '<a class="icon-man" title="分配资源" onclick="grantResource(\''+row.rolecode+'\')">&nbsp;&nbsp;&nbsp;&nbsp;</a>';
		}
		function grantResource(rolecode){
			$('#grantResourceWindow').window({href:appName + "/resource/roleselectresource/"+rolecode+".htmls"});
			$('#grantResourceWindow').window('open');
		}
	</script>
</body>
</html>