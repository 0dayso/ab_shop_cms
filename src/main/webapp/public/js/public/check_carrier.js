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

function isTel(tel) {
	var reg = /^\d{3}-\d{8}|\d{4}-\d{7}|\d{7}|\d{8}|\d{11}$/;
	return reg.test(tel);
}

function isFigure(data) {
	var reg = /^\d*$/;
	return reg.test(data);
}

function validate() {
	var code = document.getElementById('code');
	var type = document.getElementById('type');
	var name = document.getElementById('name');
	var countryCn = document.getElementById('countryCn');
	
	var shortNameCn = document.getElementById('shortNameCn').value;
	var nameEn = document.getElementById('nameEn').value;
	var shortNameEn = document.getElementById('shortNameEn').value;
	var countryEn = document.getElementById('countryEn').value;
	var telNo = document.getElementById('phone').value;
	
	if (!isNotEmpty(code, "航空公司代码不能为空！")) {
		return false;
	}else if(code.value.length>3){
		alert("航空公司代码长度不能超过3个字符!");
		document.getElementById('code').value="";
	    document.getElementById('code').focus();
		return false;
	}
	if (!isNotEmpty(type, "类型不能为空！")) {
		return false;
	}
	if (!isNotEmpty(name, "中文名称不能为空！")) {
		return false;
	}else if(name.value.length>30){
		alert("中文名称长度不能超过30个字符!");
		document.getElementById('name').value="";
	    document.getElementById('name').focus();
		return false;
	}
	if (!isNotEmpty(countryCn, "国家中文名不能为空！")) {
		return false;
	}else if(countryCn.value.length>30){
		alert("国家中文名长度不能超过30个字符!");
		document.getElementById('countryCn').value="";
	    document.getElementById('countryCn').focus();
		return false;
	}
	if (!isFigure(document.getElementById('type').value)) {
		alert("类型只能为数字！");
		return false;
	}
	if(shortNameCn.length>10){
		alert("中文名简称长度不能超过10个字符!");
		document.getElementById('shortNameCn').value="";
	    document.getElementById('shortNameCn').focus();
		return false;
	}
	if(nameEn.length>30){
		alert("英文名长度不能超过30个字符!");
		document.getElementById('nameEn').value="";
	    document.getElementById('nameEn').focus();
		return false;
	}
	if(shortNameEn.length>10){
		alert("英文名简称长度不能超过10个字符!");
		document.getElementById('shortNameEn').value="";
	    document.getElementById('shortNameEn').focus();
		return false;
	}
	if(countryEn.length>40){
		alert("国家英文名长度不能超过40个字符!");
		document.getElementById('countryEn').value="";
	    document.getElementById('countryEn').focus();
		return false;
	}
	if (!(telNo.length == 0) && !(telNo.replace(/\s/g, '').length == 0)) {
		if (!isTel(telNo)) {
			alert("电话号码格式不正确！");
			document.getElementById('phone').focus();
			return false;
		}
	}
	return true;
}

function del(carrierId){
	if(confirm("确定删除吗?")){
		window.self.location='carrierDelete.action?id='+carrierId;
	}
}

function init() {
	hiddenBtn('system/carrierEdit.action','edit');
	hiddenBtn('system/carrierDelete.action','del');
	hiddenBtn('system/carrierAdd.action','add');
}
