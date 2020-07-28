$(document).ready(function(){
var myChart = echarts.init(document.getElementById('main'));
    option = {
    		
    	    tooltip: {
    	        trigger: 'axis',
    	        axisPointer: {
    	            type: 'shadow'
    	        }
    	    },
    	    legend: {
    	         data: ['2012','2013','2014','2015','2016','2017']
    	    },
    	    grid: {
    	        left: '3%',
    	        right: '4%',
    	        bottom: '3%',
    	        containLabel: true
    	    },
    	    xAxis: {
    	         type: 'category',
    	         data: ['群体性聚集事件数量', '车辆聚集事件']
    	    },
    	    axisLabel: {  
                          interval: 0,  
                          formatter:function(value)  
                               {  
                                  var ret = "";//拼接加\n返回的类目项  
                                  var maxLength = 9;//每项显示文字个数  
                                  var valLength = value.length;//X轴类目项的文字个数  
                                  var rowN = Math.ceil(valLength / maxLength); //类目项需要换行的行数  
                                  if (rowN > 1)//如果类目项的文字大于3,  
                                    {  
                                      for (var i = 0; i < rowN; i++) {  
                                         var temp = "";//每次截取的字符串  
                                         var start = i * maxLength;//开始截取的位置  
                                         var end = start + maxLength;//结束截取的位置  
                                         //这里也可以加一个是否是最后一行的判断，但是不加也没有影响，那就不加吧  
                                          temp = value.substring(start, end) + "\n";  
                                          ret += temp; //凭借最终的字符串  
                                          }  
                                           return ret;  
                                      }else {  
                                          return value;  
                                      }  
                                   }  
            },
    	    yAxis: {
    	       type: 'value',
    	        boundaryGap: [0, 0.01]
    	    },
    	    series: [
    	        {
    	            name: '2012',
    	            type: 'bar',
    	            data: [160, 30]
    	        },
    	        {
    	            name: '2013',
    	            type: 'bar',
    	            data: [139, 25]
    	        },
    	        {
    	            name: '2014',
    	            type: 'bar',
    	            data: [128, 26]
    	        },
    	        {
    	            name: '2015',
    	            type: 'bar',
    	            data: [105, 28]
    	        },
    	        {
    	            name: '2016',
    	            type: 'bar',
    	            data: [110, 31]
    	        },
    	        {
    	            name: '2017',
    	            type: 'bar',
    	            data: [115, 75]
    	        }
    	    ]
};
  myChart.setOption(option);

  window.onresize = function () {
		myChart.resize();
  };
	
});