package com.example.shopping.processor;

import java.util.List;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

/**
 * The class implements the applyPromotion method to calculate the cost of the
 * product based marked product price and quantity
 * 
 * @author Sukhad Bhole
 *
 */
public class DefaultPromoProcessor implements IPromoProcessor {

	/**
	 * Calculates the total applicable cost of the matching product list and the
	 * promotion applicable
	 * 
	 * @param products  matching set for the promotion
	 * @param promotion promotion applicable
	 * @return total cost applicable post applicable promotion is applied
	 */
	@Override
	public double applyPromotion(List<Product> products, Promotion promotion) {
		double totalCost = 0;
		if (products != null) {
			totalCost = products.size() * products.get(0).getPrice();
		}
		return totalCost;
	}

}
