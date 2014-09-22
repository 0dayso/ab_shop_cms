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
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_attention.js"></script>
		<title>微信关注查询</title>
		<script type="text/javascript"> 
		   $(function(){
				//var id = '<s:property value="customer"/>';
				$.get("<%=request.getContextPath()%>/system/customerSearch_init.action?time="+new Date().getTime(),{name:'CZ'},
					function(data){
						$("#customerList").html(data);
					});
			});
		</script>
	</head>
	<body onload="">
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">微信关注回复</font></span>
        </div>
    	<div class="main">
			<s:form action="weixinAttentionSearch" theme="simple" method="post" namespace="/system">
				<table width="100%">
				    <tr>
						<td class="th1" align="left" style="display: none">选择商户：</td>
						<td width="190px" style="display: none"><span id="customerList"></span></td>
						<td align="center" width="100%">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'"></s:submit>
						</td>
					</tr>
				</table>
				<table class="tableborder" border="1" bordercolor="#dbdbdb">
					<tr class="th">
						<td  width="5%">
							序号
						</td>
						<td  width="10%">
							名称
						</td>						
						<td  width="77%">
							内容
						</td>
						<td width="8%">
							操作
						</td>
					</tr>
					<s:if test="weixinRespList != null && weixinRespList.size == 0">
						<tr>
							<td colspan="4">对不起，暂无数据！！！</td>
						</tr>
					</s:if>
					<s:else>
						<s:iterator value="weixinRespList" status="st" id="da">
							<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
								<td align="center">
									<s:property value="#st.count" />
								</td>
								<td align="center">
									<s:property value="title" />
								</td>									
								<td align="center">
									<s:property value="url" />	
								</td>
								<td align="center">
									<table>
										<tr>
											<td align="right">
												<input type="button" value="" class="modify_btn" onmousemove="this.className='modify_btn1'" onmouseout="this.className='modify_btn'" onclick="javaScript:window.self.location='weixinAttentionDetail.action?id=<s:property value="id"/>'">
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</s:iterator>
					</s:else>
				</table>
			</s:form>
		</div>
	</body>
</html>