package com.grapecity.xuni.samples.gauge;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.grapecity.xuni.gauge.*;

public class AutomaticScalingActivity extends Activity
{
	private XuniRadialGauge mRadialGauge;
	private TextView mStartText;
	private TextView mSweepText;
	private Timer mTimeoutTimer;
	private final Random myRandom = new Random();
	private GenerateTask mGenTask = new GenerateTask();
	private int mRandom = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_automatic_scaling);

		// initialize required widgets
		mStartText = (TextView) findViewById(R.id.startAngleText);
		mSweepText = (TextView) findViewById(R.id.sweepAngleText);
		mRadialGauge = (XuniRadialGauge) findViewById(R.id.radialGauge1);

		// setting default values
		mStartText.setText(Double.toString(mRadialGauge.getStartAngle()));
		mSweepText.setText(Double.toString(mRadialGauge.getSweepAngle()));
		mRadialGauge.setValue(60);
		mRadialGauge.setMax(200);
		// properties set in XML layout
		// mRadialGauge.setStartAngle(0.0);
		// mRadialGauge.setSweepAngle(180.0);
		mRadialGauge.setShowTextType(GaugeShowText.ALL);
		mRadialGauge.setStep(1);
		// mRadialGauge.setGaugeWidth(.5f);
		mRadialGauge.animate();

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

	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.buttonStartMinus:
				mRadialGauge.setStartAngle(mRadialGauge.getStartAngle() - 45);
				mStartText.setText(Double.toString(mRadialGauge.getStartAngle()));
				break;
			case R.id.buttonStartPlus:
				mRadialGauge.setStartAngle(mRadialGauge.getStartAngle() + 45);
				mStartText.setText(Double.toString(mRadialGauge.getStartAngle()));
				break;
			case R.id.buttonSweepMinus:
				mRadialGauge.setSweepAngle(mRadialGauge.getSweepAngle() - 45);
				mSweepText.setText(Double.toString(mRadialGauge.getSweepAngle()));
				break;
			case R.id.buttonSweepPlus:
				mRadialGauge.setSweepAngle(mRadialGauge.getSweepAngle() + 45);
				mSweepText.setText(Double.toString(mRadialGauge.getSweepAngle()));
				break;
		}
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
						mRandom = myRandom.nextInt(200);
						mRadialGauge.setValue(mRandom);
					}
				});
			}
		}
	}
}
