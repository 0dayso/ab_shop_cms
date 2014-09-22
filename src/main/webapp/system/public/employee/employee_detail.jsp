<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/public/check_employee.js"></script>
		<title>网站用户管理</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">网站用户管理-修改用户&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="employee_edit" method="post" theme="simple" namespace="/system">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">姓名</td>
					<td width="35%" align="left"><s:textfield id="name" name="name" value="%{entity.name}"></s:textfield>
					<font color="red">*</font>
					</td>
					<td class="th1">性别</td>
					<td width="35%" align="left"><s:select list="#{-1:'全部', 'M' :'男', 'F' :'女'}" id="gender" name="gender" value="%{entity.gender}"></s:select></td>
				</tr>
				<tr>
					<td class="th1">证件类型</td>
					<td width="35%" align="left"><s:select list="#{-1:'全部','NI':'身份证','PP':'护照','ID':'其他'}" id="idType" name="idType" value="%{entity.idType}"></s:select></td>
					<td class="th1">证件号码</td>
					<td width="35%" align="left"><s:textfield id="idNo" name="idNo" value="%{entity.idNo}"></s:textfield></td>
				</tr>
				<tr>
					<td class="th1">手机</td>
					<td width="35%" align="left"><s:textfield id="mobile" name="mobile" value="%{entity.mobile}"></s:textfield></td>
					<td class="th1">邮箱</td>
					<td width="35%" align="left"><s:textfield id="email" name="email" value="%{entity.email}"></s:textfield></td>
				</tr>
				<tr>
					<td class="th1">邮编</td>
					<td width="35%" align="left"><s:textfield id="postcode" name="postcode" value="%{entity.postcode}"></s:textfield></td>
					<td class="th1">地址</td>
					<td width="35%" align="left"><s:textfield id="address" name="address" value="%{entity.address}"></s:textfield></td>
				</tr>
				<tr>
					<td class="th1">区域</td>
					<td width="35%" align="left"><s:select list="#{-1:'全部', 0:'华东', 1:'华北'}" id="area" name="area" value="%{entity.area}"></s:select></td>
					<td class="th1">角色权限</td>
					<td width="35%" align="left">
						<s:select list="roleList" id="roleId" listKey="id" listValue="name" name="roleId" headerKey="0" headerValue="请选择"  value="%{roleEntity.id}"></s:select>
						<font color="red">*</font>
					</td>
				</tr>
				<s:if test="%{entity.customerId != 0  && #session.projectName == 'VAS'}">
					<tr>
							<td align="right" class="th1">
								所属客户：
							</td>
							<td align="left"  colspan="3">
								<s:select list="customerList" id="customerId" listKey="id" listValue="companyName" name="customerId" value="%{customer.id}" headerKey="0" headerValue="请选择"></s:select>
								<font color="red">*</font>
							</td>
					</tr>
				</s:if>
				<s:else>
						<input type="hidden" name="customerId" value="0">
				</s:else>
				<tr>
					<td colspan="4">
						<s:hidden id="id" name="id" value="%{entity.id}"></s:hidden>
						<s:hidden name="login" value="%{entity.login}"></s:hidden>
						<s:submit value="" cssClass="modify_btn" onmousemove="this.className='modify_btn1'"  onmouseout="this.className='modify_btn'" onclick="return validate('%{entity.login}')"></s:submit>
						<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'"  onclick="JavaScript:window.history.back();" />
					</td>
				</tr>
			</table>
		</s:form>
		</div>
	</body>
</html>