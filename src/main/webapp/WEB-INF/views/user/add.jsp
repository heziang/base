<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>userInfo</title>
</head>
<body>
	 <form id="addSysUserForm">
    	<table>
    		<tr>
    			<td>用户名:</td>
    			<td><input class="easyui-textbox" type="text" name="userid" required="true" data-options="validType:['username','length[0,50]']"></input></td>
    			<td>昵称:</td>
    			<td><input class="easyui-textbox" type="text" name="nickname" data-options="validType:['unnormal','length[0,50]']"></input></td>
    		</tr>
    		<tr>
    			<td>密码:</td>
    			<td><input class="easyui-passwordbox" id="pwd" name="pwd" prompt="Password" required="true" data-options="validType:['unnormal','length[0,50]']"></input></td>
    			<td>确认密码:</td>
    			<td><input class="easyui-passwordbox" id="pwdconfirm" prompt="Password" required="true" validtype="same['pwd']"></input></td>
    		</tr>
    		<tr>
    			<td>性别:</td>
    			<td>
					<input id="sexcomb" class="easyui-combobox" style="width:100%;" name="sex" required="true" data-options="textField:'text',valueField:'value',url:'/dictionary/searchByTypecode/sex.htmls'">
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
	    	<a class="easyui-linkbutton" onclick="saveSysUser()">保存</a>
	    	<a class="easyui-linkbutton" onclick="closeWindow()">关闭</a>
	    </div>
	</form>	
	<script type="text/javascript">
		$(document).ready(function() {
		});
	
		function saveSysUser(){
			alert($("#addSysUserForm").serialize());
			if($("#addSysUserForm").form('enableValidation').form('validate')){
				$.ajax({
		               type: "POST",
		               url: appName+"/user/save.htmls",
		               data: $("#addSysUserForm").serialize(),
		               success: function(data){
		            	    $('#addUserWindow').window('close');
							$('#userListTable').datagrid('reload');
		                  }
		            });
			}
		}
		
		function closeWindow(){
			$('#addUserWindow').window('close');
		}
	</script>
</body>
</html>