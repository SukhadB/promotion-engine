package com.example.shopping.exception;

/**
 * @author Sukhad Bhole
 */
public class ProductNotPresentInInvetory extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new exception with the specified cause and a message
	 *
	 * @param message is the cause the exception occurred
	 */
	public ProductNotPresentInInvetory(String message) {
		super(message);
	}
}
