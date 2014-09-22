<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/public/check_permission.js"></script>
	</head>
	<body onload="change()">
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">权限管理-添加功能模块&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
			<s:form action="permission_add" method="post" theme="simple"
				namespace="/system" onsubmit="return validate()">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td bgcolor="#C0C9E0" align="right" width="15%">
							功能菜单级别：
						</td>
						<td bgcolor="#C0C9E0" colspan="3" align="left">
							<s:radio cssStyle="border: 0px;" list="#{1:'一级功能菜单',2:'二级功能菜单',3:'三级功能菜单'}" name="menuValue"
								onclick="change()" value="1"></s:radio>
						</td>
					</tr>
				</table>
				
				<table class="tableborder" border="1" bordercolor="#dbdbdb" style="display: block;" id="right_1">
					<tr>
						<td class="th1">
							一级菜单功能名称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="name1" name="name1" size="50"></s:textfield>
							<font color="red">*</font>
						</td>
						<td class="th1">
							一级菜单功能路径:
						</td>
						<td width="35%" align="left">
							<s:textfield id="path1" name="path1" size="50" value="welcome.jsp"
								readonly="true"></s:textfield>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="th1">
							一级菜单功能级别:
						</td>
						<td width="35%" align="left">
							<s:select name="level1"
								list="#{'0':'请选择','1':'一级功能','2':'二级功能','3':'三级功能'}" value="1"
								disabled="true" />
							<font color="red">*</font>
						</td>
						<td class="th1">
							一级菜单功能描述:
						</td>
						<td width="35%" align="left">
							<s:textfield id="description1" name="description1" size="50"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="th1">
							菜单排序:
						</td>
						<td width="35%" align="left">
							<s:textfield id="sort1" name="sort1" size="10" value="0"></s:textfield>
						</td>
						<td class="th1">
							新功能标记:
						</td>
						<td width="35%" align="left">
							<s:select name="isNew1" id="isNew1" list="#{'0':'不标记新功能','1':'标记新功能'}"/>
						</td>
					</tr>
				</table>
				
				<table class="tableborder" border="1" bordercolor="#dbdbdb" id="right_2" style="display: none;">
					<tr>
						<td class="th1">
							一级菜单功能名称:
						</td>
						<td width="35%" align="left" id="rightLevel1">
						</td>
						<td class="th1">
							二级菜单功能名称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="name2" name="name2" size="50"></s:textfield>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="th1">
							二级菜单功能路径:
						</td>
						<td width="35%" align="left">
							<s:textfield id="path2" name="path2" size="50"></s:textfield>
							<font color="red">*</font>
						</td>
						<td class="th1">
							二级菜单功能级别:
						</td>
						<td width="35%" align="left">
							<s:select name="level2"
								list="#{'0':'请选择','1':'一级功能','2':'二级功能','3':'三级功能'}" value="2"
								disabled="true" />
						</td>
					</tr>
					<tr>
						<td class="th1">
							二级菜单功能描述:
						</td>
						<td width="35%" align="left" colspan="3">
							<s:textfield id="description2" name="description2" size="50"></s:textfield>
							<s:hidden name="functionRight1" id="fr1"></s:hidden>
						</td>
					</tr>
					<tr>
						<td class="th1">
							菜单排序:
						</td>
						<td width="35%" align="left">
							<s:textfield id="sort2" name="sort2" size="10" value="0"></s:textfield>
						</td>
						<td class="th1">
							新功能标记:
						</td>
						<td width="35%" align="left">
							<s:select name="isNew2" id="isNew2" list="#{'0':'不标记新功能','1':'标记新功能'}"/>
						</td>
					</tr>
				</table>
				<table class="tableborder" border="1" bordercolor="#dbdbdb" id="right_3" style="display: none;">
					<tr>
						<td class="th1">
							一级菜单功能名称:
						</td>
						<td width="35%" align="left" id="rightLevel2">
						</td>
						<td class="th1">
							二级菜单功能名称:
						</td>
						<td width="35%" align="left" id="rightLevel3">
							
						</td>
					</tr>
					<tr>
						<td class="th1">
							三级菜单功能名称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="name3" name="name3" size="50"></s:textfield>
							<font color="red">*</font>
						</td>
						<td class="th1">
							三级菜单功能路径:
						</td>
						<td width="35%" align="left">
							<s:textfield id="path3" name="path3" size="50"></s:textfield>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="th1">
							三级菜单功能级别:
						</td>
						<td width="35%" align="left">
							<s:select name="level3" id="level3"
								list="#{'0':'请选择','3':'三级功能','4':'三级权限'}"/><font color="red">*可选择三级权限和三级功能</font>
						</td>
						<td class="th1">
							三级菜单功能描述:
						</td>
						<td width="35%" align="left" >
							<s:textfield id="description3" name="description3" size="50"></s:textfield>
							<s:hidden name="functionRight2" id="fr2"></s:hidden>
						</td>
					</tr>
					<tr>
						<td class="th1">
							菜单排序:
						</td>
						<td width="35%" align="left">
							<s:textfield id="sort3" name="sort3" size="10" value="0"></s:textfield>
						</td>
						<td class="th1">
							新功能标记:
						</td>
						<td width="35%" align="left">
							<s:select name="isNew3" id="isNew3" list="#{'0':'不标记新功能','1':'标记新功能'}"/>
						</td>
					</tr>
				</table>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td align="center">
							<s:submit value="" cssClass="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"></s:submit>
							<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
