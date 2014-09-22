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
  			<span><font class="font1">当前位置:</font><font class="menuName_1">会员信息</font></span>
    	</div>
    	<div class="main">
			<form name="aspnetForm" method="post" action="" onsubmit="javascript:return WebForm_OnSubmit();" id="aspnetForm" enctype="multipart/form-data">
				<table style="line-height: 30px; border: 1px solid #ccc; padding: 10px; background: #f4f4f4; width: 100%;">
					<tr>
						<td style="font-size: 14px; font-weight: bold;">
							联系人：
						</td>
						<td rowspan="7">
							<table align="center"
								style="text-align: center; width: 100%; border: 1px solid #ccc;">
								<tr>
									<td>
										微信二维码
									</td>
								</tr>
								<tr>
									<td>
										<img id="ctl00_ContentPlaceHolder1_wxWeiXinPicUrl"
											src="http://www.18848.cn/files/photo/2013/7/30/d5b5944d93c2449da8b3a6345a8b3f09.jpg"
											style="border-width: 1px; border-style: solid; height: 200px; width: 200px;" />
									</td>
								</tr>
								<tr>
									<td style="text-align: center">
										<input type="file" name="ctl00$ContentPlaceHolder1$f1"
											id="ctl00_ContentPlaceHolder1_f1" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<input name="ctl00$ContentPlaceHolder1$wxContract" type="text"
								value="测试" id="ctl00_ContentPlaceHolder1_wxContract"
								class="wx-input-30" style="width: 250px;" />
							<span id="ctl00_ContentPlaceHolder1_RegularExpressionValidator5"
								style="color: Red; display: none;"><br />联系人名称必须在2-20字符之间</span>
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">
							联系电话：
						</td>
					</tr>
					<tr>
						<td>
							<input name="ctl00$ContentPlaceHolder1$wxPhone" type="text"
								id="ctl00_ContentPlaceHolder1_wxPhone" class="wx-input-30"
								style="width: 250px;" />
							<span id="ctl00_ContentPlaceHolder1_RegularExpressionValidator4"
								style="color: Red; display: none;"><br />请输入正确的联系电话</span>
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;">
							手机号码：
						</td>
					</tr>
					<tr>
						<td>
							<input name="ctl00$ContentPlaceHolder1$wxMobile" type="text"
								value="13925119872" id="ctl00_ContentPlaceHolder1_wxMobile"
								class="wx-input-30" style="width: 250px;" />
							<span id="ctl00_ContentPlaceHolder1_RegularExpressionValidator1"
								style="color: Red; display: none;"><br />请输入正确的手机号码</span>
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;" colspan="2">
							联系地址：
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input name="ctl00$ContentPlaceHolder1$wxAddress" type="text"
								value="北京市海淀区" id="ctl00_ContentPlaceHolder1_wxAddress"
								class="wx-input-30" style="width: 500px;" />
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;" colspan="2">
							QQ号码：
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input name="ctl00$ContentPlaceHolder1$wxQQ" type="text"
								id="ctl00_ContentPlaceHolder1_wxQQ" class="wx-input-30"
								style="width: 500px;" />
							<span id="ctl00_ContentPlaceHolder1_RegularExpressionValidator2"
								style="color: Red; display: none;"><br />请输入正确的QQ号码</span>
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;" colspan="2">
							电子邮件：
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input name="ctl00$ContentPlaceHolder1$wxEmail" type="text"
								value="20448404@qq.com" id="ctl00_ContentPlaceHolder1_wxEmail"
								disabled="disabled" class="wx-input-30" style="width: 500px;" />
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;" colspan="2">
							网站地址：
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input name="ctl00$ContentPlaceHolder1$wxWebSite" type="text"
								id="ctl00_ContentPlaceHolder1_wxWebSite" class="wx-input-30"
								style="width: 500px;" />
							<span id="ctl00_ContentPlaceHolder1_RegularExpressionValidator3"
								style="color: Red; display: none;"><br />请输入正确的网址</span>
						</td>
					</tr>
					<tr>
						<td style="font-size: 14px; font-weight: bold;" colspan="2">
							公众账号描述：
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<div>
								<input type="hidden"
									id="ctl00_ContentPlaceHolder1_wxDescription"
									name="ctl00$ContentPlaceHolder1$wxDescription" value="" />
								<input type="hidden"
									id="ctl00_ContentPlaceHolder1_wxDescription___Config"
									value="HtmlEncodeOutput=true" />
								<textarea rows="7" cols="68"></textarea>
							</div>
						</td>
					</tr>
					<tr>
						<td style="height: 30px" colspan="2">
							<hr />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="submit" name="ctl00$ContentPlaceHolder1$btnOK"
								value="保存信息"
								onclick="javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions(&quot;ctl00$ContentPlaceHolder1$btnOK&quot;, &quot;&quot;, true, &quot;&quot;, &quot;&quot;, false, false))"
								id="ctl00_ContentPlaceHolder1_btnOK" class="btn" />
						</td>
					</tr>
				</table>

				<script type="text/javascript">
//<![CDATA[
var Page_Validators =  new Array(document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator5"), document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator4"), document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator1"), document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator2"), document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator3"));
//]]>
</script>

				<script type="text/javascript">
//<![CDATA[
var ctl00_ContentPlaceHolder1_RegularExpressionValidator5 = document.all ? document.all["ctl00_ContentPlaceHolder1_RegularExpressionValidator5"] : document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator5");
ctl00_ContentPlaceHolder1_RegularExpressionValidator5.controltovalidate = "ctl00_ContentPlaceHolder1_wxContract";
ctl00_ContentPlaceHolder1_RegularExpressionValidator5.errormessage = "<br />联系人名称必须在2-20字符之间";
ctl00_ContentPlaceHolder1_RegularExpressionValidator5.display = "Dynamic";
ctl00_ContentPlaceHolder1_RegularExpressionValidator5.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
ctl00_ContentPlaceHolder1_RegularExpressionValidator5.validationexpression = "[\\w\\W]{2,20}";
var ctl00_ContentPlaceHolder1_RegularExpressionValidator4 = document.all ? document.all["ctl00_ContentPlaceHolder1_RegularExpressionValidator4"] : document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator4");
ctl00_ContentPlaceHolder1_RegularExpressionValidator4.controltovalidate = "ctl00_ContentPlaceHolder1_wxPhone";
ctl00_ContentPlaceHolder1_RegularExpressionValidator4.errormessage = "<br />请输入正确的联系电话";
ctl00_ContentPlaceHolder1_RegularExpressionValidator4.display = "Dynamic";
ctl00_ContentPlaceHolder1_RegularExpressionValidator4.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
ctl00_ContentPlaceHolder1_RegularExpressionValidator4.validationexpression = "\\d{3}-\\d{8}|\\d{4}-\\d{7}";
var ctl00_ContentPlaceHolder1_RegularExpressionValidator1 = document.all ? document.all["ctl00_ContentPlaceHolder1_RegularExpressionValidator1"] : document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator1");
ctl00_ContentPlaceHolder1_RegularExpressionValidator1.controltovalidate = "ctl00_ContentPlaceHolder1_wxMobile";
ctl00_ContentPlaceHolder1_RegularExpressionValidator1.errormessage = "<br />请输入正确的手机号码";
ctl00_ContentPlaceHolder1_RegularExpressionValidator1.display = "Dynamic";
ctl00_ContentPlaceHolder1_RegularExpressionValidator1.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
ctl00_ContentPlaceHolder1_RegularExpressionValidator1.validationexpression = "1[0-9]{10}";
var ctl00_ContentPlaceHolder1_RegularExpressionValidator2 = document.all ? document.all["ctl00_ContentPlaceHolder1_RegularExpressionValidator2"] : document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator2");
ctl00_ContentPlaceHolder1_RegularExpressionValidator2.controltovalidate = "ctl00_ContentPlaceHolder1_wxQQ";
ctl00_ContentPlaceHolder1_RegularExpressionValidator2.errormessage = "<br />请输入正确的QQ号码";
ctl00_ContentPlaceHolder1_RegularExpressionValidator2.display = "Dynamic";
ctl00_ContentPlaceHolder1_RegularExpressionValidator2.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
ctl00_ContentPlaceHolder1_RegularExpressionValidator2.validationexpression = "[0-9]{5,12}";
var ctl00_ContentPlaceHolder1_RegularExpressionValidator3 = document.all ? document.all["ctl00_ContentPlaceHolder1_RegularExpressionValidator3"] : document.getElementById("ctl00_ContentPlaceHolder1_RegularExpressionValidator3");
ctl00_ContentPlaceHolder1_RegularExpressionValidator3.controltovalidate = "ctl00_ContentPlaceHolder1_wxWebSite";
ctl00_ContentPlaceHolder1_RegularExpressionValidator3.errormessage = "<br />请输入正确的网址";
ctl00_ContentPlaceHolder1_RegularExpressionValidator3.display = "Dynamic";
ctl00_ContentPlaceHolder1_RegularExpressionValidator3.evaluationfunction = "RegularExpressionValidatorEvaluateIsValid";
ctl00_ContentPlaceHolder1_RegularExpressionValidator3.validationexpression = "[a-zA-z]+://[^\\s]*";
//]]>
</script>


				<script type="text/javascript">
//<![CDATA[

var Page_ValidationActive = false;
if (typeof(ValidatorOnLoad) == "function") {
    ValidatorOnLoad();
}

function ValidatorOnSubmit() {
    if (Page_ValidationActive) {
        return ValidatorCommonOnSubmit();
    }
    else {
        return true;
    }
}
        //]]>
</script>
			</form>

		</div>
	</body>
</html>
