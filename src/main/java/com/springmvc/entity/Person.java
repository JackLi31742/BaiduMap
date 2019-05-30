package com.springmvc.entity;

import java.math.BigDecimal;

public class Person {

	private String event;
	private String picPath;
	//经度
	private BigDecimal longitude;
	//纬度
	private BigDecimal latitude;
	
	private int camId;
	
	private String startTime;
	
	private String endTime;
	
	private String detail;
	
	private String iframe;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public int getCamId() {
		return camId;
	}

	public void setCamId(int camId) {
		this.camId = camId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Person [event=" + event + ", picPath=" + picPath + ", longitude=" + longitude + ", latitude=" + latitude
				+ ", camId=" + camId + ", startTime=" + startTime + ", endTime=" + endTime + ", detail=" + detail + "]";
	}
	
	
	
}
