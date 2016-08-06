package com.fp.service;

import java.util.List;

import com.fp.base.DaoSupport;
import com.fp.domain.LoveType;
import com.fp.domain.Topic;

public interface LoveTypeService extends DaoSupport<LoveType> {

	LoveType getByName(String result);
	
}
