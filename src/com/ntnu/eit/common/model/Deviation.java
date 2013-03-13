package com.ntnu.eit.common.model;

import java.io.Serializable;
import java.util.Date;

public class Deviation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7187470425856282891L;
	private int deviationID;
	private String description;
	private Date timestamp;

	public Deviation(){}

	public Deviation(int deviationID, String description, Date timestamp){
		this.deviationID = deviationID;
		this.description = description;
		this.timestamp = timestamp;
	}

	public int getDeviationID(){
		return deviationID;
	}

	public void setDeviationID(int deviationID){
		this.deviationID = deviationID;
	}

	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public Date getTimestamp(){
		return timestamp;
	}

	public void setTimestamp(Date timestamp){
		this.timestamp = timestamp;
	}

	@Override
	public String toString(){
		return "Deviation [deviationID=" + deviationID 
				+ ", description=" + description 
				+ ", timestamp=" + timestamp + "]";
	}
}