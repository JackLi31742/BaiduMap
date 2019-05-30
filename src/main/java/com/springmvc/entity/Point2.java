package com.springmvc.entity;

public class Point2 {

   //经度
 	private Double lng;
 	//纬度
 	private Double lat;
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	@Override
	public String toString() {
		return "Point2 [lng=" + lng + ", lat=" + lat + "]";
	}
	public Point2(Double lng, Double lat) {
		super();
		this.lng = lng;
		this.lat = lat;
	}
 	
 	
}
