package com.ty.mastercraft.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.mastercraft.dto.Review;
import com.ty.mastercraft.repository.ReviewRepository;

@Repository
public class ReviewDao {

	@Autowired
	ReviewRepository repository;
	
	public Review saveReview(Review review) {
		return  repository.save(review);
	}
}
