package com.example.shopping.processor;

import java.util.List;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

public class ComplexProcessor implements IPromoProcessor  {
	
	Promotion promoConfig = new Promotion();
	
	public ComplexProcessor(Promotion promoConfig) {

		this.promoConfig = promoConfig;
	}

	@Override
	public double applyPromotion(List<Product> products) {
		
		int countOfProductC = products.stream().filter(p -> p.getSKUId().equals("C")).collect(Collectors.toList()).size();
		int countOfProductD = products.stream().filter(p -> p.getSKUId().equals("D")).collect(Collectors.toList()).size();
		
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
		
		return totalCostofProductCPostPromotion + totalCostofProductDPostPromotion;
	}
	
	

}
