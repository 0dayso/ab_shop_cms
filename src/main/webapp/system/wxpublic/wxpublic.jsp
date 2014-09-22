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
		<link href="./system/wxpublic/1_files/auto_width_style.css" rel="stylesheet" type="text/css"><style>.intro{text-indent: 0 !important;}</style>
	</head>
	
	<body>
		
		<s:if test="#request.conferenceList != null && #request.conferenceList.size()>0">
			<s:iterator value="#request.conferenceList" var="conference">
				<div class="main">
		<!--banner区域-->
		<div class="banner">
			<img src="<s:property value='#conference.logoUrl'/>" width="640" height="130"></div>
		<!--导语部分-->
		<div class="intro">“<s:property value='#conference.content'/>”</div>
					<s:iterator value="#request.tops" var="tops">
						<s:if test="#tops != null && tops.size()>0">
						<h2><s:property value="#tops.name"/></h2>
							<s:iterator value="#tops.subclauess" var="sub">
									<ul class="list" onclick="window.open('<s:property value='#sub.linkUrl'/>')" >
										<li class="clearfix">
											<a href="<s:property value='#sub.linkUrl'/>">
											<div class="pic">
													<img src="<s:property value='#sub.logoUrl'/>" width="75" height="55">
											</div>
											<div class="content">
												<h3>
													<a href="<s:property value='#sub.linkUrl'/>"><s:property value='#sub.title'/></a>
												</h3>
												<div class="txt">
													<a href="<s:property value='#sub.linkUrl'/>"><s:property value='#sub.content'/></a>
												</div>
											</div>
											</a>
										</li>
										
									</ul>
								
							</s:iterator>
						</s:if>
					</s:iterator>
			</s:iterator>
		</s:if>
		
	</div>
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
