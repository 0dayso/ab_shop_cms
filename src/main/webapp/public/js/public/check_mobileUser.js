var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}
function isNotEmpty(element, message){
	if(element.value.length == 0 || element.value.replace(/\s/g,'').length==0){
		alert(message);
		element.focus();
		return false;
	}
	return true;
}
function isEmail(eml) {
	 var re=new RegExp("@[\\w]+(\\.[\\w]+)+$");
	 return re.test(eml);
}
function isTel(tel) {
	var reg = /^\d{3}-\d{8}|\d{4}-\d{7}|\d{7}|\d{8}|\d{11}$/;
	return reg.test(tel);
}
function isphone(phone){
	if(/^13\d{9}$/g.test(phone)||(/^15[0-35-9]\d{8}$/g.test(phone))||(/^18[05-9]\d{8}$/g.test(phone))){  
		return true;
	}else{
		return false;
	}
}
function isIdNo(idNo){
	var reg  = /^([1-9]{2}\d{15})(\d|[x,X])$/; 
	return reg.test(idNo);
}
function validate(){
	if(!isNotEmpty(document.getElementById('name'), "姓名不能为空！")){
		return false;
	}
	if(!isNotEmpty(document.getElementById('phone'), "手机号码不能为空！")){
		return false;
	}
	if(!isNotEmpty(document.getElementById('idNo'), "证件号码不能为空！")){
		return false;
	}
	var telNo = document.getElementById('mobile').value;
	var phone = document.getElementById("phone").value;
	var idNo = document.getElementById("idNo").value;
	var email = document.getElementById("email").value;
	if(!(phone.length==0) && !(phone.replace(/\s/g,'').length==0)){
		if(!isphone(phone)){
			alert("手机号码格式不正确！");
			document.getElementById('phone').focus();
			return false;
		}
	}
	if(!(email.length==0) && !(email.replace(/\s/g,'').length==0)){
		if(!isEmail(email)){
			alert("电子邮件格式不正确！");
			document.getElementById('email').focus();
			return false;
		}
	}
	if(!(idNo.length==0) && !(idNo.replace(/\s/g,'').length==0)){
		if(document.getElementById("idType").value=="NI"){
			if(!isIdNo(idNo)){
				alert("身份证号码格式不正确！");
				document.getElementById('idNo').focus();
				return false;
			}
		}
	}
	if(!(telNo.length==0) && !(telNo.replace(/\s/g,'').length==0)){
		if(!isTel(telNo) && !isphone(telNo)){
			alert("电话号码格式不正确！");
			document.getElementById('mobile').focus();
			return false;
		}
	}
}

function init() {
	hiddenBtn('system/mobileUserEdit.action','edit');
	hiddenBtn('system/mobileUserPwd.action','del');
}
