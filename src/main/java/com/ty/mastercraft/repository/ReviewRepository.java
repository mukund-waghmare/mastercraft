package com.ty.mastercraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.mastercraft.dto.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
