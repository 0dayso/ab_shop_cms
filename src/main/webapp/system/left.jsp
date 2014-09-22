<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="../public/css/global.css" rel="stylesheet" type="text/css">
		<script type="text/javascript">
			var empName = '${employee}';
			var title = '${projectTitle}';
			if(empName == null || empName == "" || title == null || title == ""){
				window.self.location = '<%=request.getContextPath()%>';
			}
			function show(obj,length) {
				for(var i = 1; i <= length; i++){
			    	if(i == obj){
			    		if (document.getElementById('menu' + i).style.display == 'none'){
			    			document.getElementById('menu'+i).style.display = 'block';
			    			document.getElementById('menuList'+i).style.backgroundColor='#699D08';
			    			document.getElementById('menuName'+i).style.color='#fff';
			    			document.getElementById('menuName'+i).style.background='url("../public/images/bar_click.png") no-repeat scroll right center transparent';
			    		} else {
				    		document.getElementById('menu'+i).style.display = 'none';
				    		document.getElementById('menuList'+i).style.backgroundColor='#F4F4F4';
				    		document.getElementById('menuName'+i).style.color='#000';
				    		document.getElementById('menuName'+i).style.background='url("../public/images/bar_bg.png") no-repeat scroll right center transparent';
				    	}
			    	} else {
			    		if(isExist('menu' + i)){
			    			document.getElementById('menu'+i).style.display = 'none';
			    		}
			    		if(isExist('menuList'+i)){
			    			document.getElementById('menuList'+i).style.backgroundColor='#F4F4F4';
			    		}
			    		if(isExist('menuName'+i)){
			    			document.getElementById('menuName'+i).style.color='#000';
			    			document.getElementById('menuName'+i).style.background='url("../public/images/bar_bg.png") no-repeat scroll right center transparent';
			    		}
			    	}
			    }
			}
			function isExist(elementId){
				var divEle = document.getElementById(elementId);
				if(divEle){
					return true;
				}else{
					return false;
				}
			}
		</script>
		<style type="text/css"> 
		BODY { MARGIN-TOP: 0px; SCROLLBAR-FACE-COLOR: #DAE8EC; SCROLLBAR-HIGHLIGHT-COLOR: #eeeeee; MARGIN-LEFT: 2px; SCROLLBAR-SHADOW-COLOR: #eeeeee; SCROLLBAR-3DLIGHT-COLOR: #eeeeee; SCROLLBAR-ARROW-COLOR: #eeeeee; SCROLLBAR-TRACK-COLOR: #eeeeee; SCROLLBAR-DARKSHADOW-COLOR: #eeeeee; SCROLLBAR-BASE-COLOR: #eeeeee } TD { MARGIN-TOP: 0px; SCROLLBAR-FACE-COLOR: #ffffff; SCROLLBAR-HIGHLIGHT-COLOR: #eeeeee; MARGIN-LEFT: 2px; SCROLLBAR-SHADOW-COLOR: #eeeeee; SCROLLBAR-3DLIGHT-COLOR: #eeeeee; SCROLLBAR-ARROW-COLOR: #eeeeee; SCROLLBAR-TRACK-COLOR: #eeeeee; SCROLLBAR-DARKSHADOW-COLOR: #eeeeee; SCROLLBAR-BASE-COLOR: #eeeeee }
		.menuList{margin-left: 0px;border: 0px solid;height: 50px!important;line-height: 50px!important;margin-top: 0px!important;background-color: #F4F4F4;}
		a:link{color:#000;}
        a:HOVER {color:#000;}
        .menuList:hover{background-color: #fff;} 
        .bgimg{ background: url("../public/images/bar_bg.png") no-repeat scroll right center transparent;}
        .bgimg:hover{ background: url("../public/images/bar_bghover.png") no-repeat scroll right center transparent;}
        .menuList_1{height:32px;line-height: 32px;padding-left: 16px;color: #000;}
        .menuList_1:hover{background-color: #FEFEDF;} 
		</style>
	</head>
	<body style="s">
		<div style="width:210px;overflow-x:hidden;overflow-y:auto;">
			<div style="height: 40px; width: 100%; margin-top: 0px;background-color: #699D08;">
					<div style="height: 40px; width: 97%; float: right;background-color: #000;">
						<div style="height: 40px;color:#fff; font-weight: bolder; float: left; margin-left: 20px; line-height: 40px; overflow: hidden;">
							<s:iterator value="#session.functionRightList">
								<s:if test="functionRightId == #parameters.t[0]">
									<s:property value="name" />
								</s:if>
								<s:if test="#parameters.t[0] == ''">
									<s:if test="functionRightId == 1">
										<s:property value="name" />
									</s:if>
								</s:if>
							</s:iterator>
						</div>
					</div>
				</div>
				<s:iterator value="#session.functionRightList" id="fr2" status="st">
					<s:if test="functionRight.functionRightId == #parameters.t[0]">
						<div style=" height: 50px;border-bottom:1px solid #DEDEDE;">
							<a href="<s:property value="path" />" target="content2" onclick="show('<s:property value="#st.count"/>','<s:property value='#session.functionRightList.size'/>')">
								<div class="menuList" id="menuList<s:property value="#st.count"/>">
									<div id="menuName<s:property value="#st.count"/>" class="bgimg" style="margin-top: 0px;border: 0px solid;z-index: 3;margin-left: 30px;font-family: '微软雅黑';font-size: 15px;color:#000;">
										<s:property value="name" />
									</div>
								</div>
							</a>
						</div>
						<div id="menu<s:property value="#st.count"/>"  style="display:none;background-color:rgba(240,243,245,0.4);">
							<s:iterator value="#session.functionRightList" status="st" id="fr3">
								<s:if test="#fr2.functionRightId==#fr3.functionRight.functionRightId">
									<s:if test="level == '3'.toString()">
									    <a href="<s:property value='path'/>" target="content2" >
											<div class="menuList_1" id="<s:property value="#fr3.functionRightId"/>" >
												<img src="../public/images/z-top.gif" align="absmiddle" border="0" height='30'>
												<s:property value="name" />
											</div>
										</a>
									</s:if>
								</s:if>
							</s:iterator>
						</div>
					</s:if>
					<s:if test="#parameters.t[0] == ''">
						<s:if test="functionRight.functionRightId == 1">
							<div style="background: url('../public/images/menu1.png'); height: 33px;">
								<div style="margin-left: 15px;border: 0px solid;height: 33px!important;line-height: 33px!important;height: 20px;line-height: 20px;margin-top: 0px!important;margin-top: 8px;">
									<div style="margin-top: 1px;border: 0px solid;z-index: 3;">
										<a href="<s:property value="path" />" target="content2" onclick="show('<s:property value="#st.count"/>','<s:property value='#session.functionRightList.size'/>')">
										<img src="../public/images/tb2.png" align="absmiddle" border="0" id="img<s:property value="#st.count"/>" />
										<s:property value="name" />
									</a>
									</div>
								</div>
							</div>
							<div id="menu<s:property value="#st.count"/>" style="display:none;background-color:rgba(240,243,245,0.4);">
								<s:iterator value="#session.functionRightList" status="st" id="fr3">
									<s:if test="#fr2.functionRightId==#fr3.functionRight.functionRightId">
										<s:if test="level == '3'.toString()">
											<a href="<s:property value='path'/>" target="content2">
												<div class="menuList_1" id="<s:property value="#fr3.functionRightId"/>">
													<img src="../public/images/z-top.gif" align="absmiddle" border="0"  height='30'>
													<s:property value="name" />
												</div>
											</a>
										</s:if>
									</s:if>
								</s:iterator>
							</div>
						</s:if>
					</s:if>
				</s:iterator>
		</div>	
	</body>
</html>