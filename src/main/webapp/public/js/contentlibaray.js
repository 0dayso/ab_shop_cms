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
    $.ajaxFileUpload({
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
	function ajaxFileUpload3(){
	var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
	if (! reg.test($("#mp3fileupload3").val()))
	{
		$(".alertSimpleContent").html("图片格式必须为jpg、jpeg、png、bmp! ");
    	$(".alertSimple").show();
    	$("#upload_01").val("");
		return false;
	}
    $.ajaxFileUpload({
      url:'/materials.action?op=upload&stats=uploadImage',             //需要链接到服务器地址
      secureuri:false,
      fileElementId:'mp3fileupload3',                         //文件选择框的id属性
      dataType: 'json',                                     //服务器返回的格式，可以是json
      success: function (data){   
    	 if(data.code="A00006"){
    		if(Number(nowtuwen)==0){
    			$(".addmid").val(data.id);
    			$("#nowUpload").attr("src",data.url);
    		}else{
    			$(".duotuwenEvery"+nowtuwen).find("img").attr("src",data.url);
    			$(".addmid"+nowtuwen).val(data.id);
    		}
    		$("#upload_01").val("");
    		
    		
    	 }else if(data.code=="A00004"){
	    	location.href="/login.jsp";
	    }else if(data.code=="A00005"){
	    	$(".alertSimpleContent").html("文件必须小于2M！");
        	$(".alertSimple").show();
	     }
      }
    });
}
function ajaxFileUpload2(){
	var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
	if (! reg.test($("#mp3fileupload2").val()))
	{
		$(".alertSimpleContent").html("图片格式必须为jpg、jpeg、png、bmp! ");
    	$(".alertSimple").show();
		return false;
	}

    $.ajaxFileUpload({
      url:'/materials.action?op=upload&stats=uploadImage',             //需要链接到服务器地址
      secureuri:false,
      fileElementId:'mp3fileupload2',                         //文件选择框的id属性
      dataType: 'json',                                     //服务器返回的格式，可以是json
      success: function (data){   
    	 if(data.code=="A00006"){
    		 $('<ul class="newpic_h"><li class="pic_view">'
					+'<input type="text" value="'+data.filename+'" mid ='+data.id+'  class="editefilename editefilename'+data.id+'"/><div class="_Operating _edit"></div>'
					+'<div style="clear:both; height:10px;width:auto;"></div>'
					+'<img onfocus="this.blur()" alt="" src="'+data.url+'" style="width:122px;height:77px;">'
				+'</li><li class="data_con">'+data.filesize+'KB</li>'
				+'<li class="operate"><div class="_Operating _Opdel deleteImg" imgid="'+data.id+'"></div></li></ul>').prependTo('.imgcontent');
    		 $("#upload_03").val("");
    		 var vc = $(".imgCounts").html();
	    	 $(".imgCounts").html(Number(vc)+Number(1));
    	 }else if(data.code=="A00004"){
	    		location.href="/login.jsp";
	     }else if(data.code=="A00005"){
	    		$(".alertSimpleContent").html("文件必须小于2M！");
	        	$(".alertSimple").show();
	     }
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
		$(".articleTitleShow").html("新标题");
		$("#nowUpload").attr("src","./public/images/pics/view.png");
		$(".op").val("addArticle");
		
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
            url: "/materials.action",
            type:"POST",dataType:"json",
            timeout: "10000",
            data:{ "op":"getArticle",
            	"id":id
            	},
            success:function(data){
                if(data.code == "A00006"){
    	    		$("#nowUpload").attr("src",data.url);
                	$(".op").val(data.op);
                	$(".title1").val(data.title);
                	$(".articleTitleShow").html(data.title);
                	$(".description1").val(data.description);
                	$(".content1").val(data.content);
                	
                	k1.html(data.content);
                	//KE.html("texteditcontent1", data.content);
                	$(".texteditcontent").hide();
            		//$(".texteditcontent").css("position","fixed");
                	$(".texteditcontent1").show();
                	//$(".texteditcontent1").css("position","static");
            		//$(".texteditcontent1").css("left","0px");
                }else if(data.code=="A00004"){
    	    		location.href="/login.jsp";
    	    	}
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
	$(".consave").live("click",function(){
		
		$("#mp3fileupload3").remove();
		$("#submitAdd").submit();
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

});
document.onkeydown = function (e) {
	var theEvent = window.event || e;
	var code = theEvent.keyCode || theEvent.which;
	if (code == 13) {
		var emid=".editefilename"+changemid;
		$(emid).blur();
	}
};
