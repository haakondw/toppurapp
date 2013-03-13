package com.ntnu.eit;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ntnu.eit.common.model.Pasient;
import com.ntnu.eit.common.service.ServiceFactory;

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
		setContentView(R.layout.activity_pasient);
		
		Log.d("EiT", getClass().getSimpleName() + " onCreate()");
		
		//Starting
		pasientId = getIntent().getExtras().getInt(PASIENT_ID_TAG);

		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
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
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
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
				return inflater.inflate(R.layout.pasient_section_one, container, false);
			case 2:
				//Init
				View view = inflater.inflate(R.layout.pasient_section_two, container, false);
				
				//Setting data
				TextView name = (TextView) view.findViewById(R.id.pasientName);
				TextView department = (TextView) view.findViewById(R.id.pasientDepartment);
				ImageView imageView = (ImageView) view.findViewById(R.id.pasientImage);
				View notification = (View) view.findViewById(R.id.pasientNotofication);
				ListView listView = (ListView)view.findViewById(R.id.pasientTaskList);
				
				listView.setBackgroundColor(Color.BLACK);
				notification.setBackgroundColor(Color.RED);
				name.setText(pasient.getFirstName() + ", " + pasient.getLastName());
				department.setText(pasient.getDepartment().getName());
				
				
				imageView.setImageBitmap(Bitmap.createBitmap(100, 100, Config.RGB_565));
				imageView.setBackgroundColor(Color.BLUE);
				
				return view;
			case 3:
				return inflater.inflate(R.layout.pasient_section_three, container, false);
			}

			return null;
		}
	}

}
