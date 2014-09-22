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
		<link href="<%=request.getContextPath()%>/public/css/picturecss.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/kindEditor.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.9.1.js"></script>
		<SCRIPT type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></SCRIPT>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/kindeditor-min.js"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/zh_CN.js"></script>
  		<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ajaxfileupload.js"></script>
		<title>基本信息</title>
		<style type="text/css">
		    body{
		      overflow:scroll;
		    }
		    ul li{
		      list-style: none;
		      display:float;
		      overflow: hidden;
		      margin-bottom: 10px;
		      font-size:14px;
		    }
		    .titleSe{
		       float:left;
		       width:80px;
		    }
		    .contentSe{
		       float:left;
		       margin-left: 10px;
		    }
		    
		    .logoDiv{
				position:relative;
				width:320px;
			}
			.doto{
			   borad:0 none;
			}
			.modify1{
			    height:34px;
				position:absolute;
				top:105px;
				width:320px;
			}
			.edit1{
			    cursor:pointer;
				float:right;
				height:33px;
				width:40px;
			}
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
			
			//提交表单
			function submitForm(){
				var cont = editor.html();
				if(cont == ''){
					$(".picurl").tip({width:'240',status:'error',content:'回复信息不能为空',dis_time:1000});
					return false;
				}
				var form = $("#saveForm")
				//编辑器内容赋值给对应的表单元素
				$("#content1").val(cont);
				//修改内容
				$.ajax({
					url:"<%=request.getContextPath()%>/system/conference_editAjax.action",
					type:'POST',
					data:{"title":$("#title1").val(),"state":$("#state1").val(),"id":$("#id1").val(),"content":cont,"logoUrl":$("#logoUrl").val()},
					dataType:"json",
					success:function(data){
						if(data.status == 'success'){ 
							$("#title_" + data.id).html(data.title);
							alert("编辑成功");
							//$(".picurl").tip({width:'240',status:'right',content:'编辑成功！',dis_time:1000});
						}else{
							alert("编辑失败");
							//$(".picurl").tip({width:'240',status:'error',content:'编辑失败！',dis_time:1000});
						}
					}
				});
				
			}
			

			$(function () {
				reFreshList(); // 刷新列表
			});
			function changeList(){
				$("#id1").val("");
				$("#gird").hide();
				editor.html("");
				reFreshList();
			}
			
			function reFreshList() {
				$.ajax({
					url : "<%=request.getContextPath()%>/system/conference_getAllInfos.action",
					type : 'POST',
					data:{"state":$("#state").val()},
					dataType:"json",
					success : function(data) {
						var s= '<table class="tableborder" border="1" bordercolor="#dbdbdb">';
						s += '<tr class="th"><td style="width:70%;">会议标题</td><td style="width:30%;">操作</td></tr>';
						for(var i = 0; i < data.length; i++ ){
							s+='<tr class="luyu_tr_click" onclick="conferenceEdit(\''+data[i].id + '\');">' +
							'<td style="width:70%;"><span id="title_' + data[i].id + '">' + data[i].title + '</span></td>'+
							'<td style="width:30%;">'+
							'<div><input type="button" value="" class="modify_btn" onmousemove="'+ 'this.className=\'modify_btn1\'" onmouseout="' + 'this.className=\'modify_btn\'" onClick="conferenceEdit(\'' + data[i].id + '\')"/>' +
							'<input type="button" value="" class="del_btn" onmousemove="'+ 'this.className=\'del_btn1\'" onmouseout="' + 'this.className=\'del_btn\'" onClick="conferenceDelete(\'' + data[i].id + '\')"/></div>' +
							'</td>' + 
							'</tr>';
						}
						s += '</table>';
						$("#list").html(s);
					}
				});
			}
			//编辑会议
			function conferenceEdit(id){
				editor.html("");
				$.get("<%=request.getContextPath()%>/system/conference_getInfoById.action?time="+new Date().getTime(),{id:id},
						function(data){
							var vo = eval("(" + data + ")");
							//封装各自的数值
							$("#id1").val(vo.id);
							$("#title1").val(vo.title);
							$("#logo1").attr("src",vo.logoUrl);
							$("#logoUrl").val(vo.logoUrl);
							editor.html(vo.content);
							$("#state1").val(vo.state);
						}
					);
				$("#gird").show();
			}
			//删除会议
			function conferenceDelete(id){
				$.get("<%=request.getContextPath()%>/system/conference_deleteById.action?time="+new Date().getTime(),{id:id},
						function(data){
							var vo = eval("(" + data + ")");
							var s= '<table class="tableborder" border="1" bordercolor="#dbdbdb">';
						    s += '<tr class="th"><td style="width:70%;">会议标题</td><td style="width:30%;">操作</td></tr>';
							for(var i = 0; i < vo.length; i++ ){
								s+='<tr class="luyu_tr_click" onclick="conferenceEdit(\''+vo[i].id + '\');">' +
								'<td style="width:70%;"><span id="title_' + vo[i].id + '">' + vo[i].title + '</span></td>'+
								'<td style="width:30%;">'+
								'<div><input type="button" value="" class="modify_btn" onmousemove="'+ 'this.className=\'modify_btn1\'" onmouseout="' + 'this.className=\'modify_btn\'" onClick="conferenceEdit(\'' + vo[i].id + '\')"/>' +
								'<input type="button" value="" class="del_btn" onmousemove="'+ 'this.className=\'del_btn1\'" onmouseout="' + 'this.className=\'del_btn\'" onClick="conferenceDelete(\'' + vo[i].id + '\')"/></div>' +
								'</td>' + 
								'</tr>';
							}
							s += '</table>';
							$("#list").html(s);
						}
					);
				$("#gird").hide();
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
				editor = K.create('#content1', {
					themeType : 'qq',
					items : [
						'bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','plug-order','plug-indent','link'
					]
				});
			});
			$(function(){
               $(".logoDiv").find("div").bind('mouseover mouseout', function(event) {
				  if(event.type == 'mouseover') {
				   		$(this).find(".modify1").addClass("mdf-alpha");
						$(this).find(".edit1").addClass("m_editH");
				  } else {
				  	    $(this).find(".modify1").removeClass("mdf-alpha");
						$(this).find(".edit1").removeClass("m_editH");
				  }
				});
               
		   });
		
			function liando(){
				$("#mp3fileupload2").click();
			}
			 //上传异步文件
		    //参数说明：id：为input对应的ID，  resType对应于素材类型
			function ajaxFileUpload(id, resType){
				var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
				if (! reg.test($("#"+id).val()))
				{
					alert("图片格式必须为jpg、jpeg、png、bmp! ");
					return false;
				}
			    $.ajaxFileUpload({
			      url:'<%=path %>/system/conference_upload.action',  
			      secureuri:false,
			      fileElementId:id,                         //文件选择框的id属性
			      data: {resType:resType},
			      dataType: 'json',                                     //服务器返回的格式，可以是json
			      complete:function(){
						$("#loading").hide();
				  },				
				  success: function (data, status){
						if(data.status == "success"){ 
							$("#logoUrl").val(data.link);
							$("#logo1").attr("src",data.link);
						}else{
							$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
						}
				  },
				  error: function (data, status, e){
						alert(e);
				  }
			    });
			}
			
		</script>
	</head>
	<body>
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">会议管理  - 基本信息</font></span>
    	</div>
    	<div class="main">
    		<div>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
					   <td align="right" class="th1">
							会议状态：
						</td>
						<td width="30%" align="left" colspan="2">
							<s:select id="state" name="state" list="#{'1': '激活', '0': '关闭'}" cssStyle="width:100px;" onchange="changeList();"></s:select>
						</td>
						<td align="left">
							<input type="button" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'" onclick="javascript:window.location.href='<%=request.getContextPath() %>/system/public/conference/conference_add.jsp'" class="add_btn" value=""/>
						</td>
					</tr>
				</table>
			</div>
			
			<div id="selector">
				<div>
					<div id="list" style="width:380px;"></div>
					<div id="gird" style="left:400px; display:none;" >
					<s:form action="#" method="post" id="saveForm" namespace="/system">
					    <ul>
					       <li>
					          <div class="titleSe"><span>会议标题:</span></div>
					          <div class="contentSe"><s:textfield name="title" id="title1" value="" cssStyle="width:320px;"/></div>
					       </li>
					       <li>
					          <div class="titleSe"><span>会议logo:</span></div>
					          <div class="contentSe logoDiv">
					              <div class="doto">
					                   <img id="logo1" width="320" height="130"/>
					                   <div class="modify1">
					                       <div class="edit1" title="编辑" onclick="liando();"></div>
					                   </div>
					                   <input type="file" style="display:none;" onchange="ajaxFileUpload('mp3fileupload2','image');" id="mp3fileupload2" name="imagefile"/>
					               </div>
					           </div>
					       </li>
					        <li>
					       		 <div class="titleSe"><span>建议尺寸:</span></div>
					          	 <div class="contentSe"><span style="color:red;">640px * 130px</span></div>
					       </li>
					       <li>
					          <div class="titleSe"><span>激活状态:</span></div>
					          <div class="contentSe"><s:select id="state1" name="state" list="#{'': '全部', '0': '关闭', '1': '激活'}" cssStyle="width:320px;"></s:select></div>
					       </li>
					       <li>
					          <div class="titleSe"><span>会议简介:</span></div>
					          <div class="contentSe"><s:textarea id="content1" name="content" style="width:320px;height:150px;visibility:hidden;"></s:textarea></div>
					       </li>
					    </ul>
						<input id="id1" name="id" type="hidden" value=""/>
						<input id="logoUrl" name="logoUrl" type="hidden" value=""/>
			    		<input type="button" name="button" class="buttonStyle" onclick="submitForm();"/>
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