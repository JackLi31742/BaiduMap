package com.springmvc.entity;

import java.util.List;

public class People {
	private String name;
	private PeopleAttributes attributes;
	private String category;
	private double prob;
	
	private int symbolSize;
	
	private ItemStyle itemStyle;

	private List<Link> links;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PeopleAttributes getAttributes() {
		return attributes;
	}

	public void setAttributes(PeopleAttributes attributes) {
		this.attributes = attributes;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getProb() {
		return prob;
	}

	public void setProb(double prob) {
		this.prob = prob;
	}

	public int getSymbolSize() {
		return symbolSize;
	}

	public void setSymbolSize(int symbolSize) {
		this.symbolSize = symbolSize;
	}

	public ItemStyle getItemStyle() {
		return itemStyle;
	}

	public void setItemStyle(ItemStyle itemStyle) {
		this.itemStyle = itemStyle;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public People() {
		super();
		// TODO Auto-generated constructor stub
	}

	public People(String name, PeopleAttributes attributes, String category, double prob, int symbolSize,
			ItemStyle itemStyle, List<Link> links) {
		super();
		this.name = name;
		this.attributes = attributes;
		this.category = category;
		this.prob = prob;
		this.symbolSize = symbolSize;
		this.itemStyle = itemStyle;
		this.links = links;
	}

	@Override
	public String toString() {
		return "People [name=" + name + ", attributes=" + attributes + ", category=" + category + ", prob=" + prob
				+ ", symbolSize=" + symbolSize + ", itemStyle=" + itemStyle + ", links=" + links + "]";
	}

	
	
	
	
}
