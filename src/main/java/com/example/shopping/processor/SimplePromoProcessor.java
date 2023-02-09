package com.example.shopping.processor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

/**
 * The class implements the applyPromotion method to calculate the promotional
 * cost for promotion with single product within the promotion
 * 
 * @author Sukhad Bhole
 *
 */
public class SimplePromoProcessor implements IPromoProcessor {

	/**
	 * Calculates the total applicable cost of the matching product list and the
	 * promotion applicable
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
			int promotionalCost = promotion.getPromotionalCost();

			for (Map.Entry<String, Integer> entry : promoDetails.entrySet()) {
				String key = entry.getKey();
				Integer val = entry.getValue();

				List<Product> product = products.stream().filter(p -> p.getSKUId().equals(key))
						.collect(Collectors.toList());

				double productPrice = product.get(0).getPrice();

				totalApplicablePromotionCost = product.size() / val * promotionalCost
						+ product.size() % val * productPrice;

			}
		}

		return totalApplicablePromotionCost;

	}

}