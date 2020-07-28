


////执行渲染
//table.render({
//elem: '#demo' //指定原始表格元素选择器（推荐id选择器）
//,height: 315 //容器高度
//,cols: [{}] //设置表头
////,…… //更多参数参考右侧目录：基本参数选项
//});
//$(document).ready(function(){
//	console.log("初始化方法");
//	initTable();
//});
//function initTable() {
//	 console.log("执行initTable")
//	 var searchrule=$("#searchrule").val();
//	//添加数据
//	 tableIns = table.render({
//	  elem: '#tableList'
//	  ,id:'idTest'
//	  ,url: basePath+'initScheduledTable.do'
//	  ,cols: [[
//	 {field:'ScheduledName',  title: '任务名称', sort: true},
//	 {field:'cron',  title: '执行间隔', sort: true},
//	 {field:'num',  title: '每次执行条数', sort: true},
//	 {field:'is_stop',  title: '类别', sort: true},
//	 {fixed: 'right', width: 100, align:'center', toolbar: '#barDemo'}
//	 ]]
//	  ,skin: 'row' //表格风格
//	  ,even: true
//	  ,page: true //是否显示分页
//	  ,limits: [10, 20, 50 , 100] //分页类型
//	  ,limit: 10 //每页默认显示的数量
//	  ,id: 'listReload' //生成 Layui table 的标识 id，必须提供，用于后文刷新操作，笔者该处出过问题
//	  ,done: function(res, curr, count){
//	      //设置全部数据到全局变量
//	      table_data=res.data;
//	  }
//	});
//
//
//}