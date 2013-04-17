package com.ntnu.eit.common.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;

import com.ntnu.eit.common.model.Task;
import com.ntnu.eit.socket.TaskClient;
import com.ntnu.eit.socket.TaskSocketObject;

public class TaskServiceTestImpl implements TaskService{

	private HashMap<Integer, List<Task>> tasks = new HashMap<Integer, List<Task>>();
	private HashMap<Integer, List<Task>> historyTasks = new HashMap<Integer, List<Task>>();
	
	private int patientId;
	
	@Override
	public List<Task> getTasks(int pasientId) {
		if(!tasks.containsKey(pasientId)){
			tasks.put(pasientId, new ArrayList<Task>());
		}

		//Returning existing
		return tasks.get(pasientId);
	}

	@Override
	public List<Task> getHistoyTasks(int pasientId) {
		if(!historyTasks.containsKey(pasientId)){
			historyTasks.put(pasientId, new ArrayList<Task>());
		}

		//Returning existing
		return historyTasks.get(pasientId);
	}

	@Override
	public void setExecutedTasks(int pasientId, int[] indices, boolean[] isExecuted) {
		//Updating
		if(tasks.containsKey(pasientId)){
			List<Task> list = tasks.get(pasientId);
			for (Task task : list) {
				for (int i = 0; i < indices.length; i++) {
					if(task.getTaskID() == indices[i]){
						task.setExecuted(isExecuted[i]);
						break;
					}
				}
			}
		}
	}
	
	@Override
	public void setTaskList(ArrayList<Task> tasks){
		List<Task> tas = this.tasks.get(this.patientId);
		List<Task> hist = this.historyTasks.get(this.patientId);
		
		tas.clear();
		hist.clear();
		
		for (Task task : tasks) {
			if(task.isExecuted()){
				hist.add(task);
			}else{
				tas.add(task);
			}
		}
	}
	
	@Override
	public void updateTaskList(int patientId, ArrayList<Integer> executedTasks, ArrayList<Object> adapters, Context context){
		this.patientId = patientId;
		TaskSocketObject tso = new TaskSocketObject(patientId, executedTasks);
		TaskClient tc = new TaskClient(tso, adapters, context);
		tc.execute();
	}
}