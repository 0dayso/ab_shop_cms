<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="icon" href="<%=request.getContextPath()%>/public/images/<%=request.getSession().getAttribute("logoIco")%>" type="image/x-icon" />
		<link rel="shortcut icon" href="<%=request.getContextPath()%>/public/images/<%=request.getSession().getAttribute("logoIco")%>" type="image/x-icon" />
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/kindEditor.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/picturecss.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/wxmuban/css/index.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/kindeditor-min.js"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/zh_CN.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/public/check_customer.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/page.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/ajaxfileupload.js"></script>
		<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
		<title>商家优惠券</title>
		<STYLE type="text/css">
			.CouponSten{ float:left; width:780px;}
			.CouponSten li{ width:236px; border:1px solid #fff; margin:25px 0 0 18px; display:inline; float:left; height:398px;}
			.CouponSten li .CouponTip{ float:left; width:236px; height:20px; line-height:20px; text-align:center; color:#ff0000; margin-top:10px; visibility:hidden;}
			.CouponSten li .CouponTitle{ float:left; width:236px; height:20px; line-height:20px; text-align:center; font-weight:bold; margin-bottom:10px; overflow:hidden;}
			.CouponSten li .CouponAcer{ float:left; width:210px; height:312px; padding:2px; border:1px solid #bebebe; margin:10px; display:inline; position:relative;}
			.CouponSten li .CouponAcer a{ float:left;}
			.CouponSten li.CouponSten_li .CouponAcer{ margin:9px;}
			.CouponSten li.CouponSten_li .CouponTip{width:234px; margin-top:9px;}
			.CouponSten li.CouponSten_li .CouponTitle{width:234px; margin-bottom:9px;}
			.CouponSten li .MaskBox{ position:absolute; width:210px; height:312px; left:2px; top:2px; display:none;}
			.CouponSten li .MaskBox .MaskBg{ behavior: url("<%=request.getContextPath()%>/public/images/coupon/PIE.htc"); filter: alpha(opacity=70);-moz-opacity: 0.7; opacity:0.7; background-color:#333; width:210px; height:312px; position:absolute; left:0; top:0;}
			.CouponSten li .MaskBox .MaskCon{ position:absolute; left:0; top:120px; width:210px;text-align:center; font-family:"寰蒋闆呴粦"; font-size:24px; color:#fff; font-weight:bold;}
			.couponSten_li{border:1px solid #ccc !important;}
			
			.centent_r_btm {
			    background-color: #FFFFFF;
			    float: left;
			    margin-top: -1px;
			    min-height: 370px;
			    padding-bottom: 48px;
			    position: relative;
			    width: 780px;
			}
	
			.voucherBox{ float:left; width:730px; margin:20px 25px; display:inline-block;}
			.voucherBox .voucherTop{ float:left; width:730px; padding-bottom:20px; border-bottom:1px dotted #7d7d7d; margin-bottom:30px;}
			.voucherBox .voucherTop h1{ float:left; position:relative; width:730px; margin:12px 0; border-bottom:1px dotted #7d7d7d; height:1px;}
			.voucherBox .voucherTop h1 span{ position:absolute; width:56px; padding:0 8px; height:24px; background-color:#fff; right:23px; top:-11px;}
			.voucherBox .voucherTop .voucherTopBtn{ float:left; width:56px; height:24px; cursor:pointer; background-image:url(<%=request.getContextPath()%>/public/images/coupon/voucherTopBtn.gif); color:#fff;}
			.voucherBox .voucherTop ul{ float:left; width:730px;}
			.voucherBox .voucherTop ul li{ float:left; line-height:23px; margin-top:10px; width:730px;}
			.voucherBox .voucherTop ul li span{ float:left; width:90px; text-align:right;}
			.voucherBox .voucherTop ul li em{ float:left; font-style:normal; color:#a7a7a7; margin-left:10px; display:inline;}
			.voucherBox .voucherTop ul li label{ float:left;}
			.voucherBox .voucherTop .voucherTopInp{ float:left; width:150px; padding:3px; border:1px solid #aebac0;}

			.voucherBox .voucherLt{ float:left; width:320px; height:475px;}
			.voucherLt .Template{float:left; width:260px; height:475px; padding:0 30px;}
			.voucherLt .Template1{ background-image:url(<%=request.getContextPath()%>/public/images/coupon/Template1.jpg);} 
			.voucherLt .Template2{ background-image:url(<%=request.getContextPath()%>/public/images/coupon/Template2.jpg);} 
			.voucherLt .Template3{ background-image:url(<%=request.getContextPath()%>/public/images/coupon/Template3.jpg);} 
			.voucherLt .Template4{ background-image:url(<%=request.getContextPath()%>/public/images/coupon/Template4.jpg);} 
			.voucherLt .Template5{ background-image:url(<%=request.getContextPath()%>/public/images/coupon/Template5.jpg);} 
			.voucherLt .Template6{ background-image:url(<%=request.getContextPath()%>/public/images/coupon/Template6.jpg);} 
			.Template .TemplateTitle{ float:left; width:260px; height:48px; margin:52px 0 43px 0; display:inline; position:relative;}
			.Template .TemplateTitle strong{ float:left; width:260px; font-size:36px; line-height:48px; text-align:center; color:#e4e4e4;text-shadow: -1px 1px 3px #000; font-family:"微软雅黑";}
			.Template2 .TemplateTitle strong{ color:#201300; text-shadow: -1px 1px 2px #62451E;}
			.Template4 .TemplateTitle strong{ color:#201300; text-shadow: -1px 1px 2px #62451E;}
			.Template .TemplateTitle .TemplateTitleOver{ position:absolute; left:0; top:0; width:258px; height:46px; line-height:46px; text-align:center; border:1px solid #c4c4c4; background-color:#f1f1f1; font-family:"微软雅黑"; font-size:18px; color:#9a9a9a; cursor:pointer; display:none;}
			.Template .TemplateTp{ float:left; width:260px; height:100px; position:relative; margin-bottom:20px;}
			.Template .TemplateTp strong{ float:left; width:260px; height:100px;}
			.Template .TemplateTp .TemplateTpOver{ position:absolute; left:0; top:0; width:258px; height:98px; line-height:98px; text-align:center; border:1px solid #c4c4c4; background-color:#f1f1f1; font-family:"微软雅黑"; font-size:18px; color:#9a9a9a; cursor:pointer; display:none;}
			.Template .TemplateCard{ float:left; width:260px; height:25px; line-height:25px; text-align:center; font-family:Arial; font-size:14px; margin-bottom:20px;}
			.Template .TemplateCard1{ background-color:#dd8f89; color:#512400;}
			.Template .TemplateCard2{ background-color:#afa598; color:#4f402b;}
			.Template .TemplateCard3{ background-color:#000; color:#9b9b9b;}
			.Template .TemplateCard4{ background-color:#baa278; color:#4f402b;}
			.Template .TemplateCard5{ background-color:#940a15; color:#ffa5a5;}
			.Template .TemplateCard6{ background-color:#296e00; color:#d0ff13;}
			.Template .TemplateCon{ float:left; width:260px; height:130px; position:relative;}
			.Template .TemplateCon .TemplateConTop{ float:left; line-height:25px; height:130px; width:260px; overflow:hidden;}
			.Template .TemplateCon .TemplateConTop span{ float:left; font-family:"微软雅黑"; font-size:15px; margin-right:10px;}
			.Template .TemplateCon .TemplateConTop label{font-family:"微软雅黑"; font-size:15px;}
			.Template .TemplateCon1 .TemplateConTop span{ color:#fff;}
			.Template .TemplateCon1 .TemplateConTop label{ color:#9b9b9b;}
			.Template .TemplateCon2 .TemplateConTop span{ color:#372101;}
			.Template .TemplateCon2 .TemplateConTop label{ color:#95866f;}
			.Template .TemplateCon3 .TemplateConTop span{color:#fff;}
			.Template .TemplateCon3 .TemplateConTop label{color:#9b9b9b;}
			.Template .TemplateCon4 .TemplateConTop span{ color:#372101;}
			.Template .TemplateCon4 .TemplateConTop label{ color:#988058;}
			.Template .TemplateCon5 .TemplateConTop span{ color:#fff;}
			.Template .TemplateCon5 .TemplateConTop label{ color:#d58191;}
			.Template .TemplateCon6 .TemplateConTop span{color:#fff;}
			.Template .TemplateCon6 .TemplateConTop label{ color:#92ae92;}
			.Template .TemplateCon .TemplateConOver{ position:absolute; left:0; top:0; width:258px; height:130px; line-height:130px; text-align:center; border:1px solid #c4c4c4; background-color:#f1f1f1; font-family:"微软雅黑"; font-size:18px; color:#9a9a9a; cursor:pointer; display:none;}
			
			
			.voucherBox .voucherRt{ float:right; width:365px; padding-right:17px; overflow-y:scroll; height:473px; border:1px solid #c4c4c4; background-color:#f9f9f9;}
			.voucherRt .voucherTxt{ float:left; width:365px; margin-top:190px; line-height:50px; font-family:"微软雅黑"; font-size:24px; color:#c1c1c1; font-weight:bold; text-align:center;}
			
			.voucherRt .voucherTitle{ float:left; width:365px; margin-top:30px;}
			.voucherRt .voucherTitle dt{ float:left; width:365px;}
			.voucherRt .voucherTitle dt span{ width:100px; text-align:right; line-height:23px; font-size:14px; font-weight:bold; float:left;}
			.voucherRt .voucherTitle dt em{ float:left; width:265px; margin:5px 0 0 100px; display:inline; color:#999999; font-style:normal;}
			.voucherRt .voucherTitle .voucherTitleInp{ float:left; width:220px; padding:3px; border:1px solid #aebac0; background-color:#fff;}
			.voucherRt .voucherTitle .voucherTitleBtn{border:0 solid #aebac0; background-image:url(<%=request.getContextPath()%>/public/images/coupon/MicrosBtn.gif); width:109px; height:30px; margin:40px 0 0 150px; display:inline; cursor:pointer;}
			
			.voucherRt .voucherPhoto{ float:left; width:262px; margin:60px 0 0 63px; display:inline;}
			.voucherRt .voucherPhoto .PhotoBtn{ float:left; width:260px; height:100px; background-image:url(<%=request.getContextPath()%>/public/images/coupon/voucherPhoto.gif); cursor:pointer; border:1px solid #c1c1c1;}
			.voucherRt .voucherPhoto em{ float:left; width:262px; text-align:center; margin-top:10px; color:#999999; font-size:14px; font-style:normal;}
			.voucherRt .PhotoChoose{ float:left; width:260px; margin-top:40px;}
			.voucherRt .PhotoChoose input{ float:left; margin-top:3px;}
			.voucherRt .PhotoChoose span{ float:left; margin-left:5px; display:inline; font-weight:bold; line-height:20px;}
			.voucherRt .PhotoSelect{ float:left; position:relative; width:auto; margin:1px 0 0 10px;}
			.voucherRt .PhotoSelect img{ position:absolute; left:0; top:0; width:18px; height:18px; z-index:100; cursor:pointer;}
			.voucherRt .PhotoSelect .PhotoSt { background-color: #FFFFFF; border: 2px solid #7FB221; border-radius: 5px; cursor: pointer; left:8px; padding: 10px; position: absolute; top:6px; width: 190px; z-index:99; display:none;}
			
			.voucherRt .VoucherHelp{ float:left; width:322px; margin:30px 0 0 25px; display:inline-block;}
			.voucherRt .VoucherHelp h1{ float:left; width:322px; font-weight:bold; font-size:14px;}
			.voucherRt .VoucherHelp .VoucherHelpTex{ float:left; width:310px; height:150px; padding:5px; border:1px solid #aebac0; background-color:#fff; resize:none; margin-top:15px; color:#9a9a9a; font-size:12px;}
			.voucherRt .VoucherHelp em{ float:left; width:322px; margin-top:5px; text-align:right; color:#9a9a9a; font-style:normal;}
			.voucherRt .VoucherHelpBtn{ border:0; background-image:url(<%=request.getContextPath()%>/public/images/coupon/MicrosBtn.gif); width:109px; height:30px; margin:40px 0 0 100px; display:inline; cursor:pointer;}
			
			.voucherRt .VoucherSuccess{ float:left; width:365px; margin-top:145px;}
			.voucherRt .VoucherSuccess dt{ float:left; width:43px; height:43px; margin-left:165px; display:inline;}
			.voucherRt .VoucherSuccess dd{ float:left; width:365px; margin-top:20px; font-family:"微软雅黑"; font-size:24px; text-align:center;}
			.ke-toolbar {
			    background-color: #F0F0EE;
			    border-bottom: 1px solid #CCCCCC;
			    overflow: hidden;
			    padding: 2px 5px;
			    text-align: left;
			}
			.CreditsBox .Membership .MemberPhoto {
			    float: left;
			    margin: 10px 0;
			    width: 380px;
			}
			.ke-container {
			    background-color: #FFFFFF;
			    border: 1px solid #CCCCCC;
			    display: block;
			    margin: 0;
			    overflow: hidden;
			    padding: 0;
			}
			.ke-container {
   			   width: 330px !important;
			}
			.ke-toolbar 
			.ke-outline {
			    border: 1px solid #F0F0EE;
			    cursor: pointer;
			    display: block;
			    float: left;
			    font-size: 0;
			    line-height: 0;
			    margin: 1px;
			    overflow: hidden;
			    padding: 1px 2px;
			}
			.voucherBox .voucherRt {
			    background-color: #F9F9F9;
			    border: 1px solid #C4C4C4;
			    float: right;
			    height: 473px;
			    overflow-y: scroll;
			    padding-right: 17px;
			    width: 365px;
			}
			.input_file {
			    background-color: rgba(0, 0, 0, 0);
			    border: 1px solid #DBDBDB;
			    cursor: pointer;
			    height: 100px;
			    line-height: 20px;
			    vertical-align: middle;
			}
		</STYLE>
		<style>
			.Coupon .CouponNav {
			    border-bottom: 1px dotted #7D7D7D;
			    display: inline;
			    float: left;
			    margin: 0 10px;
			    padding-bottom: 25px;
			    width: 710px;
			}
			.Coupon .CouponNav span .Btn-3 {
			    border: medium none;
			    border-radius: 2px 2px 2px 2px;
			    color: #FFFFFF;
			    float: left;
			    font-family: "微软雅黑";
			    font-size: 12px;
			    height: 26px;
			    width: 108px;
			}
			.Coupon .CouponNav span .Btn-3 {
			    background: url("<%=request.getContextPath()%>/public/wxmuban/images/bgs1.png") repeat-x scroll 0 -638px transparent;
			}
			.Coupon .CouponNav span {
			    float: left;
			}
			.Coupon .CouponNav span .Btn-3:hover {
			    background: url("<%=request.getContextPath()%>/public/wxmuban/images/bgs1.png") repeat-x scroll 0 -671px transparent;
			}
			.Coupon .CouponNav label {
			    float: right;
			}
			.Coupon .CouponNav label a {
			    background-color: #EFEFEF;
			    border: 1px solid #D1D0D0;
			    border-radius: 5px 5px 5px 5px;
			    color: #666666;
			    display: inline;
			    float: left;
			    line-height: 24px;
			    margin-left: 20px;
			    text-align: center;
			    width: 104px;
			}
			
			.Coupon {
			    display: inline-block;
			    float: left;
			    margin: 30px 25px;
			    width: 730px;
			}
			.Coupon .CouponList {
			    float: left;
			    margin-bottom: 10px;
			    margin-top: 20px;
			    width: 730px;
			}
			.Coupon .CouponCer {
			    display: block;
			    float: left;
			    margin-right: 20px;
			    margin-top: 25px;
			    width: 222px;
			}
			.Coupon .CouponCer dl {
			    border: 1px solid #C0C0C0;
			    border-radius: 8px 8px 8px 8px;
			    display: block;
			    float: left;
			    padding: 10px;
			    width: 200px;
			}
			
			.Coupon .CouponCer dl dt {
			    display: block;
			    float: left;
			    height: 125px;
			    position: relative;
			    width: 200px;
			}
			.Coupon .CouponCer dl dt img {
			    float: left;
			    height: 124px;
			    width: 200px;
			}
			.Coupon .CouponCer dl dt span {
			    background-color: #383838;
			    display: none;
			    height: 33px;
			    left: 0;
			    opacity: 0.8;
			    position: absolute;
			    top: 92px;
			    width: 200px;
			}
			.Coupon .CouponCer dl dt span img {
			    float: right;
			    height: 33px;
			    width: 42px;
			}
			.Coupon .CouponCer dl dt span img {
			    height: 33px;
			    width: 42px;
			}
			.Coupon .CouponCer ul {
			    float: left;
			    height: 23px;
			    margin-top: 20px;
			    width: 200px;
			}
			.Coupon .CouponCer ul label {
			    float: right;
			    width: 110px;
			}
			.Coupon .CouponCer ul label a {
			    color: #333333;
			    float: left;
			    font-weight: bold;
			    height: 23px;
			    line-height: 23px;
			    text-align: right;
			    width: 110px;
			}
			.OverviewTop{background-color: #f9f5b7; border: 1px solid #e0ddb7; height:24px; float: left; padding: 10px 20px; width:690px; margin:24px; margin-bottom:0; position:relative;}
			.OverviewTop span{ color: #313131; float: left; font-family: "微软雅黑"; font-size: 16px; line-height:24px;}
			.OverviewTop a { height: 46px; width: 148px; position:absolute; right:-1px; top:-1px; background-image:url(/images/matter_wap_top1.gif); background-position:0 0 ;}
			.OverviewTop.OnMicro a{ background-position:0 -45px;}
			.SettingPop{ position:fixed; left:50%; top:100px; width:420px; margin-left:-210px; display:none; z-index:101;}
			.SettingPop .SettingTop{ float:left; width:396px; height:44px; background-image:url(<%=request.getContextPath()%>/public/wxmuban/images/EventProTop.png);}
			.SettingPop .SettingTop span{ float:right; width:18px; height:17px; margin:13px 15px 0 0; cursor:pointer;}
			.SettingPop .SettingCer{ float:left; width:370px; padding:13px; background-image:url(<%=request.getContextPath()%>/public/wxmuban/mages/EventProCer.png);}
			.SettingPop .SettingCer li{ float:left; width:370px; margin-top:10px;}
			.SettingPop .SettingCer li span{ float:left; width:135px; line-height:23px; font-family:"微软雅黑"; font-size:15px; text-align:right;}
			.SettingPop .SettingCer .SettingInp{ float:left; width:180px; padding:3px; border:1px solid #aeb8ca;}
			.SettingPop .SettingCer .SettingBtn{ float:left; margin:20px 0 0 135px; display:inline; width:118px; height:32px; cursor:pointer; background-image:url(/images/EntrustBtn.gif);}
		
			.UnknownBox{ position:absolute; width:410px; left:50%; margin-left:-205px; top:100px; z-index:999}
			.UnknownBox .UnknownTop{ float:left; width:410px; height:40px;}
			.UnknownBox .UnknownCer{ float:left; width:410px; background-image:url(/func/images/unknowncer.png);}
			.UnknownBox .UnknownCer dl{ float:left; width:350px; margin:50px 30px 0 30px; display:inline;}
			.UnknownBox .UnknownCer dl dt{ float:left; width:350px; text-align:center; line-height:30px; font-family:"微软雅黑"; font-size:18px;}
			.UnknownBox .UnknownCer dl dt em{ font-style:normal; color:#F00;}
			.UnknownBox .UnknownCer dl dd{ float:left; width:350px; margin-top:20px;}
			.UnknownBox .UnknownCer .UnknownBtn{ float:left; width:109px; height:30px; cursor:pointer; background-image:url(<%=request.getContextPath()%>/public/wxmuban/images/unknownbtn.gif); margin-left:120px;}
			.UnknownBox .UnknownFor{ float:left; width:410px; height:40px;}
			
			
			.Coupon .CouponCer dl dd {
			    color: #333333;
			    float: left;
			    font-size: 14px;
			    font-weight: bold;
			    height: 25px;
			    line-height: 25px;
			    margin: 0 0 10px;
			    text-align: center;
			    width: 200px;
			}
			.Coupon_over{background-color: #F7FFD7;}
		</style>
		<script type="text/javascript">
			$(document).ready(function(){
				$(".only-item").find(".tuwenedit").live('click',function(){
					$(".container,.onlymore").hide();
					$(".creatimg_text,.onlyone").show();
					$(".page").hide();
					$(".btmnav").hide();
					var id = $(this).attr("articleid");
					$(".uploadarticleid").val(id);
				});
				
				$('.CouponDl').live('mouseover mouseout', function(event) {
					if (event.type == 'mouseover') {
						$(this).find("span").show();
						$(this).addClass("Coupon_over");
					} else {
						$(this).find("span").hide();
						$(this).removeClass("Coupon_over");
					}
				}); 
				
				$(".CouponSten li").live('mouseover mouseout', function(event) {
			 	 	if (event.type == 'mouseover') {
					  	$(this).addClass("couponSten_li");
						$(this).find(".MaskBox").show();
						$(this).find(".CouponTip").css("visibility","inherit");
					} else {
						$(this).removeClass("couponSten_li");
						$(this).find(".MaskBox").hide();
						$(this).find(".CouponTip").css("visibility","hidden");
				    }
				});
		
				$(".CouponSten li").live('click', (function(){
					$(".CouponSten").hide();
					$(".centent_r_btm").show();
					//插入优惠劵
					var targetId = $(this).attr("value");
					$(".voucherTopInp").val("");
					$.ajax({
						url:"<%=request.getContextPath()%>/system/couponTemplate_loadCouponTemplate.action?time=" + new Date().getTime(),
						type: "POST",
						dataType: "json",
						timeout: 100000,
						data:{
							"id":targetId
						},
						success:function(data){
							var vo = data.vo;
							$(".Template").attr("style","background-image:url(" + data.ip + "coupon/"+ vo.bgImage +")");
							$(".Template").attr("name",vo.bgImage);
							$(".Template").attr("hdname",vo.headerImage);
							$(".TemplateCouponTitle").html(vo.title);
							$(".TemplateTp").find("img").attr("src",data.ip + "coupon/"+ vo.banner);
							$(".TemplateTp").find("img").attr("name",vo.banner);
							$(".TemplateCard").html(data.code);
							$(".TemplateConTop").find("label").html(vo.explain);
							$(".centent_r_btm_1").attr("value","add");
							$(".centent_r_btm_1").attr("targetId","");
							
						}
					});
						
				}));
				$("#SaveBtn").click(function(){
					var v = $(".centent_r_btm_1").attr("value");
					var key=$(".voucherTopInp").val();
					if(key==""){
						alert("关键字不能为空!");
						return;
					}
					var startTime=$("#datepicker").val().replace("-","").replace("-","");
					var endTime=$("#datepicker1").val().replace("-","").replace("-","");
					var bgImage=$(".Template").attr("name");
					var hdImage=$(".Template").attr("hdname");
					var title=$(".TemplateCouponTitle").text();
					var banner=$(".TemplateTp").find("img").attr("name");
					var code=$(".TemplateCard").text();
					var explain=$(".TemplateConTop").find("label").html();
					if(v=="add"){
						$.ajax({
							url:"<%=request.getContextPath()%>/system/coupon_addCoupon.action?time=" + new Date().getTime(),
							type: "POST",
							dataType: "json",
							timeout: 100000,
							data:{
								"keys":key,
								"startTime":startTime,
								"endTime":endTime,
								"bgImage":bgImage,
								"title":title,
								"banner":banner,
								"code":code,
								"explain":explain,
								"hdImage":hdImage
							},
							success:function(data){
								$(".CouponSten").hide();
								$(".right_centent").show();
								$(".menuName_2").hide(); 
								$(".arrow").hide();
								$(".centent_r_btm_1").hide();
								loadCoupon();
								$(".voucherHide").hide();
								$(".voucherTxt").show();
								$(".voucherTopInp").val("");
								$("#description").val("");
							}
						});
					}else {
					    var targetId = $(".centent_r_btm_1").attr("targetId");
						$.ajax({
							url:"<%=request.getContextPath()%>/system/coupon_updateCoupon.action?time=" + new Date().getTime(),
							type: "POST",
							dataType: "json",
							timeout: 100000,
							data:{
								"id":targetId,
								"keys":key,
								"startTime":startTime,
								"endTime":endTime,
								"bgImage":bgImage,
								"title":title,
								"banner":banner,
								"code":code,
								"explain":explain,
								"hdImage":hdImage
							},
							success:function(data){
								$(".CouponSten").hide();
								$(".right_centent").show();
								$(".menuName_2").hide(); 
								$(".arrow").hide();
								$(".centent_r_btm_1").hide();
								loadCoupon();
								$(".voucherHide").hide();
								$(".voucherTxt").show();
								$(".voucherTopInp").val("");
								$("#description").val("");
							}
						});
					}
			    });
				$(".TemplateTitle").hover(function(){
                      $(".TemplateTitleOver").show();
                },function(){
                      $(".TemplateTitleOver").hide();
                });
				$(".TemplateTitleOver").click(function(){
					var title = $(".TemplateCouponTitle").html();
					$(".voucherTitleInp").val(title);
					$(".voucherHide").hide();
					$(".voucherTitle").show();
				});
				
				//title
				$(".voucherTitleBtn").click(function(){
					var title = $(".voucherTitleInp").val();
					$(".TemplateCouponTitle").html(title);
					$(".voucherTitle").hide();
					$(".VoucherSuccess").show();
				});
				
				$(".TemplateTp").hover(function(){
					$(".TemplateTpOver").show();
				},function(){
					$(".TemplateTpOver").hide();
				});
				$(".TemplateTpOver").click(function(){
					$(".voucherHide").hide();
					$(".voucherPhoto").show();
				});
				$(".TemplateCon").hover(function(){
					$(".TemplateConOver").show();
				},function(){
					$(".TemplateConOver").hide();
				});
				$(".TemplateConOver").click(function(){
					$(".voucherHide").hide();
					$(".VoucherHelp").show();
				});
				$(".VoucherHelpBtn").click(function(){
				 $(".couponSaveBtn").click();
					var introduction =k1.html();
					var introductiontext =k1.text();
					if(introductiontext.length>200){
						$(".alertSimpleContent").html("优惠券说明不能超过200字");
						$(".alertSimple").show();
						return false;
					}
					$(".TemplateConTop").find("label").html(introduction);
					$(".VoucherHelp").hide();
					$(".VoucherSuccess").show();
				});
			});
			function textCount(inputText,max){
				var textlen = inputText.value.length;
				document.getElementById("nub").innerHTML=max-textlen;
			}
			function ajaxFileUploadImgEvent(){
			    var code=$(".TemplateCard").text().replace(" ","").replace(" ","");
				var regIMG = new RegExp(
				/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
				var filevAAAA = $("#ajaxFileUploadImg").val();
				if (!regIMG.test(filevAAAA)){
				    alert("图片格式必须为jpg、jpeg、png、bmp! ");
					return false;
				}else{
					$.ajaxFileUpload({
						url:"<%=request.getContextPath()%>/system/resource_uploadCouponBannerImage.action?time=" + new Date().getTime(),
				      	secureuri:false,
				      	fileElementId:'ajaxFileUploadImg',                         //文件选择框的id属性
				      	dataType: 'json', 					//服务器返回的格式，可以是json
				      	data:{
							"code":code
						},                                    
						success: function (data, status) {
							if(data.status == "success"){
								//$("#couponimg").attr("src", data.url);
								$(".TemplateTp").find("img").attr("src",data.url);
								$(".TemplateTp").find("img").attr("name",data.name);
							} else {
								$('.picurl').tip({width:'240',status:'error',content:'设置失败！',dis_time:1000});
					    	}
						}
				    });
			  }
			} 
		</script>
		<script type="text/javascript">
			var show_indexleftbar = 4;
			var pagebegin=1;
			$(document).ready(function(){
				var d =  new Date();
				var y =  d.getFullYear();
				var m = d.getMonth()+1;
				if(m<10){
					m = "0" + m;
				}
				var h = d.getDate();
				var t = y + "-" + m + "-" + h;
				$("#datepicker").val(t);
				$("#datepicker1").val(t);
			 	loadCoupon();
			//遮罩
			//	var nextpage = geturl().params.nextpage||'';
			//	if(nextpage==4){
			//		$(".showmask4").show();
			//	}
				//$("#page-4").click(function(){
				//location.href="/card/cardset.action?op=showcard&nextpage=5";
			});
			function loadCoupon(){
				$.ajax({
					url:"<%=request.getContextPath()%>/system/coupon_selectAll.action?time=" + new Date().getTime(),
					type: "POST",
					dataType: "json",
					timeout: 100000,
					data:{
						"pagebegin":pagebegin
					},
					success:function(data){
						$(".CouponList").empty();
						var coups = data.coupons;
						if(coups.length>0){
							$('<h1>已有优惠券</h1>').appendTo(".CouponList");
							for( var i = 0; i < coups.length; i++ ){
								var lcoups = coups[i];
								$('<div class="CouponCer del'+lcoups.id+'">'+
								'<dl class="CouponDl" style="overflow: hidden;" title='+lcoups.title+'>'+
								'<dd>'+lcoups.title+'</dd>'+
								'<dt>'+
								'<img src="' +data.ip + 'coupon/' + lcoups.hdImage + '"/>'+
								'<span>'+
								'<img onclick="deleteCoupons('+ lcoups.id+')" src="<%=request.getContextPath()%>/public/wxmuban/images/activitydelete.png" />'+
								'<img onclick="editCoupons('+lcoups.id+')" src="<%=request.getContextPath()%>/public/wxmuban/images/activitymodify.png" />'+
								'</span>'+
								'</dt>'+
								'<ul>'+
								'<label><a href="/toDataPort.action?op=toDataPort&couponid='+lcoups.id+'&pagebegin=1">查看统计</a></label>'+
								'</ul>'+
								'</dl>'+
								'</div>').appendTo(".CouponList");
							}
						}else{
							$('<div class="DefaultBox">'+
							'<span>还没有优惠券哦，赶快添加一个吧！</span>'+
							'</div>').appendTo(".CouponList");
						}
						
						$(".voucherTopInp").val("");
					}
				});
			}
			
			function editCoupons(targetId){
				$(".right_centent").hide();
				$(".CouponSten").hide();
				$(".centent_r_btm_1").show();
				$(".menuName_2").show(); 
				$(".arrow").show();
				$.ajax({
					url:"<%=request.getContextPath()%>/system/coupon_getCoupon.action?time=" + new Date().getTime(),
					type: "POST",
					dataType: "json",
					timeout: 100000,
					data:{
						"id":targetId
					},
					success:function(data){
						var vo = data.vo;
						var startTime = vo.startTime.toString();
						var endTime = vo.endTime.toString();
						$(".voucherTopInp").val(data.key);
						$(".Template").attr("style","background-image:url(" + data.ip + "coupon/"+ vo.bgImage +")");
						$(".Template").attr("name",vo.bgImage);
						$(".Template").attr("hdname",vo.headerImage);
						$(".TemplateCouponTitle").html(vo.title);
						$(".TemplateTp").find("img").attr("src",data.ip + "coupon/"+vo.banner);
						$(".TemplateTp").find("img").attr("name",vo.banner);
						$(".TemplateCard").html(vo.code);
						$(".TemplateConTop").find("label").html(vo.explain);
						$(".centent_r_btm_1").attr("value","edit");
						$(".centent_r_btm_1").attr("targetId",vo.id);
						var st = startTime.substring(0,4)+"-"+startTime.substring(4,6)+"-"+startTime.substring(6,8);
				    	var et = endTime.substring(0,4)+"-"+endTime.substring(4,6)+"-"+endTime.substring(6,8);
						$("#datepicker").val(st);
					    $("#datepicker1").val(et);
					}
				});
			}
			
			function deleteCoupons(targetId){
				$.ajax({
					url:"<%=request.getContextPath()%>/system/coupon_delCoupon.action?time=" + new Date().getTime(),
					type: "POST",
					dataType: "json",
					timeout: 100000,
					data:{
						"id":targetId
					},
					success:function(data){
						$(".del"+data.id).remove();
						if(data.count == 0){
							$(".CouponList").empty();
							$('<div class="DefaultBox1">'+'<span>还没有优惠券哦，赶快添加一个吧！</span>'+'</div>').appendTo(".CouponList");
						} 
						$('.picurl').tip({width:'240',status:'right',content:'刪除成功！',dis_time:1000});
						return;
					}
				});
			}
			function addCoupons() {
				$(".right_centent").hide();
				$(".CouponSten").show();
				$(".menuName_2").show();
				$(".arrow").show();
				$.ajax({
					url:"<%=request.getContextPath()%>/system/couponTemplate_selectAllCT.action?time=" + new Date().getTime(),
					type: "POST",
					dataType: "json",
					timeout: 100000,
					success:function(data){
						$(".CouponSten").empty();
						var listVo = data.listVo;
						if( listVo.length > 0 ) {
							var s = "";
							for( var i = 0; i < listVo.length; i++ ){
								var couponsTemplate = listVo[i];
								s += '<li class="" value="' + couponsTemplate.id + '"><div class="CouponAcer">'+
								'<img width="210" height="312" src=" ' + data.ip + 'coupon/' + couponsTemplate.prototype + ' "/>'+
								'<div class="MaskBox" style="display: none;"><div class="MaskBg"></div>'+
								'<div class="MaskCon">免费版</div></div></div>'+
								'</li>';
							}
							var s1 = '<ul>'+ s + '</ul>';
							$(".CouponSten").append(s1);
						}else{
							$('<div class="DefaultBox"><span>没有可添加的优惠劵!</span></div>').appendTo(".CouponSten");
						}
					}
				});
			}
			function backShow(){
				$(".CouponSten").hide();
				$(".right_centent").show();
				$(".menuName_2").hide(); 
				$(".arrow").hide();
				$(".centent_r_btm_1").hide();
				$(".voucherHide").hide();
				$(".voucherTxt").show();
			}
        </script> 
        <script type="text/javascript">
			$(document).ready(function(){
				$(".alertSimpleColose").click(function(){
					$(".alertSimple").hide();
				});
			});
		</script>
		<style type="text/css">
		    .menuName_1{
		        cursor:pointer;
		    }
			.menuName_2{
				font-size: 12px;
				color:#699D08;
				display: none;
			}
			.arrow {
				font-size: 11px;
				margin-left: 5px;
				margin-right: 5px;
				display: none;
			}
		</style>
	</head>
	<body onload="init()">
		<div class="headerPart">
   			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
   			<span><font class="font1">当前位置:</font><font class="menuName_1" onclick="backShow()">商家优惠券</font>  <font class="arrow">></font> <font class="menuName_2">添加优惠券</font></span>
    	</div>
		<div class="main">
			<div class="right_centent">
				<div class="sort">
					<div class="centent_r_btm">
						<div class="UnknownBox alertSimple" style="display:none; left: 50%;top:10%; margin-left:-277px; position: fixed; _position: absolute;z-index: 8000;">
							<div class="UnknownTop"><img src="/func/images/unknowntop.png" /></div>
							<div class="UnknownCer">
								<dl>
									<dt class="alertSimpleContent">您已达发送上限。今天还能发送<em>0</em>次。</dt>
									<dd><input name="" type="button" class="UnknownBtn alertSimpleColose"></dd>
								</dl>
							</div>
							<div class="UnknownFor"><img src="/func/images/unknownfor.png" /></div>
							</div>
							<div class="OverviewTop OnShow" style="display:none;">
								<span>已切换至微服务接口，如您有任何问题或建议可联系在线客服，我们会及时为您解答！</span>
							</div>
							<div class="SettingPop">
								<div class="SettingTop"><span><img src="/images/poclose.gif" class="SettingPop_Close"/></span></div>
								<div class="SettingCer">
									<ul>
									<li><span>公众账号：</span><input type="text" class="SettingInp checkusernamer" /></li>
									<li><span>公众账号密码：</span><input type="password" class="SettingInp checkpwr" /></li>
									<li class="yzm" style="display: none;"><span>验证码：</span><img class="yzmimg" src="/images/90902013251854011704.jpg" alt="" /><input name="" type="text" class="InterfaceInp vcode" style="float: left; width: 100px; height: 23px; border: 1px solid #AEB8CA;"></li>
									</ul>
									<input type="button" class="SettingBtn" />
								</div>
								<div class="SettingFor"><img src="/crm/images/EventProFor.png" width="396" height="35" /></div>
							</div>
							<div class="Coupon">
								<div class="CouponNav">
									<span>
										<button class="Btn-3" onclick="addCoupons()">新建优惠券</button>
									</span>
								</div>
								<div class="CouponList">
									<h1>正在加载....</h1>
								</div>
								<div class="Pagination">
									<ul class="Pageing">
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div> 
			<div class="CouponSten" style="display: none;"></div>
		</div>
		
		<div class="centent_r_btm centent_r_btm_1" style="display:none">
			<div class="voucherBox">
				<div style="display: none" class="voucherTop voucherTop1">
					<h1>
						<span><input type="button" id="EditBtn" value="编辑"
								class="voucherTopBtn">
						</span>
					</h1>
					<ul>
						<li>
							<span>关键字：</span>
							<label class="verifycode" id="verifycode" name="verifycode"></label>
						</li>
						<li>
							<span>领取起止日期：</span>
							<label id="startenddate">
								&mdash;
							</label>
						</li>
					</ul>
				</div>
				<div class="voucherTop voucherTop2">
					<h1>
						<span><input type="button" id="SaveBtn" value="保存"
								class="voucherTopBtn">
						</span>
					</h1>
					<ul>
						<li>
							<span>关键字：</span>
							<input type="text" value="" class="voucherTopInp"
								name="verifycode">
							<em>用户输入关键字领取优惠券</em>
							<dt style="margin: 0 0 0 100px; display: inline; width: auto;"
								class="error"></dt>
						</li>
						<li>
							<div
								style="float: right; width: 640px; height: 22px; line-height: 22px; padding: 0; border: none;"
								class="_countitem">
								<input type="text" value=""
									class="view-rank-1 datepicker-icon st hasDatepicker"
									id="datepicker" name="starttime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" >
								至
								<input type="text" value=""
									class="view-rank-1 datepicker-icon et hasDatepicker"
									id="datepicker1" name="endtime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">

							</div>
							<span>领取起止日期：</span>

						</li>
					</ul>
				</div>
				<div class="voucherLt">
					<div class="Template" id="div_img">
						<div class="TemplateTitle">
							<strong class="TemplateCouponTitle">优惠券</strong>
							<span class="TemplateTitleOver" style="display: none;">编辑优惠券名称</span>
						</div>
						<div class="TemplateTp">
							<strong><img width="260" height="100" src="<%=request.getContextPath()%>/public/images/coupon/TemplateTp.gif">
							</strong>
							<span class="TemplateTpOver" style="display: none;">编辑配图</span>
						</div>
						<div class="TemplateCard TemplateCard1">A6B8 C6D8 E6F8</div>
						<div class="TemplateCon TemplateCon1">
							<div class="TemplateConTop"><span>优惠说明</span><label>优惠说明</label></div>
							<span class="TemplateConOver" style="display: none;">编辑优惠券说明</span>
						</div>
					</div>
				</div>
				<div class="voucherRt">
					<div class="voucherTxt voucherHide">
						点击左侧预览图对应区域
						<br>
						即可进行编辑哦~
					</div>
					<div style="display: none;" class="voucherTitle voucherHide">
						<dl>
							<dt>
								<span>优惠券名称：</span>


								<input type="text" value="优惠券" class="voucherTitleInp"
									id="description" name="" onfocus="textCount(this,6);" onkeyup="textCount(this,6);" onkeydown="textCount(this,6);">


								<em>剩余<em
									style="font-style: normal; float: none; margin: 0;" id="nub">6</em>字</em>
							</dt>
							<dd>
								<input type="button" class="voucherTitleBtn" name="">
							</dd>
						</dl>
					</div>
					<div style="display: none;" class="voucherPhoto voucherHide">
						<div style="position: relative;" class="MemberPhoto Hidden">
							<input type="file" class="input_file"
								style="opacity: 0; filter: alpha(opacity = 00); position: absolute; left: -864px; top: 0; height: 144x; font-size: 78px; cursor: pointer;"
								name="imagefile" onchange="ajaxFileUploadImgEvent();"
								id="ajaxFileUploadImg"/>
							<img width="260" height="100"
								src="<%=request.getContextPath()%>/public/images/coupon/voucherPhoto.gif" id="couponimg"/>
							<em>图片建议 260 x100 像素</em>
							<div style="display: none;" class="PhotoChoose">
								<input type="checkbox" value="" name="">
								<span>没有图片</span>
								<div class="PhotoSelect">
									<img class="PhotoSelectImg" src="<%=request.getContextPath()%>/public/images/coupon/stamenu.png">
									<div class="PhotoSt">
										<strong>勾选后，左侧预览部分配图板块隐藏。。</strong>
									</div>
								</div>
							</div>
						</div>
     	
						<input type="hidden" class="couponSaveBtn">
						<input type="hidden" class="verify_code">
					</div>
					<div style="display: none;" class="VoucherHelp voucherHide">
						<h1>
							优惠券说明：
						</h1>
							<textarea id="texteditcontent1" name="content" style="visibility:hidden;" class="VoucherHelpTex" rows="" cols=""></textarea>
							
							<script type="text/javascript">
							var k1;
							KindEditor.ready(function(K) {
							   K.each({
									'plug-align' : {
										name : '对齐方式',
										method : {
											'justifyleft' : '左对齐',
											'justifycenter' : '居中对齐',
											'justifyright' : '右对齐'
										}
									},
									'plug-order' : {
										name : '编号',
										method : {
											'insertorderedlist' : '数字编号',
											'insertunorderedlist' : '项目编号'
										}
									},
									'plug-indent' : {
										name : '缩进',
										method : {
											'indent' : '向右缩进',
											'outdent' : '向左缩进'
										}
									}
								},function( pluginName, pluginData ){
									var lang = {};
									lang[pluginName] = pluginData.name;
									KindEditor.lang( lang );
									KindEditor.plugin( pluginName, function(K) {
										var self = this;
										self.clickToolbar( pluginName, function() {
											var menu = self.createMenu({
													name : pluginName,
													width : pluginData.width || 100
												});
											K.each( pluginData.method, function( i, v ){
												menu.addItem({
													title : v,
													checked : false,
													iconClass : pluginName+'-'+i,
													click : function() {
														self.exec(i).hideMenu();
													}
												});
											})
										});
									});
								});
							   k1 = K.create("#texteditcontent1",{
							        resizeType:0,
							        allowFileManager : true,
									items:['fontsize','forecolor','bold','italic','underline','strikethrough','removeformat','|'
									       ,'justifyleft' ,
									       'justifycenter' ,'justifyright','justifyfull']
							   });
							});
							$(".texteditcontent1").show();
							
							</script>
						<em>限输入200字</em>
						<input type="button" class="VoucherHelpBtn">
					</div>
					<div style="display: none;" class="VoucherSuccess voucherHide">
						<dl>
							<dt>
								<img width="43" height="43"
									src="<%=request.getContextPath()%>/public/images/coupon/VoucherSuccess.png">
							</dt>
							<dd>
								已成功保存
							</dd>
						</dl>
					</div>
				</div>
			</div>
		</div>	
		<div style="opacity:0;  filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px;z-index: -99;">
        		<input id="urlt" class="picurl" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" type="file">
        </div>
	</body>
</html>