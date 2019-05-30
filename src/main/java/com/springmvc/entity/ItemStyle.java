package com.springmvc.entity;

public class ItemStyle {

	private Normal normal;

	public Normal getNormal() {
		return normal;
	}

	public void setNormal(Normal normal) {
		this.normal = normal;
	}

	public ItemStyle(Normal normal) {
		super();
		this.normal = normal;
	}

	public ItemStyle() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ItemStyle [normal=" + normal + "]";
	}
	
	
}


