package com.springmvc.entity;

public class Danger_level {
	
	private String danger_type;
	private int score;
	private Factors factors;
	public String getDanger_type() {
		return danger_type;
	}
	public void setDanger_type(String danger_type) {
		this.danger_type = danger_type;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Factors getFactors() {
		return factors;
	}
	public void setFactors(Factors factors) {
		this.factors = factors;
	}
	public Danger_level(String danger_type, int score, Factors factors) {
		super();
		this.danger_type = danger_type;
		this.score = score;
		this.factors = factors;
	}
	public Danger_level() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Danger [danger_type=" + danger_type + ", score=" + score + ", factors=" + factors + "]";
	}
	
	
 
}
