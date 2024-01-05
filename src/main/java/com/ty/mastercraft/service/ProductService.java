package com.ty.mastercraft.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.mastercraft.dao.ProductDao;
import com.ty.mastercraft.dao.UserDao;
import com.ty.mastercraft.dto.Product;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.dto.UserRole;
import com.ty.mastercraft.exception.OnlyMerchantCanAddProductException;
import com.ty.mastercraft.exception.ProductIdNotFoundException;
import com.ty.mastercraft.exception.ProductNameNotFoundException;
import com.ty.mastercraft.exception.ProductPriceNotFoundException;
import com.ty.mastercraft.exception.UserIdNotFoundException;
import com.ty.mastercraft.repository.ShopingCartRepository;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	

	
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product,int uid){
		User user=userDao.getUserById(uid);
		if(user!=null) {
			if(user.getRole()==UserRole.Merchant) {
	
				System.out.println(product.getProductName());
				Product product2=productDao.addProduct(product);
				System.out.println(product2.getProductName());
				product2.setUser(user);
				Product product3=productDao.updateProduct(product2);
				System.out.println(product3.getProductName());
				List<Product> products=user.getMerchantProductList();
				if(products==null) {
					products=new ArrayList<>();
				}
				products.add(product3);
				User user1=userDao.updateUserById(user, uid);
				
				ResponseStructure<Product> structure=new ResponseStructure<>();
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage("product added successfully");
				structure.setData(product3);
				
				return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.CREATED);
			}
			else {
				throw new OnlyMerchantCanAddProductException("user is not merchant");
			}
		}
		else {
			throw new UserIdNotFoundException("invalid id: " + uid);
		}
		
	}
	
	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product){
		Product product2=productDao.getProductById(product.getProductId());
		if(product2!=null) {
			Product product3=productDao.updateProduct(product);
			
			ResponseStructure<Product> structure=new ResponseStructure<>();
			
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			structure.setMessage("product updated successfully");
			structure.setData(product3);
			
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.ACCEPTED);
		}
		else {
			throw new ProductIdNotFoundException("invalid id: "+ product.getProductId());
		}
	}
	
	public ResponseEntity<ResponseStructure<Product>> getProductById(int pid){
		Product product=productDao.getProductById(pid);
		if(product!=null) {
			ResponseStructure<Product> structure=new ResponseStructure<>();
			
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("product found successfully");
			structure.setData(product);
			
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
		}
		else {
			throw new ProductIdNotFoundException("invalid id: "+ pid);
		}
	}
	
	public ResponseEntity<ResponseStructure<Product>> getProductByName(String name){
		Product product=productDao.getProductByName(name);
		if(product!=null) {
			ResponseStructure<Product> structure=new ResponseStructure<>();
			
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("product found successfully");
			structure.setData(product);
			
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
		}
		else {
			throw new ProductNameNotFoundException("invalid name: "+ name);
		}
	}
	
	public ResponseEntity<ResponseStructure<Product>> getProductByPrice(double price){
		Product product=productDao.getProductByPrice(price);
		if(product!=null) {
			ResponseStructure<Product> structure=new ResponseStructure<>();
			
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("product found successfully");
			structure.setData(product);
			
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
		}
		else {
			throw new ProductPriceNotFoundException("invalid price: " + price);
		}
	}
	
	public ResponseEntity<ResponseStructure<Product>> removeProductById(int pid){
		Product product=productDao.getProductById(pid);
		if(product!=null) {
			Product product2=productDao.removeProduct(pid);
			ResponseStructure<Product> structure=new ResponseStructure<>();
			
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("product found successfully");
			structure.setData(product2);
			
			return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
		}
		else {
			throw new ProductIdNotFoundException("invalid id: "+ pid);
		}
	}
}
