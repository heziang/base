<%@page language="java" pageEncoding="UTF-8"%>
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
			</div>
		</div>
		<div data-options="region:'center'">
			<div id="index_main_panel" class="easyui-panel"  style="width:100%;height:100%;background:#fafafa;" data-options="">
			</div>
		</div>
	</div>
</body>
</html>