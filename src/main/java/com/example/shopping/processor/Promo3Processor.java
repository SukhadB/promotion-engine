package com.example.shopping.processor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;

public class Promo3Processor extends AbstractPromoProcessor  {
	
	public Promo3Processor() {

		Map<String, Integer> promoDetails = new HashMap<>();
		promoDetails.put("C", 1);
		promoDetails.put("D", 1);
		
		promoConfig.setPromoCode("P3");
		promoConfig.setPromoDetails(promoDetails);
		promoConfig.setPromotionalCost(30);
	}

	@Override
	public BigDecimal applyPromotion(List<Product> products) {
		
		int countOfProductC = products.stream().filter(p -> p.getId().equals("C")).collect(Collectors.toList()).size();
		int countOfProductD = products.stream().filter(p -> p.getId().equals("D")).collect(Collectors.toList()).size();
		
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
		
		return new BigDecimal(totalCostofProductCPostPromotion + totalCostofProductDPostPromotion);
	}
	
	

}
