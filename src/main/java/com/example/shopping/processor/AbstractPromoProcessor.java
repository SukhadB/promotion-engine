package com.example.shopping.processor;

import java.math.BigDecimal;
import java.util.List;

import com.example.shopping.model.Product;
import com.example.shopping.model.PromotionConfiguration;

public abstract class AbstractPromoProcessor implements IPromoProcessor{
	
	PromotionConfiguration promoConfig = new PromotionConfiguration();
	
	public abstract BigDecimal applyPromotion(List<Product> products);


}
