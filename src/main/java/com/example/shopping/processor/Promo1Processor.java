package com.example.shopping.processor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;

public class Promo1Processor extends AbstractPromoProcessor {
	
	public Promo1Processor() {

		Map<String, Integer> promoDetails = new HashMap<>();
		promoDetails.put("A", 3);
		
		promoConfig.setPromoCode("P1");
		promoConfig.setPromoDetails(promoDetails);
		promoConfig.setPromotionalCost(130);
	}
	
	@Override
	public BigDecimal applyPromotion(List<Product> products) {
		
		Map<String, Integer> promoDetails = promoConfig.getPromoDetails();
		int promotionalCost = promoConfig.getPromotionalCost();
		
		BigDecimal totalApplicablePromotionCost = BigDecimal.ZERO;
		
		for (Map.Entry<String, Integer> entry : promoDetails.entrySet()) {
			String key = entry.getKey();
			Integer val = entry.getValue();

			List<Product> product = products.stream().filter(p -> p.getSKUId().equals(key)).collect(Collectors.toList());
			
			double productPrice = product.get(0).getPrice();
			
			totalApplicablePromotionCost.add(new BigDecimal(product.size()/val * promotionalCost + product.size()%val * productPrice));
			
		}
		
		
		return totalApplicablePromotionCost;
		
	}

}