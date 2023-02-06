package com.example.shopping.processor;

import java.math.BigDecimal;
import java.util.List;

import com.example.shopping.exception.ProductNotInPromotion;
import com.example.shopping.model.Product;

public interface IPromoProcessor {
	
	public BigDecimal applyPromotion(List<Product> products);

}
