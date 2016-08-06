package com.fp.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
/**
 * 表现表
 * 和恋爱类型 多对多
 * @author ssy
 *
 */
@Entity
public class Performance {
	
	private Long id ;
	private String name;//名称
	private String description;//描述
	
	private Set<LoveType> loveTypes = new HashSet<LoveType>();
	
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Type(type="text")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToMany
	@JoinTable(name="performance_loveType",
			joinColumns={@JoinColumn(name="performanceId")},
			inverseJoinColumns={@JoinColumn(name="loveTypeId")}
	)
	public Set<LoveType> getLoveTypes() {
		return loveTypes;
	}
	public void setLoveTypes(Set<LoveType> loveTypes) {
		this.loveTypes = loveTypes;
	}
}
