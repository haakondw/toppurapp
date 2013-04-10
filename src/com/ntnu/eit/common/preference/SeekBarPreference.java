package com.ntnu.eit.common.preference;

import com.ntnu.eit.R;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SeekBarPreference extends Preference implements OnSeekBarChangeListener{

	//FINALS
	public static final int DEFAULT_VALUE = 50;
	
	//View
	private SeekBar seekBar;
	private TextView valueView;

	/**
	 * 
	 * @param context
	 */
	public SeekBarPreference(Context context, AttributeSet attributes) {
		//Super
		super(context, attributes);
		
		//This
		setPersistent(false);
	}

	@Override
	protected void onBindView(View view) {
		//Super
		super.onBindView(view);

		//Init
		seekBar = (SeekBar) view.findViewById(R.id.prefSeekbar);
		valueView = (TextView) view.findViewById(R.id.prefSeekbarValueView);
		
		//Setting value
		valueView.setText(getSharedPreferences().getInt(getKey(), DEFAULT_VALUE) + " %");
		seekBar.setProgress(getSharedPreferences().getInt(getKey(), DEFAULT_VALUE));
		
		//Listener
		seekBar.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		valueView.setText(progress + " %");
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		getEditor().putInt(getKey(), seekBar.getProgress()).commit();
	}
}