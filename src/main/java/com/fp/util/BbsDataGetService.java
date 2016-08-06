package com.fp.util;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.fp.domain.BbsReply;
import com.fp.domain.BbsTopic;
import com.fp.domain.Topic;

public class BbsDataGetService {
	
	@SuppressWarnings("deprecation")
	public void saveBbsTopic(BbsTopic bbsTopic) {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(bbsTopic);
		session.getTransaction().commit();
		session.clear();
		session.close();
		sessionFactory.close();
	}
	@SuppressWarnings("deprecation")
	public void saveBbsReply(BbsReply bbsReply) {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(bbsReply);
		session.getTransaction().commit();
		session.clear();
		session.close();
		sessionFactory.close();
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Topic> selectTopicData() {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		String sql="select * from topic";		
		List<Topic> topics=session.createSQLQuery(sql).addEntity(Topic.class).list();
		session.getTransaction().commit();
		session.clear();
		session.close();
		sessionFactory.close();
		return topics;
	}
	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<BbsTopic> selectBbsTopicData() {
		SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		String sql="select * from bbstopic";		
		List<BbsTopic> topics=session.createSQLQuery(sql).addEntity(BbsTopic.class).list();
		session.getTransaction().commit();
		session.clear();
		session.close();
		sessionFactory.close();
		return topics;
	}
	
}
