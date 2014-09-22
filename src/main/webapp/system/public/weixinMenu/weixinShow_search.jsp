<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath() %>/public/css/163css.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<title>效果预览</title>
		<script type="text/javascript">
			$(function(){
				load();
			});
	
			
			function load() {
				// 加载菜单
				$.get("<%=request.getContextPath()%>/system/weixinMenu_createMenu.action?time="+new Date().getTime(),{operation:'show'},
					function(data){
						var result = eval("(" + data + ")");
						var str = "<div class=ext id='input_or_service' onclick='inpOrSer()'> </div><div class=menu_list id=menu_list><ul class=lang>";
						for (var i = 0; i < result.length; i++) {
							var level1 = result[i].level1;
							var level2 = result[i].level2;
							if (level2 != "" && level2.length > 0) {
								str += "<li id=menu_list><SPAN >" + level1.name + "</SPAN>";
								str += "<ul>";
								for (var j = 0; j < level2.length; j++) {
									str += "<li><A href='javascript:returnMenu(" + level2[j].id + ");'>" + level2[j].name + "</A></li>";
								}
								str += "</ul>";
							} else {
								str += '<li id="menu_list" ><SPAN onclick="returnMenu(' + level1.id + ')">' + level1.name + "</SPAN>";
							}
							str += "</li>";
						}
						str += "</ul></div>";
						str += "<div class=retrun_inp id=retrun_inp style=display:none;><input type='text' id='inp_return' class='input_show' value=''/><input type='button' id='inp_return' class='button_show' value='' onclick='returnNew()'/></div>";
						$("#menu").html(str);
					}
				);

				$.get("<%=request.getContextPath()%>/system/weixinKey_keyResponse.action?time="+new Date().getTime(),{event:'subscribe'},
					function(data){
						var vo = eval("(" + data + ")");
						var type = vo.type;
						var listRetNew;
						if (type == 'text') {
							listRetNew = '<div class="new_from_pc"><div class="main_pic"></div>'+
		    							 '<div class="main_cont"><div class="label_new" id="newPc">'+vo.content+'</div></div></div>';
		    				$("#info").append(listRetNew);
						} else if (type == 'image') {
							listRetNew = '<div class="new_from_pc"><div class="main_pic"></div>'+
		    							 '<img alt="图片" src="'+vo.picLink+'" class="ret_pic_only"/></div>';
		    				$("#info").append(listRetNew);
						} else if (type == 'news') {
							listRetNew = '<div class="main_cont_pic_text"><div class="pc_title"><span class="pc_title_show" id="pc_title_show">'+vo.title+'</span></div>'+
	    								 '<img alt="图片" src="'+vo.picLink+'" class="ret_pic_pc"/><div class="pc_content">'+
	    								 '<div class="pc_content_show" id="pc_content_show">'+vo.contents+'</div></div>'+
	    								 '<div class="pc_url"><a target="_blank" href="'+vo.url+'">查看详细信息</a></div></div>';
		    				$("#info").append(listRetNew);
						} 
					}
				);
			}
			
			function submitQuery() {
				var customer = $("#customer").val();
				if (customer == "") {
					alert("请选择商户");
					return;
				}
				
			}
			
			//切换
			var index = 1;
			function inpOrSer(){
				if(index == 1){
					$("#menu_list").hide();
					$("#retrun_inp").show();
					index = index + 1;
				}else{
					$("#menu_list").show();
					$("#retrun_inp").hide();
					index = index - 1;
				}
			}
			
			function returnNew(){
				var content = $("#inp_return").val();
				if (content == null || content.length < 1) {
					return;
				}
				var listRetNew1 = '<div class="new_from_ps"><div class="person_pic"></div><div class="person_cont">'+
		    						     '<div class="label_new" id="newPer">'+content+'</div></div></div>';
				$("#info").append(listRetNew1);
				$.get("<%=request.getContextPath()%>/system/weixinKey_keyResponse.action?time="+new Date().getTime(),
					{event:'key',content:content},
					function(data){
					
						var vo = eval("(" + data + ")");
						var type = vo.type;
						var listRetNew;
						if (type == 'text') {
							listRetNew = '<div class="new_from_pc"><div class="main_pic"></div>'+
		    							 '<div class="main_cont"><div class="label_new" id="newPc">'+vo.content+'</div></div></div>';
		    				$("#info").append(listRetNew);
						} else if (type == 'image') {
							listRetNew = '<div class="new_from_pc"><div class="main_pic"></div>'+
		    							 '<img alt="图片" src="'+vo.picLink+'" class="ret_pic_only"/></div>';
		    				$("#info").append(listRetNew);
						} else if (type == 'news') {
							listRetNew = '<div class="main_cont_pic_text"><div class="pc_title"><span class="pc_title_show" id="pc_title_show">'+vo.title+'</span></div>'+
	    								 '<img alt="图片" src="'+vo.picLink+'" class="ret_pic_pc"/><div class="pc_content">'+
	    								 '<div class="pc_content_show" id="pc_content_show">'+vo.contents+'</div></div>'+
	    								 '<div class="pc_url"><a target="_blank" href="'+vo.url+'">查看详细信息</a></div></div>';
		    				$("#info").append(listRetNew);
						} 
						$("#inp_return").val("");
					}
				);
			}

			function returnMenu(menuId){
				$.get("<%=request.getContextPath()%>/system/weixinMenu_menuResponse.action?time="+new Date().getTime(),
					{menuId:menuId},
					function(data){
						var vo = eval("(" + data + ")");
						var type = vo.type;
						var listRetNew;
						if (type == 'text') {
							listRetNew = '<div class="new_from_pc"><div class="main_pic"></div>'+
		    							 '<div class="main_cont"><div class="label_new" id="newPc">'+vo.content+'</div></div></div>';
		    				$("#info").append(listRetNew);
						} else if (type == 'image') {
							listRetNew = '<div class="new_from_pc"><div class="main_pic"></div>'+
		    							 '<img alt="图片" src="'+vo.picLink+'" class="ret_pic_only"/></div>';
		    				$("#info").append(listRetNew);
						} else if (type == 'news') {
							listRetNew = '<div class="main_cont_pic_text"><div class="pc_title"><span class="pc_title_show" id="pc_title_show">'+vo.resouceTitle+'</span></div>'+
	    								 '<img alt="图片" src="'+vo.picLink+'" class="ret_pic_pc"/><div class="pc_content">'+
	    								 '<div class="pc_content_show" id="pc_content_show">'+vo.content+'</div></div>'+
	    								 '<div class="pc_url"><a target="_blank" href="'+vo.url+'">查看详细信息</a></div></div>';
		    				$("#info").append(listRetNew);
						} 
						$("#inp_return").val("");
					}
				);
			}
		</script>
	</head>
	<body>
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">微信维护-效果预览</font></span>
    	</div>
    	<div class="main" style="background-image: url('<%=request.getContextPath()%>/public/images/show.png');width:320px;height:480px;position: absolute;">
    		<div id="info" class="new_from"></div>
			<div class="menu" id="menu" ></div>
		</div>
	</body>
</html>