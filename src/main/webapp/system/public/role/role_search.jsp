<%@ page language="java" contentType="text/html; charset=UTF-8"
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

		<title>My JSP 'role_search.jsp' starting page</title>
		<link href="<%=request.getContextPath()%>/public/css/global.css"
			rel="stylesheet" type="text/css">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<SCRIPT type="text/javascript" src="<%=request.getContextPath()%>/public/js/My97DatePicker/WdatePicker.js"></SCRIPT>
		<script type="text/javascript" src="<%=path%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/public/check_role.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/page.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/dateTime.js"></script>
	</head>

	<body onload="init();">
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">权限管理-角色查询</font></span>
    	</div>
		<div class="main">
			<s:form action="role_search" theme="simple" namespace="/system" onsubmit="return checkTime()">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							角色名称:
						</td>
						<td width="20%" align="left">
							<s:textfield id="name" name="name"></s:textfield>
						</td>
						<td class="th1">
							部门名称:
						</td>
						<td width="20%" align="left">
							<s:textfield id="departName" name="departName"></s:textfield>
						</td>
						<td class="th1">
							创建日期:
						</td>
						<td width="50%" align="left">
							<table>
								<tr>
									<td>
										<s:textfield id="beginDates" name="beginTime" cssClass="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="%{beginTime}"/>
									</td>
									<td width="3">
										-
									</td>
									<td>
										<s:textfield id="endDates" name="endTime" cssClass="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="%{endTime}"/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="right">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'" onclick="return checkTime3()"></s:submit>
						</td>
						<td colspan="3" align="left">
							<div id="add" style="display:none">
							<input type="button" value="" class="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"
								onclick="javascript:window.location.href='<%=basePath%>/system/permission/role_view.action'" />
							</div>
						</td>
					</tr>
				</table>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr class="th">
						<td width="10%">
							序号
						</td>
						<td width="25%">
							角色名称
						</td>
						<td width="25%">
							部门名称
						</td>
						<td width="20%">
							创建日期
						</td>
						<td width="20%" colspan="2">
							操作
						</td>
					</tr>
					<s:if test="pageList != null && pageList.size == 0">
							<tr>
								<td colspan="11">对不起，暂无数据！！！</td>
							</tr>
					</s:if>
					<s:else>
						<s:iterator value="pageList" status="st" id="b">
							<tr
								class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
								<td>
									<s:property value="#st.count" />
								</td>
								<td>
									<s:property value="name" />
								</td>
								<td>
									<s:property value="departName" />
								</td>
								<td>
									<s:property value="%{formatDateOrTime(createdDate)}" />
								</td>
								<td align="right">
									<div id="edit<s:property value='#st.count' />" style="display:none">
										<input type="button" value="" class="modify_btn" onmousemove="this.className='modify_btn1'"  onmouseout="this.className='modify_btn'"
											onclick="javaScript:window.self.location='<%=request.getContextPath() %>/system/role_detail.action?id=<s:property value="id"/>'">
									</div>
								</td>
								<td align="left">
									<div id="del<s:property value='#st.count' />" style="display:none">
										<input type="button" value="" class="del_btn" onmousemove="this.className='del_btn1'" onmouseout="this.className='del_btn'"
											onclick="del(<s:property value='id'/> )">
									</div>
								</td>
							</tr>
						</s:iterator>
						<s:if test="pageList != null && pageList.size>0">
							<tr class="pageTr">
								<td colspan="6">
									<s:url id="url_pre" value="role_search.action">
										<s:param name="pageIndex" value="pageIndex-1"></s:param>
										<s:param name="name" value="name"></s:param>
										<s:param name="departName" value="departName"></s:param>
										<s:param name="beginTime" value="beginTime"></s:param>
										<s:param name="endTime" value="endTime"></s:param>
									</s:url>
									<s:url id="url_next" value="role_search.action">
										<s:param name="pageIndex" value="pageIndex+1"></s:param>
										<s:param name="name" value="name"></s:param>
										<s:param name="departName" value="departName"></s:param>
										<s:param name="beginTime" value="beginTime"></s:param>
										<s:param name="endTime" value="endTime"></s:param>
									</s:url>
									<s:url id="url_first" value="role_search.action">
										<s:param name="pageIndex" value="1"></s:param>
										<s:param name="name" value="name"></s:param>
										<s:param name="departName" value="departName"></s:param>
										<s:param name="beginTime" value="beginTime"></s:param>
										<s:param name="endTime" value="endTime"></s:param>
									</s:url>
									<s:url id="url_last" value="role_search.action">
										<s:param name="pageIndex" value="pageCount"></s:param>
										<s:param name="name" value="name"></s:param>
										<s:param name="departName" value="departName"></s:param>
										<s:param name="beginTime" value="beginTime"></s:param>
										<s:param name="endTime" value="endTime"></s:param>
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
