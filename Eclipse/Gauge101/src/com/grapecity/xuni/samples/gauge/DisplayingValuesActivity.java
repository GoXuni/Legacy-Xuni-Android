package com.grapecity.xuni.samples.gauge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.grapecity.xuni.gauge.*;

public class DisplayingValuesActivity extends Activity
{
	private XuniLinearGauge mLinearGauge;
	private Spinner mShowTextSpinner;
	private XuniRadialGauge mRadialGauge;
	private GaugeRangeCollection mRanges = new GaugeRangeCollection();
	private double mValue = .25;
	private TextView mValueText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_displaying_values);

		// initializing widgets
		mLinearGauge = (XuniLinearGauge) findViewById(R.id.linearGauge1);
		mRadialGauge = (XuniRadialGauge) findViewById(R.id.radialGauge1);
		mShowTextSpinner = (Spinner) findViewById(R.id.showTextSpinner);
		mValueText = (TextView) findViewById(R.id.valueText);

		// creating and initializing adapter to string array
		ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.showTextSpinnerValues, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		mShowTextSpinner.setAdapter(adapter1);
		mShowTextSpinner.setOnItemSelectedListener(new OnItemSelectedListener()
		{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
			{
				switch (position)
				{
					case 0:
						mLinearGauge.setShowTextType(GaugeShowText.ALL);
						mRadialGauge.setShowTextType(GaugeShowText.ALL);
						break;
					case 1:
						mLinearGauge.setShowTextType(GaugeShowText.MINMAX);
						mRadialGauge.setShowTextType(GaugeShowText.MINMAX);
						break;
					case 2:
						mLinearGauge.setShowTextType(GaugeShowText.VALUE);
						mRadialGauge.setShowTextType(GaugeShowText.VALUE);
						break;
					case 3:
						mLinearGauge.setShowTextType(GaugeShowText.NONE);
						mRadialGauge.setShowTextType(GaugeShowText.NONE);
						break;
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{

			}
		});

		// setting dafault values
		mValueText.setText(Integer.toString((int) (mValue * 100)));

		mLinearGauge.setShowTextType(GaugeShowText.ALL);
		mRadialGauge.setShowTextType(GaugeShowText.ALL);

		setRange(0, 40, -65536);
		setRange(40, 80, -256);
		setRange(80, 100, -16711936);

		mLinearGauge.setValue(mValue);
		mLinearGauge.setMin(0);
		mLinearGauge.setMax(1);
		mLinearGauge.setStep(.01f);
		mLinearGauge.setRanges(mRanges);
		mLinearGauge.setShowRanges(false);
		mLinearGauge.setShowTextType(GaugeShowText.ALL);
		mLinearGauge.setGaugeWidth(.5f);
		mLinearGauge.animate();
		mLinearGauge.setFormat("#%");

		mRadialGauge.setValue(mValue);
		mRadialGauge.setMin(0);
		mRadialGauge.setMax(1);
		mRadialGauge.setStep(.01f);
		mRadialGauge.setRanges(mRanges);
		mRadialGauge.setShowRanges(false);
		mRadialGauge.setShowTextType(GaugeShowText.ALL);
		mRadialGauge.setGaugeWidth(.5f);
		mRadialGauge.animate();
		mRadialGauge.setFormat("#%");
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.buttonMinus:
				// decrease gauge value
				mValue -= (mValue > 0) ? .25 : 0;
				break;
			case R.id.buttonPlus:
				// increase gauge value
				mValue += (mValue < 1) ? .25 : 0;
				break;
		}
		mLinearGauge.setValue(mValue);
		mRadialGauge.setValue(mValue);
		mValueText.setText(Integer.toString((int) (mValue * 100)));
	}

	// a method to create a new range and adding it to the list
	private void setRange(double min, double max, int color)
	{
		GaugeRange range = new GaugeRange();
		range.setMin(min);
		range.setMax(max);
		range.setColor(color);
		mRanges.add(range);
	}
}
