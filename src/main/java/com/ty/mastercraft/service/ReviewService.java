package com.ty.mastercraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.mastercraft.dao.ReviewDao;
import com.ty.mastercraft.dao.UserDao;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.Review;
import com.ty.mastercraft.dto.User;

@Service
public class ReviewService {

	@Autowired
	ReviewDao reviewDao;
	
	@Autowired
	UserDao userDao;
	
	
}
