package com.springmvc.entity;

public class Attributes {
	
	private String mental;
	private String criminal;
	private String family;
	private String education;
	public String getMental() {
		return mental;
	}
	public void setMental(String mental) {
		this.mental = mental;
	}
	public String getCriminal() {
		return criminal;
	}
	public void setCriminal(String criminal) {
		this.criminal = criminal;
	}
	public String getFamily() {
		return family;
	}
	public void setFamily(String family) {
		this.family = family;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public Attributes(String mental, String criminal, String family, String education) {
		super();
		this.mental = mental;
		this.criminal = criminal;
		this.family = family;
		this.education = education;
	}
	public Attributes() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Attributes [mental=" + mental + ", criminal=" + criminal + ", family=" + family + ", education="
				+ education + "]";
	}
	
	

}
