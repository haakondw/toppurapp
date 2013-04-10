package com.ntnu.eit.common.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import android.content.Context;
import android.util.SparseArray;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;
import com.ntnu.eit.socket.PictureClient;
import com.ntnu.eit.socket.PictureSocketObject;

public class PatientServiceTestImpl implements PatientService{
	
	private String[] firstNames = {	"Jonas", "Matias", "Aleksander", "Andreas","Elias",
									"Christian","Sebastian","Marcus","Sander","Tobias",
									"Tea","Emma","Sarah","Julie","Ida",
									"Hanna","Nora","Ingrid","Emilie","Amalie"};
	private String[] lastNames = {	"Aasen","Amdahl","Asheim","Augdahl","Jagland","Jeppsson",
									"Kittelsen","Monrad","Otterstad","Solberg","Strandberg", "Tufte"}; 

	//TEST DATA
	private SparseArray<Patient> pasientsSparse;
	private Patient[] patients;
	

	@Override
	public Patient[] getPatients(Context context, Department[] departments) {
		if(patients == null){			
			pasientsSparse = new SparseArray<Patient>();
			
			int end = (int) (Math.random()*50) + 1;
			patients = new Patient[end];
			
			for(int i = 0; i < end; i++){				
				String firstname = firstNames[(int) (Math.random()*firstNames.length)];
				String lastname = lastNames[(int) (Math.random()*lastNames.length)];
				Patient pasient = new Patient(i, departments[(int) (Math.random()*departments.length)].getDepartmentID(), "", firstname, lastname);
				pasientsSparse.put(i, pasient);
				patients[i] = pasient;
			}
		}

		return patients;
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
	
	private void fetchPatientPicture(Patient patient, ArrayList<Object> adapters) throws NoSuchAlgorithmException{
		PictureSocketObject pso = new PictureSocketObject(patient.getPatientID());
		if(patient.getPicture() != null && patient.getPicture().length > 0){
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] checksum = md.digest(patient.getPicture());
				pso.setLastChecksum(checksum);
		}
		PictureClient pc = new PictureClient(pso, adapters);
		//TODO execute pictureclient
		
	}
	
	@Override
	public void setPatientList(ArrayList<Patient> patients){
		ArrayList<Patient> temp = new ArrayList<Patient>();
		boolean updated;
		
		for(Patient p : patients){
			updated = false;
			for(int i=0; i<this.patients.length; i++){
				if(p.getPatientID() == this.patients[i].getPatientID()){
					this.patients[i].setFirstname(p.getFirstname());
					this.patients[i].setLastname(p.getLastname());
					this.patients[i].setPictureOffset(p.getPictureOffset());
					this.patients[i].setSocialSecurityNumber(p.getSocialSecurityNumber());
					updated = true;
					break;
				}
			}
			if(!updated){
				temp.add(p);
			}
		}
		
		Patient[] newPatientList = new Patient[this.patients.length+temp.size()];

		System.arraycopy(this.patients, 0, newPatientList, 0, this.patients.length);
		Patient[] patientsToAdd = new Patient[temp.size()];
		temp.toArray(patientsToAdd);
		System.arraycopy(patientsToAdd, 0, newPatientList, this.patients.length, patientsToAdd.length);
		
		this.patients = new Patient[newPatientList.length];
		System.arraycopy(newPatientList, 0, patients, 0, newPatientList.length);
	}
	
	@Override
	public void setPatientPicture(int patientId, byte[] picture){
		for(Patient p : patients){
			if(p.getPatientID() == patientId){
				p.setPicture(picture);
			}
		}
	}
}
