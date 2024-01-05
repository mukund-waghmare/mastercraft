package com.ty.mastercraft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import com.ty.mastercraft.dto.ResponseStructure;

@ControllerAdvice
public class UserException {
	
	
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> UserIdNotFoundException(UserIdNotFoundException exception)
	{
		ResponseStructure<String> responseStructure= new ResponseStructure<String>();
		
		responseStructure.setMessage("User Doe not Exist For Given Id");
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(LoginFailedException.class)
	public ResponseEntity<ResponseStructure<String>> UserIdNotFoundException(LoginFailedException exception)
	{
		ResponseStructure<String> responseStructure= new ResponseStructure<String>();
		
		responseStructure.setMessage("Invalid Email or Password");
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setData("Invalid Login Credentials");
		
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure,HttpStatus.BAD_REQUEST);
		
	}

}
