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
	if (!isNotEmpty(document.getElementById('region'), "地区不能为空！")) {
		return false;
	}
	if (!isNotEmpty(document.getElementById('cityName'), "营业部不能为空！")) {
		return false;
	}
	if (!isNotEmpty(document.getElementById('telNo'), "电话不能为空！")) {
		return false;
	}
	if (!isNotEmpty(document.getElementById('faxNo'), "传真不能为空！")) {
		return false;
	}
	var coordinate=document.getElementById("coordinate").value;
	if(coordinate!=null&&coordinate!=""){
		if(coordinate.indexOf(",")==-1){
			alert("请输入正确的坐标以\",\"分割");
			return false;
		}else if((coordinate.split(",").length>2||coordinate.charAt(coordinate.length-1)==",")){
			alert("请正确输入坐标，经纬度之间用\",\"分割");
			return false;
		}
	}
//	if (!isTel(document.getElementById('telNo').value)) {
//		alert("电话号码格式不正确！");
//		document.getElementById('telNo').focus();
//		return false;
//	}
//	if (!isTel(document.getElementById('faxNo').value)) {
//		alert("传真号码格式不正确！");
//		document.getElementById('faxNo').focus();
//		return false;
//	}
	return true;
}

function init() {
	hiddenBtn('system/businessEdit.action','edit');
	hiddenBtn('system/businessDelete.action','del');
	hiddenBtn('system/businessAdd.action','add');
}

function del(businessId){
	if(confirm("确定删除吗?")){
		window.location.href='businessDelete.action?id='+businessId;
	}
}