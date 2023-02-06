package com.example.shopping.processor;

import java.util.HashMap;
import java.util.Map;

public class Promo1Processor extends HomegenousPromoProcessor  {
	
	public Promo1Processor() {

		Map<String, Integer> promoDetails = new HashMap<>();
		promoDetails.put("A", 3);
		
		promoConfig.setPromoCode("P1");
		promoConfig.setPromoDetails(promoDetails);
		promoConfig.setPromotionalCost(130);
	} 

}
