package com.ntnu.eit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.common.service.ServiceFactory;
import com.ntnu.eit.pasients.model.PasientsListAdapter;

public class PasientsActivity extends Activity {

	//Activity Params
	public static final String DEPARTMENTS_INDICES = "depIndices";
	
	//Data
	private Pasient[] pasients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//Super
		super.onCreate(savedInstanceState);
		
		//This
		setContentView(R.layout.activity_pasients);
		
		//Data
		pasients = ServiceFactory.getInstance().getPasientService().getPasients(null, null);
		
		//View
		ListView listView = (ListView) findViewById(R.id.pasientList);
		listView.setAdapter(new PasientsListAdapter(this, R.layout.pasients_row, pasients));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pasient, menu);
		return true;
	}

	public void onPasientClick(View view){
		String pasientName = ((TextView)(view.findViewById(R.id.pasientName))).getText().toString();
		
		for (Pasient pasient : pasients) {
			String temp = pasient.getFirstname() + ", " + pasient.getLastname();
			if(temp.equalsIgnoreCase(pasientName)){
				//Start Pasient activity
				Intent intent = new Intent(this, PasientActivity.class);
				
				intent.putExtra(PasientActivity.PASIENT_ID_TAG, pasient.getPasientID());
				startActivity(intent);
			}
		}
	}
}
