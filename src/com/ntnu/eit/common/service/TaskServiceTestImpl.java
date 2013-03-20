package com.ntnu.eit.common.service;

import java.util.Date;

import com.ntnu.eit.common.model.Task;

public class TaskServiceTestImpl implements TaskService{

	@Override
	public Task[] getTasks(int pasientId) {
		Task[] tasks = new Task[10];
		long now = System.currentTimeMillis();
		for (int i = 0; i < tasks.length; i++) {
			long t = System.currentTimeMillis() + 3600000*(i+1) - 3600000*4 + 60000;
			
			tasks[i] = new Task(i, 1, 1, new Date(t), "dosage", t < now);
		}
		
		return tasks;
	}

	@Override
	public void setExecutedTasks(int[] indices) {
		
	}
}