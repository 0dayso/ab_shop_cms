<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_customer.js"></script>
		<title>增加商户</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">商户管理-添加商户&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></font></span>
        </div>
    	<div class="main">
			<s:form action="customer_add" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							商户名称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="companyName" name="companyName"></s:textfield>
							<font color="red">*</font>
						</td>
						<td class="th1">
							商户简称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="shotName" name="shotName"></s:textfield>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="th1">
							地址:
						</td>
						<td width="35%" align="left">
							<s:textfield id="address" name="address"></s:textfield>
						</td>
						<td class="th1">
							是否有效:
						</td>
						<td width="35%" align="left">
							<s:select list="#{0:'有效',1:'无效'}" id="isValid" name="isValid"></s:select>
						</td>
					</tr>
					<tr>
						<td class="th1">
							绑定的IP地址:
						</td>
						<td width="35%" align="left">
							<s:textfield id="ipAddress" name="ipAddress"></s:textfield>
						</td>
						<td class="th1">
							提供服务类别:
						</td>
						<td width="35%" align="left">
							<s:select list="#{0: '服务1', 1: '服务2', 2: '服务3'}" id="serviceItem" name="serviceItem"></s:select>
						</td>
					</tr>
					<tr>
						<td class="th1">
							商户号:
						</td>
						<td width="35%" align="left">
							<s:textfield id="channelDesc" name="channelDesc"></s:textfield>
						</td>
						<td class="th1">
							联系人姓名:
						</td>
						<td width="35%" align="left">
							<s:textfield id="contactor" name="contactor"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="th1">
							联系人电话:
						</td>
						<td width="35%" align="left">
							<s:textfield id="phone" name="phone" maxlength="15"></s:textfield>
						</td>
						<td class="th1">
							联系人手机:
						</td>
						<td width="35%" align="left">
							<s:textfield id="mobile" name="mobile" maxlength="11"></s:textfield>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<s:submit value="" cssClass="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"></s:submit>
							<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>