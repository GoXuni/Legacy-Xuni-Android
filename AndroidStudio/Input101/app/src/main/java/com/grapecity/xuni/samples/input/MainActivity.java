package com.grapecity.xuni.samples.input;

import com.grapecity.xuni.samples.input.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener
{
	ListView mSampleList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// setting the license key to check for demo/full version
		com.grapecity.xuni.core.LicenseManager.KEY = License.KEY;

		// initializing listView and applying custom adapter
		mSampleList = (ListView) findViewById(R.id.listView1);
		SampleListAdapter adapter = new SampleListAdapter(this);
		mSampleList.setAdapter(adapter);
		mSampleList.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		Intent intent = new Intent();
		// initialize the intent based on user click
		switch (mSampleList.getPositionForView(view))
		{
			case 0:
				intent = new Intent(getApplicationContext(), AutoCompleteActivity.class);
				break;
			case 1:
				intent = new Intent(getApplicationContext(), ComboBoxActivity.class);
				break;
			case 2:
				intent = new Intent(getApplicationContext(), DropDownActivity.class);
				break;
			case 3:
				intent = new Intent(getApplicationContext(), BasicMaskActivity.class);
				break;
		}
		// start the new activity
		startActivity(intent);
	}
}
