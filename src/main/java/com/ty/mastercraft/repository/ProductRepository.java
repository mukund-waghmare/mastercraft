package com.ty.mastercraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ty.mastercraft.dto.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.productName=?1")
	public Product getByName(String name);
	
	@Query("select p from Product p where p.productPrice=?1")
	public Product getByPrice(double price);
}
