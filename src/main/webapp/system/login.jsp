<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	request.setAttribute("projectTitle",request.getAttribute("projectTitle"));
	request.setAttribute("loginPng", request.getAttribute("loginPng"));
	request.setAttribute("logoPng", request.getAttribute("logoPng"));
	request.setAttribute("logoIco", request.getAttribute("logoIco"));

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
	<head>
		<meta name="Keywords" content="Travel, Flight, Airline" />
		<meta http-equiv="windows-target" content="_top" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="<%=request.getContextPath()%>/public/css/global.css"
			rel="stylesheet" type="text/css">
		<link rel="icon"
			href="<%=request.getContextPath()%>/public/images/<%=request.getSession().getAttribute("logoIco")%>"
			type="image/x-icon" />
		<link rel="shortcut icon"
			href="<%=request.getContextPath()%>/public/images/<%=request.getSession().getAttribute("logoIco")%>"
			type="image/x-icon" />
		<style type="text/css">
			.first {
				width: 100px;
			}
		</style>
		<title><%=request.getSession().getAttribute("projectTitle")%></title>
<script type="text/javascript">
<!--
	if (top.location != self.location) {
		top.location = self.location;
	}
	function checklogin() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var verifyCode = document.getElementById("verifyCode").value;
		if (username == '') {
			alert("请输入用户名!");
			document.getElementById("username").focus();
			return false;
		}
		if (password == '') {
			alert("请输入密码!");
			document.getElementById("password").focus();
			return false;
		}
	
		if (verifyCode == '') {
			alert("请输入验证码!");
			document.getElementById("verifyCode").focus();
			return false;
		}
	 
	}
	function checklogins() {
		var username = document.getElementById("username").value;
		var password = document.getElementById("password").value;
		var verifyCode = document.getElementById("verifyCode").value;
		if (username == '') {
			alert("请输入用户名!");
			document.getElementById("username").focus();
			return false;
		}
		if (password == '') {
			alert("请输入密码!");
			document.getElementById("password").focus();
			return false;
		}
	
		if (verifyCode == '') {
			alert("请输入验证码!");
			document.getElementById("verifyCode").focus();
			return false;
		}
	}
	//更换验证码
	function changeVerifyCode() {
	
		//用<img>实现，则修改<img src=url>的url
		//这里有一个小技巧，如果给url赋相同的值，浏览器不会重新发出请求，因此用js生成一个即时毫秒数做url中的参数
		t = new Date().getTime();
		document.getElementById("verifyCodeImg").src = "verifyCodeAction?t=" + t;
	}
	//给登陆按钮绑定回车
	/*if (document.addEventListener) {
		document.addEventListener("keypress", fireFoxHandler, true);
	} else {
		document.attachEvent("onkeypress", ieHandler);
	}
	function fireFoxHandler(evt) {
		if (evt.keyCode == 13) {
			document.getElementById("login").onclick();
		}
	}
	function ieHandler(evt) {
		if (evt.keyCode == 13) {
			document.getElementById("login").onclick();
		}
	}*/
	function BindEnter(obj)
	{
	    //使用document.getElementById获取到按钮对象
	    var button = document.getElementById('login');
	    if(obj.keyCode == 13)
	        {
	            button.click();
	            obj.returnValue = false;
	        }
	}
	function register(){
		window.location.href="<%=basePath %>system/register.jsp";
	}
	
--></script>
	</head>
	<body onload="BindEnter(event)">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			class="loginTab">
			<tr height="40%"></tr>
			<tr height="30%">
				<td>
					<table width="100%">
						<tr>
							<td width="30%"></td>
							<td align="center" width="40%">
								<div class="LoginMid" style="margin-top: 130px; width: 745px;">
									<div class="login_top">
									</div>
									<div
										style="height: 353px; padding-right: 30px; position: relative;">
										<%--											<div class="login_center1"></div>--%>
										<div class="login_center2">
											<div
												style="height: 160px; margin-top: 10px; float: left; margin-left: 25px;">
												<s:form name="userLogin" action="login" method="post"
													namespace="/system" onsubmit="return checklogins()">
													<table width="265" border="0" align="center"
														cellpadding="0" cellspacing="0">
														<tr>
															<td class="first" height="30" align="right">
																用户名：
															</td>
															<td height="30" align="left">
																<input name="userName" type="text" id="username"
																	size="20" />
															</td>
														</tr>
														<tr>
															<td class="first" height="30" align="right">
																密&nbsp;&nbsp;码：
															</td>
															<td height="30" align="left">
																<input name="passWord" type="password" id="password" />
															</td>
														</tr>
														<tr>
															<td class="first" height="30" align="right">
																验证码：
															</td>
															<td height="30" align="left">
																<input name="verifyCode" id="verifyCode" type="text"
																	size="4" maxlength="4" style="width: 93px;">
																&nbsp;
																<img id="verifyCodeImg"
																	src="<%=request.getContextPath()%>/img/verifyCode"
																	style="cursor: hand; margin-top: 5px;" align="top"
																	onclick="this.src = this.src + '?1'" />
															</td>
														</tr>
														<tr>
															<td colspan="2" align="center">
																<font color="red"><s:fielderror />
																</font>
															</td>
														</tr>
														<tr>
															<td colspan="2" align="center">
																<input type="submit"
																	style="background: url('<%=request.getContextPath()%>/public/images/loginButton.png'); background-repeat: no-repeat; width:100px;margin-top:10px; margin-left:40px;height: 33px;border: 0; cursor:hand;"
																	value="">
																<input type="button" onclick="register()"
																	style="background: url('<%=request.getContextPath()%>/public/images/regButton.png'); background-repeat: no-repeat; width:100px; margin-top:10px; height: 33px;border: 0; cursor:hand;"
																	value="">
															</td>
														</tr>
													</table>
												</s:form>
											</div>
										</div>
									</div>
									<div class="login_bto"></div>
								</div>
							</td>
							<td width="30%"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr height="40%"></tr>
		</table>
	</body>
</html>
