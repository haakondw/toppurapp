package com.ntnu.eit.common.service;

import java.util.ArrayList;

import android.content.Context;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.socket.DepartmentClient;
import com.ntnu.eit.socket.DepartmentSocketObject;

public class DepartmentServiceTestImpl implements DepartmentService{
	private Department[] departments;

	@Override
	public Department[] getDepartments() {
		Department[] departments = new Department[10];
		departments[0] = new Department(0, "Avdeling A");
		departments[1] = new Department(1, "Avdeling B");
		departments[2] = new Department(2, "Avdeling C");
		departments[3] = new Department(3, "Avdeling D");
		departments[4] = new Department(4, "Avdeling E");
		departments[5] = new Department(5, "Avdeling F");
		departments[6] = new Department(6, "Avdeling G");
		departments[7] = new Department(7, "Avdeling H");
		departments[8] = new Department(8, "Avdeling I");
		departments[9] = new Department(9, "Avdeling J");
		
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		Department[] departments = new Department[10];
		departments[0] = new Department(0, "Avdeling A");
		departments[1] = new Department(1, "Avdeling B");
		departments[2] = new Department(2, "Avdeling C");
		departments[3] = new Department(3, "Avdeling D");
		departments[4] = new Department(4, "Avdeling E");
		departments[5] = new Department(5, "Avdeling F");
		departments[6] = new Department(6, "Avdeling G");
		departments[7] = new Department(7, "Avdeling H");
		departments[8] = new Department(8, "Avdeling I");
		departments[9] = new Department(9, "Avdeling J");
		
		return departments[id];
	}

	@Override
	public void setDepartmentList(ArrayList<Department> departments) {
		if(departments != null && departments.size() > 0){
			Department[] departmentsArray = (Department[]) departments.toArray();
			this.departments = new Department[departmentsArray.length];
			System.arraycopy(departmentsArray, 0, this.departments, 0, departmentsArray.length);
		}		
	}
	
	@Override
	public void updateDepartmentList(ArrayList<Object> adapters, Context context){
		DepartmentSocketObject dso = new DepartmentSocketObject();
		DepartmentClient dc = new DepartmentClient(dso, adapters, context);
		dc.execute();
	}
}