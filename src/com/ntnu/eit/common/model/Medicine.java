package com.ntnu.eit.common.model;

import java.io.Serializable;

public class Medicine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1557058877058892787L;
	private int medicineID;
	private String name;

	public Medicine(){}

	public Medicine(int medicineID, String name){
		this.medicineID = medicineID;
		this.name = name;
	}

	public int getMedicineID(){
		return medicineID;
	}

	public void setMedicineID(int medicineID){
		this.medicineID = medicineID;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public String toString(){
		return "Medicine [medicineID=" + medicineID + ", name=" + name + "]";
	}
}