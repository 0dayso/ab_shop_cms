var empName = '${employee.name }';
if(empName == null || empName == ""){
	window.self.location = '<%=request.getContextPath()%>';
}
var objRoleName="";//原始角色名

function checkNode(root, ids) {
	var array = ids.substring(0, ids.length - 1).split(',');
	for ( var i = 0; i < array.length; i++) {
		if(document.getElementById(array[i]) != null){
			document.getElementById(array[i]).checked = root.checked;
		}
	}
}
function checkNode1(root, ids, cids) {
	var che1 = false;
	var array = ids.substring(0, ids.length - 1).split(',');
	var array1 = cids.substring(0, cids.length - 1).split(',');
	document.getElementById(array[0]).checked = root.checked;
	for ( var i = 1; i < array.length; i++) {
		document.getElementById(array[i]).checked = root.checked;
	}
	for ( var i = 0; i < array1.length; i++) {
		if(document.getElementById(array1[i]) != null){
			if (document.getElementById(array1[i]).checked) {
				che1 = true;
				break;
			}
		}
	}
	document.getElementById(array[0]).checked = che1;
}
function checkNode2(root,obj, ids, cids, ccids) {
	var che1 = false;
	var che2 = false;
	var array = ids.substring(0, ids.length - 1).split(',');//一二级节点(为两个数)
	var array1 = cids.substring(0, cids.length - 1).split(',');//一级节点，本身ID
	var array2 = ccids.substring(0, ccids.length - 1).split(',');//某个一级节点下的所有子节点ID

	for ( var i = 1; i < array1.length; i++) {
		if (document.getElementById(array1[i]).checked) {//三级权限选中（增删改查）
			che1 = true;
			break;
		}
	}
	document.getElementById(array[1]).checked = che1;//当前上一子节点选中
	for ( var i = 0; i < array2.length; i++) {
		if (document.getElementById(array2[i])!= null && document.getElementById(array2[i]).checked) {//所有节点选中一或多
			che2 = true;
			break;
		}
	}
	document.getElementById(array[0]).checked = che2;//一级权限选中
	if(obj.indexOf('表') == -1 && obj.indexOf('点卡订单') == -1 && obj.indexOf('彩票订单') == -1){
		document.getElementById(array1[1]).checked = che1;
	}
	if(obj.indexOf('短信添加')!=-1){
		document.getElementById(array1[array1.length -2]).checked = che1;
	}
}

function checkData(){
	var name=document.getElementById('name').value;
 	var departName = document.getElementById('departName').value;
 	
 	if(name.length==0 || name.replace(/\s/g,'').length==0){
		alert('角色名称不能为空！');
		document.getElementById('name').focus();
		return false;
	}
 	var nameAfter = document.getElementById("name").value.replace(/\s/g,'');
 	//当原始角色名和修改后的角色名不同时验证修改后的角色名是否存在
 	if(nameAfter!=objRoleName){
	 	if(!flag){
	 		alert("该角色名称已经 存在！");
	 		document.getElementById('name').focus();
	 		return false;
	 	}
 	}
	if(departName.length==0 || departName.replace(/\s/g,'').length==0){
		alert('部门名称不能为空！');
		document.getElementById('departName').focus();
		return false;
	}
}

function del(obj){
	if(confirm("确定删除吗?")){
		$.get('system/permission/role_delete.action?timestamp='+new Date().getTime(),{id:obj},
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
	hiddenBtn('system/permission/role_edit.action','edit');
	hiddenBtn('system/permission/role_delete.action','del');
	hiddenBtn('system/permission/role_add.action','add');
}
var flag = false;
function checkName(){
	var name = document.getElementById("name").value.replace(/\s/g,'');
	if(objRoleName!=name){
		if(name.length>0){
			$.get('system/permission/role_checkName.action?timestamp='+new Date().getTime(),{name:escape(name)},function(data){
				if(data.length>0){
					document.getElementById("result").innerHTML="<font color='red'>"+data+"</font>";
					flag = false;
				}else{
					document.getElementById("result").innerHTML="";
					flag = true;
				}
			});
		}
	}
}

function setSubmitButton(obj){
	if(flag){
		obj.style.display='none';
	}
}

$(document).ready(function(){
	objRoleName=document.getElementById("name").value.replace(/\s/g,'');
}); 
