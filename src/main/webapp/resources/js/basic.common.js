(function(window, $) {
var pagination = {
	personal: null,
	listul: null,
	listli: null,
	CARDCOUNT: window.CARDCOUNT,
	CARDWIDTH: 200,
	CARDHEIGHT: 200,
	pagecount: 0,
	curtpagesize: 0,
	page: 1,
	pagesize: 0,
	prevBtn: null,
	nextBtn: null,
	init: function() {
		this.personal = $('div.personal');
		this.listul = this.personal.find('ul');
		this.listli = this.listul.find('li');
		this.prevBtn = $('a.nextIcon');
		this.nextBtn = $('a.prevIcon');

		var self = this;
		this.prevBtn.on('click', function() {self.prev()});
		this.nextBtn.on('click', function() {self.next()});
	},
	setPageSize: function(pagesize) {
		this.pagecount = Math.ceil(this.CARDCOUNT / pagesize);
		this.pagesize = Math.min(pagesize, this.CARDCOUNT);

		this.curtpagesize = pagesize;
		this.updateBtn();
	},
	updateBtn: function() {
		this.nextBtn[this.pagecount > this.page ? 'show' : 'hide']();
		this.prevBtn[this.page > 1 ? 'show' : 'hide']();
	},
	prev: function() {
		var self = this;
		if (this.page <= 1) {
			return;
		}

		var _page = self.page;
		this.listul.fadeOut(function() {
			for (var i = 0, l = self.curtpagesize; i <= l; i++) {
				self.listli.eq(self.curtpagesize * (_page - 1) - i).show();
			}
			self.listul.show();
		});

		this.page--;
		this.updateBtn();
	},
	next: function() {
		var self = this;
		if (this.page >= this.pagecount) {
			return;
		}

		var _page = self.page;
		this.listul.fadeOut(function() {
			for (var i = 0, l = self.curtpagesize * _page; i < l; i++) {
				self.listli.eq(i).hide();
			}
			self.listul.show();
		});

		this.page++;
		this.updateBtn();
	}
};
	pagination.init();
	$(window).resize(function() {
		pagination.setPageSize(
			Math.floor(pagination.listul.width() / pagination.CARDWIDTH) * Math.floor((pagination.personal.height() - 80 - 30) / pagination.CARDHEIGHT)
		);
	}).resize();
})(window, jQuery);