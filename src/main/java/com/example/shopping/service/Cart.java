package com.example.shopping.service;

import java.util.ArrayList;
import java.util.List;

import com.example.shopping.model.Inventory;
import com.example.shopping.model.Product;

public class Cart implements ICart{
	
	private List<Product> productList;
	private double totalAmount;
	
	private Inventory inventory;
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public List<Product> getProducts() {
		return productList;
	}
	
	@Override
	public void empty() {
		// TODO Auto-generated method stub
		
	}

	public void add(List<Product> productList) {
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

	@Override
	public void add(Product product) {
		if(this.productList == null) {
			this.productList = new ArrayList<Product>();
		}
		this.totalAmount += product.getPrice();
		this.productList.add(product);
	}
	
	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", totalAmount=" + totalAmount + "]";
	}
	
	public double checkout() {
		return 0;
	}

}
