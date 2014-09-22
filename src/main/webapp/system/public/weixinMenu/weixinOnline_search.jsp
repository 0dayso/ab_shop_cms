<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>在线菜单</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/public/css/global.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/uploadify.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/markitup.set.css" rel="stylesheet" type="text/css">
		<link href="<%=request.getContextPath()%>/public/css/jquery.wysiwyg.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery-1.4.js"></script>
		<link href="<%=request.getContextPath()%>/public/css/jquery.loadmask.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery.markitup.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/jquery.loadmask.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/set.js"></script>
		<script type="text/javascript">
			var gifPath = "<%=request.getContextPath()%>/public/images/treeBuilderImages/";
			function search() {
				var commands = "";
				$("body").mask("正在查询中,请稍等...");
				$.post(
					"<%=request.getContextPath()%>/system/weixinMenuSearch_operate.action",
					{operation:'get'},
					function(data){
						$("#jvalue").val(data);
						$("body").unmask();
						$("#save").submit();
					}
				);
			}
			$(function(){
				JSONeditor.start('tree','jform',false,true);
				Opera=(navigator.userAgent.toLowerCase().indexOf("opera")!=-1);
				Safari=(navigator.userAgent.toLowerCase().indexOf("safari")!=-1);
				Explorer=(document.all && (!(Opera || Safari)));
				Explorer7=(Explorer && (navigator.userAgent.indexOf("MSIE 7.0")>=0));
				if(Explorer7 && location.href.indexOf("file:")!=0){
					prompt("This is just to get input boxes started in IE7 - who deems them unsecure.","I like input boxes...")
				}
				$('#jvalue').markItUp(mySettings);
			});
		</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/public/js/JSONeditor.js"></script>
	</head>
	
	<body>
		<div class="headerPart">
  			<img class="imgAll" src="<%=request.getContextPath() %>/public/images/tubiao-01.png"/>
  			<span><font class="font1">当前位置:</font><font class="menuName_1">在线菜单</font>
  			<input type="button" name="search" id="search" onclick="search();" class="query_btn" onmousemove="this.className='query_btn1'" onmouseout="this.className='query_btn'">
  			</span>
        </div>
		<div class="main">
			<table class="tableborder" border="1" bordercolor="#dbdbdb">
				<tr id="tr2">
					<td align="left" valign="top">
						<div id="tree"></div>
					</td>
					<td align="left" style="padding-left:10px;" valign="top">
						<div id="jform">
							<form name="jsoninput" onsubmit="return treeBuilder.jsonChange(this)">
								<div id="jExamples">
									案例:&nbsp;
									<select name="jloadExamples" onchange="JSONeditor.loadExample(this.value)">
										<option value="0">
											None/empty
										</option>
										<option value="1">
											Employee data
										</option>
										<option value="2">
											Sample Konfabulator Widget
										</option>
										<option value="3">
											Member data
										</option>
										<option value="4">
											A menu system
										</option>
										<option value="5">
											The source code of this JSON editor
										</option>
									</select>
									<br>
								</div>
								左键: <input name="jlabel" type="text" value="" size="60" style="width: 252px"><br>
								右值:
								<br/>
								<textarea name="jvalue" id = "jvalue" rows="20" cols="80"></textarea>
								<br>
								Data type:
								<select onchange="treeBuilder.changeJsonDataType(this.value,this.parentNode)" name="jtype">
									<option value="object">
										object
									</option>
									<option value="array">
										array
									</option>
									<option value="function">
										function
									</option>
									<option value="string">
										string
									</option>
									<option value="number">
										number
									</option>
									<option value="boolean">
										boolean
									</option>
									<option value="null">
										null
									</option>
									<option value="undefined">
										undefined
									</option>
								</select>
								<input name="orgjlabel" type="hidden" value="" size="50" style="width: 300px">
								<input onfocus="this.blur()" type="submit" value="Save" id="save">
								<br>
								<input name="jAddChild" onfocus="this.blur()" type="button"
									onclick="treeBuilder.jsonAddChild(this.parentNode)"
									value="Add child">
								<input name="jAddSibling" onfocus="this.blur()" type="button"
									onclick="treeBuilder.jsonAddSibling(this.parentNode)"
									value="Add sibling">
								<br>
								<input name="jRemove" onfocus="this.blur()" type="button"
									onclick="treeBuilder.jsonRemove(this.parentNode)"
									value="Delete">
								<input name="jRename" onfocus="this.blur()" type="button"
									onclick="treeBuilder.jsonRename(this.parentNode)"
									value="Rename">
								<input name="jCut" onfocus="this.blur()" type="button"
									onclick="treeBuilder.jsonCut(this.parentNode)" value="Cut">
								<input name="jCopy" onfocus="this.blur()" type="button"
									onclick="treeBuilder.jsonCopy(this.parentNode)" value="Copy">
								<input name="jPaste" onfocus="this.blur()" type="button"
									onclick="treeBuilder.jsonPaste(this.parentNode)" value="Paste">
								<br>
								<input type="checkbox" name="jbefore">Add children first/siblings before
								<br>
								<input type="checkbox" name="jPasteAsChild">
									Paste as child on objects & arrays
								<br>
								<div id="jformMessage"></div>
							</form>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>