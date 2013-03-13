package com.ntnu.eit.common.model;

import java.util.Date;

public class Deviation {

	private final int id;
	private final Pasient pasient;
	private final String description;
	private final Date date;

	public Deviation(int id, Pasient pasient, String description, Date date) {
		this.id = id;
		this.pasient = pasient;
		this.description = description;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public Pasient getPasient() {
		return pasient;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}
}