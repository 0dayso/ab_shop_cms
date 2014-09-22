var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

String.prototype.trim = function() {
	return this.replace(/^(|[\s])+|(|[\s])+$/g, "");
}



function del(paramId){
	if(confirm("确定要删除吗?")){
		javaScript:window.self.location='parameter_delete.action?id='+paramId;
	}
}
function init() {
	hiddenBtn('system/parameterEdit.action','edit');
	hiddenBtn('system/parameterDelete.action','del');
	hiddenBtn('system/parameterAdd.action','add');
}