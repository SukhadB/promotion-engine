package com.example.shopping.processor;

import java.util.List;

import com.example.shopping.model.Product;

public interface IPromoProcessor {
	
	public double applyPromotion(List<Product> products);

}
