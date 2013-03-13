package com.ntnu.eit.common.model;

import java.io.Serializable;

public class MedicineForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7859719947157585234L;
	private String form;

	public MedicineForm(){}

	public MedicineForm(String form){
		this.form = form;
	}

	public String getForm(){
		return form;
	}

	public void setForm(String form){
		this.form = form;
	}

	@Override
	public String toString() {
		return "MedicineForm [form=" + form + "]";
	}



}