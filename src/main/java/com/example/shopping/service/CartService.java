package com.example.shopping.service;

import java.util.List;
import java.util.Map;

import com.example.shopping.model.Cart;
import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

/**
 * The class consists of the Cart details
 * 
 * @author Sukhad Bhole
 *
 */
public class CartService  {
	
	private static CartService cartService = null;

	private PromotionEngine promotionEngine;
	
	private CartService() {
	}
	
	public static CartService getInstance() {
		if (cartService == null) {
			cartService = new CartService();
		}
		return cartService;
	}
	
	public void setPromotionEngine(PromotionEngine promotionEngine) {
		this.promotionEngine = promotionEngine;
	}

	
	public double checkout(Cart cart) {
		double finalPrice = 0;

		if (cart.getProducts() != null) {

			Map<Promotion, List<Product>> promos = promotionEngine.getPromoForCart(cart.getProducts(), cart.getProductCount());

			for (Map.Entry<Promotion, List<Product>> listProductEntry : promos.entrySet()) {
				finalPrice += promotionEngine.getPromoProcessor(listProductEntry.getKey().getPromotionType())
						.applyPromotion(listProductEntry.getValue(), listProductEntry.getKey());
			}
		}

		return finalPrice;
	}
}
