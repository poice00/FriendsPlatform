$(function() {
	var subform = $('#register_form'),
		phone = $('#phone'),
		phone_msg = $('#phone_msg'),
		phone_icon = $('#phone_icon'),
		password = $('#password'),
		password_msg = $('#password_msg'),
		password_icon = $('#password_icon'),
		captcha = $('#captcha'),
		captcha_btn = $('#captcha_btn'),
		captcha_msg = $('#captcha_msg'),
		captcha_icon = $('#captcha_icon'),
		captcha_tips = $('#captcha_tips'),
		login_btn = $('#login_btn'),
		LANG = {
			password_short: '你输入的密码不足6位',
			password_long: '你输入的密码已超过16位',
			password_format: '密码只能由英文字母或数字组成',
			password_empty: '请输入密码',
			captcha_format:'验证码格式不正确',
		},
		Util = {
			checkPhone: function(phone) {
				if (!this.check(/^1[0-9]{10}$/i, phone)) {
					Util.error(phone_msg, phone_icon);
					return false;
				} else {
					Util.ok(phone_msg, phone_icon);
				}
				return true;
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

				if (this.check(/[^a-z0-9]{6,16}/i, pwd)) {
					Util.error(password_msg, password_icon, LANG.password_format);
					return false;
				} else {
					Util.ok(password_msg, password_icon);
				}

				return true;
			},
			checkCaptcha: function(captcha) {
				return this.check(/^[0-9]{6}$/, captcha);
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

	var cv = captcha.val();
	captcha.focus(function() {
		if (this.value == cv) {
			this.value = '';
		}
	}).blur(function() {
		if (!this.value) {
			this.value = cv;
			return;
		}

		if (!Util.checkCaptcha(this.value)) {
			Util.error(captcha_msg, captcha_icon, LANG.captcha_format);
		} else {
			Util.ok(captcha_msg, captcha_icon);	
		}
	});

	var time = captcha_tips.data('time'),
		unit = captcha_tips.data('unit'),
		capurl = captcha_btn.data('url');
		inerVal = null,
		timeStart = false;

	captcha_btn.on('click', function() {
		captcha_msg.hide();
		if (timeStart) {
			return false;
		}

		if (!Util.checkPhone(phone.val())) {
			Util.error(phone_msg, phone_icon);
			return false;
		}

		var timeLeft = time;
		$.getJSON(capurl, {phone: phone.val()}, function(r) {
			if (r.ret != 0) {
				captcha_msg.text(r.message).show();
			} else {
				timeStart = true;
				captcha_tips.text(timeLeft + unit).show();
				inerVal = setInterval(function() {
					--timeLeft;

					if (timeLeft <= 0) {
						inerVal && clearInterval(inerVal);
						timeStart = false;
						captcha_tips.hide();
						captcha_btn.show();
					} else {
						captcha_tips.text(timeLeft + unit);
					}
				}, 1000);
				captcha_btn.hide();
			}
		});
	});

	var phoneVal, passwordVal, captchaVal;
	login_btn.click(function() {
		if (!(phoneVal = phone.val()) || (phoneVal == phv) || !Util.checkPhone(phoneVal)) {
			Util.error(phone_msg, phone_icon);
			return false;
		} else {
			Util.ok(phone_msg, phone_icon);	
		}

		if (!(captchaVal = captcha.val()) || !Util.checkCaptcha(captchaVal)) {
			Util.error(captcha_msg, captcha_icon);
			return false;
		} else {
			Util.ok(captcha_msg, captcha_icon);
		}

		passwordVal = password.val();
		if (passwordVal == pwv) {
			Util.error(password_msg, password_icon, LANG.password_empty);
			return false;
		}

		if (!Util.checkPassword(passwordVal)) {
			return false;
		}

		$.post(subform.attr('action'), {phone: phoneVal, password: passwordVal, captcha:captchaVal}, function(r) {
			if (r.ret != 0) {
				switch (r.ret) {
					case 10030:
						Util.error(phone_msg, phone_icon, r.message);
						break;
					case 10015:
						Util.error(captcha_msg, captcha_icon, r.message);
						break;
				}
			} else {
				setTimeout(function() {
					window.location.href = r.result;
				}, 16);
			}
		}, 'json');
	});
});