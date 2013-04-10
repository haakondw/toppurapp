package com.ntnu.eit.common.service;

import android.content.Context;
import android.util.SparseArray;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;

public class PatientServiceTestImpl implements PatientService{
	
	private String[] firstNames = {"Davit", "Lukas", "Yusif", "Ivan","Noah","Milan","Lucas","Nathan","Jakub","Victor","Harry"};
	private String[] lastNames = {"SMITH","JOHNSON","WILLIAMS","JONES","BROWN","DAVIS","MILLER","WILSON","MOORE","TAYLOR","ANDERSON"}; 

	//TEST DATA
	private SparseArray<Patient> pasientsSparse;
	private Patient[] pasients;
	
	@Override
	public Patient[] getPatients(Context context, Department[] departments) {
		if(pasients == null){			
			pasientsSparse = new SparseArray<Patient>();
			
			int end = (int) (Math.random()*50) + 1;
			pasients = new Patient[end];
			
			for(int i = 0; i < end; i++){				
				String firstname = firstNames[(int) (Math.random()*firstNames.length)];
				String lastname = lastNames[(int) (Math.random()*lastNames.length)];
				Patient pasient = new Patient(i, departments[(int) (Math.random()*departments.length)].getDepartmentID(), "", firstname, lastname);
				pasientsSparse.put(i, pasient);
				pasients[i] = pasient;
			}
		}

		return pasients;
	}

	@Override
	public Patient getPatientById(int id) {
		return pasientsSparse.get(id);
	}

	@Override
	public byte[] getPatientPicture(Context context, int id) {
//		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.patient1);		
		byte[] bs = new byte[100*100*3];
//		int[] temp = new int[bitmap.getWidth()*bitmap.getHeight()];
//		bitmap.getPixels(temp, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
		for (int i = 0; i < bs.length; i++){
			bs[i] = (byte) (i%100);
		}
		return bs;
	}
}
