package com.example.shopping.processor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.shopping.exception.ProductNotPresentInInvetory;
import com.example.shopping.model.Product;

public class DefaultPromoProcessorTest {
	
	@Test
	public void testApplyPromotionwithNull() {
		DefaultPromoProcessor promoProcessor = new DefaultPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(null, null), 0.0d, 0.001);
	}
	
	
	@Test
	public void testApplyPromotion() {
		List<Product> products = new ArrayList<>();
		try {
			products.add(new Product("A", 50));
		} catch (ProductNotPresentInInvetory e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DefaultPromoProcessor promoProcessor = new DefaultPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, null), 50.0d, 0.001);
	}

}
