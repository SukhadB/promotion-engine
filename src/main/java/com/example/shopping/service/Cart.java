/**
 * This package consist of the services which will be exposed
 */
package com.example.shopping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.shopping.exception.ProductNotPresentInInvetory;
import com.example.shopping.model.Inventory;
import com.example.shopping.model.Product;

/**
 * The class consists of the Cart details
 * @author Sukhad Bhole
 *
 */
public class Cart implements ICart{
	
	private List<Product> productList = new ArrayList<>();
	private double totalAmount;
	private Map<String, Integer> productCount = new HashMap<>();
	
	private PromotionEngine promotionEngine;
	private Inventory inventory;
	
	public Cart() {
		
	}
	
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
	
	public Map<String, Integer> getProductCount(){
		return this.productCount;
	}
	
	@Override
	public void empty() {
		this.productList.clear();
		
	}

//	public void add(List<Product> productList) {
//		this.productList = productList;
//	}
	
	public void add(String productName) throws ProductNotPresentInInvetory {
		Product inventoryProduct = inventory.getListedProducts().get(productName);
		productList.add(new Product(inventoryProduct));
	}

	public double calculateTotalCartValue() {
		double totalCost = 0;
		for (Product product : productList) {
			totalCost += product.getPrice();
		}
		this.totalAmount = totalCost;
		return totalCost;
	}
	
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
	public void add(Product product) {
		if(this.productList == null) {
			this.productList = new ArrayList<Product>();
		}
		this.totalAmount += product.getPrice();
		Integer count = this.productCount.get(product.getSKUId());
		if (count == null) {
			count = 0;
		}
		this.productCount.put(product.getSKUId(), ++count);
		this.productList.add(product);
	}
	
	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", totalAmount=" + totalAmount + "]";
	}
	
	public double checkout() {
		double finalPrice = 0;
		
		if (productList != null) {
		
			Map<String, List<Product>> groupProducts = productList.stream().collect(Collectors.groupingBy(Product::getSKUId));
			
			for (Map.Entry<String, List<Product>> listProductEntry : groupProducts.entrySet()) {
				finalPrice += promotionEngine.findPromo(listProductEntry.getKey(), listProductEntry.getValue().size()).getProcessor().applyPromotion(listProductEntry.getValue());
	        }
		}
		
		return finalPrice;
	}

}
