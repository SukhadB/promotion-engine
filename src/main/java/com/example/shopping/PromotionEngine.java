package com.example.shopping;

import java.util.List;
import java.util.stream.Collectors;

public class PromotionEngine {
	
	private List<Promotion> promotionList; 

	public List<Promotion> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}

	public double applyPromotion(Cart cart) {
		double promotedCost = 0;
		List<Product> productList = cart.getProductList();
		
		if (productList != null && !productList.isEmpty()) {
	 		int countOfProductA = productList.stream().filter(p -> p.getId().equals("A")).collect(Collectors.toList()).size();
			
			int countOfProductB = productList.stream().filter(p -> p.getId().equals("B")).collect(Collectors.toList()).size();
			
			int countOfProductC = productList.stream().filter(p -> p.getId().equals("C")).collect(Collectors.toList()).size();
			
			int countOfProductD = productList.stream().filter(p -> p.getId().equals("D")).collect(Collectors.toList()).size();
			
			double totalCostofProductAPostPromotion = (countOfProductA/3 * 130) + (countOfProductA%3 * 50); 
			double totalCostofProductBPostPromotion = (countOfProductB/2 * 45) + (countOfProductB%2 * 30); 
			double totalCostofProductCPostPromotion = 0;
			double totalCostofProductDPostPromotion = 0;
			
			if (countOfProductD == 0) {
				totalCostofProductCPostPromotion = (countOfProductC * 20);
			}
			if (countOfProductC == 0) {
				totalCostofProductDPostPromotion = (countOfProductD * 15);		
			}
			if (totalCostofProductCPostPromotion == 0 && totalCostofProductDPostPromotion == 0) {
				if (countOfProductC == countOfProductD) {
					totalCostofProductDPostPromotion = (countOfProductD * 30);
				}
				if (countOfProductC > countOfProductD) {
					totalCostofProductDPostPromotion = (countOfProductD  * 30);
					totalCostofProductCPostPromotion = (countOfProductC - countOfProductD)  * 20;
				} else if (countOfProductD > countOfProductC) {
					totalCostofProductCPostPromotion = (countOfProductC  * 30);
					totalCostofProductDPostPromotion = (countOfProductD - countOfProductC) * 15;
				}
			}
			
			promotedCost = totalCostofProductAPostPromotion + totalCostofProductBPostPromotion + totalCostofProductCPostPromotion+ totalCostofProductDPostPromotion;
		}
		return promotedCost;
	}

}