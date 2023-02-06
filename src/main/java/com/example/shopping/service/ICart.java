package com.example.shopping.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.shopping.model.Product;

public interface ICart {
	
	public List<Product> getProducts();

    public void empty();

    public void add(Product product);

    public void add(List<Product> products);

    public BigDecimal calculateFinalPrice();


}
