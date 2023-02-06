package com.example.shopping.service;

import java.util.List;

import com.example.shopping.model.Product;

public interface ICart {
	
	public List<Product> getItems();

    public void empty();

    public void add(String itemName);

    public void add(List<String> itemNames);

    public double calculateFinalPrice();


}
