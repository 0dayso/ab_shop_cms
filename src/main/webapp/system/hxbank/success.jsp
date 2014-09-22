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
</head>
<body>
	<header class="header">
	<div class="imgCent">
	登        录
	</div>
	<img alt="logo" src="./system/hxbank/images/logo.png" width="100" height="30" class="logo"/>
	</header>
	<div class="body_cont_suc">
		<img src="./system/hxbank/images/right.png" alt="suc" class="img_suc" width="68" height="68"/>
		<div class="not_suc">您的卡号与微信绑定成功!</div>
	</div>
	<button class="button_yellow" type="button" name="Submit" onclick="history.go(-2);">进入我的主页</button>
</body>
</html>
