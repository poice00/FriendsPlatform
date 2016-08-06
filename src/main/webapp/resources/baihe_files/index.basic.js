$(function() {
	$(document).on('click', function(event) {
		var target = event.target,
			_target = $(target),
			action = '';

		action = _target.data("action");
		if (action) {
			if (action.indexOf('/') == -1) {
				action = '/FriendsPlatform/user/' + action;
			}

			$.get(action, function(r) {
				art.dialog({content: r});
			});
		}
	});	
});

$(function() {
	$('img.showphoto').each(function() {
		var type = this.getAttribute('data-type');
		if (!type) {return}

		if (this.src.indexOf('showphoto/?userId') != -1) {
			this.src = this.src + '&type=' + type;
		} else {
			this.src = this.src.replace(/_87/g, '_' + type);
		}
	});

	var ADDPHOTOTPL = '<a class="addPic" href="javascript:;" data-action="/basic/photo?'+(new Date()).getTime()+'&AP={AP}" data-ap="{AP}"><i data-action="/basic/photo?'+(new Date()).getTime()+'&AP={AP}" data-ap="{AP}">点击添加照片</i></a>';
	$('img.showphoto').on('error', function() {
		$(this).parent().html(ADDPHOTOTPL.replace(/{AP}/g, ((new Date()).getTime()) + ((Math.random() * (20 - 1) + 1))));
	});

	var list = $('div.list');
	list.find('li').hover(function() {
		var self = $(this),
			imgclose = self.find('a.close'),
			imsetup = self.find('div.setUp');
		imgclose.show();
		imsetup.show();
	}, function() {
		var self = $(this),
			imgclose = self.find('a.close'),
			imsetup = self.find('div.setUp');

		imgclose.hide();
		imsetup.hide();
	});		
});

(function(window) {
window.Util = {
	artDialogClose: function() {
		var list = art.artDialog.list;
		for (var i in list) {
			list[i].close();
		}
	}
};
})(window);

(function($) {
	$('div.personal').find('a[href="/index/preview"]').on('click', function() {
		var href = this.href;
		$.getJSON(href, function(r) {
			if (r.ret != 0) {
				alert(r.result);
			} else {
				setTimeout(function() {
					window.location.href = r.result;
				});
			}
		});

		return false;
	});
})(jQuery);