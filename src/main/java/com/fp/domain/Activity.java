package com.fp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

@Entity
public class Activity {
	
	private Long id ;
	private String img ;//展示图片
	private String title ;//标题
	private String time ;//活动时间
	private String city ;//城市
	private String address ;//地址
	private String expense ;//费用
	private String content ;//内容	
	
	private User user;
	
	private Set<ActivityImg> imgs = new  HashSet<ActivityImg>();
	private Set<ActivityReply> activityReplys = new  HashSet<ActivityReply>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExpense() {
		return expense;
	}

	public void setExpense(String expense) {
		this.expense = expense;
	}
	@Type(type="text")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@OneToMany(mappedBy="activity")
	public Set<ActivityImg> getImgs() {
		return imgs;
	}

	public void setImgs(Set<ActivityImg> imgs) {
		this.imgs = imgs;
	}
	@OneToMany(mappedBy="activity")
	public Set<ActivityReply> getActivityReplys() {
		return activityReplys;
	}

	public void setActivityReplys(Set<ActivityReply> activityReplys) {
		this.activityReplys = activityReplys;
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
