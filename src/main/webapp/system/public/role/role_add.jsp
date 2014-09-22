<%@ page language="java"
	import="java.util.*, net.rytong.admin.utils.*, net.rytong.entity.*"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
List<FunctionRight> allFunction = (List<FunctionRight>)request.getAttribute("functionList");
Map<Long,List<FunctionRight>> functionMap = (Map<Long,List<FunctionRight>>)request.getAttribute("childFunctionMap");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'role_add.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_role.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
			//alert($("div #show > div:first > div:first span").attr("id"))
				//alert($("div #show > div:first").attr("id"))
				//var happy = '#' + $("span.has_Children").attr("id")
				var pwgl = "#" + $("div #show > div:first > div:first span").attr("id");
				//alert(pwgl)
				//var pwgl = $("div #show > div:first > div:first span").attr("id");
				//alert($(pwgl).parent().attr("id"))
				/*
				$(pwgl).toggle(function(){
				$(pwgl).siblings().children().hide().show();},function(){
					$(pwgl).siblings().children().show();
				})
				*/
				//alert($("#show > div").html())
				
				//alert($("div #show > div > div :gt(0) ").html())
				$(pwgl).toggle(function(){
					$(pwgl).parent().siblings().show();},function(){
					$(pwgl).parent().siblings().hide();
				})
				/*
				$(pwgl).toggle(function(){
				$(pwgl).siblings().children().hide();},function(){
					$(pwgl).siblings().children().show();
				})
				*/
				//alert($(this).attr("id")); var name = '#' + $(this).attr("id"); alert(name);
				//alert(alert($("div #haha > div:last").attr("id")))
				var qxgl = "#" + $("div #show > div:eq(1) > div:first span").attr("id");
				$(qxgl).toggle(function(){
					$(qxgl).parent().siblings().show();},function(){
					$(qxgl).parent().siblings().hide();
				})
				var yxgl = "#" + $("div #show > div:eq(2) > div:first span").attr("id");
				$(yxgl).toggle(function(){
					$(yxgl).parent().siblings().show();},function(){
					$(yxgl).parent().siblings().hide();
				})
				var cpgl = "#" + $("div #show > div:eq(3) > div:first span").attr("id");
				$(cpgl).toggle(function(){
					$(cpgl).parent().siblings().show();},function(){
					$(cpgl).parent().siblings().hide();
				})
				var zzgl = "#" + $("div #show > div:last > div:first span").attr("id");
				$(zzgl).toggle(function(){
					$(zzgl).parent().siblings().show();},function(){
					$(zzgl).parent().siblings().hide();
				})
			})
		</script>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">权限管理-角色添加&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
		<div class="main">
			<s:form action="role_add" method="post" theme="simple"
				namespace="/system" onsubmit="return checkData()">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							角色名称
						</td>
						<td align="left" width="35%">
							<s:textfield id="name" name="name" onblur="checkName()"></s:textfield>
							<font color="red">*</font>
							<span id="result"></span>
						</td>
						<td class="th1">
							部门名称
						</td>
						<td align="left"  width="35%">
							<s:textfield id="departName" name="departName"></s:textfield>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="left">
						<div id="show">
							<% 
							 	if(allFunction != null && !allFunction.isEmpty()){
							 	for(FunctionRight functionRight : allFunction){ 
							 	%>
								<div style="border: 0px solid gray;margin-left: 20px;" id="moon_<%=functionRight.getFunctionRightId()%>">
									<div
										style="height: 20px;line-height:20px; width: 25px; vertical-align:top;  display: inline;">
										<input style="border: 0px;margin-top: 4px;" id="<%=functionRight.getFunctionRightId() %>"
											onclick="checkNode(this,'<%for(FunctionRight f : functionRight.getFunctionRights()){%><%=f.getFunctionRightId()%>,<%for(FunctionRight f1 : functionMap.get(f.getFunctionRightId())){ %><%=f1.getFunctionRightId()%>,<%} %><%} %>')"
											type="checkbox" name="functionIds"
											value="<%= functionRight.getFunctionRightId() %>">
											<span id="show1_<%=functionRight.getFunctionRightId() %>"><label style="font-weight: bold;"><%=functionRight.getName() %><font style="color: red">(点击显示/隐藏)</font></label></span>
									</div><!--<span id="show1_<%=functionRight.getFunctionRightId() %>"><label style="font-weight: bold;"><%=functionRight.getName() %>**点击显示/隐藏</label></span>-->
									<%for(FunctionRight functionRight1 : functionRight.getFunctionRights()){ %>
									<%if(functionRight1.getIsValid() == 1){ %>
									<div style="margin-left: 30px; margin-top: 0px; display:none;">
										<div
											style="height: 20px;line-height:20px; width: 25px; vertical-align:top;  display: inline">
											<input style="border: 0px;margin-top: 4px;" id="<%= functionRight1.getFunctionRightId() %>"
												onclick="checkNode1(this,'<%=functionRight.getFunctionRightId() %>,<%for(FunctionRight f : functionMap.get(functionRight1.getFunctionRightId())){ %><%=f.getFunctionRightId()%>,<%} %>','<%for(FunctionRight f : functionRight.getFunctionRights()){%><%=f.getFunctionRightId()%>,<%for(FunctionRight f1 : functionMap.get(f.getFunctionRightId())){ %><%=f1.getFunctionRightId()%>,<%} %><%} %>')"
												type="checkbox" name="functionIds"
												value="<%= functionRight1.getFunctionRightId() %>">
												<!--<span id="<%=functionRight.getFunctionRightId() %>_<%= functionRight1.getFunctionRightId() %>"></span>-->
												<font style="color: red"><%=functionRight1.getName() %></font>
										</div>
									</div>
									<%} %>
									<div style="margin-left: 50px; margin-top: 0px;border: 0px solid; display:none;">
										<%for(FunctionRight functionRight2 : functionMap.get(functionRight1.getFunctionRightId())){ %>
											<div
												style="height: 20px;line-height:20px; width: 150px; vertical-align:top; display: inline;margin-left: 10px;">
												<input style="border: 0px;margin-top: 4px;"   id="<%=functionRight2.getFunctionRightId() %>"
													onclick="checkNode2(this,'<%=functionRight2.getName() %>','<%=functionRight.getFunctionRightId() %>,<%=functionRight1.getFunctionRightId() %>,','<%=functionRight.getFunctionRightId() %>,<%for(FunctionRight f : functionMap.get(functionRight1.getFunctionRightId())){ %><%=f.getFunctionRightId()%>,<%} %>','<%for(FunctionRight f : functionRight.getFunctionRights()){%><%=f.getFunctionRightId()%>,<%for(FunctionRight f1 : functionMap.get(f.getFunctionRightId())){ %><%=f1.getFunctionRightId()%>,<%} %><%} %>')"
													type="checkbox" name="functionIds"
													value="<%= functionRight2.getFunctionRightId() %>">
													<!--<span  id="<%=functionRight.getFunctionRightId() %>_<%= functionRight1.getFunctionRightId() %>_<%= functionRight2.getFunctionRightId() %>"></span>-->
													<%=functionRight2.getName() %>
											</div>
										<%} %>
									</div>
								<% }%>
							</div>
							<% } } %>
						</div>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<s:submit value="" cssClass="add_btn" onclick="JavaScript:setSubmitButton(this);" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"></s:submit>
							<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'"
								onclick="JavaScript:window.history.back();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
