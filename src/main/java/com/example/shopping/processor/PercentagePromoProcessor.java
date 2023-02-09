package com.example.shopping.processor;

import java.util.List;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

public class PercentagePromoProcessor implements IPromoProcessor {

	@Override
	public double applyPromotion(List<Product> products, Promotion promotion) {
		return 0;
	}

}
