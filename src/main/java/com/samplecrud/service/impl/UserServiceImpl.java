package com.samplecrud.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.samplecrud.dao.UserDAO;
import com.samplecrud.model.Users;
import com.samplecrud.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	@Transactional
	public void addUser(Users u) {
		this.userDAO.addUser(u);
	}

	@Override
	@Transactional
	public void updateUser(Users u) {
		this.userDAO.updateUser(u);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Users> listUsers() {
		return this.userDAO.listUsers();
	}
	
	@Override
	@Transactional
	public Users getUserById(int id) {
		return this.userDAO.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}

	@Override
	public Users findByUserName(String username) {
		// TODO Auto-generated method stub
		return this.userDAO.findByUserName(username);
	}
	
}
	