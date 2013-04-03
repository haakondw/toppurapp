package com.ntnu.eit.common.model;

import java.io.Serializable;

public class Department implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -470289347664691483L;
	private int departmentID;
	private String name;

	public Department(){}

	public Department(int departmentID, String name){
		this.departmentID = departmentID;
		this.name = name;
	}

	public int getDepartmentID(){
		return departmentID;
	}

	public void setDepartmentID(int departmentID){
		this.departmentID = departmentID;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	@Override
	public String toString() {
		return "Patient [departmentID=" + departmentID + ", name=" + name + "]";
	}
}