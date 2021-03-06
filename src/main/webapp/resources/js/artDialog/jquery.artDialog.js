!function(a, b, c) {
    a.noop = a.noop || function() {}
    ;
    var d, e, f, g, h = 0, i = a(b), j = a(document), k = a("html"), l = document.documentElement, m = b.VBArray && !b.XMLHttpRequest, n = "createTouch" in document && !("onmousemove" in l) || /(iPhone|iPad|iPod)/i.test(navigator.userAgent), o = "artDialog" + +new Date, p = function(b, e, f) {
        b = b || {},
        ("string" == typeof b || 1 === b.nodeType) && (b = {
            content: b,
            fixed: !n
        });
        var g, i = p.defaults, j = b.follow = 1 === this.nodeType && this || b.follow;
        for (var k in i)
            b[k] === c && (b[k] = i[k]);
        return a.each({
            ok: "yesFn",
            cancel: "noFn",
            close: "closeFn",
            init: "initFn",
            okVal: "yesText",
            cancelVal: "noText"
        }, function(a, d) {
            b[a] = b[a] !== c ? b[a] : b[d]
        }
        ),
        "string" == typeof j && (j = a(j)[0]),
        b.id = j && j[o + "follow"] || b.id || o + h,
        g = p.list[b.id],
        j && g ? g.follow(j).zIndex().focus() : g ? g.zIndex().focus() : (n && (b.fixed = !1),
        a.isArray(b.button) || (b.button = b.button ? [b.button] : []),
        e !== c && (b.ok = e),
        f !== c && (b.cancel = f),
        b.ok && b.button.push({
            name: b.okVal,
            callback: b.ok,
            focus: !0
        }),
        b.cancel && b.button.push({
            name: b.cancelVal,
            callback: b.cancel
        }),
        p.defaults.zIndex = b.zIndex,
        h++,
        p.list[b.id] = d ? d._init(b) : new p.fn._init(b))
    }
    ;
    if (p.fn = p.prototype = {
        version: "4.1.7",
        closed: !0,
        _init: function(a) {
            var e, c = this, f = a.icon;
            return f && (m ? {
                png: "icons/" + f + ".png"
            } : {
                backgroundImage: "url('" + a.path + "/skins/icons/" + f + ".png')"
            }),
            c.closed = !1,
            c.config = a,
            c.DOM = e = c.DOM || c._getDOM(),
            e.wrap.addClass(a.skin),
            e.content.css("padding", a.padding),
            c[a.show ? "show" : "hide"](!0).content(a.content, !0).size(a.width, a.height).time(a.time),
            a.follow ? c.follow(a.follow) : c.position(a.left, a.top),
            c.zIndex().focus(),
            a.lock && c.lock(),
            c._addEvent(),
            c._ie6PngFix(),
            d = null ,
            a.init && a.init.call(c, b),
            c
        },
        content: function(a) {
            var b, d, e, f, g = this, h = g.DOM, i = h.wrap[0], j = i.offsetWidth, k = i.offsetHeight, l = parseInt(i.style.left), m = parseInt(i.style.top), n = i.style.width, o = h.content, p = o[0];
            return g._elemBack && g._elemBack(),
            i.style.width = "auto",
            a === c ? p : ("string" == typeof a ? o.html(a) : a && 1 === a.nodeType && (f = a.style.display,
            b = a.previousSibling,
            d = a.nextSibling,
            e = a.parentNode,
            g._elemBack = function() {
                b && b.parentNode ? b.parentNode.insertBefore(a, b.nextSibling) : d && d.parentNode ? d.parentNode.insertBefore(a, d) : e && e.appendChild(a),
                a.style.display = f,
                g._elemBack = null 
            }
            ,
            o.html(""),
            p.appendChild(a),
            a.style.display = "block"),
            arguments[1] || (g.config.follow ? g.follow(g.config.follow) : (j = i.offsetWidth - j,
            k = i.offsetHeight - k,
            l -= j / 2,
            m -= k / 2,
            i.style.left = Math.max(l, 0) + "px",
            i.style.top = Math.max(m, 0) + "px"),
            n && "auto" !== n && (i.style.width = i.offsetWidth + "px"),
            g._autoPositionType()),
            g._ie6SelectFix(),
            g._runScript(p),
            g)
        },
        position: function(a, b) {
            var d = this
              , e = d.config
              , f = d.DOM.wrap[0]
              , g = m ? !1 : e.fixed
              , h = m && d.config.fixed
              , k = j.scrollLeft()
              , l = j.scrollTop()
              , n = g ? 0 : k
              , o = g ? 0 : l
              , p = i.width()
              , q = i.height()
              , r = f.offsetWidth
              , s = f.offsetHeight
              , t = f.style;
            return (a || 0 === a) && (d._left = -1 !== a.toString().indexOf("%") ? a : null ,
            a = d._toNumber(a, p - r),
            "number" == typeof a ? (a = h ? a += k : a + n,
            t.left = Math.max(a, n) + "px") : "string" == typeof a && (t.left = a)),
            (b || 0 === b) && (d._top = -1 !== b.toString().indexOf("%") ? b : null ,
            b = d._toNumber(b, q - s),
            "number" == typeof b ? (b = h ? b += l : b + o,
            t.top = Math.max(b, o) + "px") : "string" == typeof b && (t.top = b)),
            a !== c && b !== c && (d._follow = null ,
            d._autoPositionType()),
            d
        },
        size: function(a, b) {
            var c, d, e, f, g = this, j = (g.config,
            g.DOM), k = j.wrap, l = j.main, m = k[0].style, n = l[0].style;
            return a && (g._width = -1 !== a.toString().indexOf("%") ? a : null ,
            c = i.width() - k[0].offsetWidth + l[0].offsetWidth,
            e = g._toNumber(a, c),
            a = e,
            "number" == typeof a ? (m.width = "auto",
            n.width = Math.max(g.config.minWidth, a) + "px",
            m.width = k[0].offsetWidth + "px") : "string" == typeof a && (n.width = a,
            "auto" === a && k.css("width", "auto"))),
            b && (g._height = -1 !== b.toString().indexOf("%") ? b : null ,
            d = i.height() - k[0].offsetHeight + l[0].offsetHeight,
            f = g._toNumber(b, d),
            b = f,
            "number" == typeof b ? n.height = Math.max(g.config.minHeight, b) + "px" : "string" == typeof b && (n.height = b)),
            g._ie6SelectFix(),
            g
        },
        follow: function(b) {
            var c, d = this, e = d.config;
            if (("string" == typeof b || b && 1 === b.nodeType) && (c = a(b),
            b = c[0]),
            !b || !b.offsetWidth && !b.offsetHeight)
                return d.position(d._left, d._top);
            var f = o + "follow"
              , g = i.width()
              , h = i.height()
              , k = j.scrollLeft()
              , l = j.scrollTop()
              , n = c.offset()
              , p = b.offsetWidth
              , q = b.offsetHeight
              , r = m ? !1 : e.fixed
              , s = r ? n.left - k : n.left
              , t = r ? n.top - l : n.top
              , u = d.DOM.wrap[0]
              , v = u.style
              , w = u.offsetWidth
              , x = u.offsetHeight
              , y = s - (w - p) / 2
              , z = t + q
              , A = r ? 0 : k
              , B = r ? 0 : l;
            return y = A > y ? s : y + w > g && s - w > A ? s - w + p : y,
            z = z + x > h + B && t - x > B ? t - x : z,
            v.left = y + "px",
            v.top = z + "px",
            d._follow && d._follow.removeAttribute(f),
            d._follow = b,
            b[f] = e.id,
            d._autoPositionType(),
            d
        },
        show: function() {
            return this.DOM.wrap.show(),
            !arguments[0] && this._lockMaskWrap && this._lockMaskWrap.show(),
            this
        },
        hide: function() {
            return this.DOM.wrap.hide(),
            !arguments[0] && this._lockMaskWrap && this._lockMaskWrap.hide(),
            this
        },
        close: function() {
            if (this.closed)
                return this;
            var a = this
              , c = a.DOM
              , e = c.wrap
              , f = p.list
              , g = a.config.close
              , h = a.config.follow;
            if (a.time(),
            "function" == typeof g && g.call(a, b) === !1)
                return a;
            a.unlock(),
            a._elemBack && a._elemBack(),
            e[0].className = e[0].style.cssText = "",
            c.content.length && (c.content[0].innerHTML = ""),
            p.focus === a && (p.focus = null ),
            h && h.removeAttribute(o + "follow"),
            delete f[a.config.id],
            a._removeEvent(),
            a.hide(!0)._setAbsolute();
            for (var i in a)
                a.hasOwnProperty(i) && "DOM" !== i && delete a[i];
            return d ? e.remove() : d = a,
            a
        },
        time: function(a) {
            var b = this
              , c = b.config.cancelVal
              , d = b._timer;
            return d && clearTimeout(d),
            a && (b._timer = setTimeout(function() {
                b._click(c)
            }
            , 1e3 * a)),
            b
        },
        focus: function() {
            try {
                if (this.config.focus) {
                    var a = this._focus && this._focus[0] || this.DOM.close[0];
                    a && a.focus()
                }
            } catch (b) {}
            return this
        },
        zIndex: function() {
            var a = this
              , b = a.DOM
              , c = b.wrap
              , d = p.focus
              , e = p.defaults.zIndex++;
            return c.css("zIndex", e),
            a._lockMask && a._lockMask.css("zIndex", e - 1),
            d && d.DOM.wrap.removeClass("aui_state_focus"),
            p.focus = a,
            c.addClass("aui_state_focus"),
            a
        },
        lock: function() {
            if (this._lock)
                return this;
            var b = this
              , c = p.defaults.zIndex - 1
              , d = b.DOM.wrap
              , e = b.config
              , f = j.width()
              , g = j.height()
              , h = b._lockMaskWrap || a(document.body.appendChild(document.createElement("div")))
              , i = b._lockMask || a(h[0].appendChild(document.createElement("div")))
              , k = "(document).documentElement"
              , l = n ? "width:" + f + "px;height:" + g + "px" : "width:100%;height:100%"
              , o = m ? "position:absolute;left:expression(" + k + ".scrollLeft);top:expression(" + k + ".scrollTop);width:expression(" + k + ".clientWidth);height:expression(" + k + ".clientHeight)" : "";
            return b.zIndex(),
            d.addClass("aui_state_lock"),
            h[0].style.cssText = l + ";position:fixed;z-index:" + c + ";top:0;left:0;overflow:hidden;" + o,
            i[0].style.cssText = "height:100%;background:" + e.background + ";filter:alpha(opacity=0);opacity:0",
            m && i.html('<iframe src="about:blank" style="width:100%;height:100%;position:absolute;top:0;left:0;z-index:-1;filter:alpha(opacity=0)"></iframe>'),
            i.stop(),
            i.bind("click", function() {
                b._reset()
            }
            ),
            0 === e.duration ? i.css({
                opacity: e.opacity
            }) : i.animate({
                opacity: e.opacity
            }, e.duration),
            b._lockMaskWrap = h,
            b._lockMask = i,
            b._lock = !0,
            b
        },
        unlock: function() {
            var a = this
              , b = a._lockMaskWrap
              , c = a._lockMask;
            if (!a._lock)
                return a;
            var e = b[0].style
              , f = function() {
                m && (e.removeExpression("width"),
                e.removeExpression("height"),
                e.removeExpression("left"),
                e.removeExpression("top")),
                e.cssText = "display:none",
                d && b.remove()
            }
            ;
            return c.stop().unbind(),
            a.DOM.wrap.removeClass("aui_state_lock"),
            a.config.duration ? c.animate({
                opacity: 0
            }, a.config.duration, f) : f(),
            a._lock = !1,
            a
        },
        _getDOM: function() {
            var b = document.createElement("div")
              , c = document.body;
            b.style.cssText = "position:absolute;left:0;top:0",
            b.innerHTML = p._templates,
            c.insertBefore(b, c.firstChild);
            for (var d, e = 0, f = {
                wrap: a(b)
            }, g = b.getElementsByTagName("*"), h = g.length; h > e; e++)
                d = g[e].className.split("aui_")[1],
                d && (f[d] = a(g[e]));
            return f
        },
        _toNumber: function(a, b) {
            if (!a && 0 !== a || "number" == typeof a)
                return a;
            var c = a.length - 1;
            return a.lastIndexOf("px") === c ? a = parseInt(a) : a.lastIndexOf("%") === c && (a = parseInt(b * a.split("%")[0] / 100)),
            a
        },
        _ie6PngFix: m ? function() {
            for (var b, c, d, e, a = 0, f = p.defaults.path + "/skins/", g = this.DOM.wrap[0].getElementsByTagName("*"); a < g.length; a++)
                b = g[a],
                c = b.currentStyle.png,
                c && (d = f + c,
                e = b.runtimeStyle,
                e.backgroundImage = "none",
                e.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + d + "',sizingMethod='crop')")
        }
         : a.noop,
        _ie6SelectFix: m ? function() {
            var a = this.DOM.wrap
              , b = a[0]
              , c = o + "iframeMask"
              , d = a[c]
              , e = b.offsetWidth
              , f = b.offsetHeight;
            e += "px",
            f += "px",
            d ? (d.style.width = e,
            d.style.height = f) : (d = b.appendChild(document.createElement("iframe")),
            a[c] = d,
            d.src = "about:blank",
            d.style.cssText = "position:absolute;z-index:-1;left:0;top:0;filter:alpha(opacity=0);width:" + e + ";height:" + f)
        }
         : a.noop,
        _runScript: function(a) {
            for (var b, c = 0, d = 0, e = a.getElementsByTagName("script"), f = e.length, g = []; f > c; c++)
                "text/dialog" === e[c].type && (g[d] = e[c].innerHTML,
                d++);
            g.length && (g = g.join(""),
            b = new Function(g),
            b.call(this))
        },
        _autoPositionType: function() {
            this[this.config.fixed ? "_setFixed" : "_setAbsolute"]()
        },
        _setFixed: function() {
            return m && a(function() {
                var b = "backgroundAttachment";
                "fixed" !== k.css(b) && "fixed" !== a("body").css(b) && k.css({
                    zoom: 1,
                    backgroundImage: "url(about:blank)",
                    backgroundAttachment: "fixed"
                })
            }
            ),
            function() {
                var a = this.DOM.wrap
                  , b = a[0].style;
                if (m) {
                    var c = parseInt(a.css("left"))
                      , d = parseInt(a.css("top"))
                      , e = j.scrollLeft()
                      , f = j.scrollTop()
                      , g = "(document.documentElement)";
                    this._setAbsolute(),
                    b.setExpression("left", "eval(" + g + ".scrollLeft + " + (c - e) + ') + "px"'),
                    b.setExpression("top", "eval(" + g + ".scrollTop + " + (d - f) + ') + "px"')
                } else
                    b.position = "fixed"
            }
        }
        (),
        _setAbsolute: function() {
            var a = this.DOM.wrap[0].style;
            m && (a.removeExpression("left"),
            a.removeExpression("top")),
            a.position = "absolute"
        },
        _click: function(a) {
            var c = this
              , d = c._listeners && c._listeners[a] && c._listeners[a].callback;
            return "function" != typeof d || d.call(c, b) !== !1 ? c.close() : c
        },
        _reset: function(a) {
            var b, c = this, d = c._winSize || i.width() * i.height(), e = c._follow, f = c._width, g = c._height, h = c._left, j = c._top;
            a && (b = c._winSize = i.width() * i.height(),
            d === b) || ((f || g) && c.size(f, g),
            e ? c.follow(e) : (h || j) && c.position(h, j))
        },
        _addEvent: function() {
            var a, c = this, e = (c.config,
            "CollectGarbage" in b), f = c.DOM;
            c._winResize = function() {
                a && clearTimeout(a),
                a = setTimeout(function() {
                    c._reset(e)
                }
                , 40)
            }
            ,
            i.bind("resize", c._winResize),
            f.wrap.bind("click", function(a) {
                var d, b = a.target;
                return b.disabled ? !1 : (d = b[o + "callback"],
                d && c._click(d),
                c._ie6SelectFix(),
                void 0)
            }
            ).bind("mousedown", function() {
                c.zIndex()
            }
            )
        },
        _removeEvent: function() {
            var a = this
              , b = a.DOM;
            b.wrap.unbind(),
            i.unbind("resize", a._winResize)
        }
    },
    p.fn._init.prototype = p.fn,
    a.fn.dialog = a.fn.artDialog = function() {
        var a = arguments;
        return this[this.live ? "live" : "bind"]("click", function() {
            return p.apply(this, a),
            !1
        }
        ),
        this
    }
    ,
    p.focus = null ,
    p.get = function(a) {
        return a === c ? p.list : p.list[a]
    }
    ,
    p.list = {},
    j.bind("keydown", function(a) {
        var b = a.target
          , c = b.nodeName
          , d = /^INPUT|TEXTAREA$/
          , e = p.focus
          , f = a.keyCode;
        e && e.config.esc && !d.test(c) && 27 === f && e._click(e.config.cancelVal)
    }
    ),
    g = b._artDialog_path || function(a, b, c) {
        for (b in a)
            a[b].src && -1 !== a[b].src.indexOf("artDialog") && (c = a[b]);
        return e = c || a[a.length - 1],
        c = e.src.replace(/\\/g, "/"),
        c.lastIndexOf("/") < 0 ? "." : c.substring(0, c.lastIndexOf("/"))
    }
    (document.getElementsByTagName("script")),
    f = e.src.split("skin=")[1]) {
        var q = document.createElement("link");
        q.rel = "stylesheet",
        q.href = g + "/skins/" + f + ".css?" + p.fn.version,
        e.parentNode.insertBefore(q, e)
    }
    i.bind("load", function() {
        setTimeout(function() {
            h || p({
                left: "-9999em",
                time: 9,
                fixed: !1,
                lock: !1,
                focus: !1
            })
        }
        , 150)
    }
    );
    try {
        document.execCommand("BackgroundImageCache", !1, !0)
    } catch (r) {}
    p._templates = '<div class="aui_main"><div class="aui_content"></div></div>',
    p.defaults = {
        content: '<div class="aui_loading"><span>loading..</span></div>',
        title: "\u6d88\u606f",
        button: !1,
        ok: !1,
        cancel: !1,
        init: !1,
        close: !1,
        okVal: "\u786e\u5b9a",
        cancelVal: "\u53d6\u6d88",
        width: "auto",
        height: "auto",
        minWidth: 96,
        minHeight: 32,
        padding: "20px 25px",
        skin: "",
        icon: !1,
        time: !1,
        esc: !1,
        focus: !0,
        show: !0,
        follow: null ,
        path: g,
        lock: !0,
        background: "#fff",
        opacity: .5,
        duration: 300,
        fixed: !1,
        left: "50%",
        top: "50%",
        zIndex: 1987,
        resize: !1,
        drag: !1
    },
    b.artDialog = a.dialog = a.artDialog = p
}
(this.art || this.jQuery && (this.art = jQuery), this);
