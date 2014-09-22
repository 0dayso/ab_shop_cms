<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" import="net.rytong.entity.FunctionRight"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	FunctionRight functionRight = (FunctionRight)request.getAttribute("functionRight");
	int parentId = 0;
	int grandpaId = 0;
	if(functionRight.getLevel() > 1){
		FunctionRight  parent = functionRight.getFunctionRight();
		parentId = parent.getFunctionRightId().intValue();
		if(parent.getLevel() > 1){
			FunctionRight grandpa = parent.getFunctionRight();
			grandpaId = grandpa.getFunctionRightId().intValue();
		}
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>My JSP 'permission_eidt.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/public/js/public/check_permission.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script>
			//初始化的时候需要赋值level
			$(document).ready(function() {
				$('#parentTr').hide();
				$('#menu1Name').hide();
				$('#level1').hide();
				$('#menu2Name').hide();
				$('#level2').hide();
				var level = "<s:property value='%{#request.functionRight.level}'/>";//开始级别
				var parentId = <%=parentId%>;
				var grandpaId = <%=grandpaId%>;
				displayMenuLevel(grandpaId,parentId,level);
			});
			//改变的时候需要赋值
			function displayMenuLevel(grandPaId,parentId,level){
				var t  = new Date().getTime();
				if(level==2){
					$('#parentTr').show();
					$('#menu1Name').show();
					$('#level1').show();
				}
				if(level>=3){
					$('#parentTr').show();
					$('#menu1Name').show();
					$('#level1').show();
					$('#menu2Name').show();
					$('#level2').show();
				}
				//得到所有的一级菜单
				$.get("menu/getMenuData?random=" + t, {'functionId': grandPaId , 'parentId': "0",functionName:"level1Function",level:"1",js:"changeLevel1Menu();"},  
	 			  	function(data){
				  		document.getElementById('level1').innerHTML = data;
				  		if(level==2 && parentId>0){
				  			$("#level1Function").val(parentId);
				  		}
				  		if(level>=3&&grandPaId>0){
				  			$("#level1Function").val(grandPaId);
				  		}
				  		changeParentId();
					});
				//如果存在二级菜单-加载
				if(grandPaId>0){
					$.get("menu/getMenuData?random=" + t, {'parentId': grandPaId, functionName:"level2Function", level:"2", js:"changeLevel2Menu();"},  
	 			  	function(data){
				  		document.getElementById('level2').innerHTML = data;
				  		if(parentId>0){
				  			$("#level2Function").val(parentId);
				  		}
				  		changeParentId();
					});
				}
			}
			function changeDisplayMenu(level){
				var parentId="<s:property value='%{parent.functionRightId}'/>";//父节点
				var grandPaId=0;
				if(level>=3){
					tempId="<s:property value='%{parent.functionRight.functionRightId}'/>";//祖节点
					if(tempId>0){
						grandPaId=tempId;
					}
				}
				displayMenuLevel(grandPaId,parentId,level);
				if(level==1){
					//菜单全部消失
					$('#parentTr').hide();
					$('#menu1Name').hide();
					$('#level1').hide();
					$('#menu2Name').hide();
					$('#level2').hide();
					//设置parentId隐藏域的值
					$('#parentId').val('0');
				}
				if(level==2){
					//显示一级菜单
					$('#parentTr').show();
					$('#menu1Name').show();
					$('#level1').show();
					$('#menu2Name').hide();
					$('#level2').hide();
				}
				if(level>=3){
					//显示全部菜单
					$('#parentTr').show();
					$('#menu1Name').show();
					$('#level1').show();
					$('#menu2Name').show();
					$('#level2').show();
				}
				//判断菜单级别是不是被改变了
				var originalLevel="<s:property value='%{#request.functionRight.level}'/>";
				if(originalLevel!=level&&(parseInt(originalLevel)+parseInt(level))<7){
					$('#hasChangeLevel').val("true");
				}
				//更新parentId
				changeParentId();
			}
			//一级菜单onchange事件
			function changeLevel1Menu(){
				//如果有二级菜单需要重新加载二级菜单
				var t  = new Date().getTime();
				var level=$("input[name='level']:checked").val();
				var menu1Value=$('#level1Function').val();
				if(level>=3){
					$.get("menu/getMenuData?random=" + t, {'parentId': menu1Value,functionName:"level2Function",level:"2",js:"changeLevel2Menu();"},  
		 			  	function(data){
					  		document.getElementById('level2').innerHTML = data;
					  		changeParentId();
						});
				}
				else{
					changeParentId();
				}
			}
			//二级菜单 onchange 事件
			function changeLevel2Menu(){
				changeParentId();
			}
			function checkForm(){
				var hasChangeLevel=$('#hasChangeLevel').val();
				if(hasChangeLevel=='true'){
					//判断是不是有子节点，如果有的话提示
					var childSize="<s:property value='%{#request.functionRight.functionRights.size}'/>";
					if(parseInt(childSize)>0){
						alert("该菜单下还有子菜单不可以跨级移动菜单!");
						return false;
					}
				}
				return true;
			}
			function changeParentId(){
				var level=$("input[name='level']:checked").val();
				if(level==1){
					$("#parentId").val(0);
				}
				if(level==2){
					$("#parentId").val($('#level1Function').val());
				}
				if(level>=3){
					$("#parentId").val($('#level2Function').val());
				}
			}
		</script>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">权限管理-修改功能模块&nbsp;&nbsp;&nbsp;&nbsp;(带<font color="red">*</font>的为必填项)</font></span>
        </div>
    	<div class="main">
			<s:form action="permission_edit" method="post" theme="simple" namespace="/system" onsubmit="return checkForm();">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td bgcolor="#C0C9E0" align="right" width="15%">
							功能菜单级别：
						</td>
						<td bgcolor="#C0C9E0" colspan="3" align="left">
							<s:radio cssStyle="border: 0px;" list="#{1:'一级功能菜单',2:'二级功能菜单',3:'三级功能菜单',4:'三级权限菜单'}" name="level"
								onclick="changeDisplayMenu(this.value)" value="%{#request.functionRight.level}"></s:radio>
						</td>
					</tr>
				</table>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							菜单功能名称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="name" name="name" value="%{#request.functionRight.name}" size="50"></s:textfield>
							<font color="red">*</font>
						</td>
						<td class="th1">
							菜单功能路径:
						</td>
						<td width="35%" align="left">
							<s:textfield id="path" name="path" value="%{#request.functionRight.path}" size="50"></s:textfield>
							<font color="red">*</font>
						</td>
					</tr>
					<tr>
						<td class="th1">激活:</td>
						<td width="35%" align="left">
							<s:if test='%{#request.functionRight.level=="1"}'>
								<s:radio cssClass="dd_radio" name="isValid" id="isValid" list="#{'1':'是','0':'否'}" value="%{#request.functionRight.isValid}"></s:radio>
							</s:if>
							<s:else>
								<s:radio cssClass="dd_radio" name="isValid" id="isValid" list="#{'1':'是','0':'否'}" value="%{#request.functionRight.isValid}"></s:radio>
							</s:else>
						</td>
						<td class="th1">
							菜单功能描述:
						</td>
						<td width="35%" align="left">
							<s:textfield id="description" name="description" value="%{#request.functionRight.description}" size="50"></s:textfield>
							<s:hidden name="id" value="%{#request.functionRight.functionRightId}"></s:hidden>
							<s:hidden id="parentId" name="parentId" value="%{#request.functionRight.functionRight.functionRightId}"></s:hidden>
							<s:hidden id="hasChangeLevel" name="hasChangeLevel" value="false"></s:hidden>
						</td>
					</tr>
					<tr>
						<td class="th1">
							菜单排序:
						</td>
						<td width="35%" align="left">
							<s:textfield id="sort" name="sort" size="10" value="%{#request.functionRight.sort}"></s:textfield>
						</td>
						<td class="th1">
							新功能标记:
						</td>
						<td width="35%" align="left">
							<s:select name="isNew" id="isNew" list="#{'0':'不标记新功能','1':'标记新功能'}"/>
						</td>
					</tr>
					<tr id="parentTr">
						<td class="th1" id="menu1Name">一级菜单:</td>
						<td width="35%" align="left" id="level1">
						</td>
						<td class="th1" id="menu2Name">二级菜单:</td>
						<td width="35%" align="left" id="level2">
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<s:submit value="" cssClass="modify_btn" onmousemove="this.className='modify_btn1'"  onmouseout="this.className='modify_btn'"></s:submit>
							<input type="button" value="" class="back_btn" onmousemove="this.className='back_btn1'" onmouseout="this.className='back_btn'"
								onclick="JavaScript:window.history.back();" />
						</td>
					</tr>
				</table>
			</s:form>
		</div>
	</body>
</html>
