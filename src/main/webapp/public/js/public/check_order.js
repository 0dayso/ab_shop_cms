var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}
function check(tag){
	var refundRate = document.getElementById('refundRate_'+tag).value;
	var r = /^\d+$/;
	if(!r.test(refundRate)){
		alert("退票费率只能为正整数且不能为空！");
		document.getElementById('refundRate_'+tag).value = "";
		return false;
	}
	if(isNaN(parseInt(refundRate)) ){
		alert("退票费率只能为数字！");
		document.getElementById('refundRate_'+tag).value = "";
		return false;
	}
	if(parseInt(refundRate) > 100){
		alert("退票费率不能大于100！");
		document.getElementById('refundRate_'+tag).value = "";
		return false;
	}
	var refundCauseType = document.getElementById('refundCauseType_'+tag).value;
	if(refundCauseType == 0){
		alert("请选择退款类型!");
		document.getElementById('refundCauseType_'+tag).focus();
		return false;
	}
	var refundReason = document.getElementById('refundReason_'+tag).value;
	if(refundCauseType == 1){
		if(refundReason == 0){
			alert("请选择退款申请原因!");
			document.getElementById('refundReason_'+tag).focus();
			return false;
		}
	}
	var optState = document.getElementById('optState_'+tag).value;
	if(optState == 0){
		alert("请选择退票处理状态!");
		document.getElementById('optState_'+tag).focus();
		return false;
	}
}

function checkpay(){
	var paySerialNo = document.getElementById('paySerialNo').value;
	var r = /^\d+$/;
	if(!r.test(paySerialNo)){
		alert("支付宝流水号输入有误，请重新输入！");
		document.getElementById('paySerialNo').value = "";
		return false;
	}
}

function checkData(){
	var ticketState = document.getElementsByName('ticketState');
	var ticketNo = document.getElementsByName('ticketNo');
	for(var i = 0;i < ticketState.length;i++){
		if(ticketState[i].value != 8 && ticketState[i].value != 11 && ticketState[i].value != 0 && ticketState[i].value != 1 && ticketState[i].value != 7){
			if(ticketNo[i].value.length == 0 || ticketNo[i].value.replace(/\s/g,'').length==0){
				alert("机票票号不能为空!");
				return false;
			}
		}
	}
	var pnrNo = document.getElementById("pnrNo").value;
	if(pnrNo.length!=0||pnrNo.length.replace(/\s/g,'').length!=0){
		var reg = /^[a-zA-Z0-9]{5,6}$/;
		if(!reg.test(pnrNo)){
			alert("PNR只能是字母或数字，长度只能为5或6！");
			return false;
		}
	}
}

function init(tag){
	hiddenBtn('system/'+tag+'.action',tag);
}

function caOrderInit(){
	var psgIds = document.getElementsByName('paId');
	for(var i = 0;i < psgIds.length;i++){
		var tag = psgIds[i].value;
		hiddenBtn2('system/CArefundAdd.action','refundAdds_'+tag,tag);//退票添加
		hiddenBtn2('system/CArefundEdit.action','refundEdits_'+tag,tag);//退票修改
	}
	hiddenBtn('system/cancleRefund.action','cancleRefund');//取消退票 
	hiddenBtn('system/queryPay.action','queryPay');//支付查询
	hiddenBtn('system/webTicketOut.action','webTicketOut');//网站出票 
	hiddenBtn('system/refundSlip.action','refundSlip');//差错退款
	hiddenBtn('system/refundStateAdd.action','refundStateAdd');//网站退票
	hiddenBtn('system/refundOrder.action','refundOrder');//请求退款
}

function jdOrderInit(){
	var psgIds = document.getElementsByName('paId');
	for(var i = 0;i < psgIds.length;i++){
		var tag = psgIds[i].value;
		hiddenBtn2('system/CArefundAdd.action','refundAdds_'+tag,tag);//退票添加
		hiddenBtn2('system/CArefundEdit.action','refundEdits_'+tag,tag);//退票修改
	}
	hiddenBtn('system/cancleRefund.action','cancleRefund');//取消退票 
	hiddenBtn('system/queryPay.action','queryPay');//支付查询
	hiddenBtn('system/webTicketOut.action','webTicketOut');//网站出票 
	hiddenBtn('system/refundSlip.action','refundSlip');//差错退款
	hiddenBtn('system/refundStateAdd.action','refundStateAdd');//网站退票
	hiddenBtn('system/refundOrder.action','refundOrder');//请求退款
}

function czOrderInit(){
	hiddenBtn('system/queryOrderState.action','queryOrderState');//状态同步
}

function huOrderInit(){
	var psgIds = document.getElementsByName('paId');
	for(var i = 0;i < psgIds.length;i++){
		var tag = psgIds[i].value;
		hiddenBtn2('system/HUrefundAdd.action','refundAdds_'+tag,tag);//退票添加
		hiddenBtn2('system/HUrefundEdit.action','refundEdits_'+tag,tag);//退票修改
	}
	hiddenBtn('system/queryPayHU.action','queryPay');//支付查询
	hiddenBtn('system/webTicketOutHU.action','webTicketOut');//网站出票 
	hiddenBtn('system/refundSlipHU.action','refundSlip');//差错退款
	hiddenBtn('system/applyAndConfirm.action','pay');//支付申请和确认
	hiddenBtn('system/refundTicket.action','refundTicket');//请求退票处理操作
}
function fileInit(){
	hiddenBtn('hnair/report/file_upload.jsp','upLoad');
}
