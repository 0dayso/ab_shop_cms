var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

function del(id){
	if(confirm("确定删除吗?")){
		window.location.href='system/msgsalesDel.action?msgId='+id;
	}
}

function init() {
	hiddenBtn('system/msgsalesAdd.action','add');
	hiddenBtn('system/msgsalesDel.action','del');
	hiddenBtn('system/msgsalesEdit.action','edit');
}

function countWord(tag){
	var len = tag.value;
	var lens = len.replace(/\s/g,'').length;
	document.getElementById('contentLength').value = lens;
	if(lens > 500){
		alert("短信内容不能超过500个字符!");
	}
}
function change(){
	var checkBox = document.getElementById('checkBox');
	var fileDiv = document.getElementById('fileDiv');
	var Btns = document.getElementById('Btns');
	if(checkBox.checked==true){
		fileDiv.style.display="block";
		Btns.style.display="none";
	}else{
		fileDiv.style.display="none";
		Btns.style.display="block";
	}
}
function submitForm(){
	var checkBox = document.getElementById('checkBox');
	var message = document.getElementById('content').value;
	var file = document.getElementById('file').value;
	if(checkBox.checked){
		if(file.length==0){
			alert("请选择需要上传的文件");
			document.getElementById('file').focus();
			return false;
		}
	}else{
		if(message.replace(/\s/g,'').length == 0){
			alert("短信内容不能为空！");
			document.getElementById('content').focus();
			return false;
		}
		if(message.replace(/\s/g,'').length > 500){
			alert("短信内容不能超过500个字符！");
			document.getElementById('content').focus();
			return false;
		}
		var mobiles = document.getElementById('mobiles').value;
		if(mobiles.replace(/\s/g,'').length == 0){
			alert("手机号码不能为空！");
			document.getElementById('mobiles').focus();
			return false;
		}
		
		for (i = 0;  i < mobiles.replace(/\s/g,'').length;  i++){  
	    	if (mobiles.charAt(i) != "," && "1234567890".indexOf(mobiles.charAt(i)) == -1){
	    		alert("号码存在非法字符，只允许输入数字及英文逗号！");
	    		return false;
	    	}
	    }
	    if(mobiles.replace(/\s/g,'').indexOf(',') != -1){
	    	var str = mobiles.split(",");
	    	for(var j = 0; j < str.length;j++){
				if(str[j].substring(0,1) != 1 && str[j].substring(0,1) != 0){
					alert("输入的号码错误，请检查后重新输入！");
					return false;
				}
				if(str[j].length != 11){
					alert("输入的号码错误，请检查后重新输入！");
					return false;
				}
			}
	    }else if(mobiles.replace(/\s/g,'').length != 11 || (mobiles.replace(/\s/g,'').substring(0,1) != 1 && mobiles.replace(/\s/g,'').substring(0,1) != 0)){
			alert("输入的号码错误，请检查后重新输入！");
			return false;
		}
	}
}


function checkData(){
	var orderNo = document.getElementById('orderNo').value;
	if(orderNo.replace(/\s/g,'').length == 0){
		alert("订单号不能为空！");
		document.getElementById('orderNo').focus();
		return false;
	}
	var msgTels = document.getElementById('msgTels').value;
	if(msgTels.replace(/\s/g,'').length == 0){
		alert("电话号码不能为空！");
		document.getElementById('msgTels').focus();
		return false;
	}
	
//	for (i = 0;  i < msgTels.replace(/\s/g,'').length;  i++){  
//    	if (msgTels.charAt(i) != "," && "1234567890".indexOf(msgTels.charAt(i)) == -1){
//    		alert("号码存在非法字符，只允许输入数字及英文逗号！");
//    		return false;
//    	}
//    }
//    if(msgTels.replace(/\s/g,'').indexOf(',') != -1){
//    	var str = msgTels.split(",");
//    	for(var j = 0; j < str.length;j++){
//			if(str[j].substring(0,1) != 1 && str[j].substring(0,1) != 0){
//				alert("输入的号码错误，请检查后重新输入！");
//				return false;
//			}
//			if(str[j].length != 11){
//				alert("输入的号码错误，请检查后重新输入！");
//				return false;
//			}
//		}
//    }else
    if(msgTels.replace(/\s/g,'').length != 11 || (msgTels.replace(/\s/g,'').substring(0,1) != 1 && msgTels.replace(/\s/g,'').substring(0,1) != 0)){
		alert("输入的号码错误，请检查后重新输入！");
		return false;
	}
      var price = document.getElementById('price').value;
	if(price.replace(/\s/g,'').length == 0){
		alert("促销金额不能为空！");
		document.getElementById('price').focus();
		return false;
	}
	var r = /^\d+$/;
	if(!r.test(price)){
		alert("促销金额只能为数字！");
		document.getElementById('price').value = "";
		return false;
		}
//	var msgContents = document.getElementById('msgContents').value;
//	if(msgContents.replace(/\s/g,'').length == 0){
//		alert("填写发送给用户的信息内容不能为空！");
//		document.getElementById('msgContents').focus();
//		return false;
//	}
//	if(msgContents.replace(/\s/g,'').length >500){
//		alert("填写发送给用户的信息内容不能超过500个字符！");
//		document.getElementById('msgContents').focus();
//		return false;
//	}
//	var msgContent = document.getElementById('msgContent').value;
//	if(msgContent.replace(/\s/g,'').length == 0){
//		alert("规定用户发送信息的促销内容不能为空！");
//		document.getElementById('content').focus();
//		return false;
//	}
//	if(msgContent.replace(/\s/g,'').length >100){
//		alert("规定用户发送信息的促销内容不能超过100个字符！");
//		document.getElementById('msgContent').focus();
//		return false;
//	}
//    
//    //获得起始日期，并且变成格式yyyymmdd
//	var beginDate = document.getElementById('beginDate').value; 
//	var begin = beginDate.replace(/-/g,"").replace(/:/g,"").replace(/\s/g,"");  
//	   
//	//获得截止日期，并且变成格式yyyymmdd
//   	var endDate = document.getElementById('endDate').value; 
//   	var end = endDate.replace(/-/g,"").replace(/:/g,"").replace(/\s/g,""); 
//    //如果起始日期大于截止日期，则跳出提示
// 	if(begin!=null && end!=null && parseInt(begin)>parseInt(end)){     
//       	alert("起始日期不能大于结束日期!");
//       	document.getElementById('beginDate').focus();
//       	return false;
// 	}
}
