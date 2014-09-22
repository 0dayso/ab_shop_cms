
function getCityName(codeDiv,name,code,xmlstr){
	if(!$("#air"+name).html())
	$("#"+codeDiv).after(getAirport(xmlstr,name,code));
}

function getCode(name,code,nameVal,codeVal){
	$("#"+name).attr("value",nameVal);
	$("#"+code).attr("value",codeVal);
	$('#air'+name).remove();
}

function createTd(name,code,item){
	var codeVal  = '\''+item.attr("code")+'\'';
	var nameVal  = '\''+item.attr("name").substring(1)+'\'';
	return '<td width="17%" style="cursor: pointer;font: bold 16px arial;height: 36px;text-align: center;" onclick="getCode('+name+','+code+','+nameVal+','+codeVal+')">'+item.attr("name").substring(1)+'</td>';
}

function getAirport(xmlstr,name,code){
	var divName = name;
	name = '\''+name+'\'';
	code = '\''+code+'\'';
	var xmldom=null
    if(navigator.userAgent.toLowerCase().indexOf("msie")!=-1){
        xmldom=new ActiveXObject("Microsoft.XMLDOM");
        xmldom.loadXML(xmlstr);
    }else{
        xmldom=new DOMParser().parseFromString(xmlstr,"text/xml");
    }
	var htmlData ='<div style="100px;margin:-8px 10px 10px 10px;border: 1px solid #C5D9E8;" id="air'+divName+'">' +
	'<table>' +
	'<tr style="background-color: #B9DBFF;border: 1px solid #C5D9E8;cursor: pointer;font:16px arial;height: 36px;text-align: center;">' +
	'<td onclick="hideCityText(0)" id="td0" width="17%" style="font-size:20px;background-color:#FFFFFF;">热门</td><td onclick="hideCityText(1)" id="td1" width="17%">A-F</td><td onclick="hideCityText(2)" id="td2" width="17%">G-J</td><td onclick="hideCityText(3)" id="td3" width="17%">K-N</td><td onclick="hideCityText(4)" id="td4" width="17%">P-W</td><td onclick="hideCityText(5)" id="td5" width="17%">X-Z</td></tr></table>';
	var aHtml = '<div id="city1" style="display:none;"><table>';
	var bHtml = '<div id="city2" style="display:none;"><table>';
	var cHtml = '<div id="city3" style="display:none;"><table>';
	var dHtml = '<div id="city4" style="display:none;"><table>';
	var eHtml = '<div id="city5" style="display:none;"><table>';
	var html = '</table></div>';
	var a=1,b=1,c=1,d=1,e = 1;
	$(xmldom).find("airports").find("otherAirports").find("item").each(function(){
		var item = $(this);
		if("ABCDEF".indexOf(item.attr("name").substring(0,1)) != -1){
			if(a % 6 == 1)
				aHtml += '<tr align="center">';
			aHtml += createTd(name,code,item);
			if(a % 6 == 0)
				aHtml +="</tr>";
			a++;
		}
		if("GHIJ".indexOf(item.attr("name").substring(0,1)) != -1){
			if(b % 6 == 1)
				bHtml += '<tr align="center">';
			bHtml += createTd(name,code,item);
			if(b % 6 == 0)
				bHtml +="</tr>";
			b++;
		}
		if("KLMN".indexOf(item.attr("name").substring(0,1)) != -1){
			if(c % 6 == 1)
				cHtml += '<tr align="center">';
			cHtml += createTd(name,code,item);
			if(c % 6 == 0)
				cHtml +="</tr>";
			c++;
		}
		if("OPQRSTUVW".indexOf(item.attr("name").substring(0,1)) != -1){
			if(d % 6 == 1)
				dHtml += '<tr align="center">';
			dHtml += createTd(name,code,item);
			if(d % 6 == 0)
				bHtml +="</tr>";
			d++;
		}
		if("XYZ".indexOf(item.attr("name").substring(0,1)) != -1){
			if(e % 6 == 1)
				eHtml += '<tr align="center">';
			eHtml += createTd(name,code,item);
			if(e % 6 == 0)
				eHtml +="</tr>";
			e++;
		}
	});
	
	htmlData += aHtml+html;
	htmlData += bHtml+html;
	htmlData += cHtml+html;
	htmlData += dHtml+html;
	htmlData += eHtml+html;
	
	var n = 1;
	htmlData += '<div id="city0"><table>';
	$(xmldom).find("airports").find("hotAirports").find("item").each(function(){
		var item = $(this);
		if(n % 6 == 1)
			htmlData += '<tr align="center">';
		htmlData += createTd(name,code,item);
		if(n % 6 == 0)
			htmlData +="</tr>";
		n++;
	});
	htmlData += "</table></div>";
	
	htmlData += "</div>";
	return htmlData;
}

function hideCityText(obj){
	for(var i = 0; i < 6; i++){
		if(obj == i){
			$("#city"+i).css("display","block");
			$("#td"+i).css({
				"font-size":"20px",
				"background-color":"#FFFFFF"
			});
		}else{
			$("#city"+i).css("display","none");
			$("#td"+i).css({
				"font-size":"16px",
				"background-color":"#B9DBFF"
			});
		}
	}
}

