package com.example.shopping;

import org.junit.Test;

import com.example.shopping.Cart;
import com.example.shopping.Product;
import com.example.shopping.PromotionEngine;

import junit.framework.Assert;

public class PromotionEngineTest {	
	
	@Test
	public void promote() {
		// First Test case to start the Promotion Engine
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
				
		Assert.assertEquals(0.00, promotionEngine.applyPromotion(cart));
		
	}

	@Test
	public void addProductToCart() {
		// Test case to add Product to the cart
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		
		Assert.assertEquals(50.00, promotionEngine.applyPromotion(cart));
		
	}
	
	@Test
	public void calculateTotalCartValue() {
		// Test case to add calculate Total Cart Value
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		
		Assert.assertEquals(50.00, cart.calculateTotalCartValue());
		
	}
	
	@Test
	public void calculateTotalCartValueforAllProduct() {
		// Test case to add Total cart value with all product
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("C");
		cart.addProduct(product);
		product = new Product("D");
		cart.addProduct(product);
		
		Assert.assertEquals(115.00, cart.calculateTotalCartValue());
		
	}
	
	@Test
	public void scenarioA() {
		// Test case for scenario A
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("C");
		cart.addProduct(product);
		
		Assert.assertEquals(100.00, promotionEngine.applyPromotion(cart));
	}
	
	@Test
	public void totalCartValueForScenariaB() {
		// Test case for getting total cart value for scenario B
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("C");
		cart.addProduct(product);
		
		Assert.assertEquals(420.00, cart.calculateTotalCartValue());
	}
	
	@Test
	public void scenariaB() {
		// Test case for getting total cart value for scenario B
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		
		product = new Product("C");
		cart.addProduct(product);
		
		Assert.assertEquals(370.00, promotionEngine.applyPromotion(cart));
	}
	
	
	@Test
	public void scenariaC() {
		// Test case for getting total cart value for scenario C
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		
		product = new Product("C");
		cart.addProduct(product);
		
		product = new Product("D");
		cart.addProduct(product);

		Assert.assertEquals(280.00, promotionEngine.applyPromotion(cart));
	}
	
	@Test
	public void checkPromotedValueForProductDonly() {
		// Test case for getting total cart value when C is not added and product D is added
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		
		product = new Product("D");
		cart.addProduct(product);

		Assert.assertEquals(265.00, promotionEngine.applyPromotion(cart));
	}
	
	@Test
	public void scenarioD() {
		// Test case for getting total cart value when C is not added and product D is added
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		product = new Product("A");
		cart.addProduct(product);
		
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		product = new Product("B");
		cart.addProduct(product);
		
		product = new Product("C");
		cart.addProduct(product);
		product = new Product("C");
		cart.addProduct(product);
		
		product = new Product("D");
		cart.addProduct(product);

		Assert.assertEquals(300.00, promotionEngine.applyPromotion(cart));
	}
	
	@Test
	public void scenarioE() {
		// Test case for getting total cart value when C is not added and product D is added
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("C");
		cart.addProduct(product);
		product = new Product("C");
		cart.addProduct(product);
		product = new Product("C");
		cart.addProduct(product);

		
		product = new Product("D");
		cart.addProduct(product);
		product = new Product("D");
		cart.addProduct(product);


		Assert.assertEquals(80.00, promotionEngine.applyPromotion(cart));
	}
	
	@Test
	public void scenarioF() {
		// Test case for getting total cart value when C is not added and product D is added
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("D");
		cart.addProduct(product);
		product = new Product("D");
		cart.addProduct(product);
		product = new Product("D");
		cart.addProduct(product);

		
		product = new Product("C");
		cart.addProduct(product);
		product = new Product("C");
		cart.addProduct(product);


		Assert.assertEquals(75.00, promotionEngine.applyPromotion(cart));
	}
	
	public void addingPromotion() {
		// Test case for getting total cart value when C is not added and product D is added
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("D");
		cart.addProduct(product);
		product = new Product("D");
		cart.addProduct(product);
		product = new Product("D");
		cart.addProduct(product);

		
		product = new Product("C");
		cart.addProduct(product);
		product = new Product("C");
		cart.addProduct(product);


		Assert.assertEquals(75.00, promotionEngine.applyPromotion(cart));
	}
}

