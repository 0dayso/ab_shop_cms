var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

String.prototype.trim = function() {
	return this.replace(/^(|[\s])+|(|[\s])+$/g, "");
}
function check(){
	var name=document.getElementById("name").value;
	var code=document.getElementById("code").value;
	var venderId=document.getElementById("vender").value;
	
	if(name!=null&&name!='') name=name.trim();
	if(code!=null&&code!='') code=code.trim();
	
	if(name.length==0||name.length>30){
		alert("请填写区域名称且长度不大于30！");
		document.getElementById("name").value="";
		document.getElementById("name").focus();
		return false;
	}
	if(code.length==0||code.length>20){
		alert("请填写区域代码且长度不大于20！");
		document.getElementById("code").value="";
		document.getElementById("code").focus();
		return false;
	}
	if(venderId==''){
		alert("请选择供应商！");
		document.getElementById("vender").value="";
		document.getElementById("vender").focus();
		return false;
	}
}
function del(id,cityId){
	if(confirm("确定删除吗?")){
		window.self.location='areaDelete.action?id='+id+'&cityId='+cityId;
	}
}
function init() {
	hiddenBtn('system/areaEdit.action','edit');
	hiddenBtn('system/areaDelete.action','del');
	hiddenBtn('system/areaAdd.action','add');
}