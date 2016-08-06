(function($, undefined) {

var NOTICETYPE = {
	10 : {ID: 'n_doubleenjoy', OBJ: null, TPL: '<a href="/index/double">双向倾心</a><em></em>',},
	15 : {ID: 'n_like', OBJ: null, TPL: '<a href="/index/enjoyme">喜欢我的</a><em></em>'},
	20 : {ID: 'n_msg', OBJ: null, TPL: '<a class="menu02" href="/msg"><i></i><span>消息</span><em></em></a>'}
}, REFRESHTIME = 5000, NOTICEURL = '/notice/index';

var Notice = {
	init: function() {
		var i;
		for (i in NOTICETYPE) {
			if (!NOTICETYPE[i].ID) {
				continue;
			}

			NOTICETYPE[i].OBJ = $('#' + NOTICETYPE[i].ID);
			if (!NOTICETYPE[i].OBJ.length) {
				NOTICETYPE[i].OBJ = null;
			}
		}
	},
	addNotice: function (obj) {
		var notice = NOTICETYPE[obj['type']];

		if (notice === undefined || notice.OBJ === null) {
			return false;
		}

		notice.OBJ.html(notice.TPL);
	},
	removeAllNotice: function() {
		$.each(NOTICETYPE, function(i, x) {
			if (x.OBJ === null) {
				return false;
			}

			var em = x.OBJ.find('em');

			if (em.length) {
				em.remove();
			}
		});
	},
	pullNotice: function() {
		var func = arguments.callee,
			self = this;

		$.getJSON(NOTICEURL, function(r) {
			self.removeAllNotice();
			if (r.result.length) {
				$.each(r.result, function(i, x) {self.addNotice(x);});
			}
			setTimeout(function() {
				func.call(self);
			}, REFRESHTIME);
		});
	}
};

Notice.init();
Notice.pullNotice();

var eventType = document.mozHidden !== undefined ? 'DOMMouseScroll' : 'mousewheel';
var next = $('a.prevIcon'), prev = $('a.nextIcon');
$('#content').on(eventType, function(e) {
	var delta = (e.originalEvent.wheelDelta) ? e.originalEvent.wheelDelta / 120 : -(e.originalEvent.detail) / 3;
	if (delta > 0) {
		if (!prev.length || prev.data('lock')) {
			return false;
		}

		prev.data('lock', true).get(0).click();
		setTimeout(function() {
			prev.data('lock', false);
		}, 1000);

	} else {
		if (!next.length || next.data('lock')) {
			return false;
		}

		next.data('lock', true).get(0).click();
		setTimeout(function() {
			next.data('lock', false);
		}, 1000);
	}
});
})(jQuery);