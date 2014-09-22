$(function() {
	
	if (ifStartCityError == "error") {
		alert("请输入正确的出发城市！")
	}
	if (ifArriveCityError == "error") {
		alert("请输入正确的到达城市！")
	}
	new HuCalendar().init("start", "back_First", 6, "flight",G_compatibility);
	new HuCalendar().init("back", "start", 6, "flight", G_compatibility);
	if (GStartDate == "") {
		plusDays("startInputHiddenCalendar", 0);
		plusDays("startShowVal", 0, "0")
	}
	if (GBackDate == "") {
		plusDays("backShowVal", 0, "0");
		plusDays("backInputHiddenCalendar", 0)
	}
	
	if(GStartDate == "weekbefore"){
		plusDays("startInputHiddenCalendar", 0);
		plusDays("startShowVal", -7, "0")
	}
	if (GBackDate == "") {
		plusDays("backShowVal", 0, "0");
		plusDays("backInputHiddenCalendar", 0)
	}
	//机票预订航班查询的时候初始化日期:开始
	if (GStartDate == "queryFlight") {
		plusDays("startInputHiddenCalendar", 0);
		plusDays("startShowVal", 1, "0");
	}
	if (GBackDate == "queryFlight") {
		plusDays("backShowVal", 2, "0");
		plusDays("backInputHiddenCalendar", 0)
	}
	//:结束
	$("#oneWayFlight").bind("click", function() {
		$("#backDateDt").addClass("hide");
		$("#backDateDd").addClass("hide");
		$("#flightType").val("oneWay")
	});
	$("#roundWayFlight").bind("click", function() {
		$("#backDateDt").removeClass("hide");
		$("#backDateDd").removeClass("hide");
		$("#flightType").val("roundWay")
	});
	$("#searchSubmit").bind("click", function() {
		var d = $("#startCity");
		var c = $("#destCity");
		if (d.hasClass("infotext")) {
			d.val("")
		}
		if (c.hasClass("infotext")) {
			c.val("")
		}
		if (isEmpty("startCity") && isEmpty("destCity")) {
			alert("请输入出发和到达城市");
			d.focus();
			return false
		} else {
			if (isEmpty("startCity")) {
				alert("请输入出发城市");
				c.focus();
				return false
			} else {
				if (isEmpty("destCity")) {
					alert("请输入到达城市");
					c.focus();
					return false
				} else {
					if (d.val() == c.val()) {
						alert("出发城市和到达城市相同，请重新输入");
						d.val("");
						c.val("");
						d.focus();
						return false
					} else {
						$("#flightSearchForm").submit();
						return true
					}
				}
			}
		}
	});
	if (flightType != "") {
		$("#" + flightType + "Flight").trigger("click")
	}
	$(document.body).mousedown(
			function(c) {
				var d = c.target.id;
				if ("startCity" == c.target.id || "destCity" == c.target.id) {
					if (ScrollTop.domId != undefined && ScrollTop.domId != ""
							&& d != ScrollTop.domId) {
						$("#" + ScrollTop.domId).next().addClass("hide");
						ScrollTop.removeBottomDiv()
					}
				} else {
					b.hideAllExpendSuggest();
					a.hideAllExpendSuggest();
					if (ScrollTop.isScroll) {
						ScrollTop.fireEvent()
					} else {
						ScrollTop.removeBottomDiv()
					}
				}
				ScrollTop.isScroll = false
			})
});
$(document).ready(function() {
	setTimeout(scrollTo, 200, 0, 1)
});
var plusDays = function(c, e, d) {
	var a = new Date();
	a.setDate(a.getDate() + e);
	var b = a.getFullYear()
			+ "-"
			+ ((a.getMonth() + 1) < 10 ? "0" + (a.getMonth() + 1) : (a
					.getMonth() + 1)) + "-"
			+ (a.getDate() < 10 ? "0" + a.getDate() : a.getDate());
	if (d == "0") {
		$("#" + c).html(b)
	} else {
		$("#" + c).val(b)
	}
};