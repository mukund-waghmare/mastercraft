package com.ty.mastercraft.exception;

public class EmptyCartException extends RuntimeException {
	

	String message;
	
	
	public EmptyCartException() {
		// TODO Auto-generated constructor stub
	}

	
	public EmptyCartException(String message) {
		this.message=message;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
