package com.ntnu.eit.common.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.ntnu.eit.R;

public class ConfirmationDialog extends Dialog implements android.view.View.OnClickListener{

	private ConfirmationDialogListener listener;

	private ConfirmationDialog(Context context, ConfirmationDialogListener listener) {
		//Super
		super(context);
		
		//Init
		this.listener = listener;
	}
	
	public static ConfirmationDialog create(Context context, String title, ConfirmationDialogListener listener){
		return create(context, title, "Cancel", "Confirm", listener);
	}
	
	public static ConfirmationDialog create(Context context, String title, String cancelTxt, String confirmTxt, ConfirmationDialogListener listener){
		//Init
		ConfirmationDialog dialog = new ConfirmationDialog(context, listener);
		
		//Dialog
		dialog.setTitle(title);
		dialog.setContentView(R.layout.dialog_confirmation);
		
		//Adding button listeners
		((Button)(dialog.findViewById(R.id.dialog_confirmation_cancel))).setOnClickListener(dialog);
		((Button)(dialog.findViewById(R.id.dialog_confirmation_confirm))).setOnClickListener(dialog);
		
		//Button text
		((Button)(dialog.findViewById(R.id.dialog_confirmation_cancel))).setText(cancelTxt);
		((Button)(dialog.findViewById(R.id.dialog_confirmation_confirm))).setText(confirmTxt);
		
		return dialog;
	}

	@Override
	public void onClick(View v) {
		if(v == findViewById(R.id.dialog_confirmation_confirm)){
			listener.onConfirm(this);
		}else{
			listener.onCancel(this);
		}
	}
	
	public interface ConfirmationDialogListener{
		public void onConfirm(ConfirmationDialog dialog);
		public void onCancel(ConfirmationDialog dialog);
	}
}