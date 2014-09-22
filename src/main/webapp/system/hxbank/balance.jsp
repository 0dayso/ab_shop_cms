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
	余 额 查 询
	</div>
	<img alt="logo" src="./system/hxbank/images/logo.png" width="100" height="30" class="logo"/>
	</header>
	<div class="body_cont_info">
		<ul>
			<li class="li_left">客户姓名：</li>
			<li class="li_right_info">华夏</li>
		</ul>
		<ul>
			<li class="li_left">账号：</li>
			<li class="li_right_info">10224865965237</li>
		</ul>
		<ul>
			<li class="li_left">所属卡号：</li>
			<li class="li_right_info">62235656665565666</li>
		</ul>
		<ul>
			<li class="li_left">开户机构：</li>
			<li class="li_right_info">北京分行北沙滩支行</li>
		</ul>
		<ul>
			<li class="li_left">开户日期：</li>
			<li class="li_right_info">2013-05-06</li>
		</ul>
		<ul>
			<li class="li_left">储种：</li>
			<li class="li_right_info">个人人民币活期普通结算账号</li>
		</ul>
		<ul>
			<li class="li_left">利率：</li>
			<li class="li_right_info">0.6%</li>
		</ul>
		<ul>
			<li class="li_left">币种：</li>
			<li class="li_right_info">人民币</li>
		</ul>
		<ul>
			<li class="li_left">状态：</li>
			<li class="li_right_info">正常</li>
		</ul>
		<ul>
			<li class="li_left">余额：</li>
			<li class="li_right_info">166.905.00</li>
		</ul>
	</div>
</body>
</html>
