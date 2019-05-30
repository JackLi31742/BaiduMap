package com.springmvc.entity;

import java.math.BigDecimal;

public class Camera {
	
	//经度
		private BigDecimal longitude;
		//纬度
		private BigDecimal latitude;
		
		private String camera_id;
		private String platform_id;
		private String camera_ip;
		private String username;
		private String password;
		
		private int camera_port;
		
		private double height;
		
		private String detail;

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

		public String getCamera_id() {
			return camera_id;
		}

		public void setCamera_id(String camera_id) {
			this.camera_id = camera_id;
		}

		public String getPlatform_id() {
			return platform_id;
		}

		public void setPlatform_id(String platform_id) {
			this.platform_id = platform_id;
		}

		public String getCamera_ip() {
			return camera_ip;
		}

		public void setCamera_ip(String camera_ip) {
			this.camera_ip = camera_ip;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getCamera_port() {
			return camera_port;
		}

		public void setCamera_port(int camera_port) {
			this.camera_port = camera_port;
		}

		public double getHeight() {
			return height;
		}

		public void setHeight(double height) {
			this.height = height;
		}

		public String getDetail() {
			return detail;
		}

		public void setDetail(String detail) {
			this.detail = detail;
		}

		@Override
		public String toString() {
			return "Camera [longitude=" + longitude + ", latitude=" + latitude + ", camera_id=" + camera_id
					+ ", platform_id=" + platform_id + ", camera_ip=" + camera_ip + ", username=" + username
					+ ", password=" + password + ", camera_port=" + camera_port + ", height=" + height + ", detail="
					+ detail + "]";
		}

		
		
		
		

}
