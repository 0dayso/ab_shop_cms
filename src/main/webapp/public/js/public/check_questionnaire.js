var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}
function checkBox(){
	var check=document.getElementsByName("check");
	for ( var i = 0; i < check.length; i++) {
		check[i].checked = true;
	}
	checkOn(obj);
}
function clearBox(){
	var check=document.getElementsByName("check");
	for ( var i = 0; i < check.length; i++) {
		check[i].checked = false;
	}
}
var id="";
function checkOn(obj){
	var on=0;
	var off=0;
	var ids="";
	var check=document.getElementsByName("check");
	var checkHead=document.getElementById("checkHead");
	for(var i=0;i<check.length;i++){
		if(check[i].checked==true){
			on=on+1;
			ids=ids+","+check[i].value;
		}else{
			off=off+1;
		}
	}
	id=ids.substring(1,ids.length);
	if(on==check.length){
		checkHead.checked = true;
	}else{
		checkHead.checked = false;
	}
}
function delMessages(){
	if(id==""){
		alert("请选择要删除的信息！");
		return false;
	}
	window.self.location='questionnaireDelete.action?id='+id;
}

 


function del(id){
	if(confirm("确定删除吗?")){
	   window.location.href='questionnaireDelete.action?id='+id;
		
	}
}
function init() {
	
	hiddenBtn('system/questionnaireDelete.action','del');
	hiddenBtn('system/questionnaireDelete.action','manyDel');
	
}
	