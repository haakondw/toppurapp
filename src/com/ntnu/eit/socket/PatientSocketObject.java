package com.ntnu.eit.socket;

import java.io.Serializable;


public class PatientSocketObject implements Serializable{
  

	private static final long serialVersionUID = 5936434774692547982L;
	private int departmentId;
	public PatientSocketObject(int departmentId) {
		this.departmentId = departmentId;

		
	}
	public void setDepartmentId(int departmentId){
		this.departmentId = departmentId;
	}
	public int getDepartmentId(){
		return departmentId;
	}

    
    
    
}
