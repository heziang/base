<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userInfo</title>
</head>
<body>
	 <form id="editSysUserForm">
    	<table>
    		<tr>
    			<td>用户名:</td>
    			<td><input class="easyui-textbox" type="text" name="userid" required="true" data-options="editable:false,validType:['unnormal','length[0,50]']"></input></td>
    			<td>昵称:</td>
    			<td><input class="easyui-textbox" type="text" name="nickname" data-options="validType:['unnormal','length[0,50]']"></input></td>
    		</tr>
    		<tr>
    			<td>性别:</td>
    			<td>
					<input id="sexcomb" class="easyui-combobox" style="width:100%;" name="sex" required="true" data-options="textField:'text',valueField:'value',url:'<%=getServletContext().getContextPath()%>/dictionary/searchByTypecode/sex.htmls'">
				</td>
    			<td>姓名:</td>
    			<td><input class="easyui-textbox" data-options="validType:['name','length[0,10]']" type="text" name="username" required="true"></input></td>
    		</tr>
    		<tr>
    			<td>职务:</td>
    			<td><input class="easyui-textbox" type="text" name="post" data-options="validType:['length[0,25]']"></input></td>
    			<td>固话:</td>
    			<td><input class="easyui-textbox" type="text" name="telephone" data-options="validType:['phone']"></input></td>
    		</tr>
    		<tr>
    			<td>邮箱:</td>
    			<td><input class="easyui-textbox" type="text" name="email" data-options="validType:['email','length[0,50]']"></input></td>
    			<td>手机:</td>
    			<td><input class="easyui-textbox" data-options="validType:['mobile']" type="text" name="mobilephone"></input></td>
    		</tr>
		</table>
		<div style="text-align:center;padding:5px">
	    	<a class="easyui-linkbutton" onclick="updateSysUser()">保存</a>
	    	<a class="easyui-linkbutton" onclick="closeEditUserWindow()">关闭</a>
	    </div>
	</form>	
	<script type="text/javascript">
		var info = <%=request.getAttribute("info") %>;
		$(document).ready(function() {
			$('#editSysUserForm').form('load',info);
		});
	
		function updateSysUser(){
			if($("#editSysUserForm").form('enableValidation').form('validate')){
				$.ajax({
		               type: "POST",
		               url: appName+"/user/update.htmls",
		               data: $("#editSysUserForm").serialize(),
		               success: function(data){
		            	    $('#editUserWindow').window('close');
							$('#userListTable').datagrid('reload');
							$.messager.alert('操作结果','操作成功');
		                  }
		            });
			}
		}
		
		function closeEditUserWindow(){
			$('#editUserWindow').window('close');
		}
	</script>
</body>
</html>