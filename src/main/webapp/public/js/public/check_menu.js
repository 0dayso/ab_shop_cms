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
	var venderId=document.getElementById("venderId").value;
	
	if(name!=null&&name!='') name=name.trim();
	if(code!=null&&code!='') code=code.trim();
	
	if(name.length==0||name.length>30){
		alert("请填写菜单名称且长度不大于30！");
		addMenu.name.focus();
		return false;
	}
	if(code.length==0||code.length>20){
		alert("请填写菜单代码且长度不大于20！");
		addMenu.code.focus();
		return false;
	}
	if(venderId==''){
		alert("请选择供应商！");
		addMenu.venderId.focus();
		return false;
	}
}

function del(id,cuisinesId){
	if(confirm("确定删除吗?")){
		window.self.location='menuDelete.action?id='+id+'&cuisinesId='+cuisinesId;
	}
}
function init() {
	hiddenBtn('system/menuEdit.action','edit');
	hiddenBtn('system/menuDelete.action','del');
	hiddenBtn('system/menuAdd.action','add');
}