package com.ntnu.eit.socket;

import java.io.Serializable;
import java.util.ArrayList;

import com.ntnu.eit.common.model.Department;


public class PatientSocketObject implements Serializable{
  

	private static final long serialVersionUID = 5936434774692547982L;
	private ArrayList<Integer> departmentIds;
	public PatientSocketObject(ArrayList<Integer> departmentId) {
		this.departmentIds = departmentIds;
	}
	public void setDepartmentIds(ArrayList<Integer> departmentIds){
		this.departmentIds = departmentIds;
	}
	public ArrayList<Integer> getDepartmentIds(){
		return departmentIds;
	}

    
    
    
}
