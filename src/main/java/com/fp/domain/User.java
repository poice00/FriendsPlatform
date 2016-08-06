package com.fp.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

@Entity
public class User {
	
	private Long id ;
	private String loginName ;//登录名
	private String password ;//密码
	private int count ;//登陆次数
	
	private LoveType loveType;
	
	private Set<ActivityReply> replys = new HashSet<ActivityReply>(); //回复
	private Set<Activity> activitys = new HashSet<Activity>(); //活动
	
	private Set<Messgae> send = new HashSet<Messgae>(); //发消息
	private Set<Messgae> rece = new HashSet<Messgae>(); //收消息
	
	private Set<Feel> users = new HashSet<Feel>(); //用户
	private Set<Feel> recoms = new HashSet<Feel>(); //推荐
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(nullable=false)
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	@OneToMany(mappedBy="sender")
	public Set<Messgae> getSend() {
		return send;
	}
	public void setSend(Set<Messgae> send) {
		this.send = send;
	}
	
	@OneToMany(mappedBy="receiver")
	public Set<Messgae> getRece() {
		return rece;
	}
	
	public void setRece(Set<Messgae> rece) {
		this.rece = rece;
	}
	@OneToMany(mappedBy="user")
	public Set<Feel> getUsers() {
		return users;
	}

	public void setUsers(Set<Feel> users) {
		this.users = users;
	}
	@OneToMany(mappedBy="recommender")
	public Set<Feel> getRecoms() {
		return recoms;
	}

	public void setRecoms(Set<Feel> recoms) {
		this.recoms = recoms;
	}
	@OneToMany(mappedBy="user")
	public Set<ActivityReply> getReplys() {
		return replys;
	}

	public void setReplys(Set<ActivityReply> replys) {
		this.replys = replys;
	}
	@OneToMany(mappedBy="user")
	public Set<Activity> getActivitys() {
		return activitys;
	}

	public void setActivitys(Set<Activity> activitys) {
		this.activitys = activitys;
	}
	@ManyToOne
	@JoinColumn(name="loveTypeId")
	public LoveType getLoveType() {
		return loveType;
	}

	public void setLoveType(LoveType loveType) {
		this.loveType = loveType;
	}

	
	
	
	
	
	

	
}
