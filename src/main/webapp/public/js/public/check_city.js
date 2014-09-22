var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

String.prototype.trim = function() {
	return this.replace(/^(|[\s])+|(|[\s])+$/g, "");
}
function check(){
	var name=document.getElementById("name").value;
	var pinyin=document.getElementById("pinyin").value;
	var code=document.getElementById("code").value;
	//var venderId=document.getElementById("vender").value;
	
	if(name!=null&&name!='') name=name.trim();
	if(pinyin!=null&&pinyin!='') pinyin=pinyin.trim();
	if(code!=null&&code!='') code=code.trim();
	
	if(name.length==0||name.length>30){
		alert("请填写城市名称且长度不大于30！");
		document.getElementById("name").value="";
		document.getElementById("name").focus();
		return false;
	}
	if(pinyin.length==0||pinyin.length>127){
		alert("请填写城市拼音且长度不大于127！");
		document.getElementById("pinyin").value="";
		document.getElementById("pinyin").focus();
		return false;
	}
	if(code.length==0||code.length>20){
		alert("请填写城市代码且长度不大于20！");
		document.getElementById("code").value="";
		document.getElementById("code").focus();
		return false;
	}
	/*if(venderId==''){
		alert("请选择供应商！");
		document.getElementById("vender").value="";
		document.getElementById("vender").focus();
		return false;
	}*/
}
function del(cityId){
	if(confirm("确定删除吗?")){
		window.self.location='cityDelete.action?id='+cityId;
	}
}
function init() {
	hiddenBtn('system/cityEdit.action','edit');
	hiddenBtn('system/cityDelete.action','del');
	hiddenBtn('system/cityAdd.action','add');
}