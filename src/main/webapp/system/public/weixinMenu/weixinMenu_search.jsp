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
		<title>微信菜单自定义</title>
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
			textarea {
				display: block;
			}
			.title_pic_1,.title_pic_2{
			 	font-size:13px;
			    font-weight:bold;
			    margin-left:1%;
			    margin-top:5px;
			    margin-bottom:5px;
			}
			.title_pic_2{
				color:#53BCE5;
			}
			.left_pic,.left_pic_mult{
		       width:250px;
		       height: auto;
		       border:1px solid #B7D8ED;
		       float: left;
		   	}
			.right_pic,.right_pic_mult{
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
		   	.maintd{
		       width: 85px;
		   	} 
		   	.edit_menu{
		    	margin-left:5px;
		   	}
		   	.delete_menu{
		    	margin-left:5px;
		   	}
		   	.edit_menu_1{
		    	margin-left:21px;
		   	}
		   	.delete_menu_1{
		    	margin-left:5px;
		   	}
		   	.footer {
				height: 29px;
				width: 100%;
				background-color: #E6F5FC;
				line-height: 29px;
				border: 1px solid #B7D8ED;
				margin-left: -1px;
				margin-top: 191px;
				margin-bottom: 0px;
			}
			.ret_pic_pc{
				height: 100px;
				width: 180px;
			}
		</style>
		<script type="text/javascript">
			function addChildMenu(pos, lastMenuId) {
				openDialogMenu("left_menu_" + pos, 1, 'add', null, pos, null, lastMenuId);
			}

			function editMenu(pos, f, tValue, menuId, lastMenuId) {
				if (f == 0) { //编辑主菜单
					var value = $("#span_menu_name_" + pos).html();
					openDialogMenu("left_menu_" + pos, f, 'edit', value, pos, menuId, null);
				} else if (f == 1) { // 编辑子菜单
					var value = $("#span_child_menu_name_" + pos).html();
					openDialogMenu("menu_config", f, 'edit', value, pos, menuId, lastMenuId);
				}
			}

			function loadMenu() {
				$.get("<%=request.getContextPath()%>/system/weixinMenu_getAll.action?time="+new Date().getTime(),{name:'CZ'},
					function(data){
						var result = eval('(' + data + ')');
						var s = '';
						for (var i = 0; i < result.length; i++) {
							var level1 = result[i].level1;
							s += '<jkfs><table id="left_menu_' + i + '" class="left_menu" width="100%" border="0" cellspacing="0"><tr id="menu_list_' + i + 
							     '" onclick="add_back_col(' + i + ',' +  level1.id + ')"><td><img src="<%=request.getContextPath() %>/public/images/menu_2.png" border="0" align="absmiddle" /></td>';
							s += '<td colspan="2"  class="maintd"><span id="span_menu_name_' + i + '">' + level1.name + '</span></td><td>' + 
								'<a href="javascript:addChildMenu(' + i + ',' +  level1.id + ');"><img src="<%=request.getContextPath() %>/public/images/tb2.png"/></a>'+
			  	            	'<a href="javascript:editMenu(' + i + ',0,\'' + level1.name + '\',' + level1.id + ')"><img class="edit_menu"  src="<%=request.getContextPath() %>/public/images/pencil.png"/></a>'+
			  	            	'<a href="javascript:deleteMenu(' + level1.id + ')"><img class="delete_menu" src="<%=request.getContextPath() %>/public/images/delete_1.png"/></a></td>';
							s += '</tr>';
							var level2 = result[i].level2;
							if (level2 != "" && level2.length > 0) {
								for (var j = 0; j < level2.length; j++) {
									s += '<tr id="menu_list_'+i+'_'+j+'" onclick="add_back_col(\'' + i + '_' + j + '\',' + level2[j].id + ')"><td id="td1"></td><td><img src="<%=request.getContextPath() %>/public/images/menu_3.png" border="0" align="absmiddle" /></td>';
									s += '<td><span id="span_child_menu_name_' + i + '_' + j + '">' + level2[j].name + '</span></td>';
									s += '<td><a href="javascript:editMenu(\'' + i + '_' + j + '\',1,\'' + level2[j].name + '\',' + level2[j].id + ',\'' + level2[j].subId + '\')"><img class="edit_menu_1" src="<%=request.getContextPath() %>/public/images/pencil.png"/></a>';
									s += '<a href="javascript:deleteMenu(' + level2[j].id + ')"><img class="delete_menu_1" src="<%=request.getContextPath() %>/public/images/delete_1.png"/></a>';
									s += '</td></tr>'
								}
							}
							s += '</table>';
						}
						$("#menu_config").html(s);
					}
				);
			}

			$(function(){
				loadMenu();
			});
			// 处理tabs
			$(function(){
				var $tabs = $('#gird').tabs({
					show: {effect: "fade",duration: 200},
				    hide: {effect: "fade",duration: 200},
				    collapsible: false,
					activate: selectMenuFunction
				});
				function selectMenuFunction(event, ui){
					var panelId = $tabs.find( ".ui-tabs-active" ).attr( "aria-controls" );
					if (panelId == "tabs-2") {
						openDialogImage();
					} else if (panelId == "tabs-3") {
						openDialogNews();
					}
				}
			})
			// 处理弹出层
			$(function(){
				$( "#add_menu_inp" ).click(function() {
					openDialogMenu("menu_config", 0, 'add');
				});
				add_back_col("0","1");
			})
			// 处理图文
			$(function(){
				$("#createSingle").click(function() {
					$("#add-news-flag").val(1);
					$("#dialog-news-select-single").hide();
					$("#dialog-news-add-single").show();
					//openDialogNews('key');
			    });
			    //多图文
			    $("#createMult").click(function() {
					$("#add-news-flag").val(1);
					$("#dialog-news-select-single").hide();
					$("#dialog-news-add-multi").show();
			    });
			    $("#title_input").keyup(function() {
					changeText();
				});
				$("#pic_content_add").click(function() {
					addPicConDiv();
				});
				editContPicShow('1');
				editContPic('1');
			});
			
			//多图
			function addPicConDiv(){
				var cl = $("#counterList").val();
				var ind = parseInt(cl)+1;
				$("#counterList").val(ind);
				var listAdd = '<div class="pic_content_'+ind+'" id="pic_content_'+ind+'" onclick="editContPic(\''+ind+'\')">'+
			                  '<img id="img_pic_1" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>'+
			                  '<img class="img_pic_2" onclick="deleteContPic(\''+ind+'\')" src="<%=request.getContextPath() %>/public/images/delete.png"/>'+
							  '<div class="pic_content_1_1"><div class="" id="title">标题</div>'+
							  '<input type="text" name="name" id="title_input_'+ind+'" class="text ui-widget-content ui-corner-all input_pic" />'+
						      '<div class="">描述</div><div class="describe_1">在sd卡酒喝多了克里斯撒旦个外人投票</div></div></div>';
			    $("#pic_content_list").append(listAdd);
			    
			    $("#pic_content_"+ind).css({
					"width": "100%",
					"border-top": "1px solid #B7D8ED",
					"height":"auto",
					"display": "table"
			    });
			}
			
			//delete
			function deleteContPic(target){
				$("#pic_content_"+target).remove();
				editContPic("1");
			}
			//edit
			function editContPic(target){
		    	var t =	document.getElementById("pic_content_"+target).offsetTop;
				if(target!="1"){
					$(".right_pic_mult").css("margin-top",(t-100)+"px");
				}else {
					$(".right_pic_mult").css("margin-top","0px");
				}
				var valShow =$("#title_input_"+target).val();
				$("#title_input_multi").val(valShow);
				$("#right_pic_multi").attr("onkeyup","editContPicShow("+target+")");
				//选中
				$("#pic_content_list div").removeClass("tr_back_list");
		    	$("#pic_content_"+target).addClass("tr_back_list");
			}
			//编辑页
			function editContPicShow(target){
				var valShow =$("#title_input_multi").val();
				$("#title_input_"+target).val(valShow);
			}
			//
			function describeRuleLength(target){
				var desText = $("#describe_rule_"+target).text();
				$('.picurl').tip({width:'240',status:'right',content:desText,dis_time:1000});
			}
			
			function openDialogMenu(layer, f, model, tValue, pos, menuId, lastMenuId) {
				var name = $("#menu_name");
				var allFields = $([]).add(name);
				var tips = $(".validateTips");
				if (model == "edit") {
					name.val(tValue);
				}
				$("#dialog-form").dialog({
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
								if (f == 0 && model == "add") {
									$.get("<%=request.getContextPath()%>/system/weixinMenu_addOrUpdateMenu.action?time="+new Date().getTime(),{name:name.val()},
										function(data){ // 发送请求，添加菜单
											var vo = eval("(" + data + ")");
											var newPos = $(".left_menu").length + 1;
											var s = '<table class="left_menu" id="left_menu_' + newPos + '" width="100%" border="0" cellspacing="0">' +
												'<tr id="menu_list_'+newPos+'" onclick="add_back_col('+newPos+',' + vo.id + ')"><td><img src="<%=request.getContextPath() %>/public/images/menu_2.png" border="0" align="absmiddle" /></td>' + 
												'<td colspan="2" class="maintd"><span id="span_menu_name_' + newPos + '">' + vo.name + '</span></td><td>'+
												'<a href="javascript:addChildMenu(' + newPos + ',' + vo.id + ')"><img src="<%=request.getContextPath() %>/public/images/tb2.png"/></a>'+
								  	            '<a href="javascript:editMenu(' + newPos + ',0,\'' + vo.name + '\',' + vo.id + ')"><img class="edit_menu" src="<%=request.getContextPath() %>/public/images/pencil.png"/></a>'+
								  	            '<a href="javascript:deleteMenu(' + vo.id + ')"><img  class="delete_menu" src="<%=request.getContextPath() %>/public/images/delete_1.png"/></a></td></tr></table>';
											$("#" + layer).append(s);
										}
									);
								} else if (f == 1 && model == "add") {
									$.get("<%=request.getContextPath()%>/system/weixinMenu_addOrUpdateMenu.action?time="+new Date().getTime(),{lastMenuId:lastMenuId, name:name.val()},
										function(data){ // 发送请求，添加菜单
											var vo = eval("(" + data + ")");
											var newPos = $("#" + layer).find("tr").length - 1; // 一级菜单tr，二级菜单下标从0开始
											var s = '<tr id="menu_list_' + pos + '_' + newPos + '" onclick="add_back_col(\'' + pos + '_' + newPos +'\',' + vo.id + ')"><td></td><td><img src="<%=request.getContextPath() %>/public/images/menu_3.png" border="0" align="absmiddle" /></td>';
											s += '<td><span id="span_child_menu_name_' + pos + '_' + newPos + '">' + vo.name + '</span></td>';
											s += '<td><a href="javascript:editMenu(\'' + pos + '_' + newPos + '\',1,\'' + vo.name + '\',' + vo.id + ',\'' + vo.subId + '\')"><img class="edit_menu_1" src="<%=request.getContextPath() %>/public/images/pencil.png"/></a>';
											s += '<a href="javascript:deleteMenu(' + vo.id + ')"><img class="delete_menu_1"  src="<%=request.getContextPath() %>/public/images/delete_1.png"/></a>';
											s += '</td></tr>';
											$("#" + layer).append(s);
										}
									);
								} else if (f == 0 && model == "edit") {
									$.get("<%=request.getContextPath()%>/system/weixinMenu_addOrUpdateMenu.action?time="+new Date().getTime(),{menuId:menuId, name:name.val()},
										function(data){ // 发送请求，添加菜单
											var vo = eval("(" + data + ")");
											$("#span_menu_name_" + pos).html(vo.name);
										}
									);
								} else if (f == 1 && model == "edit") {
									$.get("<%=request.getContextPath()%>/system/weixinMenu_addOrUpdateMenu.action?time="+new Date().getTime(),{menuId:menuId, name:name.val()},
										function(data){ // 发送请求，添加菜单
											var vo = eval("(" + data + ")");
											$("#span_child_menu_name_" + pos).html(vo.name);
										}
									);
								}
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

			function openDialogImage() {
				$.get("<%=request.getContextPath()%>/system/weixinKey_getImage.action?time="+new Date().getTime(),{},
					function(data){
			    		var result = eval("(" + data + ")");
			    		var htmlVar = "";
			    		var path = result[0].path;
			    		var list = result[0].list;
			    		for (var i = 0; i < list.length; i++) {
			    			htmlVar += '<div><input type="radio" key1="' + (path + list[i].picName) + '" name="group-menu-image" value="' + list[i].id + '" />选择</div><br/>' + '<a href = "' + (path + list[i].picName) + '" target="_blank"><img class="ret_pic_pc" src="' + path + list[i].picName + '" alt="" /></a>';
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
							var obj = $("input[name='group-menu-image']:checked");
							if (obj.length < 1) {
								$( this ).dialog( "close" );
								return;
							}
							var value = obj.val();
							var key1 = obj.attr("key1");
							var result = '<img class="ret_pic_pc" src="' + key1 + '" alt="" /><input type="text" style="display:none" value="' + value + '" id="menu_image_confirm_resource_id"/>';
							$("#tabs-2").html(result);
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

			function openDialogNews() {
				$("#dialog-news-add-single").css("display", "none");
				$("#dialog-news-add-multi").css("display", "none");
				
				$.get("<%=request.getContextPath()%>/system/weixinKey_getNews.action?time="+new Date().getTime(),{},
					function(data){
			    		var result = eval("(" + data + ")");
			    		var htmlVar = "";
			    		var path = result[0].path;
			    		var list = result[0].list;
			    		for (var i = 0; i < list.length; i++) {
			    			htmlVar += '<div style="border:1px solid #c4c4c4;"><div><input type="radio" key1="' + (path + list[i].picName) + 
	    			 		   '" key2="' + list[i].title + '" key3= "' + list[i].content + '" name="group-menu-news" value="' + 
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
							    			htmlVar += '<div style="border-style:double;"><div><input type="radio" key1="' + (path + list[i].picName) + 
					    			 		   '" key2="' + list[i].title + '" key3= "' + list[i].content + '" name="group-menu-news" value="' + list[i].id + '" />选择</div>' + 
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
										$("#dialog-news-add-multi").hide();
									}
								);
							} else if (saveNewsFlag == "0") { // 选中单条图文
								var obj = $("input[name='group-menu-news']:checked");
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
											'<input type="text" style="display:none" value="' + value + '" id="menu_news_confirm_resource_id"/>';

								$("#tabs-3").html(result);
								$( this ).dialog( "close" );
							} 
						},
						"取消": function() {
							$("#add-news-flag").val(0);
							$("#dialog-news-select-single").show();
							$("#dialog-news-add-single").hide();
							$("#dialog-news-add-multi").hide();
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
				    		result = '<div><input type="radio" key1="' + result.link + '" name="group-menu-image" value="' + result.id + 
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

			function keys(i, key) { // 消除jquery ui tabs影响
				var active = $("#gird").tabs( "option", "active" );
				if (1 == active && i == 1) {
					openDialogImage();
				}
				if (2 == active && i == 2) {
					openDialogNews();
				}
			}

			function saveMenuReplay() {
				var panelId = $("#gird").tabs( "option", "active" );
				var menuId = $("#selected_menu").val();
				if (menuId == null || menuId.length < 1) {
					$('.picurl').tip({width:'240',status:'error',content:'请选择菜单',dis_time:1000});
					return;
				}
				if (panelId == "0") {
					var textContent = editor.html();
					if (textContent == null || textContent.length < 1) {
						$('.picurl').tip({width:'240',status:'error',content:'至少输入一个字符',dis_time:1000});
						return;
					}
					$.get("<%=request.getContextPath()%>/system/weixinMenu_addText.action?time="+new Date().getTime(),{menuId:menuId, content:textContent},
						function(data){
							$('.picurl').tip({width:'240',status:'right',content:'更新成功',dis_time:1000});
						}
					);
				} else if (panelId == "1") {
					var resourceId = $("#menu_image_confirm_resource_id").val();
					if (resourceId == 'undefined') {
						$('.picurl').tip({width:'240',status:'error',content:'缺少回复资源',dis_time:1000});
						return;
					}
					$.get("<%=request.getContextPath()%>/system/weixinMenu_addImage.action?time="+new Date().getTime(),{menuId:menuId,resourceId:resourceId},
						function(data){
							$('.picurl').tip({width:'240',status:'right',content:'更新成功',dis_time:1000});
						}
					);
				} else if (panelId == "2") {
					var resourceId = $("#menu_news_confirm_resource_id").val();
					if (resourceId == 'undefined') {
						$('.picurl').tip({width:'240',status:'error',content:'缺少回复资源',dis_time:1000});
						return;
					}
					$.get("<%=request.getContextPath()%>/system/weixinMenu_addNews.action?time="+new Date().getTime(),{menuId:menuId,resourceId:resourceId},
						function(data){
							$('.picurl').tip({width:'240',status:'right',content:'更新成功',dis_time:1000});
						}
					);
				} else if (panelId == "3") {
					var url = $("#menu_link").val();
					if (url == null || url.length < 1) {
						$('.picurl').tip({width:'240',status:'error',content:'至少输入一个字符',dis_time:1000});
						return;
					}
					$.get("<%=request.getContextPath()%>/system/weixinMenu_addLink.action?time="+new Date().getTime(),{menuId:menuId, url:url},
						function(data){
							$('.picurl').tip({width:'240',status:'right',content:'更新成功',dis_time:1000});
						}
					);
				}
			}
			function add_back_col(target, menuId){
		    	$("table tr").removeClass("tr_back_list");
		    	$("#menu_list_"+target).addClass("tr_back_list");
		    	$("#selected_menu").val(menuId);
		    	// 根据类型显示详细
		    	$.get("<%=request.getContextPath()%>/system/weixinMenu_getMenuDetail.action?time="+new Date().getTime(),{menuId:menuId},
					function(data){
		    			var vo = eval("(" + data + ")");
		    			$("#menu_link").val(vo.url);
		    			var type = vo.type;
			    		if (type == 'text') {
			    			editor.html(vo.content);
							$("#gird").tabs({active: "0"});
						} else if (type == 'image') {
							$("#page-flag-open-div").val("1");
							editor.html("");
							var result = '<img class="ret_pic_pc" src="' + vo.picLink + '" alt="" /><input type="text" style="display:none" value="' + 
										  vo.resourceId + '" id="menu_image_confirm_resource_id"/>';
							$("#tabs-2").html(result);
							$("#gird").tabs({active: 1});
							$("#page-flag-open-div").val("0");
						} else if (type == 'news') {
							$("#page-flag-open-div").val("1");
							editor.html("");
							var result = '<span>' + vo.resouceTitle + '</span><br/>' + 
							   			 '<a href = "' + vo.picLink + '" target="_blank"><img class="ret_pic_pc" src="' + vo.picLink + 
							   			 '" alt="" /></a><br/>' + 
							   			 '<span>' + vo.content + '</span>' + 
										 '<input type="text" style="display:none" value="' + vo.resourceId + '" id="menu_news_confirm_resource_id"/>';
							$("#tabs-3").html(result);
							$("#gird").tabs({active: 2});
							$("#page-flag-open-div").val("0");
						} 
					}
				);
		   	}
			function deleteMenu(menuId){
				$.get("<%=request.getContextPath()%>/system/weixinMenu_deleteMenu.action?time="+new Date().getTime(),{menuId:menuId},
					function(data){
						loadMenu();
						$('.picurl').tip({width:'240',status:'right',content:'删除成功',dis_time:1000});
					}
				);
		   	}
			function sysMenu(op){
				var plat = $("#plat").val();
				$.get("<%=request.getContextPath()%>/system/weixinMenu_menuSys.action?time="+new Date().getTime(),{operation:op, plat:plat},
					function(data){
						alert(data);
						//$('.picurl').tip({width:'240',status:'right',content:data,dis_time:1000});
					}
				);
		   	}
		   	
			var editor;
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
				editor = K.create('#menu_content', {
					themeType : 'qq',
					items : [
						'bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','plug-order','plug-indent','link'
					]
				});
			});
		</script>
	</head>
	<body>
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">微信菜单自定义</font></span>
    	</div>
    	<input type="text" style="display:none" value="0" id="page-flag-open-div"/></span>
    	<div class="main">
			<div id="selector">
				<div class="inner">
					<div class="headerTitle">
					   <span class="whiteTil">菜单管理</span>
					   <input type="button" value="添加菜单" id="add_menu_inp" class="add_menu_inp"/>
					   <input type="button" value="筛选" class="select_menu_inp"/>
					   <input type="text" value="" style="display: none" name="selected_menu" id="selected_menu"/>
				   	</div>
					<div id="menu_config"></div>
				</div>
			</div>
			<div id="xbar"></div>
			<div id="gird">
				<div class="add_pic_check">
					<ul>
						<li><a href="#tabs-1">文字</a></li>
						<li><a href="#tabs-2" onclick="keys(1);">图片</a></li>
						<li><a href="#tabs-3" onclick="keys(2);">图文消息</a></li>
						<li><a href="#tabs-4">链接</a></li>
						<li><a href="#tabs-5">语音</a></li>
						<li><a href="#tabs-6">视频</a></li>
					</ul>
					<div id="tabs-1">
						<s:textarea id="menu_content" name="menu_content" style="width:400px;height:290px;visibility:hidden;"></s:textarea>
					</div>
					<div id="tabs-2"></div>
					<div id="tabs-3"></div>
					<div id="tabs-4">
						<s:textarea id="menu_link" name="menu_link" cols="70" rows="5"></s:textarea>
					</div>
					<div id="tabs-5"></div>
					<div id="tabs-6"></div>
				</div>
				 <div class="submit_save">
				 	<input type="button" value="&nbsp;&nbsp;保存&nbsp;&nbsp;" id="key_save_submit" onclick="saveMenuReplay();" class="add_menu_inp key_save_submit"/>
				 </div>
			</div>
			<div id="footer">
				<div id="innerFooter">
					<p id="copyright">
						<input type="button" value="同步菜单" onclick="sysMenu('create')" class="business_btn"/>
					   	<input type="button" value="获取菜单" onclick="sysMenu('get')" class="business_btn"/>
					   	<input type="button" value="删除菜单" onclick="sysMenu('delete')" class="business_btn"/>
					   	<select name="plat" id="plat">
						  	<option value ="weixin">微信</option>
						  	<option value ="yixin">易信</option>
						</select>
					</p>
				</div>
			</div>
		</div>
		<div id="dialog-form" title="添加/编辑菜单" style="display: none">
			<form>
				<fieldset>
					<label for="name">菜单名称</label>
					<input type="text" name="menu_name" id="menu_name" class="text ui-widget-content ui-corner-all" />
				</fieldset>
			</form>
		</div>
		<div id="dialog-image" title="选择图片" style="display: none">
			<form>
				<fieldset>
					<input type="file" onchange="ajaxFileUpload1();" name="fileInput" id="uploadify" class="text ui-widget-content ui-corner-all"/>
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
			<div id="dialog-news-add-multi" style="display:none;">
			<form>
				<fieldset>
				<div class="left_pic_mult">
					<div class="title_pic">2013-08-12</div>
					<img id="img_pic" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
					<div class="describe">
					********************
					</div>
					<div id="pic_content_list">
				    	<input type="hidden" id="counterList" value="1" />
						<div class="pic_content_1" id="pic_content_1"  onclick="editContPic('1')">
							<img id="img_pic_1" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
							<div class="pic_content_1_1">
								<div class="" id="title">标题</div>
								<input type="text" name="name" id="title_input_1" class="text ui-widget-content ui-corner-all input_pic" />
								<div class="">描述</div>
								<div class="describe_1" >
								<span id="describe_rule_1" ></span>
								<input type="hidden" id="describe_multi_k_1" value="1" />
								</div>
							</div>
					    </div>
					</div>
					<div class="pic_content_add" id="pic_content_add">
					  	  <img id="img_pic_add" src="<%=request.getContextPath() %>/public/images/plus.png"/>
					</div>
				</div>
				<div class="right_pic_mult" id="right_pic_multi">
					<div class="title_pic_1">标题</div>
				    <input type="text" name="name" id="title_input_multi" class="text ui-widget-content ui-corner-all input_pic" />
				    <div class="title_pic_1">封面</div>
			     	<input type="file" name="file" id="file" class="text ui-widget-content ui-corner-all input_pic" value="上传"/>
					<div class="title_pic_2">添加摘要</div>
					<div class="title_pic_1">正文</div>
					<div style="margin-left: 1%;width：390px;">
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