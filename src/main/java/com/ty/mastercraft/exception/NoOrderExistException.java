package com.ty.mastercraft.exception;

public class NoOrderExistException extends RuntimeException {
	

	String message;
	
	
	public NoOrderExistException() {
		// TODO Auto-generated constructor stub
	}

	
	public NoOrderExistException(String message) {
		this.message=message;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

}
