<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<title>修改关注回复</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微信关注回复-修改&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="weixinAttentionEdit" method="post" theme="simple" namespace="/system">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td bgcolor="#C0C9E0" align="left">
						菜单类型：
					</td>
				</tr>
				<tr>
					<td bgcolor="#C0C9E0" align="left">
						<textarea style="width:100%;height:400px;" cols="70"  name="url" id="url"><s:property value="weixinResp.url"/></textarea>
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