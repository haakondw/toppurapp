package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.Department;

public class DepartmentServiceTestImpl implements DepartmentService{

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
}