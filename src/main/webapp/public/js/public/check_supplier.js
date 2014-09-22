var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

//判断是否为空
function isNotEmpty(element, message) {
	if (element.value.length == 0
			|| element.value.replace(/\s/g, '').length == 0) {
		alert(message);
		element.focus();
		return false;
	}
	return true;
}
//判断是否为电话号码
//function isTel(tel) {
//	var reg = /^\d[-]*[\|]*$/;
//	return reg.test(tel);
//}

function validate() {
	if (!isNotEmpty(document.getElementById('simpleName'), "供应商简称不能为空！")) {
		return false;
	}
	if (document.getElementById('productId').value == 0) {
		alert("请选择供应商产品！");
		return false;
	}
	if (!isNotEmpty(document.getElementById('rebatesRate'), "返点利率不能为空！")) {
		return false;
	}
	var s = /^[.\d]*[.][.\d]+|\d+$/;
	if(!s.test(document.getElementById('rebatesRate').value)){
		alert("输入的返点利率不合规范！");
		document.getElementById('rebatesRate').value = "";
		return false;
	}
	return true;
}

function init() {
	hiddenBtn('system/supplierEdit.action','edit');
	hiddenBtn('system/supplierDelete.action','del');
	hiddenBtn('system/supplierAdd.action','add');
}

function del(id){
	if(confirm("确定删除吗?")){
		window.location.href='supplierDelete.action?id='+id;
	}
}