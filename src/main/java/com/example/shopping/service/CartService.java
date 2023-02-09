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
public class CartService {

	private static CartService cartService = null;

	private PromotionEngine promotionEngine;

	private CartService() {
	}

	/**
	 * Constructs the CreateService Object
	 * 
	 * @return the createService Object
	 */
	public static CartService getInstance() {
		if (cartService == null) {
			cartService = new CartService();
		}
		return cartService;
	}

	/**
	 * Sets the promotion engine which holds all the promotion details
	 * 
	 * @param promotionEngine engine to find and apply promotion details to the
	 *                        product within the Cart
	 */
	public void setPromotionEngine(PromotionEngine promotionEngine) {
		this.promotionEngine = promotionEngine;
	}

	/**
	 * method to checkout all the products with the cart and calculate the final
	 * price basis the promotion and the price of the product
	 * 
	 * @param cart object consisting the product details
	 * @return final price for the all the products with promotion applied
	 */
	public double checkout(Cart cart) {
		double finalPrice = 0;

		if (cart.getProducts() != null) {

			Map<Promotion, List<Product>> promos = promotionEngine.getPromoForCart(cart.getProducts(),
					cart.getProductCount());

			for (Map.Entry<Promotion, List<Product>> listProductEntry : promos.entrySet()) {
				finalPrice += promotionEngine.getPromoProcessor(listProductEntry.getKey().getPromotionType())
						.applyPromotion(listProductEntry.getValue(), listProductEntry.getKey());
			}
		}

		return finalPrice;
	}
}
