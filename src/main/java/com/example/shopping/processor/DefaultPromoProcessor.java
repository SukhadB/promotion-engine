package com.example.shopping.processor;

import java.util.List;

import com.example.shopping.model.Product;

public class DefaultPromoProcessor implements IPromoProcessor {


	@Override
	public double applyPromotion(List<Product> products) {
		double totalCost = 0;
		totalCost = products.size() * products.get(0).getPrice();
		return totalCost;
	}

}