<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <title>定时任务列表</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>index/css/scroll-bar.css">
    <link rel="stylesheet" href="<%=basePath%>index/css/sub-page.css">
<link rel="stylesheet" href="//at.alicdn.com/t/font_693759_ciewkz7d5vo.css" />

</head>
<body>
<form class="layui-form" action="" lay-filter="mainFilter">
<div class="ok-body">
    <blockquote class="layui-elem-quote">
      当前时间: <span id="nowTime"></span> <span id="weekday"></span>
    </blockquote>
    <fieldset class="layui-elem-field">
        <legend>数据统计</legend>
        <div class="layui-row layui-col-space10 layui-row-margin">
            <div class="layui-col-md3">
                <div class="layui-bg-green md2-sub1">
                    <i class="iconfont icon-dianliyonghuzongshu"></i>
                </div>
                <div class="md2-sub2">
                    <span id="peo_wc">100</span>
                    <cite>人群计数完成数量</cite>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="layui-bg-green md2-sub1">
                    <i class="iconfont icon-dianliyonghuzongshu"></i>
                </div>
                <div class="md2-sub2">
                    <span id="peo_wwc" >100</span>
                    <cite>人群计数未完成数量</cite>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="layui-bg-red md2-sub1">
                    <i class="iconfont icon-jiaose"></i>
                </div>
                <div class="md2-sub2">
                    <span id="lin_wc">100</span>
                    <cite>人员徘徊完成数量</cite>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="layui-bg-red md2-sub1">
                    <i class="iconfont icon-jiaose"></i>
                </div>
                <div class="md2-sub2">
                    <span id="lin_wwc">100</span>
                    <cite>人员徘徊未完成数量</cite>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="layui-bg-orange md2-sub1">
                    <i class="iconfont icon-goods"></i>
                </div>
                <div class="md2-sub2">
                    <span id="rem_wc" >100</span>
                    <cite>可疑物遗留完成数量</cite>
                </div>
            </div>
            <div class="layui-col-md3">
                <div class="layui-bg-orange md2-sub1">
                    <i class="iconfont icon-goods"></i>
                </div>
                <div class="md2-sub2">
                    <span id="rem_wwc">100</span>
                    <cite>可疑物遗留未完成数量</cite>
                </div>
            </div>
             <div class="layui-col-md3">
                <div class="layui-bg-cyan md2-sub1">
                    <i class="iconfont icon-webpage"></i>
                </div>
                <div class="md2-sub2">
                    <span id="face_wc">100</span>
                    <cite>人脸识别完成数量</cite>
                </div>
            </div>
             <div class="layui-col-md3">
                <div class="layui-bg-cyan  md2-sub1">
                    <i class="iconfont icon-webpage"></i>
                </div>
                <div class="md2-sub2">
                    <span id="face_wwc">100</span>
                    <cite>人脸识别未完成数量</cite>
                </div>
            </div>
        </div>
    </fieldset>
	<table class="layui-table" id="tableList" lay-filter="demoEvent"></table> 
</div>
</form>
<!--js逻辑-->
<script src="<%=basePath%>layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>content/Scheduled/js/jquery.js"></script>
<script type="text/html" id="barDemo">
<a class="layui-btn layui-btn-xs" lay-event="into">编辑</a>
</script>
<script language="javascript">
    var basePath="<%=basePath%>";
</script>
<script>


    /**
     * 初始化函数
     */
     layui.use('form', function() {
			var form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
			form.render();
		});
    setDate();
    
    /**
     * 初始化table
     */
     layui.use('table', function(){
    	  var table = layui.table;
    	  table.render({
    		    elem: '#tableList'
    		    ,height: 312
    		    ,id:'table'
    		    ,url: basePath+'initScheduledTable.do' //数据接口
    		    ,page: true //开启分页
    		    ,cols: [[ //表头
    		    	{field:'ScheduledName',  title: '任务名称', sort: true},
     	    		 {field:'cron',  title: '执行间隔', sort: true},
     	    		 {field:'num',  title: '每次执行条数', sort: true},
     	    		 {field:'is_stop',  title: '类别', sort: true},
     	    		 {fixed: 'right',title: '操作',width: 100, align:'center', toolbar: '#barDemo'}
    		    ]]
    		  });
    	  table.on('tool(demoEvent)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
    		    var data = obj.data //获得当前行数据
    		    ,layEvent = obj.event; //获得 lay-event 对应的值
    		    if(layEvent === 'into'){
    				console.log("跳转页面")
    				layer.open({
			            type: 2,
			            skin: 'layui-layer-demo', //样式类名
			            title: '任务设置',
			            closeBtn: 0, //不显示关闭按钮
			            anim: 2,
			            area: ['700px', '400px'],
			            shadeClose: true, //开启遮罩关闭
			            closeBtn :1 ,
			            content: 'Scheduled_Setcron.jsp?uuid='+data.uuid
			        });

    		    }
    		  });
    	 	});
     

    
   	/**
   	*获取各部分完成未完成的数量
   	*/
   	$.ajax({
		url : basePath +'GetComplete.do',
		async : false,
		type : 'POST',
		
		success : function(data) {
			$("#peo_wc").text(data.peo_wc)
			$("#peo_wwc").text(data.peo_wwc)
			$("#lin_wc").text(data.lin_wc)
			$("#lin_wwc").text(data.lin_wwc)
			$("#rem_wc").text(data.rem_wc)
			$("#rem_wwc").text(data.rem_wwc)
			$("#face_wc").text(data.face_wc)
			$("#face_wwc").text(data.face_wwc)
		},
		error : function() {
			alert("网络连接出错");
		}
	});
   	
    /**
     * 获取当前时间
     */
    var nowDate1 = "";
    function setDate() {
        var date = new Date();
        var year = date.getFullYear();
        nowDate1 = year + "-" + addZero((date.getMonth() + 1)) + "-" + addZero(date.getDate()) + "  ";
        nowDate1 += addZero(date.getHours()) + ":" + addZero(date.getMinutes()) + ":" + addZero(date.getSeconds());
        document.getElementById("nowTime").innerHTML = nowDate1;
        setTimeout('setDate()', 1000);
    }

    /**
     * 年月日是分秒为10以下的数字则添加0字符串
     * @param time
     * @returns {number | *}
     */
    function addZero(time) {
        var i = parseInt(time);
        if (i / 10 < 1) {
            i = "0" + i;
        }
        return i;
    }

    /**
     * 初始化星期几
     * @type {string}
     */
    var weekday = "星期" + "日一二三四五六".charAt(new Date().getDay());
    document.getElementById("weekday").innerHTML = weekday;

    layui.use('util', function () {
        var util = layui.util;
        util.fixbar({});
    });
</script>
<!--天气预报插件-->
<script>
    (function(T,h,i,n,k,P,a,g,e){g=function(){P=h.createElement(i);a=h.getElementsByTagName(i)[0];P.src=k;P.charset="utf-8";P.async=1;a.parentNode.insertBefore(P,a)};T["ThinkPageWeatherWidgetObject"]=n;T[n]||(T[n]=function(){(T[n].q=T[n].q||[]).push(arguments)});T[n].l=+new Date();if(T.attachEvent){T.attachEvent("onload",g)}else{T.addEventListener("load",g,false)}}(window,document,"script","tpwidget","//widget.seniverse.com/widget/chameleon.js"))
</script>
<script>
    tpwidget("init", {
        "flavor": "bubble",
        "location": "WX4FBXXFKE4F",
        "geolocation": "enabled",
        "position": "top-right",
        "margin": "10px 10px",
        "language": "zh-chs",
        "unit": "c",
        "theme": "chameleon",
        "uid": "U3414DB4A9",
        "hash": "91ff44d1248d72fc847c6177474e1533"
    });
    tpwidget("show");
</script>
</body>
</html>
