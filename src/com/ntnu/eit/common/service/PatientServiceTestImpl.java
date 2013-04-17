package com.ntnu.eit.common.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.SparseArray;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;
import com.ntnu.eit.socket.PatientClient;
import com.ntnu.eit.socket.PatientSocketObject;
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
	private List<Patient> patients;
	

	@Override
	public List<Patient> getPatients(Context context, List<Department> departments) {
		if(patients == null){
			patients = new ArrayList<Patient>();
			if(ServiceFactory.getInstance().getAuthenticationService().isDebug()){		
				
				int end = 10;
				
				for(int i = 0; i < end; i++){				
					String firstname = firstNames[(int) (Math.random()*firstNames.length)];
					String lastname = lastNames[(int) (Math.random()*lastNames.length)];
					Patient pasient = new Patient(i, departments.get(i/6).getDepartmentID(), "", firstname, lastname);
					patients.add(pasient);
				}
			}
		}

		return patients;
	}

	@Override
	public Patient getPatientById(int id) {
		for (Patient patient: patients) {
			if(patient.getPatientID() == id){
				return patient;
			}
		}
		return null;
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
	
	@Override
	public void setPatientList(ArrayList<Patient> patients){
		if(patients == null){
			this.patients.clear();
			return;
		}
			
		
		ArrayList<Patient> temp = new ArrayList<Patient>();
		boolean updated;
		
		for(Patient p : patients){
			updated = false;
			for(Patient patient: this.patients){
				if(p.getPatientID() == patient.getPatientID()){
					patient.setFirstname(p.getFirstname());
					patient.setLastname(p.getLastname());
					patient.setPictureOffset(p.getPictureOffset());
					patient.setSocialSecurityNumber(p.getSocialSecurityNumber());
					updated = true;
					break;
				}
			}
			if(!updated){
				temp.add(p);
			}
		}
		
		this.patients.clear();
		this.patients.addAll(patients);
	}
	
	@Override
	public void setPatientPicture(int patientId, byte[] picture){
		for(Patient p : patients){
			if(p.getPatientID() == patientId){
				p.setPicture(picture);
			}
		}
	}
	
	@Override
	public void updatePatientList(ArrayList<Integer> departmentIds, ArrayList<Object> adapters, Context context){
		PatientSocketObject pso = new PatientSocketObject(departmentIds);
		PatientClient pc = new PatientClient(pso, adapters, context);
		pc.execute();
		//TODO maybe start update of pictures here? happy threading!
	}
	
	@Override
	public void updatePatientPicture(Patient patient, ArrayList<Object> adapters, Context context) throws NoSuchAlgorithmException{
		PictureSocketObject pso = new PictureSocketObject(patient.getPatientID());
		if(patient.getPicture() != null && patient.getPicture().length > 0){
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] checksum = md.digest(patient.getPicture());
				pso.setLastChecksum(checksum);
		}
		PictureClient pc = new PictureClient(pso, adapters, context);
		pc.execute();
	}
}
