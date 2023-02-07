package com.example.shopping.model;

import java.math.BigDecimal;
import java.util.List;

import com.example.shopping.processor.IPromoProcessor;

public class DefaultProcessor implements IPromoProcessor {

	@Override
	public double applyPromotion(List<Product> products) {
		double totalCost = 0;
		totalCost = products.size() * products.get(0).getPrice();
		return totalCost;
	}

}
