package com.samplecrud.service;

import java.util.List;

import com.samplecrud.model.Users;

public interface UserService {

	List<Users> listUsers(boolean enalbed);
	public void addUser(Users u);
	public void updateUser(Users u);
	public Users getUserById(int id);
	public void removeUser(int id);
	public Users findByUserName(String username);
	public void changeUserStatus(Users u);

}
