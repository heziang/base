<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPEhtmlPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>首页</title>
	<%@include file="../ui/page/easyui.jsp"%>
	<script type="text/javascript" src="../js/common/index.js"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;">
		<div data-options="region:'north'" style="height:10%">
			<%@include file="../common/header.jsp"%>
		</div>
		<div data-options="region:'south',split:true" style="height:10%;"></div>
		<div data-options="region:'west',split:true" title="菜单" style="width:20%;">
			<div id="index_main_menu" class="easyui-accordion" data-options="fit:true,border:false">
				<div title="权限管理" style="padding:10px;">
					<div onclick="index_selectMeanu('用户管理','/user/list.htmls')">用户管理</div>
					<div onclick="index_selectMeanu('角色管理','/role/list.htmls')">角色管理</div>
					<div onclick="index_selectMeanu('资源管理','/resource/list.htmls')">资源管理</div>
				</div>
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="index_main_tab" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			</div>
		</div>
	</div>
</body>
</html>