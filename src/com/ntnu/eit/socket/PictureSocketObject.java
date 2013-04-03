package com.ntnu.eit.socket;

import java.io.Serializable;


public class PictureSocketObject implements Serializable{
  

	private static final long serialVersionUID = 5936434784692547982L;
	private int patientId;

	public PictureSocketObject(int patientIds) {
		this.patientId = patientIds;
	}

	public void setPatientId(int patientIds){
		this.patientId = patientIds;
	}
	public int getPatientId(){
		return patientId;
	}

    
    
    
}
