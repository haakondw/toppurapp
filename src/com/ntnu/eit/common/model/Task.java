package com.ntnu.eit.common.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241151504752949838L;
	private int taskID;
	private int medicineId;
	private String medicineForm;
	private Date timestamp;
	private String dosage;
	private boolean executed;

	public Task(){}

	public Task(int taskID, int medicineId, String medicineForm, Date timestamp, String dosage, boolean executed) {
		this.taskID = taskID;
		this.medicineId = medicineId;
		this.medicineForm = medicineForm;
		this.timestamp = timestamp;
		this.dosage = dosage;
		this.executed = executed;
	}

	public int getTaskID(){
		return taskID;
	}

	public void setTaskID(int taskID){
		this.taskID = taskID;
	}
	
	public int getMedicineId(){
		return medicineId;
	}
	
	public void setMedicineId(int medicineId){
		this.medicineId = medicineId;
	}
	
	public String getMedicineForm(){
		return medicineForm;
	}
	
	public void setMedicineFormId(String medicineForm){
		this.medicineForm = medicineForm;
	}

	public Date getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(Date timestamp){
		this.timestamp = timestamp;
	}

	public String getDosage(){
		return dosage;
	}

	public void getDosage(String dosage){
		this.dosage = dosage;
	}
	
	public boolean isExecuted(){
		return executed;
	}
	
	public void setExecuted(boolean executed){
		this.executed = executed;
	}

	@Override
	public String toString(){
		return "Task [taskID=" + taskID + ", timestamp=" + timestamp
				+ ", dosage=" + dosage + "]";
	}
}