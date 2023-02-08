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

	@Override
	public double applyPromotion(List<Product> products, Promotion promotion) {
		double totalCost = 0;
		totalCost = products.size() * products.get(0).getPrice();
		return totalCost;
	}

}
