<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript">
		/* 判断元素值是否为空 element:element in the page message:error message */
		function isNotEmpty(element, message) {
			if (element.value.length == 0
					|| element.value.replace(/\s/g, '').length == 0) {
				alert(message);
				element.focus();
				return false;
			}
			return true;
		}		
		function validate() {
			if (!isNotEmpty(document.getElementById('title'), "回复标题不能为空！")) {
				return false;
			}
			if (!isNotEmpty(document.getElementById('keyId'), "回复KEY值不能为空！")) {
				return false;
			}
			if (!isNotEmpty(document.getElementById('description'), "回复描述不能为空！")) {
				return false;
			}
			if (!isNotEmpty(document.getElementById('picName'), "回复图片名称不能为空！")) {
				return false;
			}
			if (!isNotEmpty(document.getElementById('url'), "回复URL链接不能为空！")) {
				return false;
			}		
			return true;
		}
		$(function(){
			$.get("<%=request.getContextPath()%>/system/customerSearch_init.action?time="+new Date().getTime(),{name:'CZ'},
				function(data){
					$("#customerList").html(data);
				});
		});

		function pre() {
			var title = $("#title").val();
			var picName = $("#upload").val();
			var result = "<span>标题:" + title + "</span><br/><span>图片：" + picName + " </span>";
			$("#pre").html(result);
		}

		function createFile(){
		    var input = document.createElement('input');
		    input.setAttribute('type', 'file');
		    input.setAttribute('name', 'upload');
		    var parent = document.getElementById('children');
		    parent.insertBefore(document.createElement('br'), null);
		    parent.insertBefore(input, null);

		}
		</script>
		<title>添加菜单回复</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微信管理-添加菜单回复&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="weixinRespAdd" method="post" theme="simple" namespace="/system" onsubmit="return validate()" enctype="multipart/form-data">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				 <tr>
					<td bgcolor="#C0C9E0" align="right" width="15%" style="display: none">
						选择商户:
					</td>
					<td bgcolor="#C0C9E0" align="left" colspan="3" style="display: none">
						<span id="customerList">
					</td>
				</tr>
				<tr>
					<td class="th1">
						回复标题:
					</td>
					<td width="35%" align="left">
						<s:textfield id="title" name="title"></s:textfield>
					</td>
					<td class="th1">
						回复类型:
					</td>			
					<td width="35%" align="left">
						<s:select id="type" name="type" list="#{'text': '文本消息','news': '图文消息'}"></s:select>
					</td>
					</tr>
				<tr>
					<td class="th1">
						事件编码:
					</td>
					<td width="35%" align="left">
						<s:textfield id="keyId" name="keyId" ></s:textfield>
					</td>			
					<td class="th1">
						<input type="button" onclick="" value="图片上传:" />
					</td>
					<td width="35%" align="left">
						<div id="children">
							<s:file name="upload" id="upload1"></s:file>
							<input type="button" onclick="createFile();" value="点击继续添加" />
						</div>
						
					</td>
				</tr>
				<tr>
					<td class="th1">
						回复描述:
					</td>
					<td width="35%" align="left">
						<s:textfield id="description" name="description" ></s:textfield>
					</td>
					<td class="th1">
						URL链接: 
					</td>
					<td width="35%" align="left" >
						<s:textfield id="url" name="url" ></s:textfield>
					</td>
				</tr>
			</table>
			
			<div style="text-align: center;">
				<span>
						<s:submit value="" cssClass="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"></s:submit>
					<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();">
				</span>
			</div>
		</s:form>
		</div>
	</body>
</html>