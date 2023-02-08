package com.example.shopping.processor;

import java.util.List;

import com.example.shopping.model.Product;

/**
 * Classes that implement the interface needs to implement the applyPromotion
 * method to put the logic to calculate the promotional cost for the available
 * product list
 * 
 * @author Sukhad Bhole
 *
 */
public interface IPromoProcessor {

	public double applyPromotion(List<Product> products);

}
