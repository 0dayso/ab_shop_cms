<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/jquery.loadmask.css" rel="stylesheet" type="text/css" />
		<SCRIPT type="text/javascript" src="<%=request.getContextPath() %>/public/js/My97DatePicker/WdatePicker.js"></SCRIPT>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_employee.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/dateTime.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery.loadmask.min.js"></script>
		<title>网站彩票管理-购买彩票</title>
		<script type="text/javascript">
		   	function init() {
		   		$("body").mask("loading...");
		   		$.ajax({
			 		url:"<%=request.getContextPath()%>/system/lotteryBuy_updateNum.action?time="+new Date().getTime(),
				  	type:"post",dataType:"json",
				  	timeout:1000000,
				  	data:{issue:$("#issue").val()},
				  	success:function(data){
				  		alert("导入数据 " + data.oneNum + ",消去重复 " + data.delNum + " 注,更新数据 " + data.upNum);
				  		$("body").unmask();
					}
				});
		   	}
			
		   	$(function(){
		   		$.get("<%=request.getContextPath()%>/system/issue_init.action?time="+new Date().getTime(),{},
					function(data){
						$("#issueList").html(data);
					}
		   		);
		   	});
		</script>
	</head>
	<body>
		<div  class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">网站彩票管理-购买彩票</font></span>
    	</div>
    	<div class="main">
		<s:form action="lotteryBuy_search" method="post" theme="simple" namespace="/system" id="lottery_search">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">期号</td>
					<td align="left"><span id="issueList"></td>
					<td class="th1">含号</td>
					<td align="left"><s:textfield id="containNumber" name="containNumber"></s:textfield>逗号分隔</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<div id="add">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'"></s:submit>
							<input type="button" class="business_btn" value="更新号码"  onclick="init();"/>
						</div>
					</td>
				</tr>
			</table>
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr class="th">
					<td>序号</td>
					<td>期号</td>
					<td>号码</td>
				</tr>
				<s:if test="pageList.size == 0">
					<tr>
						<td colspan="3">对不起，暂无数据！！！</td>
					</tr>
				</s:if>
				<s:else>
				<s:iterator value="pageList" status="st" id="e">
					<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
						<td><s:property value="#st.count"/></td>
						<td><s:property value="issue"/></td>
						<td><s:property value="number"/></td>
					</tr>
				</s:iterator>
					<s:if test="pageList != null && pageList.size>0">
						<tr class="pageTr">
							<td colspan="13">
								<s:url id="url_pre" value="lotteryBuy_search.action">
									<s:param name="pageIndex" value="pageIndex-1"></s:param>
									<s:param name="issue" value="issue"></s:param>
									<s:param name="containNumber" value="containNumber"></s:param>
								</s:url>
								<s:url id="url_next" value="lotteryBuy_search.action">
									<s:param name="pageIndex" value="pageIndex+1"></s:param>
									<s:param name="issue" value="issue"></s:param>
									<s:param name="containNumber" value="containNumber"></s:param>
								</s:url>
								<s:url id="url_first" value="lotteryBuy_search.action">
									<s:param name="pageIndex" value="1"></s:param>
									<s:param name="issue" value="issue"></s:param>
									<s:param name="containNumber" value="containNumber"></s:param>
								</s:url>
								<s:url id="url_last" value="lotteryBuy_search.action">
									<s:param name="pageIndex" value="pageCount"></s:param>
									<s:param name="issue" value="issue"></s:param>
									<s:param name="containNumber" value="containNumber"></s:param>
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