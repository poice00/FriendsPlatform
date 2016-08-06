$(function() {
	var login_form = $('#login_form'),
		phone = $('#phone'),
		phone_msg = $('#phone_msg'),
		phone_icon = $('#phone_icon'),
		password = $('#password'),
		password_msg = $('#password_msg'),
		password_icon = $('#password_icon'),
		LANG = {
			password_short: '你输入的密码不足6位',
			password_long: '你输入的密码已超过16位',
			password_format: '密码只能由英文字母或数字组成',
			password_empty: '请输入密码'
		},
		Util = {
			checkPhone: function(phone) {
				return this.check(/^(1\d{2}|0[1-9][0-9]{1,2})\d{8}$/, phone) || this.check(/^([_a-zA-Z0-9\-]+(\.[_a-zA-Z0-9\-]*)*@[a-zA-Z0-9\-]+([\.][a-zA-Z0-9\-]+)+)$/, phone);
			},
			checkPassword: function(pwd) {
				if (pwd.length < 6) {
					Util.error(password_msg, password_icon, LANG.password_short);
					return false;
				}

				if (pwd.length > 16) {
					Util.error(password_msg, password_icon, LANG.password_long);
					return false;			
				}

				if (this.check(/[^a-z0-9]{1,16}/i, pwd)) {
					Util.error(password_msg, password_icon, LANG.password_format);
					return false;
				} else {
					Util.ok(password_msg, password_icon);
				}

				return true;
			},
			check: function(reg, data) {
				return reg.test(data);
			},
			error: function(msgobj, iconobj, msg) {
				if (msg) {
					msgobj.text(msg);
				}
				msgobj.show();
				iconobj.removeClass('ok').addClass('error').show();				
			},
			ok: function(msgobj, iconobj) {
				msgobj.hide();
				iconobj.removeClass('error').addClass('ok').show();				
			}
		};

	var phv = phone.val();
	phone.focus(function() {
		if (this.value == phv) {
			this.value = '';
		}
	}).blur(function() {
		if (!this.value) {
			this.value = phv;
			return;
		}

		if (!Util.checkPhone(this.value)) {
			Util.error(phone_msg, phone_icon);
		} else {
			Util.ok(phone_msg, phone_icon);
		}
	});

	var pwv = password.val();
	password.focus(function() {
		if (this.value == pwv) {
			this.value = '';
		}

		password.attr('type', 'password');
	}).blur(function() {
		if (!this.value) {
			password.attr('type', 'text');
			this.value = pwv;
			return;
		}

		if (!Util.checkPassword(this.value)) {
			return false;
		}

		password.attr('type', 'password');
	});

	var phoneVal, passwordVal;
	var login = function() {
		if (!(phoneVal = phone.val()) || (phoneVal == phv) || !Util.checkPhone(phoneVal)) {
			Util.error(phone_msg, phone_icon);
			return false;
		} else {
			Util.ok(phone_msg, phone_icon);	
		}

		passwordVal = password.val();
		if (passwordVal == pwv) {
			Util.error(password_msg, password_icon, LANG.password_empty);
			return false;
		}

		if (!Util.checkPassword(passwordVal)) {
			return false;
		}

		$.post(login_form.attr('action'), {phone: phoneVal, password: passwordVal}, function(r) {
			if (r.ret != 0) {
				switch (r.ret) {
					case 10005:
						Util.error(phone_msg, phone_icon, r.message);
						break;
					case 10010:
						Util.error(password_msg, password_icon, r.message);
						break;
				}
			} else {
				setTimeout(function() {
					window.location.href = r.result || '/index/';
				}, 16);
			}
		}, 'json');
	};

	$('#login_btn').click(login);
	$(document).on('keydown', function(event) {
		if (event.keyCode == 13) {
			login();
		}
	});
});