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

public class PercentagePromoProcessorTest {

	@Test
	public void testApplyPromotionwithNull() {
		PercentagePromoProcessor promoProcessor = new PercentagePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(null, null), 0.0d, 0.001);
	}

	@Test
	public void testApplyPromotion() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("A", 3);
		Promotion promotion = new Promotion("P1", promoDetails1, 0, 50);
		Product a = new Product("A", 50);
		products.add(a);
		products.add(a);
		products.add(a);
		PercentagePromoProcessor promoProcessor = new PercentagePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 75.0d, 0.001);
	}

	@Test
	public void testApplyPromotionforAnotherProduct() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("B", 2);
		Promotion promotion = new Promotion("P2", promoDetails1, 0, 45);
		Product b = new Product("B", 30);
		products.add(b);
		products.add(b);
		PercentagePromoProcessor promoProcessor = new PercentagePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 27.0d, 0.001);
	}

	@Test
	public void testApplyPromotionMultipleCombination() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("B", 2);
		Promotion promotion = new Promotion("P2", promoDetails1, 0, 80);
		Product b = new Product("B", 30);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		PercentagePromoProcessor promoProcessor = new PercentagePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 120.0d, 0.001);
	}

	@Test
	public void testApplyPromotionMultipleCombination2() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("B", 2);
		Promotion promotion = new Promotion("P2", promoDetails1, 0, 80);
		Product b = new Product("B", 30);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		PercentagePromoProcessor promoProcessor = new PercentagePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 120.0d, 0.001);
	}
	
	@Test
	public void testApplyPromotionMultipleCombination3() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("B", 2);
		Promotion promotion = new Promotion("P2", promoDetails1, 0, 80);
		Product b = new Product("B", 30);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		products.add(b);
		PercentagePromoProcessor promoProcessor = new PercentagePromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 144.0d, 0.001);
	}

}
