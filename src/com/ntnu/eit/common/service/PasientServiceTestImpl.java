package com.ntnu.eit.common.service;

import java.util.ArrayList;
import java.util.List;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.login.model.User;

public class PasientServiceTestImpl implements PasientService{

	@Override
	public Pasient[] getPasients(User user, Department[] departments) {
		List<Pasient> pasients = new ArrayList<Pasient>();

		int end = (int) (Math.random()*50);

		for(int i = 0; i < end; i++){
			pasients.add(new Pasient());
		}

		return pasients.toArray(new Pasient[pasients.size()]);
	}

	@Override
	public Pasient getPasientById(int id) {
		return new Pasient();
	}
}
