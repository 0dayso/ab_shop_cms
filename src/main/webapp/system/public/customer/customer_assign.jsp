<%@ page language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>客户分配</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
				var trancodecheckbox = "#" + $("div #show > div:first > div:first span").attr("id");
				$(trancodecheckbox).toggle(function(){
					$(trancodecheckbox).parent().siblings().show();},function(){
					$(trancodecheckbox).parent().siblings().hide();
				})
				//定义全选框的名称
				var $checkAll = $("#trancodes");
				var $checkArray = $("input[type='checkbox']").not($("#trancodes"));
				//var $checkUnAll = $("#checkUnAll");
				//alert($checkArray.length);
		
				//定义全选框的全选和全不选
				$checkAll.click(function(){
					var checkAll_State = $(this).attr("checked");
					//alert(checkAll_State);
					$checkArray.each(function(){
						$(this).attr("checked",checkAll_State);
					})
				})
				/**
				       1. $("input[name='checkbox_name'][checked]"); //选择被选中CheckBox元素的集合 如果你想得到 
				       2.  Value值你需要遍历这个集合 
				       3. $($("input[name='checkbox_name'][checked]")). 
				       4. each(function(){arrChk+=this.value + ',';});//遍历被选中CheckBox元素的集合 得到Value值 
				       5. $("#checkbox_id").attr("checked"); //获取一个CheckBox的状态(有没有被选中,返回true/false) 
				       6. $("#checkbox_id").attr("checked",true); //设置一个CheckBox的状态为选中(checked=true) 
				       7. $("#checkbox_id").attr("checked",false); //设置一个CheckBox的状态为不选中(checked=false) 
				       8. $("input[name='checkbox_name']").attr 
				       9. ("checked",$("#checkbox_id").attr("checked"));//根据3,4,5条，你可以分析分析这句代码的意思  
				      10. $("#text_id").val().split(","); //将Text的Value值以','分隔 返回一个数组 
				*/
				$("input:checked").each(function(){   
					if($(this).attr('checked')==true){
    					var name = $(this).attr("id");
    					var array = new Array();
    					array = name.split("_");
    					//alert(array[0]);
    					$("#"+array[0]).attr("checked",true);
    					$("#trancodes").attr("checked",true);
    				}
				})   
				
		})
		function checkNode1(obj){
			//alert(obj)
			//alert(document.getElementById(obj).checked);
			if(document.getElementById(obj).checked == true){
				document.getElementById("trancodes").checked = true;
				$("div[id="+obj+"] input:checkbox").attr("checked",true);
			}else{
				$("div[id="+obj+"] input:checkbox").attr("checked",false);
			}
			var $fatherarray = $("div[id=father]").children();
			$fatherarray.each(function(){
				var state = $(this).attr("checked");
				if(state == true){
					document.getElementById("trancodes").checked = true;
					return false;
				}else{
					document.getElementById("trancodes").checked = false;
				}
			})
		}
		function checkNode2(obj){
			//alert(obj.length);
			var array1 = new Array();
			array1 = obj.split("_");
			//alert(array1[0]);
			//alert(document.getElementById(obj).checked)
			
			var $sonarray = $("div[id="+array1[0]+"] input:checkbox")
			$sonarray.each(function(){
				var state = $(this).attr("checked");
				if(state == true){
					$("input[id="+array1[0]+"]").attr("checked",true);
					document.getElementById("trancodes").checked = true;
					return false;
				}else{
					$("input[id="+array1[0]+"]").attr("checked",false);
					var $fatherarray = $("div[id=father]").children();
					$fatherarray.each(function(){
						var state = $(this).attr("checked");
						if(state == true){
							document.getElementById("trancodes").checked = true;
							return false;
						}else{
							document.getElementById("trancodes").checked = false;
						}
					})
					//document.getElementById("trancodes").checked = false;
				}
			})
		}
		</script>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">客户管理-权限分配</font></span>
        </div>
		<div class="main">
			<s:form action="customerAddService" method="post" theme="simple"
				namespace="/system" onsubmit="return checkData()">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							客户名称:
						</td>
						<td align="left" width="100%">
							<s:property value="companyName"/>
							<input type="hidden" name="customerId" value="<s:property value="id"/>">
						</td>
					</tr>
					<tr>
						<td colspan="4" align="left">
						<div id="show">
							<div style="border: 0px solid gray;margin-left: 20px;">
								<div style="height: 20px;line-height:20px; width: 25px; vertical-align:top;  display: inline;">
										<input style="border: 0px;" id="trancodes"
											type="checkbox" name="trancodes"
											value="">
											<span id="show1"><label style="font-weight: bold;">增值业务分配<font style="color: red">(点击显示/隐藏)</font></label></span>
								</div>
								<div style="margin-left: 30px; margin-top: 0px; display:none;">
									<div style="height: 20px;line-height:20px; width: 25px; vertical-align:top;  display: inline">
										<s:iterator value="productFunctionMap" id="pf">
											<div style="margin-left: 0px;border: 0px solid;height: 20px;line-height: 20px;" id="father">
												<input style="border: 0px;" id="<s:property value='#pf.key'/>"
													onclick="checkNode1('<s:property value="#pf.key"/>')" type="checkbox" name="productId"
													value=""/>
													<font color="red"><s:property value="#pf.key" /></font>
											</div>
											<div id="<s:property value='#pf.key'/>">
												<s:iterator value="#pf.value">
													<s:if test="productFunctionIds.contains(id)">
														<div style="border-bottom: 1px dotted gray;"><input checked="checked" style="border: 0px;margin-left: 30px;" id="<s:property value='#pf.key'/>_<s:property value='id'/>" onclick="checkNode2('<s:property value="#pf.key"/>_<s:property value="id"/>')" type="checkbox" name="productFunctionIds" value="<s:property value='id'/>"/>
														<s:property value="operationName"/></div>
													</s:if>
													<s:else>
														<div style="border-bottom: 1px dotted gray;"><input style="border: 0px;margin-left: 30px;" id="<s:property value='#pf.key'/>_<s:property value='id'/>" onclick="checkNode2('<s:property value="#pf.key"/>_<s:property value="id"/>')" type="checkbox" name="productFunctionIds" value="<s:property value='id'/>"/>
														<s:property value="operationName"/></div>
													</s:else>					
												</s:iterator>
											</div>
										</s:iterator>
									</div>
								</div>
							</div>
						</div>
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<s:submit value="" cssClass="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"></s:submit>
							<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'"
								onclick="JavaScript:window.history.back();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>