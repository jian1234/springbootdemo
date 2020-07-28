<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String port=request.getServerPort()==80?"":":"+request.getServerPort();
String basePath = request.getScheme()+"://"+request.getServerName()+port+path+"/";
long randomVal = System.currentTimeMillis();//防止缓存

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>layui/css/layui.css" />
<link rel="stylesheet" href="<%=basePath%>index/css/okadmin.css" />
<link rel="stylesheet" href="//at.alicdn.com/t/font_693759_ciewkz7d5vo.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/nprogress/nprogress.css" />
	<script type="text/javascript" src="<%=basePath%>js/jquery/jquery-1.8.2.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>layui/layui.all.js"></script>
<script language="javascript">
    var basePath="<%=basePath%>";

</script>
</head>
<body class="layui-layout-body">
	<title>调用成功</title>
</div>
<script src="<%=basePath%>/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>nprogress/nprogress.js"></script>
<script type="text/javascript" src="<%=basePath%>content/index.js"></script>
 <div class="yy"></div>
</body>
</html>