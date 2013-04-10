package com.ntnu.eit.common.service;

import java.util.ArrayList;

import android.content.Context;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;

public interface PatientService{
	
	public Patient[] getPatients(Context context, Department[] departments);
	
	public Patient getPatientById(int id);
	
	public byte[] getPatientPicture(Context context, int id);

	public void setPatientList(ArrayList<Patient> patients);
	
	public void setPatientPicture(int patientId, byte[] picture);
	
	
}