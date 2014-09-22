
function bind(o,ev,fn){
	if (document.all)o.attachEvent('on'+ev,fn);
	else o.addEventListener(ev,fn,false);
}
function setCookie(name,value,time){
	var exp = new Date();
	exp.setTime(exp.getTime() + 1*24*60*60*1000);
	//document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function getCookie(name){
	var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)");
	if(arr=document.cookie.match(reg)) return unescape(arr[2]);
	else return null;
}
function Move_autosetdef(moveobj){
	var tripTypeTit = new Array("1","2");
	var bNum =0 ,mNum =50 , iNum = 2 , icolength = 6;
	//分别 bNum 起始距离距moveobj内侧左部的长度 ，mNum 单位长度，iNum 单位数 0-1-2-3，icolength 图标距离的1/2。
	var eNum = bNum + mNum * (iNum-1);
	var $D = new Function('str','return document.getElementById(str);');
	var $event = new Function('e','return (!e)?window.event:e;');
	var checknowleft = function(z){return ((z<bNum)?bNum:((z>eNum)?eNum:(((z-bNum)%mNum>200)?z+mNum-(z-bNum)%mNum:z-(z-bNum)%mNum)));}
	var nowseat = 0,drag_=false;
	var Move_autoset = function(iNum){
		$D(moveobj).getElementsByTagName("div")[0].style.left = iNum*mNum+bNum+"%";
		/*$D("login-tit").innerHTML = logintit[iNum];*/
		$D("tripType").value = iNum+1;//向hidden的login-type里添加参数值
		if(iNum==0){
			$(".oneOrTwoTitle").text("往返");
			$(".imgp").text("单程");
			$(".oneOrTwoTitle").removeClass("changecolor");
			$("#moveico div").css('position','relative');
			$("#timeChoose").show(100);
			$(".wangfanTime").hide(100);
		}else if(iNum==1){
			$(".oneOrTwoTitle").text("单程");
			$(".imgp").text("往返");
			$(".oneOrTwoTitle").addClass("changecolor");
			$("#moveico div").css('position','absolute');
			$(".wangfanTime").show(100);
			$("#timeChoose").hide(100);
		}
	} ;
	nowseat = (getCookie("nowseat")==null)?0:getCookie("nowseat");
	Move_autoset(nowseat);
	$D(moveobj).getElementsByTagName("div")[0].onmouseover = function(){
		var x,y,z;
		this.onmousedown=function(e){
			drag_=true;
			with(this){
				var temp0=style.left.replace("%","")*1,temp1=offsetLeft,temp2=offsetTop;
				x=$event(e).clientX;
				y=$event(e).clientY;
				z=temp0;
				document.onmousemove=function(e){
					//this.selection.empty();
					if(!drag_)return false;
					with(this){
					z = temp0 + $event(e).clientX-x;
					z = (z<bNum)?bNum:((z>eNum)?eNum:z);
					style.left=z+"%";
				    }
		        };
				document.onmouseup=function(e){
					drag_ = false;
					var nowleft = checknowleft(z);
					nowseat = (nowleft-bNum)/mNum;
					Move_autoset(nowseat);
					setCookie('nowseat',(nowleft-bNum)/mNum);
				};
		    }
		};
	};
	$D(moveobj).onmouseover = function(){
		this.onmousedown=function(e){
		if(drag_)return false;
			with(this){
				var icoobj = getElementsByTagName("div")[0];
				var temp0 = icoobj.offsetLeft;
				var x=$event(e).clientX;
				var nowleft = checknowleft(bNum+mNum*nowseat+(x-temp0)-icolength);
				document.onmouseup=function(e){
					nowseat = (nowleft-bNum)/mNum;
					Move_autoset(nowseat);
					setCookie('nowseat',(nowleft-bNum)/mNum);
				};
			}
		};
	};
}

