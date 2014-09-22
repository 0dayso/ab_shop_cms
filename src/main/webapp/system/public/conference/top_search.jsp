<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/searchman.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath() %>/public/css/163css.css" rel="stylesheet" type="text/css">
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
		<title>栏目设置</title>
		<style type="text/css">
			.luyu_tr_click {
				background-color: #F4F4F4;
			}
			.luyu_tr_click1 {
				background-color: yellow;
			}
			#selector {
				top: 100px;
				width:920px;
				height:100%;
				bottom: 68px;
				z-index: 16;
			}
			#gird {
				top: 0px;
				left:400px;
				bottom: 60px;
				z-index: 9;
			}
		</style>
		<script type="text/javascript">
			function luyuTrClick(state, id, o, t, name) {
				if (t == 0) {
					$(".luyu_tr_click").each(function(i){
						$(this).removeClass("luyu_tr_click1");
					});
					$(o).addClass("luyu_tr_click1");
					$("#h_top_id").val(id);
					$("#h_top_state").val(state);
					$("#h_top_name").val(name);
					reFreshDetail(id);
				} 
			}

			$(function () {
				load();
				initMethod();
			});


			function load(){
				$.get("<%=request.getContextPath()%>/system/conference_getAllConference.action?time="+new Date().getTime(),{},
					function(data){
						$("#customerList").html(data);
						reFreshList(); // 刷新列表
				});
			}

			function reFreshList() {
				init(); // 初始化
				var obj = document.getElementById('state');   
				$("#selector").css("display", "");  
				var index=obj.selectedIndex;
				var infoType=obj.options[index].value;
				$.ajax({
					url : "<%=request.getContextPath()%>/system/top_getAllTopByConference.action",
					type : 'POST',
					data:{"conferenceId":$("#conference").val(), "state" : $("#state").val()},
					dataType:"json",
					success : function(result) {
						var s= '<table class="tableborder" border="1" bordercolor="#dbdbdb">';
						s += '<tr class="th1"><td>栏目名称</td><td>栏目点赞量</td><td>栏目评论量</td></tr>';
						for(var i = 0; i < result.length; i++ ){
							s+='<tr class="luyu_tr_click" onclick="luyuTrClick(' + result[i].state + ',' + result[i].id + ',this, 0, \'' + result[i].name + '\');"><td>' + result[i].name + '</td><td>' + result[i].count + '</td><td>' + result[i].count + '</tr>';
						}
						s += '</table>';
						$("#list").html(s);
					}
				});
			}

			function addOrEditSub() {
				var id = $("#h_top_id").val();
				if (id == "" || id.length < 1) {
					$.get("<%=request.getContextPath()%>/system/top_getAllTopSelectByConference.action?time="+new Date().getTime(),
						{"conferenceId":$("#conference").val(), "state" : $("#state").val()},
						function(data){
							$("#topList").html(data);
							openDialogMenuForSub('add');
					});
				} else {
					$.get("<%=request.getContextPath()%>/system/top_getAllTopSelectByConference.action?time="+new Date().getTime(),
						{"id" : id, "conferenceId":$("#conference").val(), "state" : $("#state").val()},
						function(data){
							$("#topList").html(data);
							openDialogMenuForSub('add');
					});
				}
			}

			function editSub(id, title, logo, content) {
				//var id = $("#h_top_id").val();
				$("#sub_title").val(title);
				$("#sub_content").val(content);
				$("#news_file_h").html(logo);

				var res = '<div id="news_pic"><img id="img_pic" src="' + logo + '" alt="" />';
				$("#news_pic").html(res);
				openDialogMenuForSub('edit', id);
				$.get("<%=request.getContextPath()%>/system/top_getAllTopSelectByConference.action?time="+new Date().getTime(),
					{"id" : id, "conferenceId":$("#conference").val(), "state" : $("#state").val()},
					function(data){
						$("#topList").html(data);
				});
			}

			function detailSub(id, content, banner) {  
				$("#huodong_content").val(content);
				$("#huodong_file_h").html(banner);
				var res = '<div id="huodong_pic"><img id="huodong_pic1" src="' + banner + '" alt="" />';
				$("#huodong_pic").html(res);
				openDialogMenuForHuodong(id);
			}

			function initMethod() {
				$("#state").bind("change", reFreshList);
				$( "#add_new_top" ).click(function() {
					openDialogMenu('add');
				});
				$( "#edit_new_top" ).click(function() {
					var id = $("#h_top_id").val();
					if (id == "" || id.length < 1) {
						$('.picurl').tip({width:'240',status:'right',content:'请选择栏目进行编辑',dis_time:1000});
					} else {
						openDialogMenu('edit');
					}
				});
				$( "#del_new_top" ).click(function() {
					var id = $("#h_top_id").val();
					var name = $("#h_top_name").val();
					if (id == "" || id.length < 1) {
						$('.picurl').tip({width:'240',status:'right',content:'请选择栏目进行删除',dis_time:1000});
					} else {
						if(window.confirm('你确定要删除栏目：' + name)){
							$.ajax({
								url : "<%=request.getContextPath()%>/system/top_delTop.action?time="+new Date().getTime(),
								type : 'POST',
								data:{"id":id},
								dataType:"json",
								success : function(result) {
									var s= '<table class="tableborder" border="1" bordercolor="#dbdbdb">';
									s += '<tr class="th1"><td>栏目名称</td><td>栏目点赞量</td><td>栏目评论量</td></tr>';
									for(var i = 0; i < result.length; i++ ){
										s+='<tr class="luyu_tr_click" onclick="luyuTrClick(' + result[i].state + ',' + result[i].id + ',' + 'this, 0, \'' + result[i].name + '\');"><td>' + result[i].name + '</td><td>' + result[i].count + '</td><td>' + result[i].count +'</td>' + '</tr>';
									}
									s += '</table>';
									$("#list").html(s);
									$("#h_top_id").val("");
								}
							});
			            } 
					}
				});
			}

			function deleteSub(id) {
				if(window.confirm('你确定要删除该活动?')){
					$.ajax({
						url : "<%=request.getContextPath()%>/system/top_delSub.action?time="+new Date().getTime(),
						type : 'POST',
						data:{"subId":id},
						dataType:"json",
						success : function(result) {
							buildSubClause(result);
						}
					});
				}
			}

			function openDialogMenu(model) {
				var name = $("#top_name");
				var allFields = $([]).add(name);
				var tips = $(".validateTips");
				if (model == "edit") {
					var tValue = $("#h_top_name").val();
					var tState = $("#h_top_state").val();
					name.val(tValue);
					$("#top_state").val(tState);
				}
				
				$("#top-form").dialog({
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
								if (model == "add") {
									var state = $("#top_state").val();
									$.ajax({
										url : "<%=request.getContextPath()%>/system/top_addTop.action?time="+new Date().getTime(),
										type : 'POST',
										data:{"name":name.val(),"topState":state,"conferenceId":$("#conference").val(), "state" : $("#state").val()},
										dataType:"json",
										success : function(result) {
											var s= '<table class="tableborder" border="1" bordercolor="#dbdbdb">';
											s += '<tr class="th1"><td>栏目名称</td><td>栏目点赞量</td><td>栏目评论量</td></tr>';
											for(var i = 0; i < result.length; i++ ){
												s+='<tr class="luyu_tr_click" onclick="luyuTrClick(' + result[i].state + ',' + result[i].id + ',' + 'this, 0, \'' + result[i].name + '\');"><td>' + result[i].name + '</td><td>' + result[i].count + '</td><td>' + result[i].count +'</td>' + '</tr>';
											}
											s += '</table>';
											$("#list").html(s);
											$("#h_top_id").val("");
										}
									});
								} else if (model == "edit") {
									var state = $("#top_state").val();
									var id =  $("#h_top_id").val();
									$.ajax({
										url : "<%=request.getContextPath()%>/system/top_editTop.action?time="+new Date().getTime(),
										type : 'POST',
										data:{"id":id, "name":name.val(),"topState":state,"conferenceId":$("#conference").val(), "state" : $("#state").val()},
										dataType:"json",
										success : function(result) {
											var s= '<table class="tableborder" border="1" bordercolor="#dbdbdb">';
											s += '<tr class="th1"><td>栏目名称</td><td>栏目点赞量</td><td>栏目评论量</td></tr>';
											for(var i = 0; i < result.length; i++ ){
												s+='<tr class="luyu_tr_click" onclick="luyuTrClick(' + result[i].state + ',' + result[i].id + ',' + 'this, 0, \'' + result[i].name + '\');"><td>' + result[i].name + '</td><td>' + result[i].count + '</td><td>' + result[i].count +'</td>' + '</tr>';
											}
											s += '</table>';
											$("#list").html(s);
											$("#h_top_id").val("");
										}
									});
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

			function openDialogMenuForHuodong(subId) {
				$("#huodong-form").dialog({
					autoOpen: true,
					height: 500,
					width: 720,
					modal: true,
					buttons: {
						"确定": function() {
							var content = $("#huodong_content").val();
							var logo = $("#huodong_file_h").html();
							var topId = $("#h_top_id").val();
							
							$.ajax({
								url : "<%=request.getContextPath()%>/system/top_editSubDetail.action?time="+new Date().getTime(),
								type : 'POST',
								data:{"id":topId, "subId":subId, "describe":content, "banner":logo},
								dataType:"json",
								success : function(result) {
									buildSubClause(result);
								}
							});
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
			}
			
			function openDialogMenuForSub(model, subId) {
				var name = $("#sub_title");
				var allFields = $([]).add(name);
				var tips = $(".validateTips");
				
				$("#sub-form").dialog({
					autoOpen: true,
					height: 500,
					width: 720,
					modal: true,
					buttons: {
						"确定": function() {
							var bValid = true;
							if (name.val().length < 1) {
								name.addClass( "ui-state-error" );
								return false;
							} 
							if (bValid) {
								if (model == "add") {
									var title = name.val();
									var content = $("#sub_content").val();
									var logo = $("#news_file_h").html();
									var topId = $("#topId").val(); // 下拉框所选择

									$.ajax({
										url : "<%=request.getContextPath()%>/system/top_addSubClause.action?time="+new Date().getTime(),
										type : 'POST',
										data:{"id":topId, "title":title, "content":content, "logo":logo},
										dataType:"json",
										success : function(result) {
											buildSubClause(result);
										}
									});
								} else if (model == "edit") {
									var title = name.val();
									var content = $("#sub_content").val();
									var logo = $("#news_file_h").html();
									var topId = $("#topId").val();

									$.ajax({
										url : "<%=request.getContextPath()%>/system/top_editSubClause.action?time="+new Date().getTime(),
										type : 'POST',
										data:{"id":topId, "subId":subId, "title":title, "content":content, "logo":logo},
										dataType:"json",
										success : function(result) {
											buildSubClause(result);
										}
									});
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

			function reFreshDetail(id) {
				$.ajax({
					url : "<%=request.getContextPath()%>/system/top_getAllSubByTop.action?time="+new Date().getTime(),
					type : 'POST',
					data:{"id":id},
					dataType:"json",
					success : function(result) {
						buildSubClause(result);
					}
				});
			}

			function buildSubClause(result) {
				var vo = result;
				var listRetNew = '<div class="new_from_pc"><div class="main_pic"></div>'+
  				  				 '<div class="main_cont"><div class="label_new" id="newPc">' +
  				  				 '<input type="button" value="添加新动态" id="add_new_sub" onclick="addOrEditSub()" class="business_btn"/>' +
  				  				 '</div></div></div>';
				for (var i = 0; i < vo.length; i++) {
					var obj = vo[i];
					listRetNew += '<div id="new_from_pc_' + obj.id + '" class="new_from_pc"><div class="main_pic" style=\"background: url(\"' + obj.logo + '\") no-repeat;background-size:46px 40px;\"></div>';
					listRetNew += '<div class="main_cont"><div class="label_new" id="newPc_' + obj.id + '" >' + obj.title + '<br/>';
					listRetNew += obj.content + '<br/>' + '<input align="right" type="button" value="编辑" onclick="editSub(' + obj.id + ',\'' + obj.title  + '\',\'' + obj.logo + '\',\'' + obj.content + '\')" id="edit_new_sub"  class="business_btn" /><input align="right" type="button" value="删除" onclick="deleteSub(' + obj.id + ')" id="del_new_sub" class="business_btn"/><input align="right" type="button" value="详细" onclick="detailSub(' + obj.id + ',\'' + obj.describe  + '\',\'' + obj.banner + '\')" id="del_new_sub" class="business_btn"/>' + '</div></div></div>';
				}
				$("#info").html(listRetNew);
			}

			function init() {
				var listRetNew1 = '<div class="new_from_pc"><div class="main_pic"></div>'+
				  				  '<div class="main_cont"><div class="label_new" id="newPc"><input type="button" value="添加新动态" id="add_new_sub" onclick="addOrEditSub()" class="business_btn"/></div></div></div>';
				$("#info").html(listRetNew1);			
			}

			function ajaxFileUpload2(){
				var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
				if (! reg.test($("#news_file").val())) {
					$('.picurl').tip({width:'240',status:'right',content:'图片格式必须为jpg、jpeg、png、bmp!',dis_time:1000});
					return false;
				}
			    $.ajaxFileUpload({
			    	url:'<%=request.getContextPath()%>/system/top_upload.action',  
			      	secureuri:false,
			      	fileElementId:'news_file',                         //文件选择框的id属性
			      	dataType: 'json',                                     //服务器返回的格式，可以是json			
					success: function (data, status){
						if(data.status == "success"){
							var result = data;
							var res = '<div id="news_pic"><img id="img_pic" src="' + result.link + '" alt="" />';
							$("#news_file_h").html(result.link);
							$("#news_pic").html(res);
						} else{
							$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
						}
					},
					error: function (data, status, e){
						$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
					}
			    });
			}

			function ajaxFileUpload3(){
				var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
				if (! reg.test($("#huodong_file").val())) {
					$('.picurl').tip({width:'240',status:'right',content:'图片格式必须为jpg、jpeg、png、bmp!',dis_time:1000});
					return false;
				}
			    $.ajaxFileUpload({
			    	url:'<%=request.getContextPath()%>/system/top_upload.action',  
			      	secureuri:false,
			      	fileElementId:'huodong_file',                         //文件选择框的id属性
			      	dataType: 'json',                                     //服务器返回的格式，可以是json			
					success: function (data, status){
						if(data.status == "success"){
							var result = data;
							var res = '<div id="huodong_pic"><img id="huodong_pic1" src="' + result.link + '" alt="" />';
							$("#huodong_file_h").html(result.link);
							$("#huodong_pic").html(res);
						} else{
							$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
						}
					},
					error: function (data, status, e){
						$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
					}
			    });
			}
			
		</script>
	</head>
	<body>
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">栏目设置</font></span>
    	</div>
    	<div class="main">
    		<div>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
					   <td align="right" class="th1">
							默认会议：
						</td>
						<td width="30%" align="left" colspan="2">
							<span id="customerList"></span>
						</td>
						 <td align="right" class="th1">
							是否启用：
						</td>
						<td align="left">
							<s:select id="state" name="state" list="#{'': '全部', '1': '启用', '0': '关闭'}" cssStyle="width:100px;"></s:select>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="selector" style="display: none;">
				<div class="inner">
					<div style="margin-left: 40px;">
						<input align="right" type="button" value="添加栏目" id="add_new_top" class="business_btn"/>
						<input align="right" type="button" value="编辑栏目" id="edit_new_top" class="business_btn"/>
						<input align="right" type="button" value="删除栏目" id="del_new_top" class="business_btn"/>
					</div>
					<div id="list" style="width:320px;"></div>
					<div id="gird" style="left:400px;width:320px;height:480px;background-image: url('<%=request.getContextPath()%>/public/images/show.png');">
			    		<div id="info" class="new_from"></div>
						<div class="menu" id="menu" ></div>
					</div>
				</div>
			</div>
		</div>
		
		<div id="top-form" title="添加/编辑栏目" style="display: none">
			<form>
				<fieldset>
					<label for="name">栏目名称</label>
					<input type="text" name="top_name" id="top_name" class="text ui-widget-content ui-corner-all" />
				</fieldset>
				<fieldset>
					<label for="name">是否启用</label>
					<s:select id="top_state" name="top_state" list="#{'1': '启用', '0': '关闭'}" class="text ui-widget-content ui-corner-all"></s:select>
				</fieldset>
			</form>
		</div>
		
		<div id="sub-form" title="添加/编辑动态" style="display: none">
			<form>
				<fieldset>
					<div class="left_pic">
						<div class="title_pic" id="left_news_title">动态标题</div>
						<div id="news_pic"><img id="img_pic" src="<%=request.getContextPath()%>/public/images/tubiao-01.png" /></div>
						<div class="news_describe" id="news_describe"></div>
					</div>
					<div class="right_pic">
						<div class="title_pic_1">动态标题</div>
						<input type="text" name="sub_title" id="sub_title" class="text ui-widget-content ui-corner-all input_pic" />
						<div class="title_pic_1">动态图片</div>
						<input type="file" name="fileInput" onchange="ajaxFileUpload2();" id="news_file" class="text ui-widget-content ui-corner-all input_pic" value="上传" />
						<div class="title_pic_2">动态描述</div>
						<div style="margin-left: 1%;">
							<textarea id="sub_content" onkname="sub_content" cols="50" rows="12"></textarea>
						</div>
						<div class="title_pic_1">栏目选择</div>
						<span id="topList"></span>
					</div>
				</fieldset>
			</form>
		</div>
		
		<div id="huodong-form" title="链接详细" style="display: none">
			<form>
				<fieldset>
					<div class="left_pic">
						<div id="huodong_pic"><img id="huodong_pic1" src="<%=request.getContextPath()%>/public/images/tubiao-01.png" /></div>
						<div class="news_describe" id="huodong_describe"></div>
					</div>
					<div class="right_pic">
						<div class="title_pic_1">导航图片（默认使用会议图片）</div>
						<input type="file" name="fileInput" onchange="ajaxFileUpload3();" id="huodong_file" class="text ui-widget-content ui-corner-all input_pic" value="上传" />
						<div class="title_pic_2">链接详细<font color="#FF0000">（最多4000字）</font></div>
						<div style="margin-left: 1%;">
							<textarea id="huodong_content" onkname="huodong_content" cols="50" rows="22"></textarea>
						</div>
					</div>
				</fieldset>
			</form>
		</div>
		
		<div style="opacity:0;  filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px;">
        	<input id="urlt" class="picurl" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" type="file">
        </div>
		<input type="text" name="h_top_id" id="h_top_id" style="display: none" />
		<input type="text" name="h_top_state" id="h_top_state" style="display: none" />
		<input type="text" name="h_top_name" id="h_top_name" style="display: none" />
		<span id="news_file_h" style="display: none"></span>
		<span id="huodong_file_h" style="display: none"></span>
	</body>
</html>