package com.ntnu.eit;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;
import com.ntnu.eit.common.service.ServiceFactory;
import com.ntnu.eit.pasients.model.PatientsListAdapter;

public class PatientsActivity extends Activity {

	//Thread
	private Runnable runnable = new Runnable() {		
		@Override
		public void run() {
			ArrayList<Object> adapters = new ArrayList<Object>();
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			for (int i = 0; i < departments.length; i++) {
				list.add(departments[i]);
			}
			
			adapters.add(adapter);
			
			ServiceFactory.getInstance().getPatientService().updatePatientList(list, adapters, PatientsActivity.this);
		}
	};
	
	//Activity Params
	public static final String DEPARTMENTS_INDICES = "depIndices";
	
	//View
	private ListView listView;
	
	//Data
	private Patient[] pasients;
	private PatientsListAdapter adapter;
	private int[] departments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Super
		super.onCreate(savedInstanceState);
		
		//Log
		Log.i("EiT", getClass().getSimpleName() + ".onCreate()");
		
		//Params
		departments = getIntent().getExtras().getIntArray(DEPARTMENTS_INDICES);
		Department[] departments = new Department[this.departments.length];
		for (int i = 0; i < departments.length; i++) {
			departments[i] = ServiceFactory.getInstance().getDepartmentService().getDepartmentById(this.departments[i]);
		}
		
		//ThispasientId
		setContentView(R.layout.activity_patients);
		
		//Data
		pasients = ServiceFactory.getInstance().getPatientService().getPatients(this, departments);
		
		//View
		adapter = new PatientsListAdapter(this, R.layout.patients_row, pasients);
		listView = (ListView) findViewById(R.id.pasientList);
		listView.setAdapter(adapter);
		
		//Thread
		if(!ServiceFactory.getInstance().getAuthenticationService().isDebug()){			
			runOnUiThread(runnable);
		}
	}
	
	@Override
	protected void onResume() {
		//Super
		super.onResume();
		
		//Refreshing rows due to updating clock
		listView.invalidateViews();
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pasient, menu);
		return true;
	}
	

	public void onPatientClick2(View view){
		onPatientClick((View) view.getParent());
	}

	public void onPatientClick(View view){
		int count = listView.getAdapter().getCount();
		for (int i = 0; i < count; i++) {
			View child = listView.getChildAt(i);
			
			if(child == view){
				Patient pasient = (Patient) listView.getAdapter().getItem(i);
				
				//Start Patient activity
				Intent intent = new Intent(this, PatientActivity.class);
				
				intent.putExtra(PatientActivity.PASIENT_ID_TAG, pasient.getPatientID());
				startActivity(intent);
			}
		}
	}
}
