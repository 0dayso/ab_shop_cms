<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>数据统计</title>
		<link href="<%=request.getContextPath()%>/public/css/tab.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/highcharts/highcharts.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/highcharts/exporting.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/chart.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/dateUtils.js"></script>
		<script type="text/javascript">
			function getMonth() {
				var date, currentMonth, temp;
				date = new Date();
				currentMonth = date.getFullYear().toString();
				temp = date.getMonth() + 1;
				currentMonth += "-"+(temp > 9 ? "" : "0") + temp;
				$('#month').val(currentMonth);
				return currentMonth;
			}
			$(function () {
				var url = "<%=request.getContextPath()%>/system/chartReport_count.action?month="+getMonth()+"&type="+$('#count-type').val()+"&period="+$('#count-period').val();
				showChart(url,'chart-container','line');
				$('#close-button').click(function(){
					$('#tabWindow').hide();	
					$('#overlay').hide();
				});
				$('#popup').click(function(){
					$('#tabWindow').show();
					$('#overlay').css({width:$(document).width(),height:$(document).height()}).show();	
				});
				$('.tabs > li').each(function(index){
					$(this).click(function(){
						$('#count-period').val(index+1);
						$('#error-msg').text("");
						$(this).addClass('active').siblings().removeClass('active');
						$('#tab-body-inner > div').hide().eq(index).show();
					});  
				});
				$('#count-type').change(function(){
					url = "<%=request.getContextPath()%>/system/chartReport_count.action?"
						+"year="+$('#year').val()+"&day="+$('#day').val()
						+"&month="+$('#month').val()+"&yearBeg="+$('#yearBeg').val()+"&yearEnd="+$('#yearEnd').val()
						+"&type="+$('#count-type').val()+"&period="+$('#count-period').val();
					showChart(url,'chart-container','line');
				});
				
				$('#submit-btn').click(function(){
					if($('#day').val()=="" && $('#count-period').val()==1){
						$('#error-msg').text("请选择日期!");
					}else if($('#month').val()=="" && $('#count-period').val()==2){
						$('#error-msg').text("请选择月份!");
					}else if($('#year').val()=="" && $('#count-period').val()==3){
						$('#error-msg').text("请选择年份!");
					}else if(($('#yearBeg').val()=="" || $('#yearEnd').val()=="")&& $('#count-period').val()==4){
						$('#error-msg').text("请选择区间!");
					}else{
						$('#error-msg').text("");
						url = "<%=request.getContextPath()%>/system/chartReport_count.action?"
							+"year="+$('#year').val()+"&day="+$('#day').val()
							+"&month="+$('#month').val()+"&yearBeg="+$('#yearBeg').val()+"&yearEnd="+$('#yearEnd').val()
							+"&type="+$('#count-type').val()+"&period="+$('#count-period').val();
						$('#tabWindow').hide();
						$('#overlay').hide();
						showChart(url,'chart-container','line');
					}
				});
			});
		</script>
		<style type="text/css">
			body{
				font: 14px 微软雅黑;
			}
			.main {
				margin: 15px;
				padding: 15px;
				border-radius: 5px;
				box-shadow: inset 0 0 15px #06C
			}
			.chart-container{
				min-width: 310px;
				height: 400px;
				margin: 0;
			}
			.close-button {
				display: block;
				float: right;
				line-height: 1.3em;
				vertical-align: middle;
				position: relative;
				right: -3px;
				background-image: url(<%= request.getContextPath () %>/public/images/close2.png );
				background-repeat: no-repeat;
				text-decoration: none;
				top:2px;
			}
			.submit-btn {
				float: right;
				position: relative;
				right: 35px;
				top: 100px;
			}
			
			.tabWindow {
				display: none;
				left: 50%;
				margin-left: -300px;
				position: fixed;
				top: 17%;
				width: 500px;
				z-index: 3;
			}
			
			.overlay {
				display: block;
				background-color: black;
				left: 0pt;
				position: absolute;
				top: 0pt;
				z-index: 2
			}
			
			.transparent {
				filter: alpha(opacity = 50);
				-moz-opacity: 0.5;
				-khtml-opacity: 0.5;
				opacity: 0.5;
			}
			
			#chart-type-toggle{
				position:relative;
				float:right;
			}
			#chart-type-toggle-inner{
				position:absolute;
				top:-100px;
				left:-85px;
				width:100px
			}
			#chart-type-toggle img{
				display:inline;
				position:relative;
				top:2px
			}
			#chart-type-toggle label{
				display:block;
				margin-bottom:10px;
			}
			.time-link {
				text-decoration:none !important;
				border-bottom:1px dashed Black;
				cursor: pointer;
			}
			#chart-control{
				margin: 10px;
				padding: 10px;
			}
			.error-msg{
				position: absolute; 
				left:130px;
				top:130px;
				color:red;
			}
		</style>
	</head>
	<body>
		<div id="overlay" class="overlay transparent"></div>
		<div id="main" class="main">
			<div id="chart-container" class="chart-container"></div>
			<div id="chart-control" align="center">
				<label>统计类型:</label>
				<select id="count-type" style="font:12px 微软雅黑">
					<option value="1" selected="selected">用户访问量</option>
					<option value="2">关键字访问量</option>
					<option value="3">菜单访问量</option>
				</select>
				<label>时间区间:</label>
				<span id="popup" class="time-link">
					设置
				</span>
			</div>
			<div id="chart-type-toggle">
				<div id="chart-type-toggle-inner">
					<label>
						<input id="chart-type-line" name="chart_type" type="radio"
							value="line" checked="checked" onclick="renderChart('chart-container',this.value)">
						<img src="<%=request.getContextPath()%>/public/images/chart_line.png"
							width="16" height="16">
						线性图
					</label>
					<label>
						<input id="chart-type-bar" name="chart_type" type="radio"
							value="column" onclick="renderChart('chart-container',this.value)">
						<img src="<%=request.getContextPath()%>/public/images/chart_bar_h.png"
							width="16" height="16">
						柱状图
					</label>
				</div>
			</div>
		</div>
		<div id="tabWindow" class="tabWindow">
			<div class="tab-container">
				<ul class="tabs clearfix">
					<li>
						<a href=#>按小时统计</a>
					</li>
					<li class=active>
						<a href=#>按天统计</a>
					</li>
					<li>
						<a href=#>按月统计</a>
					</li>
					<li>
						<a href=#>按年统计</a>
					</li>
				</ul>
			</div>
			<div class="tab-body" id="tab-body-ourter">
				<a href="#" id="close-button" class="close-button">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a>
				<div id="tab-body-inner" class="tab-body-inner">
					<div id="hourly" style="display:none">
						选择日期：<input class="Wdate" id="day" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					<div id="daily" style="display:block">
						选择月份：<input class="Wdate" id="month" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/>
					</div>
					<div id="monthly" style="display:none">
						选择年份：<input class="Wdate" id="year" type="text" onClick="WdatePicker({dateFmt:'yyyy'})" />
					</div>
					<div id="yearly" style="display:none">
						选择区间：<input class="Wdate" id="yearBeg" type="text" onClick="WdatePicker({dateFmt:'yyyy'})" />—<input class="Wdate" id="yearEnd" type="text" onClick="WdatePicker({dateFmt:'yyyy'})" />
					</div>
				</div>
				<div id="error-msg" class="error-msg"></div>
				<div class="submit-btn">
					<input id="count-period" type="hidden" value="2"/>
					<input id="submit-btn" type="button" value="更新图表" />
				</div>
			</div>
		</div>
	</body>
</html>
