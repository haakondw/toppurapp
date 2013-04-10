package com.ntnu.eit.department.model;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ntnu.eit.R;
import com.ntnu.eit.common.model.Department;

public class DepartmentListAdapter extends ArrayAdapter<Department>{

	private Context context;
	private Department[] data;
	private int textViewResourceId;

	public DepartmentListAdapter(Context context, int textViewResourceId, Department[] data) {
		super(context, textViewResourceId, data);
		
		this.data = data;
		this.context = context;
		this.textViewResourceId = textViewResourceId;
	}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	View row = convertView;
    	DepartmentHolder holder = null;
        
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(textViewResourceId, parent, false);
            
            holder = new DepartmentHolder();
            holder.txtTitle = (TextView) row.findViewById(R.id.departmentName);
            holder.checkBox = (CheckBox) row.findViewById(R.id.departmentCheckBox);
            
            //Text size
    		int size = PreferenceManager.getDefaultSharedPreferences(context).getInt("text_size", 50);
    		holder.txtTitle.setTextSize(50*size/100);
            
            row.setTag(holder);
        }else{
            holder = (DepartmentHolder)row.getTag();
        }
        
        Department department = data[position];
        holder.txtTitle.setText(department.getName());
        
        return row;
    } 
    
    static class DepartmentHolder
    {
        TextView txtTitle;
        CheckBox checkBox;
    }
}