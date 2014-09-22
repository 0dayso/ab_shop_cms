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
		<title>网站彩票管理-彩票信息</title>
		<script type="text/javascript">
		   	function init() {
		   		if(confirm("确定同步吗?")){
		   			$("body").mask("正在计算，这个过程可能需要几分钟，请您耐心等待...");
		   			$.get("<%=request.getContextPath()%>/system/lottery_init.action?time="+new Date().getTime(),{},
   						function(data){
		   					$("body").unmask();
   							$("#lottery_search").submit();
   						}
		   			);
				}
		   	}
		   	
		   	$(function(){
		   		var issue = "<s:property value='issue'/>";
		   		$.get("<%=request.getContextPath()%>/system/issue_init.action?time="+new Date().getTime(),{issue:issue},
					function(data){
						$("#issueList").html(data);
					}
		   		);
		   	});
		   	
		   	function initAlay() {
		   		$("body").mask("loading...");
		   		$.ajax({
			 		url:"<%=request.getContextPath()%>/system/lottery_countBall.action?time="+new Date().getTime(),
				  	type:"post",dataType:"json",
				  	timeout:1000000,
				  	data:{issue:$("#issue").val(),fiterNmuber:$("#fiterNmuber").val(), containNumber:$("#containNumber").val()},
				  	success:function(data){
				  		$("#rate-f-left1").html(data.ratef1);
				  		$("#rate-f-left2").html(data.ratef2);
				  		$("#rate-f-left3").html(data.ratef3);
				  		$("#rate-f-left4").html(data.ratef4);
				  		$("#rate-f-left5").html(data.ratef5);
				  		
				  		$("#rate-c-left1").html(data.ratec1);
				  		$("#rate-c-left2").html(data.ratec2);
				  		$("#rate-c-left3").html(data.ratec3);
				  		$("#rate-c-left4").html(data.ratec4);
				  		$("#rate-c-left5").html(data.ratec5);
				  		$("#rate-c-left6").html("总 " + data.total + " 注");
				  		$("body").unmask();
					}
				});
		   	}
		   	
		   	function kxNum() {
		   		$("body").mask("loading...");
		   		$.ajax({
			 		url:"<%=request.getContextPath()%>/system/lottery_totalKxBallNum.action?time="+new Date().getTime(),
				  	type:"post",dataType:"json",
				  	timeout:1000000,
				  	data:{issue:$("#issue").val(), fiterNmuber:$("#fiterNmuber").val(), containNumber:$("#containNumber").val()},
				  	success:function(data){
				  		$("#rate-kx-num").html("优选 " + data.total + " 注");
				  		$("#rate-n-left1").html(data.raten1);
				  		$("#rate-n-left2").html(data.raten2);
				  		$("#rate-n-left3").html(data.raten3);
				  		$("#rate-n-left4").html(data.raten4);
				  		$("#rate-n-left5").html(data.raten5);
				  		$("body").unmask();
					}
				});
		   	}
		   	
		   	function quchong() {
		   		$("body").mask("loading...");
		   		$.ajax({
			 		url:"<%=request.getContextPath()%>/system/lottery_deleteRepeated.action?time="+new Date().getTime(),
				  	type:"post",dataType:"json",
				  	timeout:1000000,
				  	data:{issue:$("#issue").val()},
				  	success:function(data){
				  		alert("消去重复 " + data.num + " 注");
				  		$("body").unmask();
					}
				});
		   	}
		   	
		   	function daoru() {
		   		$("body").mask("loading...");
		   		$.ajax({
			 		url:"<%=request.getContextPath()%>/system/lottery_daoru.action?time="+new Date().getTime(),
				  	type:"post",dataType:"json",
				  	timeout:1000000,
				  	data:{issue:$("#issue").val()},
				  	success:function(data){
				  		alert("导入数据 " + data.oneNum + ",消去重复 " + data.delNum + " 注,更新数据 " + data.upNum);
				  		$("body").unmask();
					}
				});
		   	}
		   	daoru
		</script>
	</head>
	<body>
		<div  class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">网站彩票管理-彩票信息</font></span>
    	</div>
    	<div class="main">
		<s:form action="lottery_search" method="post" theme="simple" namespace="/system" id="lottery_search">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">期号</td>
					<td align="left"><span id="issueList"></td>
					<td class="th1">来源</td>
					<td align="left"><s:select list="#{'':'全部', '1' :'淘宝'}" id="souce" name="souce"></s:select></td>
				</tr>
				<tr>
					<td class="th1">滤号</td>
					<td align="left"><s:textfield id="fiterNmuber" name="fiterNmuber"></s:textfield>逗号分隔</td>
					<td class="th1">含号</td>
					<td align="left"><s:textfield id="containNumber" name="containNumber"></s:textfield>逗号分隔</td>
				</tr>
				<tr>
					<td align="center" colspan="4">
						<div id="add">
							<s:submit value="" cssClass="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'"></s:submit>
							<input type="button" class="business_btn" value="统计"  onclick="initAlay();"/>
							<input type="button" class="business_btn" value="选号"  onclick="kxNum();"/>
							<input type="button" class="business_btn" value="同步"  onclick="init();"/>
							<input type="button" class="business_btn" value="导入"  onclick="daoru();"/>
							<input type="button" class="business_btn" value="去重"  onclick="quchong();"/>
						</div>
					</td>
				</tr>
			</table>
			<br>
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<caption style="padding-left:10px; color:red; font-weight: bold;border-style: dashed;">投注统计简单分析</caption>
				<tr>
					<td class="th1">排序比例</td>
					<td align="left" id="rate-f-left1">...</td>
					<td align="left" id="rate-f-left2">...</td>
					<td align="left" id="rate-f-left3">...</td>
					<td align="left" id="rate-f-left4">...</td>
					<td align="left" id="rate-f-left5">...</td>
					<td align="left" id="rate-f-left6">...</td>
				</tr>
				<tr>
					<td class="th1">正序比例</td>
					<td align="left" id="rate-c-left1">...</td>
					<td align="left" id="rate-c-left2">...</td>
					<td align="left" id="rate-c-left3">...</td>
					<td align="left" id="rate-c-left4">...</td>
					<td align="left" id="rate-c-left5">...</td>
					<td align="left" id="rate-c-left6">...</td>
				</tr>
				<tr>
					<td class="th1">备选号码</td>
					<td align="left" id="rate-n-left1">...</td>
					<td align="left" id="rate-n-left2">...</td>
					<td align="left" id="rate-n-left3">...</td>
					<td align="left" id="rate-n-left4">...</td>
					<td align="left" id="rate-n-left5">...</td>
					<td align="left" id="rate-kx-num">...</td>
				</tr>
			</table>
		
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr class="th">
					<td>序号</td>
					<td>投注号码</td>
					<td>期号</td>
					<td>数量</td>
					<td>身份</td>
					<td>来源</td>
				</tr>
				<s:if test="pageList.size == 0">
					<tr>
						<td colspan="6">对不起，暂无数据！！！</td>
					</tr>
				</s:if>
				<s:else>
				<s:iterator value="pageList" status="st" id="e">
					<tr class="<s:if test="#st.even">row-even</s:if><s:else>row-odd</s:else>">
						<td><s:property value="#st.count"/></td>
						<td><s:property value="betNumber"/></td>
						<td><s:property value="issue"/></td>
						<td><s:property value="num"/></td>
						<td><s:property value="uid"/></td>
						<td>
							<s:if test='souce=="1"'>淘宝</s:if>
							<s:else>其它</s:else>
						</td>
					</tr>
				</s:iterator>
					<s:if test="pageList != null && pageList.size>0">
						<tr class="pageTr">
							<td colspan="13">
								<s:url id="url_pre" value="lottery_search.action">
									<s:param name="pageIndex" value="pageIndex-1"></s:param>
									<s:param name="issue" value="issue"></s:param>
									<s:param name="souce" value="souce"></s:param>
									<s:param name="fiterNmuber" value="fiterNmuber"></s:param>
									<s:param name="containNumber" value="containNumber"></s:param>
								</s:url>
								<s:url id="url_next" value="lottery_search.action">
									<s:param name="pageIndex" value="pageIndex+1"></s:param>
									<s:param name="issue" value="issue"></s:param>
									<s:param name="souce" value="souce"></s:param>
									<s:param name="fiterNmuber" value="fiterNmuber"></s:param>
									<s:param name="containNumber" value="containNumber"></s:param>
								</s:url>
								<s:url id="url_first" value="lottery_search.action">
									<s:param name="pageIndex" value="1"></s:param>
									<s:param name="issue" value="issue"></s:param>
									<s:param name="souce" value="souce"></s:param>
									<s:param name="fiterNmuber" value="fiterNmuber"></s:param>
									<s:param name="containNumber" value="containNumber"></s:param>
								</s:url>
								<s:url id="url_last" value="lottery_search.action">
									<s:param name="pageIndex" value="pageCount"></s:param>
									<s:param name="issue" value="issue"></s:param>
									<s:param name="souce" value="souce"></s:param>
									<s:param name="fiterNmuber" value="fiterNmuber"></s:param>
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