<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'div.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function $(obj){return document.getElementById(obj)}
function   locking(){
	var Layer1 = document.getElementById("Layer1");
  //document.all.Layer1.style.display="block";   
  //document.all.Layer1.style.width=document.body.clientWidth;   
  //document.all.Layer1.style.height=document.body.clientHeight;
  Layer1.style.display="block";
  Layer1.style.width=document.body.clientWidth;
  Layer1.style.height=document.body.clientHeight;
  //alert("close layer1.......");
  //document.all.Layer1.style.display='none';
  alert("layer2 run.......");
  var Layer2 = document.getElementById("Layer2");
  Layer2.style.display="block";
  //document.all.divLayer2.style.display='block';
  //document.all.Layer2.style.width=document.body.clientWidth;
  //document.all.Layer2.style.height=document.body.clientHeight;
  //alert("layer2 block.......");
  }   
  function   Lock_CheckForm(theForm){   
  document.all.Layer1.style.display='none';
  //document.all.Layer2.style.display='none';
  return   false;   
  }
  function InfoCode(InfoNum){
var Str="";
if(InfoNum==0){
	Str="";
}else{
	var rs = document.getElementsByName("picurl");
	for(var i=0;i<rs.length;i++){
		if(rs[i].checked){
		
			if(rs[i].id=="radio2"){
				Str=$("localImg").value;
			}else {
				Str=$("httpImg").value;
			}
			break;
		}
	}
	format("InsertImage",Str);
	
}
Str=Str.replace(/\'/g,""); 
Str=Str.replace(/\"/g,"");
Str=Str.replace(/javascript/g,"");
Str=Str.replace(/onload/g,"");
Str=Str.replace(/onerror/g,"");
Str=Str.replace(/document/g,"");
if(Str=="http://"){Str=""}
  }
</script>
  </head>
  
  <body>
    <div id="Layer1" style="position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;
        z-index: 2; left: 0px; display: none;">
    </div>
    <div id="Layer2" align="center" style="position: absolute; z-index: 3; left: expression((document.body.offsetWidth-540)/2); top: expression((document.body.offsetHeight-170)/2); background-color: #fff; display: none;" >
    <s:form action="ajax-file!fileUpload.action" id="ajaxForm" name="ajaxForm" enctype="multipart/form-data" namespace="/ajaxFile" method="post" target="hidden_frame" >
    <table width="540" border="0" cellpadding="0" cellspacing="0" style="border: 0   solid   #e7e3e7;border-collapse: collapse">
        <tr>
            <td style="background-color: #73A2d6; color: #fff; padding-left: 4px; padding-top: 2px;
                font-weight: bold; font-size: 14px;" height="27" valign="middle">
                [&nbsp;请选择图片&nbsp;]
            </td>
        </tr>
        <tr>
      <td height="30%"><input type="radio" name="picurl" id="radio1" hidefocus="true" onclick="if(this.checked==true){$('httpImg').disabled='';$('localImg').disabled='disabled';}"/>&nbsp;图片地址:
      <input type="text" value="http://" style="border: 1px solid #7E9DB9; width: 235px;" id="httpImg" name="imgpath" disabled="disabled"/></td>
    </tr>
    <tr> 
      <td height="30%" align="left" id="upid" width="400"><input type="radio" id="radio2" name="picurl" hidefocus="true" onclick="if(this.checked==true){$('httpImg').disabled='disabled';$('localImg').disabled='';}" checked/>&nbsp;上传图片: 
        <input type="file" name="ajaxFile" id="localImg" style="width:235px; border:#7E9DB9 1px solid"/>
      </td>
    </tr>
	<tr><td height="30%">
		<input type="button" class='But' value="确定" onclick="InfoCode(1)"/> 
		<input type="button" class='But' value="取消" onclick="Lock_CheckForm(this);"/>
		</td>
	</tr>
        
    </table>
    <input type="hidden" id="fileSub" name="fileSub"/>
    <input type="hidden" id="support" name="support" value=".jpg|.jpeg|.png|.gif|.bmp"/>
    <iframe name='hidden_frame' id="hidden_frame" style='display:none'></iframe>  
    </s:form> 
</div>
  </body>
</html>
