package com.example.shopping.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The inventory class to hold all the Product available to the shopping cart
 * 
 * @author Sukhad Bhole
 *
 */
public class Inventory {

	private Map<String, Product> listedProducts = new HashMap<>();

	/**
	 * Constructs the inventory object with the list of Product
	 * 
	 * @param - productList the Product List
	 */
	public Inventory(List<Product> productList) {
		productList.stream().forEach(p -> {
			listedProducts.put(p.getSKUId(), p);
		});
	}

	/**
	 * returns all the product available with the inventory
	 * @return map of the product within the inventory 
	 */
	public Map<String, Product> getListedProducts() {
		return listedProducts;
	}

}
