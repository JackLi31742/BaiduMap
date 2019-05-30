package com.springmvc.entity;

public class Factors {

	private int factorA;
	private int factorB;
	private int factorC;
	public int getFactorA() {
		return factorA;
	}
	public void setFactorA(int factorA) {
		this.factorA = factorA;
	}
	public int getFactorB() {
		return factorB;
	}
	public void setFactorB(int factorB) {
		this.factorB = factorB;
	}
	public int getFactorC() {
		return factorC;
	}
	public void setFactorC(int factorC) {
		this.factorC = factorC;
	}
	public Factors(int factorA, int factorB, int factorC) {
		super();
		this.factorA = factorA;
		this.factorB = factorB;
		this.factorC = factorC;
	}
	public Factors() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Factors [factorA=" + factorA + ", factorB=" + factorB + ", factorC=" + factorC + "]";
	}
	
	
	
}
