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
		 <ul id="rolegrouptree" class="easyui-tree" data-options="url:'<%=getServletContext().getContextPath() %>/rolegroup/searchByParent.htmls',onClick:clickSelectRoleGroupTree">
	    
		</ul>
    </div>
    <div data-options="region:'center',hideCollapsedContent:true">
    	
    	<div style="height:10%">
    		<form id="searchSysRoleForm">
    			<input type="hidden" id="roleselectlistgroupcode" name="conditions[groupcode]" value="<%=request.getAttribute("groupcode") %>">
    			<table>
    				<tr>
		    			<td>角色名称:</td>
		    			<td><input class="easyui-textbox" type="text" name="conditions[rolename]"  data-options="validType:['length[0,50]']"></input></td>
		    			<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchRoleList()">查询</a></td>
		    		</tr>
    			</table>
    		</form>
    	</div>
		<table id="roleSelectListTable"   title="角色列表"  style="height:90%" data-options="onClickRow:selectListRole,url:'<%=getServletContext().getContextPath()%>/role/search/list.htmls',idField:'userid',pagination:true">
			<thead>
	            <tr>
	                <th data-options="field:'rolename',width:'50%'">角色名称</th>
	                <th data-options="field:'roleorder',width:'49%'">排序</th>
	            </tr>
	        </thead>
		</table>
    </div>
    <div id="selectedIds"   style="height:20%" data-options="region:'south',split:true" >
    	<a class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveUserSelectedRoles()">保存</a>
    </div>
</div>
	<script type="text/javascript">
		var userid = '<%=request.getAttribute("userid") %>';
		$(document).ready(function() {
			$('#roleSelectListTable').datagrid();
			//加载用户已有角色
			loadUserRoles(userid);
		});
		
		function searchRoleList(){
			$('#roleSelectListTable').datagrid('load',$("#searchSysRoleForm").serializeObject());
		}
		
		//角色树页面点击事件
		function clickSelectRoleGroupTree(node){
			$("#roleselectlistgroupcode").val(node.id);
			$('#roleSelectListTable').datagrid('load',$("#searchSysRoleForm").serializeObject());
		}
		//列表单击事件
		function selectListRole(index,row){
			if($.inArray(row.rolecode,getSelectedCodes())<0){
				//如果没有，加入。
				var bt = $("<button onclick='$(this).remove()' name='selected_role_name' id='role_"+row.rolecode+"'>"+row.rolename+"</button>");
				$("#selectedIds").append(bt);
			}else{
				$("#role_"+row.rolecode).remove();
			}
		}
		//得到已经选择的记录code数组
		function getSelectedCodes(){
			var ids = [];
			$("button[name='selected_role_name']").each(function(index,obj){
				ids.push($(obj).attr("id").replace("role_",""));
			});
			return ids;
		}
		//保存数据
		function saveUserSelectedRoles(){
			var rolecodes = getSelectedCodes().join(",");
			$.ajax({
	               type: "POST",
	               url: appName+"/user/saveRole.htmls",
	               data: {userid:userid,rolecodes:rolecodes},
	               success: function(data){
						$.messager.alert('操作结果','操作成功');
	                  }
	            });
		}
		//加载用户已有角色
		function loadUserRoles(userid){
			$.ajax({
	               type: "POST",
	               url: appName+"/role/getRolesByUserid/"+userid+".htmls",
	               dataType:"json",
	               success: function(data){
						$(data).each(function(index,row){
							var bt = $("<button onclick='$(this).remove()' name='selected_role_name' id='role_"+row.rolecode+"'>"+row.rolename+"</button>");
							$("#selectedIds").append(bt);
						});
	                  }
	            });
		}
	</script>
</body>
</html>