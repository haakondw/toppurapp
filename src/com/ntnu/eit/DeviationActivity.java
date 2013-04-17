package com.ntnu.eit;

import com.ntnu.eit.common.model.Patient;
import com.ntnu.eit.common.service.ServiceFactory;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

public class DeviationActivity extends Activity {
	
	//Params
	public static String PARAM_PATIENT_ID;
	
	//Patient
	private Patient patient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Super
		super.onCreate(savedInstanceState);
		
		//Init
		int patientId = getIntent().getExtras().getInt(PARAM_PATIENT_ID);
		patient = ServiceFactory.getInstance().getPatientService().getPatientById(patientId);
		
		//This
		setContentView(R.layout.activity_deviation);
	}
	
	@Override
	protected void onResume() {
		//Super
		super.onResume();
		
		//Init
		int size = PreferenceManager.getDefaultSharedPreferences(this).getInt("text_size", 50);
		
		//Text Size/text
		TextView textView = (TextView) findViewById(R.id.deviation_patient_name);
		textView.setText("Pasient: " + patient.getFirstname() + ", " + patient.getLastname());
		textView.setTextSize(50*size/100);
		
		//Time
		TimePicker timePicker = (TimePicker) findViewById(R.id.deviation_time);
		int hour = (int) (System.currentTimeMillis() % (1000*24));
		int minute = (int) (System.currentTimeMillis() % (1000*60));
		timePicker.setCurrentHour(hour);
		timePicker.setCurrentMinute(minute);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_deviation, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(this, LoginSettingsActivity.class));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void onDeviationSubmition(View view){
		Log.d("EiT", "onDeviationSubmition()");
		
		finish();
	}
}