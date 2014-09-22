<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/searchman.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.set.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/dtree.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/kindEditor.css" rel="stylesheet" type="text/css">
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
		<link href="<%=request.getContextPath()%>/public/css/jquery.loadmask.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery.loadmask.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/markitup/jquery.markitup.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/markitup/set.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/dtree.js"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/kindeditor.js"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/zh_CN.js"></script>
  		<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ajaxfileupload.js"></script>
		<title>自动回复</title>
		<style type="text/css">
			input.text { margin-bottom:12px; width:95%; padding: .4em; }
			fieldset { padding:0; border:0; margin-top:25px; }
			.title_pic{
				font-size:14px;
			    margin-left:15px;
			    margin-top:5px;
			    width:220px;
			    word-wrap:break-word;
			}
			.title_pic_1,.title_pic_2{
			 	font-size:13px;
			    font-weight:bold;
			    margin-left:1%;
			    margin-top:5px;
			    margin-bottom:5px;
			}
			textarea {
				display: block;
			}
			.title_pic_2{
				color:#53BCE5;
			}
			.left_pic{
		       width:250px;
		       height: auto;
		       border:1px solid #B7D8ED;
		       float: left;
		   	}
			.right_pic{
			 	width:400px;
			    height:400px;
			    border:1px solid #B7D8ED;
			    margin-left:270px;
			}
			.news_describe{
				width:220px;
			    margin-left:15px;
			    height:auto;
			    margin-bottom:5px;
			    font-size:11px;
			}
			#img_pic{
			    width:220px;
			    height:200px;
			    margin-left:15px;
			    margin-bottom:5px;
			    margin-top:5px;
			}
			.input_pic{
				margin-left:1%;
			}
			.add_pic_check{
		       width: 100%;
		       height:90%;
		   	}
		   	.key_save_submit{
		    	float: left;
		    	margin-top: 10px;
		    	margin-left: 20px;
		    	width: 80px;
		   	}
		   	.ret_pic_pc{
				height: 100px;
				width: 180px;
			}
		</style>
		<script type="text/javascript">
			$(function(){
				$("#add_attention_replay").bind("click",function(){
					// 初始化内容
					editor1.html("");
					$("#attention-2").html("");
					$("#attention-3").html("");

					//菜单选中背景
					$("#left_menu_tr3").removeClass("tr_back_list");
				    $("#left_menu_tr2").removeClass("tr_back_list");
				    $("#left_menu_tr1").addClass("tr_back_list");
				    
					$.get("<%=request.getContextPath()%>/system/weixinKey_getText.action?time="+new Date().getTime(),{eventType:'Attention'},
						function(data){
							var vo = eval("(" + data + ")");
							if (vo.type == 'text') {
								attentionDetail(vo.name,vo.key,vo.type,vo.content,vo.picLink,vo.resourceId,vo.title);
							} else {
								attentionDetail(vo.name,vo.key,vo.type,vo.contents,vo.picLink,vo.resourceId,vo.title);
							}
						}
					);
					$("#attention_flag").val("0");
					$("#gird").show();
					$("#key-panel").hide();
		        });
				$("#auto_attention_replay").bind("click",function(){
					// 初始化
					editor1.html("");
					$("#attention-2").html("");
					$("#attention-3").html("");

					//菜单选中背景
					$("#left_menu_tr3").removeClass("tr_back_list");
				    $("#left_menu_tr1").removeClass("tr_back_list");
				    $("#left_menu_tr2").addClass("tr_back_list");
				    
					$.get("<%=request.getContextPath()%>/system/weixinKey_getText.action?time="+new Date().getTime(),{eventType:'AutoAnswer'},
						function(data){
							var vo = eval("(" + data + ")");
							if (vo.type == 'text') {
								attentionDetail(vo.name,vo.key,vo.type,vo.content,vo.picLink,vo.resourceId,vo.title);
							} else {
								attentionDetail(vo.name,vo.key,vo.type,vo.contents,vo.picLink,vo.resourceId,vo.title);
							}
						}
					);
					$("#attention_flag").val("1");
					$("#gird").show();
					$("#key-panel").hide();
		        });
				$("#key_auto_replay").bind("click",function(){
					//菜单选中背景
					$("#left_menu_tr1").removeClass("tr_back_list");
				    $("#left_menu_tr2").removeClass("tr_back_list");
				    $("#left_menu_tr3").addClass("tr_back_list");
				    
					$("#gird").hide();
					$("#key-panel").show();

					loadKeyTree();
		        });
				// 添加规则
				$("#add-key").bind("click",function(){
					$("#key-eidt-panel").show();
					$("#key_confirm_save").show();
					$("#key_confirm_update").hide();
					$("#key_name").val("");
					$("#key_word").val("");
					editor2.html("");
					$("#key-gird").tabs({active: "0"});
				});
			});
			
			// 处理tabs
			$(function(){
				var attentions = $('#gird').tabs({
					activate: selectFunction
				});
				var keys = $('#key-gird').tabs({
					show: {effect: "fade",duration: 200},
				    hide: {effect: "fade",duration: 200},
				    collapsible: false,
					activate: selectKeyFunction
				});
				function selectFunction(event, ui){
					var panelId = attentions.find( ".ui-tabs-active" ).attr( "aria-controls" );
					if (panelId == "attention-2") {
						openDialogPicture('attention');
					} else if (panelId == "attention-3") {
						openDialogNews('attention');
					}
				}
				function selectKeyFunction(event, ui){
					var panelId = keys.find( ".ui-tabs-active" ).attr( "aria-controls" );
					if (panelId == "key-2") {
						openDialogPicture('key');
					} else if (panelId == "key-3") {
						openDialogNews('key');
					}
				}
				// 规则保存
				$("#key_confirm_save").bind("click",function(){
					//var active = $( "#key-gird" ).tabs( "option", "active" );
					var selectId = d.selectedNode;
					if (selectId == null) { // 默认设置第一个节点，弥补dtree不足
						selectId = 0;
					}
					selectId = d.aNodes[selectId].id;
					var tName = $("#key_name").val();
					var tWord = $("#key_word").val();
					var tContent = editor2.html();
					var panelId = keys.find( ".ui-tabs-active" ).attr( "aria-controls" );
					if (tName == null || tName.length < 1) {
						$('.picurl').tip({width:'240',status:'error',content:'规则名不能为空！',dis_time:1000});
						return;
					}
					if (tWord == null || tWord.length < 1) {
						$('.picurl').tip({width:'240',status:'error',content:'关键字不能为空！',dis_time:1000});
						return;
					}
					if (selectId == null || selectId == "0") {
						selectId = "";
					}
					if (panelId == "key-1") {
						if (tContent == null || tContent.length < 1) {
							$('.picurl').tip({width:'240',status:'error',content:'回复信息不能为空！',dis_time:1000});
							return;
						}
						$.get("<%=request.getContextPath()%>/system/weixinKey_addText.action?time="+new Date().getTime(),
							{eventType:'KeyWord',tName:tName,tWord:tWord,tContent:tContent,superId:selectId},
							function(data){
								var vo = eval("(" + data + ")");
								loadKeyTree();
								if (vo.code=="ok") {
									$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
								} else {
									$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
								}
						});
					} else if (panelId == "key-2") {
						var resourceId = $("#key_image_confirm_resource_id").val();
						if (resourceId == 'undefined') {
							$('.picurl').tip({width:'240',status:'error',content:'缺少回复资源！',dis_time:1000});
							return;
						}
						$.get("<%=request.getContextPath()%>/system/weixinKey_addImage.action?time="+new Date().getTime(),
							{eventType:'KeyWord',tName:tName,tWord:tWord,resourceId:resourceId,superId:selectId},
							function(data){
								var vo = eval("(" + data + ")");
								loadKeyTree();
								if (vo.code=="ok") {
									$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
								} else {
									$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
								}
						});
					} else if (panelId == "key-3") {
						var resourceId = $("#key_news_confirm_resource_id").val();
						if (resourceId == 'undefined') {
							$('.picurl').tip({width:'240',status:'error',content:'缺少回复资源！',dis_time:1000});
							return;
						}
						$.get("<%=request.getContextPath()%>/system/weixinKey_addNews.action?time="+new Date().getTime(),
							{eventType:'KeyWord',tName:tName,tWord:tWord,resourceId:resourceId,superId:selectId},
							function(data){
								var vo = eval("(" + data + ")");
								loadKeyTree();
								if (vo.code=="ok") {
									$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
								} else {
									$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
								}
						});
					}
				});

				// 规则删除
				$("#key_confirm_delete").bind("click",function(){
					var selectId = d.selectedNode;
					if (selectId == null) { // 默认设置第一个节点，弥补dtree不足
						selectId = 0;
					}
					if(!window.confirm('你确定要删除该条回复吗？')){
		            	return;
		            }
					selectId = d.aNodes[selectId].id;
					$.get("<%=request.getContextPath()%>/system/weixinKey_deleteKey.action?time="+new Date().getTime(),
						{selectId:selectId},
						function(data){
							var vo = eval("(" + data + ")");
							loadKeyTree();
							if (vo.code=="ok") {
								$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
							} else if (vo.code=="no") {
								$('.picurl').tip({width:'240',status:'error',content:'对不起，请先删除下级规则！',dis_time:1000});
							} else {
								$('.picurl').tip({width:'240',status:'error',content:'对不起，删除失败！',dis_time:1000});
							}
					});
				});

				// 规则更新
				$("#key_confirm_update").bind("click",function(){
					var active = $( "#key-gird" ).tabs( "option", "active" );
					var selectId = d.selectedNode;
					if (selectId == 0) {
						$('.picurl').tip({width:'240',status:'error',content:'请选择左侧更新关键字！',dis_time:1000});
						return;
					}
					selectId = d.aNodes[selectId].id;
					var tName = $("#key_name").val();
					var tWord = $("#key_word").val();
					var tContent = editor2.html();
					var panelId = keys.find( ".ui-tabs-active" ).attr( "aria-controls" );
					if (tName == null || tName.length < 1) {
						$('.picurl').tip({width:'240',status:'error',content:'规则名不能为空！',dis_time:1000});
						return;
					}
					if (tWord == null || tWord.length < 1) {
						$('.picurl').tip({width:'240',status:'error',content:'关键字不能为空！',dis_time:1000});
						return;
					}
					if (selectId == null) {
						selectId = "";
					}
					if (panelId == "key-1") {
						if (tContent == null || tContent.length < 1) {
							$('.picurl').tip({width:'240',status:'error',content:'回复信息不能为空！',dis_time:1000});
							return;
						}
						$.get("<%=request.getContextPath()%>/system/weixinKey_updateText.action?time="+new Date().getTime(),
							{eventType:'KeyWord',tName:tName,tWord:tWord,tContent:tContent,selectId:selectId},
							function(data){
								var vo = eval("(" + data + ")");
								var bakSelected = d.selectedNode;
								loadKeyTree();
								d.s(bakSelected);
								if (vo.code=="ok") {
									$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
								} else {
									$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
								}
						});
					} else if (panelId == "key-2") {
						var resourceId = $("#key_image_confirm_resource_id").val();
						if (resourceId == 'undefined') {
							$('.picurl').tip({width:'240',status:'error',content:'缺少回复资源！',dis_time:1000});
							return;
						}
						$.get("<%=request.getContextPath()%>/system/weixinKey_updateImage.action?time="+new Date().getTime(),
							{eventType:'KeyWord',tName:tName,tWord:tWord,resourceId:resourceId,selectId:selectId},
							function(data){
								var vo = eval("(" + data + ")");
								loadKeyTree();
								if (vo.code=="ok") {
									$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
								} else {
									$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
								}
						});
					} else if (panelId == "key-3") {
						var resourceId = $("#key_news_confirm_resource_id").val();
						if (resourceId == 'undefined') {
							$('.picurl').tip({width:'240',status:'error',content:'缺少回复资源！',dis_time:1000});
							return;
						}
						$.get("<%=request.getContextPath()%>/system/weixinKey_updateNews.action?time="+new Date().getTime(),
							{eventType:'KeyWord',tName:tName,tWord:tWord,resourceId:resourceId,selectId:selectId},
							function(data){
								var vo = eval("(" + data + ")");
								loadKeyTree();
								if (vo.code=="ok") {
									$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
								} else {
									$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
								}
						});
					}
				});
			})
			// 处理弹出层
			$(function(){
				$( "#add_menu_inp" ).click(function() {
					openDialogForm();
				});
			})

			function openDialogForm() {
				var name = $("#name"),
				allFields = $([]).add(name),
				tips = $(".validateTips");
				$("#dialog-text").dialog({
					autoOpen: true,
					height: 200,
					width: 350,
					modal: true,
					buttons: {
						"确定": function() {
							var bValid = true;
							if (name.val().length < 1) {
								name.addClass( "ui-state-error" );
								return false;
							} 
							if (bValid) {
								$("#menu_config").append('<table id="left_menu" width="100%" border="0" cellspacing="0">' +
										'<tr><td id="td1"><img src="<%=request.getContextPath()%>/public/images/menu_1.png" border="0" align="absmiddle" /></td>' + 
										'<td colspan="2" id="td1_1"><a href="#">' + name.val() + '</a></td><td id="td1_2">'+
										'<a href="#"><img src="<%=request.getContextPath()%>/public/images/menu_add.png"/></a>'+
						  	            '<a href="#"><img src="<%=request.getContextPath()%>/public/images/pencil.png"/></a>'+
						  	            '<a href="#"><img src="<%=request.getContextPath()%>/public/images/delete_1.png"/></a></td></tr></table>');
								$( this ).dialog( "close" );
							}
						},
						"取消": function() {
							$( this ).dialog( "close" );
						}
					},
					close: function() {
						allFields.val( "" ).removeClass( "ui-state-error" );
					}
				});
			}
			function openDialogPicture(flag) {
				// 加载已经存在的资源图片
				$.get("<%=request.getContextPath()%>/system/weixinKey_getImage.action?time="+new Date().getTime(),{},
					function(data){
			    		var result = eval("(" + data + ")");
			    		var htmlVar = "";
			    		var path = result[0].path;
			    		var list = result[0].list;
			    		for (var i = 0; i < list.length; i++) {
			    			htmlVar += '<div><input type="radio" key1="' + (path + list[i].picName) + '" name="group-image" value="' + list[i].id + '" />选择</div><br/>' + '<a href = "' + (path + list[i].picName) + '" target="_blank"><img src="' + path + list[i].picName + '" alt="" /></a>';
				    	}
			    		$("#group-image-panel").html(htmlVar);
					}
				);
				// 阻止弹出层
				var flag_001 = $("#page-flag-open-div").val();
				if (flag_001 == "1") {
					return;
				}
				$("#dialog-image").dialog({
					autoOpen: true,
					height: 500,
					width: 600,
					modal: true,
					buttons: {
						"确定": function() {
							var obj = $("input[name='group-image']:checked");
							if (obj.length < 1) {
								$( this ).dialog( "close" );
								return;
							}
							var value = obj.val();
							var key1 = obj.attr("key1");
							var result = '<img class="ret_pic_pc" src="' + key1 + '" alt="" /><input type="text" style="display:none" value="' + value + '" id="' + flag + '_image_confirm_resource_id"/>';
							$("#" + flag + "-2").html(result);
							$( this ).dialog( "close" );
						},
						"取消": function() {
							$( this ).dialog( "close" );
						}
					},
					close: function() {
						//allFields.val( "" ).removeClass( "ui-state-error" );
					}
				});
			};

			// 处理图文
			$(function(){
				$("#createSingle").click(function() {
					$("#add-news-flag").val(1);
					$("#dialog-news-select-single").hide();
					$("#dialog-news-add-single").show();
					//openDialogNews('key');
			    });
			    $("#news_title").keyup(function() {
			     	$("#left_news_title").html($("#news_title").val());
			    });
			    $("#news_content").keyup(function() {
			     	$("#news_describe").html($("#news_content").val());
			    });
			})
			   
			function openDialogNews(flag) {
				// 加载已经存在的资源图文
				$.get("<%=request.getContextPath()%>/system/weixinKey_getNews.action?time="+new Date().getTime(),{},
					function(data){
			    		var result = eval("(" + data + ")");
			    		var htmlVar = "";
			    		var path = result[0].path;
			    		var list = result[0].list;
			    		for (var i = 0; i < list.length; i++) {
			    			htmlVar += '<div style="border:1px solid #c4c4c4;"><div><input type="radio" key1="' + (path + list[i].picName) + 
	    			 		   '" key2="' + list[i].title + '" key3= "' + list[i].content + '" name="group-' + flag + '-news" value="' + 
	    			 		     list[i].id + '" />选择</div>' + 
	    			 		   '<span>' + list[i].title + '</span><br/>' + 
	    					   '<a href = "' + (path + list[i].picName) + '" target="_blank"><img class="ret_pic_pc" src="' + 
	    					   	path + list[i].picName + '" alt="" /></a><br/>' + 
	    					   '<span>' + list[i].content + '</span></div></div>';
				    	}
			    		$("#group-news-panel").html(htmlVar);
					}
				);
				// 阻止弹出层
				var flag_001 = $("#page-flag-open-div").val();
				if (flag_001 == "1") {
					return;
				}
				$("#dialog-news").dialog({
					autoOpen: true,
					height: 500,
					width: 720,
					modal: true,
					buttons: {
						"确定": function() {
							var saveNewsFlag = $("#add-news-flag").val();
							if (saveNewsFlag == "1") { // 添加单条图文
								var newsTitle = $("#news_title").val();
								var newsContent = $("#news_content").val();
								var newsResourceId = $("#news_resource_id").val();
								if (newsTitle == 'undefined' || newsTitle == null && newsTitle.length < 1) {
									$('.picurl').tip({width:'240',status:'error',content:'亲，你忘记输入标题喔！',dis_time:1000});
									return;
								}
								if (newsContent == 'undefined' || newsContent == null && newsContent.length < 1) {
									$('.picurl').tip({width:'240',status:'error',content:'亲，多少输点吧！',dis_time:1000});
									return;
								}
								if (newsResourceId == 'undefined' || newsResourceId == null && newsResourceId.length < 1) {
									$('.picurl').tip({width:'240',status:'error',content:'亲，你真忍心浪费地盘！',dis_time:1000});
									return;
								}
								$.get("<%=request.getContextPath()%>/system/weixinKey_addSingleNews.action?time="+new Date().getTime(),
									{tTitle:newsTitle,tContent:newsContent,resourceId:newsResourceId},
									function(data){
										var result = eval("(" + data + ")");
							    		var htmlVar = "";
							    		var path = result[0].path;
							    		var list = result[0].list;
							    		
							    		for (var i = 0; i < list.length; i++) {
							    			htmlVar += '<div style="border:1px solid #c4c4c4;"><div><input type="radio" key1="' + (path + list[i].picName) + 
					    			 		   '" key2="' + list[i].title + '" key3= "' + list[i].content + '" name="group-' + flag + '-news" value="' + list[i].id + '" />选择</div>' + 
					    			 		   '<span>' + list[i].title + '</span><br/>' + 
					    					   '<a href = "' + (path + list[i].picName) + '" target="_blank"><img class="ret_pic_pc" src="' + 
					    					   	path + list[i].picName + '" alt="" /></a><br/>' + 
					    					   '<span>' + list[i].content + '</span></div></div>';
								    	}
								    	// 回到上一层
							    		$("#group-news-panel").html(htmlVar);
							    		$("#add-news-flag").val(0);
										$("#dialog-news-select-single").show();
										$("#dialog-news-add-single").hide();
									}
								);
							} else if (saveNewsFlag == "0") { // 选中单条图文
								var obj = $("input[name='group-" + flag + "-news']:checked");
								if (obj.length < 1) {
									$( this ).dialog( "close" );
									return;
								}
								var value = obj.val();
								var link = obj.attr("key1");
								var title = obj.attr("key2");
								var content = obj.attr("key3");
								var result = '<span>' + title + '</span><br/>' + 
		    					   			'<a href = "' + link + '" target="_blank"><img class="ret_pic_pc" src="' + link + 
		    					   			'" alt="" /></a><br/>' + 
		    					   			'<span>' + content + '</span>' + 
											'<input type="text" style="display:none" value="' + value + '" id="' + flag + '_news_confirm_resource_id"/>';

								$("#" + flag + "-3").html(result);
								$( this ).dialog( "close" );
							} 
						},
						"取消": function() {
							$("#add-news-flag").val(0);
							$("#dialog-news-select-single").show();
							$("#dialog-news-add-single").hide();
							$( this ).dialog( "close" );
						}
					},
					close: function() {
						//allFields.val( "" ).removeClass( "ui-state-error" );
					}
				});
			};

			function ajaxFileUpload1(){
				var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
				if (! reg.test($("#uploadify").val())) {
					$('.picurl').tip({width:'240',status:'right',content:'图片格式必须为jpg、jpeg、png、bmp!',dis_time:1000});
					return false;
				}
			    $.ajaxFileUpload({
			    	url:'<%=request.getContextPath()%>/system/weixinKey_upload.action',  
			      	secureuri:false,
			      	fileElementId:'uploadify',                         //文件选择框的id属性
			      	dataType: 'json',                                  //服务器返回的格式，可以是json		
			      	complete:function() {
						$("#loading").hide();
					},	
					success: function (data, status){
						if(data.status == "success"){
							var result = data;
							result = '<div><input type="radio" key1="' + result.link + '" name="group-image" value="' + result.id + 
		    				 		'" />选择</div><br/>' + '<a href = "' + result.link + 
		    				 		'" target="_blank"><img src="' + result.link + '" alt="" /></a></div>';
		    				$("#group-image-panel").append(result);
						} else{
							$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
						}
					},
					error: function (data, status, e){
						$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试!!!！',dis_time:1000});
					}
			    });
			}

			function ajaxFileUpload2(){
				var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
				if (! reg.test($("#news_file").val())) {
					$('.picurl').tip({width:'240',status:'right',content:'图片格式必须为jpg、jpeg、png、bmp!',dis_time:1000});
					return false;
				}
			    $.ajaxFileUpload({
			    	url:'<%=request.getContextPath()%>/system/weixinKey_upload.action',  
			      	secureuri:false,
			      	fileElementId:'news_file',                         //文件选择框的id属性
			      	dataType: 'json',                                     //服务器返回的格式，可以是json			
					success: function (data, status){
						if(data.status == "success"){
							var result = data;
							result = '<div id="news_pic"><img id="img_pic" src="' + result.link + '" alt="" />' + 
									 '<input type="radio" style="display:none" name="news_resource_id" id="news_resource_id" value="' + result.id + '" />';
					    	$("#news_pic").html(result);
						} else{
							$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
						}
					},
					error: function (data, status, e){
						$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
					}
			    });
			}

			function loadKeyTree() {
				d = new dTree('d',"<%=request.getContextPath()%>/public/images/dtree/");
				d.add(0,-1,'关键词');
				$.ajax({
					url:"<%=request.getContextPath()%>/system/weixinKey_buildKeyTree.action?time=" + new Date().getTime(),
		            type:"POST",
		            dataType:"json",
		            timeout:"10000",
		            data:{},
		            success:function(data){
		            	var result = data;
		            	if (result.code == "ok") {
							result = result.list;
							for (var i = 0; i < result.length; i++) {
								if (result[i].type == 'text') {
									/*d.add(result[i].id,result[i].subId,result[i].name,'javascript:keyDetail(\'' + 
											result[i].id + '\', \'' + result[i].key + '\', \'' + 
											result[i].type + '\', \'' + result[i].content + '\',\'' + 
											result[i].picLink + '\', \'' + result[i].resourceId + '\', \'' + 
											result[i].title + '\');');*/
									d.add(result[i].id,result[i].subId,result[i].name, "javascript:keyDetail('" + result[i].id + "');");
								} else if(result[i].type == 'image' || result[i].type == 'news') {
									d.add(result[i].id,result[i].subId,result[i].name, "javascript:keyDetail('" + result[i].id + "');");
									/*d.add(result[i].id,result[i].subId,result[i].name,'javascript:keyDetail(\'' + 
											result[i].name + '\', \'' + result[i].key + '\', \'' + 
											result[i].type + '\', \'' + result[i].contents + '\',\'' + 
											result[i].picLink + '\', \'' + result[i].resourceId + '\', \'' + 
											result[i].title + '\');');*/
								}
							}
		            	} else {
		            		$('.picurl').tip({width:'240',status:'error',content:'对不起，加载失败！',dis_time:1000});
		            	}
						$("#key-tree").html(d.toString());
		            }
		 		});
			}

			function attentionDetail(name,key,type,content,picLink,resourceId,title){
				if (type == 'text') {
					editor1.html(content);
					$("#gird").tabs({active: "0"});
				} else if (type == 'image') {
					$("#page-flag-open-div").val("1");
					editor1.html("");
					var result = '<img class="ret_pic_pc" src="' + picLink + '" alt="" /><input type="text" style="display:none" value="' + 
								  resourceId + '" id="attention_image_confirm_resource_id"/>';
					$("#attention-2").html(result);
					$("#gird").tabs({active: 1});
					$("#page-flag-open-div").val("0");
				} else if (type == 'news') {
					$("#page-flag-open-div").val("1");
					editor1.html("");
					var result = '<span>' + title + '</span><br/>' + 
					   			 '<a href = "' + picLink + '" target="_blank"><img class="ret_pic_pc" src="' + picLink + 
					   			 '" alt="" /></a><br/>' + 
					   			 '<span>' + content + '</span>' + 
								 '<input type="text" style="display:none" value="' + resourceId + '" id="attention_news_confirm_resource_id"/>';
					$("#attention-3").html(result);
					$("#gird").tabs({active: 2});
					$("#page-flag-open-div").val("0");
				} 
			}

			function keyDetail(id,key,type,content,picLink,resourceId,title){
				$("#key-eidt-panel").show();
				$("#key_confirm_save").hide();
				$("#key_confirm_update").show();

				$.ajax({
					url:"<%=request.getContextPath()%>/system/weixinKey_getKeyDetail.action?time=" + new Date().getTime(),
		            type:"POST",
		            dataType:"json",
		            timeout:"10000",
		            data:{id:id},
		            success:function(data){
		            	var vo = data;
		            	if (vo.code == "ok") {
							$("#key_name").val(vo.name);
							$("#key_word").val(vo.key);
							
							if (vo.type == 'text') {
								editor2.html(vo.content);
								$("#key-gird").tabs({active: "0"});
							} else if (vo.type == 'image') {
								$("#page-flag-open-div").val("1");
								editor2.html("");
								var result = '<img class="ret_pic_pc" src="' + vo.picLink + '" alt="" /><input type="text" style="display:none" value="' + 
											  vo.id + '" id="key_image_confirm_resource_id"/>';
								$("#key-2").html(result);
								$("#key-gird").tabs({active: 1});
								$("#page-flag-open-div").val("0");
							} else if (vo.type == 'news') {
								$("#page-flag-open-div").val("1");
								editor2.html("");
								var result = '<span>' + vo.title + '</span><br/>' + 
								   			 '<a href = "' + vo.picLink + '" target="_blank"><img class="ret_pic_pc" src="' + vo.picLink + 
								   			 '" alt="" /></a><br/>' + 
								   			 '<span>' + vo.contents + '</span>' + 
											 '<input type="text" style="display:none" value="' + vo.id + '" id="key_news_confirm_resource_id"/>';
								$("#key-3").html(result);
								$("#key-gird").tabs({active: 2});
								$("#page-flag-open-div").val("0");
							} 
		            	} else {
		            		$('.picurl').tip({width:'240',status:'error',content:'对不起，加载失败！',dis_time:1000});
		            	}
		            }
		 		});
			}
			//Node js调用方法
			function nodeInit() {
				$("#key_confirm_save").show();
				$("#key_confirm_update").hide();
				$("#key_name").val("");
				$("#key_word").val("");
				editor2.html("");
			}
			
			function hiddenKey(){
				$("#key-eidt-panel").hide();
			}
			
			function keys(layer, i, key) { // 消除jquery ui tabs影响
				var active = $("#" + layer).tabs( "option", "active" );
				if (1 == active && i == 1) {
					openDialogPicture(key);
				}
				if (2 == active && i == 2) {
					openDialogNews(key);
				}
			}
			
			function saveAttention() {
				var panelId = $("#gird").tabs( "option", "active" );
				var flag = $("#attention_flag").val();
				var flagEvent = "Attention";
				if (flag == "1") {
					flagEvent = "AutoAnswer";
				}
				if (panelId == "0") {
					var textContent = editor1.html();
					if (textContent == null || textContent.length < 0) {
						$('.picurl').tip({width:'240',status:'error',content:'至少输入一个字符',dis_time:1000});
						return;
					}
					$.get("<%=request.getContextPath()%>/system/weixinKey_addText.action?time="+new Date().getTime(),{eventType:flagEvent,textContent:textContent},
						function(data){
							var vo = eval("(" + data + ")");
							if (vo.code=="ok") {
								$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
							} else {
								$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
							}
						}
					);
				} else if (panelId == "1") {
					var resourceId = $("#attention_image_confirm_resource_id").val();
					if (resourceId == 'undefined') {
						$('.picurl').tip({width:'240',status:'error',content:'缺少回复资源.',dis_time:1000});
						return;
					}
					$.get("<%=request.getContextPath()%>/system/weixinKey_addImage.action?time="+new Date().getTime(),{eventType:flagEvent,resourceId:resourceId},
						function(data){
							var vo = eval("(" + data + ")");
							if (vo.code=="ok") {
							    $('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
							} else {
								$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
							}
						}
					);
				} else if (panelId == "2") {
					var resourceId = $("#attention_news_confirm_resource_id").val();
					if (resourceId == 'undefined') {
						$('.picurl').tip({width:'240',status:'error',content:'缺少回复资源.',dis_time:1000});
						return;
					}
					$.get("<%=request.getContextPath()%>/system/weixinKey_addNews.action?time="+new Date().getTime(),{eventType:flagEvent,resourceId:resourceId},
						function(data){
							var vo = eval("(" + data + ")");
							if (vo.code=="ok") {
								$('.picurl').tip({width:'240',status:'right',content:'恭喜您，更新成功！',dis_time:1000});
							} else {
								$('.picurl').tip({width:'240',status:'error',content:'对不起，更新失败！',dis_time:1000});
							}
						}
					);
				}
			}

			var editor1,editor2;
			KindEditor.ready(function(K) {
				K.each({
					'plug-align' : {
						name : '对齐方式',
						method : {
							'justifyleft' : '左对齐',
							'justifycenter' : '居中对齐',
							'justifyright' : '右对齐'
						}
					},
					'plug-order' : {
						name : '编号',
						method : {
							'insertorderedlist' : '数字编号',
							'insertunorderedlist' : '项目编号'
						}
					},
					'plug-indent' : {
						name : '缩进',
						method : {
							'indent' : '向右缩进',
							'outdent' : '向左缩进'
						}
					}
				},function( pluginName, pluginData ){
					var lang = {};
					lang[pluginName] = pluginData.name;
					KindEditor.lang( lang );
					KindEditor.plugin( pluginName, function(K) {
						var self = this;
						self.clickToolbar( pluginName, function() {
							var menu = self.createMenu({
									name : pluginName,
									width : pluginData.width || 100
								});
							K.each( pluginData.method, function( i, v ){
								menu.addItem({
									title : v,
									checked : false,
									iconClass : pluginName+'-'+i,
									click : function() {
										self.exec(i).hideMenu();
									}
								});
							})
						});
					});
				});
				editor1 = K.create('#attention_content', {
					themeType : 'qq',
					items : [
						'bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','plug-order','plug-indent','link'
					]
				});
				editor2 = K.create('#key_content', {
					themeType : 'qq',
					items : [
						'bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','plug-order','plug-indent','link'
					]
				});
			});
		</script>
	</head>
	<body>
		<div  class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">自动回复定义</font></span>
    	</div>
		<input type="text" style="display:none" value="0" id="page-flag-open-div"/>
    	<div class="main">
			<div id="selector">
				<div class="inner">
					<div class="headerTitle">
					   <span>自动回复</span>
				   	</div>
					<div id="menu_config">
						<input type="text" style="display:none" value="0" id="attention_flag"/>
						<table id="left_menu" width="100%" border="0" cellspacing="0">
							<tr id="left_menu_tr1">
								<td><img src="<%=request.getContextPath()%>/public/images/menu_1.png" border="0" align="absmiddle" /></td>
								<td colspan="2"><span id="add_attention_replay">被添加自动回复</span></td><td id="td1_2"></td>
							</tr>
							<tr id="left_menu_tr2">
								<td><img src="<%=request.getContextPath()%>/public/images/menu_1.png" border="0" align="absmiddle" /></td>
								<td colspan="2"><span id="auto_attention_replay">消息自动回复</span></td><td id="td1_2"></td>
							</tr>
							<tr id="left_menu_tr3">
								<td><img src="<%=request.getContextPath()%>/public/images/menu_1.png" border="0" align="absmiddle" /></td>
								<td colspan="2"><span id="key_auto_replay">关键词自动回复</span></td><td id="td1_2"></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div id="xbar"></div>
			<div id="gird">
				<div class="add_pic_check">
					<ul>
						<li><a href="#attention-1">文字</a></li>
						<li><a href="#attention-2" onclick="keys('gird', 1, 'attention');">图片</a></li>
						<li><a href="#attention-3" onclick="keys('gird', 2, 'attention');">图文消息</a></li>
						<li><a href="#attention-4">语音</a></li>
						<li><a href="#attention-5">视频</a></li>
						
					</ul>
					<div id="attention-1">
						<s:textarea id="attention_content" name="attention_content" style="width:400px;height:330px;visibility:hidden;"></s:textarea>
					</div>
					<div id="attention-2"></div>
					<div id="attention-3"></div>
					<div id="attention-4"></div>
					<div id="attention-5"></div>
				 </div>
				 <div class="submit_save">
				 	<input type="button" value="&nbsp;&nbsp;保存&nbsp;&nbsp;" id="key_save_submit" onclick="saveAttention();" class="add_menu_inp key_save_submit"/>
				 </div>
			</div>
			<div id="key-panel" style="display: none;margin-left:210px;">
				<div class="inner1" style="width:200px;height: 100%;">
					<div class="headerTitle">
						<span>关键词自动回复</span>
						<span><input type="button" name="add-key" id="add-key" value="添加规则" class="add_menu_inp"/></span>
					</div>
					<div id="key-tree" style="margin-top: 2px;"></div>
				</div>
				<div class="inner1" id="key-eidt-panel" style="display:none;width:640px;height:100%;margin-left: 215px;">
					<div class="headerTitle">
					   <span>新规则</span>
				   	</div>
					<div>
						规则名<input type="text" name="key_name" id="key_name" value="" style="margin-top:10px; width:300px;" class="text ui-widget-content ui-corner-all" />
						规则名最多60个字
					</div>
					<div class="inner" style="float:left;width:236px;height:70%;">
						<div class="headerTitle"><span>关键词（多关键词顿号分隔）</span></div>
					   	<div><textarea rows="12" cols="25" name="key_word" id="key_word" value=""></textarea></div>
					</div>
					<div class="inner" style="float:right;width:360px;height:70%;">
						<div class="headerTitle"><span>回复消息</span></div>
					   	<div id="key-gird">
							<ul>
								<li><a href="#key-1">文字</a></li>
								<li><a href="#key-2" onclick="keys('key-gird', 1, 'key');">文件</a></li>
								<li><a href="#key-3" onclick="keys('key-gird', 2, 'key');">图文</a></li>
							</ul>
							<div id="key-1">
								<textarea id="key_content" name="key_content" value="" style="width:300px;height:180px;visibility:hidden;"></textarea>
							</div>
							<div id="key-2">
									
							</div>
							<div id="key-3"></div>
						</div>
					</div>
					<div style="float: right; margin-top: 10px;">
						<input type="button" value="&nbsp;&nbsp;保存&nbsp;&nbsp;" id="key_confirm_save" class="add_menu_inp_1"/>
						<input type="button" style="display:none" value="&nbsp;&nbsp;更新&nbsp;&nbsp;" id="key_confirm_update" class="add_menu_inp_1"/>
						<input type="button" value="&nbsp;&nbsp;删除&nbsp;&nbsp;" id="key_confirm_delete" class="add_menu_inp_1"/>
					</div>
				</div>
				</div>
			</div>
			<div id="footer">
				<div id="innerFooter">
					<p id="copyright">
						Copyright ?
						<a rel="license" href="/" title="">知识共享组织</a>
					</p>
				</div>
			</div>
		</div>
		<div id="dialog-text" title="添加菜单" style="display: none">
			<form>
				<fieldset>
					<label for="name">菜单名称</label>
					<input type="text" name="name" id="name" class="text ui-widget-content ui-corner-all" />
				</fieldset>
			</form>
		</div>
		<div id="dialog-image" title="选择图片" style="display: none">
			<form>
				<fieldset>
					<input type="file" name="fileInput" onchange="ajaxFileUpload1();" id="uploadify" class="text ui-widget-content ui-corner-all"/>
				</fieldset>
				<fieldset id="group-image-panel"></fieldset>
			</form>
		</div>
		<div id="dialog-news" title="选择图文" style="display: none">
			<input type="text" style="display:none" name="add-news-flag" id="add-news-flag" value="0"/>
			<div id="dialog-news-select-single">
				<form>
					<fieldset>
						<input type="button" name="createSingle" id="createSingle" value="创建单图文" class="business_btn"/>
						<input type="button" name="createMult" id="createMult" value="创建多图文" class="business_btn"/>
					</fieldset>
					<fieldset id="group-news-panel"></fieldset>
				</form>
			</div>
			<div id="dialog-news-add-single" style="display: none">
				<form>
					<fieldset>
						<div class="left_pic">
							<div class="title_pic" id="left_news_title">标题</div>
							<div class="title_pic">2013-08-12</div>
							<div id="news_pic"><img id="img_pic" src="<%=request.getContextPath()%>/public/images/tubiao-01.png" /></div>
							<div class="news_describe" id="news_describe"></div>
						</div>
						<div class="right_pic">
							<div class="title_pic_1">标题</div>
							<input type="text" name="name" id="news_title" class="text ui-widget-content ui-corner-all input_pic" />
							<div class="title_pic_1">封面</div>
							<input type="file" name="fileInput" onchange="ajaxFileUpload2();" id="news_file" class="text ui-widget-content ui-corner-all input_pic" value="上传" />
							<div class="title_pic_2">添加摘要 正文</div>
							<div style="margin-left: 1%;">
								<textarea id="news_content" onkname="news_content" cols="50" rows="12"></textarea>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>
		<div style="opacity:0;  filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px;">
        		<input id="urlt" class="picurl" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" type="file">
        </div>
	</body>
</html>