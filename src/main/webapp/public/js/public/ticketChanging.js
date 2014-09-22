var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}
function isNotEmpty(element, message) {
	if (element.value.length == 0 
			|| element.value.replace(/\s/g, '').length == 0) {
		alert(message);
		element.focus();
		return false;
	}
	return true;
}

function validate() {
	var passengersType = document.getElementById('passengersType');
	var priceLevel = document.getElementById('priceLevel');
	var rankBunk = document.getElementById('rankBunk');
	
	var refundRules = document.getElementById('refundRules').value;
	var rerouteRules = document.getElementById('rerouteRules').value;
	var changeRules = document.getElementById('changeRules').value;
	
	if (document.getElementById('carrierId').value == 0) {
		alert("航空公司名称不能为空，请选择！");
		document.getElementById('carrierId').focus();
		return false;
	}
	if (!isNotEmpty(passengersType, "旅客类型不能为空！")) {
		return false;
	}else if(passengersType.value.length>40){
		alert("旅客类型长度不能超过40个字符!");
		document.getElementById('passengersType').value="";
	    document.getElementById('passengersType').focus();
		return false;
	}
	if (!isNotEmpty(priceLevel, "票价级别不能为空！")) {
		return false;
	}else if(priceLevel.value.length>40){
		alert("票价级别长度不能超过40个字符!");
		document.getElementById('priceLevel').value="";
	    document.getElementById('priceLevel').focus();
		return false;
	}
	if (!isNotEmpty(rankBunk, "舱位等级不能为空！")) {
		return false;
	}else if(rankBunk.value.length>40){
		alert("舱位等级长度不能超过40个字符!");
		document.getElementById('rankBunk').value="";
	    document.getElementById('rankBunk').focus();
		return false;
	}
	if(refundRules.length>800){
		alert("退票规定长度不能超过800个字符!");
		document.getElementById('refundRules').value="";
	    document.getElementById('refundRules').focus();
		return false;
	}
	if(rerouteRules.length>150){
		alert("签转规定长度不能超过150个字符!");
		document.getElementById('rerouteRules').value="";
	    document.getElementById('rerouteRules').focus();
		return false;
	}
	if(changeRules.length>1500){
		alert("签转规定长度不能超过1500个字符!");
		document.getElementById('changeRules').value="";
	    document.getElementById('changeRules').focus();
		return false;
	}
	/**
	if(document.all.effectiveDate){
		if (!isNotEmpty(document.getElementById('effectiveDate'), "生效日期不能为空！")) {
			return false;
		}
	}
	*/
	return true;
}


function getCrrier(obj){
	$.get('system/carrierTicket.action?t='+new Date().getTime(),{carrierId:obj},function(data){				
		document.getElementById("carrier").innerHTML = data;
	});
}	

function del(tktId) {
	if (confirm("确定要删除吗?")) {
		javaScript: window.self.location = 'ticketChangDelete.action?id=' + tktId;
	}
}

function init() {
	hiddenBtn('system/ticketChangEdit.action','edit');
	hiddenBtn('system/ticketChangDelete.action','del');
	hiddenBtn('system/ticketChangAdd.action','add');
}