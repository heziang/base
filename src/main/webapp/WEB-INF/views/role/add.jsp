<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>role</title>
</head>
<body>
	 <form id="addSysRoleForm">
    	<table>
    		<input type="hidden" name="groupcode" value="<%=request.getAttribute("groupcode") %>">
    		<tr>
    			<td>角色名称:</td>
    			<td><input class="easyui-textbox" type="text" name="rolename" prompt="输入角色名称"  required="true" data-options="validType:['unnormal','length[0,25]']"></input></td>
    		</tr>
    		<tr>
    			<td>排序:</td>
    			<td><input class="easyui-textbox" data-options="validType:['int','length[0,5]']" type="text" name="roleorder" required="true"></input></td>
    		</tr>
		</table>
		<div style="text-align:center;padding:5px">
	    	<a class="easyui-linkbutton" onclick="saveSysRole()">保存</a>
	    	<a class="easyui-linkbutton" onclick="closeAddRoleWindow()">关闭</a>
	    </div>
	</form>	
	<script type="text/javascript">
		
		$(document).ready(function() {
		});
	
		function saveSysRole(){
			if($("#addSysRoleForm").form('enableValidation').form('validate')){
				$.ajax({
		               type: "POST",
		               url: appName+"/role/save.htmls",
		               data: $("#addSysRoleForm").serialize(),
		               success: function(data){
		            	   closeAddRoleWindow();
							$('#roleListTable').datagrid('reload');
							$.messager.alert('操作结果','操作成功');
		                  }
		            });
			}
		}
		
		function closeAddRoleWindow(){
			$('#addRoleWindow').window('close');
		}
	</script>
</body>
</html>