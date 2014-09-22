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
			$(function(){
				//var id = '<s:property value="customer"/>';
				$.get("<%=request.getContextPath()%>/system/customerSearch_init.action?time="+new Date().getTime(),{name:'CZ'},
					function(data){
						$("#customerList").html(data);
					});
			});
		</script>
		<script type="text/javascript">
			function del(id){
				if(confirm("确定删除吗?")){
					window.self.location='weixinMenuDelete.action?id='+id;
				}
			}
			function init() {
				hiddenBtn('system/weixinMenuEdit.action','edit');
				hiddenBtn('system/weixinMenuDelete.action','del');
				hiddenBtn('system/weixinMenuAdd.action','add');
			}
			function checkSession(empName,url){
				if(empName == null || empName == ""){
					window.self.location = url;
				}
			}
			function weixin(operation){
				var customer = $("#customer").val();
				if (customer == "") {
					alert("请选择商户");
					return;
				}
				window.self.location='weixinMenuSearch_operate.action?operation='+operation+'&customer='+customer
			}
		</script>		
		<title>微信菜单查询</title>
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微信关键字回复管理</font></span>
        </div>
    	<div class="main">
			<s:form action="weixinSelfMenuSearch" theme="simple" method="post" namespace="/system">
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr>
						<td class="th1" style="display: none">
							菜单类型:
						</td>
						<td align="left" width="35%" style="display: none">
							<s:select id="level" name="level" list="#{'3': '全部',0: '主菜单(无子菜单)', 1: '主菜单(有子菜单)', 2: '子菜单'}"></s:select>
						</td>
						<td class="th1" style="display: none">选择商户：</td>
					    <td width="35%" align="left" style="display: none"><span id="customerList"></span></td>
					</tr>
					<tr>
						<td class="th1" style="display: none">
							事件类型:
						</td>
						<td width="35%" align="left" style="display: none">
							<s:select id="type" name="type" list="#{'searchKey': '关键字','click': '点击事件'}"></s:select>
						</td>
						<td class="th1" style="display: none">
							菜单名称:
						</td>
						<td width="35%" align="left" style="display: none">
							<s:textfield id="name" name="name"></s:textfield>
						</td>
					</tr>
					<tr>
						<td class="th1">
							关键字词:
						</td>
						<td width="35%" align="left">
							<s:textfield id="keyId" name="keyId"></s:textfield>
						</td>
						<td class="th1">
							是否启用:
						</td>
						<td width="35%" align="left">
							<s:select id="state" name="state" list="#{'':'全部',0:'启用',1: '关闭'}" ></s:select>
						</td>
					</tr>
				</table>
				
				<table width="100%">
					<tr>
						<td align="right" width="50%">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'"></s:submit>
						</td>
						<td align="left" width="10%">
							<input type="button" class="add_btn" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'" value="" onclick="javascript:window.location.href='<%=request.getContextPath() %>/system/public/weixinSelfMenu/weixinSelfMenu_add.jsp'"/>
						</td>
						<td align="right" width="34%" style="color: red">
						</td>
						<td align="right" width="8%">
						</td>
						<td align="left" width="8%">
						</td>
					</tr>
				</table>
				
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr class="th">
						<td>
							序号
						</td>
						<td>
							关键字词
						</td>
						<td>
							业务名称
						</td>
						<td width="40%">
							回复信息
						</td>
						<td width="20%">
							上级业务
						</td>
						<td>
							是否启用
						</td>		
						<td width="10%">
							操作
						</td>
					</tr>
						<s:if test="menuList != null && menuList.size == 0">
							<tr>
								<td colspan="7">对不起，暂无数据！！！</td>
							</tr>
						</s:if>
						<s:else>
							<s:iterator value="menuList" status="st" id="da">
								<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
									<td align="center">
										<s:property value="#st.count" />
									</td>
									<td align="center">
										<s:if test="keyId==null || keyId==''">无</s:if>
										<s:else><s:property value="keyId" /></s:else>										
									</td>
									<td align="center">
										<s:property value="name" />										
									</td>
									<td align="center">
										<s:property value="content" />										
									</td>
									<td align="center">
										<s:property value="%{getLastName(subId)}"/>
									</td>
									<td align="center">
										<s:if test="state==0">启用</s:if>
										<s:elseif test="state==1">关闭</s:elseif>										
									</td>
									<td align="center">
										<table>
											<tr>
												<td align="right">
													<input type="button" value="" class="modify_btn" onmousemove="this.className='modify_btn1'" onmouseout="this.className='modify_btn'" onclick="javaScript:window.self.location='weixinSelfMenuDetail.action?id=<s:property value="id"/>'">
												</td>
												<td align="left">
													<input type="button" value="" class="del_btn" onmousemove="this.className='del_btn1'" onmouseout="this.className='del_btn'" onclick="del(<s:property value='id'/>)">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</s:iterator>
							<s:if test="menuList != null && menuList.size>0">
								<tr class="pageTr">
									<td colspan="7">
										<s:url id="url_pre" value="weixinSelfMenuSearch.action">
											<s:param name="pageIndex" value="pageIndex-1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="type" value="type"></s:param>
											<s:param name="keyId" value="keyId"></s:param>
											<s:param name="state" value="state"></s:param>
											<s:param name="level" value="level"></s:param>
										</s:url>
										<s:url id="url_next" value="weixinSelfMenuSearch.action">
											<s:param name="pageIndex" value="pageIndex+1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="type" value="type"></s:param>
											<s:param name="keyId" value="keyId"></s:param>
											<s:param name="state" value="state"></s:param>
											<s:param name="level" value="level"></s:param>
										</s:url>
										<s:url id="url_first" value="weixinSelfMenuSearch.action">
											<s:param name="pageIndex" value="1"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="type" value="type"></s:param>
											<s:param name="keyId" value="keyId"></s:param>
											<s:param name="state" value="state"></s:param>
											<s:param name="level" value="level"></s:param>
										</s:url>
										<s:url id="url_last" value="weixinSelfMenuSearch.action">
											<s:param name="pageIndex" value="pageCount"></s:param>
											<s:param name="name" value="name"></s:param>
											<s:param name="type" value="type"></s:param>
											<s:param name="keyId" value="keyId"></s:param>
											<s:param name="state" value="state"></s:param>
											<s:param name="level" value="level"></s:param>
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