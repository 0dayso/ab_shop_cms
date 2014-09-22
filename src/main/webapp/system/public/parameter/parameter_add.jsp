<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/public/check_parameter.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
	<script type="text/javascript">
			function check() {
				var code = document.getElementById("code").value.toUpperCase();
				var name = document.getElementById("name").value;
				var value = document.getElementById("value").value;
				var startTime = document.getElementById("effectiveDate").value;
				var endTime = document.getElementById("effectiveDate").value;
				var myRegExp = /^(10|[0-9])$/;
				var str = $("#str").text();
				if (code != '' && code != null)
					code = code.trim();
				if (name != '' && name != null)
					name = name.trim();
				if (value != '' && value != null)
					value = value.trim();
				if (startTime != '' && startTime != null)
					startTime = startTime.trim();
				if (endTime != '' && endTime != null)
					endTime = endTime.trim();
			
				if (code.length == 0 || code.length > 20) {
					alert("请填写参数类别代码且长度不能超过20！");
					document.getElementById("code").value = "";
					document.getElementById("code").focus();
					return false;
				}
				if (name.length == 0 || name.length > 50) {
					alert("请填写参数名称且长度不能超过50！");
					document.getElementById("name").value = "";
					document.getElementById("name").focus();
					return false;
				}
			
				if(code == "MARQUEE" ){
					if(isNaN(name)){
						alert("MARQUEE的参数名称必须为数字！")
						document.getElementById("name").value = "";
						document.getElementById("name").focus();
						return false;
					}
					if(!myRegExp.test(name)){
						alert("数字必须是0到10！")
						document.getElementById("name").value = "";
						document.getElementById("name").focus();
						return false;
					}
				}
			
				if (value.length == 0 || value.length > 100) {
					alert("请填写参数值且长度不能超过100！");
					document.getElementById("value").value = "";
					document.getElementById("value").focus();
					return false;
				}
								
				if(str.length == 13){
					 alert("同一类别下参数名称不能相同");
					 document.getElementById("name").value = "";
					 document.getElementById("name").focus();
					 return false;
				}
				if(startTime.length > 0){
					if(endTime.length == 0){
						alert("请填写有效的结束日期！");
						document.getElementById("endDate").value="";
						document.getElementById("endDate").focus();
						return false;
					}
				}
				if(endTime.length > 0){
					if(startTime.length == 0){
						alert("请填写有效的起始日期！");
						document.getElementById("beginDate").value="";
						document.getElementById("beginDate").focus();
						return false;
					}
				}
				if(endTime != 0){
					if(endTime<startTime){
						alert("结束日期不能小于起始日期！");
						document.getElementById("endDate").focus();
						return false;
					}
				}
			}
		function refresh(){
			$.get('<%=request.getContextPath()%>/system/parameter_codeValide.action?t='+new Date().getTime(),{code : $("#code").val(),name : $("#name").val()},function(data){				
				if($("#name").val() != ''){
					$("#str").html(data);
				}else{
					$("str").html();
				}
			});
		}
	</script>
	
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">参数管理-添加参数&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
		<s:form name="add_parameter" action="parameter_add" method="post" namespace="/system" theme="simple" onsubmit="return check()">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">
						参数类别代码:
					</td>
					<td width="35%" align="left"><div> 
						<input type="text" name="code" value="" id="code"><font color="red">*</font></div>
					</td>
					<td class="th1">
						参数名称:
					</td>
					<td width="35%" align="left">
						<input type="text" name="name" value="" id="name" onblur="refresh()"><font color="red">*<span id="str"></span></font>
					</td>
				</tr>
				<tr>
					<td class="th1">
						参数值:
					</td>
					<td width="35%" align="left">
						<textarea id="value" name="value"  cols="70" rows="10" ></textarea><font color="red">*</font>
					</td>
					<td class="th1">
						备注:
					</td>
					<td width="35%" align="left">
						<input type="text" name="remark" value="">
					</td>
				</tr>
				<tr>
					<td class="th1">有效起始日期:</td>
					<td width="35%" align="left">
						<input class="Wdate"  id="effectiveDate" style="width: 150px;" name="effectiveDate" type="text" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/>
					</td>
					<td class="th1">有效结束日期:</td>
					<td width="35%" align="left">
						<input class="Wdate"  id="expirationDate" style="width: 150px;" name="expirationDate" type="text" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" />
					</td>
				</tr>
				<tr>
					<td align="center" colspan=4>
					<input type="submit" id="add" value="" class="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"/>
					<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'" onclick="JavaScript:window.history.back();" />				
					</td>
				</tr>
			</table>
		</s:form>
		</div>
	</body>
</html>
