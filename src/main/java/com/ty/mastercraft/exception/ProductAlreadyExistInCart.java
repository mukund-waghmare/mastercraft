package com.ty.mastercraft.exception;

public class ProductAlreadyExistInCart extends RuntimeException {

	String message;
	
	
	public ProductAlreadyExistInCart() {
		// TODO Auto-generated constructor stub
	}

	
	public ProductAlreadyExistInCart(String message) {
		this.message=message;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
