/*
 * Ajax回调判断某用户是否具有某种权限，如果没有权限，则屏蔽按钮
 */
function hiddenBtn(path,opt){
	$.get("system/checkRight.action?timestamp="+new Date().getTime(), {path:path},  
	  	function(data){
	  		if(data == 0){
	  			if(opt =='manyDel' || opt == 'refundTicket' || opt == 'pay' || opt=='refundAdd'|| opt=='refundEdit' || opt=='cancleRefund' || opt=='queryPay' || opt=='webTicketOut'|| opt=='refundSlip'|| opt=='refundStateAdd' || opt=='refundOrder' || opt=='queryOrderState'){
	  				if(isExist(opt)){
	  					document.getElementById(opt).style.display ='block';
	  				}
	  			}
	  			else if(opt=='add'|| opt=='CZorderEdit' || opt=='CAorderEdit' || opt=='VASorderEdit' || opt == 'HUorderEdit' || opt=='reRequest'){
	  				if(isExist(opt)){
	  					document.getElementById(opt).style.display ='block';
	  				}
	  			}
	  			else{
	  				for(var i = 1;i <= 20;i++){
	  					if(isExist(opt+i)){
	  						document.getElementById(opt+i).style.display ='block';
	  					}
		  			}
	  			}
	  		}
	});	
}

function hiddenBtn2(path,opt,tag){
	$.get("system/checkRight.action?timestamp="+new Date().getTime(), {path:path},  
	  	function(data){
	  		if(data == 0){
	  			if(opt=='refundAdds_'+tag || opt=='refundEdits_'+tag){
	  				if(isExist(opt)){
	  					document.getElementById(opt).style.display ='block';
	  				}
	  			}
	  		}
	});	
}

/* 
 * 判断页面上某个元素是否存在 
 * elementId:元素的ID
 */
function isExist(elementId){
	var divEle = document.getElementById(elementId);
	if(divEle){
		return true;
	}else{
		return false;
	}
}


function show(tag) {
	var temp_h1 = document.body.clientHeight;
	var temp_h2 = document.documentElement.clientHeight;
	var isXhtml = (temp_h2<=temp_h1&&temp_h2!=0)?true:false;
	var htmlbody = isXhtml?document.documentElement:document.body;
	var light = document.getElementById(tag);
	var fade = document.getElementById('fade');
	light.style.display = 'block';
	fade.style.display = 'block';
	htmlbody.style.overflow = "auto";
	if(document.getElementById('main') == null){
		if(isXhtml){
			fade.style.height = temp_h2;
		}else{
			fade.style.height = temp_h1;
		}
	}else{
		fade.style.height = document.getElementById('main').offsetHeight +60+ "px";
	}
}
function hide(tag) {
	var temp_h1 = document.body.clientHeight;
	var temp_h2 = document.documentElement.clientHeight;
	var isXhtml = (temp_h2<=temp_h1&&temp_h2!=0)?true:false;
	var htmlbody = isXhtml?document.documentElement:document.body;
	var light = document.getElementById(tag);
	var fade = document.getElementById('fade');
	light.style.display = 'none';
	fade.style.display = 'none';
	htmlbody.style.overflow = "auto";
}