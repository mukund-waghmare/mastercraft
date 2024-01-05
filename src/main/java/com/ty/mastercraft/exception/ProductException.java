package com.ty.mastercraft.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ty.mastercraft.dto.ResponseStructure;

@RestControllerAdvice
public class ProductException {

	@ExceptionHandler(OnlyMerchantCanAddProductException.class)
	public ResponseEntity<ResponseStructure<String>> merchantAddProduct(OnlyMerchantCanAddProductException exception){
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		structure.setMessage(exception.getMessage());
		structure.setData("only merchant can add product");
		
		return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.BAD_REQUEST);
	}
}
