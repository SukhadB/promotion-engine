package com.example.shopping.processor;

import java.util.List;

import com.example.shopping.model.Product;

public abstract class AbstractPromoProcessor implements IPromoProcessor {

	public abstract double applyPromotion(List<Product> products);

}
