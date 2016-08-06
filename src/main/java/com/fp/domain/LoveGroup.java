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
public class LoveGroup {
	private Long id ;
	
	private String name;//名称 
	private String compnent;//组成 
	private String loveIntro;//爱情宣言
	private String tags;//个性标签
	
	private Set<LoveType> loveTypes = new HashSet<LoveType>(); 
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCompnent() {
		return compnent;
	}
	public void setCompnent(String compnent) {
		this.compnent = compnent;
	}
	@Type(type="text")
	public String getLoveIntro() {
		return loveIntro;
	}
	public void setLoveIntro(String loveIntro) {
		this.loveIntro = loveIntro;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@OneToMany(mappedBy="loveGroup")
	public Set<LoveType> getLoveTypes() {
		return loveTypes;
	}
	public void setLoveTypes(Set<LoveType> loveTypes) {
		this.loveTypes = loveTypes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
