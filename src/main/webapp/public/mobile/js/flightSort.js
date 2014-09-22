function sortPrice(){

	var lis = document.getElementsByName("sortP");
	var vLi = "";
	var temp;

	//将lis放入数组中
	var tArr = new Array();
	for(var r=0;r<lis.length;r++){
		tArr[r] = lis[r];
	}

//var tArr = new Array(4,9,20,1,5,1111,1002,23,5);
	//对数组中li节点按照price排序
	var j;
	for(var i=0;i<tArr.length-1;i++){
		
		//var tableA = tArr[i].childNodes[1].childNodes[1];
		//var priceA = parseInt(tableA.rows[0].cells[0].childNodes[0].nodeValue.substring(1));
		//var priceA = tArr[i];

		for(j=i+1;j<tArr.length;j++){
			//var tableB = tArr[j].childNodes[1].childNodes[1];
			//var priceB = parseInt(tableB.rows[0].cells[0].childNodes[0].nodeValue.substring(1));
			//priceB = tArr[j];
			//if(parseInt(tArr[i].childNodes[1].childNodes[1].rows[0].cells[0].childNodes[0].nodeValue.substring(1)) > parseInt(tArr[j].childNodes[1].childNodes[1].rows[0].cells[0].childNodes[0].nodeValue.substring(1))){
			//if(priceA > priceB){
			if(parseInt(tArr[i].getAttribute("sop"))>parseInt(tArr[j].getAttribute("sop"))){
				temp = tArr[i];
				tArr[i] = tArr[j];
				tArr[j] = temp;
				
			}
		}
	}

	//var newLis = new Array();
	//将排序好的li节点转换成字符串(dom)
	var liContent = "";
	for(var u=0;u<tArr.length;u++){
		liContent +=transElementToString(tArr[u]);
	}

	var ul = document.getElementById("uiIDlist");
	//移除旧的排序列表
	var orgLis = document.getElementsByName("11");
	var ln = orgLis.length;
	for(var t=0;t<ln;t++){
		ul.removeChild(orgLis.item(0));
	}

	//插入新的排序列表
	ul.innerHTML = liContent;
	
}
//转换Elem对象成字符串
function transElementToString(elemObj){
 var returnString = "";
 //如果节点类型不对则返回Null
 if (elemObj.nodeType == 3){
  returnString = elemObj.data+" ";
  return returnString;
 }else if (elemObj.nodeType == 1){
  //节点头部
  returnString = "<"+elemObj.tagName+" "+getEnableAttribute(elemObj,'id')+"> ";
  var child=elemObj.firstChild;
  while(child!=null){
    returnString +=transElementToString(child);
    child = child.nextSibling;
  }
  //节点尾部
  returnString += "</"+elemObj.tagName+"> "
 }
 return returnString;
}

//取得节点有效属性 elemObj:节点对象 filter:属性过滤
//filter 将不希望出现的属性名组成一个字符串,最好每个名之间分隔一下.
function getEnableAttribute(elemObj,filter){
 var returnString = "";
 var test ="";
 var attrs = elemObj.attributes;
 for(var i = 0;i<attrs.length;i++){
  test += attrs[i].name + "='" + attrs[i].value + "' "
  if(attrs[i].value.toLowerCase() != "null" && attrs[i].value.toLowerCase() != "" && attrs[i].value.toLowerCase() != "0" && attrs[i].value!="false" && filter.indexOf(attrs[i].name) == -1){
   returnString += attrs[i].name + "='" + attrs[i].value + "' "
  }
 }
 return returnString;
}

function sortByTime(){

	document.getElementById("ulSortByPrice").style.display = "none";
	document.getElementById("ulSortByTime").style.display = "";

}
function sortByPrice(){

	document.getElementById("ulSortByTime").style.display = "none";
	document.getElementById("ulSortByPrice").style.display = "";

}