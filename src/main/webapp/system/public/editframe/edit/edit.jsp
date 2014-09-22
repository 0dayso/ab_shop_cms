<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>-</title>
<script type="text/javascript" src="../../../common/js/jquery-1.4.2.min.js"></script>
<style>
html{overflow:hidden;scrollbar-face-color:#F7F5F4;scrollbar-highlight-color:#cccccc;scrollbar-shadow-color:#cccccc;scrollbar-3dlight-color: #ffffff;scrollbar-arrow-color: #cccccc;scrollbar-track-color:#ffffff;scrollbar-darkshadow-color:#ffffff;width:100%;height:100%}
body{font-family:"宋体";font-size:12px;margin:0px;width:100%;height:100%;color:#333333}
.cImg{height:19px;width:19px;cursor:pointer}
.face{height:24px;width:24px;cursor:pointer}
#EditWin{border:1px #CCCCCC solid;padding-bottom:5px}
#EditWin img{cursor:pointer;width:300px;height:200px;background:url(images/ico.gif)}
/*简单模式*/
#EditWinT1{background:#F7F5F4;width:100%;height:24px;padding-top:4px;border-bottom:1px #CCCCCC solid}
#EditWinT1 div{float:left;display:inline;overflow:hidden}
/*高级模式*/
#EditWinT2{background:#F7F5F4;width:100%;height:56px;border-bottom:1px #CCCCCC solid}
#EditWinT2 div{float:left;display:inline;overflow:hidden}
#EditWinF{position:relative;overflow:hidden;margin:5px auto 0px auto;border:1px #CCCCCC solid}

#Other{position:absolute;z-index:1;top:0px;left:0px;background:#666666;width:100%;height:100%;filter:alpha(opacity=10);opacity:0.1}
#Other_Text{background:#FFFFFF;position:absolute;z-index:2;border:1px #2EA7FB solid;padding:0px 0px 1px 0px}
#Other_Text a{text-decoration:none;color:#333333;margin-top:1px;background:#f1f1f1;display:block;width:100%;padding:3px 0px 3px 0px;text-align:center}
#Other_Text a:hover{background:#2EA7FB;color:#FFFFFF}

.Line{height:100%;width:2px;background:url(images/ico.gif) -298px 0px}
.Line2{height:19px;width:2px;background:url(images/ico.gif) -298px -4px}

.ButDiv{height:100%}
.ButDivT{width:100%;height:22px;padding-top:6px}
.ButDivF{width:100%;height:25px;padding-top:3px}
.ButDivC{width:100%;height:53px;padding-top:3px}

.But1{height:18px;border:1px #F7F5F4 solid}
.But1On{height:18px;border:1px #2EA7FB solid;background:#DDF0FF}
.But1Down{height:17px;border:1px #0078FF solid;background:#BDE2FF;padding-top:1px}

.But2{margin-left:5px;width:63px;height:20px;background:url(images/ico.gif) 0px -61px}
.But2On{margin-left:5px;width:63px;height:20px;background:url(images/ico.gif) -64px -61px}
.But2Down{margin-left:5px;width:63px;height:19px;padding-top:1px;background:url(images/ico.gif) -128px -61px}

.But2L{width:44px;padding-left:3px;height:100%;line-height:180%}
.But2R{width:16px;height:100%}
.But3{height:48px;border:1px #F7F5F4 solid}
.But3On{height:48px;border:1px #2EA7FB solid;background:#DDF0FF}
.But3Down{height:47px;border:1px #0078FF solid;background:#BDE2FF;padding-top:1px}
</style>
<script language="javascript">
function $(obj){return document.getElementById(obj)}
function S(Divid,Ty){$(Divid).scrollTop=Ty}
function C(Divid,Css){$(Divid).className=Css}
var E;
var QJ_Content="";
var gIsIE=document.all;
var Scolor="";
var Sface="";
var ScolorType="ForeColor";
//色块
function GetScolor(){
var color = [
		['#000000', '#993300', '#333300', '#003300', '#003366', '#000080', '#333399', '#333333'],
		['#800000', '#FF6600', '#808000', '#008000', '#008080', '#0000FF', '#666699', '#808080'],
		['#FF0000', '#FF9900', '#99CC00', '#339966', '#33CCCC', '#3366FF', '#800080', '#999999'],
		['#FF00FF', '#FFCC00', '#FFFF00', '#00FF00', '#00FFFF', '#00CCFF', '#993366', '#C0C0C0'],
		['#FF99CC', '#FFCC99', '#FFFF99', '#CCFFCC', '#CCFFFF', '#99CCFF', '#CC99FF', '#F1F1F1']
	];
var r = color.length;
var c = color[0].length;
var tr = [];
for(var i=0;i<r;i++) {
var td = [];
for(var j=0;j<c;j++) {
		td.push('<td style="background:'+color[i][j]+';font-size:0px" onclick="format(ScolorType,\''+color[i][j]+'\')"><img src="images/b.gif" class="cImg"></td>');
		}
	tr.push('<tr>'+ td.join("") +'</tr>');
	}
Scolor='<table cellspacing="1" cellpadding="0">'+tr.join("")+'</table>';
}

//调节窗口大小
function ChangeWin(){
var H=document.documentElement.clientHeight;
var W=document.documentElement.clientWidth;
H=H-43;
if($("EditWinT2").style.display==""){H=H-28}
$("EditWinF").style.height=H+"px";
$("EditWinF").style.width=W-14+"px";
}

//脚本
function format(type,para) {
E.focus();
	if(type=="StarText"){
		var R=E.document.selection.createRange().duplicate();
		var text=R.text;
		if(text==""){
			text=window.prompt("请输入发光字", "文字");
			if(!text||text==null)return;
			}
		R.pasteHTML("<span class='ShowText' style='filter:glow(color="+para+",strength=3)'>"+text+"</span>&nbsp;");
		return;
	}

	if (!para){
	//if (para){
		if(gIsIE){
			E.document.execCommand(type);
		}else{
			E.document.execCommand(type,false,false);
		}
	}else{
		E.document.execCommand(type,false,para);
		//做一个图片上传的操作，然后再执行下面命令，并且把para换为已上传的图片网络地址
		//document.getElementById("ajaxFile").value=para;
		//document.getElementById("fileSub").value=para.substring(para.lastIndexOf("."));//文件扩展名
		//ajaxForm.action="/ajaxFile/ajax-file!fileUpload.action";
		//ajaxForm.submit();
		//TODO 弹出一个遮蔽层，提示：文件上传中，请稍后。
	    
		//
		//var reg=new RegExp( ' <img src= " '+para+ ' "> ', 'ig'); 
		//E.document.body.innerHTML=E.document.body.innerHTML.replace(reg,"<img width='200px' height='100px' src= 'E:\Media\pic\QQ_73.jpg' >"); 
	}
	E.focus();
}
	//ajax上传文件完毕后回调的函数
	function callBack(path,msg,flag){
		E.focus();
		E.document.execCommand("InsertImage",false,unescape(path));
		parent.unLock();
		E.focus();
	}
function newWIn(){
var str=window.showModalDialog("face.html","","dialogWidth=450px;dialogHeight=258px;center=yes;help=no;resizable=no;status=no;scroll=no");
if(str!="")format("InsertImage",str)
}
function InsertHtml(InType){
var InText;
switch (InType){
	case "img": InType="InsertImage";InText="插入图片";break;
	case "link": InType="CreateLink";InText="插入链接(请先选择一段文字或图片)";break;
	}
var Text= window.prompt(InText, "http://");
if (!Text||Text==null||Text.length<10)return;
format(InType,Text);
}

var OnOType=null;
function openOther(OType,T,L){
if(OnOType==OType){CloseOther();return}
W=100,OHtml="";
switch (OType){
	case "size":
		W=130;
		OHtml=	"<a href='#' onClick=\"format('fontsize',1);return false\" style='font-size:8pt'>1(8pt)</a>"+
			"<a href='#' onClick=\"format('fontsize',2);return false\" style='font-size:10pt'>2(10pt)</a>"+
			"<a href='#' onClick=\"format('fontsize',3);return false\" style='font-size:12pt'>3(12pt)</a>"+
			"<a href='#' onClick=\"format('fontsize',4);return false\" style='font-size:14pt'>4(14pt)</a>"+
			"<a href='#' onClick=\"format('fontsize',5);return false\" style='font-size:18pt'>5(18pt)</a>"+
			"<a href='#' onClick=\"format('fontsize',6);return false\" style='font-size:24pt'>6(24pt)</a>";
	break;
	case "family":
		W=110;
		var fontface = ['宋体','黑体','楷体','隶书','Arial Black','Century Gothic','Comic Sans MS','Verdana','WingDings'];
		var a = [];
		for(var k=0;k<fontface.length;k++) {
			a.push('<a href="#" onClick="format(\'FontName\',\''+fontface[k]+'\');return false" style="font:normal 12px '+fontface[k]+';">'+fontface[k]+'</a>');
			}
		OHtml=a.join("");
	break;
	case "more":
		W=61;
		OHtml=	"<a href='#' onclick=\"format('Unlink');return false\">清除链接</a>"+
			"<a href='#' onclick=\"InfoHtml('code',500,240);return false\">插入代码</a>"+
			"<a href='#' onclick=\"format('InsertOrderedList');return false\">编号列表</a>"+
			"<a href='#' onclick=\"format('InsertUnorderedList');return false\">项目符号</a>"+
			"<a href='#' onclick=\"format('InsertHorizontalRule');return false\">水平线</a>"+
			"<a href='#' onclick=\"format('Indent');return false\">缩进＋</a>"+
			"<a href='#' onclick=\"format('Outdent');return false\">缩进－</a>"+
			"<a href='#' onclick=\"format('SaveAs');return false\">另存为</a>"+
			"<a href='#' onclick=\"format('SelectAll');return false\">全选</a>";

	break;
	case "color":
		W=161;OHtml=Scolor;ScolorType="ForeColor";
	break;
	case "bgcolor":
		W=161;OHtml=Scolor;ScolorType="BackColor";
	break;
	case "showtext":
		if(gIsIE){
			W=161;OHtml=Scolor;ScolorType="StarText";
			}else{alert("您的浏览器不支持发光字,建议您使用IE浏览器!");return}
	break;
	case "face":
		W=401;
		if(Sface==""){
			var kk=0;
			for(var k=0;k<80;k++){
				Sface+="<td style='background:#ffffff'><img src='images/face/"+k+".gif' class='face' onclick=\"format('InsertImage',this.src);\"></td>"
				if(kk>14&&k!=79){kk=0;Sface+="</tr><tr>"}else{kk++}
				}
			Sface="<table cellspacing='1' cellpadding='0' style='background:#cccccc'><tr>"+Sface+"</table>"
			}
		OHtml=Sface;
	break;
	}
OnOType=OType;
$("Other_Text").style.left=L+"px";
$("Other_Text").style.top=T+"px";
$("Other_Text").style.width=W+"px";
$("Other_Text").innerHTML=OHtml;
$("Other_Text").style.display="";
$("Other").style.display="";
}
function CloseOther(){OnOType=null;$("Other").style.display="none";$("Other_Text").style.display="none"}
document.onclick=function(e){
if(gIsIE){var el=event.srcElement}else{var el=e.target}
if(el.className!="Aico"&&OnOType!=null){CloseOther()}
}

//切换模式
var OpenMenu=1;
function ChangeMenu(){
if(args["A"]==0){alert('此环境下,系统不允许切换编辑器!');return}
if($("EditWinT1").style.display==""){
	$("EditWinT1").style.display="none";
	$("EditWinT2").style.display=""
	}else{
	$("EditWinT1").style.display="";
	$("EditWinT2").style.display="none"
	}
ChangeWin()
}

//获取URL参数
function GetUrlParms() {
var args=new Object();
var query=location.search.substring(1);
var pairs=query.split("&");
for(var i=0;i<pairs.length;i++) {   
	var pos=pairs[i].indexOf('=');
	if(pos==-1) continue;
	var argname=pairs[i].substring(0,pos);
	var value=pairs[i].substring(pos+1);
	args[argname]=unescape(value);
	}
return args;
}
var args = new Object();
args = GetUrlParms();

//提交事件
function GetCon(){parent.$(args["V"]).value=E.document.getElementsByTagName("body")[0].innerHTML}
window.onload=function(){
if(!parent.$(args["V"])){alert('参数错误!');return;}
GetScolor();
OpenMenu=args["T"];
$("EditWinT"+OpenMenu).style.display="";

ChangeWin();
E=window.frames["HtmlEditor"];
//E.document.designMode="on";
window.setTimeout("LoadOkNow()",200)
}
function LoadOkNow(){
QJ_Content=parent.$(args["V"]).value;
var BlankHtml="<html><head><link href='images/blank.css' rel='stylesheet' type='text/css' /></head><body></body></html>";
E.document.open();
E.document.designMode="on";
E.document.write(BlankHtml);
E.document.close();
//E.focus();
E.document.getElementsByTagName("body")[0].innerHTML=QJ_Content;
}

function InfoHtml(InfoT,InfoW,InfoH){
	var str="";
	if(InfoT=="imgupload"){
		var InfoUrl="../../User_admin_BlogUpload.asp";
	}if(InfoT=="img"){
		E.focus();
		parent.locking();//调用父页面中方法，弹出遮蔽层
	}else{
		var InfoUrl=InfoT+".html";
		str=window.showModalDialog(InfoUrl,"","dialogWidth="+InfoW+"px;dialogHeight="+InfoH+"px;center=yes;help=no;resizable=no;status=no;scroll=no");
	}


if(str!=""){
	switch(InfoT){
		case "img":
		//case "imgupload":
			format("InsertImage",str);
		break;
		case "code":
		case "swf":
		case "mp3":
		case "wmv":
			if(str!=""&&str!="undefined"){
				E.document.getElementsByTagName("body")[0].innerHTML+=str;
				}
		break;
		}
	}
E.focus();
}
//清空编辑区1
function clearArea(){
	E.document.getElementsByTagName("BODY")[0].innerHTML="";
}
</script>
</head>
<body scroll=no onresize="ChangeWin();">
<noscript><div style="width:100%;height:100%">温馨提示：<br/><br/>您的浏览器不支持Javascript，请进入“工具”-->>“Internet选项”-->>“高级”选项卡下设置</div></noscript>
<div id="EditWin">
<div id="EditWinT1" style="display:none">
<!--简单模式-->
<div id="B1" class="But2"><div class='But2L'>字体</div><div class='But2R' onmouseover="C('B1','But2On')" onmouseout="C('B1','But2')" onmousedown="C('B1','But2Down')" onclick="C('B1','But2On');openOther('family',26,6)"><img src='images/b.gif' style='margin-left:-200px' class='Aico'/></div></div>
<div id="B2" class="But2"><div class='But2L'>字号</div><div class='But2R' onmouseover="C('B2','But2On')" onmouseout="C('B2','But2')" onmousedown="C('B2','But2Down')" onclick="C('B2','But2On');openOther('size',26,74)"><img src='images/b.gif' style='margin-left:-200px' class='Aico'/></div></div>
<div class="Line2" style="margin-left:8px"></div>
<div id="B3" style="margin-left:3px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Bold')"><img src="images/b.gif" alt="加粗"/></div>
<div id="B4" style="margin-left:2px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Italic')"><img src="images/b.gif" style="margin-left:-16px" alt="斜体"/></div>
<div id="B5" style="margin-left:4px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Underline')"><img src="images/b.gif" style="margin-left:-34px" alt="下划线"/></div>
<div class="Line2" style="margin-left:6px"></div>
<div id="B6" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');openOther('face',26,54)"><img src="images/b.gif" style="margin-left:-81px;margin-top:-22px" alt="选择表情" class='Aico'/></div>
<div id="B15" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');InfoHtml('img',440,100)"><img src="images/b.gif" style="margin-left:-106px;margin-top:-22px" alt="插入图片" class='Aico'/></div>
<div id="B7" style="margin-left:5px;width:20px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');openOther('color',26,279)"><img src="images/b.gif" style="margin-left:-51px" alt="文本颜色" class='Aico'/></div>
<div id="B8" style="margin-left:5px;width:22px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');openOther('bgcolor',26,306)"><img src="images/b.gif" style="margin-left:-156px" alt="文本底色" class='Aico'/></div>
<div id="B9" style="margin-left:4px;width:19px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');openOther('showtext',26,334)"><img src="images/b.gif" style="margin-left:-70px" alt="发光字" class='Aico'/></div>
<div id="B10" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');InsertHtml('link')"><img src="images/b.gif" style="margin-left:-179px" alt="插入链接"/></div>
<div id="B11" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('RemoveFormat')"><img src="images/b.gif" style="margin-left:-89px" alt="清除格式"/></div>
<div class="Line2" style="margin-left:6px"></div>
<div id="B12" style="margin-left:6px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Justifyleft')"><img src="images/b.gif" style="margin-left:-105px" alt="左对齐"/></div>
<div id="B13" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Justifycenter')"><img src="images/b.gif" style="margin-left:-122px" alt="居中"/></div>
<div id="B14" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Justifyright')"><img src="images/b.gif" style="margin-left:-139px" alt="右对齐"/></div>
<div class="Line2" style="margin-left:6px"></div>
<div id="B16" style="margin-left:5px;width:46px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');ChangeMenu()"><img src="images/b.gif" style="margin-left:-131px;margin-top:-22px" alt="切换到高级模式"/></div>
</div>

<!-- 高级模式 -->
<div id="EditWinT2" style="display:none">
	<div class="ButDiv" style="width:214px;">
<!--第一列按钮-->
		<div class="ButDivT">
			<div id="A1" class="But2"><div class='But2L'>扩展</div><div class='But2R' onmouseover="C('A1','But2On')" onmouseout="C('A1','But2')" onmousedown="C('A1','But2Down')" onclick="C('A1','But2On');openOther('more',28,6)"><img src='images/b.gif' style='margin-left:-200px' class='Aico'/></div></div>
			<div id="A2" class="But2"><div class='But2L'>字体</div><div class='But2R' onmouseover="C('A2','But2On')" onmouseout="C('A2','But2')" onmousedown="C('A2','But2Down')" onclick="C('A2','But2On');openOther('family',28,25)"><img src='images/b.gif' style='margin-left:-200px' class='Aico'/></div></div>
			<div id="A3" class="But2"><div class='But2L'>字号</div><div class='But2R' onmouseover="C('A3','But2On')" onmouseout="C('A3','But2')" onmousedown="C('A3','But2Down')" onclick="C('A3','But2On');openOther('size',28,73)"><img src='images/b.gif' style='margin-left:-200px' class='Aico'/></div></div>
		</div>
		<div class="ButDivF">
			<div id="A4" style="margin-left:4px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Bold')"><img src="images/b.gif" alt="加粗"/></div>
			<div id="A5" style="margin-left:2px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Italic')"><img src="images/b.gif" style="margin-left:-16px" alt="斜体"/></div>
			<div id="A6" style="margin-left:4px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Underline')"><img src="images/b.gif" style="margin-left:-34px" alt="下划线"/></div>
			<div id="A7" style="margin-left:5px;width:20px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');openOther('color',53,76)"><img src="images/b.gif" style="margin-left:-51px" alt="文本颜色" class='Aico'/></div>
			<div id="A8" style="margin-left:5px;width:22px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');openOther('bgcolor',53,103)"><img src="images/b.gif" style="margin-left:-156px" alt="文本底色" class='Aico'/></div>
			<div id="A9" style="margin-left:5px;width:19px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');openOther('showtext',53,132)"><img src="images/b.gif" style="margin-left:-70px" alt="发光字" class='Aico'/></div>
			<div id="A10" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');InsertHtml('link')"><img src="images/b.gif" style="margin-left:-179px" alt="插入链接"/></div>
			<div id="A11" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('RemoveFormat')"><img src="images/b.gif" style="margin-left:-89px" alt="清除格式"/></div>
		</div>
	</div>
	<div class="Line"></div>
<!--第二列按钮-->
	<div class="ButDiv" style="width:85px">
		<div class="ButDivT">
			<div id="A12" style="margin-left:6px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Justifyleft')"><img src="images/b.gif" style="margin-left:-105px" alt="左对齐"/></div>
			<div id="A13" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Justifycenter')"><img src="images/b.gif" style="margin-left:-122px" alt="居中"/></div>
			<div id="A14" style="margin-left:5px;width:18px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Justifyright')"><img src="images/b.gif" style="margin-left:-139px" alt="右对齐"/></div>
		</div>
		<div class="ButDivF">
			<div id="A15" style="margin-left:4px;width:74px" class="But1" onmouseover="C(this.id,'But1On')" onmouseout="C(this.id,'But1')" onmousedown="C(this.id,'But1Down')" onclick="C(this.id,'But1On');format('Justifyfull')"><img src="images/b.gif" style="margin-top:-22px" alt="自动对齐"/></div>
		</div>
	</div>
	<div class="Line"></div>
<!--第三列按钮-->
	<div class="ButDiv" style="width:190px"><div class="ButDivC">
		<div id="A16" style="width:30px;margin-left:5px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');openOther('face',53,130)"><img src="images/b.gif" style="margin:-89px 0px 0px -15px" alt="插入表情" class='Aico'/></div>
		<div id="A17" style="width:30px;margin-left:4px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');InfoHtml('img',440,100)"><img src="images/b.gif" style="margin:-89px 0px 0px -60px" alt="插入图片"/></div>
		<div id="A18" style="width:34px;margin-left:4px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');InfoHtml('swf',440,100)"><img src="images/b.gif" style="margin:-89px 0px 0px -108px" alt="插入Flash"/></div>
		<div id="A19" style="width:30px;margin-left:4px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');InfoHtml('mp3',440,100)"><img src="images/b.gif" style="margin:-89px 0px 0px -159px" alt="插入Mp3/Wma音乐"/></div>
		<div id="A20" style="width:30px;margin-left:4px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');InfoHtml('wmv',440,100)"><img src="images/b.gif" style="margin:-89px 0px 0px -205px" alt="插入Wmv视频"/></div>
	</div></div><div class="Line"></div>
<!--第四列按钮-->
	<div class="ButDiv" style="width:80px"><div class="ButDivC">
		<div id="A21" style="width:30px;margin-left:5px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');format('Undo')"><img src="images/b.gif" style="margin:-142px 0px 0px -15px" alt="撤消操作"/></div>
		<div id="A22" style="width:30px;margin-left:5px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');format('Redo')"><img src="images/b.gif" style="margin:-142px 0px 0px -58px" alt="恢复操作"/></div>
	</div></div><div class="Line"></div>
<!--第五列按钮-->
	<div class="ButDiv" style="width:40px"><div class="ButDivC">
		<div id="A23" style="width:30px;margin-left:5px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');ChangeMenu()"><img src="images/b.gif" style="margin:-142px 0px 0px -110px" alt="切换至普通模式"/></div>
	</div></div><div class="Line"></div>
	
<!--第六列按钮-->
<!-- 
	<div class="ButDiv" style="width:40px"><div class="ButDivC">
		<div id="A24" style="width:30px;margin-left:5px" class="But3" onmouseover="C(this.id,'But3On')" onmouseout="C(this.id,'But3')" onmousedown="C(this.id,'But3Down')" onclick="C(this.id,'But3On');ChangeMenu()"><img src="images/b.gif" style="margin:-142px 0px 0px -110px" alt="预览"/></div>
	</div></div>
	 -->
</div>
<div id="EditWinF">
<iframe id="HtmlEditor" name="HtmlEditor" style="height:100%;width:100%" frameborder="0" marginheight=0 marginwidth=0 src="about:blank"></iframe>
<div id="Other" style="display:none" onclick="CloseOther()"></div>
</div>
</div>
<div id="Other_Text"></div>
</body>
</html>