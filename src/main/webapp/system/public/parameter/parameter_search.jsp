<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_parameter.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/dateTime.js"></script>
	</head>
	<body>
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">参数管理</font></span>
    	</div>
    	<div class="main">
			<s:form name="list_parameter" action="parameter_search" method="post" theme="simple" namespace="/system" onsubmit="return checkTime3()">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							参数类别代码:
						</td>
						<td width="35%" align="left">
							<s:textfield id="temp_code" name="code" value="%{code}"></s:textfield>
						</td>
						<td class="th1">
							参数名称:
						</td>
						<td width="35%" align="left">
							<s:textfield id="temp_name" name="name" value="%{name}"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="th1">
							参数值
						</td>
						<td width="35%" align="left">
							<input name="value" type="text" value="<s:property value='value'/>">
						</td>
						<td class="th1"></td>
						<td width="35%" align="left"></td>
					</tr>
					<tr>
						<td class="th1">有效起始日期:</td>
						<td width="35%" align="left">
						<input class="Wdate"  id="beginDate" style="width: 150px;" name="effectiveDate" type="text" value="<s:property value='effectiveDate'/>" onClick="WdatePicker({dateFmt:'yyyyMMdd'})"/>
						</td>
						<td class="th1">有效结束日期:</td>
						<td width="35%" align="left">
							<input class="Wdate"  id="endDate" style="width: 150px;" name="expirationDate" type="text" value="<s:property value='expirationDate'/>" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<input type="submit" value="" class="query_btn"  onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'">
						</td>
						<td colspan="2" align="left">
							<div id="add">
								<input type="button" class="add_btn" value="" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'" onclick="javascript:window.location.href='<%=request.getContextPath()%>/system/public/parameter/parameter_add.jsp'">
							</div>
						</td>
					</tr>
				</table>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr class="th">
						<td width="5%">
							序号
						</td>
						<td width="15%">
							参数类型代码
						</td>
						<td width="15%">
							参数名称
						</td>
						<td width="35%">
							参数值
						</td>
						<td width="20%">
							备注
						</td>
						<td>
							操作
						</td>
					</tr>
					<s:if test="pageList != null && pageList.size == 0">
						<tr>
							<td colspan="8">对不起，暂无数据！！！</td>
						</tr>
					</s:if>
					<s:else>
					<s:iterator value="pageList" status="st" id="p">
						<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
							<td>
								<s:property value="#st.getCount()" />
							</td>
							<td>
								<s:property value="code" />
							</td>
							<td>
								<s:property value="name" />
							</td>
							<td>
								<s:property value="value" />
							</td>
							<td>
								<s:property value="remark" />
							</td>
							<td align="center">
								<table border="0">
									<tr>
										<td>
											<div id="edit<s:property value="#st.count" />">
												<input type="button" value="" class="modify_btn" onmousemove="this.className='modify_btn1'"  onmouseout="this.className='modify_btn'" onclick="javaScript:window.self.location='parameter_detail.action?id=<s:property value="id"/>'">
											</div>
										</td>
										<td>
											<div id="del<s:property value="#st.count" />">
												<input type="button" value="" class="del_btn" onmousemove="this.className='del_btn1'" onmouseout="this.className='del_btn'" onclick="return del(<s:property value='id'/>)">
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</s:iterator>
					<s:if test="pageList != null && pageList.size>0">
						<tr class="pageTr">
							<td colspan="8">
								<s:url id="url_pre" value="parameter_search.action">
									<s:param name="pageIndex" value="pageIndex-1"></s:param>
									<s:param name="code" value="code"></s:param>
									<s:param name="name" value="name"></s:param>
									<s:param name="value" value="value"></s:param>
									<s:param name="value" value="effectiveDate"></s:param>
									<s:param name="value" value="expirationDate"></s:param>
								</s:url>
								<s:url id="url_next" value="parameter_search.action">
									<s:param name="pageIndex" value="pageIndex+1"></s:param>
									<s:param name="code" value="code"></s:param>
									<s:param name="name" value="name"></s:param>
									<s:param name="value" value="value"></s:param>
									<s:param name="value" value="effectiveDate"></s:param>
									<s:param name="value" value="expirationDate"></s:param>
								</s:url>
								<s:url id="url_first" value="parameter_search.action">
									<s:param name="pageIndex" value="1"></s:param>
									<s:param name="code" value="code"></s:param>
									<s:param name="name" value="name"></s:param>
									<s:param name="value" value="value"></s:param>
									<s:param name="value" value="effectiveDate"></s:param>
									<s:param name="value" value="expirationDate"></s:param>
								</s:url>
								<s:url id="url_last" value="parameter_search.action">
									<s:param name="pageIndex" value="pageCount"></s:param>
									<s:param name="code" value="code"></s:param>
									<s:param name="name" value="name"></s:param>
									<s:param name="value" value="value"></s:param>
									<s:param name="value" value="effectiveDate"></s:param>
									<s:param name="value" value="expirationDate"></s:param>
								</s:url>
								共<s:property value="total"/>条记录&nbsp;&nbsp;当前第<s:property value="pageIndex"/>页，共<s:property value="pageCount"/>页&nbsp;&nbsp;
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
								<input type="submit" value="转" onclick="return checkPageIndex(<s:property value='pageCount'/>)"/>
							</td>
						</tr>
					</s:if>
					</s:else>
				</table>
			</s:form>
		</div>
	</body>
</html>
