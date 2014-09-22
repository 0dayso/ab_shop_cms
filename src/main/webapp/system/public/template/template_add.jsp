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
			if (!isNotEmpty(document.getElementById('name'), "菜单名称不能为空！")) {
				return false;
			}
			if (!isNotEmpty(document.getElementById('buttonNo'), "菜单序列不能为空！")) {
				return false;
			}
			if (document.getElementById('type').value!='') {
				if (document.getElementById('keyId').value=='') {
					alert("若为点击事件，则事件KEY值必须有值！");
					return false;
				}
			}				
			return true;
		}

		$(function(){
			$.get("<%=request.getContextPath()%>/system/customerSearch_init.action?time="+new Date().getTime(),{name:'CZ'},
				function(data){
					$("#customerList").html(data);
				});
			
			$.get("<%=request.getContextPath()%>/system/weixinMenuSearch_lastKeyMenu.action?time="+new Date().getTime(),{},
					function(data){
						$("#subIdList").html(data);
					});
		});

		function selectReType() {
			var reTypeValue = $("#reType").val();
			if ("news" == reTypeValue) {
				$("#isNews1").css("display", "");
				$("#isNews2").css("display", "");
				$("#isNews3").css("display", "");
			} else if ("text" == reTypeValue) {
				$("#isNews1").css("display", "none");
				$("#isNews2").css("display", "none");
				$("#isNews3").css("display", "none");
			}
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
		<title>增加微信菜单</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微信关键字回复管理-添加微信关键字&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="template_add" method="post" theme="simple" namespace="/system" onsubmit="return validate()" enctype="multipart/form-data">
		
		<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td bgcolor="#C0C9E0" align="right" width="15%">
						模板名称:
					</td>
					<td align="left">
						<s:textfield id="name" name="name"  value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td bgcolor="#C0C9E0" align="right" width="15%">
						模板code:
					</td>
					<td align="left" >
						<s:textfield id="code" name="code"  value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="th1">
						是否可用:
					</td>
					<td align="left" >
						<s:select id="state" name="state" list="#{1: '启用', 0: '关闭'}"></s:select>
					</td>
				</tr>
				<tr>
					<td class="th1">
						模板url:
					</td>			
					<td align="left">
						<s:textfield id="url" name="url"  value=""></s:textfield>
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