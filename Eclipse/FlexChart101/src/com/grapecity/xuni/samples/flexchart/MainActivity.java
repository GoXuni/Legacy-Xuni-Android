package com.grapecity.xuni.samples.flexchart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener
{
	private ListView mSampleList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// setting the license key to check for demo/full version
		com.grapecity.xuni.core.LicenseManager.KEY = License.KEY;

		// initializing listView and applying custom adaptor
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
				intent = new Intent(getApplicationContext(), GettingStartedActivity.class);
				break;
			case 1:
				intent = new Intent(getApplicationContext(), BasicChartTypesActivity.class);
				break;
			case 2:
				intent = new Intent(getApplicationContext(), MixedChartTypesActivity.class);
				break;
			case 3:
				intent = new Intent(getApplicationContext(), FinancialChartTypesActivity.class);
				break;
			case 4:
				intent = new Intent(getApplicationContext(), BubbleChartActivity.class);
				break;
			case 5:
				intent = new Intent(getApplicationContext(), CustomTooltipsActivity.class);
				break;
			case 6:
				intent = new Intent(getApplicationContext(), DataLabelsActivity.class);
				break;
			case 7:
				intent = new Intent(getApplicationContext(), ThemingActivity.class);
				break;
			case 8:
				intent = new Intent(getApplicationContext(), StylingSeriesActivity.class);
				break;
			case 9:
				intent = new Intent(getApplicationContext(), CustomizingAxesActivity.class);
				break;
			case 10:
				intent = new Intent(getApplicationContext(), LegendAndTitlesActivity.class);
				break;
			case 11:
				intent = new Intent(getApplicationContext(), SelectionModesActivity.class);
				break;
			case 12:
				intent = new Intent(getApplicationContext(), ToggleSeriesActivity.class);
				break;
			case 13:
				intent = new Intent(getApplicationContext(), AnimationOptionsActivity.class);
				break;
			case 14:
				intent = new Intent(getApplicationContext(), UpdateAnimationActivity.class);
				break;
			case 15:
				intent = new Intent(getApplicationContext(), ConditionalFormattingActivity.class);
				break;
			case 16:
				intent = new Intent(getApplicationContext(), DynamicChartsActivity.class);
				break;
			case 17:
				intent = new Intent(getApplicationContext(), ScrollingActivity.class);
				break;
			case 18:
				intent = new Intent(getApplicationContext(), ZoomingAndScrollingActivity.class);
				break;
			case 19:
				intent = new Intent(getApplicationContext(), HitTestActivity.class);
				break;
			case 20:
				intent = new Intent(getApplicationContext(), MultipleAxesActivity.class);
				break;
			case 21:
				intent = new Intent(getApplicationContext(), CustomPlotElementsActivity.class);
				break;
			case 22:
				intent = new Intent(getApplicationContext(), SnapshotActivity.class);
				break;
		}
		// start the new activity
		startActivity(intent);
	}
}
