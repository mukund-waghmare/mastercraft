package com.ty.mastercraft.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ty.mastercraft.dto.Orders;
import com.ty.mastercraft.dto.Product;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.ShopingCart;
import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.repository.UserRepository;

import jakarta.persistence.criteria.Order;

@Repository
public class UserDao {
	
	@Autowired
	UserRepository userRepository;
	
	
	public User saveUser(User PassedUser)
	{
		userRepository.save(PassedUser);
		return PassedUser;
	}
	
	public User saveAdmin(User PassedUser)
	{
		userRepository.save(PassedUser);
		return PassedUser;
	}
	
	public User getUserById(int passedId)
	{
		Optional<User> optUser=userRepository.findById(passedId);
		
		User user=optUser.get();
		return user;
		
	}
	
	
	public boolean deleteUserById(int passedId)
	{

		Optional<User> optUser=userRepository.findById(passedId);
		
		if(optUser.isPresent())
		{

			User user=optUser.get();
			userRepository.delete(user);
			
			return true;
		}
		
		return false;
	}
	
	// update name,email,password
	public User updateUserById(User passedUserToUpdate,int UserIdToUpdate)
	{
		
		Optional<User> optUser=userRepository.findById(UserIdToUpdate);
		
		if(optUser.isPresent())
		{

			User user=optUser.get();
			
			user.setUserName(passedUserToUpdate.getUserName());
			user.setUserEmail(passedUserToUpdate.getUserEmail());
			user.setUserPassword(passedUserToUpdate.getUserPassword());
			userRepository.save(user);
			
			return user;
			
		}
		return null;
		
	}
	
	
	public User loginUser(String email,String password)
	{
		User user=userRepository.findUserByuserEmailAnduserPassword(email, password);
		
		return user;
	}
	
	public List<Product> getCartByUserId(int userId)
	{
		User user=getUserById(userId);
		if(user!=null)
		{
			System.out.println(user);
			ShopingCart cart=user.getShopingCart();
			
			if(cart!=null)
			{
			List<Product> productList=cart.getProductList();
			
			if(!productList.isEmpty())
			{
				return productList;
			}
			}
			
			return null;
		}
		
		return null;
	}
	
	
	public List<Orders> getOrdersByUserId(int userId)
	{
		User user=getUserById(userId);
		
		if(user!=null)
		{
			
		  List<Orders> order= user.getOrderList();
		  
		  return order;
		}
		return null;
	}
	
	
	public Product addProductToShopingCart(int productId,int userId)
	{
		
		User user=getUserById(userId);
		//product repository get product by id
		
		List<Product> productList=null;
		
		if(user!=null)
		{
		
			if(user.getShopingCart()!=null)
			{
				ShopingCart cart=user.getShopingCart();
				if(cart==null)
				{
					cart= new ShopingCart();
					
				}
				List<Product> product=cart.getProductList();
				if(product==null)
				{
					product= new ArrayList();
				}
				
			}
			
		}
		return null;
		
	}
	
	
	

}
