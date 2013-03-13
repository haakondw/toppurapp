package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.Department;

public interface DepartmentService {

	public Department[] getDepartments();

	public Department getDepartmentById(int id);
	
}