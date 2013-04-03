package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.common.model.User;

public interface PasientService{
	
	public Pasient[] getPasients(User user, Department[] departments);
	
	public Pasient getPasientById(int id);
	
}