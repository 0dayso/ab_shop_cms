<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="net.rytong.entity.Customer" %>
<%
     Customer customer  = (Customer)request.getSession().getAttribute("customer");
     String id = "";
     String url = "";
     String token = "";
     String appId = "";
     String secret = "";
     
     String yxUrl = "";
     String yxToken = "";
     String yxAppId = "";
     String yxSecret = "";
     
     if(customer != null){
    	 id = customer.getId().toString();
    	 url = customer.getUrl();
    	 token = customer.getToken();
    	 appId = customer.getAppId();
    	 secret = customer.getSecret();
    	 
    	 yxUrl = customer.getYxUrl();
    	 yxToken = customer.getYxToken();
    	 yxAppId = customer.getYxAppId();
    	 yxSecret = customer.getYxSecret();
     }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_parameter.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/dateTime.js"></script>
		<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
		
		<script type="text/javascript">
		     function saveInfo(){
		    	 var id = $("#weixin_id").val();
		    	 var url = $("#weixin_url").val();
		    	 var token = $("#weixin_token").val();
		    	 var appId = $("#weixin_appId").val();
		    	 var secret = $("#weixin_secret").val();

		    	 var url1 = $("#yixin_url").val();
		    	 var token1 = $("#yixin_token").val();
		    	 var appId1 = $("#yixin_appId").val();
		    	 var secret1 = $("#yixin_secret").val();
		    	 
		    	 $.ajax({
		    		url:"<%=request.getContextPath()%>/system/customer_savePort.action",
		    		dataType:"json",
		    		type:"post",
		    		data:{"id":id, "url":url, "token":token, "appId":appId, "secret":secret,"yxUrl":url1, "yxToken":token1, "yxAppId":appId1, "yxSecret":secret1},
		    		success:function(data){
		    			if(data.code == 'ok'){
		    		    	$(".picurl").tip({width:'240',status:'right',content:'编辑成功',dis_time:1000});
		    		    }else{
		    		    	$(".picurl").tip({width:'240',status:'error',content:'编辑失败',dis_time:1000});
		    		    }
		    		}
		    	 });
		     }
		     $(function(){
		    	  $("#weixin_id").val('<%=id %>')
		    	  $("#weixin_url").val('<%=url %>');
		    	  $("#weixin_token").val('<%=token %>');
		    	  $("#weixin_appId").val('<%=appId %>');
		    	  $("#weixin_secret").val('<%=secret %>');

		    	  $("#yixin_url").val('<%=yxUrl %>');
		    	  $("#yixin_token").val('<%=yxToken %>');
		    	  $("#yixin_appId").val('<%=yxAppId %>');
		    	  $("#yixin_secret").val('<%=yxSecret %>');
		     });
		</script>
	</head>
	<body>
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">接口设置</font></span>
    	</div>
    	<div class="main">
			<form name="aspnetForm" method="post" action="" id="aspnetForm">
				<table style="line-height: 30px; border: 1px solid #ccc; padding: 10px; background: #f4f4f4; width: 100%;">
					<tr>
						<td style="font-size: 14px; font-weight: bold;">微信接口URL</td>
					</tr>
					<tr>
						<td>
							<input name="weixin_url" type="text" value="" id="weixin_url" class="wx-input-30" style="width: 500px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">微信接口Token</td>
					</tr>
					<tr>
						<td>
							<input name="weixin_token" type="text" value="" id="weixin_token" class="wx-input-30" style="width: 500px;" />
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">微信appId</td>
					</tr>
					<tr>
						<td>
							<input name="weixin_appId" type="text" value="" id="weixin_appId" class="wx-input-30" style="width: 500px;" />
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">微信secret</td>
					</tr>
					<tr>
						<td>
							<input name="weixin_secret" type="text" value="" id="weixin_secret" class="wx-input-30" style="width: 500px;" />
						</td>
					</tr>
				</table>
				
				<table style="line-height: 30px; border: 1px solid #ccc; padding: 10px; background: #f4f4f4; width: 100%;">
					<tr>
						<td style="font-size: 14px; font-weight: bold;">易信接口URL</td>
					</tr>
					<tr>
						<td>
							<input name="weixin_url" type="text" value="" id="yixin_url" class="wx-input-30" style="width: 500px;" disabled="disabled"/>
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">易信接口Token</td>
					</tr>
					<tr>
						<td>
							<input name="weixin_token" type="text" value="" id="yixin_token" class="wx-input-30" style="width: 500px;" />
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">易信appId</td>
					</tr>
					<tr>
						<td>
							<input name="weixin_appId" type="text" value="" id="yixin_appId" class="wx-input-30" style="width: 500px;" />
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">易信secret</td>
					</tr>
					<tr>
						<td>
							<input name="weixin_secret" type="text" value="" id="yixin_secret" class="wx-input-30" style="width: 500px;" />
						</td>
					</tr>
				</table>
				<table style="line-height: 30px; border: 1px solid #ccc; padding: 10px; background: #f4f4f4; width: 100%;">
					<tr>
						<td>
							<input type="button" id="save" name="save" value="保存配置" id="ctl00_ContentPlaceHolder1_btnOK" class="btn" onclick="saveInfo();"/>
						</td>
					</tr>
				</table>
				<input type="hidden" id="weixin_id" name="weixin_id">
			</form>
		</div>
		<div style="opacity:0;  filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px; display:none;">
       		<input type="file" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" class="picurl" name="mp3file" id="urlt_second_edt">
       	</div>
	</body>
</html>
