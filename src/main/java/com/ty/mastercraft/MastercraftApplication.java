package com.ty.mastercraft;

import java.util.NoSuchElementException;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.BeanDefinitionDsl.Role;

import com.ty.mastercraft.dao.UserDao;
import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.dto.UserRole;
import com.ty.mastercraft.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ty.mastercraft"})
public class MastercraftApplication {
	
	
//	@Autowired
//	User user;
	
	@Autowired
	UserDao userDaoObject;
	
	
	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(MastercraftApplication.class, args);
	}

	@PostConstruct
	private void init() {
	  
		User user=null;
		user =userRepository.findUserByUserRole(UserRole.Admin);
		
		
		if(user==null)
		{
			user= new User();
			user.setUserName("Shiv");
			user.setUserEmail("shiv@gmail.com");
			user.setUserPassword("shiv@123");
			user.setRole(UserRole.Admin);
			user.setUserAddress("Mumbai");
			userDaoObject.saveAdmin(user);
			System.out.println("Admin Created Successfully");
			
		}
		else
		{
			System.out.println("Admin Already Exists");
		}
		
		
		
	
	}
	
}
