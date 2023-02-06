package com.example.shopping.service;

import org.junit.Test;

import junit.framework.Assert;

public class CartTest {
	
	@Test
	public void checkout() {
		Cart cart = new Cart();
		Assert.assertEquals(0.00, cart.checkout());
		
	}
}
