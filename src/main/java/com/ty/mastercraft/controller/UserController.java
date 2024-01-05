package com.ty.mastercraft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.mastercraft.dto.Orders;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UserController {
	
	@Autowired
	UserService userServiceObject;
	
	@PostMapping("/saveUser")
	@Operation(description = "save User",summary = "user saved successfully")
	@ApiResponses(value = {@ApiResponse(description = "user saved successfully",responseCode = "201"),@ApiResponse(description = "user not saved",responseCode = "404")})
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User passedUser)
	{
		return userServiceObject.saveUser(passedUser);
	}
	

	@GetMapping("/getUserById/{passedUserId}")
	@Operation(description = "find Use By Id ",summary = "Get User By Id")
	@ApiResponses(value = {@ApiResponse(description = "User Found Successfully",responseCode = "201"),@ApiResponse(description = "User Not Exist For Given Id",responseCode = "404")})
	
	public ResponseEntity<ResponseStructure<User>> getUserByUserId(@PathVariable int passedUserId)
	{
		return userServiceObject.getUserByUserId(passedUserId);
	}
	
	@PutMapping("/updateUserById/{passedUserId}")
	@Operation(description = "update User ",summary = "User Updated")
	@ApiResponses(value = {@ApiResponse(description = "User Updated Successfully",responseCode = "201"),@ApiResponse(description = "User Not Exist For Given Id",responseCode = "404")})
	
	public ResponseEntity<ResponseStructure<User>> updateUserByUserId(@RequestBody User passedUser,@PathVariable int passedUserId)
	{
		return userServiceObject.updateUserByUserId(passedUser, passedUserId);
	}
	
	
	@DeleteMapping("/deleteUserById/{passedUserId}")
	@Operation(description = "delete User ",summary = "User Deleted")
	@ApiResponses(value = {@ApiResponse(description = "User Deleted Successfully",responseCode = "201"),@ApiResponse(description = "User Not Exist For Given Id",responseCode = "404")})
	
	public ResponseEntity<ResponseStructure<User>> deleteUserByUserId(@PathVariable int passedUserId)
	{
		return userServiceObject.deleteUserByUserId(passedUserId);
	}
	
	@PostMapping("/userLogin")
	@Operation(description = " User Login ",summary = "User Login")
	@ApiResponses(value = {@ApiResponse(description = "User LoggedIn Successfully",responseCode = "201"),@ApiResponse(description = "Login Failed",responseCode = "404")})
	
	public ResponseEntity<ResponseStructure<User>> userLogin(@RequestParam String passedEmail,@RequestParam String passedPassword)
	{
	
		return userServiceObject.userLogin(passedEmail, passedPassword);
	}
	
	@GetMapping("/getOrderByUserId/{passedUserId}")
	@Operation(description = "Get Orders",summary = "Ordered Details Fetched")
	@ApiResponses(value = {@ApiResponse(description = "Ordered Details Fetched Successfully",responseCode = "201"),@ApiResponse(description = "User Not Exist For Given Id",responseCode = "404")})
	public ResponseEntity<ResponseStructure<List<Orders>>> getOrdersByUserId(@PathVariable int passedUserId)
	{

		return userServiceObject.getOrdersByUserId(passedUserId);
		
	}
	
	
}
