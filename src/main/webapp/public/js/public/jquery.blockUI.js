﻿(function() {
	function a(j) {
		if (/1\.(0|1|2)\.(0|1|2)/.test(j.fn.jquery) || /^1.1/.test(j.fn.jquery)) {
			alert("blockUI requires jQuery v1.2.3 or later!  You are using v"
					+ j.fn.jquery);
			return
		}
		j.fn._fadeIn = j.fn.fadeIn;
		var d = function() {
		};
		var k = document.documentMode || 0;
		var f = j.browser.msie && ((j.browser.version < 8 && !k) || k < 8);
		var g = j.browser.msie && /MSIE 6.0/.test(navigator.userAgent) && !k;
		j.blockUI = function(q) {
			e(window, q)
		};
		j.unblockUI = function(q) {
			i(window, q)
		};
		j.growlUI = function(u, s, t, q) {
			var r = j('<div class="growlUI"></div>');
			if (u) {
				r.append("<h1>" + u + "</h1>")
			}
			if (s) {
				r.append("<h2>" + s + "</h2>")
			}
			if (t == undefined) {
				t = 3000
			}
			j.blockUI( {
				message : r,
				fadeIn : 700,
				fadeOut : 1000,
				centerY : false,
				timeout : t,
				showOverlay : false,
				onUnblock : q,
				css : j.blockUI.defaults.growlCSS
			})
		};
		j.fn.block = function(r) {
			var q = j.extend( {}, j.blockUI.defaults, r || {});
			this.each(function() {
				var s = j(this);
				if (q.ignoreIfBlocked && s.data("blockUI.isBlocked")) {
					return
				}
				s.unblock( {
					fadeOut : 0
				})
			});
			return this.each(function() {
				if (j.css(this, "position") == "static") {
					this.style.position = "relative"
				}
				if (j.browser.msie) {
					this.style.zoom = 1
				}
				e(this, r)
			})
		};
		j.fn.unblock = function(q) {
			return this.each(function() {
				i(this, q)
			})
		};
		j.blockUI.version = 2.42;
		j.blockUI.defaults = {
			message : "正在处理，请等待。。。",
			title : null,
			draggable : true,
			theme : false,
			css : {
				padding : 0,
				margin : 0,
				width : "30%",
				top : "40%",
				left : "35%",
				textAlign : "center",
				font: "16px",
				color : "#000",
				border : "3px solid #aaa",
				backgroundColor : "#fff",
				cursor : "wait"
			},
			themedCSS : {
				width : "30%",
				top : "40%",
				left : "35%"
			},
			overlayCSS : {
				backgroundColor : "#000",
				opacity : 0.6,
				cursor : "wait"
			},
			growlCSS : {
				width : "350px",
				top : "10px",
				left : "",
				right : "10px",
				border : "none",
				padding : "5px",
				opacity : 0.6,
				cursor : "default",
				color : "#fff",
				backgroundColor : "#000",
				"-webkit-border-radius" : "10px",
				"-moz-border-radius" : "10px",
				"border-radius" : "10px"
			},
			iframeSrc : /^https/i.test(window.location.href || "") ? "javascript:false"
					: "about:blank",
			forceIframe : false,
			baseZ : 1000,
			centerX : true,
			centerY : true,
			allowBodyStretch : true,
			bindEvents : true,
			constrainTabKey : true,
			fadeIn : 200,
			fadeOut : 400,
			timeout : 0,
			showOverlay : true,
			focusInput : true,
			applyPlatformOpacityRules : true,
			onBlock : null,
			onUnblock : null,
			quirksmodeOffsetHack : 4,
			blockMsgClass : "blockMsg",
			ignoreIfBlocked : false
		};
		var c = null;
		var h = [];
		function e(u, G) {
			var D, O;
			var B = (u == window);
			var x = (G && G.message !== undefined ? G.message : undefined);
			G = j.extend( {}, j.blockUI.defaults, G || {});
			if (G.ignoreIfBlocked && j(u).data("blockUI.isBlocked")) {
				return
			}
			G.overlayCSS = j.extend( {}, j.blockUI.defaults.overlayCSS,
					G.overlayCSS || {});
			D = j.extend( {}, j.blockUI.defaults.css, G.css || {});
			O = j.extend( {}, j.blockUI.defaults.themedCSS, G.themedCSS || {});
			x = x === undefined ? G.message : x;
			if (B && c) {
				i(window, {
					fadeOut : 0
				})
			}
			if (x && typeof x != "string" && (x.parentNode || x.jquery)) {
				var J = x.jquery ? x[0] : x;
				var Q = {};
				j(u).data("blockUI.history", Q);
				Q.el = J;
				Q.parent = J.parentNode;
				Q.display = J.style.display;
				Q.position = J.style.position;
				if (Q.parent) {
					Q.parent.removeChild(J)
				}
			}
			j(u).data("blockUI.onUnblock", G.onUnblock);
			var C = G.baseZ;
			var N = (j.browser.msie || G.forceIframe) ? j('<iframe class="blockUI" style="z-index:'
					+ (C++)
					+ ';display:none;border:none;margin:0;padding:0;position:absolute;width:100%;height:100%;top:0;left:0" src="'
					+ G.iframeSrc + '"></iframe>')
					: j('<div class="blockUI" style="display:none"></div>');
			var M = G.theme ? j('<div class="blockUI blockOverlay ui-widget-overlay" style="z-index:' + (C++) + ';display:none"></div>')
					: j('<div class="blockUI blockOverlay" style="z-index:' + (C++) + ';display:none;border:none;margin:0;padding:0;width:100%;height:100%;top:0;left:0"></div>');
			var L, H;
			if (G.theme && B) {
				H = '<div class="blockUI '
						+ G.blockMsgClass
						+ ' blockPage ui-dialog ui-widget ui-corner-all" style="z-index:'
						+ (C + 10)
						+ ';display:none;position:fixed"><div class="ui-widget-header ui-dialog-titlebar ui-corner-all blockTitle">'
						+ (G.title || "&nbsp;")
						+ '</div><div class="ui-widget-content ui-dialog-content"></div></div>'
			} else {
				if (G.theme) {
					H = '<div class="blockUI '
							+ G.blockMsgClass
							+ ' blockElement ui-dialog ui-widget ui-corner-all" style="z-index:'
							+ (C + 10)
							+ ';display:none;position:absolute"><div class="ui-widget-header ui-dialog-titlebar ui-corner-all blockTitle">'
							+ (G.title || "&nbsp;")
							+ '</div><div class="ui-widget-content ui-dialog-content"></div></div>'
				} else {
					if (B) {
						H = '<div class="blockUI ' + G.blockMsgClass
								+ ' blockPage" style="z-index:' + (C + 10)
								+ ';display:none;position:fixed"></div>'
					} else {
						H = '<div class="blockUI ' + G.blockMsgClass
								+ ' blockElement" style="z-index:' + (C + 10)
								+ ';display:none;position:absolute"></div>'
					}
				}
			}
			L = j(H);
			if (x) {
				if (G.theme) {
					L.css(O);
					L.addClass("ui-widget-content")
				} else {
					L.css(D)
				}
			}
			if (!G.theme
					&& (!G.applyPlatformOpacityRules || !(j.browser.mozilla && /Linux/
							.test(navigator.platform)))) {
				M.css(G.overlayCSS)
			}
			M.css("position", B ? "fixed" : "absolute");
			if (j.browser.msie || G.forceIframe) {
				N.css("opacity", 0)
			}
			var A = [ N, M, L ], P = B ? j("body") : j(u);
			j.each(A, function() {
				this.appendTo(P)
			});
			if (G.theme && G.draggable && j.fn.draggable) {
				L.draggable( {
					handle : ".ui-dialog-titlebar",
					cancel : "li"
				})
			}
			var w = f
					&& (!j.boxModel || j("object,embed", B ? null : u).length > 0);
			if (g || w) {
				if (B && G.allowBodyStretch && j.boxModel) {
					j("html,body").css("height", "100%")
				}
				if ((g || !j.boxModel) && !B) {
					var F = n(u, "borderTopWidth"), K = n(u, "borderLeftWidth");
					var y = F ? "(0 - " + F + ")" : 0;
					var E = K ? "(0 - " + K + ")" : 0
				}
				j
						.each(
								[ N, M, L ],
								function(t, T) {
									var z = T[0].style;
									z.position = "absolute";
									if (t < 2) {
										B ? z
												.setExpression(
														"height",
														"Math.max(document.body.scrollHeight, document.body.offsetHeight) - (jQuery.boxModel?0:"
																+ G.quirksmodeOffsetHack
																+ ') + "px"')
												: z
														.setExpression(
																"height",
																'this.parentNode.offsetHeight + "px"');
										B ? z
												.setExpression(
														"width",
														'jQuery.boxModel && document.documentElement.clientWidth || document.body.clientWidth + "px"')
												: z
														.setExpression("width",
																'this.parentNode.offsetWidth + "px"');
										if (E) {
											z.setExpression("left", E)
										}
										if (y) {
											z.setExpression("top", y)
										}
									} else {
										if (G.centerY) {
											if (B) {
												z
														.setExpression(
																"top",
																'(document.documentElement.clientHeight || document.body.clientHeight) / 2 - (this.offsetHeight / 2) + (blah = document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + "px"')
											}
											z.marginTop = 0
										} else {
											if (!G.centerY && B) {
												var R = (G.css && G.css.top) ? parseInt(G.css.top)
														: 0;
												var S = "((document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop) + "
														+ R + ') + "px"';
												z.setExpression("top", S)
											}
										}
									}
								})
			}
			if (x) {
				if (G.theme) {
					L.find(".ui-widget-content").append(x)
				} else {
					L.append(x)
				}
				if (x.jquery || x.nodeType) {
					j(x).show()
				}
			}
			if ((j.browser.msie || G.forceIframe) && G.showOverlay) {
				N.show()
			}
			if (G.fadeIn) {
				var I = G.onBlock ? G.onBlock : d;
				var r = (G.showOverlay && !x) ? I : d;
				var q = x ? I : d;
				if (G.showOverlay) {
					M._fadeIn(G.fadeIn, r)
				}
				if (x) {
					L._fadeIn(G.fadeIn, q)
				}
			} else {
				if (G.showOverlay) {
					M.show()
				}
				if (x) {
					L.show()
				}
				if (G.onBlock) {
					G.onBlock()
				}
			}
			m(1, u, G);
			if (B) {
				c = L[0];
				h = j(":input:enabled:visible", c);
				if (G.focusInput) {
					setTimeout(p, 20)
				}
			} else {
				b(L[0], G.centerX, G.centerY)
			}
			if (G.timeout) {
				var v = setTimeout(function() {
					B ? j.unblockUI(G) : j(u).unblock(G)
				}, G.timeout);
				j(u).data("blockUI.timeout", v)
			}
		}
		function i(t, u) {
			var s = (t == window);
			var r = j(t);
			var v = r.data("blockUI.history");
			var w = r.data("blockUI.timeout");
			if (w) {
				clearTimeout(w);
				r.removeData("blockUI.timeout")
			}
			u = j.extend( {}, j.blockUI.defaults, u || {});
			m(0, t, u);
			if (u.onUnblock === null) {
				u.onUnblock = r.data("blockUI.onUnblock");
				r.removeData("blockUI.onUnblock")
			}
			var q;
			if (s) {
				q = j("body").children().filter(".blockUI").add(
						"body > .blockUI")
			} else {
				q = j(".blockUI", t)
			}
			if (s) {
				c = h = null
			}
			if (u.fadeOut) {
				q.fadeOut(u.fadeOut);
				setTimeout(function() {
					l(q, v, u, t)
				}, u.fadeOut)
			} else {
				l(q, v, u, t)
			}
		}
		function l(q, t, s, r) {
			q.each(function(u, v) {
				if (this.parentNode) {
					this.parentNode.removeChild(this)
				}
			});
			if (t && t.el) {
				t.el.style.display = t.display;
				t.el.style.position = t.position;
				if (t.parent) {
					t.parent.appendChild(t.el)
				}
				j(r).removeData("blockUI.history")
			}
			if (typeof s.onUnblock == "function") {
				s.onUnblock(r, s)
			}
		}
		function m(q, u, v) {
			var t = u == window, s = j(u);
			if (!q && (t && !c || !t && !s.data("blockUI.isBlocked"))) {
				return
			}
			s.data("blockUI.isBlocked", q);
			if (!v.bindEvents || (q && !v.showOverlay)) {
				return
			}
			var r = "mousedown mouseup keydown keypress";
			q ? j(document).bind(r, v, o) : j(document).unbind(r, o)
		}
		function o(u) {
			if (u.keyCode && u.keyCode == 9) {
				if (c && u.data.constrainTabKey) {
					var s = h;
					var r = !u.shiftKey && u.target === s[s.length - 1];
					var q = u.shiftKey && u.target === s[0];
					if (r || q) {
						setTimeout(function() {
							p(q)
						}, 10);
						return false
					}
				}
			}
			var t = u.data;
			if (j(u.target).parents("div." + t.blockMsgClass).length > 0) {
				return true
			}
			return j(u.target).parents().children().filter("div.blockUI").length == 0
		}
		function p(q) {
			if (!h) {
				return
			}
			var r = h[q === true ? h.length - 1 : 0];
			if (r) {
				r.focus()
			}
		}
		function b(w, q, A) {
			var z = w.parentNode, v = w.style;
			var r = ((z.offsetWidth - w.offsetWidth) / 2)
					- n(z, "borderLeftWidth");
			var u = ((z.offsetHeight - w.offsetHeight) / 2)
					- n(z, "borderTopWidth");
			if (q) {
				v.left = r > 0 ? (r + "px") : "0"
			}
			if (A) {
				v.top = u > 0 ? (u + "px") : "0"
			}
		}
		function n(q, r) {
			return parseInt(j.css(q, r)) || 0
		}
	}
	if (typeof define === "function" && define.amd && define.amd.jQuery) {
		define( [ "jquery" ], a)
	} else {
		a(jQuery)
	}
})();