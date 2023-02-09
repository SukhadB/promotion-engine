package com.example.shopping.processor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

/**
 * The class implements the applyPromotion method to calculate the cost of the
 * product based on percentage of product price
 * 
 * @author Sukhad Bhole
 *
 */
public class PercentagePromoProcessor implements IPromoProcessor {

	/**
	 * Calculates the total applicable cost of the matching product list and the
	 * promotion applicable for percentage
	 * 
	 * @param products  matching set for the promotion
	 * @param promotion promotion applicable
	 * @return total cost applicable post applicable promotion is applied
	 */
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
