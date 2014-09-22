<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
%>
<html>
<head>
	<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/public/wxmuban/css/microsite.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/public/wxmuban/css/website.css" rel="stylesheet" type="text/css">
	<link rel="<%=request.getContextPath()%>/public/wxmuban/css/default.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/public/wxmuban/css/index.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/public/css/kindEditor.css" rel="stylesheet" type="text/css">
	<link href="<%=request.getContextPath()%>/public/css/picturecss.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
		var path = '<%=request.getContextPath()%>/public';
	</script>
	<script src="<%=request.getContextPath()%>/public/wxmuban/js/jquery-1.js" type="text/javascript"></script>
	<!--<script src="<%=request.getContextPath()%>/public/wxmuban/js/woscaller.js" async="" type="text/javascript"></script>-->
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/wxmuban/js/microsite1.js"></script>
	<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/public/wxmuban/js/jscolor.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/public/wxmuban/js/default-tip.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/wxmuban/js/geturl.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/kindeditor-min.js"></script>
	<script type="text/javascript" src="<%=path%>/public/js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/zh_CN.js"></script>
	<style type="text/css">
		.li_arr{margin-left: 10px;font-size: 12px;}
		.Stencil_1 .bgImg_arr{ 
		    float: left;
		    background: url("<%=request.getContextPath()%>/public/wxmuban/images/website_back01.png") no-repeat;
		    width: 40px;
		    height: 45px;
		    position: absolute;
		}
		
		.Stencil_1 .bgImg_home{ 
		    float: left;
		    background: url("<%=request.getContextPath()%>/public/wxmuban/images/website_back02.png") no-repeat;
		    width: 40px;
		    height: 45px;
		    position: absolute;
		    left: 40px;
		}
		.Microsite ._selectface dd{
			float: none;
			background-image:  url("<%=request.getContextPath()%>/public/wxmuban/images/selectface1.png");
			width:320px;
		}
		.Microsite ._selectface ._face1{
		    background-position: 0 0;
		}
		.Microsite .AddPhotos .AddPhotosBtn{
		    background-image:  url("<%=request.getContextPath()%>/public/wxmuban/images/AddPhotos1.png");
		}
		.moban_style {
			 border-bottom: 1px solid rgba(233, 233, 233, 0.8);
			 height: 24px;
			 margin-left: 13px;
			 margin-top: 10px;
			 width: 310px;
		}
		.moban_style ul li {
			display: inline;
			width: 80px;
			float: left;
			color: #E9E9E9;
		}
		.moban_style ul li:hover {
			color:#ABDB0A;
			font-size: 20px;
			font-weight: bold;
		}
		.moban_style ul .moban_style_color {
			font-size: 18px;
			color:#699D08;
			font-weight: bold;
		}
		.moban_style_color {
			font-size: 18px;
			color:#699D08;
			font-weight: bold;
		}
		.Microsite .Stencil_1 .Stencil_11_1 {
		 	position:relative;
						width: 320px;
						margin-left: 10px;
		}
		.Microsite .Stencil_1 .Stencil_11_1 .change_size_title {
			font-size: 12px;
			font-weight: bold;
			color: #595959;
			margin-bottom: 4px;
			margin-left: 4px;
		}
		.Microsite .Stencil_1 .Stencil_11_1 .change_size_1 {
			font-size: 12px;
			color: #595959;
			margin-bottom: 3px;
			margin-left: 2px;
		}
		.Microsite .Stencil_1 .Stencil_11_1 .change_size_2 {
			width: 100px;
		}
		.Microsite .Stencil_1 .Stencil_11_1 .change_size_2 input {
			width: 60px;
		}
		.Microsite .Stencil_1 .Stencil_11_1 .change_size_width,.Microsite .Stencil_1 .Stencil_11_1 .change_size_heigth{
			float: left;width: 100px;
			height: 50px;
		}
		.Microsite .Stencil_1 .Stencil_11_1 .change_size_select{
			width: 100px;
			height: 50px;
			margin-left: 203px;
		}
		.Microsite .Stencil_1 .Stencil_11_1 .change_size_img{
			width: 320px;
			background-color: #F9F9F9;
						border: 1px solid #C4C4C4;
			height: 200px;
		}
		.Microsite .MicrosBtn_1 {
		    background-image: url("<%=request.getContextPath()%>/public/wxmuban/images/popup_bg.gif");
		    cursor: pointer;
		    display: inline;
		    float: left;
		    height: 30px;
		    margin: 10px 0 0 100px;
		    width: 109px;
		    color: #fff;
		    font-weight: bold;
		    font-size: 14px;
		}
		
		.Microsite .textSize {
			background-color: #fff;width: 100px ;
			height: 100px;
			border-right:dotted 1px;
			border-bottom:dotted 1px;
		}
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
		.Coupon .CouponNav span  .Btn-3 {
		    background: url("<%=request.getContextPath()%>/public/wxmuban/images/bgs1.png") repeat-x scroll 0 -638px transparent;
		}
		.Coupon .CouponNav span {
		    float: left;
		}
		.Coupon .CouponNav span :hover .Btn-3 {
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
		.Coupon .CouponCer dl dt span a {
		    float: right;
		    height: 33px;
		    width: 42px;
		}
		.Coupon .CouponCer dl dt span a img {
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
		.modefy .m_edit {
   		 	cursor: pointer;
    		height: 33px;
    		margin-left: 100px;
		}
		.m_editH {
		    background: url("<%=request.getContextPath()%>/public/images/pics/activitymodify.png") no-repeat;
		}
		
		
	</style>
	<script type="text/javascript">
		//加载模板
		function loadPicNews(){
			$.ajax({
				url:"<%=path %>/system/pageTemplate_getInfos.action",
				type:'post',
				dataType:'json',
				success: function(data){
					var len = data.length;
					var s = '';
					for (var i = 0; i < len; i++) {
						var res = data[i];
						s += '<li id= "li_'  + res.id + '" style="height:250px; background-position: 0px 196px; background-image:url();" class="only-item"><div class="mouseedit">'+
							'<img src="' + res.picUrl+ '" width="160px">'+
							'<div class="modefy" style="height:40px;top:150px;width:160px;">'+
							'<div id="'+res.id + '" class="m_edit tuwenedit " title="编辑" articleid="' + res.id + '"></div></div></li></div>';
						$("#pics_show").html(s);
					}
					$("#imgCounts_1").text(len);
					$("#imgCounts_2").text(len);
				}
			});
		}
		
		$(document).ready(function(e) {
			loadPicNews();
			$(".only-item").live('mouseover mouseout', function(event) {
			  if (event.type == 'mouseover') {
			    $(this).css("background-position","0 -2px");	
			  } else {
			   $(this).css("background-position","0 196px");
			  }
			});
	
			$(".newshow").find("li").live('mouseover mouseout', function(event) {
			  if (event.type == 'mouseover') {
			   		$(this).find(".mouseedit").find(".modefy").addClass("mdf-alpha");
					$(this).find(".mouseedit").find(".m_edit").addClass("m_editH");
					$(this).find(".mouseedit").find(".m_empty").addClass("m_emptyH");
			  } else {
			  	    $(this).find(".mouseedit").find(".modefy").removeClass("mdf-alpha");
					$(this).find(".mouseedit").find(".m_edit").removeClass("m_editH");
					$(this).find(".mouseedit").find(".m_empty").removeClass("m_emptyH");
			  }
			});	
			
			//编辑选择的模板
			
			$(".only-item").find(".tuwenedit").live('click',function(){
				$(".content").hide();
				var img_id=$(this).attr("id");
				load(img_id);
				$(".Microsite").show();
			});
			//点击保存微网站名称显示保存成功
			$(".editwebsitetitle").live("click",function(){
				var webtitle = $(".webtitle").val();
				var websitename = $("editweb").html(); 
				$(".divwebsitetitle").html(websitename);	
				if($.trim(webtitle) == ""){
					$('.picurl').tip({width:'240',status:'error',content:'微网站名称不能为空！',dis_time:1000});
				} else{
					//隐藏提示
					$(".MicrositeTxt").hide();
					//隐藏上传首页图片
					$(".indeximg").hide();
					//隐藏修改微网站名称
					$(".websitetitle").hide();
					//隐藏修改内容
					$(".addlistcontent").hide();
					//隐藏修改单一内容
					$(".addarticle").hide();
					//隐藏修改内容列表
					$(".showlistarticle").hide();
					//显示保存成功
					$(".MicrSuccess").show();
					$.ajax({
						url:"<%=request.getContextPath()%>/system/page_editJSON.action?time=" + new Date().getTime(),
			            type:"POST",
			            dataType:"json",
			            timeout:"10000",
			            data:{
				            "id":$("#pageId").val(),
			            	"title":webtitle
			            },
			            success:function(data){
							if(data.code == "ok" ){
								$(".StencilNav").find("span").html(data.title);
							}else {
								$('.picurl').tip({width:'240',status:'error',content:'更新失败！',dis_time:1000});
					    	}
			            }
			 		});
				}
			});

			$(".second_title2").live("mouseover mouseout",function(event){
				if(event.type == "mouseover"){
			     	$(this).find("label").show();
			     	$(this).find(".color").css({zIndex:90,height:20});
			   	} else {
			     	$(this).find("label").hide();
			     	$(this).find(".color").css({zIndex:-2,height:20});
			  	}
			});

			//关闭窗口
			$(".close_up1").click(function(){
				$(this).parents(".upload_s").hide();
			});

			// 设置默认背景图片
			$(".m_index").click(function(){
				$.ajax({
					url:"<%=request.getContextPath()%>/system/page_setDefaultBgImage.action?time=" + new Date().getTime(),
					type:"post",dataType:"json",
					timeout:10000,
					data:{
						"id":$("#pageId").val(),
		            	"bgImage":"m_bgimg.jpg"
					},
					success:function(data){
						if(data.code == "ok"){
							$(".mb2").css("backgroundImage","url(" + data.bgImage + ")");
							$(".MicrositeLt").css("backgroundImage","url(" + data.bgImage + ")");
							$(".close_up1").click();
						} else {
							$('.picurl').tip({width:'240',status:'error',content:'设置失败！',dis_time:1000});
				    	}
						
					}
				});
			});
			
			$(".back_top").bind('click',function(){
				$("._face3,._face4,.back_list,.addlistcontent_0,.AddPhotos").hide();
				$("._face1,._face2").show();
				if(te == 1){
					clearInterval(initset);
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess").hide();
					$(".mb1,.MicrositeTxt").show();
					$("._selectface dt span").html('');
				}else if(te == 6){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.demo_banner6").hide();
					$(".mb_nw3,.MicrositeTxt").show();
					$("._selectface dt span").html('');
					initset;
				}else if(te == 7){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws7,.MicrositeTxt").show();
				}else if(te == 8){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws8,.MicrositeTxt").show();
				}else if(te == 9){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws9,.MicrositeTxt").show();
					initset;
				}else if(te == 10){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws10,.MicrositeTxt").show();
				}else if(te == 11){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws11,.MicrositeTxt").show();
				}else if(te == 12){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws12,.MicrositeTxt").show();
				}else if(te == 13){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws13,.MicrositeTxt").show();
				}else if(te == 14){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws14,.MicrositeTxt").show();
				}else if(te == 15){
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.mb1,.mb_nw3").hide();
					$(".mb_nws15,.MicrositeTxt").show();
				}else{
					clearInterval(initset);
					$(".MicrositeLt,.only_cont,.more_cont,.websitetitle,.indeximg,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess").hide();
					$(".mb2,.MicrositeTxt").show();
					$("._selectface dt span").html('');
				}
			});	
			$(".back_list").bind('click',function(){
				$("._face1,._face2,._face4,.only_cont,.addarticle,.back_only").hide();
				$("._face3").show();
				
			});
			$(".back01").bind('click',function(){
				var typeid = $(this).attr("typeid");
				if(typeid==1){
					$(".back_top").click();
				}else{
					$(".back01").attr("typeid","1");
					$(".back_list").click();
				}
			});
			$(".save_back").find("li:odd").css('borderRight','none');
			$(".back02 h1").click(function(){
				if($(".save_back").is(":visible")){
					$(".save_back").hide();
					$(".back02 h1").css("backgroundImage","url(/website/images/website_back02.png)");
				}else{
					$(".save_back").show();
					$(".back02 h1").css("backgroundImage","url(/website/images/website_back022.png)");
				}
			});
			
			$(".save_back").bind('blur',function(){
				$(".save_back").hide();
				$(".back02 h1").css("backgroundImage","url(/website/images/website_back02.png)");
			});
			
			/* 通用JS */
		    $(".StencilNav").hover(function(){
				$(this).find("label").show();
			},function(){
				$(this).find("label").hide();
			});
			
			$(".StencilImg").hover(function(){
				$(this).find("span").show();
			},function(){
				$(this).find("span").hide();
			});
			
			/* 模版 */
			$(".BlueCer dd").hover(function(){
				$(this).find("label").show();
			},function(){
				$(this).find("label").hide();
			});
			$(".Stencil_11 dd,.Stencil_11 dt").live("mouseover mouseout",function(event){
				if(event.type == "mouseover"){
					$(this).find("label").show();
					$(this).find(".color").css({zIndex:90,height:20});
				}else{
					$(this).find("label").hide();
					$(this).find(".color").css({zIndex:-2,height:20});
				}
			});
			
			$(".BlueCer li").bind('mouseover mouseout',function(event){
				if(event.type == 'mouseover'){
					$(this).css("background","#7fb221");
					$(this).find("a").css("color","#ffffff");
					$(this).find(".Delete").show();
				}else{
					$(this).css("background","");
					$(this).find("a").css("color","");
					$(this).find(".Delete").hide();
				}
			});
			
		
			$(".PinkCer li").hover(function(){
				$(this).css("background","#7fb221");
				$(this).find(".Delete").show();
			},function(){
				$(this).css("background","");
				$(this).find(".Delete").hide();
			});
			$(".PinkFor dd").hover(function(){
				$(this).find("a").css("background","#7fb221");
				$(this).find("a").css("color","#fff");
				$(this).find(".Delete").show();
			},function(){
				$(this).find("a").css("background","");
				$(this).find("a").css("color","");
				$(this).find(".Delete").hide();
			});
			$(".PinkFor dt").hover(function(){
				$(this).find("a").css("background","#7fb221");
				$(this).find(".Delete").show();
			},function(){
				$(this).find("a").css("background","");
				$(this).find(".Delete").hide();
			});
		
			$(".more_content li").bind('mouseover mouseout',function(event){
				if(event.type == 'mouseover'){
					$(this).css("backgroundColor","#f1f1ef");
				}else{
					$(this).css("backgroundColor","#fefefe");
				}
			});
		
			//选择背景弹出框部分
			$("._face1").click(function(){
				$(".up_01").show();
			});
		
			$(".s_bj").hover(function(){
				$(this).css("backgroundPosition","-2px -133px");
			},function(){
				$(this).css("backgroundPosition","-2px -2px");
			});
			
			$(".s_img").hover(function(){
				$(this).css("backgroundPosition","-150px -133px");
			},function(){
				$(this).css("backgroundPosition","-150px -2px");
			});
			
			//删除用弹出框
			var this_artid, this_id;
			$(".list1").live('click',function(){
				this_id = $(this).parents(".drag").attr("id");
				this_artid = $(this).parents(".drag").attr("value");
				$(".del_cont").popup({p_width:'370',p_titletst:'',p_height:'195',p_YN_icon:'n',p_show:'fadein',p_speed:100});
			}); 
			
			$(".del_cont").find("button").live('click',function(){
				$(this).parents("#dhauthordiv").hide().remove();
				
				$.ajax({
					url:"<%=request.getContextPath()%>/system/articleTemplate_deleteJSON.action?time=" + new Date().getTime(),
					type:"post",dataType:"json",
					timeout:10000,
					data:{
		            	"id":this_artid
					},
					success:function(data){
						if(data.code == "ok"){
							$(".Template1_" + this_artid).remove();
							$(".m_title").val("");
							$(".onlyimg,.show_select_tpl,.moreimg,.view_select_tpl").hide();
							$(".listarticle").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #f9f9f9"});
							$(".select_list li").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
						} else {
							$('.picurl').tip({width:'240',status:'error',content:'删除失效！',dis_time:1000});
						}
					}
				});
			});

			 //点击编辑内容(首页) 
			$(".Template1_1").die().live("click",function(){
				$(".MicrositeTxt").hide();
				$("#editorTitle").hide();
				$("#editorPhotos").hide();
				$("#editorContent").show();
				$("#saveSuccess").hide();
				this_artid = $(this).attr("value");
				var isChange = $(this).attr("isChange");
				$.ajax({
					url:"<%=request.getContextPath()%>/system/article_detailJSON.action?time=" + new Date().getTime(),
					type:"post",dataType:"json",
					timeout:10000,
					data:{
		            	"id":this_artid
					},
					success:function(data){
						if(data.code == "ok"){
							$(".m_title").val(data.title);
							if (data.page.pageType == "2") {
								editor.html(data.page.content);
								$(".select_list li").css({backgroundColor:"#f9f9f9",borderBottom:"0px solid #c1c1c1"});
								$(".listarticle").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
								$(".onlyimg,.view_select_tpl").show();
								$(".moreimg,.show_select_tpl").hide();
							} else if (data.page.pageType == '1') {
								editor.html("");
								$("#Template1_1_" + this_artid).attr("isChange", "1");
								$("#changemore").attr("value",data.moban.id);
								$(".list_mb_view").find("img").attr("src",data.moban.templateImage);
								$(".select_list li").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
								$(".listarticle").css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
								$(".onlyimg,.show_select_tpl").hide();
								$(".moreimg,.view_select_tpl").show();
								//data.page.moban.templateImage
							}else if (data.page.pageType == undefined) {
								$(".onlyimg,.show_select_tpl,.moreimg,.view_select_tpl").hide();
								$(".listarticle").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #f9f9f9"});
								$(".select_list li").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
							}
						} else {
							$('.picurl').tip({width:'240',status:'error',content:'页面加载失败！',dis_time:1000});
						} 
					}
				});
			});
	
						
			$(".list_mb_view").hover(function(){
				$(this).find(".mask_tpl_view,#changemore").show();
			},function(){
				$(this).find(".mask_tpl_view,#changemore").hide();
			});
			
		 	$(".closetemlpate").click(function(){
		 		$(".chooseTemple").hide();
		 	});
		 	
		 	$(".count-btn").hover(function(){
		 		$(this).addClass("count-btn_hov");
		 	},function(){
		 		$(this).removeClass("count-btn_hov");
		 	});
		 	$(".count-btn6_m").hover(function(){
		 		$(this).addClass("count-btn6_m_hov");
		 	},function(){
		 		$(this).removeClass("count-btn6_m_hov");
		 	});
		 	$(".count-btn2").hover(function(){
		 		$(this).addClass("count-btn_hov2");
		 	},function(){
		 		$(this).removeClass("count-btn_hov2");
		 	});
			//添加模块
			$(".count-btn").click(function(){
				$(".mb3").show();
			});
		
			$(".count-btn2").bind('click',function(){
			 	var newIndex2 = $(".master02").find("li").last().attr("position");
				if($(".master02 li").length<1){
					newIndex2=0;
				}
			 	$.ajax({
					url:"websitestyle.action",
						type:"post",dataType:"json",
						timeout:10000,
						data:{
							"op":"addmodule",
			            	"position":newIndex2
						},
						success:function(data){
							if(data.code =="A00006"){
								var _onlyli = '<li isChange="0" class="Template1_1 Template1_1_'+data.articleid+' drag" position="'+data.position+'" value="'+data.articleid+'" >\
												<a value="'+data.articleid+'" content="" >编辑内容</a>\
												<span class="Delete Delete_onlys editcontent" style="display: none;">\
												<img width="30" height="30" src="/website/images/Delete.gif">\
												</span>\
												</li>';
								$(".master02").append(_onlyli);	
										
							}else {
					    		location.href="/login.jsp";
					    	}
						}
				});
			 	
			});
		
			$(".close_addtmp").click(function(){
				$(this).parents(".mb3").hide();
				$(".secondmb3").hide();
			});
		
			//重新选择模板
		 	$("._face2").die().live("click",function(){
		 		$(".chooseTemple").show();
		 		var pagebegin = 1; 		
		 		$.ajax({
		 			url:"<%=request.getContextPath()%>/system/pageTemplate_getAllTemplate.action?time=" + new Date().getTime(),
		    		type: "POST",
		    		dataType: "json",
		    		timeout: 100000,
		    		data:{
		 				"pageIndex":pagebegin
		    		},
		    		success:function(data){
						if( data.code == "ok" ){
							$(".show_tmp").empty();
							var pages = data.pages;
							for (var i = 0; i < pages.length; i++) {
								var packageid = $(".packageId").val();
								var page = pages[i];
								if(packageid >= 2){
									var pictirl = '<div class="list_mb"><img class="changeone" value="' + page.id + '" style="float:left; height:360px; width:220px;" src="' + page.templateImage + '" /></div>';
									$(".show_tmp").append(pictirl);
								} else {
									if(page.templateType == 2){
										var pictirl = '<div class="list_mb"><img class="changeone" value="' + page.id + '" style="float:left; height:360px; width:220px;" src="'+page.templateImage+'" /><div class="busi_mask">商务版</div></div>';
									} else {
										var pictirl = '<div class="list_mb"><img class="changeone" value="' + page.id + '" style="float:left; height:360px; width:220px;" src="'+page.templateImage+'" /></div>';
									}
									$(".show_tmp").append(pictirl);
								}
							}
							//分页
							$(".Pageing").empty();
							if(Number(data.pageIndex) > 1){
								$(".Pageing").append('<a class="masspage" pagebegin=' + Number(data.pageIndex - 1) + '>上一页</a>' +
									'<div class="ChooseLt" pagebegin='+Number(data.pageIndex-1)+'><img src="<%=request.getContextPath()%>/public/wxmuban/images/ChooseLt.png" width="12" height="19" /></div>');
		                	}
							for(var i = data.showPageHead; i <= data.showPageEnd; i++){
								if(i == data.pageIndex){
									$(".Pageing").append('<a style="background-color: #809C01;" class="masspage" pagebegin='+i+'>'+i+'</a>');
								} else {
									$(".Pageing").append('<a class="masspage" pagebegin='+i+'>'+i+'</a>');
								}
							}
		                	if(data.pageCount >= data.pageIndex && Number(data.pageIndex + 1) <= data.pageCount){
		                		$(".Pageing").append('<a  class="masspage" pagebegin="">下一页</a>'+
		                			'<div class="ChooseRt" pagebegin='+Number(data.pageIndex+1)+'><img src="<%=request.getContextPath()%>/public/wxmuban/images/ChooseRt.png" width="12" height="19" /></div>');
		                	}
						} else {
							$('.picurl').tip({width:'240',status:'error',content:'模板加载失败！',dis_time:1000});
				    	}
		    		}
		 		});
			 });

		 	//分页箭头
		 	$(".ChooseRt,.ChooseLt").die().live("mouseover mouseout",function(event){
		 		if(event.type == "mouseover"){
		 			$(this).addClass("ChooseOver");
		 	 	}else{
		 	   		$(this).removeClass("ChooseOver");
		 	   	}
		 	});

		 	//选择模板分页
			$(".Pageing").find(".masspage,.ChooseRt,.ChooseLt").die().live("click",function(){
				pagebegin = $(this).attr("pagebegin");
				$.ajax({
		 			url:"<%=request.getContextPath()%>/system/pageTemplate_getAllTemplate.action?time=" + new Date().getTime(),
		    		type: "POST",
		    		dataType: "json",
		    		timeout: 100000,
		    		data:{
		 				"pageIndex":pagebegin
		    		},
		    		success:function(data){
						if( data.code == "ok" ){
							$(".show_tmp").empty();
							var pages = data.pages;
							for (var i = 0; i < pages.length; i++) {
								var packageid = $(".packageId").val();
								var page = pages[i];
								if(packageid >= 2){
									var pictirl = '<div class="list_mb"><img class="changeone" value="' + page.id + '" style="float:left; height:360px; width:220px;" src="' + page.templateImage + '" /></div>';
									$(".show_tmp").append(pictirl);
								} else {
									if(page.templateType == 2){
										var pictirl = '<div class="list_mb"><img class="changeone" value="' + page.id + '" style="float:left; height:360px; width:220px;" src="'+page.templateImage+'" /><div class="busi_mask">商务版</div></div>';
									} else {
										var pictirl = '<div class="list_mb"><img class="changeone" value="' + page.id + '" style="float:left; height:360px; width:220px;" src="'+page.templateImage+'" /></div>';
									}
									$(".show_tmp").append(pictirl);
								}
							}
							//分页
							$(".Pageing").empty();
							if(Number(data.pageIndex) > 1){
								$(".Pageing").append('<a class="masspage" pagebegin=' + Number(data.pageIndex - 1) + '>上一页</a>' +
									'<div class="ChooseLt" pagebegin='+Number(data.pageIndex-1)+'><img src="<%=request.getContextPath()%>/public/wxmuban/images/ChooseLt.png" width="12" height="19" /></div>');
		                	}
							for(var i = data.showPageHead; i <= data.showPageEnd; i++){
								if(i == data.pageIndex){
									$(".Pageing").append('<a style="background-color: #809C01;" class="masspage" pagebegin='+i+'>'+i+'</a>');
								} else {
									$(".Pageing").append('<a class="masspage" pagebegin='+i+'>'+i+'</a>');
								}
							}
		                	if(data.pageCount >= data.pageIndex && Number(data.pageIndex + 1) <= data.pageCount){
		                		$(".Pageing").append('<a  class="masspage" pagebegin="">下一页</a>'+
		                			'<div class="ChooseRt" pagebegin='+Number(data.pageIndex+1)+'><img src="<%=request.getContextPath()%>/public/wxmuban/images/ChooseRt.png" width="12" height="19" /></div>');
		                	}
						} else {
							$('.picurl').tip({width:'240',status:'error',content:'模板加载失败！',dis_time:1000});
				    	}
		    		}
		 		});
			});	

			//点击编辑多内容
			$(".listarticle,.changemodule").die().live("click",function(){
				$(".select_list li").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
				$(".listarticle").css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
				var a = $("#Template1_1_" + this_artid).attr("isChange");
				if ($("#Template1_1_" + this_artid).attr("isChange") == "1") {
					$(".onlyimg,.show_select_tpl").hide();
					$(".moreimg,.view_select_tpl").show();
					return; 
				}
				$(".onlyimg,.view_select_tpl").hide();
				$(".moreimg,.show_select_tpl").show();
		 		var pagebegin = 1; 		
		 		$.ajax({
		 			url:"<%=request.getContextPath()%>/system/pageTemplate_getAllTemplate.action?time=" + new Date().getTime(),
		    		type: "POST",
		    		dataType: "json",
		    		timeout:10000,
		    		data:{
		 				"pageIndex":pagebegin
		    		},
		    		success:function(data){
						if( data.code == "ok" ){
							$(".moreimg_tpl").empty();
							var pages = data.pages;
							for (var i = 0; i < pages.length; i++) {
								var packageid =$(".packageId").val();
								var page = pages[i];
								if(packageid >= 2){
									var pictirl = '<div class="list_mb2"><img class="changesecond" value="' + page.id + '" style="float:left; height:217px; width:155px;;" src="'+page.templateImage+'" /></div>';
									$(".moreimg_tpl").append(pictirl);
								}else {
									if(page.templateType == 2){
										var pictirl = '<div class="list_mb2"><img style="float:left; height:217px;width:155px;" src="'+page.templateImage+'" /><div class="busi_mask" style="line-height:250px;">商务版</div></div>';
									} else{
										var pictirl = '<div class="list_mb2"><img class="changesecond" value="'+page.id+'" style="float:left; height:217px; width:155px;" src="'+page.templateImage+'" /></div>';
									}
									$(".moreimg_tpl").append(pictirl);
								}
							}									
							//分页
							$(".Pageing1").empty();
							if(Number(data.pageIndex)>1){
								$(".Pageing1").append('<a class="masspage" pagebegin='+Number(data.pageIndex-1)+'>上一页</a>');
		                	}
							
							for(var i=data.showPageHead;i<=data.showPageEnd;i++){
								if(i==data.pageIndex){
									$(".Pageing1").append('<a style="background-color: #809C01;" class="masspage" pagebegin='+i+'>'+i+'</a>');
								}else{
									$(".Pageing1").append('<a class="masspage" pagebegin='+i+'>'+i+'</a>');
								}
								
								
							}
		                	if(data.pageCount>=data.pageIndex && Number(data.pageIndex+1)<=data.pageCount){
		                		$(".Pageing1").append('<a  class="masspage" pagebegin='+Number(data.pageIndex+1)+'>下一页</a>');
		                	}
						} else {
							$('.picurl').tip({width:'240',status:'error',content:'模板加载失败！',dis_time:1000});
						}
		    		}
		 		});
		 	});

			//链接模板分页
			 $(".Pageing1").find(".masspage").die().live("click",function(){
			  	pagebegin = $(this).attr("pagebegin");
			  	$.ajax({
		 			url:"<%=request.getContextPath()%>/system/pageTemplate_getAllTemplate.action?time=" + new Date().getTime(),
		    		type: "POST",
		    		dataType: "json",
		    		timeout:10000,
		    		data:{
		 				"pageIndex":pagebegin
		    		},
		    		success:function(data){
						if( data.code == "ok" ){
							$(".moreimg_tpl").empty();
							var pages = data.pages;
							for (var i = 0; i < pages.length; i++) {
								var packageid =$(".packageId").val();
								var page = pages[i];
								if(packageid >= 2){
									var pictirl = '<div class="list_mb2"><img class="changesecond" value="' + page.id + '" style="float:left; height:217px; width:155px;;" src="'+page.templateImage+'" /></div>';
									$(".moreimg_tpl").append(pictirl);
								}else {
									if(page.templateType == 2){
										var pictirl = '<div class="list_mb2"><img style="float:left; height:217px;width:155px;" src="'+page.templateImage+'" /><div class="busi_mask" style="line-height:250px;">商务版</div></div>';
									} else{
										var pictirl = '<div class="list_mb2"><img class="changesecond" value="'+page.id+'" style="float:left; height:217px; width:155px;" src="'+page.templateImage+'" /></div>';
									}
									$(".moreimg_tpl").append(pictirl);
								}
							}									
							//分页
							$(".Pageing1").empty();
							if(Number(data.pageIndex)>1){
								$(".Pageing1").append('<a class="masspage" pagebegin='+Number(data.pageIndex-1)+'>上一页</a>');
		                	}
							
							for(var i=data.showPageHead;i<=data.showPageEnd;i++){
								if(i==data.pageIndex){
									$(".Pageing1").append('<a style="background-color: #809C01;" class="masspage" pagebegin='+i+'>'+i+'</a>');
								}else{
									$(".Pageing1").append('<a class="masspage" pagebegin='+i+'>'+i+'</a>');
								}
								
								
							}
		                	if(data.pageCount>=data.pageIndex && Number(data.pageIndex+1)<=data.pageCount){
		                		$(".Pageing1").append('<a  class="masspage" pagebegin='+Number(data.pageIndex+1)+'>下一页</a>');
		                	}
						} else {
							$('.picurl').tip({width:'240',status:'error',content:'模板加载失败！',dis_time:1000});
						}
		    		}
		 		});
			 });


			$(".changeone").die().live('click',function(){	
				var oneid = $(this).attr("value");
				$(".chooseTemple,.bgImg_arr,.bgImg_home").hide();
				$.ajax({
					url:"<%=request.getContextPath()%>/system/pageTemplate_changePageTemplate.action?time=" + new Date().getTime(),
			    	type: "POST",
		    		dataType: "json",
		    		timeout: 100000,
		    		data:{
		    			"id":oneid,
		    			"pageId":$("#pageId").val()
					},
					success:function(data){
						if(data.code == "ok"){
							var vo = data;
							$(".StencilNav").css("backgroundColor", vo.titleBgColor);
							
							$(".mb2").css("backgroundImage","url(" + vo.bgImage + ")");
							$(".MicrositeLt").css("backgroundImage","url(" + vo.bgImage + ")");
							var articles = vo.article, paths = vo.path, s = "";
							if (articles != null && articles.length > 0) {
								for (var i = 0; i < articles.length; i++) {
									var article = articles[i];
									s += '<dd isChange="0" class="Template1_1 drag Template1_' + article.id + '" position="' + article.position + '" id="Template1_1_' + article.id + '" ' + 
											'style="background-color:' + article.bgColor + ';' + article.style + '"); background-position: right center; ' +
											'background-repeat: no-repeat;" value="' + article.id + '">' + 
		
		                    				'<span style="' + article.style + '">' + article.title + '</span>' + 
		                    					'<input style="background-image: none; background-color: rgb(255, 255, 255); color: rgb(0, 0, 0); ' +
		                    					'z-index: -2; height: 20px;" autocomplete="off" class="color" value="FFFFFF">' + 
		                        			'<label class="editcontent" style="' + article.style + '">编辑内容' +
		                        				'<em class="templbtn">' + 
					                        		'<ul>' + 
						                        		'<li class="list1" title="删除编辑内容"></li>' + 
						                        		'<li class="list2" title="改变版块颜色"></li>' + 
						                        		'<li class="list3" arctileid="' + article.id + '" title="改变版块形状"></li>' + 
						                        	'</ul>' + 
						                        '</em>' + 
											'</label>' + 
			                    		'</dd>';
								}
							} 
							$("#sortable1").html(s);
							if (paths != null && paths.length > 0) {
								var p = '<ul>'; 
								for (var i = 0; i < paths.length; i++) {
									var path = paths[i];
									p += '<li class="path_index" pageId="' + path.id + '"><a href="javascript:void(0);">' + path.title + '</a></li>';
								}
								p += '</ul>';
								$('.page_path').html(p);
							}
						}
					}
			 	});
			 });
				
			$(".changesecond,#changemore").die().live('click',function(){	
				var secondid = $(this).attr("value");
				var save_title =$(".m_title").val();
				if($.trim(save_title)==""){
					$('.picurl').tip({width:'240',status:'error',content:'内容标题不能为空！',dis_time:1000});
					return false;
				}
				$.ajax({
					url:"<%=request.getContextPath()%>/system/article_updateArticle.action?time="+new Date().getTime(),
			   		type:"post",dataType:"json",
			   		timeout:10000,
			   		data:{
			             "isLinkPage":'1',
			             "id":this_artid,
			             "title":save_title,
			             "templateId":secondid
			   		},
					success:function(data){
			   			var vo = data;
						if(vo.code == "ok") {
							$("#pageId").val(vo.id);
							$(".webtitle").val(vo.title);
							$(".second_title2").find("span").html(vo.title);
							$(".StencilNav").css("backgroundColor", vo.titleBgColor);
							$(".mb2").css("backgroundImage","url(" + vo.bgImage + ")");
							$(".MicrositeLt").css("backgroundImage","url(" + vo.bgImage + ")");
							$(".uploadWeb").attr("src", vo.bannerImage);
							var articles = vo.article, paths = vo.path, s = "";
							if (articles != null && articles.length > 0) {
								for (var i = 0; i < articles.length; i++) {
									var article = articles[i];
									s += '<dd isChange="0" class="Template1_1 drag Template1_' + article.id + '" position="' + article.position + '" id="Template1_1_' + article.id + '" ' + 
											'style="background-color:' + article.bgColor + ';' + article.style + '"); background-position: right center; ' +
											'background-repeat: no-repeat;" value="' + article.id + '">' + 
		
		                    				'<span style="' + article.style + '">' + article.title + '</span>' + 
		                    					'<input style="background-image: none; background-color: rgb(255, 255, 255); color: rgb(0, 0, 0); ' +
		                    					'z-index: -2; height: 20px;" autocomplete="off" class="color" value="FFFFFF">' + 
		                        			'<label class="editcontent" style="' + article.style + '">编辑内容' +
		                        				'<em class="templbtn">' + 
					                        		'<ul>' + 
						                        		'<li class="list1" title="删除编辑内容"></li>' + 
						                        		'<li class="list2" title="改变版块颜色"></li>' + 
						                        		'<li class="list3" arctileid="' + article.id + '" title="改变版块形状"></li>' + 
						                        	'</ul>' + 
						                        '</em>' + 
											'</label>' + 
			                    		'</dd>';
								}
							} 
							
							$("#sortable1").html(s);
							$('#titleColor').html("");
							if (paths != null && paths.length > 0) {
								var p = '<ul>'; 
								if (paths.length > 1) {
									var pos = paths.length - 2;
									$(".bgImg_arr").attr("pageId",paths[pos].id);
									$(".bgImg_arr,.bgImg_home").show();
								} 
								$(".bgImg_home").attr("pageId",paths[0].id);
								for (var i = 0; i < paths.length; i++) {
									var path = paths[i];
									if(i=='0'){
										p += '<li class="path_index" pageId="' + path.id + '"><a href="javascript:void(0);">' + path.title + '</a></li>';
									} else {
										p += '<li class="li_arr">></li><li class="path_index" pageId="' + path.id + '"><a href="javascript:void(0);">' + path.title + '</a></li>';
									}
								}
								p += '</ul>';
								$('.page_path').html(p);
							}
							$(".onlyimg,.show_select_tpl,.moreimg,.view_select_tpl").hide();
							$(".listarticle").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #f9f9f9"});
							$(".select_list li").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
						}
					}
			 	});
			});
			
		 	//点击编辑单一内容
			$(".singlearticle").die().live("click",function(){
				$(".select_list li").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
				$(this).css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
				$(".moreimg").hide();
				$(".onlyimg").show();
				$(".add_active li").eq(0).click();
			});
	
			
		
		 	//添加模块
			$(".add_temp dd,.add_temp dt").click(function(){
	 			var this_obj =  $(this).clone();
	 		   	var this_U = $(this).attr("U_style");
	 		   	var this_Ustyle;
	 		   	if(this_U == "1"){
	 		  		this_Ustyle = 'width:140px;height:092px;line-height:092px;'; 
	 		   	} else if(this_U == "2"){
	 		    	this_Ustyle = 'width:293px;height:092px;line-height:092px;'; 
	 		   	} else if(this_U == "3"){
	 		    	this_Ustyle = 'width:290px;height:044px;line-height:044px;background-image:url("<%=path%>/public/wxmuban/images/stencil_tb1.png");background-position: right center;background-repeat: no-repeat;';
	 		   	} else if(this_U == "4"){
	 		   		this_Ustyle = 'width:185px;height:042px;line-height:042px;background-image:url("<%=path%>/public/wxmuban/images/stencil_tb.png"); background-position: right center; background-repeat: no-repeat;border: 1px solid #B6B9C6 !important;';
	 		   	} else if(this_U == "5"){
	 		   		this_Ustyle = 'width:092px;height:098px;line-height:098px;'; 
	 		   	} 
	 		   	
	 		   	$(".master").append(this_obj);
	 		   	var newIndex = $(".master").find(".drag").last().attr("position");
	 		   	if($(".master").find(".drag").length < 1){
	 		   		newIndex = 0;
	 		   	} 
 		   
	 		   	$.ajax({
	 		    	url:"<%=request.getContextPath()%>/system/page_addArticle.action?time=" + new Date().getTime(),
	 		      	type:"post",dataType:"json",
	 		      	timeout:10000,
	 		      	data:{
	 	 		      	"pageId":$("#pageId").val(),
	 		       		"style":this_Ustyle,
	 		       		"title":'编辑内容',
	 		           	"position":newIndex,
	 		           	"bgcolor":"#F5F2F2"
	 		      	},
	 		      	success:function(data){
	 	 		      	if (data.code == "ok") {
		 		      		var Default = '<input class="color" value="" style="z-index: -2; height: 20px;"/>' +
				                          '<label class="editcontent" style=' + data.style + '>编辑内容' + 
				                           '<em class="templbtn">' +
				                           	'<ul>' +
				                            	'<li class="list1" title="删除编辑内容"></li>' +
					                            '<li class="list2" title="改变版块颜色"></li>' +
					                            '<li class="list3" arctileid=' + data.id + ' title="改变版块形状"></li>' +
				                           	'</ul>' +
				                           '</em>' +
				       					  '</label>';
					       	$(".master").find(".bgcol").find("span").attr("style",data.style);
					       	$(".master").find(".bgcol").append(Default).attr({"value":data.id,"position":data.position,"style":data.style,"class":"drag Template1_"+data.id,"id":"Template1_1_"+data.id+""}).addClass("Template1_1").css("backgroundColor","#F5F2F2");
					        
					       	jscolor.init();
	 	 		      	}
	 				}
	 		    });
			});
		
			//修改模块形状
			$(".module_demo").click(function(){
				$("#dhauthordiv").remove();
				var this_U = $(this).attr("U_style");
				var this_id = $(".select_module").attr("articleid");
				var this_Ustyle;
				if(this_U == "1"){
	 		  		this_Ustyle = 'width:140px;height:092px;line-height:092px;'; 
	 		   	} else if(this_U == "2"){
	 		    	this_Ustyle = 'width:293px;height:092px;line-height:092px;'; 
	 		   	} else if(this_U == "3"){
	 		    	this_Ustyle = 'width:290px;height:044px;line-height:044px;background-image:url("<%=path%>/public/wxmuban/images/stencil_tb1.png");background-position: right center;background-repeat: no-repeat;';
	 		   	} else if(this_U == "4"){
	 		   		this_Ustyle = 'width:185px;height:042px;line-height:042px;background-image:url("<%=path%>/public/wxmuban/images/stencil_tb.png"); background-position: right center; background-repeat: no-repeat;border: 1px solid #B6B9C6 !important;';
	 		   	} else if(this_U == "5"){
	 		   		this_Ustyle = 'width:092px;height:098px;line-height:098px;'; 
	 		   	} 
				
			 	$.ajax({
			 		url:"<%=request.getContextPath()%>/system/articleTemplate_modifyStyle.action?time="+new Date().getTime(),
				  	type:"post",dataType:"json",
				  	timeout:10000,
				  	data:{
				   		"style":this_Ustyle,
				    	"id":this_id
				  	},
				  	success:function(data){
				   		if(data.code=="ok"){
						    $(".Template1_"+this_id).attr("style",data.style);
						    $(".Template1_"+this_id).css("backgroundColor",data.bgColor);
						    $(".Template1_"+this_id).find("span").attr("style",data.style);
						    $(".Template1_"+this_id).find("label").attr("style",data.style);
				   		}
					}
				});
			});
			//改变颜色
			$(".color").die().live('change',function(){
				var this_color = this.value , this_id = $(this).parent().attr("value");
				$(this).parent().css("backgroundColor","#"+this_color);
				if (this_id == "isPageHeader") {
					$.ajax({
						url:"<%=request.getContextPath()%>/system/page_modifyHeaderColor.action?time="+new Date().getTime(),
						type:"post",dataType:"json",
						timeout:10000,
						data:{ 
			            	"titleBgColor":"#" + this_color,
			            	"id":$("#pageId").val(),
						},
						success:function(){
						}
					});
				} else {
					$.ajax({
						url:"<%=request.getContextPath()%>/system/article_modifyColor.action?time="+new Date().getTime(),
						type:"post",dataType:"json",
						timeout:10000,
						data:{ 
			            	"bgColor":"#" + this_color,
			            	"id":this_id
						},
						success:function(){
						}
					});
				}
			});	

			//点击导航页面
			$(".path_index,.bgImg_home,.bgImg_arr").die().live('click',function(){
				var pageId = $(this).attr("pageId");
				$.ajax({
					url:"<%=request.getContextPath()%>/system/page_changePage.action?time="+new Date().getTime(),
					type:"post",dataType:"json",
					timeout:10000,
					data:{id:pageId},
					success:function(data){
						var vo = data;
						if(vo.code == "ok") {
							$("#pageId").val(vo.id);
							$(".webtitle").val(vo.title);
							$(".second_title2").find("span").html(vo.title);
							$(".StencilNav").css("backgroundColor", vo.titleBgColor);
							$(".mb2").css("backgroundImage","url(" + vo.bgImage + ")");
							$(".MicrositeLt").css("backgroundImage","url(" + vo.bgImage + ")");
							$(".uploadWeb").attr("src", vo.bannerImage);
							var articles = vo.article, paths = vo.path, s = "";
							if (articles != null && articles.length > 0) {
								for (var i = 0; i < articles.length; i++) {
									var article = articles[i];
									s += '<dd isChange="0" class="Template1_1 drag Template1_' + article.id + '" position="' + article.position + '" id="Template1_1_' + article.id + '" ' + 
											'style="background-color:' + article.bgColor + ';' + article.style + '"); background-position: right center; ' +
											'background-repeat: no-repeat;" value="' + article.id + '">' + 
		
		                    				'<span style="' + article.style + '">' + article.title + '</span>' + 
		                    					'<input style="background-image: none; background-color: rgb(255, 255, 255); color: rgb(0, 0, 0); ' +
		                    					'z-index: -2; height: 20px;" autocomplete="off" class="color" value="FFFFFF">' + 
		                        			'<label class="editcontent" style="' + article.style + '">编辑内容' +
		                        				'<em class="templbtn">' + 
					                        		'<ul>' + 
						                        		'<li class="list1" title="删除编辑内容"></li>' + 
						                        		'<li class="list2" title="改变版块颜色"></li>' + 
						                        		'<li class="list3" arctileid="' + article.id + '" title="改变版块形状"></li>' + 
						                        	'</ul>' + 
						                        '</em>' + 
											'</label>' + 
			                    		'</dd>';
								}
							} 
							$("#sortable1").html(s);
							if (paths != null && paths.length > 0) {
								var p = '<ul>'; 
								for (var i = 0; i < paths.length; i++) {
									var path = paths[i];
									if (paths.length > 1) {
										var pos = paths.length - 2;
										$(".bgImg_arr").attr("pageId",paths[pos].id);
										$(".bgImg_arr,.bgImg_home").show();
									} 
									$(".bgImg_home").attr("pageId",paths[0].id);
									if(i=='0'){
										$(".bgImg_arr,.bgImg_home").hide();
										p += '<li class="path_index" pageId="' + path.id + '"><a href="javascript:void(0);">' + path.title + '</a></li>';
									} else {
									    $(".bgImg_arr,.bgImg_home").show();
										p += '<li class="li_arr">></li><li class="path_index" pageId="' + path.id + '"><a href="javascript:void(0);">' + path.title + '</a></li>';
									}
								}
								p += '</ul>';
								$('.page_path').html(p);
							}
							
							
			              	jscolor.init();
						} else {
							$('.picurl').tip({width:'240',status:'error',content:'页面获取失败！',dis_time:1000});
						}
					}
				});
			});
			
			//保存内容
			$(".save_onlycont").die().live('click',function(){
				var teatarea_matter = editor.html();
			  	var save_title =$(".m_title").val();
			  	if($.trim(save_title)==""){
			   		$('.picurl').tip({width:'240',status:'error',content:'内容标题不能为空！',dis_time:1000});
			   		return false;
			  	}
			  	$.ajax({
			  		url:"<%=request.getContextPath()%>/system/article_updateArticle.action?time="+new Date().getTime(),
			   		type:"post",dataType:"json",
			   		timeout:10000,
			   		data:{
			             "isLinkPage":'0',
			             "id":this_artid,
			             "title":save_title,
			             "content":teatarea_matter
			   		},
			   		success:function(data){
			    		if(data.code == "ok"){
						    $(".addarticle,.MicrosSelect").hide();
						    $("._face4").attr("articleid",data.id);
						    $(".MicrSuccess").show();
						    //$(".Template1_" + data.id).find(".et_demo6").attr("content",data.title);
							//$(".Template1_1_" + data.id).find("a").html(data.title);
						    $(".Template1_" + data.id).find("span").html(data.title);
						    //$(".Template1_1_"+data.id).find("a").attr("content",data.title);
						    //$(".Template1_"+data.id).find(".list4").attr("content",data.title);
						    //$(".Template1_"+data.id).find("strong").html(data.title);
			    		} else {
			    			$('.picurl').tip({width:'240',status:'error',content:'添加失败！',dis_time:1000});
			       		}
			   		}
			  	});
			 });
		});

		var editor;
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
			editor = K.create('#edit_matter_05', {
				themeType : 'qq',
				items : [
					'bold','italic','underline','fontname','fontsize','forecolor','hilitecolor','plug-align','plug-order','plug-indent','link'
				]
			});
		});

		function load(img_id) {
			$.ajax({
		  		url:"<%=request.getContextPath()%>/system/pageTemplate_getTemplate.action",
		   		type:"post",dataType:"json",
		   		timeout:10000,
		   		data:{id:img_id},
		   		success:function(data){
					if(data[0].code == "ok") {
						var s = "";
						for (var i = 0; i < data.length; i++) {
							var article = data[i];
							s += '<dd isChange="0" class="Template1_1 drag Template1_' + article.id + '" position="' + article.position + '" id="Template1_1_' + article.id + '" ' + 
									'style="background-color:' + article.bgColor + ';' + article.style + '");background-position: right center; ' +
									'background-repeat: no-repeat;" value="' + article.id + '">' + 
	                    			'<span style="' + article.style + '">' + article.title + '</span>' + 
	                    				'<input style="background-image: none; background-color: rgb(255, 255, 255); color: rgb(0, 0, 0); ' +
	                    				'z-index: -2; height: 20px;" autocomplete="off" class="color" value="FFFFFF">' + 
	                        		'<label class="editcontent" style="' + article.style + '">编辑内容' +
	                        			'<em class="templbtn">' + 
				                        	'<ul>' + 
					                        	'<li class="list1" title="删除编辑内容"></li>' + 
					                        	'<li class="list2" title="改变版块颜色"></li>' + 
					                        	'<li class="list3" arctileid="' + article.id + '" title="改变版块形状"></li>' + 
					                        '</ul>' + 
					                       '</em>' + 
									'</label>' + 
		                    	'</dd>';
	
						}
						$("#sortable1").html(s);
						var h ='<label style="display: none;" class="editweb">编辑微网站标题'+
			       	           '<em class="templbtn">'+
			       			      '<ul>'+
						             '<li class="list2" title="改变版块颜色"></li>'+
						          '</ul>'+
						       '</em>'+
					           '</label>';
					    $('#titleColor').html(h);
		              	jscolor.init();
					} else {
						$('.picurl').tip({width:'240',status:'error',content:'页面获取失败！',dis_time:1000});
					}
		   		}
		  	});
		}
		
		//上传图片
		var nowtuwen=0;
		function ajaxFileUpload3(){
			var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
			if (! reg.test($("#mp3fileupload3").val())){
				$(".alertSimpleContent").html("图片格式必须为jpg、jpeg、png、bmp! ");
		    	$(".alertSimple").show();
		    	$("#upload_01").val("");
				return false;
			}
		    $.ajaxFileUpload({
		    	url:'<%=path %>/system/resource_uploadPageImage.action',  
		      	secureuri:false,
		      	data:{"pageId":$("#pageId").val()},
		      	fileElementId:'mp3fileupload3',                         //文件选择框的id属性
		      	dataType: 'json',                                     //服务器返回的格式，可以是json
		      	complete:function(){
					$("#loading").hide();
				},				
				success: function (data, status) {
					if(data.status == "success"){
						$(".mb2").css("backgroundImage","url(" + data.url + ")");
						$(".MicrositeLt").css("backgroundImage","url(" + data.url + ")");
						$(".close_up1").click();
					} else {
						$('.picurl').tip({width:'240',status:'error',content:'设置失败！',dis_time:1000});
			    	}
				},
				error: function (data, status, e){
					alert(e);
				}
		    });
		}
		
		//上传图片
		function ajaxFileUpload4(){
			var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
			if (! reg.test($("#mp3fileupload4").val())){
				$(".alertSimpleContent").html("图片格式必须为jpg、jpeg、png、bmp! ");
		    	$(".alertSimple").show();
		    	$("#upload_01").val("");
				return false;
			}
		    $.ajaxFileUpload({
		    	url:'<%=path %>/system/resource_uploadPageBannerImage.action',  
		      	secureuri:false,
		      	data:{"pageId":$("#pageId").val()},
		      	fileElementId:'mp3fileupload4',                         //文件选择框的id属性
		      	dataType: 'json',                                     //服务器返回的格式，可以是json
		      	complete:function(){
					$("#loading").hide();
				},				
				success: function (data, status) {
					if(data.status == "success"){
						$(".uploadWeb").attr("src", data.url);
					} else {
						$('.picurl').tip({width:'240',status:'error',content:'设置失败！',dis_time:1000});
			    	}
				},
				error: function (data, status, e){
					alert(e);
				}
		    });
	}	
		
		
					$(".moban_style ul li").die().live('click',function(){
						        var li = $(this).attr("li");
						        for(var i=1;i<=3;i++) {
						            if(i==li) {
						            	$(".moban_style_li"+i).addClass("moban_style_color");
						            	$(".moban_style_div_"+i).show();
						            } else {
						            	$(".moban_style_li"+i).removeClass("moban_style_color");
						            	$(".moban_style_div_"+i).hide();
						            }
						        }
					 	});
						
											 	function changeSize(l,t){
					 	    var u = $("#"+t+"_size_px").val();
					 	    if ( u == '像素') {
					 	    	u = 'px';
					 	    }
					 	    
					 	    if ( t == 'w') {
					 	    	var w = $("#"+l+"_size_w1").val();
					 	    	if ( l == 'img' ){
					 	    		$("#"+l+"Change").attr("width",w+u);
					 	    	} else {
					 	    	    $("#textChange").css("width",w+u);
					 	    	}
					 	    	
					 	    } else {
					 	    	var h = $("#"+l+"_size_h1").val();
					 	    	if ( l == 'img' ){
					 	    		$("#"+l+"Change").attr("height",h+u);
					 	    	} else {
					 	    	alert(1);
					 	    	 //h  $(".textSize").css("height",h+u);
					 	    	   $(".textSize").style.height= h+u;
					 	    	}
					 	    	
					 	    }
					 	}
						 
						function imgSizeUnit(t){
						    var u = $("#"+ t +"_unit").val();
						    if ( u == '0') {
						    	$("#"+ t +"_size_px").html('像素');
						    	$("#"+ t +"_size_px_1").html('像素');
						    } else {
						    	$("#"+ t +"_size_px").html('%');
						    	$("#"+ t +"_size_px_1").html('%');
						    }
						
						}
						
						function sureChange(t) {
							
						}
						
						
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".alertSimpleColose").click(function(){
				$(".alertSimple").hide();
		});
		});
		function intoMObanCr(){
		$(".right_centent").hide();
			$(".Microsite").show();
	}
	</script>
</head>
<body>
	<div class="headerPart">
		<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
		<span><font class="font1">当前位置:</font><font class="menuName_1">编辑模版</font></span>
   	</div>
   	
	<div class="right_centent">
		<div class="sort">
		<div class="main">
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
					<input class="voiceM42722" value="http://www.weduty.com//res/knowbusi.mp3" type="hidden">
					<!-- 显示模板 -->
					<div class="content">
						<div style="display: block;" class="container tuwen_matter switchshow">
						<div class="creatitem">
							<dl>
								<dt>
									<font>创建模板：</font>
								</dt>
								<dd style="height: 23px; line-height: normal; margin-top: 23px;">
									<input class="addArticlesDirect" value="0" type="hidden">
									<input id="only_tuwen" value="创建" type="button">
								</dd>
							</dl>
						</div>
						<dl class="newshow">
							<dt style="font-size:12pt;">
								<span>模板</span><strong style="color: red;">(<label class="imgCounts"  id="imgCounts_1">0</label>)</strong>
							</dt>
							<dd>
								<ul>
									<div id="pics_show"></div>
								</ul>
							</dd>
						</dl>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div> 
    <input value="0" class="packageId" type="hidden">
	  <div class="Microsite" style="display:none;">
	    <div class="_selectface">
			<dl>
				<dd class="_face1" title="更换背景图片"></dd>
			</dl>
		</div>
    	<div class="MicrositeLt m_b_1 mb2" style="position: relative; background-image: url('');">
	    	<div class="Stencil_1">
	        	<div class="StencilNav second_title2" value="isPageHeader">
	        		<span>您好</span>
			     	<input style="background-image: none; background-color: rgb(255, 255, 255); color: rgb(0, 0, 0); z-index: -2; height: 20px;"
			         autocomplete="off" class="color" value="FFFFFF">
			      <div id="titleColor"></div>
				</div>
				<div class="bgImg_arr" style="display: none;" pageId=""></div>
				<div class="bgImg_home" style="display: none;" pageId=""></div>
	            <div  class="StencilImg second_piculr2">
	            	<img src="http://1.202.131.149/images/CZ/s20130913101836467_283.jpg" class="uploadWeb" height="100" width="290">
	                <span style="display: none;" class="editindeximg">编辑首页配图</span>
	            </div>
	            
	            <div class="Stencil_11" style="margin-left: 2px;">
	            	<dl id="sortable1" class="master"></dl>
	                <div style=" clear:both; width:100%; height:10px;"></div>
	                <div class="count-btn"></div>
	                <!--添加模版-->
					<div class="MicrositeLt mb3" style="display:none; width: 340px; height: 400px; margin: 0px 0px 0px -170px; padding: 0px; border-radius: 5px 5px 5px 5px; z-index: 999; box-shadow: 0px 0px 10px rgb(221, 190, 161); background-color: rgb(255, 255, 255); border-width: 3px; border-style: solid; border-color: rgb(224, 224, 224); position: fixed; top: 30%; left: 50%;">
						<div style="height: 25px; background-color: rgb(204, 204, 204); background-image: url(/tools/frame/img/title-bg01.png); background-position: 0px -33px; background-repeat: repeat-x;">
							<dl style="margin: 0px; line-height: 25px;">
								<dt style="margin: 0px; line-height: 25px; height: 25px; float: left; font-size: 12px; padding-left: 5px;">添加版块</dt>
								<dd class="close_addtmp" style="margin: 0px; line-height: 25px; height: 25px; width: 20px; float: right; background: url('<%=request.getContextPath()%>/public/images/tag.png') no-repeat scroll -30px 2px transparent; cursor: pointer;" title="关闭"></dd>
							</dl>
						</div>
						<div style="clear:both; width:100%; height:1px;"></div>
						<div class="moban_style">
						     <ul>
						     	<li class="moban_style_li moban_style_li1 moban_style_color" li="1">链接区</li>
						     	<li class="moban_style_li moban_style_li2"  li="2">图片区</li>
						     	<li class="moban_style_li moban_style_li3"  li="3">文本区</li>
						     </ul>
						</div>
						<div class="Stencil_1">
						     <div class="Stencil_11 moban_style_div_1">
					        	<dl id="sortable" class="add_temp">
					            	<dd class="Template1_1 bgcol" u_style="1">
					                	<span>编辑内容</span>
					                </dd>
					                <dd class="Temp_3 bgcol" u_style="5"><span>编辑内容</span></dd>
					                <dt class="bgcol" u_style="2">
					                	<span>编辑内容</span>
					                </dt>
					             	<dd class="Temp_1 bgcol" u_style="3"><span>编辑内容</span></dd>
					             	<dd class="Temp_2 bgcol" u_style="4"><span>编辑内容</span></dd>
					            </dl>
						        <div style=" clear:both; width:100%; height:10px;"></div>
						        </div>
						    </div>
						    <div class="Stencil_11_1 moban_style_div_2" style="display: none;">
						    	<div class="change_size_title">修改尺寸：</div>
						    	<div class="change_size_width">
						    	    <div class="change_size_1">高度：</div>
						    	    <div  class="change_size_2"><input value="290" id="img_size_h1" onkeyup="changeSize('img','h')"/><span class="change_size_1" id="img_size_px">像素</span></div>
						    	</div>
						    	<div class="change_size_heigth">
						    		<div class="change_size_1">宽度：</div>
						    	    <div class="change_size_2"><input value="100" id="img_size_w1" onkeyup="changeSize('img','w')"/><span class="change_size_1" id="img_size_px_1">像素</span></div>
						    	</div>
						    	<div class="change_size_select">
						    		<div class="change_size_1">单位：</div>
						    	    <div class="change_size_2">
						    	        <select id="img_unit" onchange="imgSizeUnit('img');">
						    	    		<option value="0" checked="checked">像素</option>
						    	    		<option value="1">百分比</option>
						    	        </select>
						    	    </div>
						    	</div>
						    	<div class="change_size_img">
						    		<img class="uploadWeb" id="imgChange" width="290px" height="100px" src="/manage/public/wxmuban/images/def_demo.png">
						    	</div>
						    	 <button class="MicrosBtn_1 " onclick="sureChange('img')">确定</button>
						    	 <div style=" clear:both; width:100%; height:10px;"></div>
						    </div>
							<div class="Stencil_11_1 moban_style_div_3" style="display: none;">
								<div class="change_size_title">修改文本域大小：</div>
						    	<div class="change_size_width">
						    	    <div class="change_size_1">高度：</div>
						    	    <div  class="change_size_2"><input value="100" id="text_size_h1"  onkeyup="changeSize('text','h')"/><span class="change_size_1" id="text_size_px">像素</span></div>
						    	</div>
						    	<div class="change_size_heigth">
						    		<div class="change_size_1">宽度：</div>
						    	    <div class="change_size_2"><input value="100" id="text_size_w1"  onkeyup="changeSize('text','w')"/><span class="change_size_1" id="text_size_px_1">像素</span></div>
						    		<div style=" clear:both; width:100%; height:10px;"></div>
						    	</div>
						    	<div class="change_size_select">
						    		<div class="change_size_1">单位：</div>
						    	    <div class="change_size_2">
						    	        <select id="text_unit" onchange="imgSizeUnit('text')">
						    	    		<option value="0">像素</option>
						    	    		<option value="1">百分比</option>
						    	        </select>
						    	    </div>
						    	</div>
						    	<div class="change_size_img">
						    		<div class="textSize" id="textChange"></div>
						    	</div>
						    	<button class="MicrosBtn_1 " onclick="sureChange('text')">确定</button>
						    	<div style="clear:both; width:100%; height:10px;"></div>
							</div>
					  </div>
					  
	            </div>
             </div>
        </div>
	    
	<div class="MicrositeRt">
		<div id="loadpage" class="MicrositeTxt">点击左侧预览图对应区域<br>即可进行编辑哦~</div>
		
		<!-- 编辑标题内容 -->
        <div id="editorTitle" class="MicrosTitle websitetitle" style="display:none;">
        	<dl>
            	<dt><span>微网站名称：</span><input name="" value="您好" class="TitleInp webtitle" type="text"></dt>
                <dd><input value="" class="MicrosBtn editwebsitetitle" type="button"></dd>
            </dl>
        </div>
        <!-- 编辑图片内容 -->
        <div id="editorPhotos" class="AddPhotos indeximg" style="position:relative;">
        	<div style="opacity:0;  filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px;">
        		<input id="mp3fileupload4" onChange="ajaxFileUpload4();" name="imagefile" class="picurl" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" type="file">
        	</div>
        	<input value="" class="AddPhotosBtn " type="button">
            <span class="AddPhotosBtn_15">图片建议 320 x 475 像素</span>
            <!-- <input type="button" value="" class="MicrosBtn" /> -->
        </div>       
        
        <!-- 版块内容编辑 -->
        <div id="editorContent" class="MicrosTitle websitetitle" style="display:none;">
        <div style="clear:both;width:100%; height:35px;"></div>
           	<dl class="add_cont1">
           		<dt><span>内容标题：</span><input class="m_title" style="background-color:#FFFFFF;"  name="" value="" type="text"></dt>
           		<dd><input value="" class="MicrosBtn" type="button"></dd>
           	</dl>
       	</div>
       	
       	<!-- 编辑二级页面  -->
       	
        <div id="editor10" class="MicrosSelect addlistcontent_3" style="display:none;">
            <ul class="select_list">
                <li class="singlearticle singl2" style="border-left:none;">编辑内容</li>
                <li class="listarticle">二级页面</li>
            </ul>
            <div class="onlyimg" style="display:none;">
            	<div style="clear:both;width:100%; height:10px;"></div>
            	<ul class="add_active">
            		<li><input value="1" type="radio"><font>添加内容</font></li>
            		<li><input value="0" type="radio"><font>添加活动</font></li>
            	</ul>
            	<div class="add_none" style="clear:both;width:100%; height:10px;"></div>
            	<dl class="add_cont1 add_none">
            		<dt>内容标题：</dt>
            		<dd><input type="text"></dd>
            	</dl>
            	<div class="add_none" style="clear:both;width:100%; height:10px;"></div>
            	<!-- <dl class="add_cont2 add_none">
            		<dt>选择图标：</dt>
            		<dd>
            			<div class="add_cont2_icon"></div>
            			<div class="add_cont2_up">
            				<input type="file" />
            			</div>
            		</dd>
            	</dl> -->
            	<div class="add_none" style="clear:both;width:100%; height:10px;"></div>
            	<ul class="add_cont444 add_none">
            		<li><input type="radio"><font>链接</font></li>
            		<li><input type="radio"><font>刮刮卡</font></li>
            		<li><input type="radio"><font>大转盘</font></li>
            		<li><input checked="checked" type="radio"><font>优惠券</font></li>
            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio"><font>会员卡</font></li>
            	</ul>
            	<div class="add_none" style="clear:both;width:100%; height:10px;"></div>
            	<dl class="add_cont4 add_none">
            		<dt>名称：</dt>
            		<dd>
            			<select>
            				<option selected="selected">sduhiusd</option>
            				<option>sduhiusd</option>
            				<option>sduhiusd</option>
            			</select>
            		</dd>
            	</dl>
            	<div class="edit_matter_06">
            		<textarea id="edit_matter_06"></textarea>
            	</div>
            	
            </div>
            <div class="moreimg" style="display:none;"><img src="%E5%BE%AE%E7%BD%91%E7%AB%99_files/websitemore.gif"></div>
        </div>
        <div id="editor11" class="MicrContent addarticle" style="display:none;">
            <dl>
            	<dd><label>标题：</label><input class="ContentInp" type="text"></dd>
                <dd><label>正文：</label>
                <div style="clear:both;width:100%; height:10px;"></div>
                <div style="width: 0px;" class="ke-container ke-container-default"><div unselectable="on" class="ke-toolbar" style="display:block;"><span class="ke-outline" data-name="image" title="图片" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-image" unselectable="on"></span></span><span class="ke-outline" data-name="link" title="设置超级链接" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-link" unselectable="on"></span></span><span class="ke-outline" data-name="unlink" title="取消超级链接" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-unlink" unselectable="on"></span></span><span class="ke-inline-block ke-separator"></span><span class="ke-outline" data-name="fontsize" title="文字大小" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-fontsize" unselectable="on"></span></span><span class="ke-outline" data-name="forecolor" title="文字颜色" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-forecolor" unselectable="on"></span></span><span class="ke-outline" data-name="bold" title="粗体(Ctrl+B)" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-bold" unselectable="on"></span></span><span class="ke-outline" data-name="italic" title="斜体(Ctrl+I)" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-italic" unselectable="on"></span></span><span class="ke-outline" data-name="underline" title="下划线(Ctrl+U)" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-underline" unselectable="on"></span></span><span class="ke-outline" data-name="strikethrough" title="删除线" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-strikethrough" unselectable="on"></span></span><span class="ke-outline" data-name="removeformat" title="删除格式" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-removeformat" unselectable="on"></span></span><span class="ke-inline-block ke-separator"></span><span class="ke-outline" data-name="justifyleft" title="左对齐" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-justifyleft" unselectable="on"></span></span><span class="ke-outline" data-name="justifycenter" title="居中" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-justifycenter" unselectable="on"></span></span><span class="ke-outline" data-name="justifyright" title="右对齐" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-justifyright" unselectable="on"></span></span><span class="ke-outline" data-name="justifyfull" title="两端对齐" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-justifyfull" unselectable="on"></span></span><span class="ke-outline" data-name="insertorderedlist" title="编号" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-insertorderedlist" unselectable="on"></span></span><span class="ke-outline" data-name="insertunorderedlist" title="项目符号" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-insertunorderedlist" unselectable="on"></span></span><span class="ke-outline" data-name="indent" title="增加缩进" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-indent" unselectable="on"></span></span><span class="ke-outline" data-name="outdent" title="减少缩进" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-outdent" unselectable="on"></span></span><span class="ke-outline" data-name="subscript" title="下标" unselectable="on"><span class="ke-toolbar-icon ke-toolbar-icon-url ke-icon-subscript" unselectable="on"></span></span></div><div class="ke-edit" style="display: block; height: 100px;"><iframe style="width: 100%; height: 100px;" class="ke-edit-iframe" hidefocus="true" frameborder="0"></iframe><textarea style="width: 100%; height: 100px; display: none;" class="ke-edit-textarea" hidefocus="true"></textarea></div><div class="ke-statusbar"><span style="visibility: hidden;" class="ke-inline-block ke-statusbar-center-icon"></span><span style="visibility: hidden;" class="ke-inline-block ke-statusbar-right-icon"></span></div></div><textarea style="display: none;" cols="" rows="" class="ContentTex " id="edit-auto-only"></textarea></dd>
                <dt>
                	<input value="" class="MicrosBtn savesuccess save_onlycont" type="button">
                	<input style="display:none;" value="" class="MicrosBtn savesuccess creat_momre_cont" type="button">
                	<input style="display:none;" value="" articleid="" class="MicrosBtn savesuccess edit_momre_cont" type="button">	
                </dt>
            </dl>
        </div>
        <div id="editor12" class="MicrosList showlistarticle" style="display:none;">
        	<div class="MicrosListNav">
            	<dl>
                	<dt>标题：</dt>
                  	<dd style="display:none;">
                    	<span><em>京东商城</em><a href="javascript:;">修改</a></span>
                    </dd>
                    <dd>
                   	  <label>
                   	  	<input name="" class="MicrosListInp" type="text">
                   	  	<input class="MicrosListBtn save_mtitle" value="保存" type="button">
                   	  	<input class="MicrosListBtn edit_mtitle" value="修改" type="button">
                   	  </label>
                    </dd>
                </dl>
                <input class="MicrosListBtn1" value="新建内容" type="button">
            </div>
            <div class="MicrosListCer"><ul class="style_01"></ul></div>
            <div class="MicrosPage _page2"></div>
            <!--  <input type="button" value="" class="MicrosBtn savesuccess" />-->
        </div>
        <div id="saveSuccess" class="MicrSuccess" style="display:none;">
        	<dl>
            	<dt><img src="<%=request.getContextPath()%>/public/images/MicrSuccess.gif" height="43" width="43"></dt>
                <dd>已成功保存</dd>
            </dl>
        </div>
      </div>
	</div>

	<!--shanchu-->
	<div class="del_cont" style="display:none;">
	   <p> 确定删除此内容？</p>
	   <button> 确定</button>
	 </div>
	
	<!--选择模块-->
	<div class="select_module" articleid="44898" style="display:none;">
		<div class="module_demo module_1" u_style="1">小长方版块</div>
		<div class="module_demo module_2" u_style="2">选择大长方版块</div>
		<div class="module_demo module_3" u_style="5">竖长版块</div>
		<div class="module_demo module_4" u_style="4">长条版块</div>
		<div class="module_demo module_5" u_style="3">横通版块</div>
	</div>


	<!--选择上传背景-->
	
	<div class="upload_s up_01" style="display:none; width: 340px; height: 220px; margin: 0px 0px 0px -170px; padding: 0px; border-radius: 5px 5px 5px 5px; z-index: 999; box-shadow: 0px 0px 10px rgb(221, 190, 161); background-color: rgb(255, 255, 255); border-width: 3px; border-style: solid; border-color: rgb(224, 224, 224); position: fixed; top: 30%; left: 50%;">
		<div style="height: 25px; background-color: rgb(204, 204, 204); background-image: url(<%=request.getContextPath()%>/public/wxmuban/images/title-bg01.png); background-position: 0px -33px; background-repeat: repeat-x;">
			<dl style="margin: 0px; line-height: 25px;">
				<dt style="margin: 0px; line-height: 25px; height: 25px; float: left; font-size: 12px; padding-left: 5px;">选择背景</dt>
				<dd class="close_up1" style="margin: 0px; line-height: 25px; height: 25px; width: 20px; float: right; background: url(<%=request.getContextPath()%>/public/wxmuban/images/tag.png) no-repeat scroll -30px 2px transparent; cursor: pointer;" title="关闭"></dd>
			</dl>
		</div>
		<div style="clear:both; width:100%; height:1px;"></div>
		<ul class="s_updata s_updata_1" id="s_updata" style=" position:absolute; top:0; left:0;">
			<li class="s_bj m_index"></li>
			<li class="s_img s_index"></li>
			<div class="sc_upload">
				<input id="mp3fileupload3" onChange="ajaxFileUpload3();" class="urlt_0_1" name="imagefile" type="file">
			</div>		
		</ul>
		<div class="up_load">图片建议宽度320像素</div>
	</div>





	<!--选择上传背景--三级页-->
	<div class="upload_s up_03" style="display:none; width: 340px; height: 220px; margin: 0px 0px 0px -170px; padding: 0px; border-radius: 5px 5px 5px 5px; z-index: 999; box-shadow: 0px 0px 10px rgb(221, 190, 161); background-color: rgb(255, 255, 255); border-width: 3px; border-style: solid; border-color: rgb(224, 224, 224); position: fixed; top: 30%; left: 50%;">
		<div style="height: 25px; background-color: rgb(204, 204, 204); background-image: url(<%=request.getContextPath()%>/public/wxmuban/images/title-bg01.png); background-position: 0px -33px; background-repeat: repeat-x;">
			<dl style="margin: 0px; line-height: 25px;">
				<dt style="margin: 0px; line-height: 25px; height: 25px; float: left; font-size: 12px; padding-left: 5px;">选择背景</dt>
				<dd class="close_up1" style="margin: 0px; line-height: 25px; height: 25px; width: 20px; float: right; background: url(<%=request.getContextPath()%>/public/wxmuban/images/tag.png) no-repeat scroll -30px 2px transparent; cursor: pointer;" title="关闭"></dd>
			</dl>
		</div>
		<div style="clear:both; width:100%; height:1px;"></div>
		<ul class="s_updata s_updata_3" id="s_updata_3" style=" position:absolute; top:0; left:0;">
			<li class="s_bj m_index_3"></li>
			<li class="s_img s_index_3"></li>
			<div class="sc_upload">
				<input id="urlt_3" class="urlt_3" onChange="ajaxFileUpload_03();" name="mp3file" type="file">
			</div>
		</ul>
		<div class="up_load">图片建议宽度320像素</div>
	</div>


	<!--选择上传背景--二级页-->
	<div class="upload_s up_02" style="display:none; width: 340px; height: 220px; margin: 0px 0px 0px -170px; padding: 0px; border-radius: 5px 5px 5px 5px; z-index: 999; box-shadow: 0px 0px 10px rgb(221, 190, 161); background-color: rgb(255, 255, 255); border-width: 3px; border-style: solid; border-color: rgb(224, 224, 224); position: fixed; top: 30%; left: 50%;">
		<div style="height: 25px; background-color: rgb(204, 204, 204); background-image: url(<%=request.getContextPath()%>/public/wxmuban/images/title-bg01.png); background-position: 0px -33px; background-repeat: repeat-x;">
			<dl style="margin: 0px; line-height: 25px;">
				<dt style="margin: 0px; line-height: 25px; height: 25px; float: left; font-size: 12px; padding-left: 5px;">选择背景</dt>
				<dd class="close_up1" style="margin: 0px; line-height: 25px; height: 25px; width: 20px; float: right; background: url(<%=request.getContextPath()%>/public/wxmuban/images/tag.png) no-repeat scroll -30px 2px transparent; cursor: pointer;" title="关闭"></dd>
			</dl>
		</div>
		<div style="clear:both; width:100%; height:1px;"></div>
		<ul class="s_updata s_updata_2" id="s_updata_2" style=" position:absolute; top:0; left:0;">
			<li class="s_bj m_index_2"></li>
			<li class="s_img s_index_2"></li>
			<div class="sc_upload">
				<input id="urlt_2" class="urlt_2" onChange="ajaxFileUpload_02();" name="mp3file" type="file">
			</div>
		</ul>
		<div class="up_load">图片建议宽度320像素</div>
	</div>

	
</body>
</html>
