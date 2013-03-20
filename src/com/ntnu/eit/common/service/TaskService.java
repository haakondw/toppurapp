package com.ntnu.eit.common.service;

import com.ntnu.eit.common.model.Task;

public interface TaskService {

	public Task[] getTasks(int pasientId);
	
	public void setExecutedTasks(int[] indices);
	
}