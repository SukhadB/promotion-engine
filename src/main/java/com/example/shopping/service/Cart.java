package com.example.shopping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.shopping.model.Product;

public class Cart implements ICart{
	
	private List<Product> productList;
	private double totalAmount;
	private Map<String, Integer> productCount = new HashMap<>();
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public List<Product> getProducts() {
		return productList;
	}
	
	public Map<String, Integer> getProductCount(){
		return this.productCount;
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
		Integer count = this.productCount.get(product.getSKUId());
		if (count == null) {
			count = 0;
		}
		this.productCount.put(product.getSKUId(), ++count);
		this.productList.add(product);
	}
	
	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", totalAmount=" + totalAmount + "]";
	}
	
	public double checkout() {
		return 0;
	}
	
	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap();
		Integer i = map.get("A");
		if (i == null) {
			i = 0;
		}
		System.out.println(++i);
		
	}

}
