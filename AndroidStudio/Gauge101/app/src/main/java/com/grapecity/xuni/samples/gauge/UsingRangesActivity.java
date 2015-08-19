package com.grapecity.xuni.samples.gauge;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.grapecity.xuni.gauge.*;

public class UsingRangesActivity extends Activity
{
	private XuniLinearGauge mLinearGauge;
	private Switch mSwitchRange;
	private XuniRadialGauge mRadialGauge;
	private GaugeRangeCollection mRanges = new GaugeRangeCollection();
	private int mVal = 25;
	private int mMin = 0;
	private int mMax = 100;
	private TextView mValueText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_using_ranges);

		// initializing widgets
		mSwitchRange = (Switch) findViewById(R.id.switchRange);
		mLinearGauge = (XuniLinearGauge) findViewById(R.id.linearGauge1);
		mRadialGauge = (XuniRadialGauge) findViewById(R.id.radialGauge1);
		mValueText = (TextView) findViewById(R.id.valueText);

		// setting dafault values
		mValueText.setText(Integer.toString(mVal));

		setRange(0, 40, Color.parseColor("#22B14C"));
		setRange(40, 80, Color.parseColor("#FF8080"));
		setRange(80, 100, Color.parseColor("#00A2E8"));

		mRadialGauge.setRanges(mRanges);
		mLinearGauge.setRanges(mRanges);

		mRadialGauge.setShowRanges(false);
		mLinearGauge.setShowRanges(false);

		mLinearGauge.setValue(mVal);
		mLinearGauge.setMin(mMin);
		mLinearGauge.setMax(mMax);
		mLinearGauge.setStep(1);
		mLinearGauge.setShowTextType(GaugeShowText.NONE);
		mLinearGauge.setGaugeWidth(.5f);

		mRadialGauge.setValue(mVal);
		mRadialGauge.setMin(mMin);
		mRadialGauge.setMax(mMax);
		mRadialGauge.setStep(1);
		mRadialGauge.setShowTextType(GaugeShowText.NONE);
		mRadialGauge.setGaugeWidth(.5f);

		mSwitchRange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
		{
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				// setting flag for range display
				if (isChecked)
				{
					mLinearGauge.setShowRanges(true);
					mRadialGauge.setShowRanges(true);
				}
				else
				{
					mLinearGauge.setShowRanges(false);
					mRadialGauge.setShowRanges(false);
				}
			}
		});
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.buttonMinus:
				// decrease gauge value
				mVal -= (mVal > mMin) ? 25 : 0;
				break;
			case R.id.buttonPlus:
				// increase gauge value
				mVal += (mVal < mMax) ? 25 : 0;
				break;
		}
		mLinearGauge.setValue(mVal);
		mRadialGauge.setValue(mVal);
		mValueText.setText(Integer.toString(mVal));
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
