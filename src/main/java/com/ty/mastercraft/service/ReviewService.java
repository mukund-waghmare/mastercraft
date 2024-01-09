package com.ty.mastercraft.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.mastercraft.dao.ProductDao;
import com.ty.mastercraft.dao.ReviewDao;
import com.ty.mastercraft.dao.UserDao;
import com.ty.mastercraft.dto.Orders;
import com.ty.mastercraft.dto.Product;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.Review;
import com.ty.mastercraft.dto.ShopingCart;
import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.dto.UserRole;
import com.ty.mastercraft.exception.NoOrderExistException;
import com.ty.mastercraft.exception.NoProductOrderedException;
import com.ty.mastercraft.exception.ProductIdNotFoundException;
import com.ty.mastercraft.exception.UserIdNotFoundException;

@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ProductDao productDao;

	public ResponseEntity<ResponseStructure<Review>> saveReview(Review review, int user_id, int product_id) {
		
		User user = userDao.getUserById(user_id);
		if (user != null & user.getRole() == UserRole.Customer) {
			Product product=productDao.getProductById(product_id);
			if(product!=null) {
				review.setProduct(product);
				List<User> users=review.getReviewer();
				if(users==null) {
					users=new ArrayList<>();
				}
				users.add(user);
				review.setReviewer(users);
				Review review1=reviewDao.saveReview(review);
			List<Review> reviews2=user.getReviewList();
			if (reviews2 == null) {
				reviews2 = new ArrayList<>();
			}
			reviews2.add(review1);
			user.setReviewList(reviews2);
			userDao.saveUser(user);
			List<Review> reviews1 = product.getReviewList();
			if (reviews1 == null) {
				reviews1 = new ArrayList<>();
			}
			reviews1.add(review1);
			product.setReviewList(reviews1);
			productDao.addProduct(product);

			ResponseStructure<Review> structure = new ResponseStructure<>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("review added successfully");
			structure.setData(review1);

			return new ResponseEntity<ResponseStructure<Review>>(structure, HttpStatus.CREATED);
			}
			else
				throw new ProductIdNotFoundException("invalid product id: " + product_id);
	
				} 
		
			throw new UserIdNotFoundException("invalid user id: " + user_id);
		
	}
}
