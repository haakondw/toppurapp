package com.ntnu.eit.department.model;

public class DepartmentTestImpl implements Department{
	
	private String name;
	
	public DepartmentTestImpl(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String getId() {
		return "Id" + Math.random()*1000;
	}
}