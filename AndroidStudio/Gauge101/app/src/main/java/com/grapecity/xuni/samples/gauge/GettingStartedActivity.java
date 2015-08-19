package com.grapecity.xuni.samples.gauge;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.grapecity.xuni.gauge.*;

public class GettingStartedActivity extends Activity
{
	private XuniLinearGauge mLinearGauge;
	private XuniRadialGauge mRadialGauge;
	private XuniBulletGraph mBulletGraph;
	private TextView mValueText;
	private int mValue = 25;
	private int mMin = 0;
	private int mMax = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// setting the dark theme
		// Gauge automatically adjusts to the current theme
		setTheme(android.R.style.Theme_Holo);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_getting_started);

		// initializing widgets
		mRadialGauge = (XuniRadialGauge) findViewById(R.id.radialGauge1);
		mLinearGauge = (XuniLinearGauge) findViewById(R.id.linearGauge1);
		mBulletGraph = (XuniBulletGraph) findViewById(R.id.bulletGraph1);
		mValueText = (TextView) findViewById(R.id.valueText);

		// setting dafault values
		mValueText.setText(Integer.toString(mValue));
		mBulletGraph.setEnabled(false);
		mBulletGraph.setValue(mValue);
		mBulletGraph.setBad(45);
		mBulletGraph.setGood(80);
		mBulletGraph.setMin(mMin);
		mBulletGraph.setMax(mMax);
		mBulletGraph.setTarget(90);
		mBulletGraph.setShowTextType(GaugeShowText.NONE);
		mBulletGraph.setStep(1);
		mBulletGraph.setGaugeWidth(.5f);

		mLinearGauge.setEnabled(false);
		mLinearGauge.setValue(mValue);
		mLinearGauge.setMin(mMin);
		mLinearGauge.setMax(mMax);
		mLinearGauge.setStep(1);
		mLinearGauge.setShowTextType(GaugeShowText.NONE);
		mLinearGauge.setGaugeWidth(.5f);

		mRadialGauge.setEnabled(false);
		mRadialGauge.setValue(mValue);
		mRadialGauge.setMin(mMin);
		mRadialGauge.setMax(mMax);
		mRadialGauge.setStep(1);
		mRadialGauge.setShowTextType(GaugeShowText.NONE);
		mRadialGauge.setGaugeWidth(.5f);
	}

	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.buttonValueMinus:
				// decrease gauge value
				mValue -= (mValue > mMin) ? 25 : 0;
				break;
			case R.id.buttonValuePlus:
				// increase gauge value
				mValue += (mValue < mMax) ? 25 : 0;
				break;
		}
		mLinearGauge.setValue(mValue);
		mRadialGauge.setValue(mValue);
		mBulletGraph.setValue(mValue);
		mValueText.setText(Integer.toString(mValue));
	}

}
