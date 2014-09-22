String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
}
function send(event,pageCount){
	if(event.keyCode==13){
		checkPageIndex(pageCount);
	}
}
function checkPageIndex(pageCount) {
	var pageIndex = document.getElementById("pageIndex").value;
	
	if (pageIndex != null && pageIndex != ''){
		pageIndex = pageIndex.trim();
		reg = /^[-]?\d*$/gi;
		if (!reg.test(pageIndex)) {
			alert("页码只能是合法的数字!");
			document.getElementById("pageIndex").value = 1;
			return false;
		}
		if (pageIndex <= 0 || pageIndex > pageCount) {
			alert("页码范围只能在1~" + pageCount + "之间!");
			document.getElementById("pageIndex").value = 1;
			return false;
		}
	} else {
		document.getElementById("pageIndex").value = 1;
		return false;
	}
}