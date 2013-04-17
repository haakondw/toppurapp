package com.ntnu.eit.common.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.socket.DepartmentClient;
import com.ntnu.eit.socket.DepartmentSocketObject;

public class DepartmentServiceTestImpl implements DepartmentService{
	
	private List<Department> departments;

	@Override
	public List<Department> getDepartments() {
		if(departments == null){
			if(ServiceFactory.getInstance().getAuthenticationService().isDebug()){
				departments = new ArrayList<Department>();
				departments.add(new Department(1, "Avdeling A"));
				departments.add(new Department(2, "Avdeling B"));
				departments.add(new Department(3, "Avdeling C"));
				departments.add(new Department(4, "Avdeling D"));
				departments.add(new Department(5, "Avdeling E"));
			}else{				
				departments = new ArrayList<Department>();
			}
		}
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		for (Department department: departments) {
			if(department.getDepartmentID() == id){
				return department;
			}
		}
		return null;
	}

	@Override
	public void setDepartmentList(ArrayList<Department> departments) {
		if(departments != null && departments.size() > 0){
			this.departments.clear();
			this.departments.addAll(departments);
		}
	}

	@Override
	public void updateDepartmentList(ArrayList<Object> adapters, Context context){
		DepartmentSocketObject dso = new DepartmentSocketObject();
		DepartmentClient dc = new DepartmentClient(dso, adapters, context);
		dc.execute();
	}
}