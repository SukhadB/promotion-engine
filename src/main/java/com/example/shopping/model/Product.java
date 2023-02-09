package com.example.shopping.model;

import com.example.shopping.exception.ProductNotPresentInInvetory;

/**
 * This class defines the product and its attributes present in the inventory
 * and available to add within the Cart
 * 
 * @author Sukhad Bhole
 *
 */
public class Product {

	private String skuId;
	private double price;

	/**
	 * Constructs the new product with the required parameter
	 * 
	 * @param skuId - Stock Keeping Unit Identifier of the product
	 * @param price - Price of the Product
	 */
	public Product(String skuId, double price) throws ProductNotPresentInInvetory {
		if (skuId == null || skuId.equals("")) {
			throw new IllegalArgumentException("skuid cannot be null");
		}
		this.skuId = skuId;
		this.price = price;
	}

	/**
	 * Constructs the new product with product object obtained mostly from inventory
	 * object available with the cart
	 * 
	 * @param product - the product object
	 * @throws ProductNotPresentInInvetory - in case the Product trying to add is
	 *                                     not present in the Inventory
	 */
	public Product(Product product) throws ProductNotPresentInInvetory {
		if (product == null) {
			throw new IllegalArgumentException("Item cannot be null.");
		}

		this.skuId = product.skuId;
		this.price = product.getPrice();
	}

	public String getSKUId() {
		return skuId;
	}

	public void setSKUId(String id) {
		this.skuId = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [skuId=" + skuId + ", price=" + price + "]";
	}

}
