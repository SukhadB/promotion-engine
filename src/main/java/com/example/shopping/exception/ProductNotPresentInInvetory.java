package com.example.shopping.exception;

public class ProductNotPresentInInvetory extends Exception {

	private static final long serialVersionUID = 1L;

	public ProductNotPresentInInvetory(String message) {
        super(message);
    }
}
