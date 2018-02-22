<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志列表</title>
</head>
<body>
	
<div id="loglistlayout" class="easyui-layout" style="height:100%;">
    <div data-options="region:'center',hideCollapsedContent:true">
    	<div style="height:10%">
    		<form id="searchSysLogForm">
    			<table>
    				<tr>
		    			<td>标题:</td>
		    			<td><input class="easyui-textbox" type="text" name="conditions[logtitle]"  data-options="validType:['length[0,25]']"></input></td>
	    				<td>内容:</td>
   						<td><input class="easyui-textbox" name="conditions[logcontent]" data-options="validType:['name','length[0,10]']" type="text"  ></input></td>
		    			<td>创建时间:</td>
		    			<td><input class="easyui-datebox" type="text" name="conditions[startDate]"  data-options="validType:['date']"></input></td>
		    			<td>至</td>
		    			<td><input class="easyui-datebox" type="text" name="conditions[endDate]"   data-options="validType:['date']"></input></td>
		    			<td><a class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="searchLogList()">查询</a></td>
		    		</tr>
    			</table>
    		</form>
    	</div>
		<table id="logListTable" toolbar="#logListToolbar" title="日志列表" style="height:90%" data-options=" url:'<%=getServletContext().getContextPath()%>/syslog/search/list.htmls',idField:'logid',pagination:true">
			<thead>
	            <tr>
	                <th data-options="field:'logtitle',width:'20%'">标题</th>
	                <th data-options="field:'logcontent',width:'60%'">内容</th>
	                <th data-options="field:'createtime',width:'20%',formatter:dateFormatter">创建时间</th>
	            </tr>
	        </thead>
		</table>
    </div>
</div>
	<script type="text/javascript">
		
		$(document).ready(function() {
			$('#logListTable').datagrid();
		});
		
		function searchLogList(){
			$('#logListTable').datagrid('load',$("#searchSysLogForm").serializeObject());
		}
		
		function dateFormatter(val,row,index){
			return new Date(val).format("yyyy-MM-dd hh:mm:ss");
		}
	</script>
</body>
</html>