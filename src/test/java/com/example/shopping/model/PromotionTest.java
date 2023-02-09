package com.example.shopping.model;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.example.shopping.processor.PromotionProcessorType;

public class PromotionTest {
	
	@Test
	public void testCreateDefaultPromotion() {
		Promotion promotion = new Promotion();
		assertNotNull(promotion);
		assertTrue(promotion.getPromoCode() == null);
		assertTrue(promotion.getPromotionType() == PromotionProcessorType.DEFAULT_PROCESSOR);
	}
	
	@Test
	public void testCreateSimplePromotion() {
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("A", 3);
		Promotion promotion = new Promotion("P1", promoDetails1, 130);
		assertNotNull(promotion);
		assertTrue(promotion.getPromoCode() != null);
		assertTrue(promotion.getPromotionType() != null);
		assertTrue(promotion.getPromotionType() == PromotionProcessorType.SIMPLE_PROCESSOR);
		
	}
	
	@Test
	public void testCreateSimplePromotionWithDifferentProduct() {
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("B", 2);
		Promotion promotion = new Promotion("P2", promoDetails1, 45);
		assertNotNull(promotion);
		assertTrue(promotion.getPromoCode() != null);
		assertTrue(promotion.getPromotionType() != null);
		assertTrue(promotion.getPromotionType() == PromotionProcessorType.SIMPLE_PROCESSOR);
		
	}
	
	@Test
	public void testCreateComplexPromotion() {
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("C", 1);
		promoDetails1.put("D", 1);
		Promotion promotion = new Promotion("P3", promoDetails1, 30);
		assertNotNull(promotion);
		assertTrue(promotion.getPromoCode() != null);
		assertTrue(promotion.getPromotionType() != null);
		assertTrue(promotion.getPromotionType() == PromotionProcessorType.COMPLEX_PROCESSOR);
		
	}

}
