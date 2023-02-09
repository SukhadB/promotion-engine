package com.example.shopping.model;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.shopping.exception.ProductNotPresentInInvetory;

public class InventoryTest {
	
	@Test
	public void createInventory() {
		List<Product> products = new ArrayList<>();
		try {
			products.add(new Product("A", 50));
			products.add(new Product("B", 30));
			products.add(new Product("C", 20));
			products.add(new Product("D", 15));
		} catch (ProductNotPresentInInvetory e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Inventory inventory = new Inventory(products);
		assertNotNull(inventory);
		assertNotNull(inventory.getListedProducts());
	}

}
