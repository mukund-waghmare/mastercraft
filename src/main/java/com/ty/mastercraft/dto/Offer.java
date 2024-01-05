package com.ty.mastercraft.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Offer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int offerId;
	
	
	private LocalDateTime offerStartedDate;
	
	private LocalDateTime offerExpiryDate;
	
	@OneToMany(mappedBy = "offer")
	@JsonIgnore
	List<Product> offerProductList;
	
		
	//-----------------getters and Setters-------------------

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public LocalDateTime getOfferStartedDate() {
		return offerStartedDate;
	}

	public void setOfferStartedDate(LocalDateTime offerStartedDate) {
		this.offerStartedDate = offerStartedDate;
	}

	public LocalDateTime getOfferExpiryDate() {
		return offerExpiryDate;
	}

	public void setOfferExpiryDate(LocalDateTime offerExpiryDate) {
		this.offerExpiryDate = offerExpiryDate;
	}

	public List<Product> getOfferProductList() {
		return offerProductList;
	}

	public void setOfferProductList(List<Product> offerProductList) {
		this.offerProductList = offerProductList;
	}

	
	
	

	
}
