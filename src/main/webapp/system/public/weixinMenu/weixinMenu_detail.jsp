<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
		$(function(){
			//var name ='<s:property value="%{#request.weixinMenu.customer}"/>';
			$.get("<%=request.getContextPath()%>/system/customer_init.action?time="+new Date().getTime(),{name:'CZ'},
				function(data){
					$("#customerList").html(data);
				});
			var id ='<s:property value="%{#request.weixinMenu.subId}"/>';
			$.get("<%=request.getContextPath()%>/system/weixinMenu_lastMenu.action?time="+new Date().getTime(),{subId:id},
					function(data){
						$("#lastMenuList").html(data);
					});
		});
		</script>
		<script type="text/javascript">
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
				//if (document.getElementById('keyId').value=='') {
				//	alert("若为点击事件，则事件KEY值必须有值！");
				//	return false;
				//}
			}				
			return true;
		}
		
		</script>		
		<title>修改微信菜单</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微信菜单管理-修改微信菜单&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="weixinMenu_edit" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">
						菜单类型:
					</td>
					<td align="left">
						<s:select id="level" name="level" list="#{1: '主菜单', 2: '子菜单'}"
						value="%{#request.weixinMenu.level}"></s:select>
					</td>
					<td class="th1">
						上级菜单:
					</td>
					<td align="left"><span id="lastMenuList"></span></td>
				</tr>
				<tr>
					<td class="th1">
						菜单名称:
					</td>			
					<td width="35%" align="left">
						<s:textfield id="name" name="name" value="%{#request.weixinMenu.name}" ></s:textfield>
					</td>
					<td class="th1">
						事件类型:
					</td>
					<td width="35%" align="left">
						<s:select id="type" name="type" list="#{'click': '点击事件'}" value="%{#request.weixinMenu.type}"></s:select>
					</td>
				</tr>
				<tr>
					<td class="th1">
						事件编码:
					</td>
					<td width="35%" align="left">
						<s:textfield id="keyId" name="keyId" value="%{#request.weixinMenu.keyId}"></s:textfield>
					</td>
					<td class="th1">
						是否启用:
					</td>
					<td width="35%" align="left" >
						<s:select id="state" name="state" list="#{0: '启用', 1: '关闭'}" value="%{#request.weixinMenu.state}"></s:select>
					</td>
				</tr>
				<tr>
					<td class="th1"  style="display: none">
						菜单级别:
					</td>
					<td width="35%" align="left"  style="display: none">
					</td>
					<td class="th1" style="display: none">
						选择商户:
					</td>
					<td align="left"><span id="customerList"  style="display: none"></span></td>
				</tr>
			</table>
			
			
			<div style="text-align: center;">
				<span>
					<s:hidden id="id" name="id" value="%{id}"></s:hidden>
					<s:submit value="" cssClass="modify_btn" onmousemove="this.className='modify_btn1'" onmouseout="this.className='modify_btn'"></s:submit>
					<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();">
				</span>
			</div>
		</s:form>
		</div>
	</body>
</html>