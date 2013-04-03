package com.ntnu.eit.socket;

import java.io.Serializable;
import java.util.ArrayList;


public class TaskSocketObject implements Serializable{
  
    private static final long serialVersionUID = -1460051194874732469L;
    private int patientId;
    ArrayList<Integer> executedTasks;

    public TaskSocketObject(int patientId, ArrayList<Integer> executedTasks) {
    this.patientId = patientId;
    this.executedTasks = new ArrayList<Integer>(executedTasks);
    }
    
    
    public void setPatientId(int patientId){
        this.patientId = patientId;
    } 
    public int getPatientId(){
        return patientId;
    }

    public void setExecutedTasks(ArrayList<Integer> executedTasks){
        this.executedTasks = new ArrayList<Integer>(executedTasks);
    }
    public ArrayList<Integer> getExecutedTasks(){
        return executedTasks;
    }
    
}
