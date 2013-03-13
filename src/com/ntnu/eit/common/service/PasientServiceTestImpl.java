package com.ntnu.eit.common.service;

import java.util.ArrayList;
import java.util.List;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.login.model.User;

public class PasientServiceTestImpl implements PasientService{

	@Override
	public List<Pasient> getPasients(User user, Department[] departments) {
		List<Pasient> pasients = new ArrayList<Pasient>();

		int end = (int) (Math.random()*50);

		for(int i = 0; i < end; i++){
			pasients.add(new Pasient(i, "First name " + i, "Lastname", "##########", null, new Department("Department")));
		}

		return pasients;
	}

	@Override
	public Pasient getPasientById(int id) {
		return new Pasient(id, "First name " + id, "Lastname", "##########", null, new Department("Department"));
	}
}
