package com.ntnu.eit.socket;

import java.io.Serializable;
import java.util.ArrayList;


public class PatientSocketObject implements Serializable{
  

	private static final long serialVersionUID = 5936434774692547982L;
	private ArrayList<Integer> departmentIds;
	public PatientSocketObject(ArrayList<Integer> departmentId) {
		departmentIds = new ArrayList<Integer>(departmentId);
	}
	public void setDepartmentIds(ArrayList<Integer> departmentIds){
		this.departmentIds = departmentIds;
	}
	public ArrayList<Integer> getDepartmentIds(){
		return departmentIds;
	}

    
    
    
}
