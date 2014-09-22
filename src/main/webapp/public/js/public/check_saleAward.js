
function del(id){
	if(confirm("确定删除吗?")){
		window.self.location='saleAwardDelete.action?id='+id;
	}
}

function init() {
	hiddenBtn('system/saleAwardEdit.action','edit');
	hiddenBtn('system/saleAwardDelete.action','del');
	hiddenBtn('system/saleAward.action','add');
}

function delAward(id){
	if(confirm("确定删除吗?")){
		window.self.location='purchaseAwardDelete.action?id='+id;
	}
}

function initAward() {
	hiddenBtn('system/purchaseAwardEdit.action','edit');
	hiddenBtn('system/purchaseAwardDelete.action','del');
	hiddenBtn('system/purchaseAwardAdd.action','add');
}

function check(){
	//获得起始日期，并且变成格式yyyymmdd
	var beginDate = document.getElementById('beginDate').value; 
	var begin = beginDate.replace(/-/g,"");  
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var endDate = document.getElementById('endDate').value; 
   	var end = endDate.replace(/-/g,""); 
  //获得起始日期，并且变成格式yyyymmdd
	var invalidBeginDate = document.getElementById('invalidBeginDate').value; 
	var invalidBegin = invalidBeginDate.replace(/-/g,"");  
	   
	//获得截止日期，并且变成格式yyyymmdd
   	var invalidEndDate = document.getElementById('invalidEndDate').value; 
   	var invalidEnd = invalidEndDate.replace(/-/g,""); 
	
	//如果起始日期大于截止日期，则跳出提示
 	if(begin!=null && end!=null && parseInt(begin)>parseInt(end)){     
       	alert("生成起始日期不能大于结束日期!");
       	document.getElementById('beginDate').focus();
       	return false;
 	}
	//如果起始日期大于截止日期，则跳出提示
 	if(invalidBegin!=null && invalidEnd!=null && parseInt(invalidBegin)>parseInt(invalidEnd)){     
       	alert("失效起始日期不能大于结束日期!");
       	document.getElementById('invalidBeginDate').focus();
       	return false;
 	}
}