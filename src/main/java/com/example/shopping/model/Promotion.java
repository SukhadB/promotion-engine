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
	public Promotion(String promotionCode, Map<String, Integer> promoDetails, int promotionalCost) {
		super();
		this.promoCode = promotionCode;
		this.promoDetails = promoDetails;
		this.promotionalCost = promotionalCost;
		if (promoDetails.entrySet().size() == 1) {
			processorType = PromotionProcessorType.SIMPLE_PROCESSOR;
		} else {
			processorType = PromotionProcessorType.COMPLEX_PROCESSOR;
		}

	}

	/**
	 * Construct new promotion basis the promotion type provided
	 * 
	 * @param processorType - the promotion type
	 */
	public Promotion(PromotionProcessorType processorType) {
		this.processorType = processorType;
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
		} else {
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

	@Override
	public String toString() {
		return "Promotion [promoCode=" + promoCode + ", promoDetails=" + promoDetails + ", promotionalCost="
				+ promotionalCost + ", processorType=" + processorType + "]";
	}
}
