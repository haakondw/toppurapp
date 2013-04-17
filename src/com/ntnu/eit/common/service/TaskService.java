package com.ntnu.eit.common.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.ntnu.eit.common.model.Task;

public interface TaskService {

	public List<Task> getTasks(int pasientId);
	
	public void setExecutedTasks(int pasientId, int[] indices, boolean[] isExecuted);
	
	public void setTaskList(ArrayList<Task> tasks);
	
	public void updateTaskList(int patientId, ArrayList<Integer> executedTasks, ArrayList<Object> adapters, Context context);
	
}