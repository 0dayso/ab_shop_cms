var resultData;
function showChart(url, container, type) {
	$.ajax( {
		url : url,
		type : 'POST',
		dataType : "json",
		success : function(result) {
			if(result.series.data.length >0){
				for ( var i = 0; i < result.series.data.length; i++) {
					result.series.data[i][1] = parseInt(result.series.data[i][1]);
				}
				resultData = result;
				renderChart(container, type);
			}else{
				$('#' + container).text("No data to display!");
			}
		},
		error : function() {
			$('#' + container).text("图表加载错误");
		},
		cache : false
	});
}

function renderChart(container, type) {
	$('#' + container).highcharts({
		chart : {
			type : type,
			zoomType : 'xy',
			plotBorderWidth:1,
			plotBorderColor:'#CCCCCC'
		},
		title : {
			text : resultData.title,
			style:{
				color: '#666666',
				fontSize: '16px',
				fontWeight: 'bold',
				fontFamily: '微软雅黑'
			}
		},
		subtitle : {
			text : resultData.subtitle,
			style:{
				fontSize: '12px',
				fontFamily: '微软雅黑'
			}
		},
		xAxis : {
			categories : [],
			title : {
				text : resultData.xAxisTitle
			}
		},
		yAxis : {
			title : {
				text : resultData.yAxisTitle
			},
			alternateGridColor: '#F9F9F9'
		},
		legend : {
			align: 'right',
			verticalAlign: 'top',
	        x: 10,
	        y: 180,
	        borderRadius: 0,
	        borderWidth:0,
	        backgroundColor: '#CCCCCC',
	        itemStyle: {
        	   cursor: 'pointer',
        	   color: '#FFFFFF',
        	   fontSize: '12px',
        	   fontWeight: 'bold'
	        },
	        itemHiddenStyle: {
	        	color: '#999999'
	        },
	        itemHoverStyle: {
	        	color: '#FFFFFF'
	        }
		},
		tooltip : {
			enabled : true,
			backgroundColor : '#525253',
			borderColor : "#000",
			style : {
				color : "#fff"
			},
			headerFormat : '',
			pointFormat : '<b style="font-family:Microsoft yahei">{point.y}个</b>'
		},
		series : [ resultData.series ]
	});
}
