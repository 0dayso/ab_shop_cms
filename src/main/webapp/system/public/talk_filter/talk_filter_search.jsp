<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/searchman.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath() %>/public/css/163css.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/kindEditor.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.9.1.js"></script>
		<SCRIPT type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></SCRIPT>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/kindeditor-min.js"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/zh_CN.js"></script>
  		<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
		<title>不文明语过滤</title>
		<style type="text/css">
			.luyu_tr_click {
				background-color: #F4F4F4;
			}
			.luyu_tr_click1 {
				background-color: #699D08;
			}
			#selector {
				top: 70px;
				height:100%;
				width:100%;
				z-index: 16;
			}
			#gird {
				top: 0px;
				left:400px;
				bottom: 60px;
				z-index: 9;
			}
			.buttonStyle{
			    background-image: url("<%=basePath%>public/images/consubmit.png");
			    background-repeat: no-repeat;
			    background-position: -1px -2px;
			    width:120px;
			    height:32px;
			    margin-left:280px;
			    margin-top:8px;
			}
		</style>
		<script type="text/javascript">
			function luyuTrClick(o, t,id) {
				if (t == 0) {
					$(".luyu_tr_click").each(function(i){
						$(this).removeClass("luyu_tr_click1");
					});
					$(o).addClass("luyu_tr_click1");
					$.ajax({
						url:"<%=request.getContextPath()%>/system/wordFilter_getWordById.action",
						type:"post",
						data:{id:id},
						dataType:"json",
						success:function(data){
							//将数据赋值在对应的表单中
							$("#id1").val(data.id);
							$("#status1").val(data.status);
							$("#word1").val(data.word);
							editor.html(data.content);
						}
					})
				} 
			}
			
			//提交表单
			function submitForm(){
				var cont = editor.html();
				if(cont == ''){
					$(".picurl").tip({width:'240',status:'error',content:'回复信息不能为空',dis_time:1000});
					return false;
				}
				var form = $("#saveForm")
				//编辑器内容赋值给对应的表单元素
				$("#content").val(cont);
				//修改内容
				$.ajax({
					url:"<%=request.getContextPath()%>/system/wordFilter_editWord.action",
					type:'POST',
					data:{"word":$("#word1").val(),"status":$("#status1").val(),"id":$("#id1").val(),"content":cont},
					dataType:"json",
					success:function(data){
						$("#con"+data.id).html(data.content);
					}
				});
				$(".picurl").tip({width:'240',status:'right',content:'编辑成功！',dis_time:1000});
			}
			

			$(function () {
				$("button").bind("click",function(){
				  	$("p").slideToggle();
				});
				reFreshList(); // 刷新列表
				initMethod();
			});

			
			function changeList(){
				$("#id1").val("");
				editor.html("");
				reFreshList();
			}
			
			function reFreshList() {
				$.ajax({
					url : "<%=request.getContextPath()%>/system/wordFilter_getAllWord.action",
					type : 'POST',
					data:{"word":$("#word").val(),"status":$("#status").val()},
					dataType:"json",
					success : function(data) {
						var result = data.list;
						var s= '<table class="tableborder" border="1" bordercolor="#dbdbdb">';
						s += '<tr class="th"><td>过滤词汇</td> <td>自动回复消息</td></tr>';
						for(var i = 0; i < result.length; i++ ){
							s+='<tr class="luyu_tr_click" onclick="luyuTrClick(this, 0,\''+result[i].id + '\');"><td>' + result[i].word + '</td><td><span id=\'con' + result[i].id + '\'>' + result[i].content + '</span></td>' + '</tr>';
						}
						s += '</table>';
						$("#list").html(s);
					}
				});
			}

			function initMethod() {
				//$("#infoType").bind("change", reFreshList);
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
									listRetNew += '<div class="new_from_pc"><div class="main_pic"></div>'+
				    							 '<div class="main_cont"><div class="label_new" id="newPc">' + obj.visitTime + "<br/>" + obj.inputInfo + '</div></div></div>';
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
				editor = K.create('#content', {
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
   			<span><font class="font1">当前位置:</font><font class="menuName_1">不文明语过滤</font></span>
    	</div>
    	<div class="main">
    		<div>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
					   <td align="right" class="th1">
							包含词语：
						</td>
						<td width="30%" align="left" colspan="2">
							<s:textfield id="word" name="word" oninput="changeList();"></s:textfield>
						</td>
						 <td align="right" class="th1">
							过滤状态：
						</td>
						<td align="left">
							<s:select id="status" name="status" list="#{'': '全部', '0': '过滤', '1': '不做过滤'}" cssStyle="width:100px;" onchange="changeList();"></s:select>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="selector">
				<div>
					<div id="list" style="width:380px;"></div>
					<div id="gird" style="left:400px;">
					<s:form action="#" method="post" id="saveForm" namespace="/system">
						<input id="id1" name="id" type="hidden" value="">
						<input id="status1" name="status" type="hidden" value="">
						<input id="word1" name="word" type="hidden" value="">
			    		<div id="info1">
			    			<s:textarea id="content" name="content" style="width:200px;height:300px;visibility:hidden;"></s:textarea>
			    		</div>
			    		<input type="button" name="button" class="buttonStyle" onclick="submitForm();">
			        </s:form>
					</div>
				</div>
			</div>
		</div>
		<div style="opacity:0;  filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px; display:none;">
       		<input type="file" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" class="picurl" name="mp3file" id="urlt_second_edt">
       	</div>
	</body>
</html>