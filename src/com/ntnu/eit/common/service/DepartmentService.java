package com.ntnu.eit.common.service;

import java.util.ArrayList;

import android.content.Context;

import com.ntnu.eit.common.model.Department;

public interface DepartmentService {

	public Department[] getDepartments();

	public Department getDepartmentById(int id);
	
	public void setDepartmentList(ArrayList<Department> departments);

	public void updateDepartmentList(ArrayList<Object> adapters, Context context);
	
}