$(document).ready(function(){
		$(".bar_bg").removeClass("bar_click");
	   $(".weixinkefu").addClass("bar_click");
		
		
		$(".OverviewLogo").find("img").hover(function(){
			$(this).css("border-radius","55px");
			
		},function(){
			$(this).css("border-radius","15px");
		});
		
		//遮罩
		
		var nextpage = geturl().params.nextpage||'';
		if(nextpage==1){
			$(".showmask1").show();
			$(".maskshownull").hide();
		}
		$("#page-1").click(function(){
			location.href="/mpkeyreply.action?nextpage=2";
		});
		
		$(".needmoney").click(function(){
			alert("余额不足！");
		});
		
		$(".mpbusiness").click(function(){
			var mpbusinessid = $(this).attr("mpbusinessid"); 
			var status = $(this).attr("status");
			
			if(status ==1){
				 if(confirm("是否要停止此服务！")){
					 location.href="/weixinbusi.action?op=updateMpBusiness&mpbusinessid="+mpbusinessid;
		    	 }
			}else if(status==2){
				if(confirm("是否要开启此服务！")){
					location.href="/weixinbusi.action?op=updateMpBusiness&mpbusinessid="+mpbusinessid;
		    	 }
			}
		});		
		
		$(".Overview1,.Overview1_over").hover(function(){
			$(".Overview1_over").css("display","block");
		},function(){
			$(".Overview1_over").css("display","none");
		});
		$(".Overview2,.Overview2_over").hover(function(){
			$(".Overview2_over").css("display","block");
		},function(){
			$(".Overview2_over").css("display","none");
		});
		$(".ApplyOver").die().live('click',function(){
			location.href="/faq.action";
		});
		$(".ApplyNavOver").die().live('click',function(){
			$.ajax({
				url:"kefubao.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"apply"
				},
				success:function(data){
					if(data.code=="A00006"){
						if(data.state==1){
							$(".toocle01").show();
							$(".ToocleCer001").html("需提供账号密码自动配置微服务接口完善配置信息，并填写申请所需内容");
						}else if(data.state==2){
							$(".toocle02").show();
						}else if(data.state==3){
							$(".toocle01").show();
							$(".ToocleCer001").html("手动配置接口功能受限，您需提供密码完善配置信息，并填写申请所需内容");
						}else if(data.state==4){
							$(".error").show();
							$("html").animate({scrollTop:0},"slow");
							$(".Toocltishi").html("微服务接口已关闭，请开启后重新申请");
						}
					}else{
			    		location.href="/login.jsp";
			    	}
				}
				});
			
		});
		$(".ToocleClose02").click(function(){
			$(".error").hide();
		});
		$(".ToocleSubmit02").click(function(){
			 if($.trim($(".password102").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("客服宝密码不能为空");
			}else if($.trim($(".password202").attr("value"))!=$.trim($(".password102").attr("value"))){
				$(".error").show();
				$(".Toocltishi").html("客服宝密码两次输入不一致");
			}else if($.trim($(".pname02").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("联系人不能为空");
			}else if($.trim($(".pphone02").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("联系电话不能为空");
			}else if($.trim($(".pemail02").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("邮箱不能为空");
			}else if($.trim($(".bname02").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("企业名称不能为空");
			}else if($.trim($(".baddress02").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("企业所在地不能为空");
			}else if($.trim($(".bcontent02").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("企业简介不能为空");
			}else {
			$.ajax({
				url:"kefubao.action",
				type:"post",dataType:"json",
				timeout:"30000",
				data:{
					"op":"saveapply",
					"password":$(".password102").attr("value"),
					"bname":$(".bname02").attr("value"),
					"baddress":$(".baddress02").attr("value"),
					"industryid":$(".industry02").attr("value"),
					"bcontent":$(".bcontent02").attr("value"),
					"pname":$(".pname02").attr("value"),
					"pphone":$(".pphone02").attr("value"),
					"pemail":$(".pemail02").attr("value")
				},
				success:function(data){
					if(data.code=="A00006"){
						$(".sucess").show();
						$(".toocle02").hide();
						$(".Overview3H1").html("申请中");
						$(".Overview3H1").removeClass("ApplyNavOver");
						$(".Overview3H1").addClass("ApplyOver");
					}else if(data.state==4){
						$(".Toocltishi").html("此公共平台账号已申请过试用，如有疑问请联系在线客服");
		            	$(".error").show();
		            	$(".Overview3H1").addClass("ApplyOver");
					}else if(data.state==5){
						$(".Toocltishi").html("公众平台账号未绑定");
		            	$(".error").show();
					}
				}
			});
			}
		});
		
		$(".ToocleSubmit01").click(function(){
			if($.trim($(".gname01").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("公共平台账号不能为空");
			}else if($.trim($(".gpasword01").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("公共平台密码不能为空");
			}else if($.trim($(".password101").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("客服宝密码不能为空");
			}else if($.trim($(".password201").attr("value"))!=$.trim($(".password101").attr("value"))){
				$(".error").show();
				$(".Toocltishi").html("客服宝密码两次输入不一致");
			}else if($.trim($(".pname01").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("联系人不能为空");
			}else if($.trim($(".pphone01").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("联系电话不能为空");
			}else if($.trim($(".pemail01").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("邮箱不能为空");
			}else if($.trim($(".bname01").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("企业名称不能为空");
			}else if($.trim($(".baddress01").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("企业所在地不能为空");
			}else if($.trim($(".bcontent01").attr("value"))==""){
				$(".error").show();
				$(".Toocltishi").html("企业简介不能为空");
			}else{					
			$.ajax({
				url:"/weixinbusi.action",
				type:"post",dataType:"json",
				timeout:"30000",
				data:{
					"op":"addService",
					"weixinname":$(".gname01").attr("value"),
					"weixinpassword":$(".gpasword01").attr("value"),
					"vcode":$(".vcode").val()
				},
				success:function(data){
					if(data.code=="MPB102"){
						$(".Toocltishi").html("公共平台帐号不存在！");
		            	$(".error").show();
		            	$(".yzm").hide();
					}else if(data.code=="MPB101"){
						$(".Toocltishi").html("公众平台帐号用户名或密码不正确！");
		            	$(".error").show();
		            	$(".yzm").hide();
					}else if(data.code=="A00005"){
						$(".Toocltishi").html("超时失败！");
		            	$(".error").show();
		            	$(".yzm").hide();
					}else if(data.code=="MPB100"){
						$(".Toocltishi").html("未知错误！");
		            	$(".error").show();
		            	$(".yzm").hide();
					}else if(data.code=="MPB202"){
						$(".Toocltishi").html("公众帐号信息不完整，您需登录微信公众帐号并完成头像、描述和运营地区设置后再试！");
		            	$(".error").show();
		            	$(".yzm").hide();
					}else if(data.code=="MPB103"){
						
						$(".Toocltishi").html("请输入验证码！");
		            	$(".error").show();
		            	$(".yzm").show();
						$(".yzmimg").attr("src",data.vimgurl);
					}else if(data.code=="MPB201"){
						$(".Toocltishi").html("公众平台接口配置有错误！");
		            	$(".error").show();
		            	$(".yzm").hide();
					}else if(data.code=="MPB203"){
						$(".Toocltishi").html("公众平台接口设置错误：要求通过实名制认证！");
		            	$(".error").show();
		            	$(".yzm").hide();
					}else if(data.code=="A00006"){
						$.ajax({
							url:"kefubao.action",
							type:"post",dataType:"json",
							timeout:"30000",
							data:{
								"op":"saveapply",
								"password":$(".password101").attr("value"),
								"bname":$(".bname01").attr("value"),
								"baddress":$(".baddress01").attr("value"),
								"industryid":$(".industry01").attr("value"),
								"bcontent":$(".bcontent01").attr("value"),
								"pname":$(".pname01").attr("value"),
								"pphone":$(".pphone01").attr("value"),
								"pemail":$(".pemail01").attr("value")
							},
							success:function(data){
								if(data.code=="A00006"){
									
									$(".sucess").show();
									$(".toocle01").hide();
									$(".Overview3H1").html("申请中");
									$(".Overview3H1").removeClass("ApplyNavOver");
									$(".Overview3H1").addClass("ApplyOver");
								}else if(data.state==4){
									$(".Toocltishi").html("此公共平台账号已申请过试用，如有疑问请联系在线客服");
					            	$(".error").show();
					            	$(".Overview3H1").addClass("ApplyOver");
								}else if(data.state==5){
									$(".Toocltishi").html("公众平台账号未绑定");
					            	$(".error").show();
								}
							}
						});
						
					}else if(data.code=="MPB104"){
						$(".Toocltishi").html("验证码输入错误！");
		            	$(".error").show();
		            	$(".yzm").show();
						$(".yzmimg").attr("src",data.vimgurl);
					}else{
						$(".Toocltishi").html("未知错误！");
		            	$(".error").show();
		            	$(".yzm").hide();
					}
					
				}
			});
			}
		});
		$(".ToocleClose03").click(function(){
			$(".sucess").hide();
		});
		$(".ToocleClose,.ToocleCancel").click(function(){
			$(".toocle01").hide();
			$(".toocle02").hide();
			$(".toocle03").hide();
		});
		$(".Overview3,.Overview3_over").hover(function(){
			$.ajax({
				url:"kefubao.action",
				type:"post",dataType:"json",
				timeout:10000,
				data:{
					"op":"mouseapply"
				},
				success:function(data){
					if(data.code=="A00006"){
						if(data.state==1){
							$(".Overview3H1").html("申请试用");
							$(".Overview3H1").addClass("ApplyNavOver");
							$(".Overview3H1").removeClass("ApplyOver");
						}else if(data.state==2){
							$(".Overview3H1").html("申请中");
							$(".Overview3H1").addClass("ApplyOver");
						}else if(data.state==3){
							$(".Overview3H1").html("使用中");
							$(".Overview3H1").addClass("ApplyOver");
						}else if(data.state==4){
							$(".Overview3H1").html("申请未通过，如有疑问请联系在线客服");
							$(".Overview3H1").addClass("ApplyOver");
							$(".Overview3H1").css("fontSize","14px");
						}else if(data.state==5){
							$(".Overview3H1").html("客服宝使用已关闭，如有疑问请联系在线客服");
							$(".Overview3H1").css("fontSize","14px");
							$(".Overview3H1").addClass("ApplyOver");
						}else if(data.state==6){
							$(".Overview3H1").addClass("ApplyOver");
						}
					}else{
			    		location.href="/login.jsp";
			    	}
				}
				});  
				$(".Overview3H1").show();
			$(".Overview3_over").show();			
			$(".Overview3Bg").show();
		},function(){
			$(".Overview3_over").hide();
			$(".Overview3H1").hide();
			$(".Overview3Bg").hide();
		});
		$(".Overview4,.Overview4_over").hover(function(){
			$(".Overview4_over").css("display","block");
		},function(){
			$(".Overview4_over").css("display","none");
		});
		
		$(".QuickBox dd a").hover(function(){
			$(this).css("background","#FFF");
		},function(){
			$(this).css("background","");
		});
		
	$(".Normal").click(function(){
		$(".Free").show();
		$(".Standard").hide();
		$(".Business").hide();
		$(".CriteriaBtn").show();
		$(".CommerBtn").hide();
		$(".UpgradePop").show();
		$(".VerifyControlText").empty();
		$("<h1>标准版权限</h1>").appendTo(".VerifyControlText");
	});
	$(".UpgradeClose").click(function(){
		$(".UpgradePop").hide();
	});
	$(".Commerce").click(function(){
		$(".Free").hide();
		$(".Standard").show();
		$(".Business").hide();
		$(".CriteriaBtn").hide();
		$(".CommerBtn").show();
		$(".UpgradePop").show();
		$(".VerifyControlText").empty();
		$("<h1>商务版权限</h1>").appendTo(".VerifyControlText");
	});
	$(".Commerce_from_free").click(function(){
		$(".Free").hide();
		$(".Standard").hide();
		$(".Business").show();
		$(".CriteriaBtn").hide();
		$(".CommerBtn").show();
		$(".UpgradePop").show();
		$(".VerifyControlText").empty();
		$("<h1>商务版权限</h1>").appendTo(".VerifyControlText");
	});
	$(".StandardVeriry").click(function(){
		$(".Free").show();
		$(".Standard").hide();
		$(".Business").hide();
		$(".CriteriaBtn").hide();
		$(".CommerBtn").hide();
		$(".UpgradePop").show();
		$(".VerifyControlText").empty();
		$("<h1>商务版权限</h1>").appendTo(".VerifyControlText");
	});
	$(".BusinessVerify").click(function(){
		$(".Free").hide();
		$(".Standard").hide();
		$(".Business").show();
		$(".CriteriaBtn").hide();
		$(".CommerBtn").hide();
		$(".UpgradePop").show();
		$(".VerifyControlText").empty();
		$("<h1>商务版权限</h1>").appendTo(".VerifyControlText");
	});
	
	$(".get_data").click(function(){
		//$(".Platform01").show();
		$(".Progress_show").show();
		_progress();
	});
	/*
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

		//$(".Platform01").hide();
		$(".Progress_show").show();
		_progress();
	});
	*/
	
});
