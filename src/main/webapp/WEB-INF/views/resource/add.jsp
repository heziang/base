<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>resource</title>
</head>
<body>
	 <form id="addSysResourceForm">
    	<table>
    		<input type="hidden" name="groupcode" value="<%=request.getAttribute("groupcode") %>">
    		<tr>
    			<td>资源名称:</td>
    			<td><input class="easyui-textbox" type="text" name="resourcename" prompt="输入资源名称" required="true" data-options="validType:['unnormal','length[0,50]']"></input></td>
    		</tr>
    		<tr>
    			<td>资源值:</td>
    			<td><input class="easyui-textbox" type="text" required="true"  name="resourcevalue" data-options="validType:['unnormal','length[0,50]']"></input></td>
    		</tr>
    		<tr>
    			<td>资源类型:</td>
    			<td>
					<input class="easyui-combobox" style="width:100%;" name="resourcetype" required="true" data-options="textField:'text',valueField:'value',url:'<%=getServletContext().getContextPath() %>/dictionary/searchByTypecode/resourceType.htmls'">
				</td>
			</tr>
    		<tr>
    			<td>排序:</td>
    			<td><input class="easyui-textbox" data-options="validType:['int','length[0,5]']" type="text" name="resourceorder" required="true"></input></td>
    		</tr>
		</table>
		<div style="text-align:center;padding:5px">
	    	<a class="easyui-linkbutton" onclick="saveSysResource()">保存</a>
	    	<a class="easyui-linkbutton" onclick="closeAddResourceWindow()">关闭</a>
	    </div>
	</form>	
	<script type="text/javascript">
		
		$(document).ready(function() {
		});
	
		function saveSysResource(){
			if($("#addSysResourceForm").form('enableValidation').form('validate')){
				$.ajax({
		               type: "POST",
		               url: appName+"/resource/save.htmls",
		               data: $("#addSysResourceForm").serialize(),
		               success: function(data){
		            	    $('#addResourceWindow').window('close');
							$('#resourceListTable').datagrid('reload');
							$.messager.alert('操作结果','操作成功');
		                  }
		            });
			}
		}
		
		function closeAddResourceWindow(){
			$('#addResourceWindow').window('close');
		}
	</script>
</body>
</html>