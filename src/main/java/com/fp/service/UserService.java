package com.fp.service;

import com.fp.base.DaoSupport;
import com.fp.domain.User;

public interface UserService extends DaoSupport<User> {

	String checkUser(String name, String password);

	User getByName(String name);

	Boolean CheckUserExit(String phone);

	
	
}
