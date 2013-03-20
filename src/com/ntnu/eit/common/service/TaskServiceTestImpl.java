package com.ntnu.eit.common.service;

import java.util.Date;

import com.ntnu.eit.common.model.Task;

public class TaskServiceTestImpl implements TaskService{

	@Override
	public Task[] getTasks(int pasientId) {
		Task[] tasks = new Task[10];
		for (int i = 0; i < tasks.length; i++) {
			tasks[i] = new Task(i, 1, 1, new Date(System.currentTimeMillis() + 3600000*(i+1) - 3600000*4 + 60000), "dosage");
		}
		
		return tasks;
	}
}