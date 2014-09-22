var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

String.prototype.trim = function() {
	return this.replace(/^(|[\s])+|(|[\s])+$/g, "");
}
function check() {
	var vender_code = document.getElementById("vender_code").value;
	var company_name = document.getElementById("company_name").value;
	var address = document.getElementById("address").value;
	var contactor = document.getElementById("contactor").value;
	var mobile = document.getElementById("mobile").value;
	var phone = document.getElementById("phone").value;
	var email = document.getElementById("email").value;
	var postcode=document.getElementById("postcode").value;

	if (vender_code != null && vender_code != '')
		vender_code = vender_code.trim();
	if (company_name != null && company_name != '')
		company_name = company_name.trim();
	if (address != null && address != '')
		address = address.trim();
	if (contactor != null && contactor != '')
		contactor = contactor.trim();
	if (mobile != null && mobile != '')
		mobile = mobile.trim();
	if (phone != null && phone != '')
		phone = phone.trim();
	if (email != null && email != '')
		email = email.trim();
	if (postcode != null && postcode != '')
		postcode = postcode.trim();
	
	if (vender_code.length == 0 || vender_code.length > 20) {
		alert("请填写客户简称且长度不能超过20！");
		document.getElementById("vender_code").value = "";
		document.getElementById("vender_code").focus();
		return false;
	}
	if (company_name.length == 0 || company_name.length > 127) {
		alert("请填写公司名称且长度不能超过127！");
		document.getElementById("company_name").value = "";
		document.getElementById("company_name").focus();
		return false;
	}
	if (address.length == 0 || address.length > 255) {
		alert("请填写地址且长度不能超过255！");
		document.getElementById("address").value = "";
		document.getElementById("address").focus();
		return false;
	}
	if (contactor.length == 0 || contactor.length > 255) {
		alert("请填写联系人且长度不能超过255！");
		document.getElementById("contactor").value = "";
		document.getElementById("contactor").focus();
		return false;
	}
	if (mobile.length == 0 || mobile.length > 11) {
		alert("请填写手机且长度不能超过11！");
		document.getElementById("mobile").value = "";
		document.getElementById("mobile").focus();
		return false;
	} else {
		reg = /^1\d{10}$/gi;
		if (!reg.test(mobile)) {
			alert("非法的手机号码！");
			document.getElementById("mobile").value = "";
			document.getElementById("mobile").focus();
			return false;
		}
	}
	if (phone.length != 0) {
		reg = /^1\d{10}$/gi;
		if (!reg.test(phone)) {
			alert("非法的电话号码！");
			document.getElementById("phone").value = "";
			document.getElementById("phone").focus();
			return false;
		}
	}
	if(postcode.length!=0){
		reg=/^\d{6}$/gi;
		if(!reg.test(postcode)){
			alert("非法的邮政编码！");
			document.getElementById("postcode").value = "";
			document.getElementById("postcode").focus();
			return false;
		}
	}
	if (email.length != 0) {
		reg = /^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
		if (!reg.test(email)) {
			alert("电子邮件格式不正确！");
			document.getElementById("email").value = "";
			document.getElementById("email").focus();
			return false;
		}
	}
}
function del(venderId){
	if(confirm("确定删除吗?")){
		window.self.location='venderDelete.action?id='+venderId;
	}
}
function init() {
	hiddenBtn('system/venderEdit.action','edit');
	hiddenBtn('system/venderDelete.action','del');
	hiddenBtn('system/venderAdd.action','add');
}