package com.example.shopping.model;

import java.util.Map;

import com.example.shopping.processor.PromotionProcessorType;

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
	 * the attributes consists of the details of the promotion the key is the
	 * product name and the value is the count of the product for which the
	 * promotion will be applicable
	 * 
	 */
	private Map<String, Integer> promoDetails;

	private int promotionalCost;
	private int promotionalPercentage;
	private PromotionProcessorType processorType;

	/**
	 * Construct Default Promotion where the marked product price is considered
	 */
	public Promotion() {
		this.processorType = PromotionProcessorType.DEFAULT_PROCESSOR;
	}

	/**
	 * Constructs new promotion object with the parameters required
	 * 
	 * @param promotionCode   - promotion Code
	 * @param promoDetails    - details of the promotion based on product and count
	 *                        of the product when the promotion is applied
	 * @param promotionalCost - promotionalCost which is applicable when the
	 *                        promotion is applied
	 */
	public Promotion(String promotionCode, Map<String, Integer> promoDetails, int promotionalCost, int promotionalPercentage) {
		super();
		this.promoCode = promotionCode;
		this.promoDetails = promoDetails;
		this.promotionalCost = promotionalCost;
		this.promotionalPercentage = promotionalPercentage;
		if (promoDetails.entrySet().size() == 1 && promotionalCost != 0) {
			processorType = PromotionProcessorType.SIMPLE_PROCESSOR;
		} else if (promoDetails.entrySet().size() > 1) {
			processorType = PromotionProcessorType.COMPLEX_PROCESSOR;
		} else if (promoDetails.entrySet().size() == 1 && promotionalPercentage != 0) {
			processorType = PromotionProcessorType.PERCENTAGE_PROCESSOR;
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
			processorType = PromotionProcessorType.SIMPLE_PROCESSOR;
		} else if (promoDetails.entrySet().size() == 2) {
			processorType = PromotionProcessorType.COMPLEX_PROCESSOR;
		}
	}

	public PromotionProcessorType getPromotionType() {
		return processorType;
	}

	public int getPromotionalCost() {
		return promotionalCost;
	}

	public void setPromotionalCost(int promotionalCost) {
		this.promotionalCost = promotionalCost;
	}
	
	public int getPromotionalPercentage() {
		return promotionalPercentage;
	}

	public void setPromotionalPercentage(int promotionalPercentage) {
		this.promotionalPercentage = promotionalPercentage;
		this.promotionalCost = 0;
		if (promotionalPercentage != 0) {
			processorType = PromotionProcessorType.PERCENTAGE_PROCESSOR;
		}
	}

	@Override
	public String toString() {
		return "Promotion [promoCode=" + promoCode + ", promoDetails=" + promoDetails + ", promotionalCost="
				+ promotionalCost + ", processorType=" + processorType + "]";
	}
}
