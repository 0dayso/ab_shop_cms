<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<script type="text/javascript">
		function checkSession(empName,url){
			if(empName == null || empName == ""){
				window.self.location = url;
			}
		}
		function del(id){
		}
		function init() {
			hiddenBtn('system/weixinRespEdit.action','edit');
			hiddenBtn('system/weixinRespDelete.action','del');
			hiddenBtn('system/weixinRespAdd.action','add');
		}
		</script>		
		<title>微信菜单回复查询</title>
	</head>
	<body onload="init();">
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1">微信用户</font></span>
    	</div>
    	<div class="main">
			<s:form action="weixinUser_search" theme="simple" method="post" namespace="/system">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1">
							用户名称：
						</td>
						<td width="35%" align="left">
							<s:textfield id="name" name="name"></s:textfield>
						</td>
						<td class="th1">
							手机号码：
						</td>
						<td width="35%" align="left">
						    <s:textfield id="mobile" name="mobile"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="th1">证件号码：</td>
						<td width="35%" align="left">
							<s:textfield id="idNo" name="idNo"></s:textfield>
						</td>
						<td class="th1">电子邮件：</td>
						<td width="35%" align="left">
							<s:textfield id="email" name="email"></s:textfield>
						</td>
					</tr>
				</table>
				
				<table width="100%">
					<tr>
						<td align="right" width="50%">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'"></s:submit>
						</td>
						<td align="left" width="50%">
							<!-- <input type="button" class="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'" value="" onclick="javascript:window.location.href='<%=request.getContextPath() %>/system/public/weixinUser/weixinUser_add.jsp'"/> -->
						</td>
					</tr>
				</table>
				
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr class="th">
						<td>
							序号
						</td>
						<td>
							名字
						</td>						
						<td>
							微信号
						</td>
						<td>
							手机号码
						</td>
						<td>
							电子邮件
						</td>
						<td>
						        最后登录时间
						</td>
						<td>
						         操作
						</td>
					</tr>
						<s:if test="pageList != null && pageList.size() == 0">
							<tr>
								<td colspan="7">对不起，暂无数据！！！</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="pageList" status="st" id="da">
								<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
									<td align="center">
										<s:property value="#st.count" />
									</td>
									<td align="center">
										<s:property value="name" />
									</td>									
									<td align="center">
									    <s:property value="weixinNo"/>
									</td>
									<td align="center">
										<s:property value="mobile" />									
									</td>
									<td align="center">
										<s:property value="email" />					
									</td>
									<td align="center">
									    <s:property value="lastLoginTime"/>
									</td>
									<td align="center">
										<table>
											<tr style="display: none">
												<td align="right">
													<input type="button" value="" class="modify_btn" onmousemove="this.className='modify_btn1'" onmouseout="this.className='modify_btn'" onclick="javaScript:window.self.location='weixinRespDetail.action?id=<s:property value="id"/>'">
												</td>
												<td align="left">
													<input type="button" value="" class="del_btn" onmousemove="this.className='del_btn1'" onmouseout="this.className='del_btn'" onclick="del(<s:property value='id'/>)">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</s:iterator>
							<s:if test="userList != null && userList.size>0">
								<tr class="pageTr">
									<td colspan="7">
										<s:url id="url_pre" value="weixinUserSearchAction.action">
											<s:param name="pageIndex" value="pageIndex-1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="idNo" value="idNo"></s:param>
											<s:param name="mobile" value="mobile"></s:param>
											<s:param name="email" value="email"></s:param>
										</s:url>
										<s:url id="url_next" value="weixinUserSearchAction.action">
											<s:param name="pageIndex" value="pageIndex+1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="idNo" value="idNo"></s:param>
											<s:param name="mobile" value="mobile"></s:param>
											<s:param name="email" value="email"></s:param>
										</s:url>
										<s:url id="url_first" value="weixinUserSearchAction.action">
											<s:param name="pageIndex" value="1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="idNo" value="idNo"></s:param>
											<s:param name="mobile" value="mobile"></s:param>
											<s:param name="email" value="email"></s:param>
										</s:url>
										<s:url id="url_last" value="weixinUserSearchAction.action">
											<s:param name="pageIndex" value="pageCount"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="idNo" value="idNo"></s:param>
											<s:param name="mobile" value="mobile"></s:param>
											<s:param name="email" value="email"></s:param>
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