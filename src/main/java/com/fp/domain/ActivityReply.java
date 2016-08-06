package com.fp.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

@Entity
public class ActivityReply {
	
	private Long id ;
	private String content ;//内容	
	private Date time ;//内容	
	
	private ActivityReply parent;//父回复
	private Activity activity;//活动
	private User user;
	
	private Set<ActivityReply> childrens = new HashSet<ActivityReply>();//子回复
	
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	@ManyToOne
	@JoinColumn(name="parentId")
	public ActivityReply getParent() {
		return parent;
	}

	public void setParent(ActivityReply parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy="childrens")
	public Set<ActivityReply> getChildrens() {
		return childrens;
	}

	public void setChildrens(Set<ActivityReply> childrens) {
		this.childrens = childrens;
	}
	@ManyToOne
	@JoinColumn(name="activityId")
	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	@ManyToOne
	@JoinColumn(name="userId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	
	
	

	
	
	
	
	
	

	
}
