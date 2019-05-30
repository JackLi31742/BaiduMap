package com.springmvc.entity;

public class Targets {

	private String name;
	private String id_photo;
	private String danger_type;
	private double prob;
	private int id;
	
	private int age;
	
	private Attributes attributes;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getId_photo() {
		return id_photo;
	}


	public void setId_photo(String id_photo) {
		this.id_photo = id_photo;
	}


	public double getProb() {
		return prob;
	}


	public void setProb(double prob) {
		this.prob = prob;
	}


	

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Targets() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public Attributes getAttributes() {
		return attributes;
	}


	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}


	public String getDanger_type() {
		return danger_type;
	}


	public void setDanger_type(String danger_type) {
		this.danger_type = danger_type;
	}


	public Targets(String name, String id_photo, String danger_type, double prob, int id, int age,
			Attributes attributes) {
		super();
		this.name = name;
		this.id_photo = id_photo;
		this.danger_type = danger_type;
		this.prob = prob;
		this.id = id;
		this.age = age;
		this.attributes = attributes;
	}


	@Override
	public String toString() {
		return "Targets [name=" + name + ", id_photo=" + id_photo + ", danger_type=" + danger_type + ", prob=" + prob
				+ ", id=" + id + ", age=" + age + ", attributes=" + attributes + "]";
	}
	
}
