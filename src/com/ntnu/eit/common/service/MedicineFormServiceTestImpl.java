package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.MedicineForm;

public class MedicineFormServiceTestImpl implements MedicineFormService{

	MedicineForm[] medicineForms = {
			new MedicineForm("Pill"),
			new MedicineForm("Liquid"),
			new MedicineForm("Spray"),
			new MedicineForm("Drop"),
			new MedicineForm("Injection"),
			new MedicineForm("Lotion"),
			new MedicineForm("Suppository"),
			new MedicineForm("Ointment")
	};
	
	@Override
	public MedicineForm getMedicineForm(int id) {
		return medicineForms[(int) (Math.random()*medicineForms.length)];
	}
}