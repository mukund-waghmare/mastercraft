package com.ty.mastercraft.exception;

public class ProductIdNotFoundException extends RuntimeException {

	private String message;
	
	public ProductIdNotFoundException() {
		
	}
	
	public ProductIdNotFoundException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		
		return message;
	}
}
