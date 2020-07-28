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
<div class="layui-layout layui-layout-admin">
    <!--头部导航-->
    <div class="layui-header">
        <div class="layui-logo">后台管理</div>
        <div class="menu-switch">
            <i class="iconfont icon-caidan"></i>
        </div>
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item layui-this"><a href=""><i class="iconfont icon-jiankongkongzhiguanlijianguan"></i> 定时任务<span class="layui-badge-dot"></span></a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <!-- <img src="images/head.jpg" class="layui-nav-img"> -->
                    Alpha Shaw
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">个人中心<span class="layui-badge-dot"></span></a></dd>
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                    <dd><a href="javascript:void(0)" id="lock">锁定账户</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="javascript:void(0)" id="logout">退出</a></li>
        </ul>
    </div>
    <!--左侧导航区域-->
    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-shrink="all" lay-filter="">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">
                        <i class="layui-icon">&#xe62a;</i> 常用页面
                    </a>
                    <dl class="layui-nav-child">
                    	<dd><a href="javascript:;" path="<%=basePath%>content/Scheduled/Scheduled_main.jsp" tab-id="2-1">定时任务列表</a></dd>
                    </dl>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;" path="<%=basePath%>content/uploadPage.html" tab-id="2-1">上传</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>
    <!-- 内容主体区域 -->
    <div class="content-body">
        <div class="layui-tab layui-tab-brief" lay-filter="ok-tab" lay-allowClose="true">
            <ul class="layui-tab-title">
                <li class="layui-this"><i class="layui-icon">&#xe68e;</i> 控制台</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe src='<%=basePath%>content/welcome.jsp' frameborder="0" scrolling="yes" width="100%" height="100%"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="<%=basePath%>/layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>nprogress/nprogress.js"></script>
<script type="text/javascript" src="<%=basePath%>content/index.js"></script>
<script type="text/javascript" src="<%=basePath%>content/sjzx/js/jquery.js"></script>
 <div class="yy"></div>
</body>
</html>