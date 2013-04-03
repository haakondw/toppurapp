package com.ntnu.eit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.common.service.ServiceFactory;
import com.ntnu.eit.pasients.model.PasientsListAdapter;

public class PasientsActivity extends Activity {

	//Activity Params
	public static final String DEPARTMENTS_INDICES = "depIndices";
	
	//View
	private ListView listView;
	
	//Data
	private Pasient[] pasients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Super
		super.onCreate(savedInstanceState);
		
		//Log
		Log.i("EiT", getClass().getSimpleName() + ".onCreate()");
		
		//Params
		int[] is = getIntent().getExtras().getIntArray(DEPARTMENTS_INDICES);
		Department[] departments = new Department[is.length];
		for (int i = 0; i < departments.length; i++) {
			departments[i] = ServiceFactory.getInstance().getDepartmentService().getDepartmentById(is[i]);
		}
		
		//ThispasientId
		setContentView(R.layout.activity_pasients);
		
		//Data
		pasients = ServiceFactory.getInstance().getPasientService().getPasients(null, departments);
		
		//View
		listView = (ListView) findViewById(R.id.pasientList);
		listView.setAdapter(new PasientsListAdapter(this, R.layout.pasients_row, pasients));
	}
	
	@Override
	protected void onResume() {
		//Super
		super.onResume();
		
		//Refreshing rows due to updating clock
		listView.invalidateViews();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pasient, menu);
		return true;
	}

	public void onPasientClick(View view){
		int count = listView.getAdapter().getCount();
		for (int i = 0; i < count; i++) {
			View child = listView.getChildAt(i);
			
			if(child == view){
				Pasient pasient = (Pasient) listView.getAdapter().getItem(i);
				
				//Start Pasient activity
				Intent intent = new Intent(this, PasientActivity.class);
				
				intent.putExtra(PasientActivity.PASIENT_ID_TAG, pasient.getPasientID());
				startActivity(intent);
			}
		}
	}
}
