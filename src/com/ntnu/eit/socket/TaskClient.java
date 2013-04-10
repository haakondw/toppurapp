package com.ntnu.eit.socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import com.ntnu.eit.common.model.Task;
import com.ntnu.eit.common.service.ServiceFactory;
import com.ntnu.eit.common.service.TaskService;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.format.Time;
import android.util.Log;

/**
 * This class is an asynchronous class, designated to retrieving data from the
 * server.
 */
public class TaskClient extends AsyncTask<Void, Integer, ArrayList<Object>> {
	private final static String IP = "http://rutatkak.no";
	private final static int PORT = 31111;
	private TaskSocketObject tso = null;
	private ArrayList<Task> tasks = null;
	private TaskService ts;

	/* PID of patient, if update if from PatientActivity */
	private String patientId;

	/* Adapters to notify */
	ArrayList<Object> adapters;

	/* Dialog */
	private Context context;
	private AlertDialog errorDialog;
	private boolean showErrorDialog = false;

	/**
	 * 
	 * @param tso An object of the class TaskSocketObject, which implies to
	 *        the server that the client wants to retrieve tasks.
	 * @param adapters The adapters which should be notified that their data
	 *        has changed.
	 * @param context The context from the calling activity.
	 * @param patientId The patient ID
	 * @param executedTasks Tasks that has been checked as executed on the client side (in order to update the server database).
	 */
	public TaskClient(TaskSocketObject tso, ArrayList<Object> adapters, Context context, String patientId, ArrayList<Integer> executedTasks) {
		this.tso = tso;
		this.adapters = adapters;
		this.context = context;
		this.patientId = patientId;
		this.ts = ServiceFactory.getInstance().getTaskService();
	}

	/**
	 * This method sets up an error dialog to be used if the client cannot
	 * connect to the server.
	 */
	protected void onPreExecute() {
		if (context != null) {
			errorDialog = new AlertDialog.Builder(context).create();
			errorDialog.setTitle("context.getString(R.string.warning)");
			errorDialog.setMessage("context.getString(R.string.conncetion_failed)");
			errorDialog.setButton("OK", new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					return;
				}
			});
		}
	}

	/**
	 * This method does the communication with the server over a socket. It
	 * retrieves the wanted objects from the server.
	 */
	@Override
	protected ArrayList<Object> doInBackground(Void... params) {
		Socket s = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		ArrayList<Object> objects = null;

		try {
			objects = new ArrayList<Object>();
			s = new Socket(IP, PORT);
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject(tso);
			out.flush();
			Object o = in.readObject();
			if (o instanceof ArrayList) {
				tasks = new ArrayList<Task>();
				ArrayList<Object> list = (ArrayList<Object>) o;
				for (Object object : list) {
					if (object instanceof Task) {
						Task t = (Task) object;
						//t.setTime(new Time());
						//t.getTime().set(t.getTimeInMillis());
						objects.add(t);
					}
				}
			}
		} catch (Exception e) {
			if (context != null) {
				/**
				 * If communication with the server failed, the error dialog
				 * should be shown.
				 */
				showErrorDialog = true;
			}

		} finally {
			try {
				out.close();
				in.close();
				s.close();
			} catch (Exception e) {
				Log.e("Client", "Exception closing " + e);
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
		if (showErrorDialog) errorDialog.show();
		
		
		for (Object o : objects) {
			if (o instanceof Task) {
				Task t = (Task) o;
				tasks.add(t);
			}
		}
		if (tasks != null && !tasks.isEmpty()) {
			Log.e("Client", "tasksLength: " + tasks.size());
			
			/* update task list*/
			ts.updateTaskList(tasks);

			/* Notify adapter(s) that the data has changed */
//			if (adapters != null) {
//				for (Object a : adapters) {
//					if (a instanceof EventListFilterableAdapter) {
//						((EventListFilterableAdapter) a).notifyDataSetChanged();
//					}
//					if(a instanceof EventListPatientFilterableAdapter){
//						((EventListPatientFilterableAdapter) a).notifyDataSetChanged();
//					}
//				}
//			}
		}

	}

}

