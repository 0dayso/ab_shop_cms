<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'permission_search.jsp' starting page</title>
    <link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=path%>/public/js/jquery-1.4.js"></script>
	<script type="text/javascript" src="<%=path%>/public/js/public/check_permission.js"></script>
    <script type="text/javascript" src="<%=path%>/public/js/hidden.js"></script>
    <script type="text/javascript" src="<%=path%>/public/js/page.js"></script>
    <script type="text/javascript">
       function del(id){
			if(confirm("确定删除吗?")){
				window.self.location='<%=request.getContextPath() %>/system/conference_delete.action?id='+id;
			}
		}
    </script>
    
  </head>
  
  <body onload="init()">
  	<div class="headerPart">
			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
			<span><font class="font1">当前位置:</font><font class="menuName_1">微信维护 - 会议栏目</font></span>
   	</div>
    <div class="main">
	    <s:form action="conference_search" method="post" theme="simple" namespace="/system">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							栏目名称:
						</td>
						<td width="50%" align="left">
							<s:textfield id="name" name="name"></s:textfield>
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'"></s:submit>
							<input type="button" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'" onclick="javascript:window.location.href='<%=request.getContextPath() %>/system/public/page/page_add.jsp'" class="add_btn" value=""/>
						</td>
					</tr>
				</table>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr class="th">
						<td width="10%">
							序号
						</td>
						<td width="35%">
							会议标题
						</td>
						<td width="35%">
							栏目名称
						</td>
						<td width="20%">操作</td>
					</tr>
					<s:if test="%{pageList.size == 0}">
						<tr>
							<td colspan="5">
								对不起,暂无数据！
							</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="pageList" status="st" id="b">
							<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
								<td>
									<s:property value="#st.count" />
								</td>
								<td>
									<s:property value="#b.conference.title"/>
								</td>
								<td>
									<s:property value="#b.name"/>
								</td>
								<td align="center">
									<table>
										<tr>
											<td align="left">
												<div id="del<s:property value='#st.count' />">
													<input type="button" value="" onmousemove="this.className='del_btn1'" onmouseout="this.className='del_btn'"  class="del_btn" onclick="return del(<s:property value='id'/>)">
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</s:iterator>
					</s:else>
					<s:if test="%{pageList.size > 0}">
						<tr class="pageTr">
							<td colspan="5">
								<s:url id="url_pre" value="/system/conference_search.action">
									<s:param name="pageIndex" value="pageIndex-1"></s:param>
									<s:param name="name" value="name"></s:param>
								</s:url>
								<s:url id="url_next" value="/system/conference_search.action">
									<s:param name="pageIndex" value="pageIndex+1"></s:param>
									<s:param name="name" value="name"></s:param>
								</s:url>
								<s:url id="url_first" value="/system/conference_search.action">
									<s:param name="pageIndex" value="1"></s:param>
									<s:param name="name" value="name"></s:param>
								</s:url>
								<s:url id="url_last" value="/system/conference_search.action">
									<s:param name="pageIndex" value="pageCount"></s:param>
									<s:param name="name" value="name"></s:param>
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
				</table>
			</s:form>
		</div>
  </body>
</html>
