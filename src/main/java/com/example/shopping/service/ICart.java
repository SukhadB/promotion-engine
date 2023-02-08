package com.example.shopping.service;

import java.util.List;

import com.example.shopping.exception.ProductNotPresentInInvetory;
import com.example.shopping.model.Product;

/**
 * Interface for Cart class to implement the required method
 * 
 * @author Sukhad Bhole
 *
 */
public interface ICart {

	public List<Product> getProducts();

	public void empty();

	public void add(String productName) throws ProductNotPresentInInvetory;

	public void add(List<String> productNames);

	public double calculateTotalCartValue();

}
