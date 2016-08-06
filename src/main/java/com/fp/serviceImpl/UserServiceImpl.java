package com.fp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.base.DaoSupportImpl;
import com.fp.domain.User;
import com.fp.service.UserService;


@Service
@Transactional
@SuppressWarnings("unchecked")
public class UserServiceImpl extends DaoSupportImpl<User>implements UserService {

	
	public String checkUser(String name, String password) {
		String sql="select * from user where loginName = "+name;
		List<User> users=getSession().createSQLQuery(sql).list();
		if (users.size()==0) {
			return "无效的用户名";
		} else {
         sql="select * from user where loginName = " +name+ " and password = '"+password+"'";
         System.out.println(sql);
         users=getSession().createSQLQuery(sql).list();
         if (users.size()==0) {
			return "密码错误";
		} else {
            return "正确的用户";
		}
		}
	}

	public User getByName(String name) {
		String sql="select * from user where loginName = "+name;
		List<User> users=getSession().createSQLQuery(sql).addEntity(User.class).list();
		return users.get(0);
	}

	public Boolean CheckUserExit(String phone) {
		String sql = "select * from user where loginName = " + phone;
		List<User> users = getSession().createSQLQuery(sql).addEntity(User.class).list();
		if (users.size() == 0) {
			return false;
		} else {
			return true;
		}
	}


}
