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
	var codeClass = document.getElementById('codeClass');
	var code = document.getElementById('code');
	var name = document.getElementById('name');
	var shortCut = document.getElementById('shortCut').value;
	var remark = document.getElementById('remark').value;
	
	if (!isNotEmpty(codeClass, "代码类类别名不能为空！")) {
		return false;
	}else if(codeClass.value.length>30){
		alert("代码类类别名长度不能大于30!");
		document.getElementById('codeClass').value="";
		document.getElementById('codeClass').focus();
		return false;
	}
	if (!isNotEmpty(code, "代码不能为空！")) {
		return false;
	}else if(code.value.length>20){
		alert("代码长度不能大于20!");
		document.getElementById('code').value="";
		document.getElementById('code').focus();
		return false;
	}
	if (!isNotEmpty(name, "代码名称不能为空！")) {
		return false;
	}else if(name.value.length>50){
		alert("代码名称长度不能大于50!");
		document.getElementById('name').value="";
		document.getElementById('name').focus();
		return false;
	}
	if(shortCut.length>20){
		alert("拼音简码长度不能大于20!");
		document.getElementById('shortCut').value="";
		document.getElementById('shortCut').focus();
		return false;
	}
	if(remark.length>150){
		alert("备注长度不能大于150!");
		document.getElementById('remark').value="";
		document.getElementById('remark').focus();
		return false;
	}
	return true;
}
function del(codeId){
	if(confirm("确定删除吗?")){
		window.self.location='codeDelete.action?id='+codeId;
	}
}

function init() {
	hiddenBtn('system/codeEdit.action','edit');
	hiddenBtn('system/codeDelete.action','del');
	hiddenBtn('system/codeAdd.action','add');
}