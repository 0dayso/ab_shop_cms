var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}

function change() {
			var t  = new Date().getTime();
			var right = document.getElementsByName('menuValue');
			for(var i = 0; i < right.length;i++){
				if(right[0].value == 1 && right[0].checked){
					document.getElementById('right_1').style.display = 'block';
					document.getElementById('right_2').style.display = 'none';
					document.getElementById('right_3').style.display = 'none';
				}
				if(right[1].value == 2 && right[1].checked){
					document.getElementById('right_1').style.display = 'none';
					document.getElementById('right_2').style.display = 'block';
					document.getElementById('right_3').style.display = 'none';
					
					$.get("menu/getMenuData?random=" + t, {parentId: "0",functionName:"functionRight1",level:"1",js:"setValue()"},  
		 			  	function(data){
					  		document.getElementById('rightLevel1').innerHTML = data;
						}); 
					}
				if(right[2].value == 3 && right[2].checked){
					document.getElementById('right_1').style.display = 'none';
					document.getElementById('right_2').style.display = 'none';
					document.getElementById('right_3').style.display = 'block';
					
					$.get("menu/getMenuData?random=" + t, {parentId: "0",functionName:"functions",level:"1",js:"selectChange()"},  
		 			  	function(data){
					  		document.getElementById('rightLevel2').innerHTML = data;
						}); 
					$.get("menu/getMenuData?random=" + t, {parentId: 1,functionName:"functionRight2",level:"2"},  
		 			  	function(data){
					  		document.getElementById('rightLevel3').innerHTML = data;
						});			
				}
				break;
			}
		}
		function selectChange(){
			var t  = new Date().getTime();
			var obj = document.getElementById('functions').value;
			$.get("menu/getMenuData?random=" + t, {parentId:obj,functionName:"functionRight2",level:"2",js:"setValue2()"},  
 			  	function(data){
			  		document.getElementById('rightLevel3').innerHTML = data;
			});	
		}
		function setValue(){
			var functionRight1 = document.getElementById('functionRight1').value;
			document.getElementById('fr1').value = functionRight1;
		}
		
		function setValue2(){
			var functionRight2 = document.getElementById('functionRight2').value;
			document.getElementById('fr2').value = functionRight2;
		}
		
		function isNotEmpty(element, message){
			if(element.value.length == 0 || element.value.replace(/\s/g,'').length==0){
				alert(message);
				element.focus();
				return false;
			}
			return true;
		}
		function validate() {
			var right = document.getElementsByName('menuValue');
			for ( var i = 0; i < right.length; i++) {
				if (right[0].value == 1 && right[0].checked) {
					if (!isNotEmpty(document.getElementById('name1'), "一级菜单功能名称不能为空！")) {
						return false;
					}
				}
				if (right[1].value == 2 && right[1].checked) {
					if (!isNotEmpty(document.getElementById('name2'), "二级菜单功能名称不能为空！")) {
						return false;
					}
					if (!isNotEmpty(document.getElementById('path2'), "二级菜单功能路径不能为空！")) {
						return false;
					}
				}
				if (right[2].value == 3 && right[2].checked) {
					if (!isNotEmpty(document.getElementById('name3'), "三级菜单功能名称不能为空！")) {
						return false;
					}
					if (!isNotEmpty(document.getElementById('path3'), "三级菜单功能路径不能为空！")) {
						return false;
					}
					if (document.getElementById('level3').value == 0
							|| document.getElementById('level3').value == null) {
						alert("三级菜单功能级别不能为空，请选择！");
						return false;
					}
				}
			}
		}
		function checkData(){
			if (!isNotEmpty(document.getElementById('name'), "菜单功能名称不能为空！")) {
				return false;
			}
			if (!isNotEmpty(document.getElementById('path'), "菜单功能路径不能为空！")) {
				return false;
			}
		}
		
		function del(obj){
			if(confirm("确认删除吗？")){
				$.get('system/permission/permission_delete.action',{functionRightId:obj},
				function(data){
					if(data.length>0){
						alert(data);
					}else{
					    location.href=location.href;
					}				
				});
			}
		}
		
		function init() {
			hiddenBtn('system/permission/permission_edit.action','edit');
			hiddenBtn('system/permission/permission_delete.action','del');
			hiddenBtn('system/permission/permission_add.action','add');
		}