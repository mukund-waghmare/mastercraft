package com.ty.mastercraft.exception;

public class OnlyMerchantCanAddProductException extends RuntimeException {

	private String message;
	
	public OnlyMerchantCanAddProductException() {
		
	}
	
	public OnlyMerchantCanAddProductException(String message) {
		this.message=message;
	}
	
	@Override
	public String getMessage() {
		
		return message;
	}
}
