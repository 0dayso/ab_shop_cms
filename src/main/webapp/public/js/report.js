var beginTime = 20120101000000;
var endTime = 20140101000000;
var type = 2;
$(function () {
	showChart('system/userVisits_count.action?beginTime='+beginTime+'&endTime='+endTime+'&type='+type,'chart-container','line');
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
			$(this).addClass('active').siblings().removeClass('active');
			$('#tab-body-inner > div').hide().eq(index).show();
		});  
	});
	$('#count-type').change(function(){
		alert($('#count-type').val());
		var url = 'system/';
		var count_type = $('#count-type').val();
		switch(count_type){
			case 1:
			url+='userVisits_count.action?beginTime='+beginTime+'&endTime='+endTime+'&type='+type;
			break;
			case 2:
			url+='userVisits_count.action?beginTime='+beginTime+'&endTime='+endTime+'&type='+type;
			break;
			case 3:
			url="";
			break;
		}
		showChart(url,'chart-container','line');
	});
	
	$('#submit-btn').click(function(){
		var url = '/system/';
		var count_type = $('#count-type').val();
		switch(count_type){
			case 1:
			url+='userVisits_count.action?beginTime='+beginTime+'&endTime='+endTime+'&type='+type;
			break;
			case 2:
			url="";
			break;
			case 3:
			url="";
			break;
		}
		showChart(url,'chart-container','line');
		$('#tabWindow').hide();
		$('#overlay').hide();
	});
});