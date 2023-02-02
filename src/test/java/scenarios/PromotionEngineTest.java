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
		
		Product product = new Product("A", 50);
		cart.addProduct(product);
		
		Assert.assertEquals(100, promotionEngine.promote(cart));
		
	}
	
	@Test
	public void calculateTotalCartValue() {
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A", 50);
		cart.addProduct(product);
		
		Assert.assertEquals(50, cart.calculateTotalCartValue());
		
	}
	
	@Test
	public void calculateTotalCartValueforAllProduct() {
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Product product = new Product("A", 50);
		cart.addProduct(product);
		product = new Product("B", 30);
		cart.addProduct(product);
		product = new Product("C", 20);
		cart.addProduct(product);
		product = new Product("D", 15);
		cart.addProduct(product);
		
		Assert.assertEquals(115, cart.calculateTotalCartValue());
		
	}
}
