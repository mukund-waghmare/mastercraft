package com.ty.mastercraft.exception;

public class ProductPriceNotFoundException extends RuntimeException {

	private String message;
	
	public ProductPriceNotFoundException() {
		// TODO Auto-generated constructor stub
	}
	
	public ProductPriceNotFoundException(String message) {
		this.message=message;
	}
}
