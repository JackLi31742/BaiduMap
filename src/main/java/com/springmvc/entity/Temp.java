package com.springmvc.entity;

import java.math.BigDecimal;

public class Temp {

	//经度
			private BigDecimal longitude;
			//纬度
			private BigDecimal latitude;
			
			private String name;
			
			private long start_time;
			private long end_time;
			private long timestamp;
			private String start_timeStr;
			private String end_timeStr;
			private String timestampStr;
			private String pic_path;
			private String camera_id;
			private String type;
			private String describe;
			private String gender;
			private int age;
			private int catalog;
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
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public long getStart_time() {
				return start_time;
			}
			public void setStart_time(long start_time) {
				this.start_time = start_time;
			}
			public long getEnd_time() {
				return end_time;
			}
			public void setEnd_time(long end_time) {
				this.end_time = end_time;
			}
			public long getTimestamp() {
				return timestamp;
			}
			public void setTimestamp(long timestamp) {
				this.timestamp = timestamp;
			}
			public String getPic_path() {
				return pic_path;
			}
			public void setPic_path(String pic_path) {
				this.pic_path = pic_path;
			}
			public String getCamera_id() {
				return camera_id;
			}
			public void setCamera_id(String camera_id) {
				this.camera_id = camera_id;
			}
			
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
				this.gender = gender;
			}
			public int getAge() {
				return age;
			}
			public void setAge(int age) {
				this.age = age;
			}
			public String getDescribe() {
				return describe;
			}
			public void setDescribe(String describe) {
				this.describe = describe;
			}
			public String getStart_timeStr() {
				return start_timeStr;
			}
			public void setStart_timeStr(String start_timeStr) {
				this.start_timeStr = start_timeStr;
			}
			public String getEnd_timeStr() {
				return end_timeStr;
			}
			public void setEnd_timeStr(String end_timeStr) {
				this.end_timeStr = end_timeStr;
			}
			public String getTimestampStr() {
				return timestampStr;
			}
			public void setTimestampStr(String timestampStr) {
				this.timestampStr = timestampStr;
			}
			
			public Temp() {
				super();
				// TODO Auto-generated constructor stub
			}
			public String getType() {
				return type;
			}
			public void setType(String type) {
				this.type = type;
			}
			
			
			public int getCatalog() {
				return catalog;
			}
			public void setCatalog(int catalog) {
				this.catalog = catalog;
			}
			
			
			public Temp(BigDecimal longitude, BigDecimal latitude, String name, long start_time, long end_time,
					long timestamp, String start_timeStr, String end_timeStr, String timestampStr, String pic_path,
					String camera_id, String type, String describe, String gender, int age, int catalog) {
				super();
				this.longitude = longitude;
				this.latitude = latitude;
				this.name = name;
				this.start_time = start_time;
				this.end_time = end_time;
				this.timestamp = timestamp;
				this.start_timeStr = start_timeStr;
				this.end_timeStr = end_timeStr;
				this.timestampStr = timestampStr;
				this.pic_path = pic_path;
				this.camera_id = camera_id;
				this.type = type;
				this.describe = describe;
				this.gender = gender;
				this.age = age;
				this.catalog = catalog;
			}
			
			
			@Override
			public String toString() {
				return "Temp [longitude=" + longitude + ", latitude=" + latitude + ", name=" + name + ", start_time="
						+ start_time + ", end_time=" + end_time + ", timestamp=" + timestamp + ", start_timeStr="
						+ start_timeStr + ", end_timeStr=" + end_timeStr + ", timestampStr=" + timestampStr
						+ ", pic_path=" + pic_path + ", camera_id=" + camera_id + ", type=" + type + ", describe="
						+ describe + ", gender=" + gender + ", age=" + age + ", catalog=" + catalog + "]";
			}
			public Temp(BigDecimal longitude, BigDecimal latitude) {
				super();
				this.longitude = longitude;
				this.latitude = latitude;
			}
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
				result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
				return result;
			}
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Temp other = (Temp) obj;
				if (latitude == null) {
					if (other.latitude != null)
						return false;
				} else if (!latitude.equals(other.latitude))
					return false;
				if (longitude == null) {
					if (other.longitude != null)
						return false;
				} else if (!longitude.equals(other.longitude))
					return false;
				if (latitude!=null&&longitude!=null) {
					if (latitude.equals(other.latitude)&&longitude.equals(other.longitude)) {
						return true;
					}
				}
				return true;
			}
			
			
			
			
			
			
			
			
			
}
