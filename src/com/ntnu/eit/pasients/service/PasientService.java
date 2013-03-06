package com.ntnu.eit.pasients.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;

import com.ntnu.eit.department.model.Department;
import com.ntnu.eit.login.model.User;
import com.ntnu.eit.pasients.model.Pasient;

public class PasientService {

	public static List<Pasient> getPasients(User user, Department[] departments){
		List<Pasient> pasients = new ArrayList<Pasient>();

		pasients.add(new Pasient("Pasient 1", new Date(), Bitmap.createBitmap(50, 50, Config.RGB_565)));
		pasients.add(new Pasient("Pasient 2", new Date(), Bitmap.createBitmap(50, 50, Config.RGB_565)));
		pasients.add(new Pasient("Pasient 3", new Date(), Bitmap.createBitmap(50, 50, Config.RGB_565)));
		pasients.add(new Pasient("Pasient 4", new Date(), Bitmap.createBitmap(50, 50, Config.RGB_565)));
		pasients.add(new Pasient("Pasient 5", new Date(), Bitmap.createBitmap(50, 50, Config.RGB_565)));
		pasients.add(new Pasient("Pasient 6", new Date(), Bitmap.createBitmap(50, 50, Config.RGB_565)));
		pasients.add(new Pasient("Pasient 7", new Date(), Bitmap.createBitmap(50, 50, Config.RGB_565)));
		
		return pasients;
 	}
}