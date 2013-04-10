package com.ntnu.eit.socket;

import java.io.Serializable;


public class PictureSocketObject implements Serializable{
  

	private static final long serialVersionUID = 5936434784692547982L;
	private int patientId;
	private byte[] lastCheckSum = null;

	public PictureSocketObject(int patientIds) {
		this.patientId = patientIds;
	}

	public void setPatientId(int patientIds){
		this.patientId = patientIds;
	}
	public int getPatientId(){
		return patientId;
	}

	public byte[] getLastChecksum() {
		return lastCheckSum;
	}

	public void setLastChecksum(byte[] lastCheckSum) {
		this.lastCheckSum = lastCheckSum;
	}
    
}
