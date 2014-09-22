<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath() %>/public/css/jquery.ui.all.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.9.1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.core.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.widget.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ui/jquery.ui.tabs.js"></script>
		<script type="text/javascript">
			/* 判断元素值是否为空 element:element in the page message:error message */
			function isNotEmpty(element, message) {
				if (element.value.length == 0
						|| element.value.replace(/\s/g, '').length == 0) {
					alert(message);
					element.focus();
					return false;
				}
				return true;
			}		
			function validate() {
				if (!isNotEmpty(document.getElementById('name'), "菜单名称不能为空！")) {
					return false;
				}
				if (!isNotEmpty(document.getElementById('buttonNo'), "菜单序列不能为空！")) {
					return false;
				}
				if (document.getElementById('type').value!='') {
					//if (document.getElementById('keyId').value=='') {
					//	alert("若为点击事件，则事件KEY值必须有值！");
					//	return false;
					//}
				}				
				return true;
			}
	
			$(function(){
				$.get("<%=request.getContextPath()%>/system/customer_init.action?time="+new Date().getTime(),{name:'CZ'},
					function(data){
						$("#customerList").html(data);
					});
				$.get("<%=request.getContextPath()%>/system/weixinMenu_lastMenu.action?time="+new Date().getTime(),{},
						function(data){
							$("#lastMenuList").html(data);
						});
				$.get("<%=request.getContextPath()%>/system/event_init.action?time="+new Date().getTime(),{},
						function(data){
							$("#eventList").html(data);
						});
				$.get("<%=request.getContextPath()%>/system/weixinType_init.action?time="+new Date().getTime(),{isShowAll:0},
					function(data){
						$("#weixinTypeList").html(data);
						/*$('#reType').bind('click', function() {
							var typeValue = $("#reType").val();
							if ("news" == typeValue) {
								$("#text_flag").css("display", "none");
								$("tr[name='news_flag']").each(function() {
									$(this).css("display", "");
						        });
							} else if ("text" == typeValue) {
								$("#text_flag").css("display", "");
								$("tr[name='news_flag']").each(function() {
									$(this).css("display", "none");
						        });
							}
						});*/
					});
				$( "#tabs" ).tabs();
			});
		</script>
		<title>增加微信菜单</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微信菜单管理-添加微信菜单&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form action="weixinMenu_add" method="post" theme="simple" namespace="/system" onsubmit="return validate()">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">
						菜单类型:
					</td>
					<td align="left">
						<s:select id="level" name="level" list="#{1: '主菜单', 2: '子菜单'}"></s:select>
					</td>
					<td class="th1">上级菜单:</td>
					<td width="35%" align="left"><span id="lastMenuList"></span></td>
				</tr>
				<tr>
					<td class="th1">
						菜单名称:
					</td>			
					<td width="35%" align="left">
						<s:textfield id="name" name="name" ></s:textfield>
					</td>
					<td class="th1">
						事件类型:
					</td>
					<td width="35%" align="left"><span id="eventList"></span></td>
				</tr>
				</tr>
				
				<tr>
					<td class="th1">
						是否启用:
					</td>
					<td width="35%" align="left" >
						<s:select id="state" name="state" list="#{0: '启用', 1: '关闭'}"></s:select>
					</td>
					<td class="th1">商户选择:</td>
					<td width="35%" align="left"><span id="customerList"></span></td>
				</tr>
				<tr>
					<td class="th1">
						回复格式:
					</td>
					<td width="35%" align="left"><span id="weixinTypeList"></span></td>
					<td class="th1"></td>
					<td align="left"></td>
				</tr>
			</table>
			<div id="tabs">
				<ul>
					<li><a href="#tabs-1">文字</a></li>
					<li><a href="#tabs-2">图片</a></li>
					<li><a href="#tabs-3">语音</a></li>
					<li><a href="#tabs-4">视频</a></li>
					<li><a href="#tabs-5">图文消息</a></li>
				</ul>
				<div id="tabs-1">
					回复内容:<s:textarea id="reTextContent" name="reTextContent" cols="87" rows="5"></s:textarea>
				</div>
				<div id="tabs-2">
					
				</div>
				<div id="tabs-3">
					<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
					<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
				</div>
				<div id="tabs-4">
					<p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
					<p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
				</div>
				<div id="tabs-5">
					回复标题:<s:textarea id="reNewsTitle" name="reNewsTitle" cols="87" rows="5"></s:textarea>
					回复内容:<s:textarea id="reNewsContent" name="reNewsContent"  cols="87" rows="5" ></s:textarea>
				</div>
			</div>
			<div style="text-align: center;">
				<span>
					<s:submit value="" cssClass="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"></s:submit>
					<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();">
				</span>
			</div>
		</s:form>
		</div>
	</body>
</html>