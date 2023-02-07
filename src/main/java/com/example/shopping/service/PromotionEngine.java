package com.example.shopping.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;
import com.example.shopping.processor.IPromoProcessor;

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
	
	public Promotion findPromo(String productName, int count) {
		Promotion promoConfiguration = null;
		Optional<Promotion> optional = promotionList.stream().filter(p -> p.getPromoDetails().containsKey(productName) && count >= p.getPromoDetails().get(productName)).findFirst();
		
		if (optional != null) {
			try {
				promoConfiguration= optional.get();
			} catch (NoSuchElementException e) {
				promoConfiguration = new Promotion("Default");
			}
		} else {
			promoConfiguration = new Promotion("Default");
		}
		
		return promoConfiguration;
	}
	
	
	/*public Map<PromotionConfiguration, List<Product>> findPromo(Cart cart) {
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
					break;
				}
				matchingSet.addAll(products.stream().filter(p -> p.getSKUId().equals(key)).collect(Collectors.toList()));
			}
			
			if (isPromoApplicable) {
				applicablePromotions.put(promotion, matchingSet);
			}
		}
		
		PromotionConfiguration defaultPromotion = new PromotionConfiguration("Default");
		
		for (Map.Entry<String, Integer> productName : productCount.entrySet()) {
			String key = productName.getKey();
			Integer val = productName.getValue();
			
			Optional<PromotionConfiguration> optional = promotionList.stream().filter(p -> p.getPromoDetails().containsKey(key) && p.getPromoDetails().get("A") >= val).findFirst();
			if (optional == null) {
				List<Product> existingProducts = applicablePromotions.get(defaultPromotion);
				if (existingProducts == null) {
					existingProducts = new ArrayList<>();
				}
				List<Product> currentMatchingSet = products.stream().filter(p -> p.getSKUId().equals(key)).collect(Collectors.toList());
				if (currentMatchingSet != null) {
					existingProducts.addAll(currentMatchingSet);
				}
				
				applicablePromotions.put(defaultPromotion, existingProducts);
			}
		}
		
		return applicablePromotions;
	}
	
	public void findApplicablePromo(Cart cart) {
		Map<PromotionConfiguration, List<Product>> applicablePromotions = null;
		Map<String, Integer> productCount = cart.getProductCount();
		List<Product> products = cart.getProducts();
		
		PromotionConfiguration defaultPromotion = new PromotionConfiguration("Default");
		
		for (Map.Entry<String, Integer> productName : productCount.entrySet()) {
			String key = productName.getKey();
			Integer val = productName.getValue();
			
			Optional<PromotionConfiguration> optional = promotionList.stream().filter(p -> p.getPromoDetails().containsKey(key) && p.getPromoDetails().get("A") >= val).findFirst();
			if (optional == null) {
				List<Product> existingProducts = applicablePromotions.get(defaultPromotion);
				if (existingProducts == null) {
					existingProducts = new ArrayList<>();
				}
				List<Product> currentMatchingSet = products.stream().filter(p -> p.getSKUId().equals(key)).collect(Collectors.toList());
				if (currentMatchingSet != null) {
					existingProducts.addAll(currentMatchingSet);
				}
				
				applicablePromotions.put(defaultPromotion, existingProducts);
			}
		}
		
	}*/

}