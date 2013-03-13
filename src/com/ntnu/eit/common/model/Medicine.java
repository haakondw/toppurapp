package com.ntnu.eit.common.model;

public class Medicine {

	private final int id;
	private final String name;

	public Medicine(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}