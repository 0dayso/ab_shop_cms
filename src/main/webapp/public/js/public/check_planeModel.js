var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

String.prototype.trim = function() {
	return this.replace(/^(|[\s])+|(|[\s])+$/g, "");
}
function check_plane() {
	var code = document.getElementById("code").value;
	var seats = document.getElementById("seats").value;
	var manufacturer_cn = document.getElementById("manufacturer_cn").value;
	var manufacturer_en = document.getElementById("manufacturer_en").value;
	var model_cn = document.getElementById("model_cn").value;
	var model_en = document.getElementById("model_en").value;
	
	if (code != '' && code != null)
		code = code.trim();
	if (seats != '' && seats != null)
		seats = seats.trim();

	if (code.length == 0 || code.length > 20) {
		alert("请填写飞机代码且长度不能超过20！");
		document.getElementById("code").value = "";
		document.getElementById("code").focus();
		return false;
	}
	if (seats.length == 0 || seats.length > 20) {
		alert("请填写座位且长度不能超过20！");
		document.getElementById("seats").value = "";
		document.getElementById("seats").focus();
		return false;
	}
	if (manufacturer_cn.length > 80) {
		alert("请填写制造商(中文)且长度不能超过80！");
		document.getElementById("manufacturer_cn").value = "";
		document.getElementById("manufacturer_cn").focus();
		return false;
	}
	if (manufacturer_en.length > 80) {
		alert("请填写制造商(英文)且长度不能超过80！");
		document.getElementById("manufacturer_en").value = "";
		document.getElementById("manufacturer_en").focus();
		return false;
	}
	if (model_cn.length > 40) {
		alert("请填写类型(中文)且长度不能超过40！");
		document.getElementById("model_cn").value = "";
		document.getElementById("model_cn").focus();
		return false;
	}
	if (model_en.length > 40) {
		alert("请填写类型(英文)且长度不能超过40！");
		document.getElementById("model_en").value = "";
		document.getElementById("model_en").focus();
		return false;
	}
}

function del(planeModelId){
	if(confirm("确定要删除吗！")){
		javaScript:window.self.location='planeModelDelete.action?id='+planeModelId;
	}
}

function init() {
	hiddenBtn('system/planeModelEdit.action','edit');
	hiddenBtn('system/planeModelDelete.action','del');
	hiddenBtn('system/planeModelAdd.action','add');
}