package com.ntnu.eit.common.model;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1241151504752949838L;
	private int taskID;
	private Date timestamp;
	private String dosage;

	public Task(){}

	public Task(int taskID, Date timestamp, String dosage) {
		this.taskID = taskID;
		this.timestamp = timestamp;
		this.dosage = dosage;
	}

	public int getTaskID(){
		return taskID;
	}

	public void setTaskID(int taskID){
		this.taskID = taskID;
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

	@Override
	public String toString(){
		return "Task [taskID=" + taskID + ", timestamp=" + timestamp
				+ ", dosage=" + dosage + "]";
	}


}