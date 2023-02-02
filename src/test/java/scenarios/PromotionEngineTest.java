package scenarios;

import org.junit.Test;

import junit.framework.Assert;

public class PromotionEngineTest {
	
	public static void main(String[] args) {
		System.out.println("Promotion Engine");
	}
	
	@Test
	public void promote() {
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
				
		Assert.assertEquals(100, promotionEngine.promote(cart));
		
	}

	@Test
	public void addProductToCart() {
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		
		Assert.assertEquals(100, promotionEngine.promote(cart));
		
	}
	
	@Test
	public void calculateTotalCartValue() {
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A");
		cart.addProduct(product);
		
		Assert.assertEquals(50, cart.calculateTotalCartValue());
		
	}
	
	@Test
	public void calculateTotalCartValueforAllProduct() {
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
		
		Assert.assertEquals(115, cart.calculateTotalCartValue());
		
	}
}
