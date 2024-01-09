package com.ty.mastercraft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.Review;
import com.ty.mastercraft.service.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	ReviewService reviewService;
	
	@PostMapping("saveReview/user_id/{user_id}/product_id/{product_id}")
	public ResponseEntity<ResponseStructure<Review>> saveReview(@RequestBody Review review,@PathVariable int user_id, @PathVariable int product_id){
		return reviewService.saveReview(review, user_id, product_id);
	}
}
