package com.ntnu.eit.common.service;

import java.util.Date;

import android.util.SparseArray;

import com.ntnu.eit.common.model.Task;

public class TaskServiceTestImpl implements TaskService{

	private SparseArray<Task[]> pasientTasks;
	
	@Override
	public Task[] getTasks(int pasientId) {
		if(pasientTasks == null){
			pasientTasks = new SparseArray<Task[]>();
		}
		
		//Adding data if not existing
		if(pasientTasks.get(pasientId) == null){
			addData(pasientId);
		}

		//Returning existing
		return pasientTasks.get(pasientId);
	}

	@Override
	public void setExecutedTasks(int pasientId, int[] indices, boolean[] isExecuted) {
		//Setting tasks as executed
		Task[] tasks = pasientTasks.get(pasientId);
		
		//Updating
		for (Task task : tasks) {
			for (int i = 0; i < indices.length; i++) {
				if(task.getTaskID() == indices[i]){
					task.setExecuted(isExecuted[i]);
					break;
				}
			}
		}
		
		//Updating data
		pasientTasks.setValueAt(pasientId, tasks);
	}

	@Override
	public SparseArray<Task[]> getTasks(int[] pasientIndices) {
		if(pasientTasks == null){
			pasientTasks = new SparseArray<Task[]>();
		}	
		
		for (int i = 0; i < pasientIndices.length; i++) {
			if(pasientTasks.get(pasientIndices[i]) == null){
				addData(pasientIndices[i]);
			}
		}
		
		return pasientTasks;
	}
	
	private void addData(int pasientId){
		//Creating new shit
		Task[] tasks = new Task[10];
		for (int i = 0; i < tasks.length; i++) {
			long t = System.currentTimeMillis() + 3600000*(i+1) - 3600000*4 + 60000;
			tasks[i] = new Task(pasientId*10 + i, 1, 1, new Date(t), "dosage", false);
		}
		
		//Adding data
		pasientTasks.put(pasientId, tasks);
	}
}