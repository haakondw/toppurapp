package com.ntnu.eit.pasients.service;

import java.util.concurrent.Semaphore;

import com.ntnu.eit.common.service.ServiceFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

public class PatientPictureUpdaterService {

	private static Semaphore semaphore = new Semaphore(1);
	
	public static void setPictureForPatient(final Context context, final ImageView imageView, final int patientId){
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					semaphore.acquire();
					Log.d("EiT", "Starting thread for receiving picture for patient " + patientId);
					
					Options options = new Options();
					options.inJustDecodeBounds = false;
					
					byte[] bs = ServiceFactory.getInstance().getPatientService().getPatientPicture(context, patientId);					
					final Bitmap bitmap = BitmapFactory.decodeByteArray(bs, 0, bs.length, options);
					
					Looper looper = Looper.getMainLooper();
					Handler handler = new Handler(looper);
					handler.post(new Runnable() {
						@Override
						public void run() {
							Log.d("EiT", "Setting picture for patient " + patientId);
							if(bitmap == null){
								Log.d("EiT", "Pasient picture #" + patientId + "is NULL");
							}
							imageView.setImageBitmap(bitmap);
						}
					});
				} catch (InterruptedException e) {
					e.printStackTrace();
				}finally{
					semaphore.release();
				}
			}
		});
		
		thread.setPriority(Thread.MIN_PRIORITY);
		thread.setName("PatientPictureSetterThread");
		thread.start();
	}
}