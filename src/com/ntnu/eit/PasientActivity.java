package com.ntnu.eit;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ntnu.eit.common.model.Department;
import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.common.model.Task;
import com.ntnu.eit.common.service.ServiceFactory;
import com.ntnu.eit.pasient.model.PasientTaskListAdapter;

public class PasientActivity extends FragmentActivity {

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
	
	/**
	 * EXTRAS
	 */
	public static final String PASIENT_ID_TAG = "PASIENT_ID_TAG";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//This
		setContentView(R.layout.activity_pasient);
		
		//Starting
		pasientId = getIntent().getExtras().getInt(PASIENT_ID_TAG);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		mViewPager.setCurrentItem(1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_pasient, menu);
		return true;
	}
	
	public void onSubmit(View view){
		Log.d("EiT", "onSubmit()");
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
				return "History";
			case 1:
				return "Tasks";
			case 2:
				return "About";
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
			Pasient pasient = ServiceFactory.getInstance().getPasientService().getPasientById(getArguments().getInt(ARG_PASIENT_ID));
			
			switch (section) {
			case 1:
				//Init
				View section1view = inflater.inflate(R.layout.pasient_section_one, container, false);
				ListView taskHistory = (ListView)section1view.findViewById(R.id.pasient_task_history_list);
				
				//Task history
				//Task list
				Task[] historyTasks = ServiceFactory.getInstance().getTaskService().getTasks(pasient.getPasientID());
				List<Task> historyTasksTemp = new ArrayList<Task>();
				long histNow = System.currentTimeMillis();
				for (int i = 0; i < historyTasks.length; i++) {
					if(historyTasks[i].getTimestamp().getTime() < histNow){
						historyTasksTemp.add(historyTasks[i]);
					}
				}
				
				historyTasks = historyTasksTemp.toArray(new Task[historyTasksTemp.size()]);
				taskHistory.setAdapter(new PasientTaskListAdapter(getActivity(), R.layout.pasient_task_row, historyTasks));
				
				return section1view;
			case 2:
				//Init
				View section2view = inflater.inflate(R.layout.pasient_section_two, container, false);
				
				//Getting views
				TextView name = (TextView) section2view.findViewById(R.id.pasientName);
				TextView department = (TextView) section2view.findViewById(R.id.pasientDepartment);
				ImageView imageView = (ImageView) section2view.findViewById(R.id.pasientImage);
				View notification = (View) section2view.findViewById(R.id.pasientNotofication);
				ListView taskListView = (ListView)section2view.findViewById(R.id.pasientTaskList);
				
				//Test colors
				notification.setBackgroundColor(Color.RED);
				
				//Task list
				Task[] tasks = ServiceFactory.getInstance().getTaskService().getTasks(pasient.getPasientID());
				List<Task> temp = new ArrayList<Task>();
				long now = System.currentTimeMillis();
				for (int i = 0; i < tasks.length; i++) {
					if(tasks[i].getTimestamp().getTime() >= now){
						temp.add(tasks[i]);
					}
				}
				
				tasks = temp.toArray(new Task[temp.size()]);
				taskListView.setAdapter(new PasientTaskListAdapter(getActivity(), R.layout.pasient_task_row, tasks));
				
				//Setting pasient name
				name.setText(pasient.getFirstname() + ", " + pasient.getLastname());
				
				//Setting department
				Department deptemp = ServiceFactory.getInstance().getDepartmentService().getDepartmentById(pasient.getDepartmentID());
				department.setText(deptemp.getName());
				
				//Setting picture
				if(pasient.getPicture() == null){					
					imageView.setImageBitmap(Bitmap.createBitmap(100, 100, Config.RGB_565));
					imageView.setBackgroundColor(Color.BLUE);
				}else{
					imageView.setImageBitmap(BitmapFactory.decodeByteArray(pasient.getPicture(), pasient.getPictureOffset(), pasient.getPicture().length));
					imageView.setBackgroundColor(Color.BLUE);
				}
				
				return section2view;
			case 3:
				return inflater.inflate(R.layout.pasient_section_three, container, false);
			}

			return null;
		}
	}

	public void onTaskClick(View view){
		CheckBox checkBox = (CheckBox) view.findViewById(R.id.pasient_task_checkbox);
		checkBox.setChecked(!checkBox.isChecked());
	}
}
