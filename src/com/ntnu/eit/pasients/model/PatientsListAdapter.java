package com.ntnu.eit.pasients.model;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntnu.eit.R;
import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;
import com.ntnu.eit.common.model.Task;
import com.ntnu.eit.common.service.ServiceFactory;
import com.ntnu.eit.pasients.service.PatientPictureUpdaterService;

public class PatientsListAdapter extends ArrayAdapter<Patient>{

	private Context context;
	private Patient[] patients;
	private int textViewResourceId;
	private SimpleDateFormat format;

	public PatientsListAdapter(Context context, int textViewResourceId, Patient[] pasients) {
		super(context, textViewResourceId, pasients);
		
		this.patients = pasients;
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		format = new SimpleDateFormat("HH:mm", Locale.getDefault());
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View row = convertView;
    	PasientHolder holder = null;
        
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(textViewResourceId, parent, false);
            
            holder = new PasientHolder();
            holder.nameView = (TextView) row.findViewById(R.id.pasientName);
            holder.departmentView = (TextView) row.findViewById(R.id.pasientDepartment);
            holder.clockView = (TextView) row.findViewById(R.id.pasientClock);
            holder.pictureView = (ImageView) row.findViewById(R.id.pasientImage);
            
            //Text size
    		int size = PreferenceManager.getDefaultSharedPreferences(context).getInt("text_size", 50);
    		holder.nameView.setTextSize(50*size/100);
    		holder.clockView.setTextSize(50*size/100);
    		holder.departmentView.setTextSize(20*size/100);
            
            row.setTag(holder);
        }else{
            holder = (PasientHolder)row.getTag();
        }
        
        Patient pasient = patients[position];
        holder.nameView.setText(pasient.getFirstname() + ", " + pasient.getLastname());
        
        Department department = ServiceFactory.getInstance().getDepartmentService().getDepartmentById(pasient.getDepartmentID());
        holder.departmentView.setText(department.getName());
        
        //Setting picture
        PatientPictureUpdaterService.setPictureForPatient(context, holder.pictureView, pasient.getPatientID());
        
        //Set clock text
        Task[] tasks = ServiceFactory.getInstance().getTaskService().getTasks(pasient.getPatientID());
        if(tasks.length > 0){
        	for (int i = 0; i < tasks.length; i++) {
				if(!tasks[i].isExecuted()){	
					holder.clockView.setText(format.format(tasks[i].getTimestamp()));
					break;
				}
			}
        }
        
        return row;
    } 
    
    static class PasientHolder{
        TextView nameView;
        TextView departmentView;
        TextView clockView;
        ImageView pictureView;
    }
}