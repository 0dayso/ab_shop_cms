<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
		
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath() %>/public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/public/js/jquery-1.4.js"></script>
		<link href="<%=request.getContextPath()%>/public/css/uploadify.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.set.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=path%>/public/js/jquery-1-s.js"></script>
		<script type="text/javascript" src="<%=path%>/public/js/ajaxfileupload.js"></script>
		<title>会议添加</title>
		<script type="text/javascript">
		    //上传异步文件
		    //参数说明：id：为input对应的ID，  resType对应于素材类型
			function ajaxFileUpload(id, resType){
				var reg	= new RegExp(/[\.jpg || \.jpeg || \.bmp || \.png|| \.JPG|| \.JPEG|| \.BMP]$/);
				if (! reg.test($("#"+id).val()))
				{
					alert("图片格式必须为jpg、jpeg、png、bmp! ");
					return false;
				}
			    $.ajaxFileUpload({
			      url:'<%=path %>/system/conference_upload.action',  
			      secureuri:false,
			      fileElementId:id,                         //文件选择框的id属性
			      data: {resType:resType},
			      dataType: 'json',                                     //服务器返回的格式，可以是json
			      complete:function(){
						$("#loading").hide();
				  },				
				  success: function (data, status){
						if(data.status == "success"){ 
							$("#logoUrl").val(data.link);
						}else{
							$('.picurl').tip({width:'240',status:'error',content:'图片上传失败，请重试！',dis_time:1000});
						}
				  },
				  error: function (data, status, e){
						alert(e);
				  }
			    });
			}
		    
		    function save(){
		    	var  title = $("#title1").val();
		    	if(title == ""){
		    		alert("请输入会议标题");
		    		return false;
		    	}
		    	$("#title").val(title);
		    	var content = $("#texteditcontent1").val();
		    	if(content == ""){
		    		alert("会议简介不能为空");
		    	    return false;
		    	}else if(content.length > 2000){
		    		alert("会议简介内容长度不能超过2000");
		    		return false;
		    	}
		    	$("#content").val(content);
		    	if($("#logoUrl").val() == ""){
		    		alert("请上传会议logo");
		    		return false;
		    	}
		    	$("#form").submit();
		    }
		    
		    
		</script>
		
	</head>
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">会议管理 - 会议添加</font></span>
        </div>
    	<div class="main">
    		<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr>
					<td class="th1">会议标题:</td>
					<td align="left"><input type="text"" id="title1" value="" /></td>
					<td align="left">注意：不少于4个字</td>
				</tr>
				<tr>
					<td class="th1">会议logo:</td>
					<td align="left"><input type="file" onchange="ajaxFileUpload('mp3fileupload2','image');" id="mp3fileupload2" name="imagefile"/></td>
					<td align="left">上传图片,建议640*130尺寸</td>
					
				</tr>
				<tr>
					<td class="th1">会议简介:</td>
					<td align="left"><textarea id="texteditcontent1" style="width:100%;"></textarea></td>
					<td align="left">字数不能多于2000字</td>
				</tr>
				
				<s:form  id="form" action="conference_add" method="post" theme="simple" namespace="/system">
				<tr>
				   <td class="th1">会议状态:</td>
				   <td align="left">
				       <select name="state" id="state">
				           <option value="1">激活</option>
				           <option value="0">关闭</option>
				       </select>
				   </td>
				   <td align="left"></td>
				</tr>
				<tr>
					<td align="center" colspan="3">
					    <input type="hidden" name="title" id="title"/>
					    <input type="hidden" name="content" id="content"/>
					    <input type="hidden" name="logoUrl" id="logoUrl"/>
						<input type="button" class="add_btn1" onmousemove="this.className='add_btn1'" onmouseout="this.className='add_btn'" onclick="save();"  value=""/>
					</td>
				</tr>
				</s:form>
			</table>
		</div>
	</body>
</html>