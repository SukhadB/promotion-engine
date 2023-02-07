package com.example.shopping.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Before;
import org.junit.Test;

import com.example.shopping.exception.ProductNotPresentInInvetory;
import com.example.shopping.model.Inventory;
import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

public class CartTest {
	
	private Inventory inventory;
	private PromotionEngine promotionEngine;
    private List<Product> products = new ArrayList<>();
	
	@Before
    public void setUp() {
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
	public void checkout() {
		Cart cart = new Cart(promotionEngine, inventory);
		assertEquals(cart.checkout(), 0.0d, 0.001);
	}
	
	@Test
	public void checkout2() {
		List<String> order = Arrays.asList("A", "A", "B", "B");
		
		Cart cart = new Cart(promotionEngine, inventory);
		cart.add(order);
		
		assertEquals(cart.checkout(), 145.0d, 0.001);
	}
	
	@Test
	public void checkout3() {
		List<String> order = Arrays.asList("A", "A", "A", "B", "B", "B", "B",  "C", "C", "D", "D");
		
		Cart cart = new Cart(promotionEngine, inventory);
		cart.add(order);
		
		assertEquals(cart.checkout(), 280.0d, 0.001);
	}
	
	@Test
	public void checkout4() {
		List<String> order = Arrays.asList("A", "A", "B", "B", "B", "C", "D", "D");
		
		Cart cart = new Cart(promotionEngine, inventory);
		cart.add(order);
		System.out.println(cart.checkout());
		assertEquals(cart.checkout(), 220.0d, 0.001);
	}
	
	@Test
	public void calculateTotalCartValue() {
		// Test case to add calculate Total Cart Value
		List<String> order = Arrays.asList("A");
		
		Cart cart = new Cart(promotionEngine, inventory);
		cart.add(order);
		
		assertEquals(cart.calculateTotalCartValue(), 50.0d, 0.001);
		
	}
	
	@Test
	public void calculateTotalCartValueforAllProduct() {
		// Test case to add Total cart value with all product
		List<String> order = Arrays.asList("A", "B", "C", "D");
		
		Cart cart = new Cart(promotionEngine, inventory);
		cart.add(order);
		
		assertEquals(cart.calculateTotalCartValue(), 115.0d, 0.001);
	}
	
	@Test
	public void totalCartValueForScenariaB() {
		// Test case for getting total cart value for scenario B
		List<String> order = Arrays.asList("A", "A", "A", "A", "A", "B", "B", "B", "B", "B", "C");
		
		Cart cart = new Cart(promotionEngine, inventory);
		cart.add(order);

		assertEquals(cart.calculateTotalCartValue(), 420.0d, 0.001);
	}
	
	@Test
    public void testEmptyCart() {
		List<String> order = Arrays.asList("A", "A", "A", "A", "A", "B", "B", "B", "B", "B", "C");
		
		Cart cart = new Cart(promotionEngine, inventory);
		cart.add(order);
        cart.empty();

        assertEquals(cart.calculateTotalCartValue(), 0, 0.0);
        assertEquals(cart.checkout(), 0, 0.0);
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testCatchEmptyAddException() throws ProductNotPresentInInvetory {
		Cart cart = new Cart(promotionEngine, inventory);
        cart.add("");
        cart.empty();
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void testCatchEmptyListOnAddException() throws ProductNotPresentInInvetory {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Z"));
        Cart cart = new Cart(promotionEngine, inventory);
        cart.add(order);
        cart.empty();
    }
}
