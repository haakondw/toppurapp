package com.ntnu.eit;

import com.ntnu.eit.common.service.ServiceFactory;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;

public class LoginSettingsActivity extends PreferenceActivity {

	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		//Settings
		super.onCreate(savedInstanceState);
		
		//This
		addPreferencesFromResource(R.xml.login_settings);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
		if(!ServiceFactory.getInstance().getAuthenticationService().isLoggedIn()){
			finish();
		}
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
}