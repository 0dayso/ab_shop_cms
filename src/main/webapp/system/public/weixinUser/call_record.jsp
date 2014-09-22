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
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.9.1.js"></script>
		<SCRIPT type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></SCRIPT>
		<title>会话记录</title>
		<style type="text/css">
			.luyu_tr_click {
				background-color: #F4F4F4;
			}
			.luyu_tr_click1 {
				background-color: #699D08;
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
			function luyuTrClick(o, t, name) {
				if (t == 0) {
					$(".luyu_tr_click").each(function(i){
						$(this).removeClass("luyu_tr_click1");
					});
					$(o).addClass("luyu_tr_click1");
					reFreshDetail(name);
				} 
			}

			$(function () {
				$("button").bind("click",function(){
				  	$("p").slideToggle();
				});
				reFreshList(); // 刷新列表
				initMethod();
			});

			function reFreshList() {
				init(); // 初始化
				var obj = document.getElementById('infoType');   
				$("#selector").css("display", "");  
				var index=obj.selectedIndex;
				var infoType=obj.options[index].value;
				$.ajax({
					url : "<%=request.getContextPath()%>/system/visits_getNumsByUser.action",
					type : 'POST',
					data:{"beginDate":$("#beginDate").val(),"endDate":$("#endDate").val(),"infoType":$("#infoType").val()},
					dataType:"json",
					success : function(result) {
						var s= '<table class="tableborder" border="1" bordercolor="#dbdbdb">';
						s += '<tr class="th"><td>用户资料</td> <td>历史消息总量</td></tr>';
						for(var i = 0; i < result.length; i++ ){
							s+='<tr class="luyu_tr_click" onclick="luyuTrClick(this, 0, \'' + result[i].username + '\');"><td>' + result[i].username + '</td><td>' + result[i].count+'</td>' + '</tr>';
						}
						s += '</table>';
						$("#list").html(s);
					}
				});
				
			}

			function initMethod() {
				$("#infoType").bind("change", reFreshList);
			}

			function reFreshDetail(name) {
				var beginDate = $("#beginDate").val();
				var endDate = $("#endDate").val();
				var infoType = $("#infoType").val();
				
			 	$.get("<%=request.getContextPath()%>/system/visits_getDetilsByUser.action?time="+new Date().getTime(),
					{beginDate:beginDate,endDate:endDate,infoType:infoType, userName:name},
					function(data){
						if (data != null && data.length > 0) {
							var vo = eval("(" + data + ")");
							var listRetNew = "";
							var list = vo.list;
							for (var i = 0; i < list.length; i++) {
								var obj = list[i];
								var type = obj.infoType;
								if (type == '0') {
									listRetNew += '<div id="new_from_pc_' + obj.id + '" class="new_from_pc"><div class="main_pic"></div>'+
				    							 '<div class="main_cont"><div class="label_new" id="newPc_' + obj.id + '" onclick="getDetail(' + obj.id + ',\'' + obj.visitTime + '\')">' + obj.visitTime + '<br/>点击查看...</div></div></div>';
								} else if (type == '1') {
									listRetNew += '<div class="new_from_ps"><div class="person_pic"></div><div class="person_cont">'+
	    						     				  '<div class="label_new" id="newPer">' + obj.visitTime + "<br/>" + obj.inputInfo + '</div></div></div>';
								} 
							}
							$("#info").html(listRetNew);
						} else {
							init();
						}
					}
				);
			}

			function getDetail(id, time) {
				$.ajax({
					url : "<%=request.getContextPath()%>/system/visits_getVisitResource.action",
					type : 'POST',
					data:{"id":id},
					dataType:"json",
					success : function(result) {
						var len = result.list.length;
						if (len == 1) {
							var vo = result.list[0];
							var type = vo.type;
							var listRetNew;
							if (type == 'text') {
								listRetNew = '<div class="main_pic"></div>'+
			    							 '<div class="main_cont"><div class="label_new" id="newPc">' + time + "<br/>" + vo.content+'</div></div>';
							} else if (type == 'image') {
								listRetNew = '<div class="main_pic"></div>' + time + "<br/>" +
			    							 '<img alt="图片" src="'+vo.picName+'" class="ret_pic_only"/>';
							} else if (type == 'news') {
								$("#new_from_pc_" + id).removeClass("new_from_pc");
								$("#new_from_pc_" + id).addClass("main_cont_pic_text");
								listRetNew = '<div class="pc_title"><span class="pc_title_show" id="pc_title_show">' + time + "<br/>" +vo.title+'</span></div>'+
		    								 '<img alt="图片" src="'+vo.picName+'" class="ret_pic_pc"/><div class="pc_content">'+
		    								 '<div class="pc_content_show" id="pc_content_show">'+vo.content+'</div></div>'+
		    								 '<div class="pc_url"><a target="_blank" href="'+vo.url+'">查看详细信息</a></div>';
							} 
							$("#new_from_pc_" + id).html(listRetNew);
						}
					}
				});
			}

			function init() {
				var listRetNew1 = '<div class="new_from_pc"><div class="main_pic"></div>'+
				  				  '<div class="main_cont"><div class="label_new" id="newPc">暂无回复信息</div></div></div>';
				$("#info").html(listRetNew1);			
			}
		</script>
	</head>
	<body>
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">会话记录</font></span>
    	</div>
    	<div class="main">
    		<div>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
					   <td align="right" class="th1">
							会话时间：
						</td>
						<td width="30%" align="left" colspan="2">
							<input class="Wdate"  id="beginDate" style="width: 90px;" onchange="reFreshList();" name="bookStartTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value="bookStartTime"/>"/>  
	     						 --
	      					<input class="Wdate" id="endDate" style="width: 90px;" onchange="reFreshList();" name="bookEndTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value="bookEndTime"/>"/>
						</td>
						 <td align="right" class="th1">
							回复类型：
						</td>
						<td align="left">
							<s:select id="infoType" name="infoType" list="#{'': '全部', '0': '系统回复', '1': '人工回复'}" cssStyle="width:100px;"></s:select>
						</td>
						<td class="th1" align="right">
							昵称：
						</td>
						<td width="5%" align="left">
							<s:textfield id="nickName" name="nickName"></s:textfield>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="selector" style="display: none;">
				<div class="inner">
					<div id="list" style="width:320px;"></div>
					<div id="gird" style="left:400px;width:320px;height:480px;background-image: url('<%=request.getContextPath()%>/public/images/show.png');">
			    		<div id="info" class="new_from"></div>
						<div class="menu" id="menu" ></div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>