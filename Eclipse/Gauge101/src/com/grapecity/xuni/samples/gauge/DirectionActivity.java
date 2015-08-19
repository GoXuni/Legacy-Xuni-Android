package com.grapecity.xuni.samples.gauge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.grapecity.xuni.gauge.GaugeShowText;
import com.grapecity.xuni.gauge.LinearGaugeDirection;
import com.grapecity.xuni.gauge.XuniBulletGraph;
import com.grapecity.xuni.gauge.XuniLinearGauge;

public class DirectionActivity extends Activity
{
	private XuniBulletGraph mBulletGraph;
	private XuniLinearGauge mLinearGauge;
	private Spinner mDirectionSpinner;
	private LinearLayout mLayout;
	private LinearLayout.LayoutParams mParamsHorizontal = new LinearLayout.LayoutParams(100, LinearLayout.LayoutParams.MATCH_PARENT);
	private LinearLayout.LayoutParams mParamsVertical = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 100);

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_direction);

		// initializing widgets
		mBulletGraph = (XuniBulletGraph) findViewById(R.id.bulletGraph1);
		mLinearGauge = (XuniLinearGauge) findViewById(R.id.linearGauge1);
		mDirectionSpinner = (Spinner) findViewById(R.id.directionSpinner);
		mLayout = (LinearLayout) findViewById(R.id.layout1);

		// creating and initializing adapter to string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.directionSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mDirectionSpinner.setAdapter(adapter1);
		mDirectionSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				// setting the direction of each gauge and applying a new layout
				switch (position)
				{
					case 0:
						mLinearGauge.setDirection(LinearGaugeDirection.RIGHT);
						mBulletGraph.setDirection(LinearGaugeDirection.RIGHT);
						mBulletGraph.setLayoutParams(mParamsVertical);
						mLinearGauge.setLayoutParams(mParamsVertical);
						mLayout.setOrientation(LinearLayout.VERTICAL);
						break;
					case 1:
						mLinearGauge.setDirection(LinearGaugeDirection.LEFT);
						mBulletGraph.setDirection(LinearGaugeDirection.LEFT);
						mBulletGraph.setLayoutParams(mParamsVertical);
						mLinearGauge.setLayoutParams(mParamsVertical);
						mLayout.setOrientation(LinearLayout.VERTICAL);
						break;
					case 2:
						mLinearGauge.setDirection(LinearGaugeDirection.DOWN);
						mBulletGraph.setDirection(LinearGaugeDirection.DOWN);
						mBulletGraph.setLayoutParams(mParamsHorizontal);
						mLinearGauge.setLayoutParams(mParamsHorizontal);
						mLayout.setOrientation(LinearLayout.HORIZONTAL);
						break;
					case 3:
						mLinearGauge.setDirection(LinearGaugeDirection.UP);
						mBulletGraph.setDirection(LinearGaugeDirection.UP);
						mBulletGraph.setLayoutParams(mParamsHorizontal);
						mLinearGauge.setLayoutParams(mParamsHorizontal);
						mLayout.setOrientation(LinearLayout.HORIZONTAL);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		// setting dafault values
		mBulletGraph.setValue(40);
		mBulletGraph.setBad(45);
		mBulletGraph.setGood(80);
		mBulletGraph.setMax(100);
		mBulletGraph.setTarget(90);
		mBulletGraph.setStep(1);
		mBulletGraph.setGaugeWidth(.5f);

		mLinearGauge.setValue(40);
		mLinearGauge.setMin(0);
		mLinearGauge.setMax(100);
		mLinearGauge.setShowTextType(GaugeShowText.ALL);
		mLinearGauge.setGaugeWidth(.5f);
	}
}