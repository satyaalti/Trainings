package com.samplecrud.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.samplecrud.model.Bank;
import com.samplecrud.model.UserRole;

public class UserExclusionStrategy implements ExclusionStrategy  {
	
	@Override
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		// TODO Auto-generated method stub
		//System.out.println("f.getName():"+ f.getDeclaringClass() +"---"+ f.getName());
		return (f.getDeclaringClass() == Bank.class && f.getName().equals("user") || f.getDeclaringClass() == UserRole.class && f.getName().equals("user"));
	}

}
