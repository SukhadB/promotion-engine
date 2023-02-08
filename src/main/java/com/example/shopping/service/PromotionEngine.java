package com.example.shopping.service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

/**
 * The class helps to add and find promotion basis the product available within
 * the cart
 * 
 * @author Sukhad Bhole
 *
 */
public class PromotionEngine {

	private List<Promotion> promotionList;

	/**
	 * Constructs Promotion Engine with the list of promotion
	 * 
	 * @param promotionList
	 */
	public PromotionEngine(List<Promotion> promotionList) {
		this.promotionList = promotionList;
	}

	public List<Promotion> getPromotionList() {
		return promotionList;
	}

	/**
	 * This method is first approach towards the give problem of the
	 * promotion-engine
	 * 
	 * @param cart - the cart object with the details of the product
	 * @return - the total amount after the all the promotion are applied
	 */
	public double applyPromotion(Cart cart) {
		double promotedCost = 0;
		List<Product> productList = cart.getProducts();

		if (productList != null && !productList.isEmpty()) {
			int countOfProductA = productList.stream().filter(p -> p.getSKUId().equals("A"))
					.collect(Collectors.toList()).size();

			int countOfProductB = productList.stream().filter(p -> p.getSKUId().equals("B"))
					.collect(Collectors.toList()).size();

			int countOfProductC = productList.stream().filter(p -> p.getSKUId().equals("C"))
					.collect(Collectors.toList()).size();
			int countOfProductD = productList.stream().filter(p -> p.getSKUId().equals("D"))
					.collect(Collectors.toList()).size();

			double totalCostofProductAPostPromotion = (countOfProductA / 3 * 130) + (countOfProductA % 3 * 50);
			double totalCostofProductBPostPromotion = (countOfProductB / 2 * 45) + (countOfProductB % 2 * 30);
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
					totalCostofProductDPostPromotion = (countOfProductD * 30);
					totalCostofProductCPostPromotion = (countOfProductC - countOfProductD) * 20;
				} else if (countOfProductD > countOfProductC) {
					totalCostofProductCPostPromotion = (countOfProductC * 30);
					totalCostofProductDPostPromotion = (countOfProductD - countOfProductC) * 15;
				}
			}

			promotedCost = totalCostofProductAPostPromotion + totalCostofProductBPostPromotion
					+ totalCostofProductCPostPromotion + totalCostofProductDPostPromotion;
		}
		return promotedCost;
	}

	public Promotion findPromo(String productName, int count) {
		Promotion promotion = null;
		Optional<Promotion> optional = promotionList.stream().filter(
				p -> p.getPromoDetails().containsKey(productName) && count >= p.getPromoDetails().get(productName))
				.findFirst();

		if (optional != null) {
			try {
				promotion = optional.get();
			} catch (NoSuchElementException e) {
				promotion = new Promotion();
			}
		} else {
			promotion = new Promotion();
		}

		return promotion;
	}

	/**
	 * This methods finds the promotion applicable for the product list with the
	 * Cart
	 * 
	 * @param productList  - All the list of product with the cart
	 * @param productCount - the product and the quantity of the product with the
	 *                     cart
	 * @return - the Promotion and the matching set of the product for the promotion
	 */
	public Map<Promotion, List<Product>> findPromo(List<Product> productList, Map<String, Integer> productCount) {
		return productList.stream().collect(Collectors.groupingBy(i -> {
			String skuId = i.getSKUId();

			int count = productCount.get(skuId);

			Optional<Promotion> optional = promotionList.stream()
					.filter(p -> p.getPromoDetails().containsKey(skuId) && count >= p.getPromoDetails().get(skuId))
					.findFirst();

			Promotion promotion = null;

			if (optional != null) {
				try {
					promotion = optional.get();
					return promotion;
				} catch (NoSuchElementException e) {
					return new Promotion();
				}
			} else {
				return new Promotion();
			}

		}, Collectors.toList()));
	}

	/*
	 * public Map<PromotionConfiguration, List<Product>> findPromo(Cart cart) {
	 * Map<PromotionConfiguration, List<Product>> applicablePromotions = null;
	 * 
	 * Map<String, Integer> productCount = cart.getProductCount(); List<Product>
	 * products = cart.getProducts();
	 * 
	 * for (PromotionConfiguration promotion : promotionList) { Map<String, Integer>
	 * promoDetails = promotion.getPromoDetails();
	 * 
	 * boolean isPromoApplicable = true; List<Product> matchingSet = new
	 * ArrayList<>(); for (Map.Entry<String, Integer> entry :
	 * promoDetails.entrySet()) { String key = entry.getKey(); Integer val =
	 * entry.getValue();
	 * 
	 * if (productCount.get(key) < val) { isPromoApplicable = false; break; }
	 * matchingSet.addAll(products.stream().filter(p ->
	 * p.getSKUId().equals(key)).collect(Collectors.toList())); }
	 * 
	 * if (isPromoApplicable) { applicablePromotions.put(promotion, matchingSet); }
	 * }
	 * 
	 * PromotionConfiguration defaultPromotion = new
	 * PromotionConfiguration("Default");
	 * 
	 * for (Map.Entry<String, Integer> productName : productCount.entrySet()) {
	 * String key = productName.getKey(); Integer val = productName.getValue();
	 * 
	 * Optional<PromotionConfiguration> optional = promotionList.stream().filter(p
	 * -> p.getPromoDetails().containsKey(key) && p.getPromoDetails().get("A") >=
	 * val).findFirst(); if (optional == null) { List<Product> existingProducts =
	 * applicablePromotions.get(defaultPromotion); if (existingProducts == null) {
	 * existingProducts = new ArrayList<>(); } List<Product> currentMatchingSet =
	 * products.stream().filter(p ->
	 * p.getSKUId().equals(key)).collect(Collectors.toList()); if
	 * (currentMatchingSet != null) { existingProducts.addAll(currentMatchingSet); }
	 * 
	 * applicablePromotions.put(defaultPromotion, existingProducts); } }
	 * 
	 * return applicablePromotions; }
	 * 
	 * public void findApplicablePromo(Cart cart) { Map<PromotionConfiguration,
	 * List<Product>> applicablePromotions = null; Map<String, Integer> productCount
	 * = cart.getProductCount(); List<Product> products = cart.getProducts();
	 * 
	 * PromotionConfiguration defaultPromotion = new
	 * PromotionConfiguration("Default");
	 * 
	 * for (Map.Entry<String, Integer> productName : productCount.entrySet()) {
	 * String key = productName.getKey(); Integer val = productName.getValue();
	 * 
	 * Optional<PromotionConfiguration> optional = promotionList.stream().filter(p
	 * -> p.getPromoDetails().containsKey(key) && p.getPromoDetails().get("A") >=
	 * val).findFirst(); if (optional == null) { List<Product> existingProducts =
	 * applicablePromotions.get(defaultPromotion); if (existingProducts == null) {
	 * existingProducts = new ArrayList<>(); } List<Product> currentMatchingSet =
	 * products.stream().filter(p ->
	 * p.getSKUId().equals(key)).collect(Collectors.toList()); if
	 * (currentMatchingSet != null) { existingProducts.addAll(currentMatchingSet); }
	 * 
	 * applicablePromotions.put(defaultPromotion, existingProducts); } }
	 * 
	 * }
	 */

}