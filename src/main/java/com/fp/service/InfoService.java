package com.fp.service;

import java.util.List;

import com.fp.base.DaoSupport;
import com.fp.domain.Info;

public interface InfoService extends DaoSupport<Info> {

	List<Info> getByParams(Object... params);

	Info getByUserId(Long id);
	
}
