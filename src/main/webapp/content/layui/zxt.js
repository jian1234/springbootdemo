$(document).ready(function(){
var myChart = echarts.init(document.getElementById('main1'));
    option = {
    	    tooltip: {
    	        trigger: 'axis',
    	        axisPointer: {
    	            type: 'cross',
    	            crossStyle: {
    	                color: '#999'
    	            }
    	        }
    	    },
    	    toolbox: {
    	        feature: {
    	            dataView: {show: false, readOnly: false},
    	            magicType: {show: false, type: ['line', 'bar']},
    	            restore: {show: false},
    	            saveAsImage: {show: false}
    	        }
    	    },
    	    legend: {
    	        data:['数量（件）','增长率']
    	    },
    	    xAxis: [
    	        {
    	            type: 'category',
    	            data: ['2007年','2008年','2009年','20010年','2011年','2012年','2013年','2014年','2015年','2016年','2017年','2018年'],
    	            axisPointer: {
    	                type: 'shadow'
    	            }
    	        }
    	    ],
    	    yAxis: [
    	        {
    	            type: 'value',
    	            name: '数量',
    	            min: 0,
    	            max: 25,
    	            axisLabel: {
    	                formatter: '{value} '
    	            }
    	        },
    	        {
    	            type: 'value',
    	            name: '增长率',
    	            min: -0.3,
    	            max: 0.9,
    	            axisLabel: {
    	                formatter: '{value} '
    	            }
    	        }
    	    ],
    	    series: [
    	        {
    	            name:'数量（件）',
    	            type:'bar',
    	            data:[8, 9, 10, 18, 18.25,17.6, 16.5, 17.2,18.9, 19, 19.2, 19.3]
    	        },
    	        {
    	            name:'增长率',
    	            type:'line',
    	            yAxisIndex: 1,
    	            data:[0,0.1, 0.2, 0.8, 0, -0.05, -0.1, 0, 0.1, 0, 0,0]
    	        }
    	    ]
};
  myChart.setOption(option);

  window.onresize = function () {
		myChart.resize();
  };
	
});