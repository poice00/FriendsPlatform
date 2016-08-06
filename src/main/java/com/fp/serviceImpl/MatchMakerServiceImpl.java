package com.fp.serviceImpl;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.base.DaoSupportImpl;
import com.fp.domain.Matchmaker;
import com.fp.service.MatchMakerService;


@Service
@Transactional
public class MatchMakerServiceImpl extends DaoSupportImpl<Matchmaker>implements MatchMakerService {

	public Matchmaker getByStringId(String id) {
		return (Matchmaker) getSession().get(Matchmaker.class, id);
	}

	public boolean findUserBy(String username, String password) {
		long count = (Long) getSession().createQuery(
				"SELECT COUNT(*) FROM Matchmaker WHERE username = ? AND password=?")//
				.setParameter(0, username)//
				.setParameter(1, password)//
				.uniqueResult();
		if(count > 0)
			return true;
		
		return false;
	}

	public Matchmaker getByName(String username) {
			return (Matchmaker) getSession()
					.createQuery(//
							"From Matchmaker u where u.username=?")//
					.setParameter(0, username)//
					.uniqueResult();
	}

}
