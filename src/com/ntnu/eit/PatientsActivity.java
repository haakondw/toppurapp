package com.ntnu.eit;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.database.DataSetObserver;
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

	private Runnable runnable2 = new Runnable() {
		@Override
		public void run() {
			ArrayList<Object> adapters = new ArrayList<Object>();
			ArrayList<Integer> executedTasks = new ArrayList<Integer>();
			
			adapters.add(adapter);
			
			for (Patient patient : pasients) {			
				ServiceFactory.getInstance().getTaskService().getTasks(patient.getPatientID());
				ServiceFactory.getInstance().getTaskService().getHistoyTasks(patient.getPatientID());	
				ServiceFactory.getInstance().getTaskService().updateTaskList(patient.getPatientID(), executedTasks, adapters, PatientsActivity.this);
			}
		}
	};
	
	//Activity Params
	public static final String DEPARTMENTS_INDICES = "depIndices";
	
	//View
	private ListView listView;
	
	//Data
	private List<Patient> pasients;
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
		List<Department> departments = new ArrayList<Department>();
		for (int i = 0; i < this.departments.length; i++) {
			departments.add(ServiceFactory.getInstance().getDepartmentService().getDepartmentById(this.departments[i]));
		}
		
		//ThispasientId
		setContentView(R.layout.activity_patients);
		
		//Data
		pasients = ServiceFactory.getInstance().getPatientService().getPatients(this, departments);
		
		//View
		adapter = new PatientsListAdapter(this, R.layout.patients_row, pasients);
		listView = (ListView) findViewById(R.id.pasientList);
		listView.setAdapter(adapter);
		
		adapter.registerDataSetObserver(new Observer(this));
		
		//Thread
		if(!ServiceFactory.getInstance().getAuthenticationService().isDebug()){			
			runOnUiThread(runnable);	
		}
	}
	
	@Override
	protected void onResume() {
		//Super
		super.onResume();
		
		if(!ServiceFactory.getInstance().getAuthenticationService().isLoggedIn()){
			finish();
		}
		
		//Refreshing rows due to updating clock
		listView.invalidateViews();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(this, LoginSettingsActivity.class));
			return true;
		case R.id.logout:
			ServiceFactory.getInstance().getAuthenticationService().logout();
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_common, menu);
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
	
	class Observer extends DataSetObserver{
		
		private PatientsActivity activity;
		private int count = 0;

		public Observer(PatientsActivity activity) {
			this.activity = activity;
		}
		
		@Override
		public void onChanged() {
			super.onChanged();
			count++;
			if(count < pasients.size()){				
				activity.runOnUiThread(runnable2);
			}
		}
	}
}
