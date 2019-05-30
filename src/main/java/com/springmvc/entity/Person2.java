package com.springmvc.entity;

public class Person2 {

	private String faceId;
	private String name;
	
	
	private int age;
	
	private String gender;
	
	private String camera_id;
	
	private String pic_path;
	
	private long timestamp;
	
	private String face_feature;
	
	private String relation_ids;

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCamera_id() {
		return camera_id;
	}

	public void setCamera_id(String camera_id) {
		this.camera_id = camera_id;
	}

	public String getPic_path() {
		return pic_path;
	}

	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getFace_feature() {
		return face_feature;
	}

	public void setFace_feature(String face_feature) {
		this.face_feature = face_feature;
	}

	public String getRelation_ids() {
		return relation_ids;
	}

	public void setRelation_ids(String relation_ids) {
		this.relation_ids = relation_ids;
	}

	public Person2(String faceId, String name, int age, String gender, String camera_id, String pic_path,
			long timestamp, String face_feature, String relation_ids) {
		super();
		this.faceId = faceId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.camera_id = camera_id;
		this.pic_path = pic_path;
		this.timestamp = timestamp;
		this.face_feature = face_feature;
		this.relation_ids = relation_ids;
	}

	public Person2() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Person2 [faceId=" + faceId + ", name=" + name + ", age=" + age + ", gender=" + gender + ", camera_id="
				+ camera_id + ", pic_path=" + pic_path + ", timestamp=" + timestamp + ", face_feature=" + face_feature
				+ ", relation_ids=" + relation_ids + "]";
	}

	
	
	
}
