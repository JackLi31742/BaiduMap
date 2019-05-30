package com.springmvc.entity;

public class Event {

	private String camera_id;
	
	private String event_type;
	
	private long start_time;
	private long end_time;
	private long timestamp;
	
	private String pic_path;
	private String describe;
	public String getCamera_id() {
		return camera_id;
	}
	public void setCamera_id(String camera_id) {
		this.camera_id = camera_id;
	}
	public String getEvent_type() {
		return event_type;
	}
	public void setEvent_type(String event_type) {
		this.event_type = event_type;
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
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public Event(String camera_id, String event_type, long start_time, long end_time, long timestamp, String pic_path,
			String describe) {
		super();
		this.camera_id = camera_id;
		this.event_type = event_type;
		this.start_time = start_time;
		this.end_time = end_time;
		this.timestamp = timestamp;
		this.pic_path = pic_path;
		this.describe = describe;
	}
	
	
}
