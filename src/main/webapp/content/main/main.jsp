<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>首页</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
	<script type="text/javascript" src="<%=basePath%>layui/layui.js"></script>
	<script type="text/javascript" src="<%=basePath%>content/main/js/main.js"></script>  </head>
  
  <body>
 
    <div class="layui-side">
      <div class="layui-side-scroll" id="navbar_side" lay-filter="side"></div>
	</div>
  </body>
</html>
