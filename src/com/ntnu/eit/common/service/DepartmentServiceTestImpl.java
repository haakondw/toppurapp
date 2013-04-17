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
		if(departments == null){
			if(ServiceFactory.getInstance().getAuthenticationService().isDebug()){
				departments = new Department[5];
				departments[0] = new Department(1, "Avdeling A");
				departments[1] = new Department(2, "Avdeling B");
				departments[2] = new Department(3, "Avdeling C");
				departments[3] = new Department(4, "Avdeling D");
				departments[4] = new Department(5, "Avdeling E");
			}else{				
				departments = new Department[0];
			}
		}
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		for (int i = 0; i < departments.length; i++) {
			if(departments[i].getDepartmentID() == id){
				return departments[i];
			}
		}
		return null;
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