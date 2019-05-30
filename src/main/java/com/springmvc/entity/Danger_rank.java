package com.springmvc.entity;

public class Danger_rank {

	private String danger_type;
	private int num;
	
	private Targets targets;

	public String getDanger_type() {
		return danger_type;
	}

	public void setDanger_type(String danger_type) {
		this.danger_type = danger_type;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Targets getTargets() {
		return targets;
	}

	public void setTargets(Targets targets) {
		this.targets = targets;
	}

	public Danger_rank(String danger_type, int num, Targets targets) {
		super();
		this.danger_type = danger_type;
		this.num = num;
		this.targets = targets;
	}

	public Danger_rank() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Danger_rank [danger_type=" + danger_type + ", num=" + num + ", targets=" + targets + "]";
	}

	
	
	
}
