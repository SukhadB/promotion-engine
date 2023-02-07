/**
 * This package consist of the services which will be exposed
 */
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
 * @author Sukhad Bhole
 *
 */
public class Cart implements ICart{
	
	private List<Product> productList = new ArrayList<>();
	private double totalAmount;
	private Map<String, Integer> productCount = new HashMap<>();
	
	private PromotionEngine promotionEngine;
	private Inventory inventory;
	
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
				finalPrice += listProductEntry.getKey().getProcessor().applyPromotion(listProductEntry.getValue());
	        }
		}
		
		return finalPrice;
	}
	
	@Override
	public String toString() {
		return "Cart [productList=" + productList + ", totalAmount=" + totalAmount + "]";
	}
	
	/*public static void main(String[] args) {
		
		Inventory inventory;
		PromotionEngine promotionEngine;
	    List<Product> products = new ArrayList<>();
	    
	    products.add(new Product("A", 50));
    	products.add(new Product("B", 30));
    	products.add(new Product("C", 20));
    	products.add(new Product("D", 15));
	    
	    List<Promotion> promotionList = new ArrayList<>();
    	Map<String, Integer> promoDetails1 = new HashMap<>();
		promoDetails1.put("A", 3);
		Promotion promotion1 = new Promotion("P1", promoDetails1, 130);
		promotionList.add(promotion1);
		
		Map<String, Integer> promoDetails2 = new HashMap<>();
		promoDetails2.put("B", 2);
		Promotion promotion2 = new Promotion("P2", promoDetails2, 45);
		promotionList.add(promotion2);
		
		Map<String, Integer> promoDetails3 = new HashMap<>();
		promoDetails3.put("C", 1);
		promoDetails3.put("D", 1);
		Promotion promotion3 = new Promotion("P3", promoDetails3, 30);
		promotionList.add(promotion3);
		
		promotionEngine = new PromotionEngine(promotionList);
        inventory = new Inventory(products);
        
        List<String> order = Arrays.asList( "D");
		
		Cart cart = new Cart(promotionEngine, inventory);
		cart.add(order);
		
		List<Product> productList = cart.getProducts();
		

		List<Integer> list = Arrays.asList(2, 3, 4, 9, 11, 17, 28, 29, 32);
		
		Map<String, Integer> productCount = cart.getProductCount();
		
		
		System.out.println(productList.stream().collect(Collectors.groupingBy(i -> {
			String skuId = i.getSKUId();
			
			int count = productCount.get(skuId);
			
			Optional<Promotion> optional = promotionList.stream().filter(p -> p.getPromoDetails().containsKey(skuId) && count >= p.getPromoDetails().get(skuId)).findFirst();
			
			Promotion promotion = null;
			
			if (optional != null) {
				try {
					promotion= optional.get();
					return promotion;
				} catch (NoSuchElementException e) {
					return new Promotion("Default");
				}
			} else {
				return new Promotion("Default");
			}

		}, Collectors.toList())));
		
		Map<Promotion, List<Product>> groupProducts = productList.stream().collect(Collectors.groupingBy(i -> {
			String skuId = i.getSKUId();
			
			int count = productCount.get(skuId);
			
			Optional<Promotion> optional = promotionList.stream().filter(p -> p.getPromoDetails().containsKey(skuId) && count >= p.getPromoDetails().get(skuId)).findFirst();
			
			Promotion promotion = null;
			
			if (optional != null) {
				try {
					promotion= optional.get();
					return promotion;
				} catch (NoSuchElementException e) {
					return new Promotion("Default");
				}
			} else {
				return new Promotion("Default");
			}

		}, Collectors.toList()));
		
		double finalPrice = 0;
		
		for (Map.Entry<Promotion, List<Product>> listProductEntry : groupProducts.entrySet()) {
			finalPrice += listProductEntry.getKey().getProcessor().applyPromotion(listProductEntry.getValue());
        }
		
		System.out.println(finalPrice);
		
	}*/

}
