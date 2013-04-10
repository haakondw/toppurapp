package com.ntnu.eit.common.service;

import java.util.ArrayList;

import android.util.SparseArray;

import com.ntnu.eit.common.model.Task;

public interface TaskService {

	public Task[] getTasks(int pasientId);
	
	public SparseArray<Task[]> getTasks(int[] pasientIndices);
	
	public void setExecutedTasks(int pasientId, int[] indices, boolean[] isExecuted);
	
	public void setTaskList(ArrayList<Task> tasks);
	
}