package com.ty.mastercraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.mastercraft.dto.Product;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.service.ProductService;


@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/saveProduct/{uid}")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product,@PathVariable int uid ){
		System.out.println(product.getProductName());
		
		return productService.saveProduct(product, uid); 
	}
	
	@PutMapping("/update-product")
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product ){
		
		return productService.updateProduct(product); 
	}
	
	@GetMapping("/findproductbyid/{pid}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int pid ){
		
		return productService.getProductById(pid);
	}
	
	@GetMapping("/findproductbyname/{name}")
	public ResponseEntity<ResponseStructure<Product>> findProductByName(@PathVariable String name ){
		
		return productService.getProductByName(name);
	}
	
	@GetMapping("/findproductbyprice/{price}")
	public ResponseEntity<ResponseStructure<Product>> findProductByPrice(@PathVariable double price ){
		
		return productService.getProductByPrice(price);
	}
	
	@DeleteMapping("/removeproductbyid/{pid}")
	public ResponseEntity<ResponseStructure<Product>> removeProductById(@PathVariable int pid ){
		
		return productService.removeProductById(pid);
	}
	
}
