<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String Scheduled = request.getParameter("uuid");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <title>定时任务设置</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=basePath%>layui/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>index/css/scroll-bar.css">
    <link rel="stylesheet" href="<%=basePath%>index/css/sub-page.css">
<link rel="stylesheet" href="//at.alicdn.com/t/font_693759_ciewkz7d5vo.css" />

</head>
<body>

<div class="ok-body">
</div>
<!-- 设置执行时间 -->
<form class="layui-form layui-form-pane" action="" style="margin-left: 40px;">
	<div class="layui-form-item">
    	<label class="layui-form-label" style="width:140px;">任务名称</label>
    	<div class="layui-inline">
    		 <input type="text" id="title" name="title" lay-verify="title" autocomplete="off"  class="layui-input">
      	</div>
  	</div>
	
	<div class="layui-form-item">
    		<label class="layui-form-label" style="width:140px;">运行频率</label>
    		<div class="layui-inline">
    			<input type="number" id="frequency" name="frequency" lay-verify="required|number" autocomplete="off" class="layui-input" style="width:120px;">
      		</div>
      		<div class="layui-inline" style="width:55px";>
      			<select id="time_unit" name="time_unit" lay-verify="required" lay-search="" lay-filter="time_unit">
	 					<option id="3" value="3">时</option>
	 					<option id="2" value="2">分</option>
	 					<option id="1" value="1">秒</option>
	   			</select>
	   		</div>
  		</div>
  		<div class="layui-form-item">
    		<label class="layui-form-label" style="width:140px;">每次执行条数</label>
    		<div class="layui-inline">
    			<input type="number" id="RunNummer" name="RunNummer" lay-verify="required|number" autocomplete="off" class="layui-input" style="width:120px;">
      		</div>
  		</div>
  		<div class="layui-form-item" id = "open_div">
    		<label class="layui-form-label" style="width:140px;">是否开启任务</label>
    		<div class="layui-input-block">
      			<input type="checkbox" checked="" id = "open"name="open" lay-skin="switch" lay-filter="switchTest" lay-text="开启|关闭">
    		</div>
  		</div>
  		
 <!-- 保存按钮 -->
 <div class="layui-form-item">
    <button type="button" class="layui-btn" lay-submit="" lay-filter="demo2" id = "save">保存</button>
  </div>
</form>
<!--js逻辑-->
<script src="<%=basePath%>layui/layui.js"></script>
<script type="text/javascript" src="<%=basePath%>content/Scheduled/js/jquery.js"></script>
<script language="javascript">
    var basePath="<%=basePath%>";
    var Scheduled="<%=Scheduled%>";
</script>
<script>

/**
 * 初始化
 */
var form;
init()
layui.use('form', function() {
	form = layui.form; //只有执行了这一步，部分表单元素才会自动修饰成功
	form.render();
});
 	/* 保存时生成cron表达式 
 	 * 
 	 */
 	$("#save").click(function(){
 		save()
 	}); 
 	
 	 
 function save(){
	 var time = " *"
	 var day = " *"
	 var month = " ?"

	 var timeType = $("#time_unit").val();// 执行间隔单位
	 var time = $("#frequency").val();//执行间隔
	 var RunNummer = $("#RunNummer").val();// 每次执行条数
	 var open = $("#open").val();// 是否开启任务
	 var title = $("#title").val();// 任务名称
	 
	 
		if(time.indexOf(".") >= 0){
			layer.msg('输入的时间只能为正整数', {icon: 5,shift:6});
			return false;
		}
		var cron ="";
		if(timeType=="1"){
			if(time > 60 || time <= 0){
				layer.msg('输入秒数的时间1-59之间的数据', {icon: 5,shift:6});
				return false;
			}
			cron ="0/"+time+" * * * * ?";
		}else if(timeType=="2"){
			if(time>60 || time<=0){
				layer.msg('输入分钟的时间1-59之间的数据', {icon: 5,shift:6});
				return false;
			}
			cron ="0 0/"+time+" * * * ?";
		}else if(timeType=="3"){
			if(time >23 || time<=0){
				layer.msg('输入小时的时间0-23之间的数据', {icon: 5,shift:6});
				return false;
			}
			cron ="0 0 0/"+time+" * * ?";
		}

		/* alert("cron"+cron)
		alert("条数"+RunNummer)
		alert("名称"+title) */
		
		if($('#open').is(':checked')) {
			open = 1
			
		}else{
			open = 0
			/* alert("关闭") */
		} 
		 $.ajax({
			url : basePath + '/updateScheduledTask',
			async : false,
			type : 'POST',
			data : {cron:cron,num:RunNummer,name:title,isstop:open,uuid:Scheduled},
			success : function(data) {
				if(data == "success"){
					alert("保存成功")
					if(open == 1 ){
						$.ajax({
							url : basePath + '/restart_scheduled',
							async : false,
							type : 'POST',
							data : {flag:Scheduled},
							success : function(data) {
								
								alert("启动成功")
								
							},
							error : function() {
								/* layer.msg("启动失败") */
							}
						})
					}else{
						$.ajax({
							url : basePath + '/stop_scheduled',
							async : false,
							type : 'POST',
							data : {flag:Scheduled},
							success : function(data) {
								alert("关闭成功")
								 /* window.parent.location.replace(location.href) */
							},
							error : function() {
								alert("关闭失败")
							}
						})
						
					}
				}else{
					alert("保存失败")
				}
			},
			error : function() {
				alert("保存失败");
			}
		});
 }


	function init(){
		$.ajax({
			url : basePath + 'selectScheduled',
			async : false,
			type : 'POST',
			data : {uuid:Scheduled},
			success : function(data) {
				$("#title").val(data.ScheduledName)
				$("#RunNummer").val(data.num)
				var cro = data.cron;
				var runtime = cro.split(" ")
				for(var i = 0;i<runtime.length;i++){
					if(runtime[i]!='*'&& runtime[i]!='0'){
						$("#frequency").val(runtime[i].split("/")[1])
						var numbers = $("#time_unit").find("option"); //获取select下拉框的所有值
						for (var j = 0; j < numbers.length; j++) {
    						if ($(numbers[j]).val() == i+1+"") {
        						 $(numbers[j]).attr("selected", "selected");
   							 };
						}
						break;
					}
				}
				if(data.is_stop == "启动"){
					$("#open").attr('checked', 'checked');  //改变开关为 开
					$("#open").val(1);  //改变开关为 开
				}else{
					$("#open").removeAttr('checked');  //改变开关为 关
					$("#open").val(-1);  //改变开关为 关
				}
				console.log(data)
				
			},
			error : function() {
				alert("网络连接出错");
			}
		})
	}

</script>
<!--天气预报插件-->

</body>
</html>
