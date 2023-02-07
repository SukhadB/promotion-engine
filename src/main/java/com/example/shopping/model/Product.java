package com.example.shopping.model;

import com.example.shopping.exception.ProductNotPresentInInvetory;

public class Product {
	
	private String skuId;
	private double price;
	
	public Product(String id) {
		this.skuId = id;
		
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
	
	public Product(String id, double price) {
		this.skuId = id;
		this.price = price;
	}
	
	public Product(Product product) throws ProductNotPresentInInvetory {
		if (product == null) {
			 throw new IllegalArgumentException("Item cannot be null.");
		}
		
		this.skuId = product.skuId;
        this.price = product.getPrice();
	}

	public String getSKUId() {
		return skuId;
	}
	public void setSKUId(String id) {
		this.skuId = id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + skuId + ", price=" + price + "]";
	}
	

}
