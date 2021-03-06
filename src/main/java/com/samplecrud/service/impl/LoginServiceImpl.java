package com.samplecrud.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.samplecrud.dao.UserDAO;
import com.samplecrud.model.UserRole;
import com.samplecrud.model.Users;
  
public class LoginServiceImpl implements UserDetailsService {  
  
	 private UserDAO userDAO;

	 public void setUserDAO(UserDAO userDAO) {
	 	this.userDAO = userDAO;
	 }
	 
	 @Override  
	 public UserDetails loadUserByUsername(String username)  throws UsernameNotFoundException {  
	      
		  Users user = this.userDAO.findByUserName(username);  
		  List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());  
		  try {
			  return buildUserForAuthentication(user, authorities);
		  } 
		  catch (Exception e) {
				// TODO Auto-generated catch block
			  e.printStackTrace();
		  } 
		 return null; 
	 }  
	  
	 private User buildUserForAuthentication(Users user, List<GrantedAuthority> authorities) {
		 return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);  
	 }  
	  
	 private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {  
	  
		  Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();  
		  // Build user's authorities  
		  for (UserRole userRole : userRoles) {  
			  setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));  
		  }  
		  List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);  
		  return Result;  
	 }  
  
}