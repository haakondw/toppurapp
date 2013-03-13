package com.ntnu.eit.common.model;

public class ContactPerson {

	private final int id;
	private final int phoneNUmber;
	private final Pasient pasient;
	private final String firstName;
	private final String lastName;

	public ContactPerson(int id, String firstName, String lastName, int phoneNUmber, Pasient pasient) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNUmber = phoneNUmber;
		this.pasient = pasient;
	}

	public int getId() {
		return id;
	}

	public int getPhoneNUmber() {
		return phoneNUmber;
	}

	public Pasient getPasient() {
		return pasient;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}