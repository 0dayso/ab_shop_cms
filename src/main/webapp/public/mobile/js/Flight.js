function sortByPriceAsc(x, y) {
    return x.split(",")[6] - y.split(",")[6];
}
function sortByTimeAsc(x, y) {
    return x.split(",")[16] - y.split(",")[16];
}

function getDom(xmlstr) {
    var xmldom = null;
    if (navigator.userAgent.toLowerCase().indexOf("msie") != -1) {
	xmldom = new ActiveXObject("Microsoft.XMLDOM");
	xmldom.loadXML(xmlstr);
    } else {
	xmldom = new DOMParser().parseFromString(xmlstr, "text/xml");
    }
    var value = $(xmldom).find("status").find("value").text();
    if (value == 0) {
		return xmldom;
    } else {
		alert($(xmldom).find("status").find("msg").text());
		return false;
    }
}

function changeDays(date,days){
    var y=new Date(date.substring(0, 4)+"/"+date.substring(4, 6)+"/"+date.substring(6, 8));
    y.setDate(y.getDate()+days);
    var m=y.getMonth()+1;
    var d = y.getDate();
    if(m <= 9) m = "0"+m;
    if(d <= 9) d = "0"+d;
    return y.getFullYear()+''+m+''+d;
} 

function changeTime(urls,departDate,nextTripDate,tripType,trip,falg){
	if(tripType == 1){
		departDate = changeDays(departDate.toString(),falg);
		urls += "&departDate="+departDate;
	}else{
		if(trip == 1){
			departDate = changeDays(departDate.toString(),falg);
		}else{
			departDate = changeDays(nextTripDate.toString(),falg);
		}
		urls += "&departDate="+departDate+"&nextTripDate="+departDate;
	}
	var url = 'FlightListServlet?'+urls;
    window.self.location= url;
}

function link(tripType,trip,urls){
	if(tripType == 1){
		window.self.location= "PassengerContactAddServlet?"+urls;
	}else{
		if(trip == 1){
			window.self.location= "FlightListServlet?"+urls;
		}else{
			window.self.location= "PassengerContactAddServlet?"+urls;
		}
	}
}

function getFlightInfo(xmlstr,sortRule,tripType,trip,urls){
	var xmldom = getDom(xmlstr);
	var cabinInfo = '';
	$(xmldom).find("flights").find("item").each(function() {
		var flight = $(this);
		$(flight).find("cabins").find("item").each(function() {
	    	var cabin = $(this);
	    	cabinInfo += getCabin(flight,cabin);
	    });
	});
	var array = cabinInfo.split(";");
	var htmlData = '';
	if(sortRule == 1){
		htmlData = createInfo(array.sort(sortByTimeAsc),tripType,trip,urls);
	}else{
		htmlData = createInfo(array.sort(sortByPriceAsc),tripType,trip,urls);	
	}
	$('#flight_info').remove()
	$('#content').after(htmlData);	
}
function getCabin(flight,cabin){
	return flight.find('orgAirportCode').text()+','+flight.find('dstAirportCode').text()+','+
		   flight.find('flightNo').text()+','+flight.find('orgDate').text()+','+
		   cabin.find('cabinCode').text()+','+flight.find('airplaneStyle').text()+','+
		   cabin.find('price').text()+','+cabin.find('chdPrice').text()+','+
		   cabin.find('infPrice').text()+','+cabin.find('airportTax').text()+','+
		   cabin.find('airportTaxChd').text()+','+cabin.find('airportTaxInf').text()+','+
		   cabin.find('fuelTax').text()+','+cabin.find('fuelTaxChd').text()+','+
		   cabin.find('fuelTaxInf').text()+','+cabin.find('discount').text()+','+
		   flight.find('orgTime').text()+','+flight.find('dstTime').text()+','+
		   cabin.find('rule').text()+','+flight.find('orgTerminal').text()+','+
		   flight.find('dstTerminal').text()+','+cabin.find('farebasis').text()+','+
		   cabin.find('farebasisChd').text()+','+cabin.find('farebasisInf').text()+','+
		   flight.find('flightType').text()+','+flight.find('stop').text()+','+
		   cabin.find('basiccabinref').text()+','+cabin.find('farereference').text()+','+
		   cabin.find('seatNumber').text()+';';
}


function createInfo(array,tripType,trip,urls){
	var htmlData = '<div class="flight_info" id="flight_info">';
	for(var i = 0;i< array.length-1;i++ ){
		var url = '';
		var info = array[i].split(",");
		var num = info[28];
		if(tripType == 1){
			info[28] = 'G';
			url += "cabinInfo1="+info;
		}else{
			if(trip == 1){
				info[28] = 'G';
				url += "&trip=2&cabinInfo1="+info;
			}else{
				info[28] = 'R';
				url += "&cabinInfo2="+info;
			}
		}
		var u = '\''+urls+url+'\'';
		htmlData += '<div width="100%" class="flights" onclick="link('+tripType+','+trip+','+u+')">';
	    htmlData += '<table id="table">'
		    + '<tr><td class="time">'
		    + formaTime(info[16])
		    + '</td><td>'
		    + info[2]
		    + '</td><td class="price">￥'
		    + info[6]
		    + '</td><td rowspan="2">></td></tr>'
		    + '<tr><td>'
		    + formaTime(info[17])
		    + '</td><td>机型：'
		    + info[5]
		    + '</td><td>'
		    + getSeat(num)
		    + '</td></tr>'
		    + '</table>';
		    htmlData += '</div>';
	}
    htmlData += "</div>";
    return htmlData;
}
    

function formaTime(time){
	return time.substring(0,2)+":"+time.substring(2);
}
function getSeat(num){
	if(num == 'A'){
		return '票源充足';
	}else{
		return '剩余'+num+'张';
	}
}