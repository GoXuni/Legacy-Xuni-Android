package com.grapecity.xuni.samples.gauge;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;

import com.grapecity.xuni.gauge.*;

public class CustomAnimationActivity extends Activity
{
	private XuniRadialGauge mRadialGauge;
	private XuniLinearGauge mLinearGauge;
	private GaugeRangeCollection mRanges = new GaugeRangeCollection();
	private Timer mTimeoutTimer;
	private final Random mMyRandom = new Random();
	private GenerateTask mGenTask = new GenerateTask();
	private int mRandom = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_animation);

		// initializing widgets
		mRadialGauge = (XuniRadialGauge) findViewById(R.id.radialGauge1);
		mLinearGauge = (XuniLinearGauge) findViewById(R.id.linearGauge1);

		// disabling the touch events on the gauges
		mRadialGauge.setEnabled(false);
		mLinearGauge.setEnabled(false);

		// setting default values
		setRange(0, 40, -65536);
		setRange(40, 80, -256);
		setRange(80, 100, -16711936);

		mRadialGauge.setRanges(mRanges);
		mLinearGauge.setRanges(mRanges);

		mRadialGauge.setShowRanges(true);
		mLinearGauge.setShowRanges(true);

		mRadialGauge.setValue(60);
		mRadialGauge.setMin(0);
		mRadialGauge.setMax(100);
		mRadialGauge.setShowTextType(GaugeShowText.ALL);
		mRadialGauge.setGaugeWidth(.5f);

		mLinearGauge.setValue(60);
		mLinearGauge.setMin(0);
		mLinearGauge.setMax(100);
		mLinearGauge.setShowTextType(GaugeShowText.NONE);
		mLinearGauge.setGaugeWidth(.5f);
		mRadialGauge.animate();
		mLinearGauge.animate();

		// setting timer for auto scaling
		if (!mGenTask.started)
		{
			mGenTask.started = true;
			mTimeoutTimer = new Timer();
			mTimeoutTimer.scheduleAtFixedRate(mGenTask, 3000, 3000);
		}
		else
		{
			mGenTask.started = false;
			mTimeoutTimer.cancel();
		}
	}

	private void setRange(double min, double max, int color)
	{
		GaugeRange range = new GaugeRange();
		range.setMin(min);
		range.setMax(max);
		range.setColor(color);
		mRanges.add(range);
	}

	class GenerateTask extends TimerTask
	{
		boolean started = false;

		@Override
		public void run()
		{
			if (started)
			{
				runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						// generating a random number for each run
						mRandom = mMyRandom.nextInt(100);
						mRadialGauge.setValue(mRandom);
						mLinearGauge.setValue(mRandom);
					}
				});
			}
		}
	}
}
