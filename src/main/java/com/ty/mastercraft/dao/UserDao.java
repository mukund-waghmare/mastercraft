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
import com.ty.mastercraft.exception.EmptyCartException;
import com.ty.mastercraft.exception.UserIdNotFoundException;
import com.ty.mastercraft.repository.OrderRepository;
import com.ty.mastercraft.repository.ProductRepository;
import com.ty.mastercraft.repository.ShopingCartRepository;
import com.ty.mastercraft.repository.UserRepository;

import jakarta.persistence.criteria.Order;

@Repository
public class UserDao {
	
	@Autowired
	UserRepository userRepository;
	

	
	@Autowired
	ShopingCartRepository shopingCartRepository;
	
	
	@Autowired
	OrderRepository orderRepository;
	
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
		
		if(optUser.isPresent())
		{
			User user=optUser.get();
			return user;
		}
		return null;
		
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
	
	
	public ShopingCart addProductToCart(User passedUser,ShopingCart shopingCart)
	{
		shopingCartRepository.save(shopingCart);
		userRepository.save(passedUser);
		return shopingCart;
		
	}
	
	
	public List<Orders> orderAllProductOfCart(int userId)
	{
		User user=getUserById(userId);
		
		
		if(user!=null)
		{  
			ShopingCart cart=user.getShopingCart();
			if(cart!=null)
			{
			List<Product> productListToOrder=cart.getProductList();
			  if(productListToOrder!=null)
			  {
				  Orders order= new Orders();

				  order.setUser(user);
				  
				  List<Orders> ordersList=user.getOrderList();
				  if(ordersList==null)
				  {
					  ordersList= new ArrayList<>();
				  }
				  else
				  {
					  double totalPrice=0;
					  
					  for(int i=0;i<ordersList.size();i++)
					  {
						List<Product> product= user.getOrderList().get(i).getOrderList();
						if(product!=null)
						{
							for(int j=0;j<product.size();j++)
							{

								totalPrice=totalPrice+product.get(i).getProductPrice();
							    
							}
							
						}
					  }
				  }
				  ordersList.add(order);
				  user.setOrderList(ordersList);
				  orderRepository.save(order);
				  
				  
				  
				  return ordersList;
	
			  }
			  else
			  {
			    
			  }
			
			
			}
			else
			{
				throw new EmptyCartException();
			}
			
			
		}
		else
		{
			throw new UserIdNotFoundException();
		}
		
	
		
		
		
//		System.out.println("=============================================="+user.getUserEmail());
//		if(user!=null)
//		{
//			ShopingCart cart=user.getShopingCart();
//			System.out.println("=============================================="+cart.getCartId());
//			
//			List<Product> product=cart.getProductList();
//			double totalAmount=0;
//			
//			
//			for(Product p:product)
//			{
//				totalAmount=totalAmount+p.getProductPrice();
//				System.out.println("================"+p.getProductName()+"======================");
//			}
//			
//			List<Orders> order=user.getOrder();
//
//			
//			if(order==null)
//			{
//				order= new ArrayList();
//				
//			}
//		
//			System.out.println("=========================8888888888888888888=============================================");
//			Orders newOrder= new Orders();
//		
//			newOrder.setOrderList(product);
//			newOrder.setUser(user);
//			
//			
//			 
//			order.add(newOrder);
//			orderRepository.save(newOrder);
//			
//			user.setOrder(order);
//			
//			userRepository.save(user);
//			
//			return order ;	
//		}
//		
		return null;
	}
	
	

}
