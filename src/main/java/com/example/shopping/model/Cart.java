package com.example.shopping.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.shopping.exception.ProductNotPresentInInvetory;

/**
 * Cart Object to hold the added products
 * 
 * @author Sukhad Bhole
 *
 */
public class Cart implements ICart {

	private List<Product> productList = new ArrayList<>();
	private double totalAmount;
	
	Logger logger = Logger.getLogger(Cart.class.getName());

	/**
	 * The count of the product with the cart, the key is the Product Name and the
	 * value is the count of the product present with the cart
	 */
	private Map<String, Integer> productCount = new HashMap<>();

	/**
	 * The inventory with details of the product available for buying
	 */
	private Inventory inventory;

	/**
	 * Construct cart object with the inventory object
	 * 
	 * @param invetory
	 */
	public Cart(Inventory invetory) {
		this.inventory = invetory;
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

	/*
	 * Clears the product list added to the Cart
	 */
	public void empty() {
		this.productList.clear();

	}

	/**
	 * Adds product from the inventory to the product List and also increment the
	 * count of the product
	 * 
	 * @param productName name of the product to be added
	 * @throws ProductNotPresentInInvetory exception thrown in case the product is
	 *                                     not present in inventory
	 */
	public void add(String productName) throws ProductNotPresentInInvetory {
		Product inventoryProduct = inventory.getListedProducts().get(productName);
		productList.add(new Product(inventoryProduct));
		Integer count = this.productCount.get(inventoryProduct.getSKUId());
		if (count == null) {
			count = 0;
		}
		this.productCount.put(inventoryProduct.getSKUId(), ++count);
	}

	/**
	 * Adds list of product with product name to the cart
	 * 
	 * @param productNames list of the product name to be added to the cart
	 */
	public void add(List<String> productNames) {

		productNames.forEach((productName) -> {
			try {
				add(productName);
			} catch (ProductNotPresentInInvetory e) {
				logger.log(Level.FINE, e.getMessage());
			}
		});
	}

	/*
	 * Calculates the total cart value of the all the Products added considering the
	 * actual price
	 */
	public double calculateTotalCartValue() {
		double totalCost = 0;
		for (Product product : productList) {
			totalCost += product.getPrice();
		}
		this.totalAmount = totalCost;
		return totalCost;
	}

}
