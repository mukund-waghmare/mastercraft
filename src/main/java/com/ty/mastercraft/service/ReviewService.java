package com.ty.mastercraft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.mastercraft.dao.ReviewDao;
import com.ty.mastercraft.dao.UserDao;
import com.ty.mastercraft.dto.Orders;
import com.ty.mastercraft.dto.Product;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.Review;
import com.ty.mastercraft.dto.ShopingCart;
import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.dto.UserRole;
import com.ty.mastercraft.exception.UserIdNotFoundException;

@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Review>> saveReview(Review review,int user_id,int product_id){
		User user=userDao.getUserById(user_id);
		if(user!=null&user.getRole()==UserRole.Customer) {
			List<Orders> orders=user.getOrderList();
			if(orders.size()>0) {
				for (Orders orders2 : orders) {
					ShopingCart cart=orders2.getOrderList();
					if(cart!=null) {
						cart.getProductList();
					}
				}
			}
		}
		else {
			throw new UserIdNotFoundException("invalid user id: " + user_id);
		}
	}
}
