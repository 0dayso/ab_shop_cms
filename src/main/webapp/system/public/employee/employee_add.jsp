<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/public/check_employee.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/jquery-1.4.js"></script>
		<title>网站用户管理</title>
		
		<script type="text/javascript">
		   	$(function(){
				$.get("<%=request.getContextPath()%>/system/customer_init.action?time="+new Date().getTime(),{},
					function(data){
						$("#customerList").html(data);
					});
				
				$.get("<%=request.getContextPath()%>/system/role_initRole.action?time="+new Date().getTime(),{},
				    function(data){
					    $("#roleId").html(data);
				    }
				);
			});
		</script>
		
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">网站用户管理-添加用户&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="employee_add" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">姓名</td>
					<td width="35%" align="left">
						<s:textfield id="name" name="name"></s:textfield>
						<font color="red">*</font>
					</td>
					<td class="th1">性别</td>
					<td width="35%" align="left"><s:select list="#{-1:'全部', 'M' :'男', 'F' :'女'}" id="gender" name="gender"></s:select></td>
				</tr>
				<tr>
					<td class="th1">证件类型</td>
					<td width="35%" align="left"><s:select list="#{-1:'全部','NI':'身份证','PP':'护照','ID':'其他'}" id="idType" name="idType"></s:select></td>
					<td class="th1">证件号码</td>
					<td width="35%" align="left"><s:textfield id="idNo" name="idNo"></s:textfield></td>
				</tr>
				<tr>
					<td class="th1">手机</td>
					<td width="35%" align="left">
						<s:textfield id="mobile" name="mobile"></s:textfield>
					</td>
					<td class="th1">邮箱</td>
					<td width="35%" align="left"><s:textfield id="email" name="email"></s:textfield></td>
				</tr>
				<tr>
					<td class="th1">邮编</td>
					<td width="35%" align="left"><s:textfield id="postcode" name="postcode"></s:textfield></td>
					<td class="th1">地址</td>
					<td width="35%" align="left"><s:textfield id="address" name="address"></s:textfield></td>
				</tr>
				<tr>
					<td class="th1">区域</td>
					<td width="35%" align="left"><s:select list="#{-1:'全部', 0:'华东', 1:'华北'}" id="area" name="area"></s:select></td>
					<td class="th1">角色权限</td>
					<td width="35%" align="left"><span id="roleId"></span></td>
				</tr>
				<tr>
					<td class="th1">登录名</td>
					<td width="35%" align="left">
						<s:textfield id="login" name="login" onblur="checkLogin()"></s:textfield>
						<font color="red">*</font>
						<span id="result"></span>
					</td>
					<td class="th1">密码</td>
					<td width="35%" align="left">
						<s:textfield id="pin" name="pin"></s:textfield>
						<font color="red">*</font>
					</td>
				</tr>
				
				<tr>
				     <td class="th1">商户选择:</td>
					 <td width="35%" align="left"><span id="customerList"></span></td>
				</tr>
				
					<s:if test="%{#session.projectName == 'VAS'}">
					<tr>
							<td align="right" class="th1">
								所属客户：
							</td>
							<td align="left"  colspan="3">
								<s:select list="customerList" id="customerId" listKey="id" listValue="companyName" name="customerId" headerKey="0" headerValue="请选择"></s:select>
								<font color="red">*</font>
							</td>
					</tr>
					</s:if>
					<s:else>
<%--						<input type="hidden" name="customerId" value="0">--%>
					</s:else>
				<tr>
					<td colspan="4">
						<s:submit value="" cssClass="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"></s:submit>
						<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'"  onclick="JavaScript:window.history.back();" />
					</td>
				</tr>
			</table>
		</s:form>
		</div>
	</body>
</html>