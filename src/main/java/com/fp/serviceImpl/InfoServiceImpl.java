package com.fp.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.base.DaoSupportImpl;
import com.fp.domain.Info;
import com.fp.domain.User;
import com.fp.service.InfoService;


@Service
@Transactional
@SuppressWarnings("unchecked")
public class InfoServiceImpl extends DaoSupportImpl<Info> implements InfoService {

	public List<Info> getByParams(Object... params) {
		return getSession().createQuery(//
				"FROM Info info WHERE info.loveType=? AND info.sex=?")//
				.setParameter(0, params[0])//
				.setParameter(1, params[1])//
				//.setParameter(2, params[2])//
				.list();
	}

	@Override
	public Info getByUserId(Long id) {
		String sql="select * from info where userId = " +id;
		List<Info> infos=getSession().createSQLQuery(sql).addEntity(Info.class).list();	
		return infos.get(0);
	}
	
}
