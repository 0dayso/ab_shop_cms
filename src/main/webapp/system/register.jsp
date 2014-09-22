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
	<link rel="icon" href="<%=request.getContextPath()%>/public/images/<%=request.getSession().getAttribute("logoIco")%>" type="image/x-icon" />
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/public/images/<%=request.getSession().getAttribute("logoIco")%>" type="image/x-icon" />
	<link href="<%=request.getContextPath()%>/public/css/reg.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/jquery-1.9.1.js"></script>
	<style type="text/css">
	</style>
	<title><%=request.getSession().getAttribute("projectTitle")%></title>
	<script type="text/javascript">
	
	<!--
		if (top.location != self.location) {
			top.location = self.location;
		}
		//更换验证码
		function changeVerifyCode() {
			//用<img>实现，则修改<img src=url>的url
			//这里有一个小技巧，如果给url赋相同的值，浏览器不会重新发出请求，因此用js生成一个即时毫秒数做url中的参数
			t = new Date().getTime();
			document.getElementById("verifyCodeImg").src = "verifyCodeAction?t=" + t;
		}
		$(function(){
		    $('#login').focus();
		});
       //绑定键盘事件
		function BindEnter(obj)
		{
		    var button = document.getElementById('login');
		    if(obj.keyCode == 13)
		        {
		            button.click();
		            obj.returnValue = false;
		        }
		}
       var Regexs = {  
    	   name:(/^[0-9a-zA-Z_-]{6,16}$/),//用户名
           email: (/^[0-9a-z][0-9a-z\-\_\.]+@([0-9a-z][0-9a-z\-]*\.)+[a-z]{2,}$/i),//邮箱  
           allphpne: (/^((13[0-9])|(15[0-9])|(18[0-9]))[0-9]{8}$/),//所有手机号码  
           url: (/^http:\/\/([0-9a-z][0-9a-z\-]*\.)+[a-z]{2,}(:\d+)?\/[0-9a-z%\-_\/\.]+/i),//网址  
           num: (/[^0-9]/),//数字  
           password:(/^\S{6-16}$/),
           cnum: (/[^0-9a-zA-Z_.-]/),  
       };  
       
       function chkChinese(s) {  
            for (var i = 0; i < s.length; i++) {  
                if (s.charCodeAt(i) > 255) return true;  
            }  
            return false;  
        };  
       
       function validate(id, ftype){
    	   var nReg = Regexs[ftype]; 
    	   var str = $("#"+id).val();
    	   if(ftype == 'password'){
    		   if(str.length >= 6 && str.length <= 16){
    			   $("#"+id).parent().find(".help-inline").css("color","");
    		   }else{
    			   $("#"+id).parent().find(".help-inline").css("color","red");
    		   }
    		   return ;
    	   }
           if (!nReg.test(str)) {  
        	   $("#"+id).parent().find(".help-inline").css("color","red");
           }else{
        	   $("#"+id).parent().find(".help-inline").css("color","");
           }
       };
       
       function validateClick(){
    	   var userName  = $("#login").val();
    	   if(userName  == ''){
    		   alert("用户名不能为空")
    		   return false;
    	   }else if(!Regexs['name'].test(userName)){
    		   alert("用户名输入有误,请重新输入");
    		   $("#login").val("");
    		   return false;
    	   }
    	   
    	   var phone = $("#phone").val();
    	   if(phone  == ''){
    		   alert("手机号码不能为空")
    		   return false;
    	   }else if(!Regexs['allphpne'].test(phone)){
    		   alert("手机号码输入有误,请重新输入");
    		   $("#phone").val("");
    		   return false;
    	   }
    	   
    	   var password = $("#password").val();
    	   if(password == ''){
    		   alert("请输入密码");
    		   return false;
    	   }
    	   var rePassword =  $("#repassword").val();
    	   if(rePassword == ''){
    		   alert("请输入确认密码");
    		   return false;
    	   }
    	   
    	   var password = $("#password").val();
    	   var rePassword = $("#repassword").val();
    	   if(password != rePassword){
    		   alert("两次输入的密码不一致，请重新输入");
    		   $("#repassword").val("");
    		   return false;
    	   }
    	   
    	   var email = $("#email").val();
    	   if(email == ''){
    		   alert("请输入邮箱地址");
    		   return false;
    	   }else if(!Regexs['email'].test(email)){
    		   alert("请输入正确的邮箱地址")
    		   $("#email").val("");
    		   return false;
    	   }
    	   
    	  $("#regform").submit();
       }
       
       
      </script>
</head>
<body onload="BindEnter(event)">
    <div class="loginTab" style="width:100%; height:auto;">
        <div class="mainbody" style="background-color: #FFFFFF;">
		  <article class="top">
		    <div class="clearfix" id="hd">
		      <div class="logo"><a onfocus="this.blur();" hidefocus="true" href="/"></a></div>
		    </div>
		  </article>
		  
		  <div class="reg-wrapper2">
		    <form action="register.action" class="form-horizontal" id="regform" method="post" namespace="/system">
		      <div class="control-group">
		        <label for="login" class="control-label">用户名</label>
		        <div class="controls">
		          <input type="text" id="login" name="login" oninput="validate('login','name')" >
		          <span class="maroon">*</span>
		          <span class="help-inline">长度为6~16位字符，可以为“数字/字母/中划线/下划线”组成</span>
		       </div>
		      </div>
		      <div class="control-group">
		        <label for="name" class="control-label">姓&nbsp;&nbsp;名</label>
		        <div class="controls">
		          <input type="text" id="name" name="name" value="">
		        </div>
		      </div>
		      <div class="control-group">
		        <label for="password" class="control-label">设置密码</label>
		        <div class="controls">
		          <input type="password" id="password" name="password" value="" oninput="validate('password','password')">
		          <span class="maroon">*</span><span class="help-inline">长度为6~16位字符</span></div>
		      </div>
		      <div class="control-group">
		        <label for="repassword" class="control-label">确认密码</label>
		        <div class="controls">
		          <input type="password" id="repassword" name="repassword" oninput="validate('repassword','password')">
		          <span class="maroon">*</span>
		          <span class="help-inline">长度为6~16位字符</span>
		        </div>
		      </div>
		      <div class="control-group">
		        <label for="address" class="control-label">详细地址</label>
		        <div class="controls">
		          <input type="text" id="address"  name="address">
		          <span class="maroon">*</span>
		        </div>
		      </div>
		      <div class="control-group">
		        <label for="phone" class="control-label">手机</label>
		        <div class="controls">
		          <input type="text" id="phone" name="phone" oninput="validate('phone','allphpne')">
		          <span class="maroon">*</span><span class="help-inline">请输入正确的手机号码</span> </div>
		      </div>
		      <div class="control-group">
		        <label for="email" class="control-label">邮箱</label>
		        <div class="controls">
		          <input type="text"  id="email" name="email" oninput="validate('email','email')">
		          <span class="maroon">*</span><span class="help-inline">邮箱将与支付及优惠相关，请填写正确的邮箱</span> </div>
		      </div>
		      <div class="control-group">
		        <label for="randcode" class="control-label">验证码</label>
		        <div class="controls">
		          <input type="text" maxlength="4" id="captcha" name="captcha">
		          <span class="maroon">*</span> <span id="show_captcha" name="show_captcha"> <img id="verifyCodeImg"
																	src="<%=request.getContextPath()%>/img/verifyCode"
																	style="cursor: hand; margin-top: 5px;" align="top"
																	onclick="this.src = this.src + '?1'" /></div>
		      </div>
		      <div class="control-group">
		        <div class="controls">
		          <button class="btn_register" id="reg-btn" type="button" onclick="validateClick();"></button>
		        </div>
		      </div>
		    </form>
		  </div>
		</div>
    </div>
</body>
</html>
