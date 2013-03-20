package com.ntnu.eit.common.service;

import android.util.SparseArray;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.login.model.User;

public class PasientServiceTestImpl implements PasientService{
	
	private String[] firstNames = {"Davit", "Lukas", "Yusif", "Ivan","Noah","Milan","Lucas","Nathan","Jakub","Victor","Harry"};
	private String[] lastNames = {"SMITH","JOHNSON","WILLIAMS","JONES","BROWN","DAVIS","MILLER","WILSON","MOORE","TAYLOR","ANDERSON"}; 

	//TEST DATA
	private SparseArray<Pasient> pasientsSparse;
	private Pasient[] pasients;
	
	@Override
	public Pasient[] getPasients(User user, Department[] departments) {
		if(pasients == null){			
			pasientsSparse = new SparseArray<Pasient>();
			
			int end = (int) (Math.random()*50);
			pasients = new Pasient[end];
			
			for(int i = 0; i < end; i++){
				String firstname = firstNames[(int) (Math.random()*firstNames.length)];
				String lastname = lastNames[(int) (Math.random()*lastNames.length)];
				Pasient pasient = new Pasient(i, departments[(int) (Math.random()*departments.length)].getDepartmentID(), "", firstname, lastname);
				pasientsSparse.put(i, pasient);
				pasients[i] = pasient;
			}
		}

		return pasients;
	}

	@Override
	public Pasient getPasientById(int id) {
		return pasientsSparse.get(id);
	}
}
