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
var newId="";
function checkOn(obj){
	var on=0;
	var off=0;
	var news="";
	var check=document.getElementsByName("check");
	var checkHead=document.getElementById("checkHead");
	for(var i=0;i<check.length;i++){
		if(check[i].checked==true){
			on=on+1;
			news=news+","+check[i].value;
		}else{
			off=off+1;
		}
	}
	newId=news.substring(1,news.length);
	if(on==check.length){
		checkHead.checked = true;
	}else{
		checkHead.checked = false;
	}
}
function delNews(){
	if(newId==""){
		alert("请选择要删除的信息！");
		return false;
	}
	window.self.location='newsDelete.action?newsId='+newId;
}
function updNews(){
	if(newId==""){
		alert("请选择要处理的信息！");
		return false;
	}
	window.self.location='newsUpdate.action?newsId='+newId;
}
function checkData() {
	var reg = /^\d{1,3}$/;
    var titleName = document.getElementById("titleName").value;
    var type = document.getElementById("type").value;
    var content = document.getElementById("content").value;
    var imgUrl = document.getElementById("imgUrl").value;
    var sort = document.getElementById("sort").value;
    if(titleName == '' || titleName.replace(/\s/g,'').length==0){
        alert("请输入新闻标题!");
        document.getElementById('titleName').focus();
        return false;
    }
    if(titleName.replace(/\s/g,'').length>100){
        alert("新闻标题不得超过100个字符!");
        document.getElementById('titleName').focus();
        return false;
    }
    if(type == 0){
        alert("请选择信息类别!");
        document.getElementById('type').focus();
        return false;
    }
    if(!reg.test(sort)){
    	alert("请输入数字排序序号，1到3位!");
        document.getElementById('sort').focus();
        return false;
    }
    if(imgUrl.replace(/\s/g,'').length>100){
        alert("图片URL不得超过100个字符!");
        document.getElementById('imgUrl').focus();
        return false;
    }
    if(content.replace(/\s/g,'').length == 0){
    	alert("请输入新闻内容!");
    	document.getElementById('content').focus();
        return false;
    }
    if(content.replace(/\s/g,'').length < 10){
    	alert("新闻内容至少输入10个文字!");
    	document.getElementById('content').focus();
        return false;
    }
    if(content.replace(/\s/g,'').length > 1000){
    	alert("您最多可输入1000个文字!");
    	document.getElementById('content').focus();
	    return false;
	}
}
 
function countWord(tag){
	var len = tag.value;
	var lens = len.replace(/\s/g,'').length;
	document.getElementById('contentLength').value = lens;
	if(lens > 1000){
		alert("内容不能超过1000个字符!");
	}
}

function getWord(tag){
	var len = document.getElementById(tag).value.length;
	document.getElementById('contentLength').value = len;
}

function del(newsId){
	if(confirm("确定删除吗?")){
		window.location.href='newsDelete.action?newsId='+newsId;
	}
}
function init() {
	hiddenBtn('system/newsEdit.action','edit');
	hiddenBtn('system/newsDelete.action','del');
	hiddenBtn('system/newsDelete.action','manyDel');
	hiddenBtn('system/newsAdd.action','add');
	hiddenBtn('system/newsQuery.action','detail');
}
	