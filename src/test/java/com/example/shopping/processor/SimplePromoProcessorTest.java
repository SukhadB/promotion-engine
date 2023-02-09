package com.example.shopping.processor;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.example.shopping.exception.ProductNotPresentInInvetory;
import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

public class SimplePromoProcessorTest {

	@Test
	public void testApplyPromotionwithNull() {
		SimplePromoProcessor promoProcessor = new SimplePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(null, null), 0.0d, 0.001);
	}

	@Test
	public void testApplyPromotion() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("A", 3);
		Promotion promotion = new Promotion("P1", promoDetails1, 130);
		Product a = new Product("A", 50);
		products.add(a);
		products.add(a);
		products.add(a);
		SimplePromoProcessor promoProcessor = new SimplePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 130.0d, 0.001);
	}

	@Test
	public void testApplyPromotionforAnotherProduct() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("B", 2);
		Promotion promotion = new Promotion("P2", promoDetails1, 45);
		Product b = new Product("B", 30);
		products.add(b);
		products.add(b);
		SimplePromoProcessor promoProcessor = new SimplePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 45.0d, 0.001);
	}

	@Test
	public void testApplyPromotionMultipleCombination() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("B", 2);
		Promotion promotion = new Promotion("P2", promoDetails1, 45);
		Product b = new Product("B", 30);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		SimplePromoProcessor promoProcessor = new SimplePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 120.0d, 0.001);
	}

	@Test
	public void testApplyPromotionMultipleCombination2() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("B", 2);
		Promotion promotion = new Promotion("P2", promoDetails1, 45);
		Product b = new Product("B", 30);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		SimplePromoProcessor promoProcessor = new SimplePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 120.0d, 0.001);
	}

}
