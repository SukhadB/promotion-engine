package com.example.shopping.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
	
	public void findApplicablePromo(Cart cart) {
		Map<String, Integer> productCount = cart.getProductCount();
		List<Product> products = cart.getProducts();
		
		
		for (Map.Entry<String, Integer> productName : productCount.entrySet()) {
			String key = productName.getKey();
			Integer val = productName.getValue();
			
			Optional<PromotionConfiguration> optional = promotionList.stream().filter(p -> p.getPromoDetails().containsKey(key) && p.getPromoDetails().get("A") >= val).findFirst();
			if (optional != null) {
				PromotionConfiguration pc = optional.get();
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("A", 3);
		
		PromotionConfiguration promotion1 = new PromotionConfiguration("P1", promoDetails1, 130);
		
		Map<String, Integer> promoDetails2 = new HashMap<>();
		promoDetails2.put("B", 2);
		
		PromotionConfiguration promotion2 = new PromotionConfiguration("P2", promoDetails2, 45);
		
		Map<String, Integer> promoDetails3 = new HashMap<>();
		promoDetails3.put("C", 1);
		promoDetails3.put("D", 1);
		
		PromotionConfiguration promotion3 = new PromotionConfiguration("P3", promoDetails3, 30);
		
		List<PromotionConfiguration> promotionList = new ArrayList<>();
		promotionList.add(promotion1);
		promotionList.add(promotion2);
		promotionList.add(promotion3);
		
		System.out.println(promotionList.stream().anyMatch(p -> p.getPromoDetails().containsKey("A")));
		System.out.println(promotionList.stream().filter(p -> p.getPromoDetails().containsKey("A") && p.getPromoDetails().get("A") >= 3).findFirst().get());
	}
	
	public Map<PromotionConfiguration, List<Product>> findPromo(Cart cart) {
		Map<PromotionConfiguration, List<Product>> applicablePromotions = null;
		
		Map<String, Integer> productCount = cart.getProductCount();
		List<Product> products = cart.getProducts();
		
		for (PromotionConfiguration promotion : promotionList) {
			Map<String, Integer> promoDetails = promotion.getPromoDetails();
			
			boolean isPromoApplicable = true;
			List<Product> matchingSet = new ArrayList<>();
			for (Map.Entry<String, Integer> entry : promoDetails.entrySet()) {
				String key = entry.getKey();
				Integer val = entry.getValue();
				
				if (productCount.get(key) < val) {
					isPromoApplicable = false;
				}
				matchingSet.addAll(products.stream().filter(p -> p.getSKUId().equals(key)).collect(Collectors.toList()));
			}
			
			if (isPromoApplicable) {
				applicablePromotions.put(promotion, matchingSet);
			}
		}
		
		return applicablePromotions;
	}

	public double applyPromotion(Cart cart) {
		double promotedCost = 0;
		List<Product> productList = cart.getProducts();
		
		if (productList != null && !productList.isEmpty()) {
	 		int countOfProductA = productList.stream().filter(p -> p.getSKUId().equals("A")).collect(Collectors.toList()).size();
			
			int countOfProductB = productList.stream().filter(p -> p.getSKUId().equals("B")).collect(Collectors.toList()).size();
			
			int countOfProductC = productList.stream().filter(p -> p.getSKUId().equals("C")).collect(Collectors.toList()).size();
			int countOfProductD = productList.stream().filter(p -> p.getSKUId().equals("D")).collect(Collectors.toList()).size();
			
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
	
	public BigDecimal applyPromotion(Cart cart, List<IPromoProcessor> processor) {
		BigDecimal promotedCost = BigDecimal.ZERO;
		
		Map<PromotionConfiguration, List<Product>> detailSet = findPromo(cart);
		
		for (IPromoProcessor iPromoProcessor : processor) {
			promotedCost.add(iPromoProcessor.applyPromotion(cart.getProducts()));
		} 
		return promotedCost;
	}

}