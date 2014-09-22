<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="net.rytong.entity.Customer" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	Customer customer = (Customer)request.getSession().getAttribute("customer");
	String customerName = "";
	if(customer != null){
		customerName = customer.getShotName();
	}
	
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<title>素材管理</title>
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/kindEditor.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/picturecss.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/kindeditor-min.js"></script>
  		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/kindeditor/zh_CN.js"></script>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="<%=path%>/public/js/jquery-1-s.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/public/check_permission.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/hidden.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/page.js"></script>
		<script src="<%=request.getContextPath()%>/public/wxmuban/js/popup.js" type="text/javascript"></script>
		<script type="text/javascript">
        function del(id){
			if(confirm("确定删除吗?")){
				window.self.location='<%=request.getContextPath()%>/system/template_delete.action?id='+id;
			}
		}
		$(document).ready(function(){
			counts();
			loadPicNews();
			$(".alertSimpleColose").click(function(){
				$(".alertSimple").hide();
			});
			$("#customer").val('<%=customerName %>');
		});
		
		function counts(){
			$.ajax({
				url:"<%=request.getContextPath()%>/system/resource_counts.action?time=" + new Date().getTime(),
	            type:"POST",
	            data:{},
	            dataType:"json",
	            timeout:"10000",
				success : function(data){
	            	$("#imgCounts_1").text(data['news']);
	            	$(".materialstypegames .allActivitySum").html(data['text']);
					$(".materialstypecoupons .imgCounts").html(0);
					$(".materialstypeimgs .imgCounts").html(data['image']);
					$(".materialstypevoices .voicesCounts").html(0);
				}
			})
		}
		
		//加载图文信息
		function loadPicNews(){
			$.ajax({
				url:"<%=path %>/system/resource_getInfos.action",
				data:{resType:"news"},
				type:'post',
				dataType:'json',
				success: function(data){
					var len = data.length;
					var s = '';
					for (var i = 0; i < len; i++) {
						var res = data[i];
						s += '<li id= "li_'  + res.id + '" style="background-position: 0px 196px;" class="only-item"><div class="mouseedit">'+
							'<img src="' + res.picUrl+ '" height="124" width="201">'+
							'<div class="modefy"><!--<div class="m_empty deletearticle" articleid="225162" title="删除"></div>  -->'+
							'<div class="m_edit tuwenedit" title="编辑" articleid="' + res.id + '"></div></div></div>'+
							'<div class="contitle">' + res.title + '</div></li>';
						$("#pics_show").html(s);
					}
					$("#imgCounts_1").text(len);
					$("#imgCounts_2").text(len);
				}
			});
		}
		//加载图片信息
		function loadPictures(){
			$.ajax({
				url:"<%=basePath%>system/resource_getInfos.action",
				data:{resType:"image"},
				type:'post',
				dataType: 'json', 
				success:function(data){
					var len = data.length;
					var s = '';
					for (var i = 0; i < len; i++) {
						var res = data[i];
						s += '<li id= "li_'  + res.id + '" style="background-position: 0px 196px;" class="only-item"><div class="mouseedit">'+
							'<img src="' + res.picUrl+ '" height="175" width="201">'+
							'<div class="modefy"><!--<div class="m_empty deletearticle" articleid="225162" title="删除"></div>  -->'+
							'</div></div>'+
							'</li>';
						$("#imgcontent").html(s);
					}
					$(".materialstypeimgs .imgCounts").html(len);
					$("#picNum").html(len);
				}
			});
		}
		
		
	    </script>
	    <script type="text/javascript">
	    var changemid=0;
		function ajaxFileUpload(){
			var reg	= new RegExp(/[\.mp3]$/);
			if (! reg.test($("#mp3fileupload").val()))
			{
				 $(".alertSimpleContent").html("语音格式必须为mp3! ");
				 $("#upload_01").val("");
			     $(".alertSimple").show();
				return false;
			}
		    jQuery.ajaxFileUpload({
		      url:'/materials.action?op=upload&stats=uploadImage',             //需要链接到服务器地址
		      secureuri:false,
		      fileElementId:'mp3fileupload',                         //文件选择框的id属性
		      dataType: 'json',                                     //服务器返回的格式，可以是json
		      success: function (data){                //相当于java中try语句块的用法
		    	if(data.code=="A00006"){
		    		$('<ul class="newpic_h"><li class="pic_view">'
							+'<input type="text" value="'+data.filename+'" mid ='+data.id+' class="editefilename editefilename'+data.id+'" /><div class="_Operating _edit"></div>'
							+'<div style="clear:both; height:10px;width:auto;"></div>'
							+'<div onfocus="this.blur()" style="cursor:pointer; width:155px;height:49px;line-height:49px; background:url(\'./public/images/pics/player.png\');">'
								+'<span style="color:#fff; font-weight:bold; margin: 0 50px 0 24px;"></span><span style="color:#476600; font-size:14px; "></span>'
							+'<div/>'
							+'<object style="position:absolute;left:18px; top:53px;" type="application/x-shockwave-flash" data="/tools/dewplayer/dewplayer.swf?mp3='+data.url+'" width="200" height="20" id="dewplayer"><param name="wmode" value="transparent" /><param name="movie" value="/tools/dewplayer/dewplayer.swf?mp3='+data.url+'" /></object>'
							+'<div class="zhezhao" style="position:absolute; width:80px; height:50px;top:37px; left:153px; background-color:#f9f9f9;"></div>'	
							+'<div style="position:absolute; width:10px; height:25px;top:51px; left:143px; background-color:#a0ce3d;"></div>'
							+'</li><li class="data_con">'+data.filesize+'KB</li>'
						+'<li class="operate"><div class="_Operating _Opdel deletevoice" voiceid="'+data.id+'"></div></li></ul>').prependTo('.voiceContent');
		    		var vc = $(".voicesCounts").html();
		    		$("#upload_02").val("");
		    		$(".voicesCounts").html(Number(vc)+Number(1));
		    	}else if(data.code=="A00004"){
		    		location.href="/login.jsp";
		    	}else if(data.code=="A00005"){
		    		$(".alertSimpleContent").html("上传的语音必须小于1M且长度在60秒以内！");
		        	$(".alertSimple").show();
			     }
		      }
		    });
		}
		var nowtuwen=0;
		//参数说明： resType对应于素材类型
		function ajaxFileUpload3(id, resType){
			var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
			if (! reg.test($("#"+id).val()))
			{
				$(".alertSimpleContent").html("图片格式必须为jpg、jpeg、png、bmp! ");
		    	$(".alertSimple").show();
		    	$("#upload_01").val("");
				return false;
			}
		    $.ajaxFileUpload({
		      url:'<%=path %>/system/resource_uploadFile.action',  
		      secureuri:false,
		      fileElementId:id,                         //文件选择框的id属性
		      data: {resType:resType},
		      dataType: 'json',                                     //服务器返回的格式，可以是json
		      complete:function()
				{
					$("#loading").hide();
				},				
				success: function (data, status)
				{
					if(data.status == "success"){
						if(resType == 'news'){
							$("#nowUpload").attr("src", data.url);
							$("#picName").val(data.name);
							$('.picurl').tip({width:'240',status:'right',content:'图片上传成功！',dis_time:1000});
						}else if(resType == 'image'){ //图片时显示在其后
							$("#picName2").val(data.name);
						    var s = "";
							s = '<li id= "li_'  + data.id + '" style="background-position: 0px 196px;" class="only-item"><div class="mouseedit">'+
								'<img src="' + data.url+ '" height="175" width="201">'+
								'<div class="modefy"><!--<div class="m_empty deletearticle" articleid="225162" title="删除"></div>  -->'+
								'</div></div>'+
								'</li>';
							$("#imgcontent").append(s);
							var len = $(".materialstypeimgs .imgCounts").html();
							$(".materialstypeimgs .imgCounts").html(parseInt(len)+1);
							$("#picNum").html(parseInt(len)+1);
						}
					}else{
						$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
					}
				},
				error: function (data, status, e)
				{
					alert(e);
				}
		    });
		}
		$(function(){
			$(".bar_bg").removeClass("bar_click");
			$(".weixinkefu").addClass("bar_click");
			
			$(".sort_hover").removeClass("sort_hoverYSmr");
			$(".sort_hover4").addClass("sort_hoverYSmr");
			$(".navlist li").live('click', function(event) {
				    $(".navlist li").removeClass("tagH");
					$(".navlist li").find("div").removeClass("tagbtmh");
					$(this).addClass("tagH");
					$(this).find("div").addClass("tagbtmh");
			   });
			   
			   
			
			$(".only-item").live('mouseover mouseout', function(event) {
			  if (event.type == 'mouseover') {
			    $(this).css("background-position","0 -2px");	
			  } else {
			   $(this).css("background-position","0 196px");
			  }
			});
					
			$(".more-item").live('mouseover mouseout', function(event) {
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
			
			 
			 $(".btmnav").find("li").live('click', function(event) {
			  		 $(".btmnav").find("li").removeClass("viewnav");
				   	  $(".btmnav").find("li").find("div").removeClass("viewtag");
				   	  $(this).addClass("viewnav");
				   	  $(this).find("div").addClass("viewtag");
				});
			 
			 
			  $(".onlymore").show();
			  $(".onlyone").hide();
			  
			  $(".navlist li").click(function(){
			  	var index = $(this).index();
			  	//根据素材类型来得到对应的展示界面
			  	switch(index) 
			  	{
			  	case 0:
			  		$(".switchshow,.creatimg_text").hide();
			  		$(".tuwen_matter").show();	
			  		$(".page").hide();
			  		$(".page0").show();
			  		break;
		  		case 1:
		  			$(".switchshow,.creatimg_text").hide();
			  		$(".huodong_matter").show();	
			  		$(".page").hide();
			  		$(".page1").show();
			  		break;
		  		case 2:
		  			$(".switchshow,.creatimg_text").hide();
			  		$(".youhuiquan_matter").show();	
			  		$(".page").hide();
			  		$(".page2").show();
			  		break;
			  	case 3:
		  			$(".switchshow,.creatimg_text").hide();
			  		$(".tupian_matter").show();	
			  		$(".page").hide();
			  		$(".page3").show();
			  		break;
			  	case 4:
		  			$(".switchshow,.creatimg_text").hide();
			  		$(".yuyin_matter").show();	
			  		$(".page").hide();
			  		$(".page4").show();
			  		break;
			  	default:
		 			$(".switchshow,.creatimg_text").hide();
			  		$(".tuwen_matter").show();	
			  	}
			  });
			  
			  $(".newpic_h").live('mouseover mouseout', function(event) {
				  	if (event.type == 'mouseover') {
				  		$(this).addClass("pic_ul_h");
				  		$(this).find(".pic_view").find(".zhezhao").css("background-color","#f7ffd7")
					  } else {
						  $(this).removeClass("pic_ul_h");
						  $(this).find(".pic_view").find(".zhezhao").css("background-color","#f9f9f9")
					  }
			});
			  
			  $("._Opdel").live('mouseover mouseout', function(event) {
				  	if (event.type == 'mouseover') {
				  		$(this).css("background","url('./public/images/pics/Modify.png') 18px 0px");
					  } else {
						  $(this).css("background","url('./public/images/pics/HomeAdd.png') 18px 0px");
					  }
				});
			 
			  
			  $("._edit").live('mouseover mouseout', function(event) {
			  	if (event.type == 'mouseover') {
				  	$(this).css("background","url('./public/images/pics/Modify.png') 55px 0px");
				  } else {
					  $(this).css("background","url('./public/images/pics/HomeAdd.png') 55px 0px");
				  }
				});
		
			  
			  $("._Operating").click(function(){
			  	$(this).addClass("_Operatingclick");
			  });
			  $("._edit").live("click",function(){
			  	 $(this).parent().find("input").addClass("pic_viewInput");
			  	 $(this).parent().find("input").attr("readonly",false);
			  	 $(this).parent().find("input").focus();
			  	 $(this).parent().find("input").select();
			  	 $(this).hide();
			  	 changemid = $(this).parent().find(".editefilename").attr("mid");
			  });
				
			  $(".pic_view").find("input").live('blur', function(event) {
				  $(this).removeClass("pic_viewInput");
				  $(this).parent().find("._edit").show();
				  var mid = $(this).attr("mid");
				  var filename = $(this).val();
				  if(mid==changemid){
					  $.ajax({
				            url: "/materials.action",
				            type:"POST",dataType:"json",
				            timeout: "10000",
				            data:{ "op":"updateMaterials",
				            	"filename":filename,
				            	"id":changemid
				            	},
				            success:function(data){
				                if(data.code == "A00006"){
				                	var emid=".editefilename"+data.id;
				                	$(emid).val(data.filename);
				                	changemid=0;
				                }else if(data.code=="A00004"){
				    	    		location.href="/login.jsp";
				    	    	}
				            }
				        });
				  }
				  
				  
			});
			//点击单图文时，创建单图文信息
			$("#only_tuwen").click(function(){
				$(".container").hide();
				$("#description1").show();
				$(".creatimg_text,.onlyone").show();
				$(".moretuwen").remove();
		    	$(".duotuwenEvery").remove();
		    	$(".selectnub").hide();
		    	$(".btmnav").hide();
		    	$(".page").hide();
		    	$(".articleTitle").val("");
				$("._textedit").val("");
				$(".model").val("");
				$("#picName").val("");
				$("#resourceId").val("");
				$(".title1").val("");
				$(".articleTitleShow").html("新标题");
				$("#type").val("");
				$("#nowUpload").attr("src","./public/images/pics/view.png");
				$(".op").val("addArticle");
				$(".texteditcontent").hide();
				$(".texteditcontent1").show();
				
				k1.html("");
				/**
				KE.html("texteditcontent1", "");
				KE.html("texteditcontent2", "");
				KE.html("texteditcontent3", "");
				KE.html("texteditcontent4", "");
				KE.html("texteditcontent5", "");
				KE.html("texteditcontent6", "");
				
				$('<div class="viewmin onlymore duotuwenEvery duotuwenEvery1" flag="1">'
						+'<p>新标题</p>'
						+'<img style="float:right; width:71px; height:71px;border:1px solid #aeb8ca;padding:2px;" src="./public/images/pics/viewmin.png" />'
					+'</div>').appendTo(".duotuwenImgs");
				**/
				
				
			});
			
			$("#more_tuwen").click(function(){
				$(".container,.onlyone").hide();
				$(".creatimg_text,.onlymore").show();
				$(".page").hide();
				$(".newscount").val(3);
				$(".selectnub").show();
				$(".articleTitle").val("");
				$(".btmnav").show();
				$("._textedit").val("");
				$(".op").val("addArticles");
				$(".moretuwen").remove();
		    	$(".duotuwenEvery").remove();
				$(".articleTitleShow").html("新标题");
				$("#nowUpload").attr("src","./public/images/pics/view.png");
				$(".texteditcontent").hide();
				$(".texteditcontent1").show();
				
				k1.html("");
				k2.html("");
				k3.html("");
				k4.html("");
				k5.html("");
				k6.html("");
				k7.html("");
				k8.html("");
				$('<div class="viewmin onlymore duotuwenEvery duotuwenEvery'+2+'" flag="'+2+'">'
						+'<p class="articleTitleShow2">新标题</p>'
						+'<img style="float:right; width:71px; height:71px;border:1px solid #aeb8ca;padding:2px;" src="./public/images/pics/viewmin.png" />'
					+'</div>').appendTo(".duotuwenImgs");
				
				
				$('<li class="moretuwen" flag="'+2+'"><font>第'+2+'篇</font><div></div></li>').appendTo(".duotuwen");
				
				$('<div class="viewmin onlymore duotuwenEvery duotuwenEvery'+3+'" flag="'+3+'">'
						+'<p  class="articleTitleShow3">新标题</p>'
						+'<img style="float:right; width:71px; height:71px;border:1px solid #aeb8ca;padding:2px;" src="./public/images/pics/viewmin.png" />'
					+'</div>').appendTo(".duotuwenImgs");
				
				
				$('<li class="moretuwen" flag="'+3+'"><font>第'+3+'篇</font><div></div></li>').appendTo(".duotuwen");
					
			});	
			$(".activitydel").live("click",function(){
				var aid = $(this).attr("aid");
				if(confirm("确定删除？")){
					$.ajax({
			            url: "/activitymanage.action",
			            type:"POST",dataType:"json",
			            timeout: "10000",
			            data:{ "op":"remove",
			            	"id":aid
			            	},
			            success:function(data){
			                if(data.code == "A00006"){
			                	$(".activity"+aid).remove();
			                	var sum = $(".allActivitySum").html();
			                	$(".allActivitySum").html(Number(sum)-Number(1));
			                }else if(data.code=="A00004"){
			    	    		location.href="/login.jsp";
			    	    	}
			            }
			          });
				}
				
			});
			$("#pagecoupons").click(function(){
				var maxpage = $(this).attr("maxpage");
				var newpage = $.trim($(".pagecoupons").val());
				if(Number(newpage)>Number(maxpage)){
					newpage=1;
				}
				location.href="/materials.action?op=getAll&materialstype=coupons&pagebegin="+newpage;
			});
			$("#pagevoices").click(function(){
				var maxpage = $(this).attr("maxpage");
				var newpage = $.trim($(".pagevoices").val());
				if(Number(newpage)>Number(maxpage)){
					newpage=1;
				}
				
				location.href="/materials.action?op=getAll&materialstype=voices&pagebegin="+newpage;
			});
			$("#pageimgs").click(function(){
				var maxpage = $(this).attr("maxpage");
				var newpage = $.trim($(".pageimgs").val());
				if(Number(newpage)>Number(maxpage)){
					newpage=1;
				}
				
				location.href="/materials.action?op=getAll&materialstype=imgs&pagebegin="+newpage;
			});
			$("#pagearticles").click(function(){
				var maxpage = $(this).attr("maxpage");
				var newpage = $.trim($(".pagearticles").val());
				if(Number(newpage)>Number(maxpage)){
					newpage=1;
				}
				
				location.href="/materials.action?op=getAll&materialstype=articles&pagebegin="+newpage;
			});
			$("#pagegames").click(function(){
				var maxpage = $(this).attr("maxpage");
				var newpage = $.trim($(".pagegames").val());
				if(Number(newpage)>Number(maxpage)){
					newpage=1;
				}
				location.href="/materials.action?op=getAll&materialstype=games&pagebegin="+newpage;
			});
			//图文消息
			$(".only-item").find(".tuwenedit").live('click',function(){
				$(".container,.onlymore").hide();
				$(".creatimg_text,.onlyone").show();
				$(".page").hide();
				$(".btmnav").hide();
				var id = $(this).attr("articleid");
				$(".uploadarticleid").val(id);
				$.ajax({
		            url: "<%=request.getContextPath()%>/system/resource_getNewsById.action",
		            dataType:"json",
		            timeout: "10000",
		            data:{id:id},
		            success:function(data){
		    	    		$("#nowUpload").attr("src",data[0].picUrl);
		    	    		var picUrl = data[0].picUrl;
		    	    		var urlName = picUrl.substring(picUrl.lastIndexOf("/")+1)
		    	    		$("#picName").val(urlName);
		    	    		$("#resourceId").val(data[0].id);  //对应于素材ID
		                	$(".title1").val(data[0].title);
		                	$("#type").val(data[0].type);
		                	$(".articleTitleShow").html(data[0].title);
		                	k1.html(data[0].content);
		                	//KE.html("texteditcontent1", data.content);
		                	$(".texteditcontent").hide();
		            		//$(".texteditcontent").css("position","fixed");
		                	$(".texteditcontent1").show();
		                	//$(".texteditcontent1").css("position","static");
		            		//$(".texteditcontent1").css("left","0px");
		            }
		          });
				
			});
			
			$(".more-item").find(".tuwenedit").live('click',function(){
				$(".container,.onlyone").hide();
				$(".creatimg_text,.onlymore").show();
				$(".page").hide();
				$(".btmnav").show();
				var id = $(this).attr("articleid");
				$(".uploadarticleid").val(id);
				$.ajax({
		            url: "/materials.action",
		            type:"POST",dataType:"json",
		            timeout: "10000",
		            data:{ "op":"getArticle",
		            	"id":id
		            	},
		            success:function(data){
		                if(data.code == "A00006"){
		                	
		                	$(".newscount").val(data.articles.length);
		                	$(".moretuwen").remove();
		                	$(".duotuwenEvery").remove();
		                	for(var i = 0;i<data.articles.length;i++){
		                		var flag = i+1;
		                		if(i>0){
		                			$(".uploadarticleid"+flag).val(data.articles[i].mid);
		                			$('<div class="viewmin onlymore duotuwenEvery duotuwenEvery'+flag+'" flag="'+flag+'">'
		                					+'<p  class="articleTitleShow'+flag+'">'+data.articles[i].title+'</p>'
		                					+'<img style="float:right; width:71px; height:71px;border:1px solid #aeb8ca;padding:2px;" src="'+data.articles[i].url+'" />'
		                				+'</div>').appendTo(".duotuwenImgs");
		                			$(".duotuwenEvery"+(i+1)).find("img").attr("src",data.articles[i].url);
		                			$('<li class="moretuwen" flag="'+flag+'"><font>第'+(i+1)+'篇</font><div></div></li>').appendTo(".duotuwen");
		                		}
		                		if(i==0){
				    	    		$("#nowUpload").attr("src",data.articles[i].url);
				                	$(".op").val(data.op);
				                	$(".title1").val(data.articles[i].title);
				                	$(".articleTitleShow").html(data.articles[i].title);
				                	$(".description1").val(data.articles[i].description);
				                	$(".content1").val(data.articles[i].content);
				                	
				                	k1.html(data.articles[i].content);
				                	//KE.html("texteditcontent1", data.articles[i].content);
		
			                	}else{
			                		
			                		if(flag==1){
			                			k1.html(data.articles[i].content);
			                		}else if(flag ==2){
			                			k2.html(data.articles[i].content);
			                		}else if(flag ==3){
			                			k3.html(data.articles[i].content);
			                		}else if(flag ==4){
			                			k4.html(data.articles[i].content);
			                		}else if(flag ==5){
			                			k5.html(data.articles[i].content);
			                		}else if(flag ==6){
			                			k6.html(data.articles[i].content);
			                		}else if(flag ==7){
			                			k7.html(data.articles[i].content);
			                		}else if(flag ==8){
			                			k8.html(data.articles[i].content);
			                		}
			                		
			                		
			                		//KE.html("texteditcontent"+flag, data.articles[i].content);
			                		$(".uploadarticleid"+flag).val(data.articles[i].id);
			                		$(".description"+flag).val(data.articles[i].description);
				                	$(".content"+flag).val(data.articles[i].content);
				                	$(".title"+flag).val(data.articles[i].title);
			                	}
		                	}
		                	if($(".moretuwen").last().attr("flag")==8){
		            			$(".moretuwen").last().css("borderRight","none");
		            		}
		                }else if(data.code=="A00004"){
		    	    		location.href="/login.jsp";
		    	    	}
		            }
		          });
			});
			
			$(".activityedit").click(function(){
				var activityid = $(this).attr("activityid");
				location.href="/activitymanage.action?op=newactivity&activityid="+activityid
			});
			$(".deletearticle").live('click',function(){
				if(confirm("是否确定删除？")){
					var id =$(this).attr("articleid");
					var pagebegin = $(".pagebeignArticle").val();
					location.href="/materials.action?op=delArticles&materialstype=articles&pagebegin="+pagebegin+"&id="+id;
				}else{
					return false;
				}
				
			});
			$(".deleteImg").live('click',function(){
				if(confirm("是否确定删除？")){
					var id =$(this).attr("imgid");
					var pagebegin = $(".pagebeignImg").val();
					location.href="/materials.action?op=delMaterials&materialstype=imgs&pagebegin="+pagebegin+"&id="+id;
				}else{
					return false;
				}
				
			});
			$(".deletevoice").live('click',function(){
				if(confirm("是否确定删除？")){
					var id =$(this).attr("voiceid");
					var pagebegin = $(".pagebeignVoice").val();
					location.href="/materials.action?op=delMaterials&materialstype=voices&pagebegin="+pagebegin+"&id="+id;
				}else{
					return false;
				}
				
			});
		
			$(".articleTitle").live("blur",function(){
				var f = $(this).attr("flag");
				if(f==1){
					$(".articleTitleShow").html($(this).val());
				}else{
					$(".articleTitleShow"+f).html($(this).val());
				}
				
			});
			
			//点击保存事件
			$(".consave").live("click",function(){
				$("#mp3fileupload3").remove();
				var customer = "<%=customerName %>";
				var resourceId = $("#resourceId").val(); //对应的id值
				var picName = $("#picName").val();
			    var title = $("#submitAdd input[name='title']").val();
			    var content =  k1.html();
			    if(picName == ""){
			    	$('.picurl').tip({width:'240',status:'error',content:'请上传图片',dis_time:1000});
			    	return ;
			    }
			    if(title == ""){
			    	$('.picurl').tip({width:'240',status:'error',content:'标题不能为空',dis_time:1000});
			    	return ;
			    }
				if(resourceId == ""){  //新建图文操作
					$.ajax({
						url: "<%=basePath %>system/resource_saveResource.action",
						data : {"picName":picName, "title":title, "content":content, "type":"news" , "customer":customer},
						dataType : "json", 
						type : "post",
						success : function(data){
							if(data.code == "ok"){
								var s = "";
								s = '<li id= "li_'  + data.id + '" style="background-position: 0px 196px;" class="only-item"><div class="mouseedit">'+
								'<img src="' + data.picUrl+ '" height="124" width="201">'+
								'<div class="modefy"><!--<div class="m_empty deletearticle" articleid="225162" title="删除"></div>  -->'+
								'<div class="m_edit tuwenedit" title="编辑" articleid="' + data.id + '"></div></div></div>'+
								'<div class="contitle">' + data.title + '</div></li>';
								$("#pics_show").append(s);
								$('.picurl').tip({width:'240',status:'right',content:'保存成功',dis_time:1000});
							}else{
								$('.picurl').tip({width:'240',status:'error',content:'保存失败',dis_time:1000});
							}
						}
					});
				}else{ //编辑单图文
					$.ajax({
						url: "<%=basePath %>system/resource_editResource.action",
						data : {"id":resourceId ,"picName":picName, "title":title, "content":content, "type":"news" , "customer":customer},
						dataType : "json", 
						type : "post",
						success : function(data){
							if(data.code == "ok"){
								var id = data.id;
								$("#li_"+id).find("img").attr("src", data.picUrl);
								$("#li_"+id + " .contitle").html(data.title);
								$('.picurl').tip({width:'240',status:'right',content:'更新成功',dis_time:1000});
							}else{
								$('.picurl').tip({width:'240',status:'error',content:'更新失败',dis_time:1000});
							}
						}
					});
				}
				$(".switchshow,.creatimg_text").hide();
		  		$(".tuwen_matter").show();	
		  		$(".page").hide();
		  		$(".page0").show();
			});
			$(".newscount").live("change",function(){
				var newscount = $(this).val();
				var count =2;
				$(".duotuwenEvery").each(function(){
					var obj = $(this);
					var flag = obj.attr("flag");
					if(Number(flag)>Number(newscount)){
						obj.hide();
					}else{
						obj.show();
					}
					count++;
				});
				
				$(".moretuwen").each(function(){
					var obj = $(this);
					var flag = obj.attr("flag");
					if(Number(flag)>Number(newscount)){
						obj.hide();
					}else{
						obj.show();
					}
					
				});
				while(count<=newscount){
					$('<div class="viewmin onlymore duotuwenEvery duotuwenEvery'+count+'" flag="'+count+'">'
		   					+'<p  class="articleTitleShow'+count+'">新标题</p>'
		   					+'<img style="float:right; width:71px; height:71px;border:1px solid #aeb8ca;padding:2px;" src="./public/images/pics/viewmin.png" />'
		   				+'</div>').appendTo(".duotuwenImgs");
		   			
		   			
		   			$('<li class="moretuwen" flag="'+count+'"><font>第'+count+'篇</font><div></div></li>').appendTo(".duotuwen");
					
		   			count++;
				}
				if($(".moretuwen").last().attr("flag")==8){
					$(".moretuwen").last().css("borderRight","none");
				}
				
			});
			
			$(".moretuwenfirst").live("click",function(){
				$(".articleTitle").hide();
				$("._textedit").hide();
				$(".title"+1).show();
				$(".content"+1).show();
				
				$(".texteditcontent").hide();
				$(".texteditcontent1").show();
				nowtuwen=0;
				$("#texteditcontent1").hide();
				$(".imgadvice").html("大图片建议700X300像素 ");
			});
			
			$(".moretuwen ").live("click",function(){
				
				var flag = $(this).attr("flag");
				nowtuwen=flag;
				$(".articleTitle").hide();
				$("._textedit").hide();
				$(".title"+flag).show();
				$(".content"+flag).hide();
				$(".texteditcontent").hide();
				$(".texteditcontent"+flag).show();
				$(".imgadvice").html("图片建议300*300像素");
			});
			var nowtype =".materialstype"+$("#materialstype").val();
			$(nowtype).click();
			
			var addArticlesDirect = $(".addArticlesDirect").val();
			if(addArticlesDirect==1){
				$("#only_tuwen").click();
			}else if(addArticlesDirect==2){
				$("#more_tuwen").click();
			}
			
			
		
			$(".cont_getdata").click(function(){
				//$(".Platform01").show();
				$(".Platform01").hide();
				$(".Progress_show").show();
				_progress();
			});
			$(".PlatSubmit").click(function(){
				us_busiid = $.trim($(".get_datauserid").val());
				us_businame = $.trim($(".get_datauserpwd").val());
				if(us_busiid == ""){
					$(".get_datauserid").focus();
					return false;
				}
				if(us_businame == ""){
					$(".get_datauserpwd").focus();
					return false;
				}
				
			});
			
			$(".conback").click(function(){
				$(".switchshow,.creatimg_text").hide();
		  		$(".tuwen_matter").show();	
		  		$(".page").hide();
		  		$(".page0").show();
			});
			
			$(".materialstypeimgs").click(loadPictures);
		
		});
		document.onkeydown = function (e) {
			var theEvent = window.event || e;
			var code = theEvent.keyCode || theEvent.which;
			if (code == 13) {
				var emid=".editefilename"+changemid;
				$(emid).blur();
			}
		};
	    </script>
		<style type="text/css">
		.content .page input{width:30px; text-align:center; border: 1px solid #B8B8B8; font-size: 12px; margin-left: 2px; padding: 5px 0;}
		.ke-container{width:640px !important; }
		.ke-container .ke-edit{height:150px !important;}
		.content .creatimg_text .btmnav li {
		width: 78px;
		}
		.OverviewTop {
			background-color: #f9f5b7;
			border: 1px solid #e0ddb7;
			height: 24px;
			float: left;
			padding: 10px 20px;
			width: 690px;
			margin: 24px;
			margin-bottom: 0;
			position: relative;
		}
		
		.OverviewTop span {
			color: #313131;
			float: left;
			font-family: "微软雅黑";
			font-size: 16px;
			line-height: 24px;
		}
		
		.OverviewTop a {
			height: 46px;
			width: 148px;
			position: absolute;
			right: -1px;
			top: -1px;
			background-image: url(/images/matter_wap_top1.gif);
			background-position: 0 0;
		}
		
		.OverviewTop.OnMicro a {
			background-position: 0 -45px;
		}
		
		.SettingPop {
			position: fixed;
			left: 50%;
			top: 100px;
			width: 420px;
			margin-left: -210px;
			display: none;
			z-index: 101;
		}
		
		.SettingPop .SettingTop {
			float: left;
			width: 396px;
			height: 44px;
			background-image: url(/crm/images/EventProTop.png);
		}
		
		.SettingPop .SettingTop span {
			float: right;
			width: 18px;
			height: 17px;
			margin: 13px 15px 0 0;
			cursor: pointer;
		}
		
		.SettingPop .SettingCer {
			float: left;
			width: 370px;
			padding: 13px;
			background-image: url(/crm/images/EventProCer.png);
		}
		
		.SettingPop .SettingCer li {
			float: left;
			width: 370px;
			margin-top: 10px;
		}
		
		.SettingPop .SettingCer li span {
			float: left;
			width: 135px;
			line-height: 23px;
			font-family: "微软雅黑";
			font-size: 15px;
			text-align: right;
		}
		
		.SettingPop .SettingCer .SettingInp {
			float: left;
			width: 180px;
			padding: 3px;
			border: 1px solid #aeb8ca;
		}
		
		.SettingPop .SettingCer .SettingBtn {
			float: left;
			margin: 20px 0 0 135px;
			display: inline;
			width: 118px;
			height: 32px;
			cursor: pointer;
			background-image: url(/images/EntrustBtn.gif);
		}
		.UnknownBox {
			position: absolute;
			width: 410px;
			left: 50%;
			margin-left: -205px;
			top: 100px;
			z-index: 999
		}
		
		.UnknownBox .UnknownTop {
			float: left;
			width: 410px;
			height: 40px;
		}
		
		.UnknownBox .UnknownCer {
			float: left;
			width: 410px;
			background-image: url(./public/images/pics/unknowncer.png);
		}
		
		.UnknownBox .UnknownCer dl {
			float: left;
			width: 350px;
			margin: 50px 30px 0 30px;
			display: inline;
		}
		
		.UnknownBox .UnknownCer dl dt {
			float: left;
			width: 350px;
			text-align: center;
			line-height: 30px;
			font-family: "微软雅黑";
			font-size: 18px;
		}
		
		.UnknownBox .UnknownCer dl dt em {
			font-style: normal;
			color: #F00;
		}
		
		.UnknownBox .UnknownCer dl dd {
			float: left;
			width: 350px;
			margin-top: 20px;
		}
		
		.UnknownBox .UnknownCer .UnknownBtn {
			float: left;
			width: 109px;
			height: 30px;
			cursor: pointer;
			background-image: url(./public/images/pics/unknownbtn.gif);
			margin-left: 120px;
		}
		
		.UnknownBox .UnknownFor {
			float: left;
			width: 410px;
			height: 40px;
		}
		</style>

	</head>
	<body onload="init()">
		<div class="headerPart">
			<img class="imgAll"
				src="<%=request.getContextPath()%>/public/images/tubiao-01.png" />
			<span><font class="font1">当前位置:</font><font class="menuName_1">微信维护
					- 素材管理</font>
			</span>
		</div>
		<div class="main">
		<s:form action="resource_search" method="post" theme="simple" namespace="/system" id="select_res">
		<input id="title" name="title" type="hidden"/>
		<input name="type" value="" type="hidden"/>
		</s:form>
		<div class="centent_r_btm">
			<div style="float: right; margin-top: 5px; width: 360px;">
				<a class="cont_getdata"
					style="background: url('./public/images/pics/topnav.gif') no-repeat scroll 10px center transparent; border: 1px solid #C1BFBF; border-radius: 5px 5px 5px 5px; color: #3A5900; margin: 5px 35px -13px 0; float: right; height: 19px; line-height: 19px; padding: 0 7px 0 25px; text-decoration: underline;"
					href="javascript:;">加载公众平台内容</a>
			</div>
				<div class="UnknownBox alertSimple"
					style="display: none; left: 50%; top: 10%; margin-left: -277px; position: fixed; _position: absolute; z-index: 8000;">
					<div class="UnknownTop">
						<img
							src="./public/images/pics/unknowntop.png">
					</div>
					<div class="UnknownCer">
						<dl>
							<dt class="alertSimpleContent">
								您已达发送上限。今天还能发送
								<em>0</em>次。
							</dt>
							<dd>
								<input name="" class="UnknownBtn alertSimpleColose"
									type="button">
							</dd>
						</dl>
					</div>
					<div class="UnknownFor">
						<img
							src="./public/images/pics/unknownfor.png">
					</div>
				</div>
				<div class="content">
					<div class="subnav">
						<ul class="navlist">
							<li class="materialstypearticles tagH">
								<span>图文消息<font>（<label class="imgCounts" id="imgCounts_1">0</label>）</font>
								</span>
								<div class="tagbtmh"></div>
							</li>
							<li class="materialstypegames">
								<span>活动<font>（<label class="allActivitySum">
											0
										</label>）</font>
								</span>
								<div class=""></div>
							</li>
							<li class="materialstypecoupons">
								<span>优惠券<font>（<label class="imgCounts">0</label>）</font>
								</span>
								<div></div>
							</li>
							<li class="materialstypeimgs">
								<span>图片<font>（<label class="imgCounts">
											0
										</label>）</font>
								</span>
								<div></div>
							</li>
							<li class="clear-brd materialstypevoices">
								<span>语音<font>（<label class="voicesCounts">
											1
										</label>）</font>
								</span>
								<div></div>
							</li>
						</ul>
					</div>
					<input class="voiceM42722"
						value="http://www.weduty.com//res/knowbusi.mp3" type="hidden">
					<div style="display: block;"
						class="container tuwen_matter switchshow">
						<div class="creatitem">
							<dl>
								<dt>
									<font>创建新图文消息：</font>
								</dt>
								<dd style="height: 23px; line-height: normal; margin-top: 23px;">
									<input class="addArticlesDirect" value="0" type="hidden">
									<input id="only_tuwen" value="单图文" type="button">
									<input id="more_tuwen" value="多图文" type="button">
								</dd>
							</dl>
						</div>
						<dl class="newshow">
							<dt>
								<span>图文消息</span><strong style="color: red;">(<label
										class="imgCounts"  id="imgCounts_2">
										0
									</label>)</strong>
								<!--<a href="Javascript:;">加载公众平台内容</a>-->
							</dt>
							<dd>
								<ul>
									<div id="pics_show"></div>
								</ul>
							</dd>
						</dl>
					</div>
					<div style="display: none;"
						class="container huodong_matter switchshow">
						<div class="creatitem">
							<dl>
								<dt>
									<font>活动需在推广营销中管理，</font>
								</dt>
								<dd>
									<a href="http://www.weduty.com/activitymanage.action"><font
										style="color: #0000ff; font-weight: bold;">点击管理</font>
									</a>
								</dd>
							</dl>
						</div>
						<dl class="newshow">
							<dt>
								<span>活动</span><strong style="color: red;">(<label
										class="allActivitySum">
										0
									</label>)</strong>
							</dt>
							<dd>
								<ul>
								</ul>
							</dd>
						</dl>
					</div>
					<div style="display: none;"
						class="container youhuiquan_matter switchshow">
						<div class="creatitem">
							<dl>
								<dt>
									<font>优惠券需在推广营销中管理，</font>
								</dt>
								<dd>
									<a href="http://www.weduty.com/coupon/index.jsp"><font
										style="color: #0000ff; font-weight: bold;">点击管理</font>
									</a>
								</dd>
							</dl>
						</div>
						<dl class="newshow">
							<dt>
								<span>优惠券</span><strong style="color: red;">(0)</strong>
							</dt>
							<dd>
								<ul>

								</ul>
							</dd>
						</dl>
					</div>
					<div style="display: none;"
						class="container tupian_matter switchshow">
						<div class="creatitem">
							<dl>
								<dt style="position: relative;">
									<div class="file-box" style="position: relative;">
									    <input id="picName2" type="hidden" value="">
										<input onchange="ajaxFileUpload3('mp3fileupload2','image');" id="mp3fileupload2" name="imagefile" style="position: absolute; left: 0px; top: 22px; left: -90px\0; top: 20px\0; font-size: 20px\0; height: 27px\0; *+ left: -90px; *+ top: 8px; *+ font-size: 20px; *+ height: 27px; opacity: 0.0; filter: alpha(opacity = 00); -moz-opacity: 0.0;" size="5" type="file">
										<input style="margin-top: 20px; *+ margin-top: 5px;" class="upload2" name="submit" value="" type="button">
									</div>
								</dt>
								<dd>
									<span
										style="color: #747474; font-size: 14px; margin-left: 10px;">图片大小限制：2M，
										格式限制：bmp, png, jpeg, jpg</span>
								</dd>
							</dl>
						</div>
						<dl class="newshow_pic">
							<dt>
								<ul>
									<li class="pic_view"><span>图片</span><strong style="color: red;">(<label id="picNum"></label>)</strong></li>
								</ul>
							</dt>
							<dd id="imgcontent" class="imgcontent">
							</dd>
						</dl>
					</div>
					<div style="display: none;"
						class="container yuyin_matter switchshow">
						<div class="creatitem">
							<dl>
								<dt>
									<!--  
									<form  enctype="multipart/form-data">
										<input class="upload2" value=""  onclick="ajaxFileUpload();"  />
										<input id="upload_02" class="test_up" type="text">
										<input name="mp3file" id="mp3fileupload" value="" size="16"  onchange="document.getElementById('upload_02').value=this.value"  style="position:absolute; left:102px;top:23px;font-size:13px; filter:alpha(opacity:0);opacity: 0;" type="file">
									</form>-->
									<div class="file-box" style="position: relative;">
										<input onchange="ajaxFileUpload();" id="mp3fileupload"
											name="mp3file"
											style="position: absolute; left: 0px; top: 22px; left: -90px\0; top: 20px\0; font-size: 20px\0; height: 27px\0; *+ left: -90px; *+ top: 8px; *+ font-size: 20px; *+ height: 27px; opacity: 0.0; filter: alpha(opacity = 00); -moz-opacity: 0.0;"
											size="5" type="file">
										<input style="margin-top: 20px; *+ margin-top: 5px;"
											class="upload2" name="submit" value="" type="button">
									</div>
								</dt>
								<dd>
									<span
										style="color: #747474; font-size: 14px; margin-left: 10px;">语音大小：1M且60秒，格式：mp3</span>
								</dd>
							</dl>
						</div>
						<dl class="newshow_pic">
							<dt>
								<ul>
									<li class="pic_view">
										<span>语音</span><strong style="color: red;">(<label
												class="voicesCounts">
												1
											</label>)</strong>
									</li>
									<li class="data_con">
										大小
									</li>
									<li class="operate">
										操作
									</li>
								</ul>
							</dt>
							<dd class="voiceContent">
								<div id="jp_container_1">
									<ul class="newpic_h">
										<li class="pic_view">
											<input value="了解企业.mp3" mid="42722"
												class="editefilename editefilename42722" readonly="readonly"
												type="text">
											<div class="_Operating _edit"></div>

											<div style="clear: both; height: 10px; width: auto;"></div>
											<div class="jp-play jp-play1"
												url="http://www.weduty.com//res/knowbusi.mp3" mp3count="1"
												onfocus="this.blur()" alt=""
												style="cursor: pointer; width: 155px; height: 49px; line-height: 49px; background: url('<%=path %>/public/images/pics/player.png');">
												<span
													style="color: #fff; font-weight: bold; margin: 0 50px 0 24px;">&nbsp;&nbsp;&nbsp;</span><span
													style="color: #476600; font-size: 14px;">21"</span>
											</div>
											<object style="position: absolute; left: 18px; top: 53px;"
												type="application/x-shockwave-flash" mp3count="1"
												data="%E5%BE%AE%E6%9C%8D%E5%8A%A1-%E5%86%85%E5%AE%B9%E5%BA%93_files/dewplayer.swf"
												id="dewplayer" height="20" width="200">
												<param name="wmode" value="transparent">
												<param name="movie"
													value="/tools/dewplayer/dewplayer.swf?mp3=http://www.weduty.com//res/knowbusi.mp3">
											</object>
											<div class="zhezhao"
												style="position: absolute; width: 80px; height: 50px; top: 37px; left: 153px; background-color: #f9f9f9;"></div>
											<div
												style="position: absolute; width: 10px; height: 25px; top: 51px; left: 143px; background-color: #a0ce3d;"></div>
										</li>
										<li class="data_con">
											536KB
										</li>
										<li class="operate">
											<div class="_Operating _Opdel deletevoice" voiceid="42722"></div>
										</li>
									</ul>
								</div>
							</dd>
						</dl>
					</div>
					<!--总览内容-->
					<style>
					.texteditcontent {
						display: none;
					}
					</style>
					<input name="struts.token.name" value="token" type="hidden">
					<input name="token" value="95F1H9QIK9Z8E4RZDND3FA3VHNNMLO4C"
						type="hidden">
					<form action="#" method="post" id="submitAdd"
						onsubmit="return formchk(this);">
						<input id="type" name="type" type="hidden">
						<input id="resourceId" name="id" type="hidden">
						<input class="pagebeignArticle" value="" name="pagebegin"
							type="hidden">
						<input value="articles" name="materialstype" type="hidden">
						<input name="struts.token.name" value="token" type="hidden">
						<input name="token" value="95F1H9QIK9Z8E4RZDND3FA3VHNNMLO4C"
							type="hidden">
						<div style="display: none;" class="creatimg_text">
							<dl class="selectnub onlymore">
								<dt>
									选择数量：
								</dt>
								<dd>
									<select name="newscount" class="newscount">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3" selected="selected">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
									</select>
								</dd>
							</dl>
							<div style="clear: both; height: 20px;"></div>
							<dl>
								<dt>
									<font style="font-weight: bold; color: #727272;">预&nbsp;&nbsp;览：</font>
								</dt>
								<dd class="duotuwenImgs">
									<div class="view">
										<p
											style="font-family: '微软雅黑'; font-size: 18px; font-weight: bold;"
											class="articleTitleShow">
											我是标题我自豪！
										</p>
										<p>
											2012-10-10
										</p>
										<img
											style="width: 322px; height: 161px; border: 1px solid #aeb8ca; padding: 2px;"
											src="http://0992.mpb.weduty.com/store/mpelement/09922013251245182557.jpg"
											id="nowUpload">
										<p style="padding-top: 10px;" class="onlymore"></p>
									</div>
									
									<div class="viewmin onlymore duotuwenEvery">
										<p>
											我是标题我自豪！
										</p>
										<img
											style="float: right; width: 71px; height: 71px; border: 1px solid #aeb8ca; padding: 2px;"
											src="%E5%BE%AE%E6%9C%8D%E5%8A%A1-%E5%86%85%E5%AE%B9%E5%BA%93_files/viewmin.png">
									</div>
									<div class="viewmin onlymore duotuwenEvery">
										<p>
											我是标题我自豪！
										</p>
										<img
											style="float: right; width: 71px; height: 71px; border: 1px solid #aeb8ca; padding: 2px;"
											src="%E5%BE%AE%E6%9C%8D%E5%8A%A1-%E5%86%85%E5%AE%B9%E5%BA%93_files/viewmin.png">
									</div>
								</dd>
							</dl>
							<div style="clear: both; height: 29px;"></div>
							<ul class="btmnav onlymore duotuwen">
								<li class="viewnav moretuwenfirst">
									<font>封面内容</font>
									<div class="viewtag"></div>
								</li>
								<li class="moretuwen">
									<font>第二篇</font>
									<div></div>
								</li>
								<li class="moretuwen">
									<font>第三篇</font>
									<div></div>
								</li>
							</ul>

							<div style="clear: both; height: 1px;"></div>
							<dl class="_title">
								<dt>
									标 &nbsp;&nbsp;题：
								</dt>
								<dd>
									<input name="title" flag="1" maxlength="37"
										class="articleTitle title1" type="text">
								</dd>
							</dl>
							<div style="clear: both; height: 18px;"></div>
							<dl class="_upload">
								<dt>
									上传图片：
								</dt>
								<dd style="position: relative;">
								    <input id="picName" name="picName" type="hidden"/>
									<input id="upload_01" class="test" type="text">
									<input class="sbtn" value="选择文件" type="button">
									<!--onchange="document.getElementById('upload_01').value=this.value"  -->
									<input name="imagefile" id="mp3fileupload3" size="31"
										onchange="document.getElementById('upload_01').value=this.value;ajaxFileUpload3('mp3fileupload3','news');"
										style="position: absolute; width: 290px; top: 2px; left: 0; font-size: 13px; filter: alpha(opacity : 0); opacity: 0;"
										type="file">
									<span class="imgadvice">大图片建议720X400像素</span>
								</dd>
							</dl>
							<div style="clear: both; height: 30px;"></div>
							<dl class="_text">
								<dt>
									正 &nbsp;&nbsp;&nbsp;文：
								</dt>
								<div style="clear: both; height: 18px;"></div>
								<dd>
									<div style="display: block;">
									    <textarea id="texteditcontent1" name="content" style="width:380px;height:290px;visibility:hidden;"></textarea>
									</div>
								</dd>
							</dl>
							
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
									uploadJson : '/uploadcmsimg.action',
							        fileManagerJson : '/uploadcmsimg.action',
							        allowFileManager : true,
									items:['image','link', 'unlink','|','fontsize','forecolor','bold','italic','underline','strikethrough','removeformat','|'
									       ,'justifyleft' ,
									       'justifycenter' ,'justifyright','justifyfull','insertorderedlist','insertunorderedlist','indent','outdent', 'subscript']
							   });
							});
							$(".texteditcontent1").show();
							/**
							$(".texteditcontent").css("position","fixed");
							$(".texteditcontent1").css("position","static");
							$(".texteditcontent1").css("left","0px");
							**/
							function formchk(form){
								if($.trim(form.title.value)==""){
									alert("首标题不能为空！");
									return false;
								}
								form.content.value = k1.html();
							}
						
							</script>
							<div style="clear: both; height: 35px;"></div>
							<input class="consave" style="cursor: pointer;" type="button">
							<input class="conback" style="cursor: pointer;">
						</div>
						<input name="op" class="op" type="hidden">
						<input name="customer" type="hidden" id="customer">
					</form>
					<!--创建图文消息-->
				</div>
			</div>
		</div>
		<div style="opacity:0; filter:alpha(opacity=00); width:322px; height:102px; overflow:hidden; position:absolute; top:0px; left:31px; display:none;">
       		<input class="picurl" style="font-size:100px; height:102px; margin-left:-1100px; cursor:pointer;" >
       	</div>
	</body>
</html>
