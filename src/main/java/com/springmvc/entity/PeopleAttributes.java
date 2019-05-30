package com.springmvc.entity;

public class PeopleAttributes {

	private int age;
	private int sex;
	private String job;
	private String event;
	private String emotion;
	private String detail;
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEmotion() {
		return emotion;
	}
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public PeopleAttributes(int age, int sex, String job, String event, String emotion, String detail) {
		super();
		this.age = age;
		this.sex = sex;
		this.job = job;
		this.event = event;
		this.emotion = emotion;
		this.detail = detail;
	}
	public PeopleAttributes() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PeopleAttributes [age=" + age + ", sex=" + sex + ", job=" + job + ", event=" + event + ", emotion="
				+ emotion + ", detail=" + detail + "]";
	}
	
	
	
}
