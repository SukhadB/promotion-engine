package scenarios;

import org.junit.Test;

import junit.framework.Assert;

public class PromotionEngineTest {
	
	public static void main(String[] args) {
		System.out.println("Promotion Engine");
	}
	
	@Test
	public void scenarioA() {
		PromotionEngine promotionEngine = new PromotionEngine();
		
		Cart cart = new Cart();
		
		Assert.assertEquals(100, promotionEngine.promote(cart));
		
	}

}
