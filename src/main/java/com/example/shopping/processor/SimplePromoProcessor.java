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

	Promotion promotion;

	public SimplePromoProcessor(Promotion promotion) {

		this.promotion = promotion;
	}

	@Override
	public double applyPromotion(List<Product> products) {

		Map<String, Integer> promoDetails = promotion.getPromoDetails();
		int promotionalCost = promotion.getPromotionalCost();

		double totalApplicablePromotionCost = 0;

		for (Map.Entry<String, Integer> entry : promoDetails.entrySet()) {
			String key = entry.getKey();
			Integer val = entry.getValue();

			List<Product> product = products.stream().filter(p -> p.getSKUId().equals(key))
					.collect(Collectors.toList());

			double productPrice = product.get(0).getPrice();

			totalApplicablePromotionCost = product.size() / val * promotionalCost + product.size() % val * productPrice;

		}

		return totalApplicablePromotionCost;

	}

}