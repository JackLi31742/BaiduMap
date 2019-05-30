package com.springmvc.entity;

import java.math.BigDecimal;

public class Point {
	//经度
	private BigDecimal lng;
	//纬度
	private BigDecimal lat;
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "Point [lng=" + lng + ", lat=" + lat + "]";
	}
	
	
}
