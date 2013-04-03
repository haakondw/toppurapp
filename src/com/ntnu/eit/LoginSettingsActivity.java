package com.ntnu.eit;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class LoginSettingsActivity extends PreferenceActivity {

	@Override
	@SuppressWarnings("deprecation")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.login_settings);
	}
}