package com.ty.mastercraft.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.mastercraft.dto.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

}
