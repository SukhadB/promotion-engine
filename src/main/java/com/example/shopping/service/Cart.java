package com.example.shopping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.shopping.exception.ProductNotPresentInInvetory;
import com.example.shopping.model.Inventory;
import com.example.shopping.model.Product;
import com.example.shopping.model.Promotion;

/**
 * The class consists of the Cart details
 * 
 * @author Sukhad Bhole
 *
 */
public class Cart implements ICart {

	private List<Product> productList = new ArrayList<>();
	private double totalAmount;

	/**
	 * The count of the product with the cart, the key is the Product Name and the
	 * value is the count of the product present with the cart
	 */
	private Map<String, Integer> productCount = new HashMap<>();

	private PromotionEngine promotionEngine;
	private Inventory inventory;

	/**
	 * Constructs the cart object with promotion engine and inventory object
	 * 
	 * @param promotionEngine - it holds the list of all the promotion
	 * @param inventory       - it holds all the product to add within the shopping
	 *                        cart
	 */
	public Cart(PromotionEngine promotionEngine, Inventory inventory) {
		this.promotionEngine = promotionEngine;
		this.inventory = inventory;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public List<Product> getProducts() {
		return productList;
	}

	public Map<String, Integer> getProductCount() {
		return this.productCount;
	}

	@Override
	public void empty() {
		this.productList.clear();

	}

	@Override
	public void add(String productName) throws ProductNotPresentInInvetory {
		Product inventoryProduct = inventory.getListedProducts().get(productName);
		productList.add(new Product(inventoryProduct));
		Integer count = this.productCount.get(inventoryProduct.getSKUId());
		if (count == null) {
			count = 0;
		}
		this.productCount.put(inventoryProduct.getSKUId(), ++count);
	}

	@Override
	public void add(List<String> productNames) {

		productNames.forEach((productName) -> {
			try {
				add(productName);
			} catch (ProductNotPresentInInvetory e) {
				e.printStackTrace();
			}
		});
	}

	@Override
	public double calculateTotalCartValue() {
		double totalCost = 0;
		for (Product product : productList) {
			totalCost += product.getPrice();
		}
		this.totalAmount = totalCost;
		return totalCost;
	}

	public double checkout() {
		double finalPrice = 0;

		if (productList != null) {

			Map<Promotion, List<Product>> groupProducts = promotionEngine.findPromo(productList, productCount);

			for (Map.Entry<Promotion, List<Product>> listProductEntry : groupProducts.entrySet()) {
				finalPrice += promotionEngine.getPromoProcessor(listProductEntry.getKey().getPromotionType())
						.applyPromotion(listProductEntry.getValue(), listProductEntry.getKey());
			}
		}

		return finalPrice;
	}

	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", totalAmount=" + totalAmount + ", productCount=" + productCount
				+ ", promotionEngine=" + promotionEngine + ", inventory=" + inventory + "]";
	}
}
