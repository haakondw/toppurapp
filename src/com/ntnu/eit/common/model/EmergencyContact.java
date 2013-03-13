package com.ntnu.eit.common.model;

import java.io.Serializable;

public class EmergencyContact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9218236703958895592L;
	private int emergencyContactID;
	private String firstname;
	private String lastname;
	private String phonenumber;

	public EmergencyContact(){}

	public EmergencyContact(int emergencyContactID, String firstname, String lastname, String phonenumber){
		this.emergencyContactID = emergencyContactID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
	}

	public int getEmergencyContactID(){
		return emergencyContactID;
	}

	public void setEmergencyContactID(int emergencyContactID){
		this.emergencyContactID = emergencyContactID;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getLastname(){
		return lastname;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getPhonenumber(){
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber){
		this.phonenumber = phonenumber;
	}

	@Override
	public String toString(){
		return "EmergencyContact [emergencyContactID=" + emergencyContactID
				+ ", firstname=" + firstname
				+ ", lastname=" + lastname
				+ ", phonenumber=" + phonenumber + "]";
	}
}