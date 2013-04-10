package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.MedicineForm;

public class MedicineFormServiceTestImpl implements MedicineFormService{

	MedicineForm[] medicineForms = {
			new MedicineForm("Pille"),
			new MedicineForm("Flytende"),
			new MedicineForm("Injeksjon"),
			new MedicineForm("Krem"),
			new MedicineForm("Stikkpille"),
			new MedicineForm("Salve")
	};
	
	@Override
	public MedicineForm getMedicineForm(int id) {
		return medicineForms[id];
	}
}