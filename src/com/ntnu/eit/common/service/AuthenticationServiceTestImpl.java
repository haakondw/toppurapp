package com.ntnu.eit.common.service;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import com.ntnu.eit.common.model.User;

public class AuthenticationServiceTestImpl implements AuthenticationService{

	private String host;
	private User user;
	
	@Override
	public User login(Context context, String username,	String password) {
		//Init
		String host = PreferenceManager.getDefaultSharedPreferences(context).getString("login_settings_server_config", "");
		
		//Log
		Log.d("EiT", "Connecting to " + host);
		
		if(Math.random() < 0.3){
			return null;
		}else{
			user = new User(1, username, password, "Per", "Olav");
			return user;
		}
	}

	@Override
	public void logout(String username) {
		user = null;
	}

	@Override
	public User getLoggedInUser() {
		return user;
	}

	@Override
	public boolean isLoggedIn() {
		return user != null;
	}

	@Override
	public String getHost() {
		return host;
	}

	@Override
	public void setHost(String host) {
		this.host = host;
	}

	@Override
	public boolean isDebug() {
		return host.equalsIgnoreCase("debug") || host.equalsIgnoreCase("test");
	}
}