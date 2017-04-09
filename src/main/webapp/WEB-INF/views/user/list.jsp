<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
</head>
<body>
	<div id="userListToolbar">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addUser()">增加</a>
		<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteUsers()">删除</a>
	</div>
	
	<table id="userListTable" toolbar="#userListToolbar" title="用户列表" style="height:100%">
	</table>
	
	<div id="addUserWindow" class="easyui-window" title="增加用户" data-options="top:'25%',left:'25%',modal:true,width:'50%',height:'50%',padding:'10px'" closed="true" >
	</div>
	
	<div id="editUserWindow" class="easyui-window" title="编辑用户" data-options="top:'25%',left:'25%',modal:true,width:'50%',height:'50%',padding:'10px'" closed="true" >
	</div>

	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#userListTable').datagrid({
			    url:appName+'/user/search/list.htmls',
			    pagination:true,
			    idField:"userid",
			    columns:[[
			        {field:'check',checkbox:true},
			        {field:'userid',title:'登录名',width:'25%'},
			        {field:'username',title:'姓名',width:'25%'},
			        {field:'mobilephone',title:'手机',width:'25%'},
			        {field:'telephone',title:'电话',width:'24%'}
			    ]]
			});
		});
		
		function addUser(){
			$('#addUserWindow').window({
				href:appName + "/user/add.htmls"
			});
			$('#addUserWindow').window('open');
		}
		
		function editUser(){
			
			var row = $('#userListTable').datagrid("getSelected");
			
			$('#editUserWindow').window({
				href:appName + "/user/edit/"+row.userid+".htmls"
			});
			$('#editUserWindow').window('open');
		}
		
		function deleteUsers(){
			var ids = "";
			$($('#userListTable').datagrid("getSelections")).each(function(index,obj){
				ids += obj.userid+",";
			});
			if(ids.length>0&&ids.charAt(ids.length-1)==","){
				ids = ids.substring(0, ids.length-1);
				$.ajax({
		               type: "POST",
		               url: appName+"/user/delete.htmls",
		               data: {userids:ids},
		               success: function(data){
							$('#userListTable').datagrid('reload');
		                  }
		            });
			}else{
				alert("请选择用户！");
			}
		}
	</script>
</body>
</html>