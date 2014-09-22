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

function getDate(){
  	var myDate=new Date();
  	var y0,m0,d0;
  	y0 = myDate.getFullYear();
  	m0 = myDate.getMonth();
  	d0 = myDate.getDate();
  	myDate.setFullYear(y0,m0,d0);
  	var y,m,d,s;
  	s=myDate.getFullYear().toString(10)+"-";
  	m=myDate.getMonth()+1;
  	s+=(m>9?"":"0")+m;
  	d=myDate.getDate();
  	s+=(d>9?"":"0")+d;
	//myDate.setDate(myDate.getDate() + 1);
	myDate.setDate(myDate.getDate());
	var y1,m1,d1,s1;
	s1=myDate.getFullYear().toString(10);
  	m1=myDate.getMonth()+1;
  	s1+=(m1>9?"":"0")+m1;
  	d1=myDate.getDate();
  	s1+=(d1>9?"":"0")+d1;
  	if(document.getElementById("departDate").value.length == 0){
  		document.getElementById("departDate").value = s1;
  	}
}

/*
function display(){
	var form=document.flightSearch;
		if(form.sort[0].checked){
 		time.style.display="block";
 		price.style.display="none";
 	}else{
 		price.style.display="block";
 		time.style.display="none";
	}
}
*/
/*
function check(){
	alert("123");
	var radio = document.getElementById("sortRule");
	alert(radio.length);
	checked = false;
	for(var i = 0; i < radio.length; i++){
		if(radio[i].checked){
			checked=true;
			return true;
		}
	}
	if (!checked){
		alert('请选择查询规则!');
		return false;
	}
}
*/



