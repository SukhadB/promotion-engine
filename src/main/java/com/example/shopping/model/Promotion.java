package com.example.shopping.model;

import java.util.Map;

import com.example.shopping.processor.ComplexPromoProcessor;
import com.example.shopping.processor.DefaultPromoProcessor;
import com.example.shopping.processor.IPromoProcessor;
import com.example.shopping.processor.SimplePromoProcessor;

public class Promotion {

	private String promoCode;
	private Map<String, Integer> promoDetails;
	private int promotionalCost;
	private String promotionType;
	private IPromoProcessor processor;
	
	public Promotion() {
		
	}

	public Promotion(String promotionCode, Map<String, Integer> promoDetails, int promotionalCost) {
		super();
		this.promoCode = promotionCode;
		this.promoDetails = promoDetails;
		this.promotionalCost = promotionalCost;
		if (promoDetails.entrySet().size() == 1) {
			promotionType = "Simple";
			processor = new SimplePromoProcessor(this);
		} else {
			promotionType = "Complex";
			processor = new ComplexPromoProcessor(this);
		}
		
	}

	public Promotion(String promotionType) {
		this.promotionType = promotionType;
		processor = new DefaultPromoProcessor();
	}

	public String getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(String promoCode) {
		this.promoCode = promoCode;
	}

	public Map<String, Integer> getPromoDetails() {
		return promoDetails;
	}

	public void setPromoDetails(Map<String, Integer> promoDetails) {
		this.promoDetails = promoDetails;
		if (promoDetails.entrySet().size() == 1) {
			promotionType = "Simple";
		} else {
			promotionType = "Complex";
		}
	}

	public String getPromotionType() {
		return promotionType;
	}

	public int getPromotionalCost() {
		return promotionalCost;
	}

	public void setPromotionalCost(int promotionalCost) {
		this.promotionalCost = promotionalCost;
	}

	@Override
	public String toString() {
		return "Promotion [promoCode=" + promoCode + ", promoDetails=" + promoDetails + ", promotionalCost="
				+ promotionalCost + "]";
	}

	public IPromoProcessor getProcessor() {
		return this.processor;
	}
	
}
