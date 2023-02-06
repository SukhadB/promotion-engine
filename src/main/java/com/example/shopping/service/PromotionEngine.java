package com.example.shopping.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;
import com.example.shopping.model.PromotionConfiguration;
import com.example.shopping.processor.IPromoProcessor;

public class PromotionEngine {
	
	private List<PromotionConfiguration> promotionList; 

	public List<PromotionConfiguration> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<PromotionConfiguration> promotionList) {
		this.promotionList = promotionList;
	}
	
	public List<PromotionConfiguration> findPromo(Product product, int count) {
		List<PromotionConfiguration> applicablePromotions = null;
		return null;
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
	
	public BigDecimal applyPromotion(Cart cart, List<IPromoProcessor> promotionList) {
		BigDecimal promotedCost = BigDecimal.ZERO;
		for (IPromoProcessor iPromoProcessor : promotionList) {
			promotedCost.add(iPromoProcessor.applyPromotion(cart.getProductList()));
		} 
		return promotedCost;
	}

}