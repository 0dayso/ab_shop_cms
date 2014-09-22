var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

var flag = true;
function isNotEmpty(element, message){
	if(element.value.length == 0 || element.value.replace(/\s/g,'').length==0){
		alert(message);
		element.focus();
		return false;
	}
	return true;
}
function isIP(ip){
	var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	return exp.test(ip);
}

function isTel(tel) {
	var reg = /^\d{3}-\d{8}|\d{4}-\d{7}|\d{7}|\d{8}|\d{11}$/;
	return reg.test(tel);
}
function isMobile(mobile){
	if(/^13\d{9}$/g.test(mobile)||(/^15[0-35-9]\d{8}$/g.test(mobile))||(/^18[05-9]\d{8}$/g.test(mobile))){  
		return true;
	}else{
		return false;
	}
}
function validate(){
	var companyName = document.getElementById('companyName');
	var shortName = document.getElementById('shortName');
	var telNo = document.getElementById('phone').value;
	var ip = document.getElementById("ipAddress").value;
	var mobile = document.getElementById("mobile").value;
	
	var address = document.getElementById('address').value;
	var channelDesc = document.getElementById('channelDesc').value;
	var contactor = document.getElementById('contactor').value;
	
	if(!isNotEmpty(companyName, "公司名称不能为空！")){
		return false;
	}else if(companyName.value.length>50){
		alert("公司名称长度不能超过50个字符!");
		document.getElementById('companyName').value="";
	    document.getElementById('companyName').focus();
		return false;
	}
	if(!isNotEmpty(shortName, "客户简称不能为空！")){
		return false;
	}else if(shortName.value.length>20){
		alert("客户简称长度不能超过20个字符!");
		document.getElementById('shortName').value="";
	    document.getElementById('shortName').focus();
		return false;
	}
	if(address.length>100){
		alert("地址长度不能超过100个字符!");
		document.getElementById('address').value="";
	    document.getElementById('address').focus();
		return false;
	}
	if(channelDesc.length>50){
		alert("大客户号长度不能超过50个字符!");
		document.getElementById('channelDesc').value="";
	    document.getElementById('channelDesc').focus();
		return false;
	}
	if(contactor.length>10){
		alert("联系人姓名长度不能超过10个字符!");
		document.getElementById('contactor').value="";
	    document.getElementById('contactor').focus();
		return false;
	}
	if(!(telNo.length==0) && !(telNo.replace(/\s/g,'').length==0)){
		if(!isTel(telNo)){
			alert("电话号码格式不正确！");
			document.getElementById('phone').value = "";
			document.getElementById('phone').focus();
			return false;
		}
	}
	if(!(ip.length==0) && !(ip.replace(/\s/g,'').length==0)){
		if(!isIP(ip)){
			alert("IP地址格式不正确！");
			document.getElementById('ipAddress').value = "";
			document.getElementById('ipAddress').focus();
			return false;
		}
	}
	if(!(mobile.length==0) && !(mobile.replace(/\s/g,'').length==0)){
		if(!isMobile(mobile)){
			alert("手机号码格式不正确！");
			document.getElementById('mobile').value = "";
			document.getElementById('mobile').focus();
			return false;
		}
	}
	if(flag!=true){
		alert("请重新填写公司名称！");
		return false;
	}
}
function del(customerId){
	if(confirm("确定删除吗?")){
		window.self.location='customer_delete.action?id='+customerId;
	}
}
function assign(customerId,companyName){
	window.self.location='customerAssign.action?id='+customerId+'&companyName='+companyName;
}
function init() {
	hiddenBtn('system/customer_edit.action','edit');
	hiddenBtn('system/customer_delete.action','del');
	hiddenBtn('system/customer_add.action','add');
	hiddenBtn('system/customer_assign.action','assign');
}
function checkCompanyName(){
	var name = document.getElementById("companyName").value.replace(/\s/g,'');
	if(name.length>0){
		$.get('system/customerCheckName.action?timestamp='+new Date().getTime(),{name:name},function(data){
			if(data.length>0){
				document.getElementById("result").innerHTML="<font color='red'>"+data+"</font>";
				flag = false;
			}else{
				document.getElementById("result").innerHTML="";
				flag = true;
			}
		});
	}
}