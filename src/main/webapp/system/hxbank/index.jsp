<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
	
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>华夏银行</title>
	<meta name="viewport" content="width=device-width; initial-scale=1.0; minimum-scale=1.0; maximum-scale=1.0"/>
	<meta content="telephone=no" name="format-detection" />
	<link rel="stylesheet" href="./system/hxbank/main.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<script type="text/javascript">
		function submitForm(){
           var phone=/^1[3|4|5|8][0-9]\d{8}$/;
           if($('#idNo').val()==""){
               alert("证件号不能为空！");
               return false;
           }else if($('#telNo').val()==""){
               alert("手机号码不能为空！");
               return false;
           }else if(!phone.test($('#telNo').val())){
               alert("请正确填写手机号码!");
               return false;
           }else if($('#pass').val()==""){
               alert("手机银行密码不能为空！");
               return false;
           }else if($('#rePass').val()==""){
               alert("重复银行密码不能为空！");
               return false;
           }else if($('#pass').val() != $('#rePass').val()){
               alert("两次密码输入不一致!");
               return false;
           }else if($('#sms').val()==""){
               alert("短信动态密码不能为空！");
               return false;
           }
	       if (confirm("是否将您的卡号与微信绑定?")) {
		   	   $("#form").submit();
		   }
           
		}
		
		
	</script>
</head>
<body>
	<header class="header">
	<div class="imgCent">
	登        录
	</div>
	<img alt="logo" src="./system/hxbank/images/logo.png" width="100" height="30" class="logo"/>
	</header>
	<form action="/manage/bangding" method="post" id="form">
	<div class="body_cont">
		<ul>
			<li class="li_left">证件类型：</li>
			<li class="selectdiv">
				<select name="certType">
					<option value="0">居民身份证</option>
					<option value="1">护照</option>
					<option value="2">其他</option>
			    </select>
			</li>
			<img class="arr" alt="zhishi" src="./system/hxbank/images/arr.png">
		</ul>
		<ul>
			<li class="li_left">证件号码：</li>
			<li><input type="text" value="" id="idNo" name="certType" placeholder="请输入您的证件号" maxlength="18"/></li>
		</ul>
		<ul>
			<li class="li_left">手机号码：</li>
			<li><input type="tel" value="" id="telNo" name="certNo" placeholder="请输入注册手机号码" maxlength="11"/></li>
		</ul>
		<ul>
			<li class="li_left">手机银行密码：</li>
			<li><input type="password" value="" id="pass" name="password" placeholder="8-12为任意数字、字母" maxlength="6"/></li>
		</ul>
		<ul>
			<li class="li_left">手机银行密码：</li>
			<li><input type="password" value="" id="rePass" placeholder="再次输入手机银行密码" maxlength="6"/></li>
		</ul>
		<ul>
			<li class="li_left">预留信息设置：</li>
			<li><input type="text" value="" id="mess" name="messge" placeholder="可任意输入、详见页…" maxlength="20"/></li>
		</ul>
		<ul>
			<li class="li_left">短信动态密码：</li>
			<li><input type="password" value="" id="sms" name="sms" placeholder="" maxlength="6"/><div class="getPas">获取</div></li>
		</ul>
		<input type="hidden" name="weixinNo" value="<%=request.getAttribute("weixinNo") %>"/>
	</div>
	<input type="hidden" name="bdTrue" value="true"/>
	<button class="button" type="button" name="Submit" onclick="submitForm()">确认绑定</button>
	</form>	
</body>
</html>
