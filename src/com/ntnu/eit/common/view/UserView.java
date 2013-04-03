package com.ntnu.eit.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.ntnu.eit.R;

public class UserView extends RelativeLayout{

	public UserView(Context context, AttributeSet attrs) {
		//Super
		super(context, attrs);
		
		addView(inflate(context, R.layout.logged_in_user_view, this));
	}
}