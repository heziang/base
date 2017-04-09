<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	 <form id="addUserGroupForm">
	 	<input type="hidden" name="pgroupcode" value="<%= request.getAttribute("pid")%>"/>
    	<table>
    		<tr>
    			<td>分组名称:</td>
    			<td><input class="easyui-textbox" type="text" name="groupname" data-options="validType:['unnormal','length[0,50]']"></input></td>
    		</tr>
    		<tr>
    			<td>类型:</td>
    			<td><input class="easyui-textbox" type="text" name="grouptype" prompt="选择类型" required="true" data-options="validType:['unnormal','length[0,50]']"></input></td>
    			<td>排序:</td>
    			<td><input class="easyui-textbox" type="text" name="gorder" prompt="输入排序值" data-options="validType:['unnormal','integer']"></input></td>
    		</tr>
		</table>
		<div style="text-align:center;padding:5px">
	    	<a class="easyui-linkbutton" onclick="saveUserGroup()">保存</a>
	    	<a class="easyui-linkbutton" onclick="closeWindow()">关闭</a>
	    </div>
	</form>	
	<script type="text/javascript">
		
		$(document).ready(function() {
			
		});
	
		function saveUserGroup(){
			if($("#addUserGroupForm").form('enableValidation').form('validate')){
				$.ajax({
		               type: "POST",
		               url: appName+"/usergroup/save.htmls",
		               data: $("#addUserGroupForm").serialize(),
		               success: function(data){
		            	    $('#addUserGroupWindow').window('close');
							$.messager.alert('操作结果','操作成功');
		                  }
		            });
			}
		}
		
		function closeWindow(){
			$('#addUserGroupWindow').window('close');
		}
	</script>
</body>
</html>