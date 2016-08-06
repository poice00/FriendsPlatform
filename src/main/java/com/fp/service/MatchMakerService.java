package com.fp.service;

import java.util.List;

import com.fp.base.DaoSupport;
import com.fp.domain.LoveType;
import com.fp.domain.Matchmaker;
import com.fp.domain.Topic;

public interface MatchMakerService extends DaoSupport<Matchmaker> {

	Matchmaker getByStringId(String id);

	boolean findUserBy(String username, String password);

	Matchmaker getByName(String username);

	
}
