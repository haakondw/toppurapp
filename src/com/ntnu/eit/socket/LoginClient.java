package com.ntnu.eit.socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import com.ntnu.eit.R;
import com.ntnu.eit.common.model.User;
import com.ntnu.eit.common.service.AuthenticationService;
import com.ntnu.eit.common.service.ServiceFactory;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
/**
 * This class is an asynchronous class, designated to 
 * retrieving data from the server.
 */
public class LoginClient extends AsyncTask<Void, Integer, ArrayList<Object>>{
	private static String IP;
	private final static int PORT = 31111;
	private LoginSocketObject lso = null;
	private AuthenticationService as;
	private User user = null;
	
	/* adapters to notify */
	ArrayList<Object> adapters;
	
	/* Dialogs */
	private Context context;
	private ProgressDialog dialog;
	private AlertDialog errorDialog;
	private boolean showErrorDialog = false;
	
	/**
	 * 
	 * @param lso, an object of the class LoginSocketObject, which implies to the server that
	 * 				the client wants to retrieve login confirmation.
	 * @param adapters, the adapters which should be notified that their data has changed.
	 * @param context, the context from the calling activity.
	 * 
	 */
	public LoginClient(LoginSocketObject lso, ArrayList<Object> adapters, Context context) {
		this.lso = lso;
		this.adapters = adapters;
		this.context = context;
		this.as = ServiceFactory.getInstance().getAuthenticationService();
		IP = PreferenceManager.getDefaultSharedPreferences(context).getString("login_settings_server_config", "test");
	}

	/**
	 * This method sets up an error dialog to be used if the client cannot connect to the server.
	 * It also shows a "please wait"-dialog to tell the user to wait, while the client retrieves data from the server.
	 */
	public void onPreExecute(){
		if(context != null){
			dialog = ProgressDialog.show(context, context.getString(R.string.please_wait), context.getString(R.string.connecting));
			errorDialog = new AlertDialog.Builder(context).create();
			errorDialog.setTitle(context.getString(R.string.warning));
			errorDialog.setMessage(context.getString(R.string.conncetion_failed));
						
			errorDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
	
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.d("EiT", "Dismissing error dialog");
					dialog.dismiss();
				}
			});
		}
		
	}
	
	/**
	 * This method updates the message on the "please wait"-dialog
	 */
	protected void onProgressUpdate(Integer...args){
		if(context != null && dialog != null) dialog.setMessage(context.getString(R.string.fetching_data));
	}
	
	/**
	 * This method does the communication with the server over a socket.
	 * It retrieves the wanted objects from the server.
	 */
	@Override
	protected ArrayList<Object> doInBackground(Void... params) {
		publishProgress(1);
	    Socket s = null;
	    ObjectInputStream in = null;
	    ObjectOutputStream out = null;
	    ArrayList<Object> objects = null;

	    try {
	    	objects = new ArrayList<Object>();
			s = new Socket (IP,PORT);
			Log.e("Client","Created New Socket");
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(lso);
			out.flush();
			Object o = in.readObject();
			if (o instanceof ArrayList) {
			    ArrayList<Object> list = (ArrayList<Object>)o;
			    for (Object object : list) {
					if (object instanceof User) {
					    User u = (User) object;
					    objects.add(u);
					}
			    }
		    } 		   
	    }
	    catch (Exception e) {
	    	e.printStackTrace();
	    	if (context != null) {
	    		/**
				 * If communication with the server failed, the error dialog
				 * should be shown.
				 */
	    		
				showErrorDialog = true;
			}
	    }
	    finally {
		    try {
			    out.close();
			    in.close();
			    s.close();
		    }
		    catch (Exception e) {
		    	Log.e("Client","Exception closing "+e);
		    }
	    }
 		return objects;
	}

	/**
	 * If the communication was successful, this method casts the
	 * objects from the server to the appropriate class and adds them to a list
	 * in the appropriate service. Finally, the adapters holding the data
	 * needs to be notified that their data has changed.
	 * 
	 * If the communication with the server failed an error dialog is shown.
	 */
	protected void onPostExecute(ArrayList<Object> objects) {
		if (showErrorDialog)errorDialog.show();
		for (Object o : objects) {
			if (o instanceof User) {
				User u = (User) o;
				user = u;
			}
		}
		
		/* update patient list */
		if (user != null) {
			as.setUser(user);
		}
		
		//TODO must be changed to working adapters
//		for(Object a : adapters){
//			 if(a instanceof ManagePatientAdapter){
//				((ManagePatientAdapter) a).notifyDataSetChanged();
//				if(Overview.patients.isEmpty()) AddPatientActivity.showErrorMessage(context);
//			}
//		}
		
		if(context != null && dialog != null) {
			Log.e("Client","dialog.dismiss()");
				dialog.dismiss();
			}
		}
}

