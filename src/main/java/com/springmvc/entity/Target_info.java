package com.springmvc.entity;

public class Target_info {
	
	private String name;
	private int age;
	private String id_photo;
	
	private Attributes attributes;

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

	public String getId_photo() {
		return id_photo;
	}

	public void setId_photo(String id_photo) {
		this.id_photo = id_photo;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public Target_info(String name, int age, String id_photo, Attributes attributes) {
		super();
		this.name = name;
		this.age = age;
		this.id_photo = id_photo;
		this.attributes = attributes;
	}

	public Target_info() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Target_info [name=" + name + ", age=" + age + ", id_photo=" + id_photo + ", attributes=" + attributes
				+ "]";
	}
	
	
}
