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
		<s:form action="weixinSelfMenuAdd" method="post" theme="simple" namespace="/system" onsubmit="return validate()" enctype="multipart/form-data">
		
		<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td bgcolor="#C0C9E0" align="right" width="15%" style="display: none">
						菜单类型：
					</td>
					<td bgcolor="#C0C9E0" align="left"  style="display: none">
						<s:select id="level" name="level" list="#{3: '3', 1: '主菜单(有子菜单)', 2: '子菜单'}" value="3"></s:select>
					</td>
				</tr>
				<tr>
					<td class="th1"  style="display: none">
						选择商户:
					</td>
					<td  align="left"   style="display: none"><span id="customerList"></span></td>
				</tr>
				<tr>
					<td class="th1"  style="display: none">
						菜单序列:
					</td>
					<td align="left"  style="display: none">
						<s:textfield id="buttonNo" name="buttonNo" onkeyup="value=value.replace(/[^\d]/g,'')" value="0"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="th1"  style="display: none">
						菜单名称:
					</td>			
					<td align="left"  style="display: none">
						<s:textfield id="name" name="name22"  style="display: none" value="关键字"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="th1">
						关键字词:
					</td>
					<td width="35%" align="left">
						<s:textfield id="keyId" name="keyId" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="th1">
						是否启用:
					</td>
					<td width="35%" align="left" >
						<s:select id="state" name="state" list="#{0: '启用', 1: '关闭'}"></s:select>
					</td>
				</tr>
				<tr>
					<td class="th1">
						业务名称:
					</td>			
					<td width="35%" align="left">
						<s:textfield id="name" name="name"  value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="th1">
						回复信息:
					</td>
					<td width="50%" align="left">
						<s:textarea id="content" name="content"  cols="70" rows="5" ></s:textarea>
					</td>
				</tr>
				<tr>
					<td class="th1">
						上级业务:
					</td>
					<td width="20%" align="left">
						<span id="subIdList"></span>
					</td>
				</tr>
				<tr>
					<td class="th1">
						回复类型:
					</td>			
					<td align="left">
						<s:select id="reType" onclick="selectReType();" name="reType" list="#{'text' : 'text', 'news': 'news'}"></s:select>
					</td>
				</tr>
				<tr id="isNews1" style="display: none">
					<td class="th1">
						图文标题:
					</td>
					<td align="left">
						<s:textarea id="title" name="reTitle"  cols="70" rows="5" ></s:textarea>
					</td>
				</tr>
				<tr id="isNews2" style="display: none">
					<td class="th1">
						图文链接: 
					</td>
					<td width="35%" align="left" >
						<s:textarea id="url" name="reUrl"  cols="70" rows="5" ></s:textarea>
					</td>
				</tr>
				<tr id="isNews3" style="display: none">
					<td class="th1">附件图文:</td>
					<td align="left">
						<div id="children">
							<s:file name="upload" id="upload1"></s:file>
							<input type="button" onclick="createFile();" value="点击继续添加" />
						</div>
					</td>
				</tr>
				<tr name="isNews" style="display: none">
					<td class="th1"  style="display: none">
						事件类型:
					</td>
					<td width="35%" align="left"  style="display: none">
						<s:select id="type" name="type" list="#{'searchKey': '关键字','click': '点击事件'}" value="searchKey"></s:select>
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