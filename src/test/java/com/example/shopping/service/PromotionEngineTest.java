package com.example.shopping.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.example.shopping.exception.ProductNotPresentInInvetory;
import com.example.shopping.model.Cart;
import com.example.shopping.model.Inventory;
import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

public class PromotionEngineTest {

	private Inventory inventory;
	private PromotionEngine promotionEngine;
	private List<Product> products = new ArrayList<>();

	@Before
	public void initialConfiguration() throws ProductNotPresentInInvetory {
		products.add(new Product("A", 50));
		products.add(new Product("B", 30));
		products.add(new Product("C", 20));
		products.add(new Product("D", 15));

		List<Promotion> promotionList = new ArrayList<>();
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("A", 3);
		Promotion promotion1 = new Promotion("P1", promoDetails1, 130);
		promotionList.add(promotion1);

		Map<String, Integer> promoDetails2 = new HashMap<>();
		promoDetails2.put("B", 2);
		Promotion promotion2 = new Promotion("P2", promoDetails2, 45);
		promotionList.add(promotion2);

		Map<String, Integer> promoDetails3 = new HashMap<>();
		promoDetails3.put("C", 1);
		promoDetails3.put("D", 1);
		Promotion promotion3 = new Promotion("P3", promoDetails3, 30);
		promotionList.add(promotion3);

		promotionEngine = new PromotionEngine(promotionList);
		inventory = new Inventory(products);
	}

	@Test
	public void promote() {
		// First Test case to start the Promotion Engine
		Cart cart = new Cart(inventory);
		assertEquals(promotionEngine.applyPromotion(cart), 0.0d, 0.001);

	}

	@Test
	public void addProductToCart() {
		// Test case to add Product to the cart
		List<String> order = Arrays.asList("A");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(promotionEngine.applyPromotion(cart), 50.0d, 0.001);

	}

	@Test
	public void scenarioA() {
		// Test case for scenario A
		List<String> order = Arrays.asList("A", "B", "C");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(promotionEngine.applyPromotion(cart), 100.0d, 0.001);
	}

	@Test
	public void scenariaB() {
		// Test case for getting total cart value for scenario B
		List<String> order = Arrays.asList("A", "A", "A", "A", "A", "B", "B", "B", "B", "B", "C");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(promotionEngine.applyPromotion(cart), 370.0d, 0.001);
	}

	@Test
	public void scenariaC() {
		// Test case for getting total cart value for scenario C
		List<String> order = Arrays.asList("A", "A", "A", "B", "B", "B", "B", "B", "C", "D");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(promotionEngine.applyPromotion(cart), 280.0d, 0.001);
	}

	@Test
	public void checkPromotedValueForProductDonly() {
		// Test case for getting total cart value when C is not added and product D is
		// added
		List<String> order = Arrays.asList("A", "A", "A", "B", "B", "B", "B", "B", "D");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(promotionEngine.applyPromotion(cart), 265.0d, 0.001);
	}

	@Test
	public void scenarioD() {
		// Test case for getting total cart value when C is not added and product D is
		// added
		List<String> order = Arrays.asList("A", "A", "A", "B", "B", "B", "B", "B", "C", "C", "D");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(promotionEngine.applyPromotion(cart), 300.0d, 0.001);
	}

	@Test
	public void scenarioE() {
		// Test case for getting total cart value when C is not added and product D is
		// added
		List<String> order = Arrays.asList("C", "C", "C", "D", "D");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(promotionEngine.applyPromotion(cart), 80.0d, 0.001);
	}

	@Test
	public void scenarioF() {
		// Test case for getting total cart value when C is not added and product D is
		// added
		List<String> order = Arrays.asList("D", "D", "D", "C", "C");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(promotionEngine.applyPromotion(cart), 75.0d, 0.001);
	}
}