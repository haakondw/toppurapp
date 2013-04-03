package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;
import com.ntnu.eit.common.model.User;

public interface PatientService{
	
	public Patient[] getPatients(Department[] departments);
	
	public Patient getPatientById(int id);
	
}