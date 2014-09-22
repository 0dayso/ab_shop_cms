function Year_Month(){ 
    var now = new Date(); 
    var yy = now.getFullYear(); 
    var mm = now.getMonth()+1; 
    var dd = now.getDate();
    var cl = '<span>'; 
    if (now.getDay() == 0) cl = '<font color="#000000">'; 
    if (now.getDay() == 6) cl = '<font color="#000000">'; 
    return(cl +  yy + '-' + mm +'-'+ dd +'</span>'); }
 function Day_of_Today(){ 
    var day = new Array(); 
    day[0] = "星期日"; 
    day[1] = "星期一"; 
    day[2] = "星期二"; 
    day[3] = "星期三"; 
    day[4] = "星期四"; 
    day[5] = "星期五"; 
    day[6] = "星期六"; 
    var now = new Date(); 
    var cl = '<span>'; 
    return(cl +  day[now.getDay()] + '</span>'); }
 function CurentTime(){ 
    var now = new Date(); 
    var hh = now.getHours(); 
    var mm = now.getMinutes(); 
    var ss = now.getTime() % 60000; 
    ss = (ss - (ss % 1000)) / 1000; 
    var clock = hh+':'; 
    if (mm < 10) clock += '0'; 
    clock += mm+':'; 
    if (ss < 10) clock += '0'; 
    clock += ss; 
    return(clock); } 
function refreshCalendarClock(){ 
	document.getElementById('calendarClock1').innerHTML = Year_Month(); 
	document.getElementById('calendarClock2').innerHTML = Day_of_Today(); 
	document.getElementById('calendarClock3').innerHTML = CurentTime(); 
}
document.write('<span id="calendarClock1"> </span>');
document.write('&nbsp;')
document.write('<span id="calendarClock2"> </span>');
document.write('&nbsp;')
document.write('<span id="calendarClock3"> </span>');
setInterval('refreshCalendarClock()',1000);