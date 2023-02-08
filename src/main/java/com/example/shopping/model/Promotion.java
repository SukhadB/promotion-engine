package com.example.shopping.model;

import java.util.Map;

import com.example.shopping.processor.ComplexPromoProcessor;
import com.example.shopping.processor.DefaultPromoProcessor;
import com.example.shopping.processor.IPromoProcessor;
import com.example.shopping.processor.SimplePromoProcessor;

/**
 * The Promotion class holds the details of the promotion available with the 
 * Application
 * 
 * @author Sukhad Bhole
 *
 */
public class Promotion {

	private String promoCode;
	
	/**
	 * the attributes consists of the details of the promotion
	 * the key is the product name and the value is the count 
	 * of the product for which the promotion will be applicable
	 * 
	 */
	private Map<String, Integer> promoDetails;
	
	private int promotionalCost;
	private String promotionType;
	
	private IPromoProcessor processor;
	
	/**
	 * Construct Default Promotion where the marked product price is considered
	 */
	public Promotion() {
		this.promotionType = "Default";
		this.processor = new DefaultPromoProcessor();
	}

	/**
	 * Constructs new promotion object with the parameters required
	 * @param promotionCode - promotion Code
	 * @param promoDetails - details of the promotion based on product and count of the product
	 * when the promotion is applied 
	 * @param promotionalCost - promotionalCost which is applicable when the promotion is applied
	 */
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

	/** 
	 * Construct new promotion basis the promotion type provided 
	 * @param promotionType - the promotion type 
	 */
	public Promotion(String promotionType) {
		this.promotionType = promotionType;
		if (promotionType != null && promotionType.equals("Default")) {
			processor = new DefaultPromoProcessor();
		}
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

	public IPromoProcessor getProcessor() {
		return this.processor;
	}

	@Override
	public String toString() {
		return "Promotion [promoCode=" + promoCode + ", promoDetails=" + promoDetails + ", promotionalCost="
				+ promotionalCost + ", promotionType=" + promotionType + ", processor=" + processor + "]";
	}
}
