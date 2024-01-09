package com.ty.mastercraft.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ty.mastercraft.dao.ProductDao;
import com.ty.mastercraft.dao.UserDao;
import com.ty.mastercraft.dto.Orders;
import com.ty.mastercraft.dto.Product;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.ShopingCart;
import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.dto.UserRole;
import com.ty.mastercraft.exception.EmptyCartException;
import com.ty.mastercraft.exception.LoginFailedException;
import com.ty.mastercraft.exception.NoOrderExistException;

import com.ty.mastercraft.exception.ProductAlreadyExistInCart;


import com.ty.mastercraft.exception.ProductIdNotFoundException;
import com.ty.mastercraft.exception.UserIdNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserDao userDaoObject;
	
	@Autowired
	ProductDao productDaoObject;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User passedUser)
	{
		User user=null;
		
		if(!passedUser.getRole().equals(UserRole.Admin))
		{

			 user=userDaoObject.saveUser(passedUser);
		}
		
		if(user!=null)
		{
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(user);
			
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.ACCEPTED);
			
		}
		else
		{
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Admin Already Exists");
			responseStructure.setData(user);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.BAD_REQUEST);
			
		}
		
	}
	
	
	public ResponseEntity<ResponseStructure<User>> getUserByUserId(int passedUserId)
	{
		
		User user=userDaoObject.getUserById(passedUserId);
		
		if(user!=null)
		{
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(user);
			
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.ACCEPTED);
			
		}
		else
		{
//			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
//			
//			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			responseStructure.setMessage("User DoesNot Exist For Id : "+passedUserId);
//			responseStructure.setData(user);
//			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.BAD_REQUEST);
			
			throw new UserIdNotFoundException();
			
		}
		
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUserByUserId(User passedUser,int passedUserId)
	{
		
		User user=userDaoObject.updateUserById(passedUser,passedUserId);
		
		if(user!=null)
		{
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("User Updated Success");
			responseStructure.setData(user);
			
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.ACCEPTED);
			
		}
		else
		{
//			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
//			
//			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			responseStructure.setMessage("User DoesNot Exist For Id : "+passedUserId);
//			responseStructure.setData(user);
//			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.BAD_REQUEST);
			
			throw new UserIdNotFoundException();
			
			
		}
		
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUserByUserId(int passedUserId)
	{
		
		boolean user=userDaoObject.deleteUserById(passedUserId);
		
		if(user)
		{
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("User Deleted Success");
			
			
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.ACCEPTED);
			
		}
		else
		{
//			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
//			
//			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			responseStructure.setMessage("User DoesNot Exist For Id : "+passedUserId);
//			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.BAD_REQUEST);	
			
			throw new UserIdNotFoundException();
			
		}
		
	}
	
	
	

	public ResponseEntity<ResponseStructure<User>> userLogin(String passedEmail,String passedPassword)
	{
		
		User user=userDaoObject.loginUser(passedEmail, passedPassword);
		
		if(user!=null)
		{
			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Login Successfull");
			responseStructure.setData(user);
			
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.ACCEPTED);
			
		}
		else
		{
//			ResponseStructure<User> responseStructure = new ResponseStructure<User>();
//			
//			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			responseStructure.setMessage("Invalid Credentials : "+passedEmail+" "+passedPassword);
//			responseStructure.setData(user);
//			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.BAD_REQUEST);
			
	       throw new LoginFailedException();
			
			
		}
		
	}

	
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByUserId(int passedUserId)
	{
		
		List<Orders> orders=userDaoObject.getOrdersByUserId(passedUserId);
		
		if(!orders.isEmpty())
		{
			ResponseStructure<List<Orders>> responseStructure = new ResponseStructure<List<Orders>>();
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(orders);
			
			return new ResponseEntity<ResponseStructure<List<Orders>>>(responseStructure,HttpStatus.ACCEPTED);
			
		}
		else
		{
//			ResponseStructure<List<Orders>> responseStructure = new ResponseStructure<List<Orders>>();
//			
//			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
//			responseStructure.setMessage("No Orders Exist For User Id : "+passedUserId);
//			responseStructure.setData(orders);
//			return new ResponseEntity<ResponseStructure<List<Orders>>>(responseStructure,HttpStatus.BAD_REQUEST);
//			
			
			throw new NoOrderExistException();
		}
		
	}

	
	public ResponseEntity<ResponseStructure<List<Product>>> getcartByUserId(int passedUserId)
	{
		List<Product> shopingCart=userDaoObject.getCartByUserId(passedUserId);
		
		if(shopingCart!=null)
		{
			ResponseStructure<List<Product>> responseStructure = new ResponseStructure<List<Product>>();
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(shopingCart);
			return new ResponseEntity<ResponseStructure<List<Product>>>(responseStructure,HttpStatus.ACCEPTED);
			
		}
		else
		{	
			
			throw new EmptyCartException();
		}
		
	}
	
	public ResponseEntity<ResponseStructure<ShopingCart>> addProductToCart(int userId,int ProductId)
	{
		User user =userDaoObject.getUserById(userId);

		System.out.println("===================="+user.getUserName()+"=====================");

			if(user!=null)
		{
		Product product=productDaoObject.getProductById(ProductId);
		  if(product!=null)
		  {
			  product.setUser(user);
			  
			  ShopingCart shopingCart=user.getShopingCart();
			  if(shopingCart==null)
			  {
				  shopingCart= new ShopingCart();
			  }

			  List<Product> productList=shopingCart.getProductList();
			  if(productList==null)
			  {
				  productList= new ArrayList();
			  }

			  
			  if(!productList.contains(product))
			  {
			  productList.add(product);
			  }
			  else
			  {
				  throw new ProductAlreadyExistInCart();
			  }
			  
			  shopingCart.setProductList(productList);
			  
			  ShopingCart cart= userDaoObject.addProductToCart(user, shopingCart);
			  
			  user.setShopingCart(shopingCart);
			  
			  userDaoObject.saveUser(user);
			  
			  ResponseStructure<ShopingCart> responseStructure = new ResponseStructure<ShopingCart>();
				
			  responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			  responseStructure.setMessage("Success");
			  responseStructure.setData(cart);
			  return new ResponseEntity<ResponseStructure<ShopingCart>>(responseStructure,HttpStatus.ACCEPTED);

			 		  }
		  else
		  {
			  throw new ProductIdNotFoundException();
		  }
		
		}
		else
		{
			throw new UserIdNotFoundException();
		}
		
		
	}
	

	public ResponseEntity<ResponseStructure<List<Orders>>> orderAllProductOfCart(int userId)
	{
		List<Orders> orderList=userDaoObject.orderAllProductOfCart(userId);
		
		if(orderList!=null)
		{
			ResponseStructure<List<Orders>> responseStructure = new ResponseStructure<List<Orders>>();
			
			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
			responseStructure.setMessage("Success");
			responseStructure.setData(orderList);
			return new ResponseEntity<ResponseStructure<List<Orders>>>(responseStructure,HttpStatus.ACCEPTED);
			
		}
		else
		{
			throw new UserIdNotFoundException();
		}
	}

//	public ResponseEntity<ResponseStructure<List<Orders>>> orderAllProductOfCart(int userId)
//	{
//		List<Orders> orderList=userDaoObject.orderAllProductOfCart(userId);
//		
//		if(orderList!=null)
//		{
//			ResponseStructure<List<Orders>> responseStructure = new ResponseStructure<List<Orders>>();
//			
//			responseStructure.setStatusCode(HttpStatus.ACCEPTED.value());
//			responseStructure.setMessage("Success");
//			responseStructure.setData(orderList);
//			return new ResponseEntity<ResponseStructure<List<Orders>>>(responseStructure,HttpStatus.ACCEPTED);
//			
//		}
//		else
//		{
//			throw new UserIdNotFoundException();
//		}
//	}

	
	
	

}
