package com.fp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
@Entity
public class Info {
	
	private Long id ;
	private String headImg ;//头像
	private String nickName ;//昵称
	private String realName ;// 真实姓名
	private String realNameID ;//实名ID 
	private String loveType ;// 恋爱类型 
	private String age ;// 年龄 
	private int sex ;// 性别 
	private String height ;// 身高 
	private String education;// 学历  
	private String Ismarraied;// 婚否 
	private String introduction;// 交友宣言  
	private String city ;// 户口 
	private String liveCity ;// 居住地 
	private String nation;// 民族 
	private String homeCity ;// 家乡 
	private String animal;// 属相      
	private String astroLogical ;// 星座 
	private String bloodType;// 血型 
	private String bodyType ;// 体型 
	private String weigth ;// 体重 
	private String job ;// 职业 
	private String salary ;// 月薪 
	private String house;// 购房 
	private String car;// 购车 
	private String lauauge ;// 掌握语言 
	private String school;// 毕业学校 
	private String majorDirection;// 所学专业
	private String religion;// 宗教信仰
	private String liveStyle;// 生活作息
	private String children;// 有无子女
	private String selfScore;// 相貌自评
	private String IsSmoke;// 是否吸烟
	private String IsDrink;// 是否喝酒
	private String companyType;// 公司性质
	private String companyIndustry;// 公司行业
	private String jobState;// 工作状况
	private String familyRank;// 家中排行
	private String parentLive;// 父母情况
	private String fatherJob;// 父亲工作
	private String motherJob ;// 母亲工作
	private String parentIncome ;// 父母经济
	private String parentHealthInsure;// 父母医保
	private String IsLiveParent;// 愿与长辈同住
	private String whenMarry;// 想何时结婚
	private String needChild;// 是否想要小孩
	private String AppointmentStyle;// 偏爱约会方式
	private String hopeImportant;// 希望对方看重
	private String expectWedding;// 期待婚礼形式
	private String cook;// 厨艺状况
	private String hourseHold;// 家务分工
	//==============择偶意向===============
	private String mateName ;//年龄
	private String mateHeight ;//身高
	private String mateEduction ;// 学历
	private String mateSalary ;//月收入
	private String mateLive ;//所在地区
	private String mateMarried ;//婚姻状况
	private String mateHourse ;//购房情况
	private String mateIsChildren ;//有无子女
	
	private User user;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}


	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getRealNameID() {
		return realNameID;
	}

	public void setRealNameID(String realNameID) {
		this.realNameID = realNameID;
	}

	public String getLoveType() {
		return loveType;
	}

	public void setLoveType(String loveType) {
		this.loveType = loveType;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getIsmarraied() {
		return Ismarraied;
	}

	public void setIsmarraied(String ismarraied) {
		Ismarraied = ismarraied;
	}
	@Type(type="text")
	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public String getAstroLogical() {
		return astroLogical;
	}

	public void setAstroLogical(String astroLogical) {
		this.astroLogical = astroLogical;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public String getWeigth() {
		return weigth;
	}

	public void setWeigth(String weigth) {
		this.weigth = weigth;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getCar() {
		return car;
	}

	public void setCar(String car) {
		this.car = car;
	}

	public String getLauauge() {
		return lauauge;
	}

	public void setLauauge(String lauauge) {
		this.lauauge = lauauge;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getMajorDirection() {
		return majorDirection;
	}

	public void setMajorDirection(String majorDirection) {
		this.majorDirection = majorDirection;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getLiveStyle() {
		return liveStyle;
	}

	public void setLiveStyle(String liveStyle) {
		this.liveStyle = liveStyle;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public String getSelfScore() {
		return selfScore;
	}

	public void setSelfScore(String selfScore) {
		this.selfScore = selfScore;
	}

	public String getIsSmoke() {
		return IsSmoke;
	}

	public void setIsSmoke(String isSmoke) {
		IsSmoke = isSmoke;
	}

	public String getIsDrink() {
		return IsDrink;
	}

	public void setIsDrink(String isDrink) {
		IsDrink = isDrink;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyIndustry() {
		return companyIndustry;
	}

	public void setCompanyIndustry(String companyIndustry) {
		this.companyIndustry = companyIndustry;
	}

	public String getJobState() {
		return jobState;
	}

	public void setJobState(String jobState) {
		this.jobState = jobState;
	}

	public String getFamilyRank() {
		return familyRank;
	}

	public void setFamilyRank(String familyRank) {
		this.familyRank = familyRank;
	}

	public String getParentLive() {
		return parentLive;
	}

	public void setParentLive(String parentLive) {
		this.parentLive = parentLive;
	}

	public String getFatherJob() {
		return fatherJob;
	}

	public void setFatherJob(String fatherJob) {
		this.fatherJob = fatherJob;
	}

	public String getMotherJob() {
		return motherJob;
	}

	public void setMotherJob(String motherJob) {
		this.motherJob = motherJob;
	}

	public String getParentIncome() {
		return parentIncome;
	}

	public void setParentIncome(String parentIncome) {
		this.parentIncome = parentIncome;
	}

	public String getParentHealthInsure() {
		return parentHealthInsure;
	}

	public void setParentHealthInsure(String parentHealthInsure) {
		this.parentHealthInsure = parentHealthInsure;
	}

	public String getIsLiveParent() {
		return IsLiveParent;
	}

	public void setIsLiveParent(String isLiveParent) {
		IsLiveParent = isLiveParent;
	}

	public String getWhenMarry() {
		return whenMarry;
	}

	public void setWhenMarry(String whenMarry) {
		this.whenMarry = whenMarry;
	}

	public String getNeedChild() {
		return needChild;
	}

	public void setNeedChild(String needChild) {
		this.needChild = needChild;
	}

	public String getAppointmentStyle() {
		return AppointmentStyle;
	}

	public void setAppointmentStyle(String appointmentStyle) {
		AppointmentStyle = appointmentStyle;
	}

	public String getHopeImportant() {
		return hopeImportant;
	}

	public void setHopeImportant(String hopeImportant) {
		this.hopeImportant = hopeImportant;
	}

	public String getExpectWedding() {
		return expectWedding;
	}

	public void setExpectWedding(String expectWedding) {
		this.expectWedding = expectWedding;
	}

	public String getCook() {
		return cook;
	}

	public void setCook(String cook) {
		this.cook = cook;
	}

	public String getHourseHold() {
		return hourseHold;
	}

	public void setHourseHold(String hourseHold) {
		this.hourseHold = hourseHold;
	}

	public String getMateName() {
		return mateName;
	}

	public void setMateName(String mateName) {
		this.mateName = mateName;
	}

	public String getMateHeight() {
		return mateHeight;
	}

	public void setMateHeight(String mateHeight) {
		this.mateHeight = mateHeight;
	}

	public String getMateEduction() {
		return mateEduction;
	}

	public void setMateEduction(String mateEduction) {
		this.mateEduction = mateEduction;
	}

	public String getMateSalary() {
		return mateSalary;
	}

	public void setMateSalary(String mateSalary) {
		this.mateSalary = mateSalary;
	}

	public String getMateLive() {
		return mateLive;
	}

	public void setMateLive(String mateLive) {
		this.mateLive = mateLive;
	}

	public String getMateMarried() {
		return mateMarried;
	}

	public void setMateMarried(String mateMarried) {
		this.mateMarried = mateMarried;
	}

	public String getMateHourse() {
		return mateHourse;
	}

	public void setMateHourse(String mateHourse) {
		this.mateHourse = mateHourse;
	}

	public String getMateIsChildren() {
		return mateIsChildren;
	}

	public void setMateIsChildren(String mateIsChildren) {
		this.mateIsChildren = mateIsChildren;
	}

	public void setId(Long id) {
		this.id = id;
	}
	@ManyToOne
	@JoinColumn(unique=true,name="userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}

	@Column(nullable=false)
	public int getSex() {
		return sex;
	}


	public void setSex(int sex) {
		this.sex = sex;
	}
	
}
