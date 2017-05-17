<%@page language="java"  pageEncoding="UTF-8"%>
<!DOCTYPEhtmlPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript" src="../js/common/header.js"></script>
	<title></title>
</head>
<body>
	<script type="text/javascript">
		var appName='<%= this.getServletContext().getContextPath() %>';
	</script>
	欢迎，
	${SPRING_SECURITY_CONTEXT.authentication.principal.userinfo.username}
	
</body>
</html>