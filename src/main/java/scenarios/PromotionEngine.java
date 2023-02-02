package scenarios;

import java.util.List;
import java.util.stream.Collectors;

public class PromotionEngine {

	public int promote(Cart cart) {
		int promotedCost = 0;
		List<Product> productList = cart.getProductList();
		
		if (productList != null && !productList.isEmpty()) {
	 		int countOfProductA = productList.stream().filter(p -> p.getId().equals("A")).collect(Collectors.toList()).size();
			
			int countOfProductB = productList.stream().filter(p -> p.getId().equals("B")).collect(Collectors.toList()).size();
			
			int countOfProductC = productList.stream().filter(p -> p.getId().equals("C")).collect(Collectors.toList()).size();
			
			int countOfProductD = productList.stream().filter(p -> p.getId().equals("D")).collect(Collectors.toList()).size();
			
			int totalCostofProductAPostPromotion = (countOfProductA/3 * 130) + (countOfProductA%3 * 50); 
			int totalCostofProductBPostPromotion = (countOfProductB/2 * 45) + (countOfProductB%2 * 30); 
			int totalCostofProductCPostPromotion = (countOfProductC * 20); 
			int totalCostofProductDPostPromotion = (countOfProductD * 15);
			promotedCost = totalCostofProductAPostPromotion + totalCostofProductBPostPromotion + totalCostofProductCPostPromotion+ totalCostofProductDPostPromotion;
		}
		return promotedCost;
	}
}