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

public class ComplexPromoProcessorTest {

	@Test
	public void testApplyPromotionwithNull() {
		ComplexPromoProcessor promoProcessor = new ComplexPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(null, null), 0.0d, 0.001);
	}

	@Test
	public void testApplyPromotion() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("C", 1);
		promoDetails1.put("D", 1);
		Promotion promotion = new Promotion("P1", promoDetails1, 30, 0);
		products.add(new Product("C", 20));
		products.add(new Product("D", 15));
		ComplexPromoProcessor promoProcessor = new ComplexPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 30.0d, 0.001);
	}

	@Test
	public void testApplyPromotionWithOnlyOneProduct() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("C", 1);
		promoDetails1.put("D", 1);
		Promotion promotion = new Promotion("P1", promoDetails1, 30, 0);
		products.add(new Product("C", 20));
		// TODO Auto-generated catch block
		ComplexPromoProcessor promoProcessor = new ComplexPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 20.0d, 0.001);
	}

	@Test
	public void testApplyPromotionWithOneProduct() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("C", 1);
		promoDetails1.put("D", 1);
		Promotion promotion = new Promotion("P1", promoDetails1, 30, 0);
		products.add(new Product("D", 15));
		ComplexPromoProcessor promoProcessor = new ComplexPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 15.0d, 0.001);
	}

	@Test
	public void testApplyPromotionWithMultipleCombination() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("C", 1);
		promoDetails1.put("D", 1);
		Promotion promotion = new Promotion("P1", promoDetails1, 30, 0);
		Product c = new Product("C", 20);
		Product d = new Product("D", 15);
		products.add(c);
		products.add(c);
		products.add(d);
		products.add(d);
		ComplexPromoProcessor promoProcessor = new ComplexPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 60.0d, 0.001);
	}

	@Test
	public void testApplyPromotionWithMultipleCombination2() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("C", 1);
		promoDetails1.put("D", 1);
		Promotion promotion = new Promotion("P1", promoDetails1, 30, 0);
		Product c = new Product("C", 20);
		Product d = new Product("D", 15);
		products.add(c);
		products.add(c);
		products.add(d);
		products.add(d);
		products.add(d);
		ComplexPromoProcessor promoProcessor = new ComplexPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 75.0d, 0.001);
	}

	@Test
	public void testApplyPromotionWithMultipleCombination3() throws ProductNotPresentInInvetory {
		List<Product> products = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("C", 1);
		promoDetails1.put("D", 1);
		Promotion promotion = new Promotion("P1", promoDetails1, 30, 0);
		Product c = new Product("C", 20);
		Product d = new Product("D", 15);
		products.add(c);
		products.add(c);
		products.add(c);
		products.add(d);
		products.add(d);
		ComplexPromoProcessor promoProcessor = new ComplexPromoProcessor();
		assertEquals(promoProcessor.applyPromotion(products, promotion), 80.0d, 0.001);
	}

}
