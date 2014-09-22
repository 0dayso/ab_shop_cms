var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

/* 判断元素值是否为空 element:element in the page message:error message */
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
	var code = document.getElementById('code');
	var shortCut = document.getElementById('shortCut');
	var name = document.getElementById('name');
	var city = document.getElementById('city'); 
	var coordinate=document.getElementById("coordinate").value;
	var enName = document.getElementById("enName");
	var remark = document.getElementById("remark");
	
	if (!isNotEmpty(code, "机场代码不能为空！")) {
		return false;
	}else if(code.value.length>20){
		alert("机场代码长度不能超过20个字符!");
		document.getElementById('code').value="";
	    document.getElementById('code').focus();
		return false;
	}
	if (!isNotEmpty(shortCut, "拼音简码不能为空！")) {
		return false;
	}else if(shortCut.value.length>20){
		alert("拼音简码长度不能超过20个字符!");
		document.getElementById('shortCut').value="";
	    document.getElementById('shortCut').focus();
		return false;
	}
	if (!isNotEmpty(name, "机场中文名不能为空！")) {
		return false;
	}else if(name.value.length>30){
		alert("机场中文名长度不能超过30个字符!");
		document.getElementById('name').value="";
	    document.getElementById('name').focus();
		return false;
	}
	if (!isNotEmpty(city, "城市中文名不能为空！")) {
		return false;
	}else if(city.value.length>30){
		alert("城市中文名长度不能超过30个字符!");
		document.getElementById('city').value="";
	    document.getElementById('city').focus();
		return false;
	}
	if(enName.value.length>30){
		alert("机场英文名长度不能超过30个字符!");
		document.getElementById('enName').value="";
	    document.getElementById('enName').focus();
		return false;
	}
	if(remark.value.length>150){
		alert("备注长度不能超过150个字符!");
		document.getElementById('remark').value="";
	    document.getElementById('remark').focus();
		return false;
	}
	if(coordinate!=null&&coordinate!=""){
		if(coordinate.indexOf(",")==-1){
			alert("请输入正确的坐标以\",\"分割");
			return false;
		}else if((coordinate.split(",").length>2||coordinate.charAt(coordinate.length-1)==",")){
			alert("请正确输入坐标，经纬度之间用\",\"分割");
			return false;
		}
	}
	return true;
}

function change() {
	var flag = document.getElementById('flag').value;
	if (flag == 1) {
		document.getElementById("ia").style.display = "";
		$("#level").hide();
		//国际机场隐藏坐标列
		try{
			document.getElementById("incoordinate").style.display="none";
			document.getElementById("in2coordinate").style.display="none";
		}catch(e){}
	} else {
		document.getElementById("ia").style.display = "none";
		$("#level").show();
		//国内机场显示坐标列
		try{
			document.getElementById("incoordinate").style.display="";
			document.getElementById("in2coordinate").style.display="";
		}catch(e){}
	}
}

function init() {
	change();
	hiddenBtn('system/airportEdit.action','edit');
	hiddenBtn('system/airportDelete.action','del');
	hiddenBtn('system/airportAdd.action','add');
}

function del(airportId,flag){
	if(confirm("确定删除吗?")){
		window.self.location='airportDelete.action?id='+airportId+'&flag='+flag;
	}
}