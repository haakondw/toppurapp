package com.ntnu.eit;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ntnu.eit.common.dialog.ConfirmationDialog;
import com.ntnu.eit.common.dialog.ConfirmationDialog.ConfirmationDialogListener;
import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Patient;
import com.ntnu.eit.common.model.Task;
import com.ntnu.eit.common.service.ServiceFactory;
import com.ntnu.eit.pasient.model.PatientTaskListAdapter;

public class PatientActivity extends FragmentActivity {

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	private int pasientId;
	private static Patient pasient;
	private static List<Task> tasks, historyTasks;
	private static PatientTaskListAdapter historyAdapter;
	private static PatientTaskListAdapter tasksAdapter;
	private boolean running;

	private Runnable runnable = new Runnable() {
		@Override
		public void run() {
			Log.d("EiT", "Task Update");
			ArrayList<Object> adapters = new ArrayList<Object>();
			ArrayList<Integer> executedTasks = new ArrayList<Integer>();
			
			adapters.add(tasksAdapter);
			adapters.add(historyAdapter);
			ServiceFactory.getInstance().getTaskService().updateTaskList(pasient.getPatientID(), executedTasks, adapters, PatientActivity.this);
		}
	};
	
	/**
	 * EXTRAS
	 */
	public static final String PASIENT_ID_TAG = "PASIENT_ID_TAG";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//This
		setContentView(R.layout.activity_patient);
		running = true;
		
		//Starting
		pasientId = getIntent().getExtras().getInt(PASIENT_ID_TAG);
		tasks = ServiceFactory.getInstance().getTaskService().getTasks(pasientId);
		historyTasks = ServiceFactory.getInstance().getTaskService().getHistoyTasks(pasientId);
		pasient = ServiceFactory.getInstance().getPatientService().getPatientById(pasientId);

		historyAdapter = new PatientTaskListAdapter(this, R.layout.patient_task_row, historyTasks);
		tasksAdapter = new PatientTaskListAdapter(this, R.layout.patient_task_row, tasks);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		mViewPager.setCurrentItem(1);

		if(!ServiceFactory.getInstance().getAuthenticationService().isDebug()){			
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					while(running){						
						Log.d("EiT", "Updating tasks");
						PatientActivity.this.runOnUiThread(runnable);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			});
			thread.start();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		running = false;
	}
	
	@Override
	protected void onResume() {
		//Super
		super.onResume();
		
		if(!ServiceFactory.getInstance().getAuthenticationService().isLoggedIn()){
			finish();
		}
		
		//Init
		int size = PreferenceManager.getDefaultSharedPreferences(this).getInt("text_size", 50);
		
		//Name size
		TextView textView = (TextView) findViewById(R.id.pasientName);
		if(textView != null){
			textView.setTextSize(50*size/100);
		}
		
		//Department size
		textView = (TextView) findViewById(R.id.pasientDepartment);
		if(textView != null){
			textView.setTextSize(50*size/100);
		}
		
		//Department size
		Button submitButton = (Button) findViewById(R.id.pasientSubmitButton);
		Button deviationButton = (Button) findViewById(R.id.pasientRegisterDeviationButton);
		if(submitButton != null){
			submitButton.setTextSize(50*size/100);
		}
		if(deviationButton != null){
			deviationButton.setTextSize(50*size/100);
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:
			startActivity(new Intent(this, LoginSettingsActivity.class));
			return true;
		case R.id.logout:
			ServiceFactory.getInstance().getAuthenticationService().logout();
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_common, menu);
		return true;
	}
	
	public void onRegisterDeviation(View view){
		Intent intent = new Intent(this, DeviationActivity.class);
		intent.putExtra(DeviationActivity.PARAM_PATIENT_ID, pasientId);
		startActivity(intent);
	}
	
	public void onTasksSubmit(View view){
		ConfirmationDialog.create(this, "Bekreft signering", new ConfirmationDialogListener() {
			@Override
			public void onConfirm(ConfirmationDialog dialog) {
				//Init
				ListView listView = (ListView) findViewById(R.id.pasientTaskList);
				ArrayList<Integer> indices = new ArrayList<Integer>();

				//Checking tasks
				int count = listView.getAdapter().getCount();
				for (int i = 0; i < count; i++) {
					Task task = (Task) listView.getAdapter().getItem(i);
					ViewGroup viewGroup = (ViewGroup) listView.getChildAt(i);
					
					if(viewGroup != null){										
						CheckBox box = (CheckBox) viewGroup.findViewById(R.id.pasient_task_checkbox);
						if(box.isChecked()){
							indices.add(task.getTaskID());							
						}
					}
				}

				//Checking history tasks
				listView = (ListView) findViewById(R.id.pasient_task_history_list);
				count = listView.getAdapter().getCount();
				for (int i = 0; i < count; i++) {
					Task task = (Task) listView.getAdapter().getItem(i);		
					ViewGroup viewGroup = (ViewGroup) listView.getChildAt(i);	
					
					if(viewGroup != null){										
						CheckBox box = (CheckBox) viewGroup.findViewById(R.id.pasient_task_checkbox);
						if(box.isChecked()){
							indices.add(task.getTaskID());							
						}
					}
				}
				
				//Updating
				ArrayList<Object> adapters = new ArrayList<Object>();
				
				adapters.add(tasksAdapter);
				adapters.add(historyAdapter);
				ServiceFactory.getInstance().getTaskService().updateTaskList(pasient.getPatientID(), indices, adapters, PatientActivity.this);
				
				//Dialog
				dialog.dismiss();
				
				//Destroying activity
				PatientActivity.this.finish();
			}
			
			@Override
			public void onCancel(ConfirmationDialog dialog) {
				dialog.dismiss();
			}
		}).show();
	}
	
	@Override
	public void onBackPressed() {
		Task[] changes = getChanges();
		
		if(changes.length > 0){			
			ConfirmationDialog.create(this, "Det finnes usignerte endringer", "Gå tilbake", "Signer endringer", new ConfirmationDialogListener() {
				@Override
				public void onConfirm(ConfirmationDialog dialog) {
					//Init
					ListView listView = (ListView) findViewById(R.id.pasientTaskList);
					List<Integer> indices = new ArrayList<Integer>();
					List<Boolean> checked = new ArrayList<Boolean>();
					
					//Checking tasks
					int count = listView.getAdapter().getCount();
					for (int i = 0; i < count; i++) {
						Task task = (Task) listView.getAdapter().getItem(i);
						ViewGroup viewGroup = (ViewGroup) listView.getChildAt(i);
						
						if(viewGroup != null){			
							CheckBox box = (CheckBox)viewGroup.findViewById(R.id.pasient_task_checkbox);
							if(box != null){								
								boolean temp = box.isChecked();
								
								indices.add(task.getTaskID());
								checked.add(temp);
							}
						}
					}
					
					//Checking history tasks
					listView = (ListView) findViewById(R.id.pasient_task_history_list);
					count = listView.getAdapter().getCount();
					for (int i = 0; i < count; i++) {
						Task task = (Task) listView.getAdapter().getItem(i);
						ViewGroup viewGroup = (ViewGroup) listView.getChildAt(i);
						boolean temp = ((CheckBox)viewGroup.findViewById(R.id.pasient_task_checkbox)).isChecked();
						
						indices.add(task.getTaskID());
						checked.add(temp);
					}
					
					//Creating temp array, and setting tasks as executed
					int[] array = new int[indices.size()];
					boolean[] array2 = new boolean[checked.size()];
					for (int i = 0; i < array.length; i++) {
						array[i] = indices.get(i);
						array2[i] = checked.get(i);
					}
					
					//Updating
					ServiceFactory.getInstance().getTaskService().setExecutedTasks(pasientId, array, array2);
					
					//Dialog
					dialog.dismiss();
					
					//Destroying activity
					PatientActivity.this.finish();
				}
				
				@Override
				public void onCancel(ConfirmationDialog dialog) {
					dialog.dismiss();
					PatientActivity.this.finish();
				}
			}).show();
		}else{
			super.onBackPressed();
		}
	}

	private Task[] getChanges(){
		//Checking history tasks
		List<Task> tasks = new ArrayList<Task>();
		ListView listView = (ListView) findViewById(R.id.pasient_task_history_list);
		int count = listView.getAdapter().getCount();
		
		for (int i = 0; i < count; i++) {
			ViewGroup child = (ViewGroup) listView.getChildAt(i);
			Task task = (Task) listView.getAdapter().getItem(i);
			
			boolean isChecked = ((CheckBox)child.findViewById(R.id.pasient_task_checkbox)).isChecked();
			
			if(isChecked != task.isExecuted()){
				tasks.add(task);
			}
		}
		
		//Checking tasks
		listView = (ListView) findViewById(R.id.pasientTaskList);
		count = listView.getAdapter().getCount();
		
		for (int i = 0; i < count; i++) {
			ViewGroup child = (ViewGroup) listView.getChildAt(i);
			Task task = (Task) listView.getAdapter().getItem(i);
			
			if(child != null){				
				CheckBox box = (CheckBox)child.findViewById(R.id.pasient_task_checkbox);
				if(box != null){				
					boolean isChecked = box.isChecked();
					
					if(isChecked != task.isExecuted()){
						tasks.add(task);
					}
				}
			}
		}
		
		return tasks.toArray(new Task[tasks.size()]);
	}
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = new DummySectionFragment();
			
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			args.putInt(DummySectionFragment.ARG_PASIENT_ID, pasientId);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return getResources().getText(R.string.title_section1);
			case 1:
				return getResources().getText(R.string.title_section2);
			case 2:
				return getResources().getText(R.string.title_section3);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class DummySectionFragment extends Fragment {
		
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";
		public static final String ARG_PASIENT_ID = "pasient_id";

		public DummySectionFragment() {
			
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			// Create a new TextView and set its text to the fragment's section
			// number argument value.
			int section = getArguments().getInt(ARG_SECTION_NUMBER);
			int size = PreferenceManager.getDefaultSharedPreferences(inflater.getContext()).getInt("text_size", 50);
			
			switch (section) {
			case 1:
				//Init
				View section1view = inflater.inflate(R.layout.patient_section_one, container, false);
				ListView taskHistory = (ListView)section1view.findViewById(R.id.pasient_task_history_list);
				taskHistory.setAdapter(historyAdapter);
				
				return section1view;
			case 2:
				//Init
				View section2view = inflater.inflate(R.layout.patient_section_two, container, false);
				
				//Getting views
				TextView name = (TextView) section2view.findViewById(R.id.pasientName);
				TextView department = (TextView) section2view.findViewById(R.id.pasientDepartment);
				ImageView imageView = (ImageView) section2view.findViewById(R.id.pasientImage);
				View notification = (View) section2view.findViewById(R.id.pasientNotofication);
				ListView taskListView = (ListView)section2view.findViewById(R.id.pasientTaskList);
				Button submitButton = (Button) section2view.findViewById(R.id.pasientSubmitButton);
				
				//Text size
				name.setTextSize(50*size/100);
				department.setTextSize(30*size/100);
				submitButton.setTextSize(50*size/100);
				
				//Test colors
				notification.setBackgroundColor(Color.RED);
				
				taskListView.setAdapter(tasksAdapter);
				
				//Setting pasient name
				name.setText(pasient.getFirstname() + ", " + pasient.getLastname());
				
				//Setting department
				Department deptemp = ServiceFactory.getInstance().getDepartmentService().getDepartmentById(pasient.getDepartmentID());
				department.setText(deptemp.getName());
				
				//Setting picture
				if(imageView != null){			
					if(pasient.getPicture() == null){					
				        int picture = 0;
				        switch (pasient.getPatientID()%3) {
						case 0:
							picture = R.drawable.old_man1;
							break;
						case 1:
							picture = R.drawable.old_man2;
							break;
						case 2:
							picture = R.drawable.old_man3;
							break;
						}
				        
				        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), picture);
				        if(bitmap != null){        	
				        	imageView.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 200, 200, false));
				        }
					}else{
						imageView.setImageBitmap(BitmapFactory.decodeByteArray(pasient.getPicture(), 0, pasient.getPicture().length));
						imageView.setBackgroundColor(Color.BLUE);
					}
				}
				
				return section2view;
			case 3:
				//Init
				View section3view = inflater.inflate(R.layout.patient_section_three, container, false);
				
				//Picture
				ImageView imageView2 = (ImageView) section3view.findViewById(R.id.pasientImage);
				if(imageView2 != null){					
					if(pasient.getPicture() == null){							
				        int picture = 0;
				        switch (pasient.getPatientID()) {
						case 1:
							picture = R.drawable.old_man1;
							break;
						case 2:
							picture = R.drawable.old_man2;
							break;
						case 3:
							picture = R.drawable.old_man3;
							break;
						}
				        imageView2.setImageResource(picture);
					}else{
						imageView2.setImageBitmap(BitmapFactory.decodeByteArray(pasient.getPicture(), 0, pasient.getPicture().length));
						imageView2.setBackgroundColor(Color.BLUE);
					}
				}
				
				return section3view;
			}

			return null;
		}
	}


	public void onTaskClick2(View view){
		onTaskClick2((View) view.getParent());
	}
	
	public void onTaskClick(View view){
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.pasient_task_checkbox);
		checkBox.setChecked(!checkBox.isChecked());
	}
}
