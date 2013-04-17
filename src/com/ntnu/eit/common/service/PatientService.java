package com.ntnu.eit.common.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;

public interface PatientService{
	
	public List<Patient> getPatients(Context context, List<Department> departments);
	
	public Patient getPatientById(int id);
	
	public byte[] getPatientPicture(Context context, int id);

	public void setPatientList(ArrayList<Patient> patients);
	
	public void setPatientPicture(int patientId, byte[] picture);
	
	public void updatePatientList(ArrayList<Integer> departmentIds, ArrayList<Object> adapters, Context context);

	public void updatePatientPicture(Patient patient, ArrayList<Object> adapters, Context context) throws NoSuchAlgorithmException;
	
}