package com.springmvc.entity;

public class PeopleLink {

	private People sourcePeople;
	private People targetPeople;
	private String source;
	private String target;
	private String name;
	private String des;
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public PeopleLink(String source, String target, String name, String des) {
		super();
		this.source = source;
		this.target = target;
		this.name = name;
		this.des = des;
	}
	public PeopleLink() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PeopleLink [source=" + source + ", target=" + target + ", name=" + name + ", des=" + des + "]";
	}
	
	
}
