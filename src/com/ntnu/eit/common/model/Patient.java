package com.ntnu.eit.common.model;

import java.io.Serializable;

public class Patient implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1691023789719273095L;
	private int patientID;
	private int departmentID;
	private String socialSecurityNumber;
	private String firstname;
	private String lastname;
	private byte[] picture;
	private int pictureOffset;

	public Patient(){

	}

	public Patient(int patientID, int departmentID, String socialSecurityNumber, String firstname, String lastname) {
		this.patientID = patientID;
		this.departmentID = departmentID;
		this.socialSecurityNumber = socialSecurityNumber;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Patient(int patientID, int departmentID, String socialSecurityNumber, String firstname, String lastname, byte[] picture, int pictureOffset) {
		this.patientID = patientID;
		this.departmentID = departmentID;
		this.socialSecurityNumber = socialSecurityNumber;
		this.firstname = firstname;
		this.lastname = lastname;
		this.picture = picture;
		this.pictureOffset = pictureOffset;
	}

	public int getPatientID() {
		return patientID;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setPatientID(int patientID) {
		this.patientID = patientID;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	public int getPictureOffset(){
		return pictureOffset;
	}
	
	public void setPictureOffset(int offset){
		this.pictureOffset = offset;
	}

	@Override
	public String toString() {
		return "Patient [patientID=" + patientID + ", socialSecurityNumber="
				+ socialSecurityNumber
				+ ", firstname=" + firstname
				+ ", lastname=" + lastname + "]";
	}
}