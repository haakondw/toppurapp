package com.ntnu.eit;

import com.ntnu.eit.common.service.ServiceFactory;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class LoginSettingsActivity extends PreferenceActivity {

	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		//Settings
		super.onCreate(savedInstanceState);
		
		//This
		addPreferencesFromResource(R.xml.login_settings);
	}
}