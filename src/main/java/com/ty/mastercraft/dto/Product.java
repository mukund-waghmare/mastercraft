package com.ty.mastercraft.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;
	
	private String productName;
	
	private double productPrice;
	
	@ManyToMany
	@JoinTable(joinColumns = @JoinColumn(name="product_id"),inverseJoinColumns = @JoinColumn(name="user_id"))
	@JsonIgnore
	private List<User> userList;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Review> reviewList;
	
	
	@ManyToOne
	@JsonIgnore
	private Offer offer;
	
	//--------------------------getters and Setters-----------------

	
    
	
	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getProductPrice() {
		return productPrice;
	}


	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}


	public List<User> getUserList() {
		return userList;
	}


	public void setUserList(List<User> userList) {
		this.userList = userList;
	}


	public List<Review> getReviewList() {
		return reviewList;
	}


	public void setReviewList(List<Review> reviewList) {
		this.reviewList = reviewList;
	}


	public Offer getOffer() {
		return offer;
	}


	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	
	
	
}
