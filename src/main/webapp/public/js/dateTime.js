function getDate(){
  var d,s,t;
  d=new Date();
  s=d.getFullYear().toString(10)+"-";
  t=d.getMonth()+1;
  s+=(t>9?"":"0")+t+"-";
  t=d.getDate();
  s+=(t>9?"":"0")+t;
  var dateTime = document.getElementById("dateTime").value;
  if(dateTime == null || dateTime == ""){
	  document.getElementById("dateTime").value = s;
  }
}

function getTowDate(){
  	var myDate=new Date();
  	var y0,m0,d0;
  	y0 = myDate.getFullYear();
  	m0 = myDate.getMonth();
  	d0 = myDate.getDate();
  	myDate.setFullYear(y0,m0,d0);
  	var y,m,d,s;
  	s=myDate.getFullYear().toString(10)+"-";
  	m=myDate.getMonth()+1;
  	s+=(m>9?"":"0")+m+"-";
  	d=myDate.getDate();
  	s+=(d>9?"":"0")+d;
	
	myDate.setDate(myDate.getDate() - 10);
	var y1,m1,d1,s1;
	s1=myDate.getFullYear().toString(10)+"-";
  	m1=myDate.getMonth()+1;
  	s1+=(m1>9?"":"0")+m1+"-";
  	d1=myDate.getDate();
  	s1+=(d1>9?"":"0")+d1;
  	
  	var beginDate = document.getElementById("beginDate").value;
	var endDate = document.getElementById("endDate").value;
	if(beginDate == null || beginDate == ""){
		document.getElementById("beginDate").value = s;
	}
	if(endDate == null || endDate == ""){
		document.getElementById("endDate").value = s;
	}
}

function getTowDates(){
  	var myDate=new Date();
  	var y0,m0,d0;
  	y0 = myDate.getFullYear();
  	m0 = myDate.getMonth();
  	d0 = myDate.getDate();
  	myDate.setFullYear(y0,m0,d0);
  	var y,m,d,s;
  	s=myDate.getFullYear().toString(10)+"-";
  	m=myDate.getMonth()+1;
  	s+=(m>9?"":"0")+m+"-";
  	d=myDate.getDate();
  	s+=(d>9?"":"0")+d;
  	s+= " 23:59:59"
	
	myDate.setDate(myDate.getDate() - 10);
	var y1,m1,d1,s1;
	s1=myDate.getFullYear().toString(10)+"-";
  	m1=myDate.getMonth()+1;
  	s1+=(m1>9?"":"0")+m1+"-";
  	d1=myDate.getDate();
  	s1+=(d1>9?"":"0")+d1;
  	s1+= " 00:00:00"
  	
  	var beginDate = document.getElementById("beginDate").value;
	var endDate = document.getElementById("endDate").value;
	if(beginDate == null || beginDate == ""){
		document.getElementById("beginDate").value = s1;
	}
	if(endDate == null || endDate == ""){
		document.getElementById("endDate").value = s;
	}
}


function getMonth(){
	var d,s,t;
	d=new Date();
	s=d.getFullYear().toString(10)+"-";
	t=d.getMonth()+1;
	var dateTime = document.getElementById("dateTime").value;
	if(dateTime == null || dateTime == ""){
		document.getElementById("dateTime").value = s+""+t;
	}
}

function checkTime(){
	//获得起始日期，并且变成格式yyyymmdd
	var beginDate = document.getElementById('beginDate').value; 
	var begin = beginDate.replace(/-/g,"");  
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var endDate = document.getElementById('endDate').value; 
   	var end = endDate.replace(/-/g,""); 
	
	if(begin.length == 0){
		alert('起始日期不能为空');
		document.getElementById('beginDate').focus();
		return false;
	}
	if(end.length == 0){
		alert('结束日期不能为空');
		document.getElementById('endDate').focus();
		return false;
	}
	   //如果起始日期大于截止日期，则跳出提示
 	if(begin!=null && end!=null && parseInt(begin)>parseInt(end)){     
       	alert("起始日期不能大于结束日期!");
       	document.getElementById('beginDate').focus();
       	return false;
 	}
 	return true;
}

function validateTime(){
	//获得起始日期，并且变成格式yyyymmdd
	var beginDate = document.getElementById('beginDate').value; 
	var begin = beginDate.replace(/-/g,"").replace(/:/g,"").replace(/(^\s*)|(\s*$)/g, "");    
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var endDate = document.getElementById('endDate').value; 
   	var end = endDate.replace(/-/g,"").replace(/:/g,"").replace(/(^\s*)|(\s*$)/g, "");    
	
//	if(begin.length == 0){
//		alert('起始日期不能为空');
//		document.getElementById('beginDate').focus();
//		return false;
//	}
//	if(end.length == 0){
//		alert('结束日期不能为空');
//		document.getElementById('endDate').focus();
//		return false;
//	}
	   //如果起始日期大于截止日期，则跳出提示
 	if(begin!=null && end!=null && parseInt(begin)>parseInt(end)){     
       	alert("起始日期不能大于结束日期!");
       	document.getElementById('beginDate').focus();
       	return false;
 	}
 	var customer = document.getElementById('customer').value;
 	if(customer == 0){
 		alert("请你选择所属客户！");
		document.getElementById('customer').focus();
		return false;
 	}
 	return true;
}
function validateTimes(){
	//获得起始日期，并且变成格式yyyymmdd
	var beginDate = document.getElementById('beginDate').value; 
	var begin = beginDate.replace(/-/g,"").replace(/:/g,"").replace(/(^\s*)|(\s*$)/g, "");    
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var endDate = document.getElementById('endDate').value; 
   	var end = endDate.replace(/-/g,"").replace(/:/g,"").replace(/(^\s*)|(\s*$)/g, "");    
	
//	if(begin.length == 0){
//		alert('起始日期不能为空');
//		document.getElementById('beginDate').focus();
//		return false;
//	}
//	if(end.length == 0){
//		alert('结束日期不能为空');
//		document.getElementById('endDate').focus();
//		return false;
//	}
	   //如果起始日期大于截止日期，则跳出提示
 	if(begin!=null && end!=null && parseInt(begin)>parseInt(end)){     
       	alert("起始日期不能大于结束日期!");
       	document.getElementById('beginDate').focus();
       	return false;
 	}

 	return true;
}

function getSelectedText(name){
	var obj=document.getElementById(name);
	for(i=0;i<obj.length;i++){
	   if(obj[i].selected==true){
	    return obj[i].innerHTML;
	   }
	}
}

function checkTimes(){
	//获得起始日期，并且变成格式yyyymmdd
	var beginDate = document.getElementById('beginDate').value; 
	var begin = beginDate.replace(/-/g,"").replace(/:/g,"").replace(/(^\s*)|(\s*$)/g, "");   
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var endDate = document.getElementById('endDate').value; 
   	var end = endDate.replace(/-/g,"").replace(/:/g,"").replace(/(^\s*)|(\s*$)/g, ""); 
   	
   	var bookStartTime = document.getElementById('bookStartTime').value; 
	var bookStart = beginDate.replace(/-/g,"").replace(/:/g,"").replace(/(^\s*)|(\s*$)/g, "");   
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var bookEndTime = document.getElementById('bookEndTime').value; 
   	var bookEnd = endDate.replace(/-/g,"").replace(/:/g,"").replace(/(^\s*)|(\s*$)/g, ""); 
	
	if(begin.length == 0){
		alert('支付起始日期不能为空');
		document.getElementById('beginDate').focus();
		return false;
	}
	if(end.length == 0){
		alert('支付结束日期不能为空');
		document.getElementById('endDate').focus();
		return false;
	}
	   //如果起始日期大于截止日期，则跳出提示
 	if(begin!=null && end!=null && parseInt(begin)>parseInt(end)){     
       	alert("支付起始日期不能大于结束日期!");
       	document.getElementById('beginDate').focus();
       	return false;
 	}
 	if(bookStart!=null && bookEnd!=null && parseInt(bookStart)>parseInt(bookEnd)){     
       	alert("预订起始日期不能大于结束日期!");
       	document.getElementById('beginDate').focus();
       	return false;
 	}
 	var customer = document.getElementById('customer').value;
 	if(customer == 0){
 		alert("请你选择所属客户！");
		document.getElementById('customer').focus();
		return false;
 	}
 	var file = document.getElementById('file').value; 
	if(file.length == 0 || file.replace(/\s/g,'').length == 0){
		alert("请你上传需要的对帐单！");
		document.getElementById('file').focus();
		return false;
	}
	var customesName = getSelectedText('customer');
	var f = document.getElementById('file').value;
	var s = f.substring(f.length - 3,f.length);
	if(customesName == "北京银行" && s != "xml"){
		alert("北京银行对帐单上传格式错误！");
		document.getElementById('file').focus();
		return false;
	}
	if(customesName == "广东发展银行" && s != "txt"){
		alert("广东发展银行对帐单上传格式错误！");
		document.getElementById('file').focus();
		return false;
	}
 	return true;
}

function checkTime2(){
	var dateTime = document.getElementById("dateTime").value;
   	var time = dateTime.replace(/-/g,""); 
   	if(time.length == 0){
		alert('查询日期不能为空');
		document.getElementById('dateTime').focus();
		return false;
	}
   	return true;
}

function checkTimes2(){
	var dateTime = document.getElementById("dateTime").value;
   	var time = dateTime.replace(/-/g,""); 
   	if(time.length == 0){
		alert('查询日期不能为空');
		document.getElementById('dateTime').focus();
		return false;
	}
   	var customer = document.getElementById('customer').value;
 	if(customer == 0){
 		alert("请你选择所属客户！");
		document.getElementById('customer').focus();
		return false;
 	}
 	var file = document.getElementById('file').value; 
	if(file.length == 0 || file.replace(/\s/g,'').length == 0){
		alert("请你上传需要的对帐单！");
		document.getElementById('file').focus();
		return false;
	}
 	return true;
}

function checkTime3() {
	//获得起始日期，并且变成格式yyyymmdd
	var beginDate = document.getElementById('beginDate').value; 
	var begin = beginDate.replace(/-/g,"");  
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var endDate = document.getElementById('endDate').value; 
   	var end = endDate.replace(/-/g,""); 
    //如果起始日期大于截止日期，则跳出提示
 	if(begin!=null && end!=null && parseInt(begin)>parseInt(end)){     
       	alert("起始日期不能大于结束日期!");
       	document.getElementById('beginDate').focus();
       	return false;
 	}
}

function checkTime4() {
	//获得起始日期，并且变成格式yyyymmdd
	var beginDate = document.getElementById('beginDate').value; 
	var begin = beginDate.replace(/-/g,"").replace(/:/g,"").replace(/\s/g,"");  
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var endDate = document.getElementById('endDate').value; 
   	var end = endDate.replace(/-/g,"").replace(/:/g,"").replace(/\s/g,""); 
    //如果起始日期大于截止日期，则跳出提示
 	if(begin!=null && end!=null && parseInt(begin)>parseInt(end)){     
       	alert("起始日期不能大于结束日期!");
       	document.getElementById('beginDate').focus();
       	return false;
 	}
}

function checkMonth(){
	if (!isNotEmpty(document.getElementById('month'), "结算月份不能为空！")) {
		return false;
	}
}
