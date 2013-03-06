package com.ntnu.eit;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.ntnu.eit.pasient.model.Pasient;
import com.ntnu.eit.pasient.model.PasientsListAdapter;
import com.ntnu.eit.pasient.service.PasientService;

public class PasientsActivity extends Activity {

	//Data
	private List<Pasient> pasients;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pasients);
		
		//Data
		pasients = PasientService.getPasients(null, null);
		
		//View
		ListView listView = (ListView) findViewById(R.id.pasientList);
		listView.setAdapter(new PasientsListAdapter(this, R.layout.pasients_row, pasients.toArray(new Pasient[pasients.size()])));
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
			if(pasient.getName().equalsIgnoreCase(pasientName)){
				//Start Pasient activity
				Intent intent = new Intent(this, PasientActivity.class);
				
				intent.putExtra(PasientActivity.PASIENT_NAME_TAG, pasient.getName());
				startActivity(intent);
			}
		}
	}
}
