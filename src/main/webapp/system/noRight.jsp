<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误信息页面</title>
    <link href="<%=request.getContextPath()%>/css/ys.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.errorInfo{
			margin: 100 auto;
			text-align: center; 
			font: bold 18px 宋体;
			color: #0066CC;
			vertical-align: middle
		}
		a{
			text-decoration: none;
			font: bold 16px 宋体;
		}
		a:hover,a:active,a:visited{
			color: blue;
		}	
	</style>
  </head>
  
  <body background="<%=request.getContextPath()%>/public/images/bg1.jpg">
    <table id="welcome_body" width="100%">
		<tr height="120px">
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr height="220px">
		<td width="80px">&nbsp;</td>
			<td valign="middle" align="center">
				<label class="errorInfo">
					对不起，您没有此权限！
		        </label><br/>
		        <a href="javascript:history.go(-1)" >返回</a> 
			</td>
		</tr>
	</table>
  </body>
</html>
