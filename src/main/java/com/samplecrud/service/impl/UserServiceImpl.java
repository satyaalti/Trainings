package com.samplecrud.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.samplecrud.dao.UserDAO;
import com.samplecrud.model.Users;
import com.samplecrud.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	@Override
	@Transactional
	public void addUser(Users u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		this.userDAO.addUser(u);
	}

	@Override
	@Transactional
	public void updateUser(Users u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		this.userDAO.updateUser(u);
	}

	@Override
	@Transactional
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
	@Transactional
	public Users findByUserName(String username) {
		// TODO Auto-generated method stub
		return this.userDAO.findByUserName(username);
	}
	
}
	