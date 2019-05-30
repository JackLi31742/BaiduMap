package com.springmvc.entity;

public class Normal{
	
	private String color;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Normal(String color) {
		super();
		this.color = color;
	}

	public Normal() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Normal [color=" + color + "]";
	}
	
}