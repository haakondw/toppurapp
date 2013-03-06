package com.ntnu.eit.pasient.model;

import java.io.Serializable;
import java.util.Date;

import android.graphics.Bitmap;

public class Pasient implements Serializable{
	
	/**
	 * @author boge
	 */
	private static final long serialVersionUID = 1637207841552286613L;
	
	private String name;
	private Date birth;
	private Bitmap picture;

	public Pasient(String name, Date birth, Bitmap picture) {
		this.name = name;
		this.birth = birth;
		this.picture = picture;
	}

	public String getName(){
		return name;
	}
	
	public Date getBirthDate(){
		return birth;
	}
	
	public Bitmap getPicture(){
		return picture;
	}
}