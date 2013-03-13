package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.Department;

public class DepartmentServiceTestImpl implements DepartmentService{

	@Override
	public Department[] getDepartments() {
		Department[] departments = new Department[10];
		departments[0] = new Department(0, "Accident and emergency");
		departments[1] = new Department(1, "Anaesthetics");
		departments[2] = new Department(2, "Cardiology");
		departments[3] = new Department(3, "Chaplaincy");
		departments[4] = new Department(4, "Critical care");
		departments[5] = new Department(5, "Diagnostic imaging");
		departments[6] = new Department(6, "Discharge lounge");
		departments[7] = new Department(7, "Ear nose and throat");
		departments[8] = new Department(8, "Elderly services department");
		departments[9] = new Department(9, "General surgery");
		
		return departments;
	}

	@Override
	public Department getDepartmentById(int id) {
		Department[] departments = new Department[10];
		departments[0] = new Department(0, "Accident and emergency");
		departments[1] = new Department(1, "Anaesthetics");
		departments[2] = new Department(2, "Cardiology");
		departments[3] = new Department(3, "Chaplaincy");
		departments[4] = new Department(4, "Critical care");
		departments[5] = new Department(5, "Diagnostic imaging");
		departments[6] = new Department(6, "Discharge lounge");
		departments[7] = new Department(7, "Ear nose and throat");
		departments[8] = new Department(8, "Elderly services department");
		departments[9] = new Department(9, "General surgery");
		
		return departments[id];
	}
}