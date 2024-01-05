package com.ty.mastercraft.repository;

import org.springframework.context.support.BeanDefinitionDsl.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.dto.UserRole;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	
	@Query("select u from User u where u.userEmail=?1 and u.userPassword=?2")
	User findUserByuserEmailAnduserPassword(String userEmail,String userPassword);
	
	
	@Query("select u from User u where u.role=?1")
	User findUserByUserRole(UserRole role);

}
