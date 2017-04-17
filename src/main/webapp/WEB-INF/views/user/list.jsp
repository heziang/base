<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	
<div id="userlistlayout" class="easyui-layout" style="height:100%;">
    <div data-options="region:'west',title:'用户组',split:true" style="width:20%;">
		<div id="usergrouptreepanel" class="easyui-panel" data-options="href:'<%=getServletContext().getContextPath() %>/usergroup/tree.htmls'">
		</div>
    </div>
    <div data-options="region:'center',hideCollapsedContent:true">
    	
    	<div style="height:10%">
    		<form id="searchSysUserForm">
    			<input type="hidden" id="groupcode" name="conditions[groupcode]" value="<%=request.getAttribute("groupcode") %>;">
    			<table>
    				<tr>
		    			<td>用户名:</td>
		    			<td><input class="easyui-textbox" type="text" name="conditions[userid]"  data-options="validType:['username','length[0,50]']"></input></td>
	    				<td>姓名:</td>
   						<td><input class="easyui-textbox" data-options="validType:['name','length[0,10]']" type="text" name="conditions[username]" ></input></td>
		    			<td>创建时间:</td>
		    			<td><input class="easyui-datebox" type="text" name="conditions[startDate]"  data-options="validType:['username','length[0,50]']"></input></td>
		    			<td>至</td>
		    			<td><input class="easyui-datebox" type="text" name="conditions[endDate]"   data-options="validType:['username','length[0,50]']"></input></td>
		    			<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchUserList()">查询</a></td>
		    		</tr>
    			</table>
    		</form>
    	</div>
    	
	    <div id="userListToolbar">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">增加</a>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUsers()">删除</a>
		</div>
		<table id="userListTable" toolbar="#userListToolbar" title="用户列表" style="height:90%" data-options=" url:'<%=getServletContext().getContextPath()%>/user/search/list.htmls',idField:'userid',pagination:true">
			<thead>
	            <tr>
	                <th data-options="field:'check',checkbox:true"></th>
	                <th data-options="field:'userid',width:'25%'">用户名</th>
	                <th data-options="field:'username',width:'25%'">姓名</th>
	                <th data-options="field:'mobilephone',width:'25%'">手机</th>
	                <th data-options="field:'telephone',width:'24%'">电话</th>
	            </tr>
	        </thead>
		</table>
		
		<div id="addUserWindow" class="easyui-window" title="增加用户" data-options="top:'25%',left:'25%',modal:true,width:'50%',height:'50%',padding:'10px'" closed="true" >
		</div>
		
		<div id="editUserWindow" class="easyui-window" title="编辑用户" data-options="top:'25%',left:'25%',modal:true,width:'50%',height:'50%',padding:'10px'" closed="true" >
		</div>
    </div>
</div>
	<script type="text/javascript">
		
		$(document).ready(function() {
			
		});
		
		function addUser(){
			if($("#groupcode").val()!=""){
				$.messager.alert('提示','请选择用户组.');
			}else{
				$('#addUserWindow').window({href:appName + "/user/add/"+$("#groupcode").val()+".htmls"});
				$('#addUserWindow').window('open');
			}
		}
		
		function editUser(){
			var row = $('#userListTable').datagrid("getSelected");
			$('#editUserWindow').window({href:appName + "/user/edit/"+row.userid+".htmls"});
			$('#editUserWindow').window('open');
		}
		
		function deleteUsers(){
			var ids = "";
			$($('#userListTable').datagrid("getSelections")).each(function(index,obj){
				ids += obj.userid+",";
			});
			if(ids.length>0&&ids.charAt(ids.length-1)==","){
				$.messager.confirm('操作确认','确认操作?',function(r){
					if (r){
						ids = ids.substring(0, ids.length-1);
						$.ajax({
				               type: "POST",
				               url: appName+"/user/delete.htmls",
				               data: {userids:ids},
				               success: function(data){
									$('#userListTable').datagrid('reload');
									$.messager.alert('操作结果','操作成功');
				                  }
				            });
					}
				});
			}else{
				$.messager.alert('提示','请选择用户.');
			}
		}
		
		function searchUserList(){
			$('#userListTable').datagrid('load',$("#searchSysUserForm").serializeObject());
		}
		
		function clickUserGroupTree(node){
			$("#groupcode").val(node.id);
			$('#userListTable').datagrid('load',$("#searchSysUserForm").serializeObject());
		}
	</script>
</body>
</html>