<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">	
		<title>修改模板事件</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">模板管理-修改&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="template_edit" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
			   <tr>
					<td bgcolor="#C0C9E0" align="right" width="15%">
						模板名称:
					</td>
					<td align="left">
						<s:textfield id="name" name="name"  value="%{#request.tempInfo.name}" ></s:textfield>
					</td>
				</tr>
				<tr>
					<td bgcolor="#C0C9E0" align="right" width="15%">
						模板code:
					</td>
					<td align="left" >
						<s:textfield id="code" name="code"  value="%{#request.tempInfo.code}"></s:textfield>
					</td>
				</tr>
				<tr>
					<td class="th1">
						是否可用:
					</td>
					<td align="left" >
						<s:select id="state" name="state" value="%{#request.tempInfo.state}" list="#{1: '启用', 0: '关闭'}"></s:select>
					</td>
				</tr>
				<tr>
					<td class="th1">
						模板url:
					</td>			
					<td align="left">
						<s:textfield id="url" name="url"  value="%{#request.tempInfo.url}"></s:textfield>
					</td>
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