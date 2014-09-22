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
	<link rel="stylesheet" href="./main.css" />
	<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	
</head>
<body>
	<header class="header">
	<div class="imgCent">
	余额查询
	</div>
	<img alt="logo" src="./images/logo.png" width="100" height="30" class="logo"/>
	</header>
	<div class="body_cont body_cont_1">
		<div class="body_title"><span class="title_1">商旅卡</span>  <span class="title_1">卡号：62263001211699</span></div>
		<div class="body_sec"><span class="title_2">余额：166,907.00</span></div>
		<div class="body_end">
		   <div class="body_end_1 line_body">
			   <div>账户号1:10520000000777256</div>
			   <div>账户类型：个人人民币活期普通结算账户</div>
			   <div>余额：166,907.00</div>
			   <img class="arr1" alt="zhishi" src="./images/arr.png">
		   </div>
		   <div class="body_end_1">
		   	   <div>账户号2:10520000000777256</div>
			   <div>账户类型：个人人民币活期普通结算账户</div>
			   <div>余额：166,907.00</div>
			   <img class="arr1" alt="zhishi" src="./images/arr.png">
		   </div>
		</div >
	</div>
</body>
</html>
