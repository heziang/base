<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源列表</title>
</head>
<body>
	
<div id="selectrolelistlayout" class="easyui-layout" style="height:100%;">
    <div data-options="region:'west',title:'资源组',split:true" style="width:20%;">
		 <ul id="selectresourcegrouptree" class="easyui-tree" data-options="url:'<%=getServletContext().getContextPath() %>/resourcegroup/searchByParent.htmls',onClick:clickResourceGroupTree">
	    
		</ul>
    </div>
    <div data-options="region:'center',hideCollapsedContent:true">
    	
    	<div style="height:10%">
    		<form id="searchSelectSysResourceForm">
    			<input type="hidden" id="selectresourcelistgroupcode" name="conditions[groupcode]" value="<%=request.getAttribute("groupcode") %>">
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
    	
			<table id="selectresourceListTable"  title="资源列表" style="height:90%" data-options=" onClickRow:selectListResource,url:'<%=getServletContext().getContextPath()%>/resource/search/list.htmls',idField:'userid',pagination:true">
			<thead>
	            <tr>
	                <th data-options="field:'resourcename',width:'33%'">资源名称</th>
	              	<th data-options="field:'resourcetypename',width:'33%'">资源类型</th>
	                <th data-options="field:'resourcevalue',width:'33%'">资源值</th>
	            </tr>
	        </thead>
		</table>
    </div>
    <div id="selectedId"   style="height:20%" data-options="region:'south',split:true" >
    	<a class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="saveRoleSelectedResources()">保存</a>
    </div>
</div>
	<script type="text/javascript">
		var rolecode = '<%=request.getAttribute("rolecode") %>';
		$(document).ready(function() {
			$('#selectresourceListTable').datagrid();
			//加载已有资源
			loadRoleResources(rolecode);
		});
		
		function searchRoleList(){
			$('#selectresourceListTable').datagrid('load',$("#searchSelectSysResourceForm").serializeObject());
		}
		
		//角色树页面点击事件
		function clickResourceGroupTree(node){
			$("#selectresourcelistgroupcode").val(node.id);
			$('#selectresourceListTable').datagrid('load',$("#searchSelectSysResourceForm").serializeObject());
		}
		//列表单击事件
		function selectListResource(index,row){
			if($.inArray(row.resourcecode,getSelectedCodes())<0){
				//如果没有，加入。
				var bt = $("<button onclick='$(this).remove()' name='selected_resource_name' id='resource_"+row.resourcecode+"'>"+row.resourcename+"</button>");
				$("#selectedId").append(bt);
			}else{
				$("#resource_"+row.resourcecode).remove();
			}
		}
		//得到已经选择的记录code数组
		function getSelectedCodes(){
			var ids = [];
			$("button[name='selected_resource_name']").each(function(index,obj){
				ids.push($(obj).attr("id").replace("resource_",""));
			});
			return ids;
		}
		//保存数据
		function saveRoleSelectedResources(){
			var resourcecodes = getSelectedCodes().join(",");
			$.ajax({
	               type: "POST",
	               url: appName+"/role/saveRoleResources.htmls",
	               data: {resourcecodes:resourcecodes,rolecode:rolecode},
	               success: function(data){
						$.messager.alert('操作结果','操作成功');
	                  }
	            });
		}
		//加载角色已有资源
		function loadRoleResources(rolecode){
			$.ajax({
	               type: "POST",
	               url: appName+"/resource/getResourcesByRoleCode/"+rolecode+".htmls",
	               dataType:"json",
	               success: function(data){
						$(data).each(function(index,row){
							var bt = $("<button onclick='$(this).remove()' name='selected_resource_name' id='resource_"+row.resourcecode+"'>"+row.resourcename+"</button>");
							$("#selectedId").append(bt);
						});
	                  }
	            });
		}
	</script>
</body>
</html>