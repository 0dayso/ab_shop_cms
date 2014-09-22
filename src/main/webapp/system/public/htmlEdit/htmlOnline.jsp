<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!--
Copyright (c) 2003-2013, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.md or http://ckeditor.com/license
-->
<html>
<head>
	<head>
	<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/public/ckeditor/samples/sample.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/public/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/ckeditor/ckeditor.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.core.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.mouse.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.tabs.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.dialog.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.button.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.draggable.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.position.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.resizable.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.effect.js"></script>
	
	<style type="text/css">
	    .fildSet{position:relative; overflow:hidden;}
	    .label1 {float: left; width:80px;}
	    .select1{float:left:}
	    select,input{
	       width:200px;
	    }
	</style>
	
	<script type="text/javascript">
		
		$(function(){
			CKEDITOR.instances.content.execCommand('maximize');
			CKEDITOR.on('instanceReady', function(evt){
	        	var editor = evt.editor;
	          	editor.execCommand('maximize');
	          	saveOk();
	       	});
			
			$.get("<%=request.getContextPath()%>/system/weixinMenu_selectMenu.action?time="+new Date().getTime(),{type:'news'},
				function(data){
					$("#menuList").html(data);
					changeSel1();
				});
			
			$.get("<%=request.getContextPath()%>/system/weixinKey_selectKey.action?time="+new Date().getTime(),{type:'news'},
				function(data){
					$("#keyList").html(data);
					changeSel2();
				});

		});
		
		function changeSel1(){
			$("#menu").change(function(){
				var menuId = $("#menu").val();
				if(menuId != ""){ //设置关键词不可用状态
					$("#key").attr("disabled","disabled");
				    $("#key").css(" background-color","#F0F0F0");
				}else{
					$("#key").removeAttr("disabled");
				    $("#key").css(" background-color","");
				}
			});
		}
		
		function changeSel2(){
			$("#key").change(function(){
				var menuId = $("#key").val();
				if(menuId != ""){ //设置关键词不可用状态
					$("#menu").attr("disabled","disabled");
				    $("#menu").css(" background-color","#F0F0F0");
				}else{
					$("#menu").removeAttr("disabled");
				    $("#menu").css(" background-color","");
				}
			});
		}
		
		function saveOk(){
			$("#cke_15").removeClass("cke_button_disabled");
			$("#cke_15").addClass("cke_button_off");
			$("#cke_15").unbind("clink");
			$("#cke_15").bind('click',function(){
				noticeSec();
			});
		}
		function noticeSec(){
			var content = CKEDITOR.instances.content.getData();
			if (content == null || content.length < 1) {
				return;
			}
			openSelectDialog();
		}

		function openSelectDialog() {
			$("#layer1").css("display", "none");
			$("#dialog-select").dialog({
				autoOpen: true,
				height: 500,
				width: 600,
				modal: true,
				buttons: {
					"确定": function() {
						var name = $("#name").val();
						if(name == ""){
							alert("微页名称不能为空！");
						}
						var content = CKEDITOR.instances.content.getData();
						var menuId = $("#menu").val();
						var keyId = $("#key").val();
						$.get("<%=request.getContextPath()%>/system/customer_addHtml.action?time="+new Date().getTime(),
							{name:name,content:content,menuId:menuId,keyId:keyId},
							function(data){
								var result = '<a href="' + data + '" style="color:red" target="_blank" alt = "点击预览">点击预览此微页</a>';
								$("#dialog-result").html(result);
								$("#dialog-result").css("display", "");
								//$("#layer1").css("display", "");
								//$( this ).dialog( "close" );
							}
						);
					},
					"取消": function() {
						$("#layer1").css("display", "");
						$("#dialog-result").css("display", "none");
						$( this ).dialog( "close" );
					}
				},
				close: function() {
					$("#dialog-result").css("display", "none");
					$("#layer1").css("display", "");
				}
			});
		};
	</script>
</head>
<body>
	<div id="layer1">
		<textarea class="ckeditor" cols="200" name="content" id="content" rows="100" tabindex="1"></textarea>
	</div>
	<div id="dialog-select" title="应用选择" style="display: none; z-index: 100">
	    <div class="fildSet">
			<div class="label1" ><label for="menu">微页名称</label></div>
			<div class="select1"><input id="name" type="text" name="name"/></div>
		</div>
		<div class="fildSet">
			<div class="label1" ><label for="menu">菜单名称</label></div>
			<div class="select1" id="menuList"></div>
		</div>
		<div class="fildSet">
			<div class="label1"><label for="key">关键词</label></div>
			<div class="select1" id="keyList"></div>
		</div>
		<div id="dialog-result" style="display: none;"></div>
	</div>
</body>
</html>
