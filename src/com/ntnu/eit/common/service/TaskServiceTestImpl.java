package com.ntnu.eit.common.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
			if(ServiceFactory.getInstance().getAuthenticationService().isDebug()){
				tasks.get(pasientId).addAll(generateTestData(pasientId));
			}
		}

		//Returning existing
		return tasks.get(pasientId);
	}

	@Override
	public List<Task> getHistoyTasks(int pasientId) {
		if(!historyTasks.containsKey(pasientId)){
			historyTasks.put(pasientId, new ArrayList<Task>());
			if(ServiceFactory.getInstance().getAuthenticationService().isDebug()){
				historyTasks.get(pasientId).addAll(generateHistTestData(pasientId));
			}
		}

		//Returning existing
		return historyTasks.get(pasientId);
	}

	@Override
	public void setExecutedTasks(int pasientId, int[] indices, boolean[] isExecuted) {
		//Updating
		if(tasks.containsKey(pasientId)){
			List<Task> list = tasks.get(pasientId);
			for (int j=0; j<list.size(); j++) {
				for (int i = 0; i < indices.length && !list.isEmpty(); i++) {
					if(list.get(j).getTaskID() == indices[i]){
						list.get(j).setExecuted(isExecuted[i]);
						if(isExecuted[i]){							
							historyTasks.get(pasientId).add(list.remove(j));
							j--;
						}
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

	private List<Task> generateTestData(int pasientId) {
		long timestamp;
		long now = System.currentTimeMillis();
		List<Task> tasks = new ArrayList<Task>();
		for(int i = 0; i<Math.random()*10 + 2; i++){
			timestamp = now + i*3600*1000;				
			Task task = new Task((int) timestamp, 1, "Form", new Date(timestamp), "50 ml", false);
			tasks.add(task);
		}
		
		return tasks;
	}

	private List<Task> generateHistTestData(int pasientId) {
		long timestamp;
		long now = System.currentTimeMillis();
		List<Task> tasks = new ArrayList<Task>();
		for(int i = 0; i<Math.random()*5 + 2; i++){
			timestamp = now - i*3600*1000;
			if(now > timestamp){								
				Task task = new Task((int) timestamp, 1, "Form", new Date(timestamp), "50 ml", true);
				tasks.add(task);
			}
		}
		
		return tasks;
	}
}