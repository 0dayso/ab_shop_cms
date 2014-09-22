<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">	
		<title>素材详细</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">素材管理-素材详细&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="template_edit" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
			   <tr>
					<td bgcolor="#C0C9E0" align="right" width="15%">
						素材标题:
					</td>
					<td align="left">
						<s:property value="%{entity.title}"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#C0C9E0" align="right" width="15%">
						素材内容:
					</td>
					<td align="left" >
						<s:property value="%{entity.content}"/>
					</td>
				</tr>
				<tr>
					<td class="th1">
						素材资源:
					</td>
					<td align="left" >
						<s:property value="%{entity.picName}"/>
					</td>
				</tr>
				<tr>
					<td class="th1">
						素材URL:
					</td>			
					<td align="left">
						<s:property value="%{entity.url}"/>
					</td>
				</tr>
			</table>
			<div style="text-align: center;">
				<span>
					<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();">
				</span>
			</div>
		</s:form>
		</div>
	</body>
</html>