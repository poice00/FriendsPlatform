package com.fp.service;

import java.util.List;

import com.fp.base.DaoSupport;
import com.fp.domain.Page;
import com.fp.domain.Reply;
import com.fp.domain.Topic;

public interface TopicService extends DaoSupport<Topic> {

	List<Topic> findAllTopic(Long topicId);
	
	List<Reply> findByTopic(Topic topic);
	
	Topic getById(String topicId);

	Page<Reply> findByTopic(Topic topic, int currentPage, int pageSize);

	Page<Topic> findTopic(int currentPage, int pageSize);
}
