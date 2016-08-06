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

@Entity
public class LoveType {
	private Long id ;
	private String name;
	private String ename;
	private String shortDes;
	private String description;
	private String Characteristic1;
	private String Characteristic2;
	private String Characteristic3;
	private String lovingOne;
	private String lovingTwe;
	private String lovingThree;
	
	private LoveGroup loveGroup;
	private Set<User> users = new HashSet<User>(); 
	private Set<Performance> performance = new HashSet<Performance>(); 
	
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
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getShortDes() {
		return shortDes;
	}
	public void setShortDes(String shortDes) {
		this.shortDes = shortDes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@OneToMany(mappedBy="loveType")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	@ManyToOne
	@JoinColumn(name="loveGroupId")
	public LoveGroup getLoveGroup() {
		return loveGroup;
	}
	public void setLoveGroup(LoveGroup loveGroup) {
		this.loveGroup = loveGroup;
	}
	@ManyToMany
	@JoinTable(name="performance_loveType",
			joinColumns={@JoinColumn(name="loveTypeId")},
			inverseJoinColumns={@JoinColumn(name="performanceId")}
	)
	public Set<Performance> getPerformance() {
		return performance;
	}
	public void setPerformance(Set<Performance> performance) {
		this.performance = performance;
	}
	public String getCharacteristic1() {
		return Characteristic1;
	}
	public void setCharacteristic1(String characteristic1) {
		Characteristic1 = characteristic1;
	}
	public String getCharacteristic2() {
		return Characteristic2;
	}
	public void setCharacteristic2(String characteristic2) {
		Characteristic2 = characteristic2;
	}
	public String getCharacteristic3() {
		return Characteristic3;
	}
	public void setCharacteristic3(String characteristic3) {
		Characteristic3 = characteristic3;
	}
	public String getLovingOne() {
		return lovingOne;
	}
	public void setLovingOne(String lovingOne) {
		this.lovingOne = lovingOne;
	}
	public String getLovingTwe() {
		return lovingTwe;
	}
	public void setLovingTwe(String lovingTwe) {
		this.lovingTwe = lovingTwe;
	}
	public String getLovingThree() {
		return lovingThree;
	}
	public void setLovingThree(String lovingThree) {
		this.lovingThree = lovingThree;
	}
	
	
}
