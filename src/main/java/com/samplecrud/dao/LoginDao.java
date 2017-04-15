package com.samplecrud.dao;

import com.samplecrud.model.Users;

public interface LoginDao {  
	 Users findByUserName(String username);  
}  
