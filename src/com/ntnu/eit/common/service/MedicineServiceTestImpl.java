package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.Medicine;

public class MedicineServiceTestImpl implements MedicineService{

	Medicine[] medicines = {
			new Medicine(1, "Abacavir"),
			new Medicine(2, "Accuretic"),
			new Medicine(3, "Actilyse"),
			new Medicine(4, "Adizem-XL"),
			new Medicine(5, "Alu-cap"),
			new Medicine(6, "Atripla"),
			new Medicine(7, "Ecopace"),
			new Medicine(8, "Efavirenz"),
			new Medicine(9, "Ellaone"),
			new Medicine(10, "Endekay fluotabs"),
			new Medicine(11, "Oilatum cream"),
			new Medicine(12, "Oncovin"),
			new Medicine(13, "Opilon"),
			new Medicine(14, "Ovex"),
			new Medicine(15, "Keflex"),
			new Medicine(16, "Ketoprofen"),
			new Medicine(17, "Kytril"),
			new Medicine(18, "Klean-prep")
	};
	
	@Override
	public Medicine getMedicine(int id) {
		return medicines[id];
	}
}