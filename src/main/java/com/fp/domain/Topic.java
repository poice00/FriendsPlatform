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
public class Topic {
	
	private String id ;
	private String title ;//标题
	private String author ;//作者
	private String time ;//时间
	private String content ;//内容	
	private Double positive ;	
	private Double negative ;	
	
	//private User user;
	
	private Set<Reply> replys = new  HashSet<Reply>();
	
	@Id
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Type(type="text")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@OneToMany(mappedBy="topic")
	public Set<Reply> getReplys() {
		return replys;
	}
	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}
	public Double getPositive() {
		return positive;
	}
	public void setPositive(Double positive) {
		this.positive = positive;
	}
	public Double getNegative() {
		return negative;
	}
	public void setNegative(Double negative) {
		this.negative = negative;
	}
	
	
	
	
	
	
	
	

	
}
