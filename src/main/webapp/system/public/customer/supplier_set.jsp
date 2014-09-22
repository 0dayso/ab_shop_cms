<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<title>设置供应商</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">客户管理-设置供应商</font></span>
        </div>
    	<div class="main">
	    		<table class="tableborder" border="1" bordercolor="#dbdbdb">
	    			<tr class="th">
	    				<td>序号</td>
	    				<td>客户名称</td>
	    				<td>供应商</td>
	    				<td>操作</td>
	    			</tr>
		    		<s:iterator value="list" status="st">
		    			<s:form action="supplierSet_edit" method="post" theme="simple">
			    			<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
			    				<td><s:property value="#st.getCount()" /></td>
			    				<td>
			    					<s:if test="name=='bankcomm'">交通银行</s:if>
									<s:elseif test="name=='CEB'">中国光大银行</s:elseif>
									<s:elseif test="name=='GDB'">广东发展银行</s:elseif>
									<s:elseif test="name=='BOB'">北京银行</s:elseif>
									<s:elseif test="name=='EGB'">恒丰银行</s:elseif>
									<s:elseif test="name=='CIB'">兴业银行</s:elseif>
			    				</td>
			    				<td>
			    					<s:select list="#{'KQ': '快钱', '19PAY': '19pay'}" value="value" name="value"></s:select>
			    				</td>
			    				<td>
									<input type="hidden" id="name" name="name" value="<s:property value='name'/>"/>
			    					<input type="submit" value="" class="modify_btn" onmousemove="this.className='modify_btn1'"  onmouseout="this.className='modify_btn'"/>
			    				</td>
			    			</tr>
			    		</s:form>
		    		</s:iterator>
	    		</table>
    	</div>
	</body>
</html>