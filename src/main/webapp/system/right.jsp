<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.rytong.entity.Customer"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
   Customer customer = (Customer)request.getSession().getAttribute("customer"); 
   String id = "";
   String url = "";
   String token = "";
   if(customer != null){
	   id = customer.getId().toString();
	   url = customer.getUrl();
	   token = customer.getToken();
   }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<title>个人中心</title>
			<link rel="shortcut icon" href="http://www.weduty.com/images/damotouicon.ico">
			<link href="../public/css/index.css" rel="stylesheet" type="text/css">
			<link href="../public/css/css.css" rel="stylesheet" type="text/css">
			<script type="text/javascript" src="js/woscaller.FloatB.min.js"></script>
			<script type="text/javascript" src="../public/js/jquery-1.8.3.js"></script>
			<script type="text/javascript" src="../public/js/busi_index.js"></script>
			<script type="text/javascript" src="../public/js/geturl.js"></script>
			<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
			<script type="text/javascript">
			    function editor(flag){
			    	var customerId = "<%=id %>";
			    	var type = flag;
			    	var value =$("#"+flag).val();
			    	$.ajax({
			    		url: "<%=request.getContextPath()%>/system/customer_editorByType.action",
			    		type: "post",
			    		dataType:"json",
			    		data:{"id":customerId, "type":type,"value":value},
			    		success: function(data){
			    			if(data.code == 'ok'){
			    				$(".picurl").tip({width:'240',status:'right',content:'编辑成功',dis_time:1000});
			    			}
			    		}
			    	})
			    	
			    }
			</script>
	</head>

	<body>
		<div id="MicroBox">
			<div style="clear: both; width: 100%; height: 0px;"></div>
			<div class="matter_box">
				<div class="matter">
					<div class="matter_wap">
							<div class="centent_r_btm">
								<div class="Overview maskshownull">

									<div class="OverviewCer">
										<div class="OverviewLt">
											<div class="OverviewLogo">
												<img style="transition-duration: 1s; width: 110px;"
													src="../public/images/41af86dcc0ddadb8777debc173c9dea7.jpg">
											</div>
											<div class="OverviewName">
												<span class=""> <em>欢迎您！ </span>
												<a class="Commerce" title="升级微商宝"></a>
											</div>
											<div class="OverviewTerm">
												<ul>
													<li style="border-bottom: 1px solid #b6b9c6;">
														<span> 接口URL:</span>
														<b></b>
														<label id="url"><%=url %></label>
													</li>
													<li>
														<span>TOKEN:</span>
														<b></b>
														<input type="text" id="token"  value="<%=token %>" onchange="editor('token')">
													</li>
												</ul>
											</div>
										</div>
										<div class="OverviewRt">
											<dl>
												<dt>
													昨日统计：
												</dt>
												<dd>
													<span>新增关注</span>
													<label style="background-color: #d2dfba">
														0
													</label>
												</dd>
												<dd>
													<span>关注总量</span>
													<label style="background-color: #f2cdba">
														6
													</label>
												</dd>
												<dd>
													<span>咨询人数</span>
													<label style="background-color: #bacee8">
														0
													</label>
												</dd>
												<dd>
													<span>微网站流量</span>
													<label style="background-color: #ecc1cf">
														0
													</label>
												</dd>
											</dl>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div style="opacity:0;  filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px; display:none;">
	       		<input type="file" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" class="picurl" name="mp3file" id="urlt_second_edt">
	       	</div>
	</body>
</html>