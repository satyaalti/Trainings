package com.samplecrud.dao;

import java.util.List;

import com.samplecrud.model.Users;

public interface UserDAO {

	List<Users> listUsers(boolean enalbed);
	public void addUser(Users u);
	public void updateUser(Users u);
	public Users getUserById(int id);
	public void removeUser(int id);
	Users findByUserName(String username);
	
}
