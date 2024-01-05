package com.ty.mastercraft.exception;

public class LoginFailedException extends RuntimeException {

	
	String message;
	
	
	public LoginFailedException() {
		// TODO Auto-generated constructor stub
	}

	
	public LoginFailedException(String message) {
		this.message=message;
	}
	
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
}
