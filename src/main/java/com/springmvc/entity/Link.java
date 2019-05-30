package com.springmvc.entity;

public class Link {

	private int sourseId;
	private int targetId;
	private String detail;
	public int getSourseId() {
		return sourseId;
	}
	public void setSourseId(int sourseId) {
		this.sourseId = sourseId;
	}
	public int getTargetId() {
		return targetId;
	}
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Link(int sourseId, int targetId, String detail) {
		super();
		this.sourseId = sourseId;
		this.targetId = targetId;
		this.detail = detail;
	}
	public Link() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Link [sourseId=" + sourseId + ", targetId=" + targetId + ", detail=" + detail + "]";
	}
	
	
	
}
