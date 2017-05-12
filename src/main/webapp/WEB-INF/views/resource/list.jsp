<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	
<div id="resourcelistlayout" class="easyui-layout" style="height:100%;">
    <div data-options="region:'west',title:'资源组',split:true" style="width:20%;">
		<div id="resourcegrouptreepanel" class="easyui-panel" data-options="href:'<%=getServletContext().getContextPath() %>/resourcegroup/tree.htmls'">
		</div>
    </div>
    <div data-options="region:'center',hideCollapsedContent:true">
    	
    	<div style="height:10%">
    		<form id="searchSysResourceForm">
    			<input type="hidden" id="resourcelistgroupcode" name="conditions[groupcode]" value="<%=request.getAttribute("groupcode") %>">
    			<table>
    				<tr>
		    			<th>资源名称:</th>
		    			<td><input class="easyui-textbox" type="text" name="conditions[resourcename]"  data-options="validType:['length[0,50]']"></input></td>
		    			<th>资源类型:</th>
	    				<td><input class="easyui-combobox" style="width:100%;" name="conditions[resourcetype]"  data-options="textField:'text',valueField:'value',url:'<%=getServletContext().getContextPath() %>/dictionary/searchByTypecode/resourceType.htmls'"></td>
		    			<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchResourceList()">查询</a></td>
		    		</tr>
    			</table>
    		</form>
    	</div>
    	
	    <div id="resourceListToolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addResource()">增加</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editResource()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteResources()">删除</a>
		</div>
		<table id="resourceListTable" toolbar="#resourceListToolbar" title="资源列表" style="height:90%" data-options=" url:'<%=getServletContext().getContextPath()%>/resource/search/list.htmls',idField:'userid',pagination:true">
			<thead>
	            <tr>
	                <th data-options="field:'check',checkbox:true"></th>
	                <th data-options="field:'resourcename',width:'33%'">资源名称</th>
	                <th data-options="field:'resourcetypename',width:'33%'">资源类型</th>
	                <th data-options="field:'resourcevalue',width:'33%'">资源值</th>
	            </tr>
	        </thead>
		</table>
		
		<div id="addResourceWindow" class="easyui-window" title="增加资源" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
		</div>
		
		<div id="editResourceWindow" class="easyui-window" title="编辑资源" data-options="top:'25%',left:'25%',modal:true,width:'30%',height:'30%',padding:'10px'" closed="true" >
		</div>
    </div>
</div>
	<script type="text/javascript">
		
		$(document).ready(function() {
			$('#resourceListTable').datagrid();
		});
		
		function addResource(){
			if($("#resourcelistgroupcode").val()=="null"){
				$.messager.alert('提示','请选择组.');
			}else{
				$('#addResourceWindow').window({href:appName + "/resource/add/"+$("#resourcelistgroupcode").val()+".htmls"});
				$('#addResourceWindow').window('open');
			}
		}
		
		function editResource(){
			var rows = $('#resourceListTable').datagrid("getSelections");
			if(rows.length!=1){
				$.messager.alert('提示','请选择一条记录编辑.');
				return;
			}
			
			var row = $('#resourceListTable').datagrid("getSelected");
			$('#editResourceWindow').window({href:appName + "/resource/edit/"+row.resourcecode+".htmls"});
			$('#editResourceWindow').window('open');
		}
		
		function deleteResources(){
			var ids = "";
			$($('#resourceListTable').datagrid("getSelections")).each(function(index,obj){
				ids += obj.resourcecode+",";
			});
			if(ids.length>0&&ids.charAt(ids.length-1)==","){
				$.messager.confirm('操作确认','确认操作?',function(r){
					if (r){
						ids = ids.substring(0, ids.length-1);
						$.ajax({
				               type: "POST",
				               url: appName+"/resource/delete.htmls",
				               data: {ids:ids},
				               success: function(data){
									$('#resourceListTable').datagrid('reload');
									$.messager.alert('操作结果','操作成功');
				                  }
				            });
					}
				});
			}else{
				$.messager.alert('提示','请选择资源.');
			}
		}
		
		function searchResourceList(){
			$('#resourceListTable').datagrid('load',$("#searchSysResourceForm").serializeObject());
		}
		
		function clickResourceGroupTree(node){
			$("#resourcelistgroupcode").val(node.id);
			$('#resourceListTable').datagrid('load',$("#searchSysResourceForm").serializeObject());
		}
	</script>
</body>
</html>