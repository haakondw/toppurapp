package com.ntnu.eit.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ntnu.eit.R;
import com.ntnu.eit.common.model.User;
import com.ntnu.eit.common.service.ServiceFactory;

public class UserView extends RelativeLayout{

	public UserView(Context context, AttributeSet attrs) {
		//Super
		super(context, attrs);
		
		//Init
		inflate(context, R.layout.logged_in_user_view, this);
		
		//Text
		TextView firstNameView = (TextView) findViewById(R.id.user_view_first_name);
		TextView lastNameView = (TextView) findViewById(R.id.user_view_last_name);
		
		//Getting user info
		User user = ServiceFactory.getInstance().getAuthenticationService().getLoggedInUser();
		firstNameView.setText(user.getFirstname());
		lastNameView.setText(user.getLastname());
	}
}