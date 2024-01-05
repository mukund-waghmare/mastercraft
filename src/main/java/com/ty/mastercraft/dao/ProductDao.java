package com.ty.mastercraft.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.mastercraft.dto.Product;
import com.ty.mastercraft.repository.ProductRepository;

@Repository
public class ProductDao {

	@Autowired
	private ProductRepository productRepository;
	
	
	public Product addProduct(Product product) {
		
		return  productRepository.save(product);
	}
	
	
	public Product updateProduct(Product product) {
		
		return productRepository.save(product);
	}
	
	public Product getProductById(int id) {
		Optional<Product> optional=productRepository.findById(id);
		if(optional.isPresent()) {
			Product product=optional.get();
			return product;
		}
		else
			return null;
	}
	
	public Product removeProduct(int id) {
		Product product=getProductById(id);
		if(product!=null) {
			productRepository.deleteById(id);
			return product;
		}
		return null;
	}
	
	public Product getProductByName(String name) {
		Product product=productRepository.getByName(name);
		if(product!=null) {
			
			return product;
		}
		else
			return null;
	}
	
	public Product getProductByPrice(double price) {
		Product product=productRepository.getByPrice(price);
		if(product!=null) {
			
			return product;
		}
		else
			return null;
	}
}
