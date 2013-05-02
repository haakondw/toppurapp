package com.ntnu.eit.common.service;

import android.content.Context;

import com.ntnu.eit.common.model.User;

public interface AuthenticationService {

	public User login(Context context, String username, String password);
	
	public void logout();
	
	public User getLoggedInUser();
	
	public boolean isLoggedIn();

	public void setUser(User u);
	
	public String getHost();
	
	public void setHost(String host);
	
	public boolean isDebug();
}