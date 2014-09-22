function createSelect(selectStr, value,id) {
    var htmlData = '<div id="select'+id+'" style="border:1px solid #CCCCCC;border-top:0px;margin:-10px 10px 10px 10px;width:auto;background-color:#FFFFFF;"><table>';
	id = '\''+id+'\'';
    var data = selectStr.split(';');
    for ( var i = 0; i < data.length; i++) {
	var s = data[i].split(',');
	var val ='\''+s[0]+'\'';
	var name = '\''+s[1]+'\'';
	if(value == s[1]){
		htmlData += '<tr style="height: 36px;background-color: #B9DBFF;"><td onclick="getValue('+id+','+name+','+val+')">' + s[1] + '</td></tr>';
	}else{
		htmlData += '<tr style="height: 36px;"><td onclick="getValue('+id+','+name+','+val+')">' + s[1] + '</td></tr>';
		}
    }
    htmlData += '<table></div>';
    return htmlData;
}

function getValue(id,valueName,value){
	$(this).css("background-color","#B9DBFF");
	$("#"+id).attr("value",valueName);
	$("#"+id+"Val").attr("value",value);
	$("#select"+id).remove();
}