package com.ntnu.eit.common.model;

import java.util.Date;

public class Task {

	private final int id;
	private final Medicine medicine;
	private final Date date;
	private final Pasient pasient;
	private final String dosage;
	private final MedicineForm form;

	public Task(int id, Medicine medicine, Date date, Pasient pasient, String dosage, MedicineForm form) {
		this.id = id;
		this.medicine = medicine;
		this.date = date;
		this.pasient = pasient;
		this.dosage = dosage;
		this.form = form;
	}

	public int getId() {
		return id;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public Date getDate() {
		return date;
	}

	public Pasient getPasient() {
		return pasient;
	}

	public String getDosage() {
		return dosage;
	}

	public MedicineForm getForm() {
		return form;
	}
}