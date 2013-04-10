package com.ntnu.eit.pasient.model;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ntnu.eit.R;
import com.ntnu.eit.common.model.Medicine;
import com.ntnu.eit.common.model.Task;
import com.ntnu.eit.common.service.ServiceFactory;

public class PatientTaskListAdapter extends ArrayAdapter<Task>{

	private Context context;
	private int textViewResourceId;
	private Task[] tasks;
	private SimpleDateFormat format;

	public PatientTaskListAdapter(Context context, int textViewResourceId, Task[] tasks) {
		//Super
		super(context, textViewResourceId, tasks);
		
		//Init
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.tasks = tasks;
		format = new SimpleDateFormat("HH:mm", Locale.getDefault());
		
		Log.d("EiT", "Init TaskAdapter with " + tasks.length + " tasks");
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	Log.d("EiT", "getView(Pos = " + position + ")");
		
		View row = convertView;
    	PasientTaskHolder holder = null;
        Task task = tasks[position];
        
        if(row == null){
        	Log.d("EiT", "Row " + position + " is null");
        	
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(textViewResourceId, parent, false);
            
            holder = new PasientTaskHolder();
            holder.medicine = (TextView) row.findViewById(R.id.pasient_task_medicine);
            holder.medicineForm = (TextView) row.findViewById(R.id.pasient_task_medicine_form);
            holder.dosage = (TextView) row.findViewById(R.id.pasient_task_medicine_dosage);
            holder.time = (TextView) row.findViewById(R.id.pasient_task_time);
            holder.executed = (CheckBox) row.findViewById(R.id.pasient_task_checkbox);
            holder.background = (ViewGroup) row;
            
            //Executed
            holder.executed.setChecked(task.isExecuted());
            
            row.setTag(holder);
        }else{
            holder = (PasientTaskHolder)row.getTag();
        }
        
        //Text size
        int size = PreferenceManager.getDefaultSharedPreferences(context).getInt("text_size", 50);
        holder.medicine.setTextSize(30*size/100);
        holder.medicineForm.setTextSize(30*size/100);
        holder.dosage.setTextSize(30*size/100);
        holder.time.setTextSize(30*size/100);
        
        //Medicine text
        Medicine medicine = ServiceFactory.getInstance().getMedicineService().getMedicine(task.getMedicineId());
        holder.medicine.setText("Medicine: " + medicine.getName());

        //Medicine form text
        holder.medicineForm.setText("Medicine Form: " + task.getMedicineForm());
        
        //Dosage
        holder.dosage.setText("Dosage: " + task.getDosage());
        
        //Time
        long now = System.currentTimeMillis();
        holder.time.setText(format.format(task.getTimestamp()));
        if(task.getTimestamp().getTime() < now && !task.isExecuted()){
        	int alpha = (int) (((now-task.getTimestamp().getTime())/(1000*60*60) + 1)*50);
        	
        	if(alpha > 255){
        		alpha = 255;
        	}
        	
        	holder.background.setBackgroundColor(Color.argb(alpha, 255, 0, 0));
        }
        
        return row;
    } 
    
    static class PasientTaskHolder{
    	ViewGroup background;
        TextView medicine;
        TextView medicineForm;
        TextView dosage;
        TextView time;
        CheckBox executed;
    }
}
