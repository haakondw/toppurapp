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
			departments = new Department[0];
		}
		
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {		
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