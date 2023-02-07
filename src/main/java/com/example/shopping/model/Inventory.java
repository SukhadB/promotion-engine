package com.example.shopping.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {

	private Map<String, Product> listedProducts = new HashMap<>();
	
	public Inventory(List<Product> productList) {
		productList.stream().forEach(p -> {
			listedProducts.put(p.getSKUId(), p);
		});
	}

	public Map<String, Product> getListedProducts() {
		return listedProducts;
	}

}
