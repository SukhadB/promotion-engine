package com.example.shopping.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

public class Cart implements ICart{
	
	private List<Product> productList;
	private double totalAmount;
	private Map<String, Integer> productCount = new HashMap<>();
	private PromotionEngine promotionEngine;
	
	public Cart() {
		
	}
	
	public Cart(PromotionEngine promotionEngine) {
		this.promotionEngine = promotionEngine;
	}
	
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
		double finalPrice = 0;
		
		if (productList != null) {
		
			Map<String, List<Product>> groupProducts = productList.stream().collect(Collectors.groupingBy(Product::getSKUId));
			
			for (Map.Entry<String, List<Product>> listProductEntry : groupProducts.entrySet()) {
				finalPrice += promotionEngine.findPromo(listProductEntry.getKey(), listProductEntry.getValue().size()).getProcessor().applyPromotion(listProductEntry.getValue());
	        }
		}
		
		return finalPrice;
	}
	
	public static void main(String[] args) {
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("A", 3);
		
		Promotion promotion1 = new Promotion("P1", promoDetails1, 130);
		
		Map<String, Integer> promoDetails2 = new HashMap<>();
		promoDetails2.put("B", 2);
		
		Promotion promotion2 = new Promotion("P2", promoDetails2, 45);
		
		Map<String, Integer> promoDetails3 = new HashMap<>();
		promoDetails3.put("C", 1);
		promoDetails3.put("D", 1);
		
		Promotion promotion3 = new Promotion("P3", promoDetails3, 30);
		
		List<Promotion> promotionList = new ArrayList<>();
		promotionList.add(promotion1);
		promotionList.add(promotion2);
		promotionList.add(promotion3);
		
		System.out.println(promotionList);
		PromotionEngine promotionEngine = new PromotionEngine();
		promotionEngine.setPromotionList(promotionList);
		
		Cart cart = new Cart(promotionEngine);
		
		Product product = new Product("A");
		cart.add(product);
		product = new Product("A");
		cart.add(product);
		product = new Product("B");
		cart.add(product);
		product = new Product("C");
		cart.add(product);
		
		System.out.println(cart.checkout());
		
		
		
		
		
	}

}
