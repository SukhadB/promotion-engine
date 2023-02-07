package com.example.shopping.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

import junit.framework.Assert;

public class CartTest {
	
	@Test
	public void checkout() {
		Cart cart = new Cart();
		Assert.assertEquals(0.0, cart.checkout());
		
	}
	
	@Test
	public void Approach2Test1() {
		
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("A", 3);
		
		Promotion promotion1 = new Promotion("P1", promoDetails1, 130);
		
		Map<String, Integer> promoDetails2 = new HashMap<>();
		promoDetails2.put("B", 2);
		
		Promotion promotion2 = new Promotion("P2", promoDetails2, 45);
		
		Map<String, Integer> promoDetails3 = new HashMap<>();
		promoDetails3.put("C", 1);
		promoDetails3.put("D", 1);
		
		Promotion promotion3 = new Promotion("P3", promoDetails3, 30);
		
		List<Promotion> promotionList = new ArrayList<>();
		promotionList.add(promotion1);
		promotionList.add(promotion2);
		promotionList.add(promotion3);
		
		PromotionEngine promotionEngine = new PromotionEngine();
		promotionEngine.setPromotionList(promotionList);
		
		Cart cart = new Cart(promotionEngine);
		
		Product product = new Product("A");
		cart.add(product);
		product = new Product("A");
		cart.add(product);
		product = new Product("B");
		cart.add(product);
		product = new Product("B");
		cart.add(product);
		
		Assert.assertEquals(145.00, cart.checkout());
		
		
		
		
	}
}
