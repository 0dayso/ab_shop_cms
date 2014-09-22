<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String projectName = (String)request.getSession().getAttribute("projectName");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title><%=request.getSession().getAttribute("projectTitle")%></title>
		<link rel="icon"
			href="<%=request.getContextPath()%>/public/images/<%=request.getSession().getAttribute("logoIco")%>"
			type="image/x-icon" />
		<link rel="shortcut icon"
			href="<%=request.getContextPath()%>/public/images/<%=request.getSession().getAttribute("logoIco")%>"
			type="image/x-icon" />
		<meta http-equiv="X-UA-Compatible" content="IE=7">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="public/css/global.css" rel="stylesheet" type="text/css">
		<link href="public/css/hidden.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="public/js/jquery-1.4.js">
</script>
		<script type="text/javascript" src="public/js/hidden.js">
</script>
		<script>
if (self != top) {
	top.location = self.location;
}
var title = '${projectTitle}';
if (title == null || title == "") {
	window.self.location = '<%=request.getContextPath()%>';
}
function switchSysBar() {
	if (document.getElementById('frmTitle').style.display != 'none') {
		document.getElementById("frmTitle").style.display = "none"
		document.getElementById('imgss').src = '<%=request.getContextPath()%>/public/images/banner2.png';
	} else {
		document.getElementById("frmTitle").style.display = ""
		document.getElementById('imgss').src = '<%=request.getContextPath()%>/public/images/banner.png';
	}
}
function shows() {
	if (document.getElementById('none_left').style.display == 'block') { //判断目前左边栏的状态（显示、隐藏）
		document.getElementById('block_left').style.display = 'block'; //显示左边栏
		if (document.getElementById('block_left').style.display == 'block') {
			document.getElementById('none_left').style.display = 'none'
		}
	}
}
function change(obj, length) {
	for ( var i = 1; i <= length; i++) {
		if (obj == i) {
			if (isExist("topBtn" + i)) {
				document.getElementById("topBtn" + i).className = "topBtn1";
			}
			if (isExist("topFont" + i)) {
				document.getElementById("topFont" + i).className = "topFont1";
			}
		} else {
			if (isExist("topBtn" + i)) {
				document.getElementById("topBtn" + i).className = "topBtn";
			}
			if (isExist("topFont" + i)) {
				document.getElementById("topFont" + i).className = "topFont";
			}
		}
	}
}

function isExist(elementId) {
	var divEle = document.getElementById(elementId);
	if (divEle) {
		return true;
	} else {
		return false;
	}
}

function editSubmit(){
			var pwd1 = document.getElementById('pwd1').value;
		   	var oldPwd = document.getElementById('oldPwd').value;
		   	var empId = document.getElementById('empId').value;
		   	if(checkData()){
		   		$.get('<%=request.getContextPath()%>/system/user_editPass.action?random=' + new Date().getTime(),{oldPwd:oldPwd,pwd1:pwd1,empId:empId},
					function(data){
						if(data == 1){
							alert("对不起，输入旧密码错误！");
							document.getElementById('oldPwd').value ="";
							document.getElementById('oldPwd').focus();
						}if(data == 2){
							alert("修改成功！");
							document.getElementById("userEdit").style.display="none";
							document.getElementById("fade").style.display="none";
						}				
				});
			}
		   }
	    	function checkData(){
		    	var pwd1 = document.getElementById('pwd1').value;
		    	var pwd2 = document.getElementById('pwd2').value;
		    	if(pwd1.replace(/\s/g,'').length > 0 || pwd1.length > 0){
			    	if(pwd1.replace(/\s/g,'').length < 6 || pwd1.length < 6){
			   			alert("新密码长度不能少于6位！");
			   			return false;
			    	}
			    }else{
			    	alert("新密码不能为空!");
			    	return false;
			    }
		    	if(pwd1.replace(/\s/g,'').length != 0 || pwd1.length != 0){
		    		if(pwd2.replace(/\s/g,'').length == 0 || pwd2.length == 0){
		    			alert("新密码确认不能为空！");
		    			return false;
		    		}
		    	}
    	
		   		if(pwd1 != pwd2){
		   			alert("新密码和确认密码不一致，请重新输入！");
		   			return false;
		   		}
	   		return true;
	    }
	    	function link(){
	    		var id = document.getElementById('functionId').value;
	    		document.getElementById('carnoc').src = '<%=request.getContextPath()%>/system/left.jsp?t='+id;
	    	}
	</script>
	</head>
		<body onload="link()"
			style="overflow-y: hidden; overflow-x: hidden;MARGIN: 0px; background-color: #eefof1; width: 100%;">
			
			<table border="0" cellpadding="0" cellspacing="0" height="100%"
				width="100%" >
				<tr height="93px">
					<td>
						<table height="93px" border="0" cellspacing="0"
							cellpadding="0" width="100%" style="border-bottom:2px solid #699D08;">
							<tr>
								<td width="304">
									<img
										src="<%=request.getContextPath()%>/public/images/weixinmanlogo.png"/>
								</td>
								<td >
								<div style="width: 616px;border-right: 1px solid #DEDEDE;">
									<s:iterator value="#session.functionRightList" status="st">
										<a
											href="<%=request.getContextPath()%>/system/left.jsp?t=<s:property value='functionRightId'/>"
											target="carnoc" onclick="shows()"> <s:if test="level == 1">
												<s:if test="functionRight == null">
													<div class="topBtn"
														onclick="change('<s:property value="#st.count"/>','<s:property value='#session.functionRightList.size'/>')"
														id="topBtn<s:property value='#st.count'/>">
														<div class="topFont"
															id="topFont<s:property value='#st.count'/>">
															<s:property value="name" />
														</div>
														<input type="hidden" name="functionId" id="functionId" value="<s:property value="functionRightId" />"> 
													</div>
												</s:if>
											</s:if> </a>
									</s:iterator>
								</div>
								</td>
								<td valign="top" width="25%">
									<div
										style="height: 63px; width: 100%; float: right; margin-right: 20px; margin-top: 25px;">
										<div
											style="height: 30px; width: 100%; line-height: 30px;">
											当前时间:
											<label style="font-size: 12px;">
												<script type="text/javascript"
													src="<%=request.getContextPath()%>/public/js/time.js"></script>
											</label>
										</div>
										<div
											style="line-height: 30px; height: 30px; width: 100%; ">
											欢迎您:
											<a href="javascript:void(0)" onclick="show('userEdit')"
												title="修改密码" style="font-weight: bolder;">[${employee.name }]</a>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<a href="<%=request.getContextPath()%>/system/exit.action"
												title="退出">退出!</a>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td valign="top">
						<table border="0" cellPadding="0" cellSpacing="0" height="100%"
							width="100%" style=" background: url('../public/images/tuwen.png') no-repeat scroll 0 0 transparent;">
							<tr height="100%">
								<td align="left" " nowrap="nowrap" valign="top" id="frmTitle"
									height="100%" width="5%">
									<iframe frameBorder="0" id="carnoc" name="carnoc"
										scrolling="auto"
										src=""
										style="HEIGHT: 100%; VISIBILITY: inherit; Z-INDEX: 2; overflow-x: hidden; width: 210px; overflow-y: auto;">
									</iframe>
								</td>
								<td style="WIDTH: 6pt" height="100%" valign="top">
									<table border="0" cellPadding="0" cellSpacing="0" height="100%" >
										<tr>
											<td style="HEIGHT: 100%" onClick="switchSysBar();">
												<span id="switchPoint" title="关闭/打开左栏"><img
														id="imgss"
														src="<%=request.getContextPath()%>/public/images/banner.png">
												</span>
											</td>
										</tr>
									</table>
								</td>
								<td style="width: 100%" valign="top" height="100%">
									<table height="100%" border="0" width="100%">
										<tr>
											<td valign="top" height="100%">
													<iframe id="content2" name="content2"
													src="<%=request.getContextPath()%>/system/right.jsp"
													frameBorder="0" width="100%"
													style="height: 98%;"></iframe>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr height="20px">
					<td valign="top">
						<table align="center" border="0" cellpadding="0" cellspacing="0"
							height="20px" bgcolor="#e0e9ed" width="100%">
							<tr align="center" valign="top">
								<td align="center" valign="top">
									Copyright © <a href="http://www.rytong.net" target="_blank">北京融易通信息技术有限公司</a> All Rights Reserved.
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<div id="userEdit" class="white_content">
				<table border="1" width="100%" class="tableborder">
					<tr>
						<td class="title" colspan="4">
							个人密码修改
						</td>
					</tr>
					<tr>
						<td width="10%" class="th1" align="right">
							姓名:
						</td>
						<td width="15%" align="left">
							<s:textfield id="name" name="name" size="20"
								value="%{#session.employee.name}" disabled="true" />
						</td>
						<td width="10%" class="th1" align="right">
							登录名:
						</td>
						<td width="15%" align="left">
							<s:textfield id="login" name="login"
								value="%{#session.employee.login}" size="20" disabled="true" />
						</td>
					</tr>
					<tr>
						<td width="10%" class="th1" align="right">
							角色:
						</td>
						<td width="15%" align="left">
							<s:textfield id="role" name="role" size="20"
								value="%{#session.employee.role}" disabled="true" />
						</td>
						<td width="10%" class="th1" align="right">
							旧密码:
						</td>
						<td width="15%" align="left">
							<s:password id="oldPwd" name="oldPwd" size="20" />
						</td>
					</tr>
					<tr>
						<td width="10%" class="th1" align="right">
							新密码:
						</td>
						<td width="15%" align="left">
							<s:password id="pwd1" name="pwd1" size="20" />
						</td>
						<td width="10%" class="th1" align="right">
							新密码确认:
						</td>
						<td width="15%" align="left">
							<s:password id="pwd2" name="pwd2" size="20" />
							<s:hidden id="empId" name="empId" value="%{#session.employee.id}" />
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" class="modify_btn"
								onmousemove="this.className='modify_btn1'"
								onmouseout="this.className='modify_btn'" value=""
								onclick="editSubmit()">
							<input type="button" class="cancel_btn"
								onmousemove="this.className='cancel_btn1'"
								onmouseout="this.className='cancel_btn'" value=""
								onclick="hide('userEdit')">
						</td>
					</tr>
				</table>
			</div>
			<div id="fade" class=black_overlay></div>
		</body>
</html>