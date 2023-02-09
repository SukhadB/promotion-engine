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
	public void testApplyPromotion() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		products.add(new Product("A", 50));
		DefaultPromoProcessor promoProcessor = new DefaultPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, null), 50.0d, 0.001);
	}

}
