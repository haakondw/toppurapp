package com.ntnu.eit.pasient.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ntnu.eit.R;
import com.ntnu.eit.common.model.Medicine;
import com.ntnu.eit.common.model.MedicineForm;
import com.ntnu.eit.common.model.Task;
import com.ntnu.eit.common.service.ServiceFactory;

public class PasientTaskListAdapter extends ArrayAdapter<Task>{

	private Context context;
	private int textViewResourceId;
	private Task[] tasks;

	public PasientTaskListAdapter(Context context, int textViewResourceId, Task[] tasks) {
		//Super
		super(context, textViewResourceId, tasks);
		
		//Init
		this.context = context;
		this.textViewResourceId = textViewResourceId;
		this.tasks = tasks;
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View row = convertView;
    	PasientTaskHolder holder = null;
        
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(textViewResourceId, parent, false);
            
            holder = new PasientTaskHolder();
            holder.medicine = (TextView) row.findViewById(R.id.pasient_task_medicine);
            holder.medicineForm = (TextView) row.findViewById(R.id.pasient_task_medicine_form);
            holder.dosage = (TextView) row.findViewById(R.id.pasient_task_medicine_dosage);
            holder.time = (TextView) row.findViewById(R.id.pasient_task_time);
            holder.executed = (CheckBox) row.findViewById(R.id.pasient_task_checkbox);
            
            row.setTag(holder);
        }else{
            holder = (PasientTaskHolder)row.getTag();
        }
        
        Task task = tasks[position];
        
        //Medicine text
        Medicine medicine = ServiceFactory.getInstance().getMedicineService().getMedicine(task.getMedicineId());
        holder.medicine.setText("Medicine: " + medicine.getName());

        //Medicine form text
        MedicineForm medicineForm = ServiceFactory.getInstance().getMedicineFormService().getMedicineForm(task.getMedicineFormId());
        holder.medicineForm.setText("Medicine Form: " + medicineForm.getForm());
        
        //Dosage
        holder.dosage.setText("Dosage: " + task.getDosage());
        
        //Time
        holder.time.setText(task.getTimestamp().toString());
        
        //Executed
        holder.executed.setChecked(task.isExecuted());
        
        return row;
    } 
    
    static class PasientTaskHolder{
        TextView medicine;
        TextView medicineForm;
        TextView dosage;
        TextView time;
        CheckBox executed;
    }
}
