function del(productFunctionId){
	if(confirm("确定要删除吗！")){
		javaScript:window.self.location='productFunctionDelete.action?id='+productFunctionId;
	}
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

function validate(){
	var productId = document.getElementById("productId").value;
	var isUse = document.getElementById("isUse").value;
	//alert(productId)
	if(productId == 0){
		alert("产品名称不能为空!");
		document.getElementById("productId").focus();
		return false;
	}
	if (!isNotEmpty(document.getElementById('trancode'), "交易代码不能为空！")) {
		return false;
	}
	if (!isNotEmpty(document.getElementById('operationName'), "交易名称不能为空！")) {
		return false;
	}
	if(isUse == -1){
		alert("请选择是否可用!");
		document.getElementById("isUse").focus();
		return false;
	}
}

function init() {
	hiddenBtn('system/productFunctionEdit.action','edit');
	hiddenBtn('system/productFunctionDelete.action','del');
	hiddenBtn('system/productFunctionAdd.action','add');
}