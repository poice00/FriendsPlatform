package com.fp.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.base.DaoSupportImpl;
import com.fp.domain.LoveType;
import com.fp.service.LoveTypeService;


@Service
@Transactional
public class LoveTypeServiceImpl extends DaoSupportImpl<LoveType>implements LoveTypeService {

	public LoveType getByName(String result) {
		return (LoveType) getSession().createQuery(//
				"FROM LoveType l WHERE l.ename = ?")//
				.setParameter(0, result)
				.uniqueResult();//
	}

}
