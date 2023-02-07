package com.example.shopping.processor;

import java.util.List;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

public abstract class AbstractPromoProcessor implements IPromoProcessor{
	
	Promotion promoConfig = new Promotion();
	
	public abstract double applyPromotion(List<Product> products);


}
