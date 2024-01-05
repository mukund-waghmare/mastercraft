package com.ty.mastercraft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.mastercraft.dao.ProductDao;
import com.ty.mastercraft.dao.UserDao;
import com.ty.mastercraft.dto.Product;
import com.ty.mastercraft.dto.ResponseStructure;
import com.ty.mastercraft.dto.User;
import com.ty.mastercraft.dto.UserRole;
import com.ty.mastercraft.exception.OnlyMerchantCanAddProductException;
import com.ty.mastercraft.exception.UserIdNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private UserDao userDao;
	
	
//	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product,int uid){
//		User user=userDao.getUserById(uid);
//		if(user!=null) {
//			if(user.getRole()==UserRole.Merchant) {
//				ResponseStructure<Product> structure=new ResponseStructure<>();
//			}
//			else {
//				throw new OnlyMerchantCanAddProductException("user is not merchant");
//			}
//		}
//		else {
//			throw new UserIdNotFoundException("invalid id: " + uid);
//		}
//		
//	}
}
