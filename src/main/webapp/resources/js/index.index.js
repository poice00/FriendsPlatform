$(function() {
	var TaTpl =
'<div><img src="{mainphoto}" /></div>'
+	'<dl>'
+		'<dt>'
+			'<strong>{nickName}</strong><br />'
+			'年龄：{age}岁<br />'
+			'身高：{height}CM<br />'
+			'学历：{education}<br />'
+			'月收入：{income}<br />'
+			'居住地：{city}<br />'
+			'<div class="clear"></div>'
+			'<a href="/person?userId={userId}&next=/index?lastuserId={userId}">查看详细资料</a>'
+		'</dt>'
+		'<dd>推荐时间：<br />{time}{vipType}</dd>'
+	'</dl>'
+'</div>',
	suggestJson = jQuery.parseJSON(window.SUGGESTJSON || "{}"),
	statusAllJson = jQuery.parseJSON(window.STATUSALLJSON || "{}"),
	rightMenu = $('#rightMenuCont'),
	content = $('#content'),
	photoList = content.find('div.photoList'),
	firstPhoto = photoList.find('ul > li:eq(0) > a > span'),
	photo = content.find('#picDiv'),
	photoImg = photo.find('img'),
	isLikeDiv = photo.find('div'),
	statusLink = photo.find('a'),
	LASTUSERID = window.LASTUSERID || 0,
	lastPicDiv = $('#lastPicDiv'),
	VIPTYPE = parseInt(window.VIPTYPE || 0, 10),
	LIKED = {};
	ShowCountDown((new Date).getFullYear(),nowMon,nowData);
	window.setInterval(function(){ShowCountDown((new Date).getFullYear(),nowMon,nowData);}, interval);
	var suggest = {
		COOKIE_NAME:'enjoy_' + window.USERID + '_suggest',
		opacity:-1,
		lastSpan: null,
		changePic: function(selector) {
			this.lastSpan = selector;
			selector.stop(true, true).animate({opacity: 0});
		},
		lastUserId: -1,
		userCache:{},
		photoCache:{},
		feelCache:{},
		changeUser: function(userId) {
			if (!userId || this.lastUserId == userId) {
				return;
			}

			var person = suggestJson[userId];
			if (person === undefined) {
				return;
			}

			var html = this.userCache[userId];
			if (html === undefined) {
				html = TaTpl;

				for (var i in person) {
					if (!person.hasOwnProperty(i)) {
						continue;
					}

					html = html.replace(/{([^}]\w+)}/g, function(all, tag, pos) {
						var v = person[tag];
						if (tag == 'vipType') {
							var _html = '';
							switch (v) {
								case 0: break;
								case 1: _html = '<p class="lay02">单月包会员</p>'; break;
								case 2: _html = '<p class="lay01">人工牵线会员</p>'; break;
							}

							return _html;
						}
						return v;
					});
				}

				this.userCache[userId] = html;
			}

			rightMenu.html(html).show();
			if(person.isLike){
				isLikeDiv.html('<em></em>');
			}
			else {
				isLikeDiv.html('');
			}
			if (person.coverphoto) {
				photoImg.attr('src', person.coverphoto + '?' + userId).hide()
				photoImg.on('load', function() {
					photoImg.css({
						marginTop:(400 - photoImg.height()) / 2,
						marginLeft:(400 - photoImg.width()) / 2,
					}).fadeIn();
				});
			} else {
				photoImg.attr('src', '').hide()
			}

			this.statusImgClass(this.feelCache[userId] || person.myFeel);
			photo.data('userid', userId);
			this.lastUserId = userId;
		},
		nextCache:{},
		next: function() {
			if (this.lastSpan === null) {
				this.lastSpan = firstPhoto;
			}

			var key = this.lastSpan.data('userid'),
				span = this.nextCache[key];

			if (span === undefined) {
				span = this.lastSpan.parent('a').parent('li').next().find('span');

				if (!span.length) {
					rightMenu.parent().hide();
					photo.hide();
					lastPicDiv.show();
					return;
				}

				if (span[0].className == 'red') {
					span.click();
					return;
				}

				this.nextCache[key] = span;
			}

			if (span.parents('li').index() + 1 >= (pagination.curtpagesize * pagination.page))  {
				pagination.next();
			}

			this.show(span);
		},
		show: function(obj) {
			this.changePic(obj);
			this.changeUser(obj.data('userid'));
			obj.data('isread', 1);
		},
		history: function(defobj) {
			var _isRead = $('span[data-isread=1]', photoList).last();
			var _lastspan = $('span[data-userid='+ LASTUSERID +']', photoList);

			_lastspan.length || (_lastspan = null);
			_isRead.length || (_isRead = null);

			this.lastSpan = _lastspan || _isRead || defobj;
			this.show(this.lastSpan);
		},
		storage: function(value) {
			var values = ($.cookie(this.COOKIE_NAME) || '');
			values = values ? values.split(',') : [];
			values.push(value);
			return $.cookie(this.COOKIE_NAME, values.join(','), {expires: 1});
		},
		statusImgClass: function(myfeel) {
			if (myfeel > 0) {
				if (myfeel == statusAllJson['STATUS_SEND_RECEIVE_LIKE'] || myfeel == statusAllJson['STATUS_SEND_LIKE']) {
					statusLink.eq(0).removeClass().addClass('active01');
					statusLink.eq(1).removeClass().addClass('icon02Gray').removeAttr('data-type');

					if (VIPTYPE < 2) {
						statusLink.eq(2).removeClass().addClass('icon03Gray').removeAttr('data-type');
					} else {
						statusLink.eq(2).removeClass().addClass('icon03').attr('data-type','unlike');
					}

				} else if(myfeel == statusAllJson['STATUS_RECEIVE_THINKING']) {
					statusLink.eq(0).removeClass().addClass('icon01Gray').attr('data-type','like');
					statusLink.eq(1).removeClass().addClass('active02').attr('data-type','think');
					statusLink.eq(2).removeClass().addClass('icon03Gray').attr('data-type','unlike');
				} else if(myfeel == statusAllJson['STATUS_RECEIVE_NOT_LIKE']) {
					statusLink.eq(0).removeClass().addClass('icon01Gray').attr('data-type','like');
					statusLink.eq(1).removeClass().addClass('icon02Gray').attr('data-type','think');
					statusLink.eq(2).removeClass().addClass('active03').attr('data-type','unlike');
				}
			} else {
				statusLink.eq(0).removeClass().addClass('icon01').attr('data-type','like');
				statusLink.eq(1).removeClass().addClass('icon02').attr('data-type','think');
				statusLink.eq(2).removeClass().addClass('icon03').attr('data-type','unlike');
			}
		}
	}, pagination = {
		listul: null,
		listli: null,
		AVATARCOUNT: window.ENJOYCOUNT || 20,
		AVATARTWIDTH: 0,
		pagecount: 0,
		curtpagesize: 0,
		page: 1,
		pagesize: 0,
		init: function() {
			this.listul = photoList.find('ul');
			this.listli = this.listul.find('li');

			this.AVATARTWIDTH = this.listli.width() + 14;
		},
		setPageSize: function(pagesize) {
			pagesize = pagesize || this.AVATARCOUNT;
			this.pagesize = Math.min(pagesize, this.AVATARCOUNT);
			this.pagecount = Math.ceil(this.AVATARCOUNT / this.pagesize);
			this.curtpagesize = pagesize;
		},
		prev: function() {
			var self = this;
			if (this.page <= 1) {
				return;
			}

			var _page = self.page;
			this.listul.fadeOut('fast', function() {
				for (var i = 0, l = self.curtpagesize; i <= l; i++) {
					self.listli.eq(self.curtpagesize * (_page - 1) - i).show();
				}
				self.listul.show();
			});
			this.page--;
		},
		next: function() {
			var self = this;
			if (this.page >= this.pagecount) {
				return;
			}

			var _page = self.page;
			this.listul.fadeOut('fast', function() {
				for (var i = 0, l = self.curtpagesize * _page; i < l; i++) {
					self.listli.eq(i).hide();
				}
				self.listul.show();
			});

			this.page++;
		}
	};

	$(document).off('click');
	photoList.on('click', function(event) {
		var target = event.target, self, nodeName;
		if (target.nodeType !== 1) {
			return false;
		}

		self = $(target);
		nodeName = target.nodeName.toLowerCase();

		switch (nodeName) {
			case 'span':
				lastPicDiv.hide();
				photo.show();
				rightMenu.parent().show();
				if (target.className == 'red') {
					$.get('/payment/tips', function(r) {
						art.dialog({content: r});
					});
				} else if (self.data('isread') === 1) {
					suggest.show(self);
				}
				break;
			case 'a':
				if (target.className == 'next') {
					pagination.prev();
				} else if (target.className == 'prev') {
					pagination.next();
				}
				break;
		}
	});

	suggest.history(firstPhoto);

	photo.on('click', function(event) {
		var target = event.target, self, nodeName;
		if (target.nodeType !== 1) {
			return false;
		}

		self = $(target);
		nodeName = target.nodeName.toLowerCase();

		switch (nodeName) {
			case 'a':
				$("#lastPicDiv").show();
				
				var type = self.data('type');
				var status = '';
				if (type) {
					if (LIKED[photo.data('userid')]) {
						return;
					}
					$.post('/FriendsPlatform/user/enjoy/', {type: self.data('type'), id: $("#picDiv").attr("data-id")}, function(r) {
						if (r.ret != 0) {
							if(r.ret == 1000) {
								$.get('/basic/completetips', function(r) {
									art.dialog({content: r});
								});
							} else if (r.ret == 1200) {
								$.get('/basic/completetips?realName=1', function(r) {
									art.dialog({content: r});
								});
							} else {
								alert(r.result);
							}
						} else {
							if (type == 'like') {
								self.removeClass().addClass('active01');
								self.next('a').removeClass().addClass('icon02Gray').removeAttr('data-type');

								if (VIPTYPE < 2) {
									self.next('a').next('a').removeClass().addClass('icon03Gray').removeAttr('data-type');
								} else {
									self.next('a').next('a').removeClass().addClass('icon03').attr('data-type','unlike');
								}

								status = statusAllJson['STATUS_SEND_LIKE'];
							} else if (type == 'think') {
								self.prev('a').removeClass().addClass('icon01Gray').attr('data-type','like');
								self.removeClass().addClass('active02').attr('data-type','think');
								self.next('a').removeClass().addClass('icon03Gray').attr('data-type','unlike');
								status = statusAllJson['STATUS_RECEIVE_THINKING'];
							} else if (type == 'unlike') {
								self.prev('a').prev('a').removeClass().addClass('icon01Gray').attr('data-type','like');
								self.prev('a').removeClass().addClass('icon02Gray').attr('data-type','think');
								self.removeClass().addClass('active03').attr('data-type','unlike');
								status = statusAllJson['STATUS_RECEIVE_NOT_LIKE'];
							}
							if(status > 0) {
								suggest.feelCache[photo.data('userid')] = status;
							}
							suggest.next();
						}
					}, 'json');
				}

				break;
		}
	});

	photoList.find('li img').on('error', function() {
		this.src = this.src.substr(0, this.src.lastIndexOf('/photo/') + 7) + 'unknow.jpg';
		$(this).off('error');
	});

	pagination.init();
	$(window).resize(function() {pagination.setPageSize(Math.floor(pagination.listul.width() / pagination.AVATARTWIDTH));}).resize();
});