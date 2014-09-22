<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_customer.js"></script>
		<title>修改商户</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">商户管理-修改商户</font></span>
        </div>
    	<div class="main">
			<s:form action="customer_edit" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							商户名称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="companyName" name="companyName" value="%{entity.companyName}"></s:textfield>
						</td>
						<td class="th1">
							商户简称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="shortName" name="shortName" value="%{entity.shotName}"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="th1">
							地址:
						</td>
						<td width="35%" align="left">
							<s:textfield id="address" name="address" value="%{entity.address}"></s:textfield>
						</td>
						<td class="th1">
							是否有效:
						</td>
						<td width="35%" align="left">
							<s:select list="#{0:'有效',1:'无效'}" id="isValid" name="isValid" value="%{entity.isValid}"></s:select>
						</td>
					</tr>
					<tr>
						<td class="th1">
							绑定的IP地址:
						</td>
						<td width="35%" align="left">
							<s:textfield id="ipAddress" name="ipAddress" value="%{entity.ipAddress}"></s:textfield>
						</td>
						<td class="th1">
							提供服务类别:
						</td>
						<td width="35%" align="left">
							<s:select list="#{0: '服务1', 1: '服务2', 2: '服务3'}" id="serviceItem" name="serviceItem" value="%{entity.serviceItem}"></s:select>
						</td>
					</tr>
					<tr>
						<td class="th1">
							商户号:
						</td>
						<td width="35%" align="left">
							<s:textfield id="channelDesc" name="channelDesc" value="%{entity.channelDesc}"></s:textfield>
						</td>
						<td class="th1">
							联系人姓名:
						</td>
						<td width="35%" align="left">
							<s:textfield id="contactor" name="contactor" value="%{entity.contactor}"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="th1">
							联系人电话:
						</td>
						<td width="35%" align="left">
							<s:textfield id="phone" name="phone" value="%{entity.phone}" maxlength="15"></s:textfield>
						</td>
						<td class="th1">
							联系人手机:
						</td>
						<td width="35%" align="left">
							<s:textfield id="mobile" name="mobile" value="%{entity.mobile}" maxlength="11"></s:textfield>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<s:hidden id="id" name="id" value="%{entity.id}"></s:hidden>
							<s:submit value="" cssClass="modify_btn" onmousemove="this.className='modify_btn1'"  onmouseout="this.className='modify_btn'"></s:submit>
							<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>