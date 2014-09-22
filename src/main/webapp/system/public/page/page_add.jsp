<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/jquery-1.4.js"></script>
		<link href="<%=request.getContextPath()%>/public/css/uploadify.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.set.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery.uploadify.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/markitup/jquery.markitup.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/markitup/set.js"></script>
		<title>微页添加</title>
		<script type="text/javascript">
		   	$(function(){
			   	 $("#page_file").uploadify({
			    	'swf'			:'<%=request.getContextPath()%>/public/js/uploadify.swf',
			    	'uploader'		:'<%=request.getContextPath()%>/system/weixinKey_uploadZIP.action',
			    	'fileTypeExts'  :'*.zip;', 
					'fileTypeDesc'	:'仅支持ZIP格式，注意：ZIP中必须包含index.html',
					'fileObjName'	:'fileInput',
			        'buttonText'	:'选择',
			        'auto'			:true, 
			        'removeCompleted': true,
					'onUploadSuccess':function(file,data,response) {
			    		var vo = eval("(" + data + ")");
			    		$("#tr_show").css("display","");
			    		if (vo.code == "ok") {
			    			var result = '<a href="' + vo.msg + '" style="color:red" target="_blank" alt = "点击预览">上传成功，点此预览</a>';
			    			$("#show").html(result);
			    			$("#pageName").val(vo.msg);
			    		} else {
			    			$("#pageName").val("");
							$("#show").html('<span style="color:red">非常遗憾，上传失败！</span>');
				    	}
			        },
					'onCancel'		:function(file) {
			            alert(file.name + '上传取消.');
			        },
			        'onUploadError'	:function(file, errorCode, errorMsg, errorString) {
			            alert(file.name + '上传失败,原因:' + errorString);
			        }
			    });

			   	$.get("<%=request.getContextPath()%>/system/weixinMenu_selectMenu.action?time="+new Date().getTime(),{type:'news'},
					function(data){
						$("#menuList").html(data);
						changeSel1();
					}
				);
				
				$.get("<%=request.getContextPath()%>/system/weixinKey_selectKey.action?time="+new Date().getTime(),{type:'news'},
					function(data){
						$("#keyList").html(data);
						changeSel2();
					}
				);
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

			function save() {
				var pageName = $("#pageName").val();
				if (pageName == "") {
					alert("未上传微页面");
					return;
				}
				var menuId = $("#menu").val();
				var keyId = $("#key").val();
				$.get("<%=request.getContextPath()%>/system/packPage_addPage.action?time="+new Date().getTime(),
					{pageName:pageName,menuId:menuId,keyId:keyId},
					function(data){
						var vo = eval("(" + data + ")");
						if (vo.code=="ok") {
							alert("恭喜您，添加成功！");
						} else {
							alert('对不起，添加失败！');
						}
					}
				);
			}
		</script>
		
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微页管理-添加微页&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
    		<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">选择微页：</td>
					<td align="left"><input type="file" name="fileInput" id="page_file" value="" /></td>
					<td align="left">注意：上传文件仅支持ZIP格式，且ZIP中必须包含index.html文件</td>
				</tr>
				<tr>
					<td class="th1">图文菜单：</td>
					<td align="left"><div class="select1" id="menuList"></div></td>
					<td align="left">说明：选中该图文菜单，在用户点击该图文后，自动跳转到该微页面</td>
				</tr>
				<tr>
					<td class="th1">关键字词：</td>
					<td align="left"><div class="select1" id="keyList"></div></td>
					<td align="left">说明：选中该关键字词，在用户回复该关键字词后，自动跳转到该微页面</td>
				</tr>
				<tr id="tr_show" style="display: none">
					<td align="left" colspan="2"><div id="show" style="padding-left:100px;"></div></td>
					<td align="left">
						<input type="text" style="display:none" name="pageName" id="pageName" value="" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan="3">
						<input type="button" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'" onclick="save();" class="add_btn" value=""/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>