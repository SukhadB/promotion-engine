package com.example.shopping.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory {

	private Map<String, Product> listedProducts = new HashMap<>();
	private Map<String, PromotionConfiguration> promotions = new HashMap<>();
	
	public Inventory(Map<String, Product> listedProducts, Map<String, PromotionConfiguration> promotions) {
		this.listedProducts = listedProducts;
		this.promotions = promotions;
	}

	public Map<String, Product> getListedProducts() {
		return listedProducts;
	}

	public Map<String, PromotionConfiguration> getPromotions() {
		return promotions;
	}

}
