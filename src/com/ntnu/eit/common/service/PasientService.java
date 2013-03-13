package com.ntnu.eit.common.service;

import java.util.List;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.login.model.User;

public interface PasientService{
	
	public List<Pasient> getPasients(User user, Department[] departments);
	
	public Pasient getPasientById(int id);
	
}