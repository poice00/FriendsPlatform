package com.fp.service;

import java.util.List;

import com.fp.base.DaoSupport;
import com.fp.domain.Feel;
import com.fp.domain.Info;
import com.fp.domain.User;


public interface RecommendService extends DaoSupport<User>{
	
	
	/**
	 * @desc 获取用户的推荐记录
	 * @param user 要查询推荐记录的用户
	 * @return Feel实体类的List集合
	 * @author yanbaobin@yeah.net
	 * @date Oct 29, 2015 7:53:45 PM
	 */
	List<Feel> findRecommend(User user);
	
	/**
	 * @desc 根据用户的择偶要求给其推荐对象
	 * @param user 需要推荐对象的用户
	 * @return 符合用户择偶条件的Info对象集合
	 * @author yanbaobin@yeah.net
	 * @date Oct 31, 2015 7:38:44 PM
	 */
	List<Info> recommend(User user);
	
	/**
	 * @desc 设置用户对推荐对象是否有感觉
	 * @param userId 用户Id
	 * @param recId 被推荐的用户id
	 * @param feel 感觉
	 * @author yanbaobin@yeah.net
	 * @date Nov 1, 2015 7:08:24 PM
	 */
	void setFeel(long userId, long recId, String feel);
}
