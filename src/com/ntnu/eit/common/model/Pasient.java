package com.ntnu.eit.common.model;

import android.graphics.Bitmap;

public class Pasient {
	
	private final int id;
	private final String firstName;
	private final String lastName;
	private final String socialSecurityNumber;
	private final Bitmap picture;
	private final Department department;

	public Pasient(int id, String firstName, String lastName, String socialSecurityNumber, Bitmap picture, Department department) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.socialSecurityNumber = socialSecurityNumber;
		this.picture = picture;
		this.department = department;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public Bitmap getPicture() {
		return picture;
	}

	public Department getDepartment() {
		return department;
	}
}
