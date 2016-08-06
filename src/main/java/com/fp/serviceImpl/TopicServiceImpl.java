package com.fp.serviceImpl;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fp.base.DaoSupportImpl;
import com.fp.domain.Page;
import com.fp.domain.Reply;
import com.fp.domain.Topic;
import com.fp.service.TopicService;

@Service
@Transactional
@SuppressWarnings("unchecked")
public class TopicServiceImpl extends DaoSupportImpl<Topic>implements TopicService {

	public List<Topic> findAllTopic(Long topicId) {
		List<Topic> topics = getSession().createSQLQuery("select * from topic where topic_id = ?")
				.setParameter(0, topicId).list();
		return topics;
	}

	public List<Reply> findByTopic(Topic topic) {
		List<Reply> replies = getSession().createSQLQuery("select * from reply where topicId  = ?")
				.addEntity(Reply.class).setParameter(0, topic.getId()).list();
		return replies;
	}

	public Topic getById(String topicId) {
		return (Topic) getSession().createQuery("FROM Topic WHERE id = ?").setParameter(0, topicId).uniqueResult();
	}

	public Page<Reply> findByTopic(Topic topic, int currentPage, int pageSize) {
		int size = getSession().createCriteria(Reply.class).add(Restrictions.eq("topic", topic)).list().size();
		List<Reply> list = getSession().createCriteria(Reply.class).add(Restrictions.eq("topic", topic))
				.addOrder(Order.desc("time")).setFirstResult(pageSize * (currentPage - 1)).setMaxResults(pageSize)
				.list();
		return new Page<Reply>(pageSize, currentPage, size, list);
	}

	public Page<Topic> findTopic(int currentPage, int pageSize) {
		int size = getSession().createCriteria(Topic.class).list().size();
		List list = getSession().createCriteria(Topic.class).addOrder(Order.desc("time"))
				.setFirstResult(pageSize * (currentPage - 1)).setMaxResults(pageSize).list();
		return new Page<Topic>(pageSize, currentPage, size, list);
	}

}
