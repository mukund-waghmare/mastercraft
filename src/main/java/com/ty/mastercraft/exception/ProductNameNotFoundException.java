package com.ty.mastercraft.exception;

public class ProductNameNotFoundException extends RuntimeException {

	private String message;
	
	public ProductNameNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductNameNotFoundException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		
		return message;
	}
}
