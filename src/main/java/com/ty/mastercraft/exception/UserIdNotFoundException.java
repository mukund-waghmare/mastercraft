package com.ty.mastercraft.exception;

public class UserIdNotFoundException extends RuntimeException {
	
	String message;
	
	
	public UserIdNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	
	public UserIdNotFoundException(String message) {
		this.message=message;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
