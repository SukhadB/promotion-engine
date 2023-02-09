package com.example.shopping.processor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

public class PercentagePromoProcessor implements IPromoProcessor {

	@Override
	public double applyPromotion(List<Product> products, Promotion promotion) {
		
		double totalApplicablePromotionCost = 0;
		
		if (products != null && promotion != null) {
			Map<String, Integer> promoDetails = promotion.getPromoDetails();
			int promotionalPercentage = promotion.getPromotionalPercentage();

			for (Map.Entry<String, Integer> entry : promoDetails.entrySet()) {
				String key = entry.getKey();
				Integer val = entry.getValue();

				List<Product> product = products.stream().filter(p -> p.getSKUId().equals(key))
						.collect(Collectors.toList());

				double productPrice = product.get(0).getPrice();
				
				if (product.size() < val) {
					totalApplicablePromotionCost = product.size() * productPrice;
				} else {
					totalApplicablePromotionCost = (product.size() * productPrice) * promotionalPercentage/100; 
				}
				
			}
		}
		
		return totalApplicablePromotionCost;
		
		
	}

}
