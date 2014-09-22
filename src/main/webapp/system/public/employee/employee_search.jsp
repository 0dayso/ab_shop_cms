<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<SCRIPT type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></SCRIPT>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_employee.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/dateTime.js"></script>
		<title>网站用户管理-查询用户</title>
	</head>
	<body onload="init()">
		<div  class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">网站用户管理-查询用户</font></span>
    	</div>
    	<div class="main">
		<s:form action="employee_search" method="post" theme="simple" namespace="/system">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">姓名</td>
					<td width="20%" align="left"><s:textfield id="name" name="name"></s:textfield></td>
					<td class="th1">性别</td>
					<td width="20%" align="left"><s:select list="#{-1:'全部', 'M' :'男', 'F' :'女'}" id="gender" name="gender"></s:select></td>
					<td class="th1">证件类型</td>
					<td width="20%" align="left"><s:select list="#{-1:'全部', 'NI' :'身份证', 'PP' :'护照',  'ID' :'其他'}" id="idType" name="idType"></s:select></td>
				</tr>
				<tr>
					<td class="th1">证件号码</td>
					<td width="20%" align="left"><s:textfield id="idNo" name="idNo"></s:textfield></td>
					<td class="th1">登录名</td>
					<td width="20%" align="left"><s:textfield id="login" name="login"></s:textfield></td>
					<td class="th1">区域</td>
					<td width="20%" align="left"><s:select list="#{-1:'全部', 0:'华东', 1:'华北'}" id="area" name="area"></s:select></td>
				</tr>
				<tr>
					<td class="th1">手机</td>
					<td width="20%" align="left"><s:textfield id="mobile" name="mobile"></s:textfield></td>
					<td class="th1">邮箱</td>
					<td width="20%" align="left"><s:textfield id="email" name="email"></s:textfield></td>
					<td class="th1">邮编</td>
					<td width="20%" align="left"><s:textfield id="postcode" name="postcode"></s:textfield></td>
				</tr>
				<tr>
					<td class="th1">地址</td>
					<td width="20%" align="left"><s:textfield id="address" name="address"></s:textfield></td>
					<td class="th1">注册时间</td>
					<td align="left" colspan="3">
						<input class="Wdate"  id="beginDate" name="signStartTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value="signStartTime"/>"/>		
						--
						<input class="Wdate" id="endDate" name="signEndTime" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="<s:property value="signEndTime"/>"/>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="right">
						<input type="submit" class="query_btn" value="" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'" onclick="return checkTime3()"/>
					</td>
					<td  colspan="3" align="left">
						<div id="add" style="display: none;">
							<input type="button" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'" onclick="javascript:window.location.href='<%=request.getContextPath() %>/system/public/employee/employee_add.jsp'" class="add_btn" value=""/>
						</div>
					</td>
				</tr>
			</table>
		
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr class="th">
					<td>序号</td>
					<td>姓名</td>
					<td>性别</td>
					<td>证件类型</td>
					<td>证件号码</td>
					<td>手机</td>
					<td>区域</td>
					<td>邮编</td>
					<td>邮箱</td>
					<td>登录名</td>
					<td width="10%">操作</td>
				</tr>
				<s:if test="pageList.size == 0">
					<tr>
						<td colspan="12">对不起，暂无数据！！！</td>
					</tr>
				</s:if>
				<s:else>
				<s:iterator value="pageList" status="st" id="e">
					<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
						<td><s:property value="#st.count"/></td>
						<td><s:property value="name"/></td>
						<td>
							<s:if test='gender=="M"'>男</s:if>
							<s:elseif test='gender=="F"'>女</s:elseif>
						</td>
						<td>
							<s:if test="idType=='NI'">身份证</s:if>
							<s:elseif test="idType=='PP'">护照</s:elseif>
							<s:elseif test="idType=='CC'">信用卡</s:elseif>
							<s:elseif test="idType=='ID'">其他</s:elseif>
						</td>
						<td><s:property value="idNo"/></td>
						<td><s:property value="mobile"/></td>
						<td>
							<s:if test="area==0">华东</s:if>
							<s:elseif test="area==1">华北</s:elseif>
						</td>
						<td><s:property value="postcode"/></td>
						<td><s:property value="email"/></td>
						<td><s:property value="login"/></td>
						<td align="center">
							<table>
								<tr>
									<td align="right">
										<div id="edit<s:property value='#st.count' />" style="display: none;">
											<input type="button" value="" class="modify_btn" onmousemove="this.className='modify_btn1'"  onmouseout="this.className='modify_btn'" onclick="javaScript:window.self.location='employee_detail.action?id=<s:property value="id"/>'">
										</div>
									</td>
									<td align="left">
										<div id="del<s:property value='#st.count' />" style="display: none;">
											<input type="button" value="" onmousemove="this.className='del_btn1'" onmouseout="this.className='del_btn'"  class="del_btn" onclick="return del(<s:property value='id'/>)">
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</s:iterator>
					<s:if test="pageList != null && pageList.size>0">
						<tr class="pageTr">
							<td colspan="13">
								<s:url id="url_pre" value="employee_search.action">
									<s:param name="pageIndex" value="pageIndex-1"></s:param>
									<s:param name="login" value="login"></s:param>
									<s:param name="name" value="name"></s:param>
									<s:param name="idType" value="idType"></s:param>
									<s:param name="idNo" value="idNo"></s:param>
									<s:param name="gender" value="gender"></s:param>
									<s:param name="mobile" value="mobile"></s:param>
									<s:param name="area" value="area"></s:param>
									<s:param name="address" value="address"></s:param>
									<s:param name="signStartTime" value="signStartTime"></s:param>
									<s:param name="signEndTime" value="signEndTime"></s:param>
									<s:param name="email" value="email"></s:param>
									<s:param name="postcode" value="postcode"></s:param>
								</s:url>
								<s:url id="url_next" value="employee_search.action">
									<s:param name="pageIndex" value="pageIndex+1"></s:param>
									<s:param name="login" value="login"></s:param>
									<s:param name="name" value="name"></s:param>
									<s:param name="idType" value="idType"></s:param>
									<s:param name="idNo" value="idNo"></s:param>
									<s:param name="gender" value="gender"></s:param>
									<s:param name="mobile" value="mobile"></s:param>
									<s:param name="area" value="area"></s:param>
									<s:param name="address" value="address"></s:param>
									<s:param name="signStartTime" value="signStartTime"></s:param>
									<s:param name="signEndTime" value="signEndTime"></s:param>
									<s:param name="email" value="email"></s:param>
									<s:param name="postcode" value="postcode"></s:param>
								</s:url>
								<s:url id="url_first" value="employee_search.action">
									<s:param name="pageIndex" value="1"></s:param>
									<s:param name="login" value="login"></s:param>
									<s:param name="name" value="name"></s:param>
									<s:param name="idType" value="idType"></s:param>
									<s:param name="idNo" value="idNo"></s:param>
									<s:param name="gender" value="gender"></s:param>
									<s:param name="mobile" value="mobile"></s:param>
									<s:param name="area" value="area"></s:param>
									<s:param name="address" value="address"></s:param>
									<s:param name="signStartTime" value="signStartTime"></s:param>
									<s:param name="signEndTime" value="signEndTime"></s:param>
									<s:param name="email" value="email"></s:param>
									<s:param name="postcode" value="postcode"></s:param>
								</s:url>
								<s:url id="url_last" value="employee_search.action">
									<s:param name="pageIndex" value="pageCount"></s:param>
									<s:param name="login" value="login"></s:param>
									<s:param name="name" value="name"></s:param>
									<s:param name="idType" value="idType"></s:param>
									<s:param name="idNo" value="idNo"></s:param>
									<s:param name="gender" value="gender"></s:param>
									<s:param name="mobile" value="mobile"></s:param>
									<s:param name="area" value="area"></s:param>
									<s:param name="address" value="address"></s:param>
									<s:param name="signStartTime" value="signStartTime"></s:param>
									<s:param name="signEndTime" value="signEndTime"></s:param>
									<s:param name="email" value="email"></s:param>
									<s:param name="postcode" value="postcode"></s:param>
								</s:url>
								共
								<s:property value="total" />
								条记录&nbsp;&nbsp;当前第
								<s:property value="pageIndex" />
								页，共
								<s:property value="pageCount" />
								页&nbsp;&nbsp;
								<s:if test="pageCount > 1">
									<s:if test="pageIndex > 1">
										<s:a href="%{url_first}">首页</s:a>
										<s:a href="%{url_pre}">上一页</s:a>
									</s:if>
									<s:if test="pageIndex < pageCount">
										<s:a href="%{url_next}">下一页</s:a>
										<s:a href="%{url_last}">尾页</s:a>
									</s:if>
								</s:if>
								转到
								<input type="text" size="3" id="pageIndex" name="pageIndex" onkeypress="send(event,<s:property value='pageCount'/>);"
									onmouseover='this.focus();' onfocus='this.select()'>
								页
								<input type="submit" value="转"
									onclick="return checkPageIndex(<s:property value='pageCount'/>)" />
							</td>
						</tr>
					</s:if>
					</s:else>
				</table>
			</s:form>
		</div>
	</body>
</html>