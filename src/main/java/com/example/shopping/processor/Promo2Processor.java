package com.example.shopping.processor;

import java.util.HashMap;
import java.util.Map;

public class Promo2Processor extends HomegenousPromoProcessor  {
	
	public Promo2Processor() {

		Map<String, Integer> promoDetails = new HashMap<>();
		promoDetails.put("B", 2);
		
		promoConfig.setPromoCode("P2");
		promoConfig.setPromoDetails(promoDetails);
		promoConfig.setPromotionalCost(45);
	} 

}
