<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String title = (String)request.getAttribute("title");
%>
<!DOCTYPE html>
<html>
	<head>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=GBK">
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width; initial-scale=1.0; user-scalable=no">
		<meta name="format-detection" content="telephone=no">
		<title><%=title%></title>
		<style type="text/css">
		    body { font-family: Helvetica, "SimSun"; padding:0; margin:0; min-width:320px; }
		    .word_d { 
				font-size:15px;
				color:#005876; 
				text-indent:10px; 
				margin: 0px; 
				font-size:16px; 
		    }
		    .cont{font-size:15px; color:#999; padding:5px; text-align: justify; word-break:break-all;position:relative; line-height:150%}
		    .sub_title{
		        background-color: #ECECEC;
			    border-bottom: 1px solid #D1D1D1;
			    position: relative;
			    display:table;
			    clear:both;
			    width:100%;
		    }
		    h2{
		        color: #212121;
		        margin: 10px 15px 3px;
			    font-size: 21px;
			    text-align: center;
		    }
		    .banner img { min-width:100%; width:320px; }
		    .main { margin-left:auto; margin-right: auto;}
		</style>
	</head>
	<body>
	<s:if test="#request.state == 'fail'">
	    <div>
	       <h2>抱歉，没有相关信息</h2>
	    </div>
	</s:if>
	<s:else>
	    <div class="main">
	         <div class="sub_title">
		         <h2>
		            <span class="word_d"><s:property value="#request.subclaues.title"/></span>
		         </h2>
	        </div>
			<!--banner区域-->
			<div class="banner">
				<img src="<s:property value='#request.subclaues.logoUrl'/>" height=130/>
			</div>
			<!-- 横线 -->
			<div style="margin:0;padding:0; width:100%;height:1px;background-color:#ECECEC;"></div>
			<!--详情-->
			<div class="cont">
			   <p style="text-indent:2em;">
			       <s:if test='#request.subclaues.describe != ""'>
			       
			       	   <s:if test="#request.subclaues.describe.startsWith(\"http\")">
			       	   		<a href="<s:property value='#request.subclaues.describe'/>" alt="">打开详细</a>
			       	   </s:if> <s:else>
			       	   		 <s:property value='#request.subclaues.describe'/>
			       	   </s:else>
			       </s:if>
			       <s:else>
			                               没有相关描述
			       </s:else>
			   </p>
			</div>
			<div class="txt" style="float: right;">
				<s:if test="#request.subclaues.code == 0">
					<img width="21" height="21" align="absmiddle" src="<%=request.getContextPath()%>/system/wxpublic/1_files/zan1.jpg">已赞过
				</s:if><s:else>
					<a href="<s:property value='#request.subclaues.content'/>"><img width="21" height="21" align="absmiddle" src="<%=request.getContextPath()%>/system/wxpublic/1_files/zan.jpg"></a>赞一个
				</s:else>
				（<s:property value='#request.subclaues.zan'/>）
			</div>
		</div>
	</s:else>
	
	<script>
		document.addEventListener('WeixinJSBridgeReady', function(){
		    var imgUrl = 'http://1.202.131.149/images/logo.png';
		    var desc  = '海南航空';
		    var title = '<%=title%>';
		    // 发送给好友; 
		    WeixinJSBridge.on('menu:share:appmessage', function(argv){
		        
		        WeixinJSBridge.invoke('sendAppMessage',{
		                              "appid"      : 'wxe0b066a8b8c86791',
		                              "img_url"    : imgUrl,
		                              "img_width"  : "640",
		                              "img_height" : "640",
		                              "desc"       :  '海南航空',
		                              "title"      : '<%=title%>',
		                              "link"       : location.href
		                    }, function(res) {
		                    });
		                });
			    // 分享到朋友圈;
			    WeixinJSBridge.on('menu:share:timeline', function(argv){
			
			                    WeixinJSBridge.invoke('shareTimeline',{
			                                          "img_url"    : imgUrl,
			                                          "img_width"  : "640",
			                                          "img_height" : "640",
			                                          "desc"       : '海南航空',
			                                          "title"      : '<%=title%>',
			                                          "link"       : location.href
			                    }, function(res) {
			                    });
			        
			                });
			
			
			}, false);
		</script>
	</body>
</html>
