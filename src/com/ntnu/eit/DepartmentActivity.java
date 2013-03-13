package com.ntnu.eit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.department.model.DepartmentListAdapter;

public class DepartmentActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_department);
		
		//Departments
		ListView listView = (ListView) findViewById(R.id.departmentList);
		listView.setAdapter(new DepartmentListAdapter(this, R.layout.department_row, 
													new Department[]{
				new Department("Department 1"),
				new Department("Department 2"),
				new Department("Department 3"),
				new Department("Department 4"),
				new Department("Department 5")
													}));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_department, menu);
		return true;
	}
	
	public void onNextClicked(View view){
		startActivity(new Intent(this, PasientsActivity.class));
	}
}