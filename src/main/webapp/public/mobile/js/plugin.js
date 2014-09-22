//depends on js file jquery.blockUI.js and jquery.js
//depends on css file plugin.css
//融易通提示框插件
jQuery.rytong={
	sAlert:function(opts){
		//初始化默认参数
		var defaults={
			title:'提示',
			content:'提示内容',
			buttonValue:'确定',
			callback:function(){
			}
		};
		//初始化参数
		var params=jQuery.extend({},defaults,opts);
		//拼接内容
		var header='<div class="alertheader">'+params.title+'</div>';
		var content='<div class="alertcontent">'+params.content+'</div>';
		var confirmButton='<div class="sConfirmButton">'+params.buttonValue+'</div>';
		var footer='<div class="alertfooter">'+confirmButton+'</div>';
		var singleTemplate='<div class="alert">'+header+content+footer+'</div>';
		//显示提示框
		$.blockUI({
			message:singleTemplate,
			css:{
				left:'20%',
				right:'20%',
	            width: 'auto',
	            height:'auto',
	            top:'20%',
	            border:'radius 8px',
	            borderRadius: '8px',
	            cursor:'auto'
	 		},
			overlayCSS:{
	 			width:'100%',
	 			height:'100%',
				backgroundColor:'#B2B2B2',
				cursor:	'auto'
				}
 		});
		//$('.blockOverlay').click($.unblockUI);
		//初始化事件
		$("div.sConfirmButton").click(function(){
			params.callback();
			$.unblockUI();
		});
	},
	dAlert:function(opts){
		//默认值
		var defaults={
			title:'提示',
			content:'提示内容',
			confirmButton:'确定',
			confirmCallback:function(){
			},
			cancelButton:'取消',
			cancelCallback:function(){
			}
		};
		//初始化参数
		var params=jQuery.extend({},defaults,opts);
		//拼接内容
		var header='<div class="alertheader">'+params.title+'</div>';
		var content='<div class="alertcontent">'+params.content+'</div>';
		var confirmButton='<div class="dConfirmButton">'+params.confirmButton+'</div>';
		var cancelButton='<div class="dCancelButton" >'+params.cancelButton+'</div>';
		var buttons='<div class="footerButton">'+confirmButton+cancelButton+'</div>';
		var footer='<div class="alertfooter">'+buttons+'</div>';
		var doubleTemplate='<div class="alert">'+header+content+footer+'</div>';
		//显示提示框
		$.blockUI({
			message:doubleTemplate,
			css:{
				left:'20%',
				border:'radius 8px',
	            width: '250px',
	            top:'20%',
	            borderRadius: '8px',
	            cursor:'auto'
	 		},
			overlayCSS:{
				backgroundColor:'#B2B2B2',
				cursor:	'auto'
			}
 		});
		//初始化事件
		//$('.blockOverlay').click($.unblockUI);
		$('div.dConfirmButton').click(function(){
			params.confirmCallback();
			$.unblockUI();
		});
		$('div.dCancelButton').click(function(){
			params.cancelCallback();
			$.unblockUI();
		});
	}
};
String.prototype.trim=function()
{
	return this.replace(/(^\s*)|(\s*$)/g,"");
};
String.prototype.startsWith = function (str){
	return this.indexOf(str) == 0;
};