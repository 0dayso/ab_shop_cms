<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_customer.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<title>查询商户</title>
	</head>
	<body onload="init()">
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">商户管理</font></span>
    	</div>
    	<div class="main">
			<s:form action="customer_search" method="post" theme="simple" namespace="/system">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							商户名称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="companyName" name="companyName"></s:textfield>
						</td>
						<td class="th1">
							商户简称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="ShotName" name="shotName"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="th1">
							联系人姓名:
						</td>
						<td width="35%" align="left">
							<s:textfield id="contactor" name="contactor"></s:textfield>
						</td>
						<td class="th1">
							地址:
						</td>
						<td width="35%" align="left">
							<s:textfield id="address" name="address"></s:textfield>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'"></s:submit>
						</td>
						<td  colspan="2" align="left">
							<div id="add">
								<input type="button" value="" class="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"  onclick="javascript:window.location.href='<%=request.getContextPath() %>/system/public/customer/customer_add.jsp'"/>
							</div>
						</td>
					</tr>
				</table>
			
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr class="th">
						<td width="3%">序号</td>
						<td>商户名称</td>
						<td>商户简称</td>
						<td width="20%">地址</td>
						<td>联系人姓名</td>
						<td>联系人手机</td>
						<td>联系电话</td>
						<td>注册时间</td>
						<td width="15%">操作</td>
					</tr>
					<s:if test="pageList != null && pageList.size == 0">
						<tr>
							<td colspan="9">对不起，暂无数据！！！</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="pageList" status="st" id="c">
							<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
								<td>
									<s:property value="#st.count" />
								</td>
								<td>
									<s:property value="companyName" />
								</td>
								<td>
									<s:property value="shotName" />
								</td>
								<td>
									<s:property value="address" />
								</td>
								<td>
									<s:property value="contactor" />
								</td>
								<td>
									<s:property value="mobile" />
								</td>
								<td>
									<s:property value="phone" />
								</td>
								<td>
									<s:property value="%{formatDateOrTime(recordDate)}" />
								</td>
								<td align="center">
									<table>
										<tr>
											<td align="center">
												<div id="edit<s:property value='#st.count' />">
													<input type="button" value="" class="modify_btn" onmousemove="this.className='modify_btn1'"  onmouseout="this.className='modify_btn'" onclick="javaScript:window.self.location='customer_detail.action?id=<s:property value="id"/>'">
												</div>
											</td>
											<td align="center">
												<div id="del<s:property value='#st.count' />">
													<input type="button" value="" class="del_btn" onmousemove="this.className='del_btn1'" onmouseout="this.className='del_btn'" onclick="del(<s:property value='id'/>)">
												</div>
											</td>
											<s:if test="%{#session.projectName == 'VAS'}">
												<td align="center">
													<div id="assign<s:property value='#st.count' />">
														<input type="button" value="" class="assign_btn" onmousemove="this.className='assign_btn1'" onmouseout="this.className='assign_btn'" onclick="assign('<s:property value="id"/>','<s:property value="companyName"/>')">
													</div>
												</td>
											</s:if>
										</tr>
									</table>
								</td>
							</tr>
						</s:iterator>
						<s:if test="pageList != null && pageList.size>0">
							<tr class="pageTr">
								<td colspan="9">
									<s:url id="url_pre" value="customerSearch.action">
										<s:param name="pageIndex" value="pageIndex-1"></s:param>
										<s:param name="shortName" value="shortName"></s:param>
										<s:param name="companyName" value="companyName"></s:param>
										<s:param name="address" value="address"></s:param>
										<s:param name="contactor" value="contactor"></s:param>
										<s:param name="username" value="username"></s:param>
									</s:url>
									<s:url id="url_next" value="customerSearch.action">
										<s:param name="pageIndex" value="pageIndex+1"></s:param>
										<s:param name="shortName" value="shortName"></s:param>
										<s:param name="companyName" value="companyName"></s:param>
										<s:param name="address" value="address"></s:param>
										<s:param name="contactor" value="contactor"></s:param>
										<s:param name="username" value="username"></s:param>
									</s:url>
									<s:url id="url_first" value="customerSearch.action">
										<s:param name="pageIndex" value="1"></s:param>
										<s:param name="shortName" value="shortName"></s:param>
										<s:param name="companyName" value="companyName"></s:param>
										<s:param name="address" value="address"></s:param>
										<s:param name="contactor" value="contactor"></s:param>
										<s:param name="username" value="username"></s:param>
									</s:url>
									<s:url id="url_last" value="customerSearch.action">
										<s:param name="pageIndex" value="pageCount"></s:param>
										<s:param name="shortName" value="shortName"></s:param>
										<s:param name="companyName" value="companyName"></s:param>
										<s:param name="address" value="address"></s:param>
										<s:param name="contactor" value="contactor"></s:param>
										<s:param name="username" value="username"></s:param>
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