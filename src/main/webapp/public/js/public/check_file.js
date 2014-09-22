function check(){
	var file = document.getElementById("file").value;
	if(file.length==0){
		alert("请选择文件!");
		return false;
	}
	if(file.substring(file.lastIndexOf("."),file.length)=='.xlsx'){
		alert("请转换为office2003文件再上传！");
		return false;
	}
}
function refund(obj){
	if(confirm("确定退票?")){
		window.self.location = 'refundTicketHU.action?fileName='+obj;
	}
}

function del(obj){
	if(confirm("确定删除?")){
		window.self.location = 'delHU.action?fileName='+obj;
	}
}

function down(obj){
	window.self.location = 'fileDown.action?fileName='+obj;
}

function delFile(obj){
	if(confirm("确定删除?")){
		window.self.location = 'awardDeleteFile.action?fileName='+obj;
	}
}

function create(obj){
	if(confirm("确定生成促销券?")){
		window.self.location = 'awardCreate.action?fileName='+obj;
	}
}

function init(){
	hiddenBtn('system/refundTicketHU.action','ticketRefund');
	hiddenBtn('system/delHU.action','fileDel');
	hiddenBtn('system/fileDown.action','fileDown');
}

function initFile(){
	hiddenBtn('system/awardCreate.action','create');
	hiddenBtn('system/awardDeleteFile.action','delFile');
}