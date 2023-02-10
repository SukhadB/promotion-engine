package com.example.shopping.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.example.shopping.exception.ProductNotPresentInInvetory;

public class CartTest {


	private Inventory inventory;
	private List<Product> products = new ArrayList<>();
	

	@Before
	public void initialConfiguration() {
		try {
			products.add(new Product("A", 50));
			products.add(new Product("B", 30));
			products.add(new Product("C", 20));
			products.add(new Product("D", 15));
		} catch (ProductNotPresentInInvetory e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inventory = new Inventory(products);
	}

	@Test
	public void calculateTotalCartValue() {
		// Test case to add calculate Total Cart Value
		List<String> order = Arrays.asList("A");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(cart.calculateTotalCartValue(), 50.0d, 0.001);

	}

	@Test
	public void calculateTotalCartValueforAllProduct() {
		// Test case to add Total cart value with all product
		List<String> order = Arrays.asList("A", "B", "C", "D");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(cart.calculateTotalCartValue(), 115.0d, 0.001);
	}

	@Test
	public void totalCartValueForScenariaB() {
		// Test case for getting total cart value for scenario B
		List<String> order = Arrays.asList("A", "A", "A", "A", "A", "B", "B", "B", "B", "B", "C");

		Cart cart = new Cart(inventory);
		cart.add(order);

		assertEquals(cart.calculateTotalCartValue(), 420.0d, 0.001);
	}

	@Test
	public void testEmptyCart() {
		List<String> order = Arrays.asList("A", "A", "A", "A", "A", "B", "B", "B", "B", "B", "C");

		Cart cart = new Cart(inventory);
		cart.add(order);
		cart.empty();

		assertEquals(cart.calculateTotalCartValue(), 0, 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCatchEmptyAddException() throws ProductNotPresentInInvetory {
		Cart cart = new Cart(inventory);
		cart.add("");
		cart.empty();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCatchProductNotPresentAddException() throws ProductNotPresentInInvetory {
		List<String> order = Arrays.asList("Z");
		Cart cart = new Cart(inventory);
		cart.add(order);
		cart.add(order);
		cart.empty();
	}


}
