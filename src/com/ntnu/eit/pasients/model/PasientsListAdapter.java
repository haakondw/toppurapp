package com.ntnu.eit.pasients.model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ntnu.eit.R;
import com.ntnu.eit.common.model.Pasient;

public class PasientsListAdapter extends ArrayAdapter<Pasient>{

	private Context context;
	private Pasient[] data;
	private int textViewResourceId;

	public PasientsListAdapter(Context context, int textViewResourceId, Pasient[] data) {
		super(context, textViewResourceId, data);
		
		this.data = data;
		this.context = context;
		this.textViewResourceId = textViewResourceId;
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
            holder.clockView = (TextView) row.findViewById(R.id.pasientClock);
            holder.pictureView = (ImageView) row.findViewById(R.id.pasientImage);
            
            row.setTag(holder);
        }else{
            holder = (PasientHolder)row.getTag();
        }
        
        Pasient pasient = data[position];
        holder.nameView.setText(pasient.getFirstName() + ", " + pasient.getLastName());
        holder.pictureView.setImageBitmap(pasient.getPicture());
        
        //TODO
        //Set clock text
        holder.clockView.setText("Clock");
        
        return row;
    } 
    
    static class PasientHolder
    {
        TextView nameView;
        TextView clockView;
        ImageView pictureView;
    }

}