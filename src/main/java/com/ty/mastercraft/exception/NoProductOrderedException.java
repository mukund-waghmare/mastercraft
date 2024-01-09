package com.ty.mastercraft.exception;

public class NoProductOrderedException extends RuntimeException {
	

	String message;
	
	
	public NoProductOrderedException() {
		// TODO Auto-generated constructor stub
	}

	
	public NoProductOrderedException(String message) {
		this.message=message;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
