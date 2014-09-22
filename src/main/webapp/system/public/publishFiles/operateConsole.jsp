<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>发布窗口</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/uploadify.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.set.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<link href="<%=request.getContextPath()%>/public/css/jquery.loadmask.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery.loadmask.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery.uploadify.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/markitup/jquery.markitup.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/markitup/set.js"></script>
		<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function() {
			    $("#uploadify").uploadify({
			    	'swf'			:'<%=request.getContextPath()%>/public/js/uploadify.swf',
			    	'uploader'		:'<%=request.getContextPath()%>/system/publish_upload.action',
			    	'fileTypeExts'	:'*.png;*.jpg;*.html;*.war',
  					'fileTypeDesc'	:'目前仅支持png,jpg,html,war',
  					'fileObjName'	:'fileInput',
			        'buttonText'	:'请选择文件',
			        'auto'			:true, 
			        'removeCompleted': false,
					'onUploadSuccess':function(file,data,response) {
						$("#tempName").val(file.name);
						$('.picurl').tip({width:'240',status:'error',content:file.name + '上传成功.',dis_time:1000});
			        },
					'onCancel'		:function(file) {
			        	$('.picurl').tip({width:'240',status:'error',content:file.name + '上传取消.',dis_time:1000});
			        },
			        'onUploadError'	:function(file, errorCode, errorMsg, errorString) {
			        	$('.picurl').tip({width:'240',status:'error',content:file.name + '上传失败,原因:' + errorString,dis_time:1000});
			        }
			    });
			    $('#markItUp').markItUp(mySettings);
			    $('#show_progress').click(function() {
			 		$('#markItUp').markItUp('insert',
						{placeHolder:"ps auwx | grep java"}
					);
			 		return false;
				});
			});
			
			function confirmExe() {
				var commands = "";
				$.markItUp({ 
					openWith:'', 
					beforeInsert:function(h) {
						commands = h.selection;
					  },
					closeWith:''
				});
				
				$("body").mask("命令下发中,稍后请收看反馈信息...");
				$.post(
					"<%=request.getContextPath()%>/system/publish_commandsDown.action",
					{commands:commands},
					function(data){
						$("#markItUp").val($("#markItUp").val() + "\n" + data);
						$("body").unmask();
					}
				);
			}

			function replaceFiles() {
				
			}
		</script>
	</head>
	
	<body>
		<div class="headerPart">
			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
			<span><font class="font1">当前位置:</font><font class="menuName_1">发布管理 - 发布窗口</font></span>
		</div>
		<div class="main">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr id="tr1">
					<td class="th1" >
						终端类型：
					</td>
					<td align="left">
						<s:select id="type" name="type" list="#{1:'官网(活动)', 2:'手机(客户端)', 3:'触屏版(html5)', 4:'IPAD'}" cssStyle="width:200px;"></s:select>
					</td>
				</tr>
				<tr id="tr1">
					<td class="th1" width="15%">
						上传文件：
					</td>
					<td align="left">
						<div id="fileQueue"></div>
	　					<input type="file" name="fileInput" id="uploadify" />
					</td>
				</tr>
				<tr id="tr1">
					<td class="th1">
						替换操作：
					</td>
					<td align="left">
						<input type="button" value="点我替换" style="color:#F00;font-weight: bold;" title="" class="business_btn"  onclick="return replaceFiles();" />
						<SPAN>亲，在您点击之前，您一定要确定好，否则出现问题可不能冤枉我们喔！</SPAN>
					</td>
					<input style="display:none" name="tempName" id="tempName" value=""/>
				</tr>
				<tr id="tr2">
					<td class="th1" width="15%">
						监控窗口：
					</td>
					<td align="left">
						<textarea id="markItUp" cols="80" rows="20"></textarea>
					</td>
				</tr>
			</table>
			<div style="opacity:0; filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px; display:none;">
	       		<input class="picurl" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" >
	       	</div>
		</div>
	</body>
</html>