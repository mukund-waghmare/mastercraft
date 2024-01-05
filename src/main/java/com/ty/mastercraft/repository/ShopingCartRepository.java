package com.ty.mastercraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.mastercraft.dto.ShopingCart;

public interface ShopingCartRepository extends JpaRepository<ShopingCart,Integer> {

}
