var demoid_6 ,demoid_7,demoid_8;
var self_guideid;
var self_guidepositionID;
var guideposi;
var secondid;
var activityhref;
var this_id_def;
var secondpicurl;
var _this_posiid;
var parentid;
$(document).ready(function(){
	//编辑配图
	$(".up_pic").die().live("mouseover mouseout",function(event){
		 event.stopPropagation(); 
		if(event.type == "mouseover"){
			$(this).find(".up_newpic").hide();
			$(this).find("span").show();
		}else{
			$(this).find("span").hide();
			$(this).find(".up_newpic").show();
		}
	});
	
	$(".edit_demo6").die().live("mouseover mouseout",function(event){
		if(event.type == "mouseover"){
			$(this).children("span").hide();
			$(this).children("ul").show();
		}else{
			$(this).children("span").show();
			$(this).children("ul").hide();
		}
	});
	//编辑内容
	/*$(".et_demo6").die().live("click",function(event){
		 event.stopPropagation();
		$(".MicrositeTxt,.websitetitle,.demo_6,.addarticle,.showlistarticle,.MicrSuccess,.indeximg,.demo_banner6,.AddPhotos").hide();
		this_id_def = $(this).attr("value");
		var articleid=$(this).attr("value");
		var def_val = $(this).attr("content");
		$(".save_back").hide();
		$(".back02 h1").css("backgroundImage","url(/website/images/website_back02.png)");
		$("._morecontent").attr("articleid", articleid);
		pagebegin=1;
		if($.trim(def_val) == ""){
			console.log("1111");
			$(".MicrosListCer li").remove();
			$(".more_content li").remove();
			$(".only_titles").text('');
			$(".only_content").text('');
			$(".more_titles").html('');
			$(".MicrosListInp").val('');
			editor.html('');
			$("._selectface dt").html("<a class='back_top'>首页</a>");
			$(".MicrositeTxt,.indeximg,.websitetitle,.addarticle,.showlistarticle,.MicrSuccess,.only_cont,.more_cont,.back_list").hide();
			$(".addlistcontent").show();
			$("html").animate({scrollTop:0},"slow");
		}else{
			$(".mb1,.mb_nws7,.mb_nws8,.mb_nws9,.mb_nws10,.mb_nws11,.mb_nws12,.mb_nws13,.mb_nw14,.mb_nws15").hide();
			$.ajax({
				url:"newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"many",
	            	"articleid":articleid,
	            	"pagebegin":pagebegin
				},
				success:function(data){
					if(data.code == "A00006"){
						$("html").animate({scrollTop:0},"slow");
						if(data.type == 1){							
							$("._face4").attr("articleid",data.articleid);
							$("._face1,._face2,._face3,.edit_momre_cont,.creat_momre_cont,.more_cont").hide();
							$("._face4,.save_onlycont").show();
							$(".singlearticle").click();
						    $(".ContentInp").val(data.title);
							$(".only_titles").html(data.title);
							$(".only_content").html(data.content);
							$(".ContentTex").val(data.content);
							$(".back01").attr("typeid",1);
							editor.html(data.content);
							$("._selectface dt").html("<a class='back_top'>首页</a><span> > 文章内容</span>");
							$("._selectface dt").attr("value",data.articleid);	
							$(".only_cont").css("backgroundImage","url("+data.threeImg+")");
							$(".edit_momre_cont").attr("articleid",0);
						}else{
							if(data.type == 2){
								$("._face3").attr("articleid",data.articleid);
								$(".edit_momre_cont").attr("articleid",data.articleid);
								$(".MicrosListCer li").remove();
								$(".more_content li").remove();
								$("._page2").remove;
								$("._face1,._face2,._face4,.only_cont").hide();
								$("._face3").show();
								$(".back01").attr("typeid",1);
								$(".more_cont").css("backgroundImage","url("+data.SecondImg+")");
								$(".listarticle").click();
								$(".ContentInp").val("");
								$(".ContentTex").val("");
								$(".MicrosListInp").val(data.title);
								$(".more_titles").html(data.title);
								$("._selectface dt").html("<a class='back_top'>首页</a><a class='back_list'> > 内容列表</a>");
								$("._selectface a").last().attr("value",data.articleid);
								$.each(data.json, function(i, item){      
								   var new_li = '<li id='+item.id+' ><font>'+item.title+'</font></li>'; 
								   $(".more_content ul").append(new_li);
								   $(".MicrosListInp").val(data.title);
								   var list_more_rt = '<li>\
				                    	<div class="MicrosName">'+item.title+'</div>\
				                        <div class="MicrosGroup" style="display:none;">\
				                            <label class="HomeAdd HomeAdd'+item.id+'" parentid='+item.id+' title="添加标签"></label>\
				                            <label class="Delete Delete_more Delete'+item.id+'" deletid='+item.id+' title="删除"></label>\
				                        </div>\
				                        <div class="MicrosTime">'+item.createtime+'</div>\
			                       </li>';
			                       $(".MicrosListCer ul").append(list_more_rt);
			                       
								});  
								
								$("._page2 ul").remove();
								$('<ul>').appendTo("._page2");
								if(Number(data.pageno)>1){
			                		$('<li><a class="masspage" pagebegin='+Number(data.pageno-1)+'>上一页</a></li>').appendTo("._page2 ul");
			                	}

			                	if(data.pageno<=5){
			                		var count=1;
			                		for(var i= 1;i<=data.pagecount;i++){
			                			if(count<5){
				                			count++;
				                			$('<li><a class="masspage" pagebegin='+i+'>'+i+'</a></li>').appendTo("._page2 ul");
			                			}
			                		}
			                	}else{
			                		var count=1;
			                		for(var i=Number(data.pageno-2);i<=data.pagecount;i++){
			                			if(count<5){
				                			count++;
				                			$('<li><a class="masspage" pagebegin='+i+'>'+i+'</a></li>').appendTo("._page2 ul");
			                		
			                			}
			                		}
			                	}
			                	if(data.pagecount>=data.pageno && Number(data.pageno+1)<=data.pagecount){
			                		$('<li><a  class="masspage" pagebegin='+Number(data.pageno+1)+'>下一页</a></li>').appendTo("._page2 ul");
			                	}
			                	$('</ul>').appendTo("._page2 ul");
			                	
			                	
							
							}
						}
					}else {
			    		location.href="/login.jsp";
			    	}
				}
			});
		}
	
	});*/
	//删除模块
	
	
	
	var del_id_demo6;
	$(".del_demo6").die().live("click",function(){
		 del_id_demo6 = $(this).parents("dd").attr("value");
		$(".del_cont06").popup({p_width:'370',p_titletst:'',p_height:'195',p_YN_icon:'n',p_show:'fadein',p_speed:100});
	});
	
	$(".del_cont06").find("button").die().live("click",function(){
		$(".Template1_"+del_id_demo6).remove();
			console.log(del_id_demo6);
		$.ajax({
				url:"/newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"deletmany",
	            	"articleid":del_id_demo6
				},
				success:function(data){
					if(data.code == "A00006"){	
						$(".close").click();
					}else {
			    		location.href="/login.jsp";
			    	}
				}
		});  
		
	});
	
	
	var del_id_demo7;
	$(".del_demo7").die().live("click",function(){
		 del_id_demo7 = $(this).parents("li").attr("value");
		$(".del_cont0_7").popup({p_width:'370',p_titletst:'',p_height:'195',p_YN_icon:'n',p_show:'fadein',p_speed:100});						
	});
	$(".del_cont0_7").find("button").die().live("click",function(){
		$(".Template1_"+del_id_demo7).remove();
			console.log(del_id_demo7);
		$.ajax({
				url:"/newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"deletmany",
	            	"articleid":del_id_demo7
				},
				success:function(data){
					if(data.code == "A00006"){	
						$(".close").click();
					}else {
			    		location.href="/login.jsp";
			    	}
				}
		}); 
		
	});
	$(".master7 li,.secondmaster7 li,.secondmaster8 li,.secondmaster9 li").die().live("mouseover mouseout",function(event){
		if(event.type == 'mouseover'){
			$(this).find("label").show();
		}else{
			$(this).find("label").hide();
		}
	});
	


	//显示上传模块配图
	$(".up_click").die().live("click",function(event){
		 event.stopPropagation(); 
		 demoid_6 = $(this).parent().attr("value");
		$(".MicrositeTxt,.websitetitle,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.indeximg,.demo_banner6,.AddPhotos,.addlistcontent_0").hide();
		$(".demo_6").show();
		var te = $(this).attr("value");
		if(te==6){
			$(".picurlxiangsu").html("图片建议像素152 x 100像素");
		}else if(te==7){
			$(".picurlxiangsu").html("图片建议像素54 x 54像素");
		}else if(te==8){
			$(".picurlxiangsu").html("图片建议像素54 x 54像素");
		}else if(te==9){
			$(".picurlxiangsu").html("图片建议像素54 x 54像素");
		}else if(te==10){
			$(".picurlxiangsu").html("图片建议像素95 x 95像素");
		}else if(te==11){
			$(".picurlxiangsu").html("图片建议像素300 x 90像素");
		}else if(te==13){
			$(".picurlxiangsu").html("图片建议像素：</br> 大图：158 x 158像素</br>小图：78 x 78像素");
		}else if(te==12){
			$(".picurlxiangsu").html("图片建议像素：</br> 大图：220 x 120像素</br>小图：90 x 120像素");
		}else if(te==14){
			$(".picurlxiangsu").html("图片建议像素130 x 130像素");
		}else if(te==15){
			$(".picurlxiangsu").html("图片建议像素35 x 35像素");
		}
	});
	
	$(".master_015 img ,.secondmaster15 img ").die().live("click",function(event){
		event.stopPropagation(); 
		 demoid_6 = $(this).parent().attr("value");
		$(".MicrositeTxt,.websitetitle,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.indeximg,.demo_banner6,.addlistcontent_0").hide();
		$(".demo_6").show();
		$(".picurlxiangsu").html("图片建议像素35 x 35像素");
	});
	$(".up_mb_nws8").die().live("click",function(event){
		 event.stopPropagation(); 	 
		$(".MicrositeTxt,.websitetitle,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.indeximg,.demo_banner6,.AddPhotos,.addlistcontent_0").hide();
		self_guidepositionID=$(this).attr("value");
		self_guideid="urlt_8";
		$(".demo_8").show();
	});
	$(".up_new7_1").die().live("click",function(event){
		 event.stopPropagation(); 
		 demoid_6 = $(this).parent().attr("value");
		$(".MicrositeTxt,.websitetitle,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.indeximg,.demo_banner6,.addlistcontent_0,.AddPhotos").hide();
		$(".demo_6").show();
		$(".picurlxiangsu").html("图片建议像素54 x 54像素");
	});
	
	//添加模块
 	
	 $(".count-secondbtn").click(function(){
			$(".secondmb3").show();	 	
		 });
	
	$(".add_temp1 dd,.add_temp1 dt").click(function(){
		var this_obj =  $(this).clone();
		var this_U = $(this).attr("U_style");
		var this_Ustyle;
		
		if(this_U == "1"){
			 this_Ustyle = 'width:140px;height:092px;line-height:092px;';	
		}else if(this_U == "2"){
			 this_Ustyle = 'width:293px;height:092px;line-height:092px;';	
		}else if(this_U == "3"){
			 this_Ustyle = 'width:290px;height:044px;line-height:044px;background-image:url("/website/images/stencil_tb1.png");background-position: right center;background-repeat: no-repeat;';	
		}else if(this_U == "4"){
			 this_Ustyle = 'width:185px;height:042px;line-height:042px;background-image:url("/website/images/stencil_tb.png"); background-position: right center; background-repeat: no-repeat;border: 1px solid #B6B9C6 !important;';	
		}else if(this_U == "5"){
			 this_Ustyle = 'width:092px;height:098px;line-height:098px;';	
		}else{
		}
			$(".secondmaster02").append(this_obj);
			$.ajax({
					url:"websitestyle.action",
					type:"post",dataType:"json",
					timeout:10000,
					data:{
		                "op":"addsecondmodule",
		                "parentid":this_id_def,
						"style":this_Ustyle,
		            	"bgcolor":"#F5F2F2"
					},
					success:function(data){
						if(data.code =="A00006"){
							var Default = '<input class="color" value="" />\
		                        <label class=" editcontent" style='+data.bgcolor+'>编辑内容<em class="templbtn">\
			                        	<ul>\
			                        		<li class="list1" title="删除编辑内容"></li>\
			                        		<li class="list2" title="改变版块颜色"></li>\
			                        		<li class="list3" arctileid='+data.articleid+' title="改变版块形状"></li>\
			                        		<li class="list5" arctileid='+data.articleid+' title="编辑内容"></li>\
			                        	</ul>\
			                        </em>\
								</label>';
								$(".secondmaster02").find(".bgcol").find("span").attr("style",data.bgcolor);
								$(".secondmaster02").find(".bgcol").append(Default).attr({"value":data.articleid,"position":data.position,"style":data.style,"class":"TemplateSecond drag Template1_"+data.articleid,"id":"Template1_1_"+data.articleid+""}).addClass("Template1_1").css("backgroundColor","#F5F2F2");
								
								jscolor.init();
								
						}else {
				    		location.href="/login.jsp";
				    	}
					}
			});

	});

	
	
	 $(".count-secondbtn2").die().live('click',function(){
		  parentid=$(this).attr("value");
	 	$.ajax({
				url:"websitestyle.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
	                "op":"addsecondmodule",
	                "parentid":parentid
				},
				success:function(data){
					if(data.code =="A00006"){
						var _onlyli = '<li class="TemplateSecond Template1_1_'+data.articleid+' drag" position="'+data.position+'" value="'+data.articleid+'" >\
										<a value="'+data.articleid+'" >内容标题</a>\
										<span class="Delete Delete_onlys editcontent" style="display: none;">\
										<img width="30" height="30" src="/website/images/Delete.gif">\
										</span>\
										</li>';
						$(".secondmaster02").append(_onlyli);	
								
					}else {
			    		location.href="/login.jsp";
			    	}
				}
		});
	 	
	 });
	
	
	$(".count-btn6").die().live("click",function(){
		var _index06 = $(".master6").children("dd").last().attr("position");
		if($(".master6").children("dd").length<1){
			_index06=0;			
		}
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index06
			},success:function(data){
				if(data.code == "A00006"){
					var demo_06 = '<dd class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="width:152px; margin-top: 5px;margin-left: 5px; height:120px; line-height:120px;" value="'+data.articleid+'">'
                    	+'<div class="up_pic up_click" style="height:100px;line-height:100px;">'
                    	+'<span style="width:152px; border-radius: 5px 5px 0 0;display:none;background-color:#7fb221;color:#fff;">编辑配图</span>'
                    	+'<div class="up_newpic" style="border-radius: 5px 5px 0 0;width:152px;height:100px;background-image:url(/website/images/def_demo.png);" ></div>'
                    	+'</div>'
                    	+'<div class="edit_demo6" style="height:22px;line-height:22px;">'
                    	+'<span style=" border-radius:0 0 5px 5px;width:152px;background:#fff; font-size:14px;height:22px  overflow:hidden;">版块标题</span>'
                    	+'<ul style=" border-radius:0 0 5px 5px;display:none; width:100%; height:20px;line-height:20x;background:#4C6B14;">'
                    	+'<li class="del_demo6" style="float:right;width:20px;height:20px; background:url(/website/images/tag_m.png) -73px 1px no-repeat;"></li>'
                    	+'<li  class="et_demo6" value="'+data.articleid+'" content="" style="float:right;width:20px;height:20px; background:url(/website/images/tag_m.png) no-repeat;"></li>'                    			
                    	+'<li style="border-radius:0 0 5px 5px;float:left;width:100px;height:20px;line-height:20px; font-size:14px;">编辑内容</li>'
                    	+'</ul>'
                    	+'</div>'
                    	+'</dd>';
                    $(".master6").append(demo_06);
				}
			}
		});
	});
	
	$(".count-secondbtn6").die().live("click",function(){
		 parentid=$(this).attr("value");
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					var demo_06 = '<dd class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="width:152px; margin-top: 5px;margin-left: 5px; height:120px; line-height:120px;" value="'+data.articleid+'">'
                    	+'<div class="up_pic up_click" style="height:100px;line-height:100px;">'
                    	+'<span style="width:152px; border-radius: 5px 5px 0 0;display:none;background-color:#7fb221;color:#fff;">编辑配图</span>'
                    	+'<div class="up_newpic" style="border-radius: 5px 5px 0 0;width:152px;height:100px;background-image:url(/website/images/def_demo.png);" ></div>'
                    	+'</div>'
                    	+'<div class="edit_demo6" style="height:22px;line-height:22px;">'
                    	+'<span style=" border-radius:0 0 5px 5px;width:152px;background:#fff; font-size:14px;height:22px  overflow:hidden;">内容标题</span>'
                    	+'<ul style=" border-radius:0 0 5px 5px;display:none; width:100%; height:20px;line-height:20x;background:#4C6B14;">'
                    	+'<li class="del_demo6" style="float:right;width:20px;height:20px; background:url(/website/images/tag_m.png) -73px 1px no-repeat;"></li>'
                    	+'<li  class="list5" value="'+data.articleid+'" content="内容标题" style="float:right;width:20px;height:20px; background:url(/website/images/tag_m.png) no-repeat;"></li>'                    			
                    	+'<li style="border-radius:0 0 5px 5px;float:left;width:100px;height:20px;line-height:20px; font-size:14px;">编辑内容</li>'
                    	+'</ul>'
                    	+'</div>'
                    	+'</dd>';
                    $(".secondmaster6").append(demo_06);
				}
			}
		});
	});
	$(".count-btn7").die().live("click",function(){
		var _index07 = $(".master7").children("li").last().attr("position");
		if($(".master7").children("li").length<1){
			_index07=0;
		}
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index07
			},success:function(data){
				if(data.code == "A00006"){
					var demo_07 = '<li  class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="position:relative; width:288px; height:62px; line-height:62px;border:1px #b6b9c6 solid; background-color:#fefefe; margin:2px auto;" value="'+data.articleid+'">'					
					+'<ul value="'+data.articleid+'">'
					+'<li class="up_new7_1" style="float:left;height:60px; line-height:60px;">'
					+'<img style="width:54px; height:54px; margin:4px;"  src="/website/images/tpl7_.png" />'
					+'</li>'
					+'<li style="float:left;padding-top:10px;width:195px; height:50px; line-height:20px; overflow:hidden">'
					+'<strong class="listrong" style="float:left; width:195px; height:20px;; overflow:hidden">版块标题</strong>'
					+'</li>'
					+'<li style="float:left; width:30px; height:60px; line-height:60px;background:url(/website/images/stencil_tb2.png) 7px 22px no-repeat;"></li>'
					+'</ul>'
					+'<label class=" editcontent" style="display:none;position:absolute; color: #FFFFFF;text-align: center; right:0; width:227px;background-color:#7fb221; height:62px; line-height:62px;">'
					+'编辑内容<em class="templbtn"><ul>'
					+'<li class="del_demo7" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; " title="删除编辑内容"></li>'
					+'<li class="et_demo6" value="'+data.articleid+'" content=""; style="background:url(/website/images/tag_m.png) no-repeat; " title="编辑内容" arctileid=""></li>'
					+'</ul></em></label></li>'
                    $(".master7").append(demo_07);
				}
			}
		});
	});
	
	$(".count-secondbtn7").die().live("click",function(){
		 parentid=$(this).attr("value");
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					var demo_07 = '<li  class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="position:relative; width:288px; height:62px; line-height:62px;border:1px #b6b9c6 solid; background-color:#fefefe; margin:2px auto;" value="'+data.articleid+'">'					
					+'<ul value="'+data.articleid+'">'
					+'<li class="up_new7_1" style="float:left;height:60px; line-height:60px;">'
					+'<img style="width:54px; height:54px; margin:4px;"  src="/website/images/tpl7_.png" />'
					+'</li>'
					+'<li style="float:left;padding-top:10px;width:195px; height:50px; line-height:20px; overflow:hidden">'
					+'<strong class="listrong" style="float:left; width:195px; height:20px;; overflow:hidden">内容标题</strong>'
					+'</li>'
					+'<li style="float:left; width:30px; height:60px; line-height:60px;background:url(/website/images/stencil_tb2.png) 7px 22px no-repeat;"></li>'
					+'</ul>'
					+'<label class=" editcontent" style="display:none;position:absolute; color: #FFFFFF;text-align: center; right:0; width:227px;background-color:#7fb221; height:62px; line-height:62px;">'
					+'编辑内容<em class="templbtn"><ul>'
					+'<li class="del_demo7" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; " title="删除编辑内容"></li>'
					+'<li class="list5" value="'+data.articleid+'" content="内容标题"; style="background:url(/website/images/tag_m.png) no-repeat; " title="编辑内容" arctileid=""></li>'
					+'</ul></em></label></li>'
                    $(".secondmaster7").append(demo_07);
				}
			}
		});
	});
	$(".count-btn8").die().live("click",function(){
		var _index08 = $(".master8").children("dd").last().attr("position");
		if($(".master8").children("dd").length<1){
			_index08=0;
		}
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index08
			},success:function(data){
				if(data.code == "A00006"){
					var demo_08 = '<dd id="Template1_1_'+data.articleid+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" position="'+data.position+'" style="width:300px; text-align:left; border:1px solid #b6b9c6; border-bottom:0; background:url(/website/images/stencil_tb.png) no-repeat right center #FEFEFE; height:auto; padding:4px; margin:0; float:left;">'
                        +'<div class="up_pic up_click" style="height:55px;line-height:25px; width:55px; float:left;">'
                            +'<span style="width:55px; display:none;background-color:#7fb221;color:#fff; font-size:16px; text-align:center;">编辑<br />配图</span>'
                           +' <div class="up_newpic" style="width:55px;height:55px;">'
                          +'  <img src="/website/images/tpl7_.png" style="width:55px; height:55px;">'
                         +' </div>'
                       +' </div>'
                       +' <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">'
                          +'  <span style="width:210px; float:left; font-size:18px; color:#2b2b2b; margin-top:5px;">版块标题</span>'
                          +'  <div style="width:210px; float:left; color:#797979; font-size:12px; color:#797979; margin-top:3px;">版块介绍</div>'
                          +'  <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); text-align: center; right:0; margin-left:63px; display:inline; width:245px; background-color: rgb(127, 178, 33); height: 62px; line-height:42px; display: none;">编辑内容<em class="templbtn">'
                                 +'   <ul>'
                                     +'   <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>'
                                     +'   <li class="et_demo6"value="'+data.articleid+'" content=""; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;="" content="看好你" value="637"></li>'
                                   +' </ul>'
                            	+'</em>'
                           +' </label>'
                      +'  </div>'
                	+'</dd>';
                    $(".master8").append(demo_08);
				}
			}
		});
	});
	
	$(".count-secondbtn8").die().live("click",function(){
		 parentid=$(this).attr("value");
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					var demo_08 = '<dd id="Template1_1_'+data.articleid+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" position="'+data.position+'" style="width:300px; text-align:left; border:1px solid #b6b9c6; border-bottom:0; background:url(/website/images/stencil_tb.png) no-repeat right center #FEFEFE; height:auto; padding:4px; margin:0; float:left;">'
                        +'<div class="up_pic up_click" style="height:55px;line-height:25px; width:55px; float:left;">'
                            +'<span style="width:55px; display:none;background-color:#7fb221;color:#fff; font-size:16px; text-align:center;">编辑<br />配图</span>'
                           +' <div class="up_newpic" style="width:55px;height:55px;">'
                          +'  <img src="/website/images/tpl7_.png" style="width:55px; height:55px;">'
                         +' </div>'
                       +' </div>'
                       +' <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">'
                          +'  <span style="width:210px; float:left; font-size:18px; color:#2b2b2b; margin-top:5px;">内容标题</span>'
                          +'  <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); text-align: center; right:0; margin-left:63px; display:inline; width:245px; background-color: rgb(127, 178, 33); height: 62px; line-height:42px; display: none;">编辑内容<em class="templbtn">'
                                 +'   <ul>'
                                     +'   <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>'
                                     +'   <li class="list5"value="'+data.articleid+'" content="内容标题"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ></li>'
                                   +' </ul>'
                            	+'</em>'
                           +' </label>'
                      +'  </div>'
                	+'</dd>';
                    $(".secondmaster8").append(demo_08);
				}
			}
		});
	});
	$(".count-btn9").die().live("click",function(){
		var _index09 = $(".master9").children("dd").last().attr("position");
		if($(".master9").children("dd").length<1){
			_index09=0;
		}
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index09
			},success:function(data){
				if(data.code == "A00006"){
					var demo_09 = '<dd id="Template1_1_'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" style="width:300px; text-align:left; border:1px solid #b6b9c6; border-bottom:0; background:url(/website/images/stencil_tb.png) no-repeat right center #FEFEFE; height:auto; padding:4px; margin:0; float:left;">\
                        <div class="up_pic up_click" style="height:55px;line-height:25px; width:55px; float:left;">\
                    <span style="width:55px; display:none;background-color:#7fb221;color:#fff; font-size:16px; text-align:center;">编辑<br />配图</span>\
                    <div class="up_newpic" style="width:55px;height:55px;">\
                    <img src="/website/images/tpl7_.png" style="width:55px; height:55px;">\
					</div>\
                </div>\
                <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
                    <span style=" width:210px; float:left; color:#2b2b2b; margin-top:5px; overflow:hidden; height:20px;">版块标题</span>\
                    <div style="width:210px; float:left; color:#797979;  font-size:12px; color:#797979; margin-top:3px;">版块介绍</div>\
                    <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); text-align: center; right:0; margin-left:63px; display:inline; width:245px; background-color: rgb(127, 178, 33); height: 62px; line-height:42px; display: none;">编辑内容<em class="templbtn">\
                            <ul>\
                                <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                                <li class="et_demo6" value="'+data.articleid+'" content=""; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;="" content="看好你" value="637"></li>\
                            </ul>\
                    	</em>\
                    </label>\
                </div>\
        	</dd>';
                    $(".master9").append(demo_09);
				}
			}
		});
	});
	
	$(".count-secondbtn9").die().live("click",function(){
		 parentid=$(this).attr("value");
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					var demo_09 = '<dd id="Template1_1_'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" style="width:300px; text-align:left; border:1px solid #b6b9c6; border-bottom:0; background:url(/website/images/stencil_tb.png) no-repeat right center #FEFEFE; height:auto; padding:4px; margin:0; float:left;">\
                        <div class="up_pic up_click" style="height:55px;line-height:25px; width:55px; float:left;">\
                    <span style="width:55px; display:none;background-color:#7fb221;color:#fff; font-size:16px; text-align:center;">编辑<br />配图</span>\
                    <div class="up_newpic" style="width:55px;height:55px;">\
                    <img src="/website/images/tpl7_.png" style="width:55px; height:55px;">\
					</div>\
                </div>\
                <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
                    <span style=" width:210px; float:left; color:#2b2b2b; margin-top:5px; overflow:hidden; height:20px;">内容标题</span>\
                    <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); text-align: center; right:0; margin-left:63px; display:inline; width:245px; background-color: rgb(127, 178, 33); height: 62px; line-height:42px; display: none;">编辑内容<em class="templbtn">\
                            <ul>\
                                <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                                <li class="list5" value="'+data.articleid+'" content="内容标题"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;="" content="看好你" value="637"></li>\
                            </ul>\
                    	</em>\
                    </label>\
                </div>\
        	</dd>';
                    $(".secondmaster9").append(demo_09);
				}
			}
		});
	});

	$(".count-btn10").die().live("click",function(){
		var _index10 = $(".master10").children("dd").last().attr("position");
		if($(".master10").children("dd").length<1){
			_index10=0;
		}
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index10
			},success:function(data){
				if(data.code == "A00006"){
					var demo_10 = '<dd class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="width:95px; height:auto; margin:2px 0 0 3px; display:inline; padding:3px; background-color:#fff; border:1px solid #9ba0b2;" value="'+data.articleid+'">\
                	<div class="up_pic up_click" style="height:95px;">\
            		<span style="display:none;background-color:#7fb221;color:#fff; width:95px;">编辑配图</span>\
            		<div class="up_newpic" style="width:95;height:95px;" ><img src="/website/images/tpl7_.png" width="95" height="95" /></div>\
            	</div>\
            	<div class="edit_demo6" style="overflow:hidden; height:25px;line-height:25px; width:95px;">\
            		<span style="background:#fff; font-size:14px; width:95px;">版块标题</span>\
            		<ul style="display:none; width:100%; height:25px;line-height:25px;background:#4C6B14;">\
            			<li class="del_demo6" style="float:right; margin-top:1px;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
            			<li  class="et_demo6" value="'+data.articleid+'" content="" style="float:right;width:20px;height:22px; margin-top:1px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
            			<li style="float:left;width:55px;height:25px;line-height:25px; font-size:12px;">编辑内容</li>\
            		</ul>\
				</div>\
            </dd>';
                    $(".master10").append(demo_10);
				}
			}
		});
	});
	
	$(".count-secondbtn10").die().live("click",function(){
		 parentid=$(this).attr("value");
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					var demo_10 = '<dd class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="width:95px; height:auto; margin:2px 0 0 3px; display:inline; padding:3px; background-color:#fff; border:1px solid #9ba0b2;" value="'+data.articleid+'">\
                	<div class="up_pic up_click" style="height:95px;">\
            		<span style="display:none;background-color:#7fb221;color:#fff; width:95px;">编辑配图</span>\
            		<div class="up_newpic" style="width:95;height:95px;" ><img src="/website/images/tpl7_.png" width="95" height="95" /></div>\
            	</div>\
            	<div class="edit_demo6" style="overflow:hidden; height:25px;line-height:25px; width:95px;">\
            		<span style="background:#fff; font-size:14px; width:95px;">内容标题</span>\
            		<ul style="display:none; width:100%; height:25px;line-height:25px;background:#4C6B14;">\
            			<li class="del_demo6" style="float:right; margin-top:1px;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
            			<li  class="list5" value="'+data.articleid+'" content="内容标题" style="float:right;width:20px;height:22px; margin-top:1px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
            			<li style="float:left;width:55px;height:25px;line-height:25px; font-size:12px;">编辑内容</li>\
            		</ul>\
				</div>\
            </dd>';
                    $(".secondmaster10").append(demo_10);
				}
			}
		});
	});
	
	$(".count-btn11").die().live("click",function(){
		var _index10 = $(".master11").children("dd").last().attr("position");
		if($(".master11").children("dd").length<1){
			_index10=0;
		}
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index10
			},success:function(data){
				if(data.code == "A00006"){
					var demo_11 = '<dd  class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="width:310px; height:auto; margin:4px; margin-bottom:0; border:1px solid #9ba0b2; background-color:#faf9f9;" value="'+data.articleid+'">\
                	<div class="up_pic up_click" style=" float:left; width:300px; margin:5px; display:inline; height:90px;">\
            		<span style="display:none; width:300px; height:90px; line-height:90px; background-color:#7fb221;color:#fff;">编辑配图</span>\
            		<div class="up_newpic" style="width:100%;height:90px;" >\
            		<img src="/website/images/tpl7_.png" width="300" height="90" />\
            		</div>\
            	</div>\
            	<div class="edit_demo6" style="height:30px;line-height:30px; float:left; width:100%;">\
            		<span style="width:100%;">\
                    	<em style="font-style:normal; float:left; margin-left:10px; display:inline; font-size:14px; color:#1a1a1a;">版块标题</em>\
                    	<em style="font-style:normal; float:right; margin-right:10px; display:inline; font-size:12px; color:#b6b6b6;">'+data.createtime+'</em>\
                    </span>\
            		<ul style="display:none; width:100%; height:30px;line-height:30px;background:#4C6B14;">\
            			<li class="del_demo6" style="float:right;width:20px;height:22px; margin-top:4px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
            			<li  class="et_demo6" value="'+data.articleid+'" content="" style="float:right;width:20px; margin-top:4px; height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
            			<li style="float:left;width:270px;height:30px;line-height:30px; font-size:14px;">编辑内容</li>\
            		</ul>\
				</div>\
            </dd>';
                    $(".master11").append(demo_11);
				}
			}
		});
	});
	
	$(".count-secondbtn11").die().live("click",function(){
		 parentid=$(this).attr("value");
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					var demo_11 = '<dd  class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="width:310px; height:auto; margin:4px; margin-bottom:0; border:1px solid #9ba0b2; background-color:#faf9f9;" value="'+data.articleid+'">\
                	<div class="up_pic up_click" style=" float:left; width:300px; margin:5px; display:inline; height:90px;">\
            		<span style="display:none; width:300px; height:90px; line-height:90px; background-color:#7fb221;color:#fff;">编辑配图</span>\
            		<div class="up_newpic" style="width:100%;height:90px;" >\
            		<img src="/website/images/tpl7_.png" width="300" height="90" />\
            		</div>\
            	</div>\
            	<div class="edit_demo6" style="height:30px;line-height:30px; float:left; width:100%;">\
            		<span style="width:100%;">\
                    	<em style="font-style:normal; float:left; margin-left:10px; display:inline; font-size:14px; color:#1a1a1a;">内容标题</em>\
                    	<em style="font-style:normal; float:right; margin-right:10px; display:inline; font-size:12px; color:#b6b6b6;">'+data.createtime+'</em>\
                    </span>\
            		<ul style="display:none; width:100%; height:30px;line-height:30px;background:#4C6B14;">\
            			<li class="del_demo6" style="float:right;width:20px;height:22px; margin-top:4px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
            			<li  class="list5" value="'+data.articleid+'" content="内容标题" style="float:right;width:20px; margin-top:4px; height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
            			<li style="float:left;width:270px;height:30px;line-height:30px; font-size:14px;">编辑内容</li>\
            		</ul>\
				</div>\
            </dd>';
                    $(".secondmaster11").append(demo_11);
				}
			}
		});
	});
	$(".count-btn12").die().live("click",function(){
		var _index12 = $(".master12").children("dd").last().attr("position");
		if($(".master12").children("dd").length<1){
			_index12=0;
		}
		var Tindex12=$(".master12").children("dd").last().index()+2;
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index12
			},success:function(data){
				if(data.code == "A00006"){
					if((Tindex12)%10==1){
						var demo_12 ='<dd  value="'+data.articleid+'" class="Template1_1 drag Template1_'+data.articleid+'"  position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="background-color:#0066b2;  margin: 2px 0 0 2px; float:left; width:90px; height:120px;  cursor:pointer; text-align:left;">'
	            	 	+'<div class="up_pic up_click" style="width:90px;height:120px;">'
	                          +'<span style="width:90px; height:120px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:120px; text-align:center;">编辑配图</span>'
	                           + '<div class="up_newpic" style="width:90px;height:120px;">'
	                           +'<img src="/website/images/module_13_.jpg" style="width:90px;height:120px;">'
	                           +'</div>'
	                      +'</div>'
	                  +'<div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">'
	            	 +'<span style="position: absolute; width:80px; height:60px; overflow:hidden; line-height:26px; font-size:20px; color:#fff; margin:-120px -6px 5px;">栏目标题</span>\
	            	 	<p class="et_demo6" value="${lis[0]}" content="${lis[1]}"; arctileid="" title="编辑内容" style=" float: right; height: 30px; margin-left: 45px; margin-top: -35px; position: absolute; width: 30px"><img style="float:left;" src="/website/images/sortable6.png" width="30" height="30" /></p>\
	            	 		<label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 0; text-align: center; right:0; margin-left:0; display:inline; width:90px; background-color: rgb(127, 178, 33); height: 60px; line-height:60px; display: none;">\
	                      		   编辑内容\
	                         <em class="templbtn">\
	                            <ul>\
	                              <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
	                           		 <li class="et_demo6"value="'+data.articleid+'" content=""; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
	                   	    	 </ul>\
	                            	</em>\
	                        </label>\
	            	 	</div>\
	            	 	</dd>';
					}else if((Tindex12)%10==2||(Tindex12)%10==6){
						var demo_12 ='<dd  class="Template1_1 drag Template1_'+data.articleid+'"  position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="margin: 2px 0 0 2px; float:left; width:222px; height:120px;  cursor:pointer; text-align:center;" value="'+data.articleid+'">\
	            	 	<div class="up_pic up_click" style="width:222px;height:120px;">\
	                            <span style="width:222px; height:120px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:120px; text-align:center;">编辑配图</span>\
	                            <div class="up_newpic" style="width:222px;height:120px;">\
	                            <img src="/website/images/def_demo.png" style="width:222px;height:120px;">\
							</div>\
	                        </div>\
	                     <div class="edit_demo6" style="height:30px;line-height:30px; position:absolute; right:3px; bottom:0; width:220px;">\
	                                <span style="background:#4F4F4F; margin-left: 2px; color:#fff; font-size:14px; opacity:0.7; width:220px;filter:alpha(Opacity=70);-moz-opacity:0.7;">版块标题</span>\
	                                <ul style="display:none; width:100%; height:22px;line-height:22px; padding:4px 0; background:#4C6B14;">\
	                                    <li class="del_demo6" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
	                                    <li  class="et_demo6" arctileid="'+data.articleid+'" value="'+data.articleid+'" content="" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
	                                    <li style="float:left;width:180px;height:22px;line-height:22px; font-size:14px;">编辑内容</li>\
	                                </ul>\
	                            </div>\
	            	 	</dd>';
					}else if((Tindex12)%10==7){
						var demo_12 ='<dd  class="Template1_1 drag Template1_'+data.articleid+'"  position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="background-color:#0066b2;  margin: 2px 0 0 2px; float:left; width:90px; height:120px;  cursor:pointer; text-align:left;" value="'+data.articleid+'">'
	            	 		+'<div class="up_pic up_click" style="width:90px;height:120px;">'
	                          +'<span style="width:90px; height:120px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:120px; text-align:center;">编辑配图</span>'
	                            +'<div class="up_newpic" style="width:90px;height:120px;">'
	                           +' <img src="/website/images/module_13_1.jpg" style="width:90px;height:120px;">\
							</div>\
	                        </div>\
	                    	<div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
	            	 	<span style="  position: absolute; width:80px; height:60px; overflow:hidden; line-height:26px; font-size:20px; color:#fff; margin:-120px -6px 5px;">栏目标题</span>\
	            	 	<p class="et_demo6" value="'+data.articleid+'" content=""; arctileid="'+data.articleid+'" title="编辑内容" style=" float: right; height: 30px; margin-left: 45px; margin-top: -35px; position: absolute; width: 30px"><img style="float:left;" src="/website/images/sortable6.png" width="30" height="30" /></p>\
	            	 		<label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 0; text-align: center; right:0; margin-left:0; display:inline; width:90px; background-color: rgb(127, 178, 33); height: 60px; line-height:60px; display: none;">\
	                      		   编辑内容\
	                         <em class="templbtn">\
	                            <ul>\
	                              <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
	                           		 <li class="et_demo6"value="${lis[0]}" content="${lis[1]}"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
	                   	    	 </ul>\
	                            	</em>\
	                        </label>\
	            	 	</div>\
	            	 	</dd>';
					}else{
						var demo_12 ='<dd  class="Template1_1 drag Template1_'+data.articleid+'"  position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="background-color:#FFF;  margin: 2px 0 0 2px; float:left; width:104px; height:54px;  cursor:pointer; text-align:left;" value="'+data.articleid+'">\
	            	 	<div class="up_pic up_click" style="width:104px;height:54px;">\
                        <span style="width:104px; height:54px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:54px; text-align:center;">编辑配图</span>\
                        <div class="up_newpic" style="width:104px;height:54px; " >\
                          <img src="/website/images/module_13_2.jpg" style="width:104px;height:54px;">\
                        </div>\
                    </div>\
                <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
        	 	<span style="  position: absolute; width:80px; height:60px; overflow:hidden; line-height:26px; font-size:20px; color:#000; margin:-40px 0 5px;">栏目标题</span>\
        	 		<label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 0; text-align: center; right:0; margin-left:0; display:inline; width:104px; background-color: rgb(127, 178, 33); height: 54px; line-height:54px; display: none;">\
                  		   编辑内容\
                     <em class="templbtn">\
                        <ul>\
                          <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                       		 <li class="et_demo6"value="'+data.articleid+'" content=""; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
               	    	 </ul>\
                        	</em>\
                    </label>\
        	 	</div>\
        	 	</dd>';
					}
					 $(".master12").append(demo_12);
			}
			}
		});
	});
	
	$(".count-secondbtn12").die().live("click",function(){
		 parentid=$(this).attr("value");
		var Tindex12=$(".secondmaster12").children("dd").last().index()+2;
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					if((Tindex12)%10==1){
						var demo_12 ='<dd  value="'+data.articleid+'" class="Template1_1 drag Template1_'+data.articleid+'"  position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="background-color:#0066b2;  margin: 2px 0 0 2px; float:left; width:90px; height:120px;  cursor:pointer; text-align:left;">'
	            	 	+'<div class="up_pic up_click" style="width:90px;height:120px;">'
	                          +'<span style="width:90px; height:120px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:120px; text-align:center;">编辑配图</span>'
	                           + '<div class="up_newpic" style="width:90px;height:120px;">'
	                           +'<img src="/website/images/module_13_.jpg" style="width:90px;height:120px;">'
	                           +'</div>'
	                      +'</div>'
	                  +'<div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">'
	            	 +'<span style="position: absolute; width:80px; height:60px; overflow:hidden; line-height:26px; font-size:20px; color:#fff; margin:-120px -6px 5px;">内容标题</span>\
	            	 	<p class="list5" value="${lis[0]}" content="${lis[1]}"; arctileid="" title="编辑内容" style=" float: right; height: 30px; margin-left: 45px; margin-top: -35px; position: absolute; width: 30px"><img style="float:left;" src="/website/images/sortable6.png" width="30" height="30" /></p>\
	            	 		<label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 0; text-align: center; right:0; margin-left:0; display:inline; width:90px; background-color: rgb(127, 178, 33); height: 60px; line-height:60px; display: none;">\
	                      		   编辑内容\
	                         <em class="templbtn">\
	                            <ul>\
	                              <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
	                           		 <li class="list5"value="'+data.articleid+'" content="内容标题"; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
	                   	    	 </ul>\
	                            	</em>\
	                        </label>\
	            	 	</div>\
	            	 	</dd>';
					}else if((Tindex12)%10==2||(Tindex12)%10==6){
						var demo_12 ='<dd  class="Template1_1 drag Template1_'+data.articleid+'"  position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="margin: 2px 0 0 2px; float:left; width:222px; height:120px;  cursor:pointer; text-align:center;" value="'+data.articleid+'">\
	            	 	<div class="up_pic up_click" style="width:222px;height:120px;">\
	                            <span style="width:222px; height:120px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:120px; text-align:center;">编辑配图</span>\
	                            <div class="up_newpic" style="width:222px;height:120px;">\
	                            <img src="/website/images/def_demo.png" style="width:222px;height:120px;">\
							</div>\
	                        </div>\
	                     <div class="edit_demo6" style="height:30px;line-height:30px; position:absolute; right:3px; bottom:0; width:220px;">\
	                                <span style="background:#4F4F4F; margin-left: 2px; color:#fff; font-size:14px; opacity:0.7; width:220px;filter:alpha(Opacity=70);-moz-opacity:0.7;">内容标题</span>\
	                                <ul style="display:none; width:100%; height:22px;line-height:22px; padding:4px 0; background:#4C6B14;">\
	                                    <li class="del_demo6" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
	                                    <li  class="list5" arctileid="'+data.articleid+'" value="'+data.articleid+'" content="内容标题" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
	                                    <li style="float:left;width:180px;height:22px;line-height:22px; font-size:14px;">编辑内容</li>\
	                                </ul>\
	                            </div>\
	            	 	</dd>';
					}else if((Tindex12)%10==7){
						var demo_12 ='<dd  class="Template1_1 drag Template1_'+data.articleid+'"  position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="background-color:#0066b2;  margin: 2px 0 0 2px; float:left; width:90px; height:120px;  cursor:pointer; text-align:left;" value="'+data.articleid+'">'
	            	 		+'<div class="up_pic up_click" style="width:90px;height:120px;">'
	                          +'<span style="width:90px; height:120px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:120px; text-align:center;">编辑配图</span>'
	                            +'<div class="up_newpic" style="width:90px;height:120px;">'
	                           +' <img src="/website/images/module_13_1.jpg" style="width:90px;height:120px;">\
							</div>\
	                        </div>\
	                    	<div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
	            	 	<span style="  position: absolute; width:80px; height:60px; overflow:hidden; line-height:26px; font-size:20px; color:#fff; margin:-120px -6px 5px;">内容标题</span>\
	            	 	<p class="list5" value="'+data.articleid+'" content=""; arctileid="'+data.articleid+'" title="编辑内容" style=" float: right; height: 30px; margin-left: 45px; margin-top: -35px; position: absolute; width: 30px"><img style="float:left;" src="/website/images/sortable6.png" width="30" height="30" /></p>\
	            	 		<label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 0; text-align: center; right:0; margin-left:0; display:inline; width:90px; background-color: rgb(127, 178, 33); height: 60px; line-height:60px; display: none;">\
	                      		   编辑内容\
	                         <em class="templbtn">\
	                            <ul>\
	                              <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
	                           		 <li class="list5"value="'+data.articleid+'" content="内容标题"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
	                   	    	 </ul>\
	                            	</em>\
	                        </label>\
	            	 	</div>\
	            	 	</dd>';
					}else{
						var demo_12 ='<dd  class="Template1_1 drag Template1_'+data.articleid+'"  position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="background-color:#FFF;  margin: 2px 0 0 2px; float:left; width:104px; height:54px;  cursor:pointer; text-align:left;" value="'+data.articleid+'">\
	            	 	<div class="up_pic up_click" style="width:104px;height:54px;">\
                        <span style="width:104px; height:54px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:54px; text-align:center;">编辑配图</span>\
                        <div class="up_newpic" style="width:104px;height:54px; " >\
                          <img src="/website/images/module_13_2.jpg" style="width:104px;height:54px;">\
                        </div>\
                    </div>\
                <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
        	 	<span style="  position: absolute; width:80px; height:60px; overflow:hidden; line-height:26px; font-size:20px; color:#000; margin:-40px 0 5px;">内容标题</span>\
        	 		<label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 0; text-align: center; right:0; margin-left:0; display:inline; width:104px; background-color: rgb(127, 178, 33); height: 54px; line-height:54px; display: none;">\
                  		   编辑内容\
                     <em class="templbtn">\
                        <ul>\
                          <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                       		 <li class="list5"value="'+data.articleid+'" content="内容标题"; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
               	    	 </ul>\
                        	</em>\
                    </label>\
        	 	</div>\
        	 	</dd>';
					}
					 $(".secondmaster12").append(demo_12);
			}
			}
		});
	});
	
	$(".count-btn13").die().live("click",function(){
		var _index13 = $(".master13").children("dd").last().attr("position");
		if($(".master13").children("dd").length<1){
			_index13=0;
		}
		var Tindex13=$(".master13").children("dd").last().index()+2;
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index13
			},success:function(data){
				if(data.code == "A00006"){
					if((Tindex13)%12==1||(Tindex13)%12==6||(Tindex13)%12==7){
						var demo_13 = '<dd id="Template1_1_'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" style="margin:2px 0 0 2px; overflow: hidden;width:158px; height:158px; text-align:left; float:left;">\
                        <div class="up_pic up_click" style="width:158px;height:158px;">\
                        <span style="width:156px; height:158px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:156px; text-align:center;">编辑配图</span>\
                        <div class="up_newpic" style="width:158px;height:158px;">\
                        <img src="/website/images/def_demo.png" style="width:156px;height:156px;">\
                        </div>\
                    </div>\
                    <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
                        <span style="width:158px;  position: absolute; margin-left: -12px; float:left;  font-size:18px;  text-align: center; color:#000; margin-top:-35px;">版块标题</span>\
                        <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 124px; text-align: center; right:0; margin-left:0; display:inline; width:158px; background-color: rgb(127, 178, 33); height: 20px; line-height:20px; display: none;">\
                            <em class="templbtn">\
                                <ul>\
                                    <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                                    <li class="et_demo6"value="'+data.articleid+'" content=""; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
                                </ul>\
                        	</em>\
                        </label>\
                    </div>\
            	</dd>';
					}else if((Tindex13)%12==8){
						var demo_13 = '<dd id="Template1_1_'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" style="margin:2px 0 0 2px; overflow: hidden;width:158px; height:158px; text-align:left; float:right;">\
                        <div class="up_pic up_click" style="width:158px;height:158px;">\
                        <span style="width:156px; height:158px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:156px; text-align:center;">编辑配图</span>\
                        <div class="up_newpic" style="width:158px;height:158px;">\
                        <img src="/website/images/def_demo.png" style="width:156px;height:156px;">\
                        </div>\
                    </div>\
                    <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
                        <span style="width:158px;  position: absolute; margin-left: -12px; float:left;  font-size:18px;  text-align: center; color:#000; margin-top:-35px;">版块标题</span>\
                        <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 124px; text-align: center; right:0; margin-left:0; display:inline; width:158px; background-color: rgb(127, 178, 33); height: 20px; line-height:20px; display: none;">\
                            <em class="templbtn">\
                                <ul>\
                                    <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                                    <li class="et_demo6"value="'+data.articleid+'" content=""; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
                                </ul>\
                        	</em>\
                        </label>\
                    </div>\
                	</dd>';
					}
					else{
						var demo_13='<dd id="Template1_1_'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" style=" margin:2px 0 0 2px; overflow: hidden;width:78px; height:78px; text-align:left; float:left;">\
                        <div class="up_pic up_click" style="width:78px;height:78px;">\
                        <span style="width:78x; height:78px; font-size:18px; display:none;background-color:#7fb221;color:#fff; line-height:78px; text-align:left;">编辑配图</span>\
                        <div class="up_newpic" style="width:78px;height:78px;">\
                        <img src="/website/images/def_demo.png" style="width:78px;height:78px;">\
						</div>\
                    </div>\
                    <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
                        <span style="width:78px;  position: absolute; margin-left: -12px; float:left; font-size:14px;  text-align: center; color:#000; margin-top:-25px;">版块标题</span>\
                        <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 55px; text-align: center; right:0; margin-left:0; display:inline; width:78px; background-color: rgb(127, 178, 33); height: 20px; line-height:20px; display: none;">\
                            <em class="templbtn">\
                                <ul>\
                                    <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                                    <li class="et_demo6"value="'+data.articleid+'" content=""; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
                                </ul>\
                        	</em>\
                        </label>\
                    </div>\
            	</dd>';
					}					
                    $(".master13").append(demo_13);
				}
			}
		});
	});
	
	$(".count-secondbtn13").die().live("click",function(){
		 parentid=$(this).attr("value");
		var Tindex13=$(".secondmaster13").children("dd").last().index()+2;
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					if((Tindex13)%12==1||(Tindex13)%12==6||(Tindex13)%12==7){
						var demo_13 = '<dd id="Template1_1_'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" style="margin:2px 0 0 2px; overflow: hidden;width:158px; height:158px; text-align:left; float:left;">\
                        <div class="up_pic up_click" style="width:158px;height:158px;">\
                        <span style="width:156px; height:158px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:156px; text-align:center;">编辑配图</span>\
                        <div class="up_newpic" style="width:158px;height:158px;">\
                        <img src="/website/images/def_demo.png" style="width:156px;height:156px;">\
                        </div>\
                    </div>\
                    <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
                        <span style="width:158px;  position: absolute; margin-left: -12px; float:left;  font-size:18px;  text-align: center; color:#000; margin-top:-35px;">内容标题</span>\
                        <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 124px; text-align: center; right:0; margin-left:0; display:inline; width:158px; background-color: rgb(127, 178, 33); height: 20px; line-height:20px; display: none;">\
                            <em class="templbtn">\
                                <ul>\
                                    <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                                    <li class="list5"value="'+data.articleid+'" content="内容标题"; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
                                </ul>\
                        	</em>\
                        </label>\
                    </div>\
            	</dd>';
					}else if((Tindex13)%12==8){
						var demo_13 = '<dd id="Template1_1_'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" style="margin:2px 0 0 2px; overflow: hidden;width:158px; height:158px; text-align:left; float:right;">\
                        <div class="up_pic up_click" style="width:158px;height:158px;">\
                        <span style="width:156px; height:158px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:156px; text-align:center;">编辑配图</span>\
                        <div class="up_newpic" style="width:158px;height:158px;">\
                        <img src="/website/images/def_demo.png" style="width:156px;height:156px;">\
                        </div>\
                    </div>\
                    <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
                        <span style="width:158px;  position: absolute; margin-left: -12px; float:left;  font-size:18px;  text-align: center; color:#000; margin-top:-35px;">内容标题</span>\
                        <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 124px; text-align: center; right:0; margin-left:0; display:inline; width:158px; background-color: rgb(127, 178, 33); height: 20px; line-height:20px; display: none;">\
                            <em class="templbtn">\
                                <ul>\
                                    <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                                    <li class="list5"value="'+data.articleid+'" content="内容标题"; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
                                </ul>\
                        	</em>\
                        </label>\
                    </div>\
                	</dd>';
					}
					else{
						var demo_13='<dd id="Template1_1_'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" value="'+data.articleid+'" style=" margin:2px 0 0 2px; overflow: hidden;width:78px; height:78px; text-align:left; float:left;">\
                        <div class="up_pic up_click" style="width:78px;height:78px;">\
                        <span style="width:78x; height:78px; font-size:18px; display:none;background-color:#7fb221;color:#fff; line-height:78px; text-align:left;">编辑配图</span>\
                        <div class="up_newpic" style="width:78px;height:78px;">\
                        <img src="/website/images/def_demo.png" style="width:78px;height:78px;">\
						</div>\
                    </div>\
                    <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
                        <span style="width:78px;  position: absolute; margin-left: -12px; float:left; font-size:14px;  text-align: center; color:#000; margin-top:-25px;">内容标题</span>\
                        <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 55px; text-align: center; right:0; margin-left:0; display:inline; width:78px; background-color: rgb(127, 178, 33); height: 20px; line-height:20px; display: none;">\
                            <em class="templbtn">\
                                <ul>\
                                    <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
                                    <li class="list5"value="'+data.articleid+'" content="内容标题"; arctileid="'+data.articleid+'" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
                                </ul>\
                        	</em>\
                        </label>\
                    </div>\
            	</dd>';
					}					
                    $(".secondmaster13").append(demo_13);
				}
			}
		});
	});
	
	$(".count-btn14").die().live("click",function(){		
		var _index14 = $(".master14").children("dd").last().attr("position");
		if($(".master14").children("dd").length<1){
			_index14=0;
		}
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index14
			},success:function(data){
				if(data.code == "A00006"){
					var demo_14 = '<dd  class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="float:left; width:134px; height:210px; margin:2px 0 0 2px; border:1px solid #b5b5b5; background-color:#fff; padding:15px 10px 5px 11px; border-radius:5px; display:inline;" value="'+data.articleid+'">\
                    	<div class="up_pic up_click" style=" float:left; width:130px; height:130px; padding:2px; border:1px solid #b5b5b5; border-radius:67px;">\
            		<span style="display:none; float:left; width:130px; height:130px; border-radius:65px; background-color:#7fb221; line-height:130px; color:#fff;">编辑配图</span>\
            		<div class="up_newpic" style="width:130px;height:130px; border-radius:65px; overflow:hidden;" >\
                    	<img style="float:left; width:130px; height:130px;" src="/website/images/def_demo.png" />\
                    </div>\
            	</div>\
            	<div class="edit_demo6" style="height:22px;line-height:22px;">\
            		<span style="font-size:18px; color:#000;height:26px; float:left; width:130px; margin-top:15px;">版块标题</span>\
            		<ul style="display:none; width:100%; height:22px;line-height:22px;background:#4C6B14; float:left; margin-top:15px;">\
            			<li class="del_demo6" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
            			<li  class="et_demo6" value="'+data.articleid+'" content="" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
            			<li style="float:left;width:93px;height:22px;line-height:22px; font-size:14px;">编辑内容</li>\
            		</ul>\
				</div>\
            </dd>';
                    $(".master14").append(demo_14);
				}
			}
		});
	});
	
	$(".count-secondbtn14").die().live("click",function(){
		 parentid=$(this).attr("value");
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					var demo_14 = '<dd  class="Template1_1 drag Template1_'+data.articleid+'" position="'+data.position+'" id="Template1_1_'+data.articleid+'" style="float:left; width:134px; height:210px; margin:2px 0 0 2px; border:1px solid #b5b5b5; background-color:#fff; padding:15px 10px 5px 11px; border-radius:5px; display:inline;" value="'+data.articleid+'">\
                    	<div class="up_pic up_click" style=" float:left; width:130px; height:130px; padding:2px; border:1px solid #b5b5b5; border-radius:67px;">\
            		<span style="display:none; float:left; width:130px; height:130px; border-radius:65px; background-color:#7fb221; line-height:130px; color:#fff;">编辑配图</span>\
            		<div class="up_newpic" style="width:130px;height:130px; border-radius:65px; overflow:hidden;" >\
                    	<img style="float:left; width:130px; height:130px;" src="/website/images/def_demo.png" />\
                    </div>\
            	</div>\
            	<div class="edit_demo6" style="height:22px;line-height:22px;">\
            		<span style="font-size:18px; color:#000;height:26px; float:left; width:130px; margin-top:15px;">内容标题</span>\
            		<ul style="display:none; width:100%; height:22px;line-height:22px;background:#4C6B14; float:left; margin-top:15px;">\
            			<li class="del_demo6" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
            			<li  class="list5" value="'+data.articleid+'" content="内容标题" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
            			<li style="float:left;width:93px;height:22px;line-height:22px; font-size:14px;">编辑内容</li>\
            		</ul>\
				</div>\
            </dd>';
                    $(".secondmaster14").append(demo_14);
				}
			}
		});
	});
	
	$(".count-btn15").die().live("click",function(){
		var _index15 = $(".master_015").children("dd").last().attr("position");
		if($(".master_015").children("dd").length<1){
			_index15=0;
		}
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addmodule",
                "position":_index15
			},success:function(data){
				if(data.code == "A00006"){
					var demo_15 = '<dd value="'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" id="Template1_1_'+data.articleid+'" style="position: relative; width:90px; padding:10px 7px; height:95px; float:left; margin:2px 0 0 2px; display:inline; background-color:#fff;">\
                	<img style="float:left; width:35px; height:37px; margin:18px 28px 10px 27px; display:inline;" src="/website/images/mb_nws15_1.png" />\
                    <em style="width:90px; float:left; line-height:20px; text-align:center; font-style:normal; color:#000; font-size:15px;">版块标题</em>\
                     <ul style="display:none; width:100%; margin-left: -7px;position: absolute; bottom: 0; height:30px;line-height:30px;background:#4C6B14;">\
        			<li class="del_demo6" style="float:right;width:20px;height:22px; margin-top:4px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
        			<li  class="et_demo6" value="'+data.articleid+'" content="" style="float:right;width:20px; margin-top:4px; height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
        			<li style="float:left;width:64px;height:30px;line-height:30px; font-size:14px;">编辑内容</li>\
        			</ul>\
                     </dd>';
                    $(".master_015").append(demo_15);
				}
			}
		});
	});
	
	$(".count-secondbtn15").die().live("click",function(){
		 parentid=$(this).attr("value");
		$.ajax({
			url:"/websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
                "op":"addsecondmodule",
                "parentid":parentid
			},success:function(data){
				if(data.code == "A00006"){
					var demo_15 = '<dd value="'+data.articleid+'" position="'+data.position+'" class="Template1_1 drag Template1_'+data.articleid+'" id="Template1_1_'+data.articleid+'" style="position: relative; width:90px; padding:10px 7px; height:95px; float:left; margin:2px 0 0 2px; display:inline; background-color:#fff;">\
                	<img style="float:left; width:35px; height:37px; margin:18px 28px 10px 27px; display:inline;" src="/website/images/mb_nws15_1.png" />\
                    <em style="width:90px; float:left; line-height:20px; text-align:center; font-style:normal; color:#000; font-size:15px;">内容标题</em>\
                     <ul style="display:none; width:100%; margin-left: -7px;position: absolute; bottom: 0; height:30px;line-height:30px;background:#4C6B14;">\
        			<li class="del_demo6" style="float:right;width:20px;height:22px; margin-top:4px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
        			<li  class="list5" value="'+data.articleid+'" content="内容标题" style="float:right;width:20px; margin-top:4px; height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
        			<li style="float:left;width:64px;height:30px;line-height:30px; font-size:14px;">编辑内容</li>\
        			</ul>\
                     </dd>';
                    $(".secondmaster15").append(demo_15);
				}
			}
		});
	});
	
	$(".master15").find("dl").find("dd").die().live("mouseover mouseout",function(event){
		if(event.type == "mouseover"){
			$(this).find("ul").show();
		}else{
			$(this).find("ul").hide();
		}
	});
	
	//上欻banner配图
	$(".editindeximg_6").die().live('click',function(){
		initset;
		$(".MicrositeTxt,.websitetitle,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.indeximg,.demo_6").hide();
		$(".demo_banner6").show();
		var _index = new Array ("一","二","三","四","五","六","七","八","九","十");
		
		$.ajax({
			url:"websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
				"op":"chaxunlb"
			},success:function(data){
				if(data.code == "A00006"){
					$(".up_loadIMG").empty();
					var item = data.json;
					$(item).each(function(index,item){
						var INDEX = $(".up_loadIMG dl").index()+1;
						var pos_index = _index[INDEX];
						var _obj = '<dl position='+item.position+' class="onlyself_'+item.id+'" id='+item.id+'>'
						        			+'<h5>第<font>'+pos_index+'</font>个轮播广告：</h5>'
						        			+'<dt>'
						        				+'<img alt="点击上传轮播图" src="/website/images/up_banner.png" />'
						        				+'<div class="up_click6">'
						        					+'<input  id="up6guide_'+item.id+'" class="get_attr up6guide_'+item.id+'" name="mp3file" onchange="ajaxFileUpload_06_guide();" type="file" />'
						        				+'</div>'
						        			+'</dt>'
						        			+'<dd><input type="text"   plg_defval = "请输入标题" value="'+item.title+'"/></dd>'
						        			+'<dd><input type="text"   plg_defval = "请输入链接地址 " value="'+item.herf+'"/></dd>'
						        			+'<h5>请按如下格式填写链接地址:http://www.weduty.com</h5>'
						        			+'<div class="close_upImg"></div>'
						        			+'<div style="clear:both;width:100%; height:1px;"></div>'
						        		+'</dl>';
						        $(".up_loadIMG").append(_obj);
						        $(".up_loadIMG dd").find("input").defFieldLabels();
					});
					
				}
			}
		});

	});
	
	$(".editindeximg_second").die().live('click',function(){
		$(".MicrositeTxt,.websitetitle,.addlistcontent,.addarticle,.showlistarticle,.MicrSuccess,.indeximg,.demo_6").hide();
		$(".demo_bannersecond").show();
		var _index = new Array ("一","二","三","四","五","六","七","八","九","十");
		
		$.ajax({
			url:"websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
				"op":"chaxunsecondlb",
				"articleid"	:this_id_def
			},success:function(data){
				if(data.code == "A00006"){
					$(".up_loadIMG2").empty();
					$(".img_box2").empty();
					$(".guide_box2").empty();
					var item = data.json;
					$(item).each(function(index,item){
						var obj2='<li class="guideIMG2_'+item.id+'"><img src="'+item.picurl+'" width="311" height="100"  class="uploadWeb getIMG2_'+item.position+'" /></li>';
						 $(".img_box2").append(obj2);
						var obj3='<li class="guideIMG2_'+item.id+'"></li>';
						 $(".guide_box2").append(obj3);
						var INDEX = $(".up_loadIMG2 dl").index()+1;
						var pos_index = _index[INDEX];
						var _obj = '<dl position='+item.position+' class="onlyself_'+item.id+'" id='+item.id+'>'
						        			+'<h5>第<font>'+pos_index+'</font>个轮播广告：</h5>'
						        			+'<dt>'
						        				+'<img alt="点击上传轮播图" src="/website/images/up_banner.png" />'
						        				+'<div class="up_click6">'
						        					+'<input  id="up6guide2_'+item.id+'" class="get_attr up6guide2_'+item.id+'" name="mp3file" onchange="ajaxFileUpload_secondlb();" type="file" />'
						        				+'</div>'
						        			+'</dt>'
						        			+'<dd><input type="text"   plg_defval = "请输入标题" value="'+item.title+'"/></dd>'
						        			+'<dd><input type="text"   plg_defval = "请输入链接地址 " value="'+item.herf+'"/></dd>'
						        			+'<h5>请按如下格式填写链接地址:http://www.weduty.com</h5>'
						        			+'<div class="close_upImg"></div>'
						        			+'<div style="clear:both;width:100%; height:1px;"></div>'
						        		+'</dl>';
						        $(".up_loadIMG2").append(_obj);
						        $(".up_loadIMG2 dd").find("input").defFieldLabels();
						        
					});
					
				}
			}
		});

	});
	
	/*
	
	$(".master6").delegate("dd","click",function(){
		$(".master6 dd").css({border:"none",width:"140px",height:"92px",lineHeight:"92px"});
	 	$(this).css({border:"2px solid #79e0f6",width:"136px",height:"88px",lineHeight:"88px"});
	 	console.log("a");
	 	$(this).find(".edit_demo6").css({height:"18px",lineHeight:"18px"});
	 	$(this).find(".edit_demo6").find("span").width(136);
	 
	});
	*/
	
	
	$(".guide_box li,.guide_box2 li").die().live('click',function(){
		$(".guide_box li").css({background:"url('/website/images/guide_li.png') -19px 0"});
		$(this).css({background:"url('/website/images/guide_li.png')"});
	});
	$(".up_loadIMG dl,.up_loadIMG2 dl").die().live("mouseover mouseout",function(event){
		if(event.type == 'mouseover'){
			$(this).find(".close_upImg").show();
		}else{
			$(this).find(".close_upImg").hide();
		}
	});
	$(".up_loadIMG dd,.up_loadIMG2 dd").find("input").defFieldLabels();
	//添加轮播广告模块
	$("#add_guide_btn").click(function(){
		//console.log($(this).parent().find(".img_box").find("li").length);
			var _index = new Array ("一","二","三","四","五","六","七","八","九","十");
			if($(".up_loadIMG dl").length < 1){
				var INDEX_m =1;
			}else{
				var INDEX_m =Number($(".up_loadIMG dl").last().attr("position"))+1;
			}			
			var index_x0 = $(".up_loadIMG dl").last().index()+1;
			console.log(index_x0);
			var pos_index = _index[index_x0];
			$.ajax({
				url:"websitestyle.action",
				type:"post",
				dataType:"json",
				timeout:10000,
				data:{
					"op":"creatlb",
					"position":INDEX_m
				},success:function(data){
					if(data.code == "A00006"){
						
						var _obj = '<dl id='+data.id+' class="onlyself_'+data.id+'" position='+INDEX_m+' >'
				        			+'<h5>第<font>'+pos_index+'</font>个轮播广告：</h5>'
				        			+'<dt>'
				        				+'<img alt="点击上传轮播图" src="/website/images/up_banner.png" />'
				        				+'<div class="up_click6">'
				        					+'<input id="up6guide_'+data.id+'" class="get_attr up6guide_'+data.id+'" name="mp3file" onchange="ajaxFileUpload_06_guide();" type="file" />'
				        				+'</div>'
				        			+'</dt>'
				        			+'<dd><input type="text"   plg_defval = "请输入标题"/></dd>'
				        			+'<dd><input type="text"   plg_defval = "请输入链接地址"/></dd>'
				        			+'<h5>请按如下格式填写链接地址:http://www.weduty.com</h5>'
				        			+'<div class="close_upImg"></div>'
				        			+'<div style="clear:both;width:100%; height:1px;"></div>'
				        		+'</dl>';
				        $(".up_loadIMG").append(_obj);
				        $(".up_loadIMG dd").find("input").defFieldLabels();
				        $(".img_box").append('<li class="guideIMG_'+data.id+'"><img class="uploadWeb getIMG_'+INDEX_m+'" width="311" height="100" src="/website/images/m_bgimg.jpg"></li>');
				        $(".guide_box").append('<li class="guideIMG_'+data.id+'"></li>');
					}
				}
			});
		
		
		
		
	});
	
	$(".add_guide_btn2").click(function(){
		//console.log($(this).parent().find(".img_box").find("li").length);
			var _index = new Array ("一","二","三","四","五","六","七","八","九","十");
			if($(".up_loadIMG2 dl").length < 1){
				var INDEX_m =1;
			}else{
				var INDEX_m =Number($(".up_loadIMG2 dl").last().attr("position"))+1;
			}			
			var index_x0 = $(".up_loadIMG2 dl").last().index()+1;
			var pos_index = _index[index_x0];
			$.ajax({
				url:"websitestyle.action",
				type:"post",
				dataType:"json",
				timeout:10000,
				data:{
					"op":"creatsecondlb",
					"position":INDEX_m,
					"articleid":this_id_def
				},success:function(data){
					if(data.code == "A00006"){
						
						var _obj = '<dl id='+data.id+' class="onlyself_'+data.id+'" position='+INDEX_m+' >'
				        			+'<h5>第<font>'+pos_index+'</font>个轮播广告：</h5>'
				        			+'<dt>'
				        				+'<img alt="点击上传轮播图" src="/website/images/up_banner.png" />'
				        				+'<div class="up_click6">'
				        					+'<input id="up6guide2_'+data.id+'" class="get_attr up6guide2_'+data.id+'" name="mp3file" onchange="ajaxFileUpload_secondlb();" type="file" />'
				        				+'</div>'
				        			+'</dt>'
				        			+'<dd><input type="text"   plg_defval = "请输入标题"/></dd>'
				        			+'<dd><input type="text"   plg_defval = "请输入链接地址"/></dd>'
				        			+'<h5>请按如下格式填写链接地址:http://www.weduty.com</h5>'
				        			+'<div class="close_upImg"></div>'
				        			+'<div style="clear:both;width:100%; height:1px;"></div>'
				        		+'</dl>';
				        $(".up_loadIMG2").append(_obj);
				        $(".up_loadIMG2 dd").find("input").defFieldLabels();
				        $(".img_box2").append('<li class="guideIMG2_'+data.id+'"><img class="uploadWeb getIMG_2'+INDEX_m+'" width="311" height="100" src="/website/images/m_bgimg.jpg"></li>');
				        $(".guide_box2").append('<li class="guideIMG2_'+data.id+'"></li>');
					}
				}
			});
		
		
		
		
	});
	
	$(".get_attr").die().live("click",function(){
		self_guideid = $(this).attr("id");
		self_guidepositionID = $(this).parents("dl").attr("position");
	});
	//删除轮播广告模块
	$(".close_upImg").die().live('click',function(){
		var _index = new Array ("一","二","三","四","五","六","七","八","九","十");
		var this_id = $(this).parents("dl").attr("id");
		$(this).parents("dl").remove();
		$.ajax({
			url:"websitestyle.action",
			type:"post",
			dataType:"json",
			timeout:10000,
			data:{
				"op":"dellb",
				"id":this_id
			},success:function(data){
				if(data.code == "A00006"){
					$(".guideIMG_"+this_id).remove();
			        return true;
				}
			}
		});
		$(".up_loadIMG dl").find("h5").find("font").each(function(index,item){
			var _indexof = $(item).parents("dl").index();
		//	var _indexof_posi = $(item).parents("dl").index()+1;
			var pos_index = _index[_indexof];
			$(this).html(pos_index);
			//$(this).parents("dl").attr("position",_indexof_posi);
		});
	});
	
	$(".save_guide_btn").click(function(){
		var lbsum = $(".up_loadIMG").children("dl").length;
		var lbtitlem = $(".up_loadIMG").find("dl");
		var i = lbsum;
		var lbtitleitem;
		var data ={ 
                "op":"lbcontent",
                "lbsum":lbsum
                };
		$(lbtitlem).each(function(index,item){
			var position = $(item).attr("position");
			var lbtitleval =  $(item).find("dd").eq(0).find("input").val();
			var lbherf = $(item).find("dd").eq(1).find("input").val();
			var lbposition = $(item).attr("position");
			i--;
			var lbtitlenub = lbsum - i;
			 data["lbtitle"+i] = lbtitleval;
			 data["lbherf"+i] = lbherf;
			 data["lbposition"+i] = lbposition;
		});
		$.ajax({
				url:"websitestyle.action",
				type:"post",
				dataType:"json",
				timeout:10000,
				data:data,
				success:function(data){
					if(data.code == "A00006"){
						$(".demo_banner6").hide();
						$(".MicrSuccess").show();
					}
				}
			});
		
	});
	$(".save_guide_btn2").click(function(){
		var lbsum = $(".up_loadIMG2").children("dl").length;
		var lbtitlem = $(".up_loadIMG2").find("dl");
		var i = lbsum;
		var lbtitleitem;
		var data ={ 
                "op":"secondlbcontent",
                "lbsum":lbsum,
                "articleid":this_id_def
                }
		$(lbtitlem).each(function(index,item){
			var position = $(item).attr("position");
			var lbtitleval =  $(item).find("dd").eq(0).find("input").val();
			var lbherf = $(item).find("dd").eq(1).find("input").val();
			var lbposition = $(item).attr("position");
			i--;
			var lbtitlenub = lbsum - i;
			 data["lbtitle"+i] = lbtitleval;
			 data["lbherf"+i] = lbherf;
			 data["lbposition"+i] = lbposition;
		});
		$.ajax({
				url:"websitestyle.action",
				type:"post",
				dataType:"json",
				timeout:10000,
				data:data,
				success:function(data){
					if(data.code == "A00006"){
						$(".demo_bannersecond").hide();
						$(".MicrSuccess").show();
					}
				}
			});
		
	});
	
	$(".master7").delegate("li" +
			"",'mouseover mouseout',function(event){
		if(event.type == 'mouseover'){
			$(this).children("ul").eq(1).show();
		}else{
			$(this).children("ul").eq(1).hide();
		}
	});
	//click show self matter
	//点击编辑微网站标题
	$(".editweb").live("click",function(){
		//隐藏提示
		toggle("editorTitle");
//		$(".MicrositeTxt").hide();
//		//显示修改微网站名称
//		$(".websitetitle").show();
//		//隐藏上传首页图片
//		$(".indeximg").hide();
//		//隐藏修改内容
//		$(".addlistcontent").hide();
//		//隐藏修改单一内容
//		$(".addarticle").hide();
//		//隐藏修改内容列表
//		$(".showlistarticle").hide();
//		//隐藏保存成功
//		$(".MicrSuccess").hide();
	});
	
	//点击编辑首页配图
	$(".editindeximg").live("click",function(){
		//隐藏提示
		toggle("editorPhotos");
//		$(".MicrositeTxt").hide();
//		$(".AddPhotos").hide();
//		//显示上传首页图片
//		$(".indeximg").show();
//		//隐藏修改微网站名称
//		$(".websitetitle").hide();
//		//隐藏修改内容
//		$(".addlistcontent").hide();
//		//隐藏修改单一内容
//		$(".addarticle").hide();
//		//隐藏修改内容列表
//		$(".showlistarticle").hide();
//		//隐藏保存成功
//		$(".MicrSuccess").hide();
		$(".AddPhotosBtn_15").html("图片建议像素290 x 100像素");
	});
	
	$(".secondeditindeximg,.second_piculr15").live("click",function(){
		//隐藏提示
		$(".MicrositeTxt").hide();
		$(".AddPhotos").hide();
		//显示上传首页图片
		$(".secondimg").show();
		
		//隐藏修改微网站名称
		$(".websitetitle").hide();
		//隐藏修改内容
		$(".addlistcontent").hide();
		//隐藏修改单一内容
		$(".addarticle").hide();
		//隐藏修改内容列表
		$(".showlistarticle").hide();
		//隐藏保存成功
		$(".MicrSuccess,.addlistcontent_0").hide();
		self_guideid="urlt_second";
		if($(this).attr("value")==1){
			$(".AddPhotosBtn_215").html("图片建议像素290 x 100像素");
		}else{
			$(".AddPhotosBtn_215").html("图片建议像素315 x 180像素");
		}
		
	});
	$(".editindeximg15").live("click",function(){
		//隐藏提示
		$(".MicrositeTxt,.AddPhotos").hide();
		//显示上传首页图片
		$(".indeximg").show();
		//隐藏修改微网站名称
		$(".websitetitle").hide();
		//隐藏修改内容
		$(".addlistcontent").hide();
		//隐藏修改单一内容
		$(".addarticle").hide();
		//隐藏修改内容列表
		$(".showlistarticle").hide();
		//隐藏保存成功
		$(".MicrSuccess,.addlistcontent_0").hide();
		$(".AddPhotosBtn_15").html("图片建议像素315 x 180像素");
	});
	$(".edit_second").live("click",function(){
		//隐藏提示
		$(".MicrositeTxt,.AddPhotos").hide();
		//显示上传首页图片
		$(".secondimg_edt").show();
		//隐藏修改微网站名称
		self_guideid="urlt_second_edt";
		_this_posiid=$(this).attr("value");
		$(".websitetitle").hide();
		//隐藏修改内容
		$(".addlistcontent").hide();
		//隐藏修改单一内容
		$(".addarticle").hide();
		//隐藏修改内容列表
		$(".showlistarticle").hide();
		//隐藏保存成功
		$(".MicrSuccess,.addlistcontent_0").hide();
	});
	
//点击编辑内容(首页) 
//	$(".list4,.et_demo6,.master02 a").die().live("click",function(){
//		$(".AddPhotos,.MicrSuccess,.demo_6").hide();
//		 this_id_def = $(this).parents(".Template1_1").attr("value");
//		 $(".count-btn6_m").attr("value",this_id_def);
//		 var packageid =$(".packageId").val();
//		 var te = $(".tem").val();
//		 $("._morecontent").attr("articleid", this_id_def);
//		 var sdit_cont = $(this).attr("content");
//		 pagebegin=1;
//		if($.trim(sdit_cont) == ""){
//			$(".m_title").val('');
//			editor.html('');
//			$(".MicrositeTxt,.indeximg,.websitetitle,.addarticle,.showlistarticle,.MicrSuccess,.addlistcontent,.two_come").hide();
//			$(".addlistcontent_0,.add_cont2,.select_edit_item").show();			
//			if(packageid<2){
//				$(".add_cont2,.add_active").hide();		
//			}else{
//				if(te<=5){
//					$(".add_cont2").hide();								
//				}
//			}
//			$("html").animate({scrollTop:0},"slow");
//		}else{
//			$.ajax({
//				url:"newwebsitelist.action",
//				type:"post",dataType:"json",
//				timeout:10000,
//				data:{
//					"op":"many",
//	            	"articleid":this_id_def,
//	            	"pagebegin":pagebegin
//				},
//				success:function(data){
//					if(data.code == "A00006"){
//						$("html").animate({scrollTop:0},"slow");
//						if(data.type == 0){
//							$("._face1,._face2,._face3,.edit_momre_cont,.creat_momre_cont,.MicrositeTxt,.select_edit_item,.add_cont4 ").hide();
//							$("._face4,.save_onlycont,.addlistcontent_0,.addlistcontent,.two_come").show();							
//							if(packageid<2){
//								$(".add_cont2,.add_active").hide();		
//							}else{
//								if(te<=5){
//									$(".add_cont2").hide();								
//								}
//							}
//							$(".singlearticle").click();
//							$(".sing1").css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
//							editor.html('');
//							editor.html(data.content);
//						    $(".m_title").val(data.title);
//							$(".back01").attr("typeid",1);
//							$("._face4").attr("articleid",data.articleid);
//							$("._selectface dt").html("<a class='back_top'>首页</a><span > > 文章内容</span>");
//							$("._selectface dt").attr("value",data.articleid);							
//							$(".only_cont").css("backgroundImage","url("+data.threeImg+")");
//							$(".edit_momre_cont").attr("articleid",0);
//							$(".add_cont3").empty();
//							var activity='<li><input type="radio" name="activity" value="1" /><font>链接</font></li>\
//			            		<li><input type="radio" name="activity" value="2" /><font>刮刮卡</font></li>\
//			            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
//			            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
//			            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
//							$(".add_cont3").append(activity);
//						}else if(data.type == 1){
//								$(".listarticle").click();
//								$("._face3").attr("articleid",data.articleid);
//								$(".back01").attr("typeid",1);
//								$(".m_title").val(data.title);
//								$("._face1,._face2,._face4,.select_edit_item,.MicrositeTxt,.activityhref").hide();
//								$("._face3,.addlistcontent_0,.addlistcontent,.two_come").show();
//								if(packageid<2){
//									$(".add_cont2,.add_active").hide();		
//								}else{
//									if(te<=5){
//										$(".add_cont2").hide();								
//									}
//								}
//								$(".more_cont").css("backgroundImage","url("+data.SecondImg+")");
//								$(".edit_momre_cont").attr("articleid",data.articleid);
//								$("._selectface dt").html("<a class='back_top'>首页</a><a class='back_list'> > 内容列表</a>");
//								$("._selectface a").last().attr("value",data.articleid);
//								$(".show_select_tpl").hide();
//								$(".view_select_tpl").show();	
//								$(".list_mb_view a").find("img").attr("src",data.template);
//								$(".changemore").addClass("change_more");
//								 secondid = data.moduleid;
//								 $(".add_active li").eq(0).click();
//								 $(".add_cont3").empty();
//									var activity='<li><input type="radio" name="activity" value="1" /><font>链接</font></li>\
//					            		<li><input type="radio" name="activity" value="2" /><font>刮刮卡</font></li>\
//					            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
//					            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
//					            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
//									$(".add_cont3").append(activity);
//							}else{
//								$("._face1,._face2,._face3,.edit_momre_cont,.creat_momre_cont,.MicrositeTxt,.select_edit_item ").hide();
//								$("._face4,.save_onlycont,.addlistcontent_0,.addlistcontent,.two_come").show();							
//								if(packageid<2){
//									$(".add_cont2,.add_active").hide();		
//								}else{
//									if(te<=5){
//										$(".add_cont2").hide();								
//									}
//								}
//								activityhref=data.href;
//								$(".singlearticle").click();
//								$(".add_active li").eq(1).click();
//								$(".sing1").css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
//								editor.html('');
//								editor.html(data.content);
//							    $(".m_title").val(data.title);
//								$(".back01").attr("typeid",1);
//								$("._face4").attr("articleid",data.articleid);
//								$("._selectface dt").html("<a class='back_top'>首页</a><span > > 活动内容</span>");
//								$("._selectface dt").attr("value",data.articleid);							
//								$(".only_cont").css("backgroundImage","url("+data.threeImg+")");
//								$(".edit_momre_cont").attr("articleid",0);
//							 if(data.type == 2){
//								$(".add_cont3").empty();
//								var activity='<li><input type="radio" checked="checked" name="activity" value="1" /><font>链接</font></li>\
//				            		<li><input type="radio" name="activity" value="2" /><font>刮刮卡</font></li>\
//				            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
//				            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
//				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
//								$(".add_cont3").append(activity);
//								 $(".add_cont3 li").eq(0).click();
//							}else if(data.type == 3){
//								$(".add_cont3").empty();
//								var activity='<li><input type="radio" name="activity" value="1"/><font>链接</font></li>\
//				            		<li><input type="radio" checked="checked"  name="activity" value="2"/><font>刮刮卡</font></li>\
//				            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
//				            		<li><input type="radio" name="activity" value="4"/><font>优惠券</font></li>\
//				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
//								$(".add_cont3").append(activity);
//								 $(".add_cont3 li").eq(1).click();
//							}else if(data.type == 4){
//								$(".add_cont3").empty();
//								var activity='<li><input type="radio" value="1" name="activity"/><font>链接</font></li>\
//				            		<li><input type="radio"  name="activity" value="2" /><font>刮刮卡</font></li>\
//				            		<li><input type="radio" checked="checked" value="3" name="activity"/><font>大转盘</font></li>\
//				            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
//				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" name="activity"/><font>会员卡</font></li>';
//								$(".add_cont3").append(activity);
//								 $(".add_cont3 li").eq(2).click();
//							}else if(data.type == 5){
//								$(".add_cont3").empty();
//								var activity='<li><input type="radio" name="activity" value="1"/><font>链接</font></li>\
//				            		<li><input type="radio"  name="activity" value="2"/><font>刮刮卡</font></li>\
//				            		<li><input type="radio" name="activity" value="3"/><font>大转盘</font></li>\
//				            		<li><input type="radio" checked="checked" value="4" name="activity"/><font>优惠券</font></li>\
//				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" name="activity"/><font>会员卡</font></li>';
//								$(".add_cont3").append(activity);
//								 $(".add_cont3 li").eq(3).click();
//							}else if(data.type == 6){
//								$(".add_cont3").empty();
//								var activity='<li><input type="radio" value="1" name="activity"/><font>链接</font></li>\
//				            		<li><input type="radio"  name="activity" value="2"/><font>刮刮卡</font></li>\
//				            		<li><input type="radio" name="activity" value="3"/><font>大转盘</font></li>\
//				            		<li><input type="radio"   name="activity" value="4" /><font>优惠券</font></li>\
//				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" checked="checked" name="activity"/><font>会员卡</font></li>';
//								$(".add_cont3").append(activity);
//								 $(".add_cont3 li").eq(4).click();
//							}
//						}
//					}else {
//			    		location.href="/login.jsp";
//			    	}
//				}
//			});
//		}		
//	});
	$(".add_cont3 li").die().live('click',function(){
		var showitem = $(this).find("input").attr("value");
		$(".save_activity").attr("value",showitem);
		if(showitem==1){
			if(activityhref!=""){
				$(".m_activityhref").attr("value",activityhref);
			}
			$(".add_cont4").hide();
			$(".activityhref").show();
		}else{
		$.ajax({
			url:"newwebsitelist.action",
			type:"post",dataType:"json",
			timeout:10000,
			data:{
				"op":"activity",
				"activityid":showitem-1
			},
			success:function(data){
				if(data.code == "A00006"){
					$(".selectactivity").empty();
					$.each(data.endgives, function(i, item){
						if(item.href==activityhref){							
							var activity='<option selected="selected" value="'+item.href+'">'+item.CouponTitle+'</option>';
						}else{
							var activity='<option value="'+item.href+'">'+item.CouponTitle+'</option>';
						}
						$(".selectactivity").append(activity);
					});
					if(showitem==5){
						$(".add_cont4,.activityhref").hide();
					}else{
						$(".activityhref").hide();
						$(".add_cont4").show();
					}
				}else {
		    		location.href="/login.jsp";
		    	}
			}
		});
		}
			
	});

	$(".list5,.secondmaster02 a").die().live("click",function(){
		$(".AddPhotos").hide();
		 this_id_def = $(this).parents(".TemplateSecond").attr("value");
		 var packageid =$(".packageId").val();
		 var te = $(".tem").val();
		 var sdit_cont = $(this).attr("content");
		 pagebegin=1;
		if($.trim(sdit_cont) == "内容标题"){
			$(".MicrosListCer li").remove();
			$(".more_content li").remove();
			$(".m_title").val('');
			editor.html('');
			$(".MicrositeTxt,.indeximg,.websitetitle,.addarticle,.showlistarticle,.MicrSuccess,.addlistcontent,.two_come,.select_edit_item").hide();
			$(".save_onlycont,.addlistcontent_0,.addlistcontent,.two_come,.onlyimg").show();
			$(".select_list,.moreimg").hide();
			if(packageid<2){
				$(".add_cont2,.add_active").hide();		
			}else{
				if(te<=5){
					$(".add_cont2").hide();								
				}
			}
			$("html").animate({scrollTop:0},"slow");
		}else{
			$.ajax({
				url:"newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"many",
	            	"articleid":this_id_def,
	            	"pagebegin":pagebegin
				},
				success:function(data){
					if(data.code == "A00006"){
						$("html").animate({scrollTop:0},"slow");
						if(data.type == 0){
							$("._face1,._face2,._face3,.edit_momre_cont,.creat_momre_cont,.MicrositeTxt,.select_edit_item,.select_list,.moreimg").hide();
							$("._face4,.save_onlycont,.addlistcontent_0,.addlistcontent,.two_come,.onlyimg").show();							
							if(packageid<2){
								$(".add_cont2,.add_active").hide();		
							}else{
								if(te<=5){
									$(".add_cont2").hide();								
								}
							}
							$(".singlearticle").click();
							$(".sing1").css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
							editor.html('');
							editor.html(data.content);
						    $(".m_title").val(data.title);
							$(".back01").attr("typeid",1);
							$("._face4").attr("articleid",data.articleid);
							$("._selectface dt").html("<a class='back_top'>首页</a><span > > 文章内容</span>");
							$("._selectface dt").attr("value",data.articleid);							
							$(".only_cont").css("backgroundImage","url("+data.threeImg+")");
							$(".edit_momre_cont").attr("articleid",0);
							$(".add_cont3").empty();
							var activity='<li><input type="radio" name="activity" value="1" /><font>链接</font></li>\
			            		<li><input type="radio" name="activity" value="2" /><font>刮刮卡</font></li>\
			            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
			            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
			            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
							$(".add_cont3").append(activity);
						}else{
							$("._face1,._face2,._face3,.edit_momre_cont,.creat_momre_cont,.MicrositeTxt,.select_edit_item ").hide();
							$("._face4,.save_onlycont,.addlistcontent_0,.addlistcontent,.two_come").show();							
							if(packageid<2){
								$(".add_cont2,.add_active").hide();		
							}else{
								if(te<=5){
									$(".add_cont2").hide();								
								}
							}
							activityhref=data.href;
							$(".singlearticle").click();
							$(".add_active li").eq(1).click();
							$(".sing1").css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
							editor.html('');
							editor.html(data.content);
						    $(".m_title").val(data.title);
							$(".back01").attr("typeid",1);
							$("._face4").attr("articleid",data.articleid);
							$("._selectface dt").html("<a class='back_top'>首页</a><span > > 活动内容</span>");
							$("._selectface dt").attr("value",data.articleid);							
							$(".only_cont").css("backgroundImage","url("+data.threeImg+")");
							$(".edit_momre_cont").attr("articleid",0);
						 if(data.type == 2){
							$(".add_cont3").empty();
							var activity='<li><input type="radio" checked="checked" name="activity" value="1" /><font>链接</font></li>\
			            		<li><input type="radio" name="activity" value="2" /><font>刮刮卡</font></li>\
			            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
			            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
			            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
							$(".add_cont3").append(activity);
							 $(".add_cont3 li").eq(0).click();
						}else if(data.type == 3){
							$(".add_cont3").empty();
							var activity='<li><input type="radio" name="activity" value="1"/><font>链接</font></li>\
			            		<li><input type="radio" checked="checked"  name="activity" value="2"/><font>刮刮卡</font></li>\
			            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
			            		<li><input type="radio" name="activity" value="4"/><font>优惠券</font></li>\
			            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
							$(".add_cont3").append(activity);
							 $(".add_cont3 li").eq(1).click();
						}else if(data.type == 4){
							$(".add_cont3").empty();
							var activity='<li><input type="radio" value="1" name="activity"/><font>链接</font></li>\
			            		<li><input type="radio"  name="activity" value="2" /><font>刮刮卡</font></li>\
			            		<li><input type="radio" checked="checked" value="3" name="activity"/><font>大转盘</font></li>\
			            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
			            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" name="activity"/><font>会员卡</font></li>';
							$(".add_cont3").append(activity);
							 $(".add_cont3 li").eq(2).click();
						}else if(data.type == 5){
							$(".add_cont3").empty();
							var activity='<li><input type="radio" name="activity" value="1"/><font>链接</font></li>\
			            		<li><input type="radio"  name="activity" value="2"/><font>刮刮卡</font></li>\
			            		<li><input type="radio" name="activity" value="3"/><font>大转盘</font></li>\
			            		<li><input type="radio" checked="checked" value="4" name="activity"/><font>优惠券</font></li>\
			            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" name="activity"/><font>会员卡</font></li>';
							$(".add_cont3").append(activity);
							 $(".add_cont3 li").eq(3).click();
						}else if(data.type == 6){
							$(".add_cont3").empty();
							var activity='<li><input type="radio" value="1" name="activity"/><font>链接</font></li>\
			            		<li><input type="radio"  name="activity" value="2"/><font>刮刮卡</font></li>\
			            		<li><input type="radio" name="activity" value="3"/><font>大转盘</font></li>\
			            		<li><input type="radio"   name="activity" value="4" /><font>优惠券</font></li>\
			            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" checked="checked" name="activity"/><font>会员卡</font></li>';
							$(".add_cont3").append(activity);
							 $(".add_cont3 li").eq(4).click();
						}
					
						}
					}else {
			    		location.href="/login.jsp";
			    	}
				}
			});
		}		
	});
	
	$(".savemodule").die().live('click',function(){
		var save_title =$(".m_title").val();
		if($.trim(save_title)==""){
			$('.picurl').tip({width:'240',status:'error',content:'内容标题不能为空！',dis_time:1000});
			return false;
		}
		 $.ajax({
			 url: "/websitestyle.action",
	    		type: "POST",
	    		dataType: "json",
	    		timeout: 100000,
	    		data:{
	    			"articleid":this_id_def,
	    			"title":save_title,
	    			"op":"savesecond",
	    			"moduleid":secondid
				},
				success:function(data){
					if(data.code == "A00006"){
						$(".Template1_"+data.articleid).find(".et_demo6").attr("content",data.title);
						$(".Template1_"+data.articleid).find(".list4").attr("content",data.title);
						$(".Template1_1_"+data.articleid).find("a").html(data.title);
						$(".Template1_1_"+data.articleid).find("a").attr("content",data.title);
						$(".Template1_"+data.articleid).find(".edit_demo6").find("span").html(data.title);
						alert("保存成功");
					}
				}
		 });
	});
	$(".change_more").die().live('click',function(){
		$(".MicrositeLt,.MicrosSelect").hide();
		$(".MicrositeTxt").show();
		 $.ajax({
			 url: "/newwebsitelist.action",
	    		type: "POST",
	    		dataType: "json",
	    		timeout: 100000,
	    		data:{
	    			"op":"findContent",
	    			"articleid":this_id_def
				},
				success:function(data){
					if(data.code == "A00006"){
						if(data.moduleid==1||data.moduleid==0){
							$(".second_mb1").show();
							$(".second_title").find("span").html(data.title);
							$(".second_piculr").find("img").attr("src","/website/images/tpl7_.png");
							if(data.json1[0].picurl!=null){
								$(".second_piculr").find("img").attr("src",data.json1[0].picurl);
							}
							if(data.SecondImg!=null){
								$(".second_mb1").css("background-image","url("+data.SecondImg+")");
							}
							$(".secondmaster02").empty();
							$.each(data.json, function(i, item){      
								   var list_more_rt = '<li class="TemplateSecond Template1_1_'+item.id+' drag" value="'+item.id+'" >\
			                        <a class="" value="'+item.id+'" content="'+item.title+'">'+item.title+'</a>\
			                        <span class="Delete Delete_onlys editcontent"><img src="/website/images/Delete.gif" width="30" height="30" /></span>\
			                    </li>';
			                       $(".secondmaster02").append(list_more_rt);
			                       
								}); 
						}else if(data.moduleid>1&&data.moduleid<6){
							$(".second_mb2").show();
							$(".second_title2").find("span").html(data.title);
							$(".second_piculr2").find("img").attr("src","/website/images/tpl7_.png");
							if(data.json1[0].picurl!=null){
								$(".second_piculr2").find("img").attr("src",data.json1[0].picurl);
							}
							if(data.SecondImg!=null){
								$(".second_mb2").css("background-image","url("+data.SecondImg+")");
							}
							$(".secondmaster02").attr("id","");
							$(".secondmaster02").empty();
							$.each(data.json, function(i, item){      
								   var list_more_rt = '<dd class="TemplateSecond drag Template1_'+item.id+'" position="'+item.position+'" id="Template1_1_'+item.id+'" style="background-color:'+item.bgcolor+'; '+item.style+'" value="'+item.id+'">\
			                    	<span style="'+item.style+'">'+item.title+'</span>\
			                        <label class=" editcontent" style="'+item.style+'">\
										编辑内容\
										<em class="templbtn">\
				                        	<ul>\
				                        		<li class="list1" title="删除编辑内容"></li>\
				                        		<li class="list5" value="'+item.id+'" content="'+item.title+'"title="编辑内容"></li>\
				                        	</ul>\
				                        </em>\
									</label>\
			                    </dd>';
			                       $(".secondmaster02").append(list_more_rt);
			                       
								}); 
						}else if(data.moduleid==6){
							$(".lbpicurl").empty();
							$.each(data.json1, function(i, item){
								if(item.picurl!=null){
									var picurl='<li class="guideIMG2_'+item.id+'"><img src="'+item.picurl+'" width="311" height="100"  class="uploadWeb getIMG2_'+item.position+'" /></li>';
								}else{
									var picurl='<li class="guideIMG2_'+item.id+'"><img src="/website/images/tpl7_.png" width="311" height="100"  class="uploadWeb getIMG2_'+item.position+'" /></li>';
								}
								var box='<li class="guideIMG2_'+item.id+'"></li>';
								 $(".lbguide_box").append(box);
								 $(".lbpicurl").append(picurl);
							});
							
							$(".second_mb6").show();
							$(".secondmaster6").empty();
							$.each(data.json, function(i, item){      
								   var list_more_rt = '<dd  class="TemplateSecond drag Template1_'+item.id+'" id="Template1_1_'+item.id+'" position="'+item.position+'" style="overflow: hidden; width:152px; margin-top: 5px;margin-left: 5px; height:120px; line-height:92px;" value="'+item.id+'">\
			                    	<div class="up_pic up_click" style="height:100px;line-height:100px;" value="6">\
		                    		<span style="width:152px; border-radius: 5px 5px 0 0;display:none;background-color:#7fb221;color:#fff;">编辑配图</span>\
		                    		<div class="up_newpic" style="border-radius: 5px 5px 0 0; width:152px;height:100px;background-image:url('+item.picurl+');" ></div>\
		                    	</div>\
		                    	<div class="edit_demo6" style="height:20px;line-height:20px;">\
		                    		<span style=" border-radius:0 0 5px 5px;width:152px; background:#fff; font-size:14px;">'+item.title+' </span>\
		                    		<ul style=" border-radius:0 0 5px 5px;display:none; width:100%; height:20px;line-height:20x;background:#4C6B14;">\
		                    			<li class="del_demo6" style="float:right;width:20px;height:20px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
		                    			<li  class="list5" value="'+item.id+'" content="'+item.title+'" style="float:right;width:20px;height:20px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
		                    			<li style="border-radius:0 0 5px 5px;float:left;width:100px;height:20px;line-height:20px; font-size:14px;">编辑内容</li>\
		                    		</ul>\
								</div>\
		                    </dd>';
			                       $(".secondmaster6").append(list_more_rt);
			                       
								}); 
							
						}else if(data.moduleid==7){
							$(".second_title7").find("span").html(data.title);
							$(".second_piculr7").find("img").attr("src","/website/images/tpl7_.png");
							if(data.json1[0].picurl!=null){
								$(".second_piculr7").find("img").attr("src",data.json1[0].picurl);
							}
							if(data.SecondImg!=null){
								$(".second_mb7").css("background-image","url("+data.SecondImg+")");
							}
							$(".second_mb7").show();
							$(".secondmaster7").empty();
							$.each(data.json, function(i, item){ 
								var list_more_rt ='<li  class="TemplateSecond drag Template1_'+item.id+'" position="'+item.position+'" id="Template1_1_'+item.id+'" style=" overflow: hidden; position:relative; width:288px; height:62px; line-height:62px;border:1px #b6b9c6 solid; background-color:#fefefe; margin:2px auto;" value="'+item.id+'">\
		                    	<ul value="'+item.id+'">\
	                    		<li class="up_new7_1" style="float:left;height:60px; line-height:60px;">\
	                    			<img style="width:54px; height:54px; margin:4px;"  src="'+item.picurl+'" />\
	                    		</li>\
	                    		<li style="float:left;padding-top:10px;width:195px; height:50px; line-height:20px;">\
	                    			<strong class="listrong" style="width:195px; height:20px; float:left; overflow:hidden;">'+item.title+'</strong>\
	                    		</li>\
	                    		<li style="float:left; width:30px; height:60px; line-height:60px;background:url(/website/images/stencil_tb2.png) 7px 22px no-repeat;"></li>\
	                    	</ul>\
	                    	<label class=" editcontent" style="display:none;position:absolute; color: #FFFFFF;text-align: center; right:0; width:227px;background-color:#7fb221; height:62px; line-height:62px;">\
									编辑内容\
									<em class="templbtn">\
									<ul>\
									<li class="del_demo7" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; " title="删除编辑内容"></li>\
									<li class="list5" value="'+item.id+'" content="'+item.title+'"; style="background:url(/website/images/tag_m.png) no-repeat; " title="编辑内容" arctileid=""></li>\
									</ul>\
									</em>\
							</label>\
	                    </li>';
								 $(".secondmaster7").append(list_more_rt);
							});
						}else if(data.moduleid==8){							
							$(".second_title8").find("span").html(data.title);
							if(data.SecondImg!=null){
								$(".second_mb8").css("background-image","url("+data.SecondImg+")");
							}
								$(".second_mb8").show();
								$(".lbpicurl8").empty();
								var i=0;
								var j = 0;
								while(i<3){
									var picurl=null;
									if(i<data.json1.length){
										j = data.json1[i].position;
						            picurl='<span  id="edit_second_'+data.json1[i].position+'" value="'+data.json1[i].position+'" class="edit_second" style=" width:95px; height:95px; float:left; padding:3px; background-color:#ffffff; border:1px solid #9ba0b2; margin-left:2px; display:inline;">\
					                	<img src="'+data.json1[i].picurl+'" style="width:95px; height:95px; cursor:pointer;">\
					                </span>';
									}else{
										j++;
										 picurl='<span  id="edit_second_'+j+'" value="'+j+'" class="edit_second" style=" width:95px; height:95px; float:left; padding:3px; background-color:#ffffff; border:1px solid #9ba0b2; margin-left:2px; display:inline;">\
						                	<img src="/website/images/tpl7_.png" style="width:95px; height:95px; cursor:pointer;">\
						                </span>';
									}
									$(".lbpicurl8").append(picurl);
									i++;
									
								} 
								$(".secondmaster8").empty();
								$.each(data.json, function(i, item){ 
									var list_more_rt ='<dd id="Template1_1_'+item.id+'" class="TemplateSecond drag Template1_'+item.id+'" value="'+item.id+'" position="'+item.position+'" style="width:300px; text-align:left; border:1px solid #b6b9c6; border-bottom:0; background:url(/website/images/stencil_tb.png) no-repeat right center #FEFEFE; height:auto; padding:4px; margin:0; float:left;">'
			                        +'<div class="up_pic up_click" style="height:55px;line-height:25px; width:55px; float:left;">'
		                            +'<span style="width:55px; display:none;background-color:#7fb221;color:#fff; font-size:16px; text-align:center;">编辑<br />配图</span>'
		                           +' <div class="up_newpic" style="width:55px;height:55px;">'
		                          +'  <img src="/website/images/tpl7_.png" style="width:55px; height:55px;">'
		                         +' </div>'
		                       +' </div>'
		                       +' <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">'
		                          +'  <span style="width:210px; float:left; font-size:18px; color:#2b2b2b; margin-top:5px;">'+item.title+'</span>'
		                          +'  <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); text-align: center; right:0; margin-left:63px; display:inline; width:245px; background-color: rgb(127, 178, 33); height: 62px; line-height:42px; display: none;">编辑内容<em class="templbtn">'
		                                 +'   <ul>'
		                                     +'   <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>'
		                                     +'   <li class="list5"value="'+item.id+'" content="'+item.title+'"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ></li>'
		                                   +' </ul>'
		                            	+'</em>'
		                           +' </label>'
		                      +'  </div>'
		                	+'</dd>';
									 $(".secondmaster8").append(list_more_rt);
								});
								
							}
						else if(data.moduleid==9){
							$(".second_title9").find("span").html(data.title);
							if(data.SecondImg!=null){
								$(".second_mb9").css("background-image","url("+data.SecondImg+")");
							}
							$(".lbpicurl9").empty();
							$.each(data.json1, function(i, item){
								if(item.picurl!=null){
									var picurl='<li class="guideIMG2_'+item.id+'"><img src="'+item.picurl+'" width="311" height="100"  class="uploadWeb getIMG2_'+item.position+'" /></li>';
								}else{
									var picurl='<li class="guideIMG2_'+item.id+'"><img src="/website/images/tpl7_.png" width="311" height="100"  class="uploadWeb getIMG2_'+item.position+'" /></li>';
								}
								var box='<li class="guideIMG2_'+item.id+'"></li>';
								 $(".lbguide_box9").append(box);
								 $(".lbpicurl9").append(picurl);
							});
							
							$(".second_mb9").show();
							$(".secondmaster9").empty();
							$.each(data.json, function(i, item){ 
								
								var list_more_rt ='<dd id="Template1_1_'+item.id+'" class="TemplateSecond drag Template1_'+item.id+'" value="'+item.id+'" position="'+item.position+'" style="width:300px; text-align:left; border:1px solid #b6b9c6; border-bottom:0; background:url(/website/images/stencil_tb.png) no-repeat right center #FEFEFE; height:auto; padding:4px; margin:0; float:left;">'
							                        +'<div class="up_pic up_click" style="height:55px;line-height:25px; width:55px; float:left;">'
						                            +'<span style="width:55px; display:none;background-color:#7fb221;color:#fff; font-size:16px; text-align:center;">编辑<br />配图</span>'
						                           +' <div class="up_newpic" style="width:55px;height:55px;">'
						                          +'  <img src="/website/images/tpl7_.png" style="width:55px; height:55px;">'
						                         +' </div>'
						                       +' </div>'
						                       +' <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">'
						                          +'  <span style="width:210px; float:left; font-size:18px; color:#2b2b2b; margin-top:5px;">'+item.title+'</span>'
						                          +'  <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); text-align: center; right:0; margin-left:63px; display:inline; width:245px; background-color: rgb(127, 178, 33); height: 62px; line-height:42px; display: none;">编辑内容<em class="templbtn">'
						                                 +'   <ul>'
						                                     +'   <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>'
						                                     +'   <li class="list5"value="'+item.id+'" content="'+item.title+'"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ></li>'
						                                   +' </ul>'
						                            	+'</em>'
						                           +' </label>'
						                      +'  </div>'
						                	+'</dd>';
								 $(".secondmaster9").append(list_more_rt);
								 
							});
							
						}else if(data.moduleid==10){
							$(".second_mb10").show();
							$(".second_title10").find("span").html(data.title);
							if(data.SecondImg!=null){
								$(".second_mb10").css("background-image","url("+data.SecondImg+")");
							}
							$(".secondmaster10").empty();
							$.each(data.json, function(i, item){ 
								var list_more_rt='<dd class="TemplateSecond drag Template1_'+item.id+'"  position="'+item.position+'" id="Template1_1_'+item.id+'" style="overflow: hidden; width:95px; height:auto; margin:2px 0 0 3px; display:inline; padding:3px; background-color:#fff; border:1px solid #9ba0b2;" value="'+item.id+'">\
		                    	<div class="up_pic up_click" style="height:95px;" value="10">\
	                    		<span style="display:none;background-color:#7fb221;color:#fff; width:95px;">编辑配图</span>\
	                    		<div class="up_newpic" style="width:95;height:95px;" >\
	                    		<img src="'+item.picurl+'" width="95" height="95" />\
	                    		</div>\
	                    	</div>\
	                    	<div class="edit_demo6" style="overflow:hidden; height:25px;line-height:25px; width:95px;">\
	                    		<span style="background:#fff; font-size:14px; width:95px;">'+item.title+' </span>\
	                    		<ul style="display:none; width:100%; height:25px;line-height:25px;background:#4C6B14;">\
	                    			<li class="del_demo6" style="float:right; margin-top:1px;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
	                    			<li  class="list5" value="'+item.id+'" content="'+item.title+'" style="float:right;width:20px;height:22px; margin-top:1px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
	                    			<li style="float:left;width:55px;height:25px;line-height:25px; font-size:12px;">编辑内容</li>\
	                    			</ul>\
	                    			</div>\
	                    			</dd>';
								$(".secondmaster10").append(list_more_rt);
							});
							}else if(data.moduleid==11){
								$(".second_mb11").show();
								$(".second_title11").find("span").html(data.title);
								if(data.SecondImg!=null){
									$(".second_mb11").css("background-image","url("+data.SecondImg+")");
								}
								$(".secondmaster11").empty();
								$.each(data.json, function(i, item){ 
									var list_more_rt='<dd  class="TemplateSecond drag Template1_'+item.id+'" position="'+item.position+'" id="Template1_1_'+item.id+'" style="overflow: hidden; width:310px; height:auto; margin:4px; margin-bottom:0; border:1px solid #9ba0b2; background-color:#faf9f9;" value="'+item.id+'">\
			                    	<div class="up_pic up_click" style=" float:left; width:300px; margin:5px; display:inline; height:90px;" value="11">\
		                    		<span style="display:none; width:300px; height:90px; line-height:90px; background-color:#7fb221;color:#fff;">编辑配图</span>\
		                    		<div class="up_newpic" style="width:100%;height:90px;" >\
		                    		<img src="'+item.picurl+'" width="300" height="90" />\
		                    		</div>\
		                    	</div>\
		                    	<div class="edit_demo6" style="height:30px;line-height:30px; float:left; width:100%;">\
		                            <span style="width:100%;">\
		                            	<em style="font-style:normal; float:left; margin-left:10px; display:inline; font-size:14px; color:#1a1a1a;">'+item.title+' </em>\
		                            	<em style="font-style:normal; float:right; margin-right:10px; display:inline; font-size:12px; color:#b6b6b6;">'+item.createtime+'</em>\
		                            </span>\
		                    		<ul style="display:none; width:100%; height:30px;line-height:30px;background:#4C6B14;">\
		                    			<li class="del_demo6" style="float:right;width:20px;height:22px; margin-top:4px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
		                    			<li  class="list5" value="'+item.id+'" content="'+item.title+']}" style="float:right;width:20px; margin-top:4px; height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
		                    			<li style="float:left;width:270px;height:30px;line-height:30px; font-size:14px;">编辑内容</li>\
		                    		</ul>\
								</div>\
		                    </dd>';
									$(".secondmaster11").append(list_more_rt);
								});
							}else if(data.moduleid==12){
								$(".second_mb12").show();
								$(".second_title12").find("span").html(data.title);
								if(data.SecondImg!=null){
									$(".second_mb12").css("background-image","url("+data.SecondImg+")");
								}
								$(".secondmaster12").empty();
								for(var i=0;i<data.json.length;i++){
									if((i+1)%10==1||(i+1)%10==7){
										if(data.json[i].picurl=="/website/images/tpl7_.png"){
											var pic="/website/images/module_13_.jpg";
										}
					            		var list_more_rt='<dd  class="TemplateSecond drag Template1_'+data.json[i].id+'"  position="'+data.json[i].position+'" id="Template1_1_'+data.json[i].id+'" style="background-color:#0066b2;  margin: 2px 0 0 2px; float:left; width:90px; height:120px;  cursor:pointer; text-align:left;" value="'+data.json[i].id+'">\
					            	 	<div class="up_pic up_click" style="width:90px;height:120px;" value="12">\
					                            <span style="width:90px; height:120px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:120px; text-align:center;">编辑配图</span>\
					                            <div class="up_newpic" style="width:90px;height:120px;">\
					                            <img src="'+pic+'" style="width:90px;height:120px;">\
					            		</div>\
					                        </div>\
					                    <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
					            	 	<span style="  position: absolute; width:80px; margin:-120px -6px 5px; height:50px; overflow:hidden; line-height:26px;font-size:20px; color:#fff;">'+data.json[i].title+'</span>\
					            	 	<p class="et_demo6" value="'+data.json[i].id+'" content="'+data.json[i].title+'"; arctileid="" title="编辑内容" style=" float: right; height: 30px; margin-left: 45px; margin-top: -35px; position: absolute; width: 30px"><img style="float:left;" src="/website/images/sortable6.png" width="30" height="30" /></p>\
					            	 		<label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 0; text-align: center; right:0; margin-left:0; display:inline; width:90px; background-color: rgb(127, 178, 33); height: 60px; line-height:60px; display: none;">\
					                      		   编辑内容\
					                         <em class="templbtn">\
					                            <ul>\
					                              <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
					                           		 <li class="list5"value="'+data.json[i].id+'" content="'+data.json[i].title+'"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
					                   	    	 </ul>\
					                            	</em>\
					                        </label>\
					            	 	</div>\
					            	 	</dd>';
									}else if((i+1)%10==2||(i+1)%10==6){
										var list_more_rt='<dd  class="TemplateSecond drag Template1_'+data.json[i].id+'"  position="'+data.json[i].position+'" id="Template1_1_'+data.json[i].id+'" style=" margin: 2px 0 0 2px; float:left; width:222px; height:120px;  cursor:pointer; text-align:center;" value="'+data.json[i].id+'">\
					            	 	<div class="up_pic up_click" style="width:222px;height:120px;" value="12">\
					                            <span style="width:222px; height:120px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:120px; text-align:center;">编辑配图</span>\
					                            <div class="up_newpic" style="width:222px;height:120px;">\
					                            <img src="'+data.json[i].picurl+'" style="width:222px;height:120px;">\
					                            </div>\
					                        </div>\
					                     <div class="edit_demo6" style="height:30px;line-height:30px; position:absolute; right:3px; bottom:0; width:220px;">\
					                                <span style="background:#4F4F4F; margin-left: 2px; color:#fff; font-size:14px; opacity:0.7; width:220px;filter:alpha(Opacity=70);-moz-opacity:0.7;">'+data.json[i].title+'</span>\
					                                <ul style="display:none; width:100%; height:22px;line-height:22px; padding:4px 0; background:#4C6B14;">\
					                                    <li class="del_demo6" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
					                                    <li  class="list5" value="'+data.json[i].id+'" content="'+data.json[i].title+'" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
					                                    <li style="float:left;width:180px;height:22px;line-height:22px; font-size:14px;">编辑内容</li>\
					                                </ul>\
					                            </div>\
					                            </dd>';
									}else{
										var list_more_rt='<dd  class="TemplateSecond drag Template1_'+data.json[i].id+'"  position="'+data.json[i].position+'" id="Template1_1_'+data.json[i].id+'" style="background-color:#FFF;  margin: 2px 0 0 2px; float:left; width:104px; height:54px;  cursor:pointer; text-align:left;" value="'+data.json[i].id+'">\
					            	 	<div class="up_pic up_click" style="width:104px;height:54px;" value="12">\
			                            <span style="width:104px; height:54px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:54px; text-align:center;">编辑配图</span>\
			                            <div class="up_newpic" style="width:104px;height:54px; " >\
			                              <img src="/website/images/module_13_2.jpg" style="width:104px;height:54px;">\
			                            </div>\
			                        </div>\
			                    <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
			            	 	<span style="  position: absolute; text-align:center; width:80px; height:40px; overflow:hidden; line-height:35px;font-size:20px; color:#000; margin:-40px 0 5px;">'+data.json[i].title+'</span>\
			            	 		<label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 0; text-align: center; right:0; margin-left:0; display:inline; width:104px; background-color: rgb(127, 178, 33); height: 54px; line-height:54px; display: none;">\
			                      		   编辑内容\
			                         <em class="templbtn">\
			                            <ul>\
			                              <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
			                           		 <li class="list5"value="'+data.json[i].id+'" content="'+data.json[i].title+'"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
			                   	    	 </ul>\
			                            	</em>\
			                        </label>\
			            	 	</div>\
			            	 	</dd>';
									}
									$(".secondmaster12").append(list_more_rt);
								}
							}else if(data.moduleid==13){
								$(".second_mb13").show();
								$(".second_title13").find("span").html(data.title);
								if(data.SecondImg!=null){
									$(".second_mb13").css("background-image","url("+data.SecondImg+")");
								}
								$(".secondmaster13").empty();
								for(var i=0;i<data.json.length;i++){
									if((i+1)%12==1||(i+1)%10==6||(i+1)%10==7||(i+1)%10==8){
										if((i+1)%10==8){
											var sty="float:right;";
										}else{
											var sty="float:left;";
										}
										var list_more_rt='<dd id=" Template1_1_'+data.json[i].id+'" position="'+data.json[i].title+'" class="TemplateSecond drag Template1_'+data.json[i].id+'" value="'+data.json[i].id+'" style="margin:2px 0 0 2px; overflow: hidden;width:158px; height:158px; text-align:left; '+sty+'">\
				                        <div class="up_pic up_click" style="width:158px;height:158px;" value="13">\
			                            <span style="width:156px; height:158px; font-size:20px; display:none;background-color:#7fb221;color:#fff; line-height:156px; text-align:center;">编辑配图</span>\
			                            <div class="up_newpic" style="width:158px;height:158px;">\
			                            <img src="'+data.json[i].picurl+'" style="width:158px;height:158px;">\
			                            </div>\
			                            </div>\
			                            <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
			                            <span style="width:158px; height:23px; position: absolute;  margin-left: -12px; float:left;text-align: center; font-size:18px; color:#000; margin-top:-35px;">'+data.json[i].title+'</span>\
			                            <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 124px; text-align: center; right:0; margin-left:0; display:inline; width:158px; background-color: rgb(127, 178, 33); height: 20px; line-height:20px; display: none;">\
			                                <em class="templbtn">\
			                                    <ul>\
			                                        <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
			                                        <li class="list5"value="'+data.json[i].id+'" content="'+data.json[i].title+'"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; " ;></li>\
			                                    </ul>\
			                                        </em>\
			                                        </label>\
			                                        </div>\
			                           </dd>';
									}else{
										var list_more_rt='<dd id="Template1_1_'+data.json[i].id+'" position="'+data.json[i].title+'" class="TemplateSecond drag Template1_'+data.json[i].id+'" value="'+data.json[i].id+'" style=" margin:2px 0 0 2px; overflow: hidden;width:78px; height:78px; text-align:left; float:left;">\
				                        <div class="up_pic up_click" style="width:78px;height:78px;" value="13">\
			                            <span style="width:78x; height:78px; font-size:18px; display:none;background-color:#7fb221;color:#fff; line-height:78px; text-align:left;">编辑配图</span>\
			                            <div class="up_newpic" style="width:78px;height:78px;">\
			                            <img src="'+data.json[i].picurl+'" style="width:78px;height:78px;">\
			                            </div>\
			                        </div>\
			                        <div class="edit_demo6" style=" float:left; width:210px; margin-left:10px; display:inline; line-height:20px;">\
			                            <span style="width:78px; height:23px; position: absolute;  margin-left: -12px; float:left;text-align: center; font-size:14px; color:#000; margin-top:-25px;">'+data.json[i].title+'</span>\
			                            <label class=" editcontent" style="position: absolute; color: rgb(255, 255, 255); margin-top: 55px; text-align: center; right:0; margin-left:0; display:inline; width:78px; background-color: rgb(127, 178, 33); height: 20px; line-height:20px; display: none;">\
			                                <em class="templbtn">\
			                                    <ul>\
			                                        <li class="del_demo6" title="删除编辑内容" style="background:url(/website/images/tag_m.png) no-repeat -73px 1px; "></li>\
			                                        <li class="list5"value="'+data.json[i].id+'" content="'+data.json[i].title+'"; arctileid="" title="编辑内容" style="background:url(/website/images/tag_m.png) no-repeat; "></li>\
			                                    </ul>\
			                            	</em>\
			                            </label>\
			                        </div>\
			                	</dd>';
									}
									$(".secondmaster13").append(list_more_rt);
								}
							}else if(data.moduleid==14){
								$(".second_mb14").show();
								$(".second_title14").find("span").html(data.title);
								if(data.SecondImg!=null){
									$(".second_mb14").css("background-image","url("+data.SecondImg+")");
								}
								$(".secondmaster14").empty();
								$.each(data.json, function(i, item){ 
									var list_more_rt='<dd  class="TemplateSecond drag Template1_'+item.id+'" position="'+item.title+'" id="Template1_1_'+item.id+'" style=" overflow: hidden; float:left; width:134px; height:210px; margin:2px 0 0 2px; border:1px solid #b5b5b5; background-color:#fff; padding:15px 10px 5px 11px; border-radius:5px; display:inline;" value="'+item.id+'">\
				                    	<div class="up_pic up_click" value="14" style=" float:left; width:130px; height:130px; padding:2px; border:1px solid #b5b5b5; border-radius:67px;">\
		                    		<span style="display:none; float:left; width:130px; height:130px; border-radius:65px; background-color:#7fb221; line-height:130px; color:#fff;">编辑配图</span>\
		                    		<div class="up_newpic" style="width:130px;height:130px; border-radius:65px; overflow:hidden;" >\
		                            	<img style="float:left; width:130px; height:130px;" src="'+item.picurl+'" />\
		                            </div>\
		                    	</div>\
		                    	<div class="edit_demo6" style=" float:left; line-height:26px;">\
		                    		<span style=" font-size:18px; color:#000; height:26px; float:left; width:130px; margin-top:15px;">'+item.title+'</span>\
		                    		<ul style="display:none; width:100%; height:22px;line-height:22px; padding:2px 0; background:#4C6B14; float:left; margin-top:15px;">\
		                    			<li class="del_demo6" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
		                    			<li  class="list5" value="'+item.id+'" content="'+item.title+'" style="float:right;width:20px;height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
		                    			<li style="float:left;width:93px;height:22px;line-height:22px; font-size:14px;">编辑内容</li>\
		                    		</ul>\
								</div>\
		                    </dd>';
									$(".secondmaster14").append(list_more_rt);
								});
							}else if(data.moduleid==15){
								$(".second_mb15").show();
								$(".second_title15").find("span").html(data.title);
								$(".second_piculr15").find("img").attr("src","/website/images/tpl7_.png");
								if(data.json1[0].picurl!=null){
									$(".second_piculr15").find("img").attr("src",data.json1[0].picurl);
								}
								if(data.SecondImg!=null){
									$(".second_mb15").css("background-image","url("+data.SecondImg+")");
								}
								$(".secondmaster15").empty();
								$.each(data.json, function(i, item){
									var list_more_rt='<dd value="'+item.id+'" class="TemplateSecond drag Template1_'+item.id+'" position="'+item.position+'" id="Template1_1_'+item.id+'" style=" overflow: hidden; position: relative; width:90px; padding:10px 7px; height:95px; float:left; margin:2px 0 0 2px; display:inline; background-color:#fff;">\
	                            	<img style="float:left; width:35px; height:37px; margin:18px 28px 10px 27px; display:inline;" src="'+item.picurl+'" />\
	                                 <em style="width:90px;overflow:hidden;  float:left; line-height:20px; text-align:center; font-style:normal; color:#000; font-size:15px;">'+item.title+'</em>\
	                                 <ul style="display:none; width:100%; margin-left: -7px;position: absolute; bottom: 0; height:30px;line-height:30px;background:#4C6B14;">\
	                    			<li class="del_demo6" style="float:right;width:20px;height:22px; margin-top:4px; background:url(/website/images/tag_m.png) no-repeat -73px 1px;"></li>\
	                    			<li  class="list5" value="'+item.id+'" content="'+item.title+'" style="float:right;width:20px; margin-top:4px; height:22px; background:url(/website/images/tag_m.png) no-repeat;"></li>\
	                    			<li style="float:left;width:64px;height:30px;line-height:30px; font-size:14px;">编辑内容</li>\
	                    			</ul>\
	                                 </dd>';
									$(".secondmaster15").append(list_more_rt);
								});
							}
						}
					
				}
		 });
		
		
	});

	$(".up_click6").die().live('click',function(){
		 guideposi = $(this).parents("dl").attr("position");
	});
	
	$(".back02 li").die().live("click",function(){
		$(".AddPhotos,.MicrSuccess").hide();
		 var packageid =$(".packageId").val();
		 var te = $(".tem").val();
		this_id_def = $(this).attr("value");
		var articleid=$(this).attr("value");
		var def_val = $(this).html();
		$(".save_back").hide();
		$(".back02 h1").css("backgroundImage","url(/website/images/website_back02.png)");
		$("._morecontent").attr("articleid", articleid);
		pagebegin=1;
		if($.trim(def_val) == "编辑内容"){
			$(".back_top").click();
		}else{
			$.ajax({
				url:"newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"many",
	            	"articleid":articleid,
	            	"pagebegin":pagebegin
				},
				success:function(data){
					if(data.code == "A00006"){
						$("html").animate({scrollTop:0},"slow");
						if(data.type == 0){
							$("._face1,._face2,._face3,.edit_momre_cont,.creat_momre_cont,.MicrositeTxt,.select_edit_item,.add_cont4 ").hide();
							$("._face4,.save_onlycont,.addlistcontent_0,.addlistcontent,.two_come").show();							
							if(packageid<2){
								$(".add_cont2,.add_active").hide();		
							}else{
								if(te<=5){
									$(".add_cont2").hide();								
								}
							}
							$(".singlearticle").click();
							$(".sing1").css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
							editor.html('');
							editor.html(data.content);
						    $(".m_title").val(data.title);
							$(".back01").attr("typeid",1);
							$("._face4").attr("articleid",data.articleid);
							$("._selectface dt").html("<a class='back_top'>首页</a><span > > 文章内容</span>");
							$("._selectface dt").attr("value",data.articleid);							
							$(".only_cont").css("backgroundImage","url("+data.threeImg+")");
							$(".edit_momre_cont").attr("articleid",0);
							$(".add_cont3").empty();
							var activity='<li><input type="radio" name="activity" value="1" /><font>链接</font></li>\
			            		<li><input type="radio" name="activity" value="2" /><font>刮刮卡</font></li>\
			            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
			            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
			            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
							$(".add_cont3").append(activity);
						}else if(data.type == 1){
								$(".listarticle").click();
								$("._face3").attr("articleid",data.articleid);
								$(".back01").attr("typeid",1);
								$(".m_title").val(data.title);
								$("._face1,._face2,._face4,.select_edit_item,.MicrositeTxt,.activityhref").hide();
								$("._face3,.addlistcontent_0,.addlistcontent,.two_come").show();
								if(packageid<2){
									$(".add_cont2,.add_active").hide();		
								}else{
									if(te<=5){
										$(".add_cont2").hide();								
									}
								}
								$(".more_cont").css("backgroundImage","url("+data.SecondImg+")");
								$(".edit_momre_cont").attr("articleid",data.articleid);
								$("._selectface dt").html("<a class='back_top'>首页</a><a class='back_list'> > 内容列表</a>");
								$("._selectface a").last().attr("value",data.articleid);
								$(".show_select_tpl").hide();
								$(".view_select_tpl").show();	
								$(".list_mb_view a").find("img").attr("src",data.template);
								$(".changemore").addClass("change_more");
								 secondid = data.moduleid;
								 $(".add_active li").eq(0).click();
								 $(".add_cont3").empty();
									var activity='<li><input type="radio" name="activity" value="1" /><font>链接</font></li>\
					            		<li><input type="radio" name="activity" value="2" /><font>刮刮卡</font></li>\
					            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
					            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
					            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
									$(".add_cont3").append(activity);
							}else{
								$("._face1,._face2,._face3,.edit_momre_cont,.creat_momre_cont,.MicrositeTxt,.select_edit_item ").hide();
								$("._face4,.save_onlycont,.addlistcontent_0,.addlistcontent,.two_come").show();							
								if(packageid<2){
									$(".add_cont2,.add_active").hide();		
								}else{
									if(te<=5){
										$(".add_cont2").hide();								
									}
								}
								activityhref=data.href;
								$(".singlearticle").click();
								$(".add_active li").eq(1).click();
								$(".sing1").css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
								editor.html('');
								editor.html(data.content);
							    $(".m_title").val(data.title);
								$(".back01").attr("typeid",1);
								$("._face4").attr("articleid",data.articleid);
								$("._selectface dt").html("<a class='back_top'>首页</a><span > > 活动内容</span>");
								$("._selectface dt").attr("value",data.articleid);							
								$(".only_cont").css("backgroundImage","url("+data.threeImg+")");
								$(".edit_momre_cont").attr("articleid",0);
							 if(data.type == 2){
								$(".add_cont3").empty();
								var activity='<li><input type="radio" checked="checked" name="activity" value="1" /><font>链接</font></li>\
				            		<li><input type="radio" name="activity" value="2" /><font>刮刮卡</font></li>\
				            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
				            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
								$(".add_cont3").append(activity);
								 $(".add_cont3 li").eq(0).click();
							}else if(data.type == 3){
								$(".add_cont3").empty();
								var activity='<li><input type="radio" name="activity" value="1"/><font>链接</font></li>\
				            		<li><input type="radio" checked="checked"  name="activity" value="2"/><font>刮刮卡</font></li>\
				            		<li><input type="radio" name="activity" value="3" /><font>大转盘</font></li>\
				            		<li><input type="radio" name="activity" value="4"/><font>优惠券</font></li>\
				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" name="activity" value="5" /><font>会员卡</font></li>';
								$(".add_cont3").append(activity);
								 $(".add_cont3 li").eq(1).click();
							}else if(data.type == 4){
								$(".add_cont3").empty();
								var activity='<li><input type="radio" value="1" name="activity"/><font>链接</font></li>\
				            		<li><input type="radio"  name="activity" value="2" /><font>刮刮卡</font></li>\
				            		<li><input type="radio" checked="checked" value="3" name="activity"/><font>大转盘</font></li>\
				            		<li><input type="radio" name="activity" value="4" /><font>优惠券</font></li>\
				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" name="activity"/><font>会员卡</font></li>';
								$(".add_cont3").append(activity);
								 $(".add_cont3 li").eq(2).click();
							}else if(data.type == 5){
								$(".add_cont3").empty();
								var activity='<li><input type="radio" name="activity" value="1"/><font>链接</font></li>\
				            		<li><input type="radio"  name="activity" value="2"/><font>刮刮卡</font></li>\
				            		<li><input type="radio" name="activity" value="3"/><font>大转盘</font></li>\
				            		<li><input type="radio" checked="checked" value="4" name="activity"/><font>优惠券</font></li>\
				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" name="activity"/><font>会员卡</font></li>';
								$(".add_cont3").append(activity);
								 $(".add_cont3 li").eq(3).click();
							}else if(data.type == 6){
								$(".add_cont3").empty();
								var activity='<li><input type="radio" value="1" name="activity"/><font>链接</font></li>\
				            		<li><input type="radio"  name="activity" value="2"/><font>刮刮卡</font></li>\
				            		<li><input type="radio" name="activity" value="3"/><font>大转盘</font></li>\
				            		<li><input type="radio"   name="activity" value="4" /><font>优惠券</font></li>\
				            		<li style="border-right:1px #dbd8d8 solid;"><input type="radio" value="5" checked="checked" name="activity"/><font>会员卡</font></li>';
								$(".add_cont3").append(activity);
								 $(".add_cont3 li").eq(4).click();
							}
						}
					}else {
			    		location.href="/login.jsp";
			    	}
				}
			});
		}
	});
	
	
	//保存活动
	$(".save_activity").die().live('click',function(){
		var save_title =$(".m_title").val();
		var activityid =$(".save_activity").attr("value");
		if(activityid==1){
		var	href=$(".m_activityhref").attr("value");
		var activitytype=2;
		}else if(activityid=2){
		var	href=$(".selectactivity").attr("value");
		var activitytype=3;
		}else if(activityid=3){
			var	href=$(".selectactivity").attr("value");
			var activitytype=4;
		}else if(activityid=4){
			var	href=$(".selectactivity").attr("value");
			var activitytype=5;
		}else if(activityid=5){
			var	href=$(".selectactivity").attr("value");
			var activitytype=6;
		}
		$.ajax({
			url:"newwebsitelist.action",
			type:"post",dataType:"json",
			timeout:10000,
			data:{
				"op":"saveactivity",
            	"articleid":this_id_def,
            	"title":save_title,
            	"type":activitytype,
            	"href":href
			},
			success:function(data){
				if(data.code == "A00006"){
					$('.picurl').tip({width:'240',status:'error',content:'保存成功',dis_time:1000});
				}else {
		    		location.href="/login.jsp";
		    	}
			}
		});
		
	});
	
	//edit 
	
	$(".edit_mtitle").click(function(){
		
		$(this).hide();
		$(this).parent().next().show();
		$(".save_mtitle").show();
		$(".MicrosListInp").css({width:"110px",backgroundColor:"#fff"});
		$(".MicrosListNav label").width(160).css({borderColor:"#aebac0",backgroundColor:"#fff"});
	});
	//save
	$(".save_mtitle").click(function(){
		var this_id=$("._morecontent").attr("articleid");
		var title_val = $(".MicrosListInp").val();
		if($.trim(title_val)==""){
			$('.picurl').tip({width:'240',status:'error',content:'栏目标题不能为空！',dis_time:1000});
			return false;
		}
		$(this).hide();
		$(this).parent().next().hide();
		$(".edit_mtitle").show();
		$(".MicrosListInp").css({width:"85px",backgroundColor:"#eff1f5"});
		$(".MicrosListNav label").width(135).css({borderColor:"#EFF1F5",backgroundColor:"#EFF1F5"});
		$.ajax({
			url:"newwebsitelist.action",
			type:"post",dataType:"json",
			timeout:10000,
			data:{
				"op":"savemany",
				"type":1,
            	"articleid":this_id,
            	"articletitle":title_val
			},
			success:function(data){
				if(data.code =="A00006"){
					$(".Template1_"+this_id).find(".et_demo6").attr("content",data.title);
					$(".Template1_1_"+this_id).find("a").html(data.title);
					$(".Template1_"+this_id).find("span").html(data.title);
					$(".more_titles").html(title_val);
					$("._face3").attr("articleid",this_id);
					$("._face3").show();
				//	return true;

				}else {
		    		location.href="/login.jsp";
		    	}
			}
		});
	});
	
	// creat matter
	
	$(".MicrosListBtn1").click(function(){
		if($(".more_titles").html() == ""){
			$('.picurl').tip({width:'240',status:'error',content:'请先添加栏目名称！',dis_time:1000});
			return false;
		}
		$(".showlistarticle,.save_onlycont,.edit_momre_cont,.save_onlycont").hide();
		editor.html('');
		$(".ContentInp").val("");
		$(".only_titles,.only_content").html("");
		$(".addarticle ,.creat_momre_cont").show();
	
	});
	
	//creat more matter
	
	$(".creat_momre_cont").click(function(){			
		var this_id =$("._morecontent").attr("articleid");
		var this_title = $(".ContentInp").val(), this_cont =  editor.html();
		if($.trim(this_title)==""){
			$('.picurl').tip({width:'240',status:'error',content:'文章标题不能为空！',dis_time:1000});
			return false;
		}
		$.ajax({
			url:"newwebsitelist.action",
			type:"post",dataType:"json",
			timeout:"10000",
			data:{
				"op":"savesingle",
				"parentid":this_id,
            	"title":this_title,
            	"content":this_cont
			},
			success:function(data){
				if(data.code == "A00006"){
					$(".showlistarticle,.save_onlycont,._face3,.more_cont,.back_list").show();
					$(".addarticle ,.creat_momre_cont,.edit_momre_cont,._face4,.only_cont").hide();
					$("._face3").attr("articleid",this_id);
					var list_more_rt = '<li>\
	                    	<div class="MicrosName">'+data.title+'</div>\
	                        <div class="MicrosGroup" style="display:none;">\
	                            <label class="HomeAdd HomeAdd'+data.articleid+'"  parentid='+data.articleid+' title="添加标签"></label>\
	                            <label class="Delete Delete_more  Delete'+data.articleid+'" deletid='+data.articleid+' title="删除"></label>\
	                        </div>\
	                        <div class="MicrosTime">'+data.createtime+'</div>\
                       </li>';
                    
                    $(".MicrosListCer ul").prepend(list_more_rt);
                    $(".more_content ul").prepend('<li id='+data.articleid+'><font>'+data.title+'</font></li>');
                    $(".ContentInp").val("");
                    $(".ContentTex").val("");
				}else {
		    		location.href="/login.jsp";
		    	}
			}
		});
		
	});
	//creat more matter end
	var edit_articleid;
	
	$(".HomeAdd").die().live("click",function(){
		edit_articleid = $(this).attr("parentid");
		var picurlid=$("._face3").attr("articleid");
		$("._face4").attr("articleid",picurlid);
		$(".showlistarticle,.creat_momre_cont,._face3,.save_onlycont,.more_cont").hide();
		$(".addarticle ,.edit_momre_cont,._face4,.MicrositeRt,.only_cont").show();
		$("._selectface dt").html("<a class='back_top'>首页</a><a class='back_list'> > 内容列表</a><span class='back_only'> > 文章内容 </span>");
		$.ajax({
				url:"newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"many",
	            	"articleid":edit_articleid
				},
				success:function(data){
					if(data.code=="A00006"){
						editor.html(data.content);
						$(".back01").attr("typeid",2);
						$(".ContentInp").val(data.title);
						$(".ContentTex").val(data.content);
						$(".only_titles").html(data.title);
						$(".only_content").html(data.content);
					}else {
			    		location.href="/login.jsp";
			    	}
				}
		});
	
	});
	//edit matter
	$(".edit_momre_cont").click(function(){
		var partentid=$("._morecontent").attr("articleid");
		var this_title = $(".ContentInp").val(), this_cont = editor.html();
		if($.trim(this_title) == ""){
		     $('.edit_momre_cont').tip({width:'300', status:'error',dis_time:1000,content:'标题不能为空！'});
		     return false;
		}
			$.ajax({
					url:"newwebsitelist.action",
					type:"post",dataType:"json",
					timeout:10000,
					data:{
						"op":"savesingle",
						"parentid":partentid,
		            	"articleid":edit_articleid,
		            	"title":this_title,
	            		"content":this_cont
					},
					success:function(data){
						if(data.code=="A00006"){
							$('.edit_momre_cont').tip({width:'300', status:'right',dis_time:1000,content:'保存成功！'});
							$(".showlistarticle,.edit_momre_cont,.more_cont").show();
							$(".addarticle ,.save_onlycont,.creat_momre_cont,.only_cont").hide();
							$(".HomeAdd"+edit_articleid).parent().prev().html(data.title);
							$("#"+edit_articleid).find("font").html(data.title);
							$(".ContentInp,.ContentTex").val("");	
							
							
						}else {
				    		location.href="/login.jsp";
				    	}
					}
			});
	
			
		
	});
	
	$(".more_content ul").find("li").live("click",function(){
		var selfid = $(this).attr("id");
		edit_articleid = $(this).attr("id");
		var picurlid=$("._face3").attr("articleid");
		$("._face4").attr("articleid",picurlid);
		$(".more_cont,.showlistarticle,._face1,._face2,._face3,.save_onlycont,.creat_momre_cont").hide();
		$(".only_cont,.addarticle,._face4,.edit_momre_cont").show();
		$("._selectface dt").html("<a class='back_top'>首页</a><a class='back_list'> > 内容列表</a><span class='back_only'> > 文章内容 </span>");
		$.ajax({
				url:"newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"many",
	            	"articleid":selfid
				},
				success:function(data){
					if(data.code == "A00006"){
						$(".only_content").html(data.content);
						editor.html(data.content);
						$(".only_titles").html(data.title);
						$(".ContentInp").val(data.title);
						$(".back01").attr("typeid",2);
						$(".only_cont").css("backgroundImage","url("+data.threeImg+")");
					}else {
			    		location.href="/login.jsp";
			    	}
				}
		});
		
	});
	
	//delete data
	
	$(".Delete_more").die().live("click",function(){
		var del_id = $(this).attr("deletid");
		$.ajax({
				url:"newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"deletmany",
	            	"articleid":del_id
				},
				success:function(data){
					if(data.code == "A00006"){
						$(".Delete"+del_id).parents("li").remove();
						$("#"+del_id).remove();
					}else {
			    		location.href="/login.jsp";
			    	}
				}
		});		
		
	});
	
	// select module
	$(".list3").die().live("click",function(){
		var this_id = $(this).attr("arctileid");
		$(".select_module").attr("articleid", this_id);
		$(".select_module").popup({p_width:'370',p_height:'470',p_YN_icon:'n',p_show:'fadein',p_speed:100,p_titletst:'请选择样式'});
	});
	
	$(".module_demo").die().live('mouseover mouseout',function(event){
		if(event.type =='mouseover'){
			$(this).addClass("module_demohov");
		}else{
			$(this).removeClass("module_demohov");
		}

	});
		
	$(".Delete_onlys").die().live("click",function(event){
		event.stopPropagation();
		$(this).parents("li").remove();
		var delonly_id = $(this).parents("li").attr("value");
		
		$.ajax({
				url:"newwebsitelist.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"deletmany",
	            	"articleid":delonly_id
				},
				success:function(data){
					if(data.code == "A00006"){	
					}else {
			    		location.href="/login.jsp";
			    	}
				}
		});  
		
	});
	



//三级页上传
$("._face4").click(function(){
	$(".up_03").show();
});
$(".m_index_3").click(function(){
	$(".close_up1").click();
		var picurlid=$("._face4").attr("articleid");
		$.ajax({
				url:"websitepage.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"morenpicul",
	            	"level":"three",
	            	"articleid":picurlid
				},
				success:function(data){
					if(data.code == "A00006"){
						$(".only_cont").css("backgroundImage","url("+data.picurl+")");
					}else {
			    		location.href="/login.jsp";
			    	}
					
				}
		});
});
//三级页上传完成
//二级页上传
$("._face3").click(function(){
	$(".up_02").show();
});
$(".m_index_2").click(function(){
	$(".close_up1").click();
	var picurlid=$("._face3").attr("articleid");
	$.ajax({
			url:"websitepage.action",
			type:"post",dataType:"json",
			timeout:10000,
			data:{
				"op":"morenpicul",
            	"level":"second",
            	"articleid":picurlid
			},
			success:function(data){
				if(data.code == "A00006"){
					$(".more_cont").css("backgroundImage","url("+data.picurl+")");
				}else {
		    		location.href="/login.jsp";
		    	}
				
			}
		});
});

//二级页上传完成

$(".select_edit_item button").eq(0).click(function(){
	var save_title =$(".m_title").val();
	if($.trim(save_title)==""){
		$('.picurl').tip({width:'240',status:'error',content:'内容标题不能为空！',dis_time:1000});
		return false;
	}
	$.ajax({
		url:"newwebsitelist.action",
		type:"post",dataType:"json",
		timeout:10000,
		data:{
			"op":"savesingle",
        	"type":0,
        	"articleid":this_id_def,
        	"title":save_title
		},
		success:function(data){
			if(data.code == "A00006"){
				$(".m_title").attr("value",data.title)
				$(".select_edit_item").hide();
				$(".addlistcontent,.two_come").show();
				$(".singlearticle").click();
				$(".select_list li").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
				$(".select_list li").eq(0).css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
				$(".Template1_"+data.articleid).find(".et_demo6").attr("content",data.title);
				$(".Template1_"+data.articleid).find(".list4").attr("content",data.title);
				$(".Template1_1_"+data.articleid).find("a").html(data.title);
				$(".Template1_1_"+data.articleid).find("a").attr("content",data.title);
				$(".Template1_"+data.articleid).find(".edit_demo6").find("span").html(data.title);
			}else {
	    		location.href="/login.jsp";
	    	}
		}
	});	
});
$(".select_edit_item button").eq(1).click(function(){
	var save_title =$(".m_title").val();
	if($.trim(save_title)==""){
		$('.picurl').tip({width:'240',status:'error',content:'内容标题不能为空！',dis_time:1000});
		return false;
	}
	$(".select_edit_item ").hide();
	$(".addlistcontent,.two_come").show();
	$(".listarticle").click();
	$(".select_list li").css({backgroundColor:"#F1F0F0",borderBottom:"1px solid #c1c1c1"});
	$(".select_list li").eq(1).css({backgroundColor:"#f9f9f9",borderBottom:"1px solid #f9f9f9"});
});

//选择添加内容或者活动

$(".add_active li").click(function(){
	//$(".add_active li").find("input").attr("checked",false);
	//$(this).find("input").attr("checked",true);
	var showitem = $(this).find("input").attr("value");
		if(showitem == 1){
			$(".add_none,.save_activity,.activityhref").hide();
			$(".edit_matter_05,.save_onlycont").show();
			$(".add_active li").find("input").attr("checked",false);
			$(this).find("input").attr("checked",true);
		}else{
			
			$(".edit_matter_05,.save_onlycont").hide();
			$(".add_none,.save_activity").show();
			$(".add_active li").find("input").attr("checked",false);
			$(this).find("input").attr("checked",true);
		}
});


 
});

/*function pageselectCallback(page_index, jq){
$("#jumpTo").bind('focus',function(){
$("#jBtn").animate({width:"45px",opacity:1},"slow");
});
$("#jumpTo").bind('blur',function(){
	$("#jBtn").animate({width:"0px",opacity:0},"slow");
});
 $.ajax({
		url:"/websitepage.action",
		type:"post",
		dataType:"json",
		timeout:10000,
		data:{
			"pagebegin":$("#pageNo").attr("value"),
			"op":"alltemplate"
		},success:function(data){
			if(data.code == "A00006"){
				var obj = data.cases;
				$(obj).each(function(index ,item){
					var packageid =$(".packageId").val();
					if(packageid>=2){
						var pictirl = '<div class="list_mb2"><a href="/websitestyle.action?op=changemodule&templateid='+item.id+'"><img style="float:left; height:217px; width:155px;;" src="'+item.url+'" /></a></div>';
						$(".moreimg_tpl").append(pictirl);
					}else {
						if(item.id>5){
						var pictirl = '<div class="list_mb2"><img style="float:left; height:217px; width:155px;" src="'+item.url+'" /><div class="busi_mask">商务版</div></div>';
						}else{
						var pictirl = '<div class="list_mb2"><a href="/websitestyle.action?op=changemodule&templateid='+item.id+'"><img style="float:left; height:217px; width:155px;" src="'+item.url+'" /></a></div>';
						}
						$(".moreimg_tpl").append(pictirl);
					}					
							
						});
					}
				}
			});
        return false;
    }
  
    $(document).ready(function(){
    	var optInit = {
    			items_per_page : 2,// 每页显示条数
    			current_page : $("#pageNo").attr("value"),// 当前页
    			num_display_entries : 3,
    			num_edge_entries : 1,
    			ellipse_text : "...",
    			prev_text : "上一页",
    			next_text : "下一页",
    			callback : pageselectCallback
    		};
    		$("#Pagination").pagination(15, optInit);// 前一个参数是总记录数
});*/

//非ie下监听OnPropChanged
        function OnInput1(event) {// event.target 等同于this
	        var testvalue =  event.target.value;
	        var testval =  event.target.value.length;
	        var test = 6 - parseInt(testval) ;
	        var item = $(event.target).parent().next().find("em");
	        if(0 <= test){
	        	item.html(test);
				}else{
					var newvalue = testvalue.toString().substring(0,6);
					$(event.target).val(newvalue);
			} 
        }
//ie下监听OnPropChanged
        function OnPropChanged1(event) { //event.srcElement等同于this
            if (event.propertyName.toLowerCase () == "value") {
		        var testvalue =  event.srcElement.value;
		        var testval =  event.srcElement.value.length;
		        var test = 6 - parseInt(testval) ;
		        var item = $(event.srcElement).parent().next().find("em");
		        if(0 <= test){
		        	item.html(test);
					}else{
						var newvalue = testvalue.toString().substring(0,6);
						$(event.srcElement).val(newvalue);
				} 
            }
            
        }
        
        //非ie下监听OnPropChanged
        function OnInput2(event) {// event.target 等同于this
	        var testvalue =  event.target.value;
	        var testval =  event.target.value.length;
	        var test = 6 - parseInt(testval) ;
	        var item = $(event.target).parent().find("em");
	        if(0 <= test){
	        	item.html(test);
				}else{
					var newvalue = testvalue.toString().substring(0,6);
					$(event.target).val(newvalue);
			} 
        }
//ie下监听OnPropChanged
        function OnPropChanged2(event) { //event.srcElement等同于this
            if (event.propertyName.toLowerCase () == "value") {
		        var testvalue =  event.srcElement.value;
		        var testval =  event.srcElement.value.length;
		        var test = 6 - parseInt(testval) ;
		        var item = $(event.srcElement).parent().find("em");
		        if(0 <= test){
		        	item.html(test);
					}else{
						var newvalue = testvalue.toString().substring(0,6);
						$(event.srcElement).val(newvalue);
				} 
            }
            
        }
        
          //非ie下监听OnPropChanged
        function OnInput3(event) {// event.target 等同于this
	        var testvalue =  event.target.value;
	        var testval =  event.target.value.length;
	        var test = 6 - parseInt(testval) ;
	        var item = $(event.target).next().find("em");
	        if(0 <= test){
	        	item.html(test);
				}else{
					var newvalue = testvalue.toString().substring(0,6);
					$(event.target).val(newvalue);
			} 
        }
//ie下监听OnPropChanged
        function OnPropChanged3(event) { //event.srcElement等同于this
            if (event.propertyName.toLowerCase () == "value") {
		        var testvalue =  event.srcElement.value;
		        var testval =  event.srcElement.value.length;
		        var test = 6 - parseInt(testval) ;
		        var item = $(event.srcElement).next().find("em");
		        if(0 <= test){
		        	item.html(test);
					}else{
						var newvalue = testvalue.toString().substring(0,6);
						$(event.srcElement).val(newvalue);
				} 
            }
            
        }
        
  //隐藏或显示div层     
  function toggle(obj){
	  var arrDivs = ["loadpage","editorTitle","editorPhotos","editorContent","saveSuccess"];
	  for(var i = 0 ; i < arrDivs.length; i++){
		  if(arrDivs[i] == obj){
			  $("#" + obj).show();
		  } else {
			  $("#" + arrDivs[i]).hide();
		  }
	  }
  }
        

