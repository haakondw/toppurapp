package com.ntnu.eit.socket;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

import com.ntnu.eit.common.service.PatientService;
import com.ntnu.eit.common.service.ServiceFactory;
import com.ntnu.eit.pasients.model.PatientsListAdapter;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * This class is an asynchronous class, designated to retrieving data from the
 * server.
 */
public class PictureClient extends AsyncTask<Void, Integer, ArrayList<Object>> {
	private static String IP;
	private final static int PORT = 31111;
	private PictureSocketObject pso = null;
	private byte[] picture = null;
	private PatientService ps;

	/* Adapters to notify */
	ArrayList<Object> adapters;

	/**
	 * 
	 * @param pso An object of the class PictureSocketObject, which implies to
	 *        the server that the client wants to retrieve a picture.
	 * @param adapters The adapters which should be notified that their data
	 *        has changed.
	 */
	public PictureClient(PictureSocketObject pso, ArrayList<Object> adapters, Context context) {
		this.pso = pso;
		this.adapters = adapters;
		this.ps = ServiceFactory.getInstance().getPatientService();
		IP = ServiceFactory.getInstance().getAuthenticationService().getHost();
	}

	protected void onPreExecute() {

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
			out.writeObject(pso);
			out.flush();
			Object o = in.readObject();
			if (o instanceof byte[]) {
				byte[] o2 = (byte[]) o;
				objects.add(o2);
			}
		} catch (Exception e) {
			
		} finally {
			try {
				out.close();
				in.close();
				s.close();
			} catch (Exception e) {
				Log.e("PictureClient", "Exception closing " + e);
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
	 */
	protected void onPostExecute(ArrayList<Object> objects) {
		
		for (Object o : objects) {
			if (o instanceof byte[]) {
				picture = (byte[]) o;
			}
		}
		if (picture != null && picture.length != 0) {
			
			/* update task list*/
			ps.setPatientPicture(pso.getPatientId(), picture);

			/* Notify adapter(s) that the data has changed */
			//TODO must be changed to working adapters
			if (adapters != null) {
				for (Object a : adapters) {
					if (a instanceof PatientsListAdapter) {
						((PatientsListAdapter) a).notifyDataSetChanged();
					}
				}
			}
		}

	}

}

