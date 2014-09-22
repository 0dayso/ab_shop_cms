<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">	
		<title>修改微信事件</title>
	</head>
	<body>
		<div style="height:30px;">
   			<img src="<%=request.getContextPath()%>/public/images/tubiao-01.png"/><span><font class="font1">当前位置:</font><font color="#085370">微信事件管理-修改微信事件</font></span>
    	</div>
    	<div class="title">
    		<span style="padding:3px;height:26px;line-height:26px;">修改微信事件(带<font color="red">*</font>的为必填项)</span>
    	</div>
    	<div class="main">
		<s:form action="event_edit" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">事件编码:</td>
					<td align="left"><s:textfield id="code" name="code" value="%{code}" ></s:textfield></td>
					<td class="th1">事件名称:</td>
					<td align="left"><s:textfield id="name" name="name" value="%{name}" ></s:textfield></span></td>
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