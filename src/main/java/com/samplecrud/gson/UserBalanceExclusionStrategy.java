package com.samplecrud.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.samplecrud.model.UsersBalance;

public class UserBalanceExclusionStrategy implements ExclusionStrategy {
	
	public boolean shouldSkipClass(Class<?> arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		// TODO Auto-generated method stub
		return (f.getDeclaringClass() == UsersBalance.class && f.getName().equals("user"));
	}
	
}
