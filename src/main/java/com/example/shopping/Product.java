package com.example.shopping;

public class Product {
	
	private String id;
	private double price;
	
	public Product(String id) {
		super();
		this.id = id;
		
		if (id.equals("A")) {
			this.price = 50;
		} else if (id.equals("B")) {
			this.price = 30;
		} else if (id.equals("C")) {
			this.price = 20;
		} else if (id.equals("D")) {
			this.price = 15;
		}
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	

}
