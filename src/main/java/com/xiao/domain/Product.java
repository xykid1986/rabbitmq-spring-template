package com.xiao.domain;

import javax.xml.bind.annotation.*;


@SuppressWarnings("restriction")
@XmlRootElement
public class Product {
	
	
	private String name;
	
	private int price;
	
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XmlElement
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
