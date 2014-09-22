var HuCalendar = function() {
	var a = this;
	var b = null;
	this.activeMonth = 5;
	this.storeValues = {
		showValSuffix : "ShowVal",
		calendarContainerSuffix : "Calendar",
		inputHidenSuffix : "InputHiddenCalendar",
		dateSuffix : "Date",
		selectDaySuffix : "SelectDay",
		relateCalendar : "",
		secondCalendarTag : "",
		calendarType : "",
		compatibility : true
	};
	this.create = function(e, f, u) {
		var x = [ "一", "二", "三", "四", "五", "六", "日" ];
		var t = 1;
		var o = new Date();
		var C = o.getFullYear();
		var c = o.getMonth() + 1;
		var j = o.getDate();
		var g = "";
		var D = this.addMonths(new Date(), this.activeMonth);
		if (this.storeValues.relateCalendar == ""
				&& this.storeValues.calendarType == "hotel") {
			D = this.addDays(D, -1)
		}
		if (this.storeValues.relateCalendar != "") {
			g = $(
					"#" + this.storeValues.relateCalendar
							+ this.storeValues.showValSuffix).html();
			if (g != "" && this.storeValues.calendarType != "") {
				var v = this.addDays(this.parseTime(g, "yyyy-MM-dd"), 1);
				if (v.getTime() > D.getTime()) {
					v = D
				}
				g = this.formatDate(v.getFullYear(), v.getMonth() + 1, v
						.getDate())
			}
		}
		if (f != undefined && u != undefined) {
			o.setFullYear(f, u - 1, 1)
		} else {
			f = C;
			u = c
		}
		var I = e.substring(0, e.length
				- this.storeValues.calendarContainerSuffix.length);
		var n = this.getEndDateOfMonth(f, u);
		var z = this.offset(f, u, 1);
		var y = this.offset(f, u, n);
		var p = this.weeks(f, u);
		var m = $("#" + e).offset();
		var F = $("<table></table>");
		var E = $('<span style="cursor:pointer">&lt;&lt;</span>').click(
				function() {
					a.prevMonth(e, f, u)
				});
		var q = $('<span style="cursor:pointer">&gt;&gt;</span>').click(
				function() {
					a.nextMonth(e, f, u)
				});
		var l = $("<thead></head>");
		var w = $("<tr></tr>");
		var G = $('<td colspan="7"></td>');
		G.append($('<div class="prev"><div>').append(E));
		G.append($('<div class="next"><div>').append(q));
		G.append("<h5>" + f + "年" + u + "月</h5>");
		l.append(w.append(G));
		var s = $("<tbody></tbody>");
		var H = $("<tr></tr>");
		for ( var r in x) {
			H.append("<th>" + x[r] + "</th>")
		}
		s.append(H);
		for ( var B = 0; B < p; B++) {
			var d = $("<tr></tr>");
			if (B == 0) {
				if (z == 0) {
					for ( var A = 0; A < 6; A++) {
						d.append("<td>&nbsp;</td>")
					}
					d.append(this.append(f, u, t, C, c, j, I, g, D));
					t++
				} else {
					for ( var A = 0; A < z - 1; A++) {
						d.append("<td>&nbsp;</td>")
					}
					for ( var A = z - 1; A < 7; A++) {
						d.append(this.append(f, u, t, C, c, j, I, g, D));
						t++
					}
				}
			} else {
				if (B == p - 1) {
					if (y == 0) {
						for ( var A = n - 6; A <= n; A++) {
							d.append(this.append(f, u, A, C, c, j, I, g, D))
						}
					} else {
						for ( var A = 0; A < y; A++) {
							d.append(this.append(f, u, t, C, c, j, I, g, D));
							t++
						}
						for ( var A = y; A < 7; A++) {
							d.append("<td>&nbsp;</td>")
						}
					}
				} else {
					var h = t + 7;
					while (t < h) {
						d.append(this.append(f, u, t, C, c, j, I, g, D));
						t++
					}
				}
			}
			s.append(d)
		}
		F.append(l).append(s);
		return F
	};
	this.addMonths = function(d, c) {
		d.setMonth(d.getMonth() + c);
		return d
	};
	this.addDays = function(d, c) {
		d.setDate(d.getDate() + c);
		return d
	};
	this.weeks = function(e, d) {
		var g = this.getEndDateOfMonth(e, d);
		var c = this.offset(e, d, 1);
		if (c == 0) {
			var f = g - 1;
			return parseInt(f / 7) + 1 + (f % 7 == 0 ? 0 : 1)
		} else {
			var f = g - (8 - c);
			return parseInt(f / 7) + 1 + (f % 7 == 0 ? 0 : 1)
		}
	};
	this.append = function(e, l, i, p, c, h, t, f, q) {
		var j = "";
		var o = false;
		var m = this.storeValues[t + this.storeValues.selectDaySuffix];
		if (m != undefined) {
			var n = m.split("-");
			if (n[0] == e && n[1] == l && n[2] == i) {
				o = true
			}
		}
		var g = false;
		if (f != "") {
			var s = f.split("-");
			var k = s[0];
			var d = parseInt(s[1], 10);
			var r = parseInt(s[2], 10);
			if (this.timeCompare(k, d, r, e, l, i) > 0) {
				g = true
			}
		}
		if (this.timeCompare(q.getFullYear(), q.getMonth() + 1, q.getDate(), e,
				l, i) < 0) {
			g = true
		}
		if (a.dateChange) {
			g = false
		}
		if (this.timeCompare(e, l, i, p, c, h) > 0) {
			j = $("<td></td>").bind("mousedown", function() {
				a.selectDay(this, e, l, i, t)
			});
			if (o) {
				if (g) {
					j.attr("class", "disable active")
				} else {
					j.attr("class", "active")
				}
			} else {
				if (g) {
					j.attr("class", "disable")
				}
			}
		} else {
			if (this.timeCompare(e, l, i, p, c, h) == 0) {
				j = $("<td></td>").bind("mousedown", function() {
					a.selectDay(this, e, l, i, t)
				});
				if (o) {
					if (g) {
						j.attr("class", "disable active")
					} else {
						j.attr("class", "active")
					}
				} else {
					if (g) {
						j.attr("class", "disable")
					}
				}
			} else {
				j = $("<td></td>");
				if (a.dateChange) {
					j.bind("mousedown", function() {
						a.selectDay(this, e, l, i, t)
					});
					if (o) {
						j.attr("class", "active")
					}
				} else {
					if (o) {
						j.attr("class", "disable active")
					} else {
						j.attr("class", "disable")
					}
				}
			}
		}
		j.attr("data-day", e + "-" + l + "-" + i);
		j.append(i);
		return j
	};
	this.selectDay = function(l, f, i, k, m) {
		if ($(l).attr("class") != "disable"
				&& $(l).attr("class") != "disable active") {
			var g = m + this.storeValues.selectDaySuffix;
			var j = this.storeValues[g];
			if (j != undefined || j != null) {
				var c = j.split("-");
				var d = c[0] + "-" + c[1] + "-" + c[2];
				$("td[data-day='" + d + "']").removeClass("active");
				this.storeValues[g] = null
			}
			var e = f + "-" + i + "-" + k;
			$(l).addClass("active");
			var h = this.formatDate(f, i, k);
			$("#" + m + this.storeValues.showValSuffix).html(h);
			$("#" + m + this.storeValues.inputHidenSuffix).val(h);
			this.storeValues[g] = h;
			this.hide(m, (m + this.storeValues.calendarContainerSuffix));
			if (this.storeValues.secondCalendarTag != "") {
				this
						.setSecondCalendarVal(
								this.storeValues.secondCalendarTag, e)
			}
		}
		if (!a.storeValues.compatibility) {
			setTimeout(scrollTo, 500, 0, 1)
		}
		if ($(l).attr("class") != "disable" && a.dateChange) {
			a.dateChange()
		}
	};
	this.hide = function(d, c) {
		$("#" + d + this.storeValues.dateSuffix).removeClass("col").addClass(
				"exp");
		$("#" + c).slideUp(500, function() {
			$("#" + c).empty()
		})
	};
	this.offset = function(f, c, e) {
		return new Date(f, c - 1, e).getDay()
	};
	this.callContainer = function(e, g, f, d) {
		var c = this.create(e, g, f);
		if (d != undefined) {
			$("#" + e).empty().append(c)
		} else {
			$("#" + e).empty().hide();
			setTimeout(function() {
				$("#" + e).append(c);
				setTimeout(function() {
					$("#" + e).slideDown(200)
				}, 200, e)
			}, 100, e)
		}
	};
	this.timeCompare = function(h, g, f, d, e, c) {
		if(isQuery=="1"){return 0;}
		if (h > d) {
			return 1
		} else {
			if (h == d) {
				if (g > e) {
					return 1
				} else {
					if (g == e) {
						if (f > c) {
							return 1
						} else {
							if (f == c) {
								return 0
							} else {
								return -1
							}
						}
					} else {
						return -1
					}
				}
			} else {
				return -1
			}
		}
	};
	this.setSecondCalendarVal = function(m, k) {
		if(isQuery=="1"){return ;}
		var h = k.split("-");
		var c = $("#" + m + this.storeValues.showValSuffix);
		var f = c.html();
		var g = -1;
		if (f == "") {
			g = 1
		} else {
			var e = f.split("-");
			g = this.timeCompare(parseInt(h[0]), parseInt(h[1]),
					parseInt(h[2]), parseInt(e[0]), parseInt(e[1]),
					parseInt(e[2]))
		}
		if (g >= 0) {
			var l;
			if (this.storeValues.calendarType == "hotel") {
				l = this.addDays(this.parseTime(k, "yyyy-MM-dd"), 1)
			} else {
				l = this.addDays(this.parseTime(k, "yyyy-MM-dd"), 1)
			}
			var j = this.addMonths(new Date(), this.activeMonth);
			if (l.getTime() > j.getTime()) {
				l = j
			}
			var i = this.formatDate(l.getFullYear(), l.getMonth() + 1, l
					.getDate());
			c.html(i);
			$("#" + m + this.storeValues.inputHidenSuffix).val(i)
		}
	};
	this.parseTime = function(e, d) {
		var c = new Date();
		if (d == "yyyy-MM-dd") {
			var f = e.split("-");
			c.setFullYear(parseInt(f[0]));
			c.setMonth(parseInt(f[1], 10) - 1);
			c.setDate(parseInt(f[2], 10))
		}
		return c
	};
	this.getEndDateOfMonth = function(e, g) {
		if (e == undefined && g == undefined) {
			var c = new Date();
			e = c.getFullYear();
			g = c.getMonth() + 1
		}
		var h = e;
		var d = g++;
		if (g > 12) {
			d -= 12;
			h++
		}
		var f = new Date(h, d, 1);
		return (new Date(f.getTime() - 1000 * 60 * 60 * 24)).getDate()
	};
	this.init = function(h, f, d, g, e) {
		this.activeMonth = d;
		this.storeValues.calendarType = g;
		this.storeValues.compatibility = e;
		var c = f.indexOf("_First");
		if (c != -1) {
			this.storeValues.secondCalendarTag = f.substring(0, c)
		} else {
			this.storeValues.relateCalendar = f
		}
		$("#" + h + a.storeValues.dateSuffix)
				.click(
						function() {
							var n = $(this);
							var s = n.attr("data-tag")
									+ a.storeValues.calendarContainerSuffix;
							if (n.hasClass("col")) {
								a.hide(h, s)
							} else {
								var l = true;
								if (!a.storeValues.compatibility) {
									if ("" != a.storeValues.secondCalendarTag) {
										var j = $("#"
												+ a.storeValues.secondCalendarTag
												+ "DateDt");
										if (j) {
											if (!j.hasClass("hide")) {
												j.addClass("hide");
												l = false
											}
										}
									}
									var p = $("#keywordsDt");
									if (p) {
										p.addClass("hide")
									}
									$("#searchArea").addClass("hide");
									$("#navigator").addClass("hide")
								}
								a
										.hide(
												h,
												a.storeValues.calendarContainerSuffix);
								n.removeClass("exp").addClass("col");
								var o = $.trim($(
										"#" + n.attr("data-tag")
												+ a.storeValues.showValSuffix)
										.html());
								if (o != "") {
									var k = o.split("-");
									var m = a.formatDate(k[0], k[1], k[2]);
									a.storeValues[n.attr("data-tag")
											+ a.storeValues.selectDaySuffix] = m;
									a.callContainer(s, k[0], k[1]);
									$(
											"#"
													+ n.attr("data-tag")
													+ a.storeValues.inputHidenSuffix)
											.val(m)
								} else {
									a.callContainer(s)
								}
								if (!a.storeValues.compatibility) {
									var q = 500;
									setTimeout(
											function() {
												if (!l
														&& "" != a.storeValues.secondCalendarTag) {
													var u = $("#"
															+ a.storeValues.secondCalendarTag
															+ "DateDt");
													if (u) {
														if (u.hasClass("hide")) {
															u
																	.removeClass("hide")
														}
													}
												}
												var t = $("#keywordsDt");
												if (t) {
													t.removeClass("hide")
												}
												$("#searchArea").removeClass(
														"hide");
												$("#navigator").removeClass(
														"hide");
												setTimeout(scrollTo, 100, 10,
														165)
											}, q, l)
								}
							}
							if (a.storeValues.secondCalendarTag != "") {
								var i = $("#" + a.storeValues.secondCalendarTag
										+ a.storeValues.dateSuffix);
								if (i.hasClass("col")) {
									i.removeClass("col").addClass("exp");
									a
											.hide(
													a.storeValues.secondCalendarTag,
													a.storeValues.secondCalendarTag
															+ a.storeValues.calendarContainerSuffix)
								}
							} else {
								if (a.storeValues.relateCalendar != "") {
									var r = $("#"
											+ a.storeValues.relateCalendar
											+ a.storeValues.dateSuffix);
									if (r.hasClass("col")) {
										r.removeClass("col").addClass("exp");
										a
												.hide(
														a.storeValues.relateCalendar,
														a.storeValues.relateCalendar
																+ a.storeValues.calendarContainerSuffix)
									}
								}
							}
						})
	};
	this.nextMonth = function(c, e, d) {
		if (d == 12) {
			e++;
			d = 1
		} else {
			d++
		}
		this.callContainer(c, e, d, "right")
	};
	this.prevMonth = function(c, e, d) {
		if (d == 1) {
			e--;
			d = 12
		} else {
			d--
		}
		this.callContainer(c, e, d, "left")
	};
	this.formatDate = function(e, d, c) {
		d = parseInt(d, 10);
		c = parseInt(c, 10);
		return e + "-" + (d < 10 ? "0" + d : d) + "-" + (c < 10 ? "0" + c : c)
	};
};