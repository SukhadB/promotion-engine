package com.example.shopping;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	
	private List<Product> productList;
	private double totalAmount;
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

	public double calculateTotalCartValue() {
		double totalCost = 0;
		for (Product product : productList) {
			totalCost += product.getPrice();
		}
		this.totalAmount = totalCost;
		return totalCost;
	}

	public void addProduct(Product product) {
		if(this.productList == null) {
			this.productList = new ArrayList<Product>();
		}
		this.totalAmount += product.getPrice();
		this.productList.add(product);		
	}
	
	public double checkout() {
		double totalCartCost = 0;
		
		return totalCartCost;
	}

	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", totalAmount=" + totalAmount + "]";
	}
	
	

}
