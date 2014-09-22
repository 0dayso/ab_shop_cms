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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<title>回复格式查询</title>
		<script type="text/javascript">
			function del(id){
				if(confirm("确定删除吗?")){
					window.self.location='weixinType_delete.action?id='+id;
				}
			}
		</script>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微信回复格式管理</font></span>
        </div>
    	<div class="main">
			<s:form action="weixinType_search" theme="simple" method="post" namespace="/system">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">回复格式编码:</td>
						<td align="left"><s:textfield id="code" name="code" value="%{code}"></s:textfield></td>
						<td class="th1">回复格式名称:</td>
					    <td align="left"><s:textfield id="name" name="name" value="%{name}"></s:textfield></span></td>
					</tr>
					<tr>
						<td colspan="2" align="right">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'"></s:submit>
						</td>
						<td colspan="2" align="left">
							<input type="button" value="" class="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'"
								onclick="javascript:window.location.href='<%=basePath%>/system/weixinType/weixinType_view.action'" />
							</div>
						</td>
					</tr>
				</table>
				
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr class="th">
						<td>
							序号
						</td>
						<td>
							事件编码
						</td>
						<td>
							事件名称
						</td>
						<td width="10%">
							操作
						</td>
					</tr>
						<s:if test="pageList != null && pageList.size == 0">
							<tr>
								<td colspan="9">对不起，暂无数据！！！</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="pageList" status="st" id="da">
								<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
									<td align="center">
										<s:property value="#st.count" />
									</td>
									<td align="center">
										<s:property value="code" />
									</td>
									<td align="center">
										<s:property value="name" />
									</td>
									<td align="center">
										<table>
											<tr>
												<td align="right">
													<input type="button" value="" class="modify_btn" onmousemove="this.className='modify_btn1'" onmouseout="this.className='modify_btn'" onclick="javaScript:window.self.location='event_detail.action?id=<s:property value="id"/>'">
												</td>
												<td align="left">
													<input type="button" value="" class="del_btn" onmousemove="this.className='del_btn1'" onmouseout="this.className='del_btn'" onclick="del(<s:property value='id'/>)">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</s:iterator>
							<s:if test="pageList != null && pageList.size>0">
								<tr class="pageTr">
									<td colspan="10">
										<s:url id="url_pre" value="weixinType_search.action">
											<s:param name="pageIndex" value="pageIndex-1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="code" value="code"></s:param>
										</s:url>
										<s:url id="url_next" value="weixinType_search.action">
											<s:param name="pageIndex" value="pageIndex+1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="code" value="code"></s:param>
										</s:url>
										<s:url id="url_first" value="weixinType_search.action">
											<s:param name="pageIndex" value="1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="code" value="code"></s:param>
										</s:url>
										<s:url id="url_last" value="weixinType_search.action">
											<s:param name="pageIndex" value="pageCount"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="code" value="code"></s:param>
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