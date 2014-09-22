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
	var faqs="";
	var check=document.getElementsByName("check");
	var checkHead=document.getElementById("checkHead");
	for(var i=0;i<check.length;i++){
		if(check[i].checked==true){
			on=on+1;
			faqs=faqs+","+check[i].value;
		}else{
			off=off+1;
		}
	}
	id=faqs.substring(1,faqs.length);
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
	window.self.location='faqDelete.action?id='+id;
}

 


function del(id){
	if(confirm("确定删除吗?")){
	   window.location.href='faqDelete.action?id='+id;
		
	}
}
function init() {
	
	hiddenBtn('system/faqDelete.action','del');
	hiddenBtn('system/faqDelete.action','manyDel');
	
}
	