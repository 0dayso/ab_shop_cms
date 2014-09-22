var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}
function isNotEmpty(element, message){
	if(element.value.length == 0 || element.value.replace(/\s/g,'').length==0 || element.value == 0){
		alert(message);
		element.focus();
		return false;
	}
	return true;
}
function isMobile(mobile){
	if(/^13\d{9}$/g.test(mobile)||(/^15[0-35-9]\d{8}$/g.test(mobile))||(/^18[05-9]\d{8}$/g.test(mobile))){  
		return true;
	}else{
		return false;
	}
}
function isEmail(eml) {
	 var re=new RegExp("@[\\w]+(\\.[\\w]+)+$");
	 return re.test(eml);
}
function validate(obj){
	var name = document.getElementById('name');
	var roleId = document.getElementById('roleId');
	var login = document.getElementById('login');
	var mobile = document.getElementById("mobile").value;
	var email = document.getElementById("email").value;
	var address = document.getElementById('address').value;
	
	if(!isNotEmpty(name, "姓名不能为空！")){
		return false;
	}else if(name.value.length>30){
		alert("姓名长度不能超过30！");
		document.getElementById("name").value = "";
		document.getElementById("name").focus();
		return false;
	}
	if(obj != 'admin'){
		if(!isNotEmpty(roleId, "请为此员工分配角色权限！")){
			return false;
		}
	}
	if(!isNotEmpty(login, "登录名不能为空！")){
		return false;
	}else if(login.value.length>20){
		alert("登录名长度不能超过20！");
		document.getElementById("login").value = "";
		document.getElementById("login").focus();
		return false;
	}
	if(!flag){
		alert("该登录名已经存在!");
		document.getElementById('login').focus();
		return false;
	}
	if(address.length>100){
		alert("地址长度不能超过100！");
		document.getElementById("address").value = "";
		document.getElementById("address").focus();
		return false;
	}
	var pin = document.getElementById('pin');
	if(pin){
		if(!isNotEmpty(document.getElementById('pin'), "密码不能为空！")){
			return false;
		}
		var pwd = document.getElementById('pin').value;
		if(pwd.replace(/\s/g,'').length < 6 || pwd.length < 6){
			alert("密码长度不能少于6位！");
			return false;
		}
		var re = /^[0-9a-zA-Z]{6,20}$/;
		if(!isNaN(pwd)){
			alert("密码不能全为数字，弱密码!");
			return false;
		} else if(pwd.search(re)=="-1"){
			alert("密码必须是字母数字组合，且最少为6个字符，弱密码!");
			return false;
		}
	}
	if(!(mobile.length==0) && !(mobile.replace(/\s/g,'').length==0)){
		if(!isMobile(mobile)){
			alert("手机号码格式不正确！");
			document.getElementById('mobile').focus();
			return false;
		}
	}
	if(!(email.length==0) && !(email.replace(/\s/g,'').length==0)){
		if(!isEmail(email)){
			alert("邮箱格式不正确！");
			document.getElementById('email').focus();
			return false;
		}
	}
}

function del(employeeId){
	if(confirm("确定删除吗?")){
		window.self.location='employee_delete.action?id='+employeeId;
	}
}

function init() {
	hiddenBtn('system/employee_edit.action','edit');
	hiddenBtn('system/employee_delete.action','del');
	hiddenBtn('system/employee_view.action','add');
}
var flag = true;
function checkLogin(){
	var login = document.getElementById("login").value.replace(/\s/g,'');
	if(login.length>0){
		$.get('employee_isLogin.action?timestamp='+new Date().getTime(),{login:login},function(data){
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
