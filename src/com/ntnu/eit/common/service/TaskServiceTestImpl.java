package com.ntnu.eit.common.service;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.ntnu.eit.common.model.Task;
import com.ntnu.eit.socket.TaskClient;
import com.ntnu.eit.socket.TaskSocketObject;

public class TaskServiceTestImpl implements TaskService{

	private List<Task> tasks;
	
	@Override
	public List<Task> getTasks(int pasientId) {
		if(tasks == null){
			tasks = new ArrayList<Task>();
		}

		//Returning existing
		return tasks;
	}

	@Override
	public void setExecutedTasks(int pasientId, int[] indices, boolean[] isExecuted) {
		//Updating
		for (Task task : tasks) {
			for (int i = 0; i < indices.length; i++) {
				if(task.getTaskID() == indices[i]){
					task.setExecuted(isExecuted[i]);
					break;
				}
			}
		}
	}
	
	@Override
	public void setTaskList(ArrayList<Task> tasks){		
		this.tasks.clear();
		this.tasks.addAll(tasks);
	}
	
	@Override
	public void updateTaskList(int patientId, ArrayList<Integer> executedTasks, ArrayList<Object> adapters, Context context){
		TaskSocketObject tso = new TaskSocketObject(patientId, executedTasks);
		TaskClient tc = new TaskClient(tso, adapters, context);
		tc.execute();
	}
}