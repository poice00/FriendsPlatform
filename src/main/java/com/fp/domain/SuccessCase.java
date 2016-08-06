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

import org.hibernate.annotations.Type;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
/**
 * 红娘
 * @author ssy
 *
 */
@Entity
public class SuccessCase {
	
	private Long id ;
	private String pic;
	private String tip;
	private String femalePic;
	private String femaleName;
	private String femaleAge;
	private String femaleYear;
	private String femaleMonth;
	private String malePic;
	private String maleName;
	private String maleAge;
	private String maleYear;
	private String maleMonth;
	private String matchMaketips;
	
	private Matchmaker matchMaker;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getFemalePic() {
		return femalePic;
	}

	public void setFemalePic(String femalePic) {
		this.femalePic = femalePic;
	}

	public String getFemaleName() {
		return femaleName;
	}

	public void setFemaleName(String femaleName) {
		this.femaleName = femaleName;
	}

	public String getFemaleAge() {
		return femaleAge;
	}

	public void setFemaleAge(String femaleAge) {
		this.femaleAge = femaleAge;
	}

	public String getFemaleYear() {
		return femaleYear;
	}

	public void setFemaleYear(String femaleYear) {
		this.femaleYear = femaleYear;
	}

	public String getFemaleMonth() {
		return femaleMonth;
	}

	public void setFemaleMonth(String femaleMonth) {
		this.femaleMonth = femaleMonth;
	}

	public String getMalePic() {
		return malePic;
	}

	public void setMalePic(String malePic) {
		this.malePic = malePic;
	}

	public String getMaleName() {
		return maleName;
	}

	public void setMaleName(String maleName) {
		this.maleName = maleName;
	}

	public String getMaleAge() {
		return maleAge;
	}

	public void setMaleAge(String maleAge) {
		this.maleAge = maleAge;
	}

	public String getMaleYear() {
		return maleYear;
	}

	public void setMaleYear(String maleYear) {
		this.maleYear = maleYear;
	}

	public String getMaleMonth() {
		return maleMonth;
	}

	public void setMaleMonth(String maleMonth) {
		this.maleMonth = maleMonth;
	}
	@Type(type="text")
	public String getMatchMaketips() {
		return matchMaketips;
	}

	public void setMatchMaketips(String matchMaketips) {
		this.matchMaketips = matchMaketips;
	}
	@ManyToOne
	@JoinColumn(name="matchMakerId")
	public Matchmaker getMatchMaker() {
		return matchMaker;
	}

	public void setMatchMaker(Matchmaker matchMaker) {
		this.matchMaker = matchMaker;
	}
	
}
