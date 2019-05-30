package com.springmvc.entity;

public class Black {

	private String age;
	private String alarm_device;
	private String alarm_snapshot;
	private String alarm_time;
	private String alarm_type;
	private String face_id;
	private String gender;
	private String name;
	private String start_time;
	private String end_time;
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getAlarm_device() {
		return alarm_device;
	}
	public void setAlarm_device(String alarm_device) {
		this.alarm_device = alarm_device;
	}
	public String getAlarm_snapshot() {
		return alarm_snapshot;
	}
	public void setAlarm_snapshot(String alarm_snapshot) {
		this.alarm_snapshot = alarm_snapshot;
	}
	public String getAlarm_time() {
		return alarm_time;
	}
	public void setAlarm_time(String alarm_time) {
		this.alarm_time = alarm_time;
	}
	public String getAlarm_type() {
		return alarm_type;
	}
	public void setAlarm_type(String alarm_type) {
		this.alarm_type = alarm_type;
	}
	public String getFace_id() {
		return face_id;
	}
	public void setFace_id(String face_id) {
		this.face_id = face_id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public Black() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Black(String age, String alarm_device, String alarm_snapshot, String alarm_time, String alarm_type,
			String face_id, String gender, String name, String start_time, String end_time) {
		super();
		this.age = age;
		this.alarm_device = alarm_device;
		this.alarm_snapshot = alarm_snapshot;
		this.alarm_time = alarm_time;
		this.alarm_type = alarm_type;
		this.face_id = face_id;
		this.gender = gender;
		this.name = name;
		this.start_time = start_time;
		this.end_time = end_time;
	}
	@Override
	public String toString() {
		return "Black [age=" + age + ", alarm_device=" + alarm_device + ", alarm_snapshot=" + alarm_snapshot
				+ ", alarm_time=" + alarm_time + ", alarm_type=" + alarm_type + ", face_id=" + face_id + ", gender="
				+ gender + ", name=" + name + ", start_time=" + start_time + ", end_time=" + end_time + "]";
	}
	
	
	
	
}
