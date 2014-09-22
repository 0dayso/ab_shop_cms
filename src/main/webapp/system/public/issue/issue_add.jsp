<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_customer.js"></script>
		<title>添加彩期</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">彩期管理-添加彩期&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></font></span>
        </div>
    	<div class="main">
			<s:form action="issue_add" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							彩期名称:
						</td>
						<td align="left">
							<s:textfield id="issue" name="issue"></s:textfield>
							<font color="red">*</font>
						</td>
						<td class="th1">
							彩期KEY:
						</td>
						<td align="left">
							<s:textfield id="issueKey" name="issueKey"></s:textfield>
							<font color="red">*</font>
						</td>
						<td class="th1">
							来源:
						</td>
						<td align="left">
							<s:select list="#{'1' :'淘宝'}" id="souce" name="souce"></s:select>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td colspan="6" align="center">
							<s:submit value="" cssClass="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"></s:submit>
							<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>