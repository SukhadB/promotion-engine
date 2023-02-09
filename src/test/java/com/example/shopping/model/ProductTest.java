package com.example.shopping.model;

import org.junit.Test;

import com.example.shopping.exception.ProductNotPresentInInvetory;

public class ProductTest {

	@Test(expected = IllegalArgumentException.class)
	public void testCatchcreateNullSkuIDProduct() throws ProductNotPresentInInvetory {
		Product product = new Product(null, 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCatchcreateEmptyProduct() throws ProductNotPresentInInvetory {
		Product product = new Product("", 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCatchcreateNullProduct() throws ProductNotPresentInInvetory {
		Product product = new Product(null);
	}

}
