package com.example.shopping;

import java.util.Map;

public class Promotion {

	private String promotionCode;
	private Map<String, Integer> productDetails;
	private int promotionalCost;

	public Promotion(String promotionCode, Map<String, Integer> productDetails, int promotionalCost) {
		super();
		this.promotionCode = promotionCode;
		this.productDetails = productDetails;
		this.promotionalCost = promotionalCost;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public Map<String, Integer> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(Map<String, Integer> productDetails) {
		this.productDetails = productDetails;
	}

	public int getPromotionalCost() {
		return promotionalCost;
	}

	public void setPromotionalCost(int promotionalCost) {
		this.promotionalCost = promotionalCost;
	}

	@Override
	public String toString() {
		return "Promotion [promotionCode=" + promotionCode + ", productDetails=" + productDetails + ", promotionalCost="
				+ promotionalCost + "]";
	}
	
}
